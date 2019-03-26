package ISMCTS;

import Game.ChessBoard;

import java.util.ArrayList;

public class Node {
    public Action action;  //到这个节点的action
    public Node parent;
    public ArrayList<Node> children;
    private int wins;
    public int visits;
    private int avails;
    private String Player;

    public Node(Action action, Node parent, String Player) {
        this.action = action;
        this.parent = parent;
        this.Player = Player;
        this.wins = 0;
        this.visits = 0;
        this.avails = 1;
        children = new ArrayList<Node>();

    }

    public Node(String player) {
        this.action = null;
        this.parent = null;
        this.Player = player;
        this.wins = 0;
        this.visits = 0;
        this.avails = 1;
        children = new ArrayList<Node>();
        //children = null;

    }

    public ArrayList<Action> GetUntriedMoves(ArrayList<Action> legalAction) {
        ArrayList<Action> TriedMove = new ArrayList<Action>();
        ArrayList<Action> result = new ArrayList<Action>();
        //System.out.println(this.visits);
        if (children!=null) {
            //System.out.println("getaction finish");
            for (Node child : children) {
                //System.out.println("给老子冲");
                TriedMove.add(child.action);
            }
        }
        for (Action action_1 : legalAction) {
            boolean flag = true;
            for (Action action_2 : TriedMove) {
               // System.out.println("action1: "+action_1.x+action_1.y+"  action2:"+action_2.x+action_2.y);
                if (action_2.equals(action_1)) {
                    flag = false;

                  //  System.out.println("重复");
                    break;
                }
            }
            if(flag) {
                result.add(action_1);
            }
        }
        return result;
    }

    public Node UCBSelectChild(ArrayList<Action> legalAction) {
        ArrayList<Node> legalChildren = new ArrayList<Node>();
        for (Node child : children) {
            for (Action action : legalAction) {
                if (action.equals(child.action))
                    legalChildren.add(child);
            }
        }
       // System.out.println(legalChildren.size());
        double sum = -1;
        double temp;
        Node bestchild = null;
        for (Node child : legalChildren) {
            //System.out.println("冲");
           // System.out.println(" win:"+child.wins+" visits:"+child.visits+" avails:"+child.avails);
            temp = (double) child.wins / (double) child.visits + 0.7 * Math.sqrt(2.0 * Math.log((double) child.avails / (double) child.visits));
            if (temp > sum) {
                sum = temp;
                bestchild = child;
            }
            //System.out.println(temp);
            child.avails++;
        }
        //System.out.println(bestchild.GetUCBscore());
        return bestchild;
    }

    public Node Addchild(Action action, String Player) {
        Node new_Child = new Node(action, this, Player);
        this.children.add(new_Child);
        return new_Child;
    }

    //我佛了
    public void Update(State terminalState) {
        this.visits++;
        if (terminalState.WhoWin()) {
            wins++;
        }
    }

    public double GetUCBscore(){
        double temp = 0;
        temp = (double) wins / (double) visits + 0.7 * Math.sqrt(2.0 * Math.log((double) avails / (double) visits));
        return temp;
    }


}

