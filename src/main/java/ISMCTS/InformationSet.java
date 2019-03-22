package ISMCTS;


import Game.ChessBoard;
import UI.GameStart;

import javax.swing.plaf.synth.SynthScrollBarUI;
import java.util.Random;
import java.util.ArrayList;

public class InformationSet {


    public String[][] forbiddenList = {  //状态矩阵
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
    ;
    public String[][] knownList = {  //状态矩阵
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
    private String player;
    private transient Random random;


    InformationSet(String[][] forbiddenList, String[][] knownList) {
        this.forbiddenList = forbiddenList;
        this.knownList = knownList;
        this.random = new Random();
    }

    public InformationSet(String player) {
        this.random = new Random();
        this.player = player;
    }
	/*
	public boolean equals(InformationSet other){
		if (other.forbiddenList.equals(this.forbiddenList)&&other.knownList.equals(this.knownList))
			return true;
		else
			return false;

	}
	*/

    public ChessBoard getBoard() {
        ChessBoard board = new ChessBoard();
        return board;
    }

    public State SampleState() {
        String opponent;
        if (this.player == "1") {
            opponent = "2";
        } else
            opponent = "1";
        ArrayList<Integer> coord = new ArrayList<Integer>();
        int sum = 0;
        int KnownOpponentChessCount = 0;
        State state = new State(knownList, this.player);
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (knownList[i][j] == "0") {
                    coord.add(i * 10 + j);
                    sum++;
                }
                if (knownList[i][j] != this.player&&knownList[i][j] != "0") {
                    KnownOpponentChessCount++;
                }
            }
        }
        int need = GameStart.count - KnownOpponentChessCount;
        System.out.println("player:"+this.player+"   count"+GameStart.count+"   KnownOpponentChessCount:"+KnownOpponentChessCount);
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.print(this.knownList[i][j]);
            }
            System.out.println();
        }
        if (sum > 0) {
            for (int i = 0; i < need; i++) {
                int index = random.nextInt(sum);
                int temp = coord.get(index);
                state.board[temp / 10][temp % 10] = opponent;
                coord.remove(index);
                sum--;
                if(sum<1)
                    break;
            }
        }
        return state;
    }

}
