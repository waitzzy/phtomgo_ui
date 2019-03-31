package ISMCTS;

import java.util.ArrayList;
import java.util.List;

public class Node {
    /* 到这个节点的Action */
    public Action action;
    public Node parent;
    public List<Node> children;
    public int visits;

    private int wins;
    private int avails;
    private String player;

    public Node(Action action, Node parent, String Player) {
        this.action = action;
        this.parent = parent;
        this.player = Player;
        this.wins = 0;
        this.visits = 0;
        this.avails = 1;
        children = new ArrayList<Node>();
    }

    public Node(String player) {
        this.action = null;
        this.parent = null;
        this.player = player;
        this.wins = 0;
        this.visits = 0;
        this.avails = 1;
        children = new ArrayList<Node>();
    }

    public List<Action> getUntriedMoves(List<Action> legalAction) {
        List<Action> triedMove = new ArrayList<>();
        List<Action> result = new ArrayList<>();
        if (children != null) {
            for (Node child : children) {
                triedMove.add(child.action);
            }
        }
        for (Action action : legalAction) {
            boolean flag = true;
            for (Action actionItem : triedMove) {
                if (actionItem.equals(action)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result.add(action);
            }
        }
        return result;
    }

    public Node ucbSelectChild(List<Action> legalAction) {
        List<Node> legalChildren = new ArrayList<>();
        for (Node child : children) {
            for (Action action : legalAction) {
                if (action.equals(child.action))
                    legalChildren.add(child);
            }
        }

        double sum = -1;
        double temp;
        Node bestChild = null;
        for (Node child : legalChildren) {
            temp = (double) child.wins / (double) child.visits + 0.7 * Math.sqrt(2.0 * Math.log((double) child.avails / (double) child.visits));
            if (temp > sum) {
                sum = temp;
                bestChild = child;
            }
            child.avails++;
        }

        return bestChild;
    }

    public Node addChild(Action action, String Player) {
        Node newChild = new Node(action, this, Player);
        this.children.add(newChild);
        return newChild;
    }

    public void update(State terminalState) {
        this.visits++;
        if (terminalState.whoWin()) {
            wins++;
        }
    }

    public double getUCBscore() {
        double temp = (double) wins / (double) visits + 0.7 * Math.sqrt(2.0 * Math.log((double) avails / (double) visits));
        return temp;
    }
}

