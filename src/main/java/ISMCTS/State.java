package ISMCTS;

import java.util.ArrayList;

import Game.ChessBoard;
import Game.Coord;
import ISMCTS.Action;

public class State {
    public String[][] board = {
            {"3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3"},
            {"3", "0", "0", "0", "0", "0", "0", "0", "0", "0", "3"},
            {"3", "0", "0", "0", "0", "0", "0", "0", "0", "0", "3"},
            {"3", "0", "0", "0", "0", "0", "0", "0", "0", "0", "3"},
            {"3", "0", "0", "0", "0", "0", "0", "0", "0", "0", "3"},
            {"3", "0", "0", "0", "0", "0", "0", "0", "0", "0", "3"},
            {"3", "0", "0", "0", "0", "0", "0", "0", "0", "0", "3"},
            {"3", "0", "0", "0", "0", "0", "0", "0", "0", "0", "3"},
            {"3", "0", "0", "0", "0", "0", "0", "0", "0", "0", "3"},
            {"3", "0", "0", "0", "0", "0", "0", "0", "0", "0", "3"},
            {"3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3"}
    };
    public String player;

    State(String[][] board, String player) {
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                this.board[i][j] = board[i][j];
            }
        }
        this.player = player;

    }

    public void clone(State other) {
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                this.board[i][j] = other.board[i][j];
            }
        }
        this.player = other.player;
    }

    public State() {
    }

    public ArrayList<Action> getActions() {
        //	System.out.println("get possible moves");
        ArrayList<Action> result = new ArrayList<Action>();
        // Get possible moves
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (board[i][j] == "0")
                    result.add(new Action(i, j));
            }
        }
        return result;
    }

    //计划修改本函数
    public State CloneAndRandomize() {
        State state = null;
        return state;
    }

    public Action DoAction(Action action) {
        if (player == "2") {
            this.board[action.x][action.y] = "2";
            this.player = "1";
        } else if (player == "1") {
            this.board[action.x][action.y] = "1";
            this.player = "2";
        }


        Action nextaction = new Action(action.x, action.y);
        return nextaction;
    }


    //得到结果
    public int GetResult(char player) {
        return 0;
    }

    public boolean WhoWin() {

        String[][] forestJudgerStatus = board;
        for (int k = 1; k < 9; k++) {
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    if (forestJudgerStatus[i][j].equals("1")) {
                        if (forestJudgerStatus[i + 1][j].equals("0")) {
                            forestJudgerStatus[i + 1][j] = "1";
                        }
                        if (forestJudgerStatus[i - 1][j].equals("0")) {
                            forestJudgerStatus[i - 1][j] = "1";
                        }
                        if (forestJudgerStatus[i][j + 1].equals("0")) {
                            forestJudgerStatus[i][j + 1] = "1";
                        }
                        if (forestJudgerStatus[i][j - 1].equals("0")) {
                            forestJudgerStatus[i][j - 1] = "1";
                        }

                    }
                    if (forestJudgerStatus[i][j].equals("2")) {
                        if (forestJudgerStatus[i + 1][j].equals("0")) {
                            forestJudgerStatus[i + 1][j] = "2";
                        }
                        if (forestJudgerStatus[i - 1][j].equals("0")) {
                            forestJudgerStatus[i - 1][j] = "2";
                        }
                        if (forestJudgerStatus[i][j + 1].equals("0")) {
                            forestJudgerStatus[i][j + 1] = "2";
                        }
                        if (forestJudgerStatus[i][j - 1].equals("0")) {
                            forestJudgerStatus[i][j - 1] = "2";
                        }

                    }
                }
            }
        }
        int hunterScale = 0;
        int playerScale = 0;
        String oppoentplayer;
        if(this.player=="1")
            oppoentplayer = "2";
        else
            oppoentplayer ="1";
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (forestJudgerStatus[i][j].equals(this.player)) {
                    hunterScale++;
                } else if (forestJudgerStatus[i][j].equals(oppoentplayer)) {
                    playerScale++;
                }
            }
        }
        if (hunterScale > playerScale) {
            return true;
        } else if (hunterScale < playerScale) {
            return false;
        }
        if (hunterScale == playerScale) {
            return false;
        }
        return false;
    }



}



