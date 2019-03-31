package ISMCTS;

import Game.TakeChess;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

public class basicISMCT {
    private Node root;
    private int itermax;
    private transient Random random;


    public basicISMCT(Node node, int itermax) {
        this.root = node;
        this.itermax = itermax;
        this.random = new Random();
    }

    public basicISMCT(String player) {
        this.root = new Node(player);
        this.itermax = 1000;
        this.random = new Random();
    }

    public void Run(State rootstate) {
        Node node = root;
        for (int i = 0; i < itermax; i++) {
            //System.out.println("迭代次数"+i);
            String playerJustMoved;
            State state = new State();
            state.clone(rootstate);
			/*
			if (state.getActions().size() < 1) {
				//	System.out.println("已满");
				Action actionend = new Action(999, 999);
				break;
			}
           */
            //select
            while (IsFullyExpanded(state, node)) {
                node = node.ucbSelectChild(state.getActions());
                state.doAction(node.action);
            }
            //System.out.println("选择完毕");
            //expand
            List<Action> untriedAction = node.getUntriedMoves(state.getActions());
            if (untriedAction.size() > 0) {
                Action action = untriedAction.get(random.nextInt(untriedAction.size()));
                playerJustMoved = state.player;
                state.doAction(action);
                node = node.addChild(action, playerJustMoved);
            }
            //System.out.println("扩展完毕");
            //simulate
            List<Action> nextMove = state.getActions();
            while (state.getActions().size() > 0) {
                playerJustMoved = state.player;
                Action newAction = state.doAction(nextMove.get(random.nextInt(nextMove.size())));
                TakeChess takeChess = new TakeChess(state.board);
                String islegal = takeChess.Judgement(playerJustMoved, newAction.x, newAction.y);
                if (islegal == "illegal") {
                    state.board[newAction.x][newAction.y] = state.player;
                }
                if (islegal == "take") {
                    ArrayList deadList = new ArrayList();
                    deadList = takeChess.killDeadLife();
                    int deadNum = 0;
                    int m = 0;
                    int n = 0;
                    for (int ii = 0; ii < deadList.size(); ii++) {
                        deadNum = (Integer) deadList.get(ii);
                        m = deadNum / 9;
                        n = deadNum % 9;
                        state.board[m][n] = "0";
                    }
                }

            }
            //System.out.println("模拟完毕");
            //backpropagete
            while (node.parent != null) {
                node.update(state);
                node = node.parent;
            }
            //System.out.println("回溯完毕");
        }

    }

    public Action GetAction() {
        int temp = 0;
        int sum = 0;
        Node bestchild = null;
        for (Node child : root.children) {
            temp = child.visits;
            if (temp > sum) {
                sum = temp;
                bestchild = child;
            }
        }
        return bestchild.action;
    }

    public boolean IsFullyExpanded(State state, Node node) {
        List<Action> actions_1 = state.getActions();
        List<Action> actions_2 = node.getUntriedMoves(state.getActions());
        if (actions_1.size() >= 1 && actions_2.size() < 1) {
            return true;
        } else
            return false;
    }
}
