package ISMCTS;

import Game.Chess;
import Game.ChessBoard;
import Game.Coord;

import java.util.ArrayList;

import java.util.Random;

public class ISMCTS {
	private Node root;
	private int itermax;
	private State rootstate;
	private transient Random random;


	public ISMCTS(Node node, int itermax, State rootstate) {
		this.root = node;
		this.itermax = itermax;
		this.rootstate = rootstate;
		this.random = new Random();
	}
	public ISMCTS() {
		this.root =  new Node();
		this.itermax = 1000;
		this.rootstate = new State();
		this.random = new Random();
	}

	public Action Run() {
		for (int i = 0; i < itermax; i++) {
			Node node = root;
			State state = rootstate;
			char playerJustMoved;

			if(state.getActions().size()<1){
				Action actionend = new Action(999,9999);
				return actionend;
			}

			//select
			while (IsFullyExpanded(state, node)) {
				node = node.UCBSelectChild(state.getActions());
				state.DoAction(node.action);
			}
			//expand
			ArrayList<Action> untriedAction = node.GetUntriedMoves(state.getActions());
			if (untriedAction.size() > 0) {
				Action action = untriedAction.get(random.nextInt(untriedAction.size()));
				playerJustMoved = state.player;
				state.DoAction(action);
				node = node.Addchild(action, playerJustMoved);
			}

			//simulate
			ArrayList<Action> nextMove = state.getActions();
			while (state.getActions().size() > 0) {
				state.DoAction(nextMove.get(random.nextInt(nextMove.size())));
			}

			//backpropagete
			while (node != null) {
				node.Update(state);
				node = node.parent;
			}
		}
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
		ArrayList<Action> actions_1 = state.getActions();
		ArrayList<Action> actions_2 = node.GetUntriedMoves(state.getActions());
		if (actions_1.size() >= 1 && actions_2.size() < 1) {
			return true;
		} else
			return false;
	}
}
