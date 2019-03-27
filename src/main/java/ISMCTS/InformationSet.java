package ISMCTS;


import Game.ChessBoard;
import NewIdea.Value;
import NewIdea.prob_form;
import UI.GameStart;

import javax.swing.plaf.synth.SynthScrollBarUI;
import java.util.Random;
import java.util.ArrayList;

public class InformationSet {

/*
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
    */
    ;

    public prob_form prob_form;
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
    public String player;
    private transient Random random;
    private  ArrayList<Integer> updatecoord;

/*
    InformationSet(String[][] forbiddenList, String[][] knownList) {
       // this.forbiddenList = forbiddenList;
        this.knownList = knownList;
        this.random = new Random();
    }
*/
    public InformationSet(String player) {
        this.random = new Random();
        this.player = player;
        this.prob_form = new prob_form();
        this.updatecoord = new ArrayList<Integer>();
    }
	/*
	public boolean equals(InformationSet other){
		if (other.forbiddenList.equals(this.forbiddenList)&&other.knownList.equals(this.knownList))
			return true;
		else
			return false;

	}
	*/
    public void initialProb_form(int max){
        for(int i=1;i<10;i++){
            for(int j=1;j<10;j++){
                if(this.knownList[i][j]!=this.player&&this.knownList[i][j]!="0"){
                    this.prob_form.form[i][j] = max;
                }
                else
                    this.prob_form.form[i][j] = 0;
            }
        }
    }


    public ChessBoard getBoard() {
        ChessBoard board = new ChessBoard();
        return board;
    }

    public History SampleState() {
        History history = new History();
        this.updatecoord.clear();
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
        //System.out.println(" 当前需要棋子数："+need);
        if(GameStart.count<5){
            history.flag = false;
        }
        /*
        System.out.println("player:"+this.player+"   count:"+GameStart.count+"   KnownOpponentChessCount:"+KnownOpponentChessCount);
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.print(this.knownList[i][j]);
            }
            System.out.println();
        }
        */
        if (sum > 0) {
            for (int i = 0; i < need; i++) {
                int index = random.nextInt(sum);
                int temp = coord.get(index);
                state.board[temp / 10][temp % 10] = opponent;
                updatecoord.add(temp);
                //System.out.println("player:"+this.player+"  x:"+temp / 10+"  y:"+temp%10);
                coord.remove(index);
                sum--;
                if(sum<1)
                    break;
            }
        }
        history.state.clone(state);
        return history;
    }

    public void updatePro_form(int max){
        //System.out.println("要添加的位置有几个："+updatecoord.size());
        for(int i=0;i<updatecoord.size();i++){
           if(prob_form.form[updatecoord.get(i)/10][updatecoord.get(i)%10]!=max)
               prob_form.form[updatecoord.get(i)/10][updatecoord.get(i)%10] ++;
        }
    }

    public void StandardForm(int max){
        for(int i=1;i<10;i++){
            for(int j=1;j<10;j++){
                prob_form.form[i][j] = prob_form.form[i][j]/max;
            }
        }
    }



}
