package ISMCTS;

import Game.TakeChess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ISMCTS {

    private Node root;
    private int itermax;
    private transient Random random;
    private InformationSet set;

    public ISMCTS(Node node, int itermax, InformationSet set) {
        this.root = node;
        this.itermax = itermax;
        this.random = new Random();
        this.set = set;
    }

    public ISMCTS(String player, InformationSet set) {
        this.root = new Node(player);
        this.itermax = 1000;
        this.random = new Random();
        this.set = set;
    }

    public void Run(State rootState) {
        Node node = root;
        for (int i = 0; i < itermax; i++) {
            String playerJustMoved;
            State state = new State();
            state.clone(rootState);

            while (isFullyExpanded(state, node)) {
                node = node.ucbSelectChild(state.getActions());
                state.doAction(node.action);
            }

            List<Action> untriedAction = node.getUntriedMoves(state.getActions());
            if (untriedAction.size() > 0) {
                Action action = untriedAction.get(random.nextInt(untriedAction.size()));
                playerJustMoved = state.player;
                state.doAction(action);
                node = node.addChild(action, playerJustMoved);
            }

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
            if (state.whoWin()) {
                set.updateProForm(itermax);
            }
            //System.out.println("回溯完毕");
        }

    }

    public Action GetAction() {
        int temp = 0;
        int sum = 0;
        int count = 0;
        Node bestchild = null;
        for (Node child : root.children) {
            //System.out.println("孩子数："+count+"  总得分： "+child.getUCBscore());
            temp = child.visits;
            if (temp > sum) {
                sum = temp;
                bestchild = child;
            }
            count++;
        }
        // System.out.println("x:"+bestchild.action.x+" y:"+bestchild.action.y);
        return bestchild.action;
    }

    public boolean isFullyExpanded(State state, Node node) {
        List<Action> legalActions = state.getActions();
        List<Action> untriedActions = node.getUntriedMoves(state.getActions());
        if (legalActions.size() >= 1 && untriedActions.size() < 1) return true;

        return false;
    }
}


