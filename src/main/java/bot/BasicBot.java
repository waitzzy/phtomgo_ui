package bot;

import java.util.*;

import ISMCTS.InformationSet;
import ISMCTS.State;
import ISMCTS.Node;
import ISMCTS.Action;
import ISMCTS.basicISMCT;
import java.lang.*;
import ISMCTS.History;
public class BasicBot {
    public InformationSet set;
    public basicISMCT ismct;
    public String player;

    BasicBot(InformationSet set, String player) {
        this.set = set;
        this.player = player;
    }

    public BasicBot(String player) {
        set = new InformationSet(player);
        this.player = player;
        this.ismct = new basicISMCT(this.player);

    }

    public String botRun(int itermax) {
        Node rootnode = new Node(player);
        String ISMCTSoot = "bang";
        ismct = new basicISMCT(rootnode, itermax);
        History history = new History();
        for (int kk = 0; kk < 5; kk++) {   //状态数
            history = set.sampleState();
            State state = history.state;
            if (state.getActions().size() < 1) {
                ISMCTSoot = "pass";
                return ISMCTSoot;
            }
            //System.out.println("新建树成功");
            ismct.Run(state);
        }
        Action action = ismct.GetAction();
        ISMCTSoot = action.x + "," + action.y;
        return ISMCTSoot;
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
}