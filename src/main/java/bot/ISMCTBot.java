package bot;

import java.util.*;

import ISMCTS.ISMCTS;
import ISMCTS.InformationSet;
import ISMCTS.State;
import ISMCTS.Node;
import ISMCTS.Action;
import NewIdea.Value;
import ISMCTS.History;
import java.lang.*;

public class ISMCTBot {
    public InformationSet set;
    public ISMCTS ismct;
    public String player;
    private int StateNumber = 10;
    public Value value;
    ISMCTBot(InformationSet set, String player) {
        this.set = set;
        this.player = player;
    }

    public ISMCTBot(String player) {
        set = new InformationSet(player);
        this.player = player;
        this.ismct = new ISMCTS(this.player,this.set);
        this.value = new Value();

    }

    public String botRun(int itermax) {
        Node rootnode = new Node(player);
        String ISMCTSoot = "bang";
        set.initialProbForm(100000);
        ismct = new ISMCTS(rootnode, itermax,this.set);
        State beginState = new State();
        History history = new History();
        boolean flag = true;
        for (int kk = 0; kk < StateNumber; kk++) {   //状态数
            history = set.sampleState();
            State state = history.state;
            beginState.clone(state);
            flag = history.flag;
            if (state.getActions().size() < 1) {
                ISMCTSoot = "pass";
                return ISMCTSoot;
            }
            ismct.Run(state);
            /*
            System.out.println("当前玩家："+set.player+"   当前信息集：");
            for(int i = 1; i < set.knownList.length - 1; i++){
                for(int j = 1;j < set.knownList.length - 1; j++){
                    System.out.print(set.knownList[i][j]+" ");
                }System.out.print("\n");
            }System.out.print("\n");
            */
        }
        if(flag){
           // System.out.println("采用蒙特卡洛");
        Action action = ismct.GetAction();
        ISMCTSoot = action.x + "," + action.y;
        return ISMCTSoot;}
        else{
            //System.out.println("开始idea");
            double sum = -10000;
            double temp = 0;
            int xxx=4;
            int yyy=4;
            /*
            for(int ii = 1; ii < set.probForm.form.length - 1; ii++){
                for(int jj = 1;jj < set.probForm.form.length - 1; jj++){
                    System.out.print(set.probForm.form[ii][jj]+" ");
                }System.out.print("\n");
            }System.out.print("\n");

            */
            set.standardForm(0.7);
            /*
            System.out.printf("概率分布:\n");
            for(int ii = 1; ii < set.probForm.form.length - 1; ii++){
                for(int jj = 1;jj < set.probForm.form.length - 1; jj++){
                    System.out.print(set.probForm.form[ii][jj]+" ");
                }System.out.print("\n");
            }System.out.print("\n");
            */
            for(int i=1;i<10;i++){
                for(int j=1;j<10;j++){
                    if(set.knownList[i][j]=="0"){
                        temp = value.GetMaxValue(beginState.player,i,j,set);
                        //System.out.println("当前价值："+sum);
                        if(temp>sum){
                            sum=temp;
                            xxx = i;
                            yyy = j;
                        }
                    }
                }
            }
           // System.out.println("最大价值："+sum);
            ISMCTSoot = xxx+","+yyy;
            return  ISMCTSoot;
        }
    }

    public boolean obtainHunterJF(String hunterFeedback, int xx, int yy) {  //裁判的反馈，合法，非法，提子
        boolean flag = true;
        if (hunterFeedback.equals("take")) {
            flag = false;
        } else if (hunterFeedback.equals("illegal")) { //得知非法，已知对应坐标为0，则断定是对方地盘（棋子或眼），计入矩阵
            String opponentplay;
            if (this.player == "1") {
                opponentplay = "2";
            } else
                opponentplay = "1";
            set.knownList[xx][yy] = opponentplay;
        } else if (hunterFeedback.equals("legal")) { //如果合法，将己方落子计入矩阵
            set.knownList[xx][yy] = this.player;
            flag = false;
        }
        return flag;
    }

    public void killTake(List deadList) {
        int deadNum = 0;
        int m = 0;
        int n = 0;
        for (int i = 0; i < deadList.size(); i++) {
            deadNum = (Integer) deadList.get(i);
            m = deadNum / 9;
            n = deadNum % 9;
            set.knownList[m][n] = "0";
        }
    }

    public void SelectAction(){
        for(int i = 1;i<10;i++){
            for(int j=0;j<10;j++){

            }
        }
    }

}


		/*
		if(debug == 1){
			System.out.printf("Singer said that hunter's soot is %s.\n", hunterFeedback);
			System.out.println("Hunter's board:");
			for(int i = 1; i < forestHunterStatus.length - 1; i++){
				for(int j = 1;j < forestHunterStatus.length - 1; j++){
					System.out.print(forestHunterStatus[i][j]+" ");
				}
				System.out.print("\n");
			}
			System.out.print("\n");
		}
		if(debug == 1){
			System.out.printf("Hunter's weight matrix is.\n");
			for(int i = 1; i < forestWeight.length - 1; i++){
				for(int j = 1; j < forestWeight.length - 1; j++){
					System.out.print(forestWeight[i][j]+" ");
				}
				System.out.print("\n");
			}
			System.out.print("\n");
		}
		return flagHunter;
	}
	public void killTake(ArrayList deadList){
		int deadNum = 0;
		int m = 0;
		int n = 0;
		for(int i = 0; i < deadList.size(); i++){
			deadNum = (Integer) deadList.get(i);
			m = deadNum / 9;
			n = deadNum % 9;
			forestHunterStatus[m][n] = "0";
		}
	}
	public String smartHunter(){ //落子策略
		String hunterSoot="bang";

		int optimum = 0;
		int optix = 0;
		int optiy = 0;

		for(int i = 1; i < forestWeight.length - 1; i++ ){
			for(int j = 1; j < forestWeight.length - 1; j++ ){

				int num = Integer.parseInt(forestWeight[i][j]);
				if(num == optimum && forestHunterStatus[i][j].equals("0")){ //如果权值相等，随机选一个

					int randomNum = (int)(Math.random()*2);
					if(randomNum == 1){
						optix = i;
						optiy = j;
					}
				}else if(num > optimum && forestHunterStatus[i][j].equals("0")){ //如果新坐标权值更大，用新坐标
					optix = i;
					optiy = j;
					optimum = num;
				}
			}
		}

		//＊其他策略
		if(optix == 0 && optiy == 0){ //无处落子时，返回pass
			hunterSoot = "pass";
		}

		hunterSoot = optix+","+optiy;

		if(debug == 1){
			System.out.printf("Bang!! Hunter soot at %s.\n", hunterSoot);
		}
		return hunterSoot;
	}
	public void trap(int trapx, int trapy){ //遇到对方棋子则提高周围权重
		if(forestHunterStatus[trapx+1][trapy].equals("0")){
			forestWeight[trapx+1][trapy] = "5";
		}else if(forestHunterStatus[trapx-1][trapy].equals("0")){
			forestWeight[trapx-1][trapy] = "5";
		}else if(forestHunterStatus[trapx][trapy+1].equals("0")){
			forestWeight[trapx][trapy+1] = "5";
		}else if(forestHunterStatus[trapx][trapy-1].equals("0")){
			forestWeight[trapx][trapy-1] = "5";
		}
	}

	public void matrixRefresh(){
		for(int i = 0; i < forestWeight.length; i ++){
			for(int j = 0; j < forestWeight.length; j ++){
				if(forestHunterStatus[i][j].equals("0") && //不下愚形
						((forestHunterStatus[i+1][j].equals("2") && forestHunterStatus[i-1][j].equals("2") &&
						forestHunterStatus[i][j+1].equals("2") && forestHunterStatus[i][j-1].equals("0")) ||
						(forestHunterStatus[i+1][j].equals("0") && forestHunterStatus[i-1][j].equals("2") &&
						forestHunterStatus[i][j+1].equals("2") && forestHunterStatus[i][j-1].equals("2")) ||
						(forestHunterStatus[i+1][j].equals("2") && forestHunterStatus[i-1][j].equals("0") &&
						forestHunterStatus[i][j+1].equals("2") && forestHunterStatus[i][j-1].equals("2")) ||
						(forestHunterStatus[i+1][j].equals("2") && forestHunterStatus[i-1][j].equals("2") &&
						forestHunterStatus[i][j+1].equals("0") && forestHunterStatus[i][j-1].equals("2")))){
					forestWeight[i][j] = "0";
				}else if(forestHunterStatus[i][j].equals("0") && //直线的连接和切断
						(forestHunterStatus[i+1][j].equals("1") && forestHunterStatus[i-1][j].equals("1"))||
						(forestHunterStatus[i][j+1].equals("1") && forestHunterStatus[i][j-1].equals("1"))||
						(forestHunterStatus[i+1][j].equals("2") && forestHunterStatus[i-1][j].equals("2"))||
						(forestHunterStatus[i][j+1].equals("2") && forestHunterStatus[i][j-1].equals("2"))){
					forestWeight[i][j] = "5";
				}else if(forestHunterStatus[i][j].equals("0")){ //一个子从虎口中逃出
					if(forestHunterStatus[i+1][j].equals("1") && forestHunterStatus[i-1][j].equals("1") &&
							forestHunterStatus[i][j+1].equals("1") && forestHunterStatus[i][j-1].equals("0")){
						forestWeight[i][j-1] = "5";
					}else if(forestHunterStatus[i+1][j].equals("0") && forestHunterStatus[i-1][j].equals("1") &&
							forestHunterStatus[i][j+1].equals("1") && forestHunterStatus[i][j-1].equals("1")){
						forestWeight[i+1][j] = "5";
					}else if(forestHunterStatus[i+1][j].equals("1") && forestHunterStatus[i-1][j].equals("0") &&
							forestHunterStatus[i][j+1].equals("1") && forestHunterStatus[i][j-1].equals("1")){
						forestWeight[i-1][j] = "5";
					}else if(forestHunterStatus[i+1][j].equals("1") && forestHunterStatus[i-1][j].equals("1") &&
							forestHunterStatus[i][j+1].equals("0") && forestHunterStatus[i][j-1].equals("1")){
						forestWeight[i][j+1] = "5";
					}
				}
			}
		}
	}
}
*/