package bot;

import UI.GameStart;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.Scanner;

public class Player extends JPanel {
    String playerSoot;
    int playerSootLoca;
    int debug = 1;
    static String[][] PlayerStatus = {  //状态矩阵
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

    public String getPlayerSoot() {
        String inputSoot = "pass";//取得玩家输入
        boolean flag = true;
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (PlayerStatus[i][j] == "0")
                    flag = false;
            }
        }
        if (flag == false) {
            Scanner in = new Scanner(System.in);
            int x = in.nextInt();
            int y = in.nextInt();
            inputSoot = x + "," + y;
            System.out.println("你选择下在" + x + "和" + y);
        }
        //GameStart.inputFlag = true;
        return inputSoot;
    }

    public boolean obtainPlayerJF(String playerFeedback, int xx, int yy) {  //提示合法，非法，提子

        boolean flagPlayer = true;

        if (playerFeedback.equals("take")) {
            System.out.println("Congratulations! Your soot is take.");
            flagPlayer = false;
        } else if (playerFeedback.equals("illegal")) { //得知非法，如果对应坐标为0，则断定是对方地盘（棋子或眼），计入矩阵
            if (PlayerStatus[xx][yy].equals("0")) {
                PlayerStatus[xx][yy] = "2";
            }
            System.out.println("Your soot is illegal, you have to soot again.");
            flagPlayer = true;
        } else if (playerFeedback.equals("legal")) { //如果合法，将己方落子计入矩阵
            PlayerStatus[xx][yy] = "1";
            System.out.println("Your soot is legal.");
            flagPlayer = false;
        }
        if (debug == 1) {
            System.out.println("Player's board:");
            for (int i = 1; i < PlayerStatus.length - 1; i++) {
                for (int j = 1; j < PlayerStatus.length - 1; j++) {
                    System.out.print(PlayerStatus[i][j] + " ");
                }
                System.out.print("\n");
            }
        }
        return flagPlayer;
    }

    public void killTake(ArrayList deadList) { //提子，更新棋盘
        int deadNum = 0;
        int m = 0;
        int n = 0;
        for (int i = 0; i < deadList.size(); i++) {
            deadNum = (Integer) deadList.get(i);
            m = deadNum / 9;
            n = deadNum % 9;
            PlayerStatus[m][n] = "0";
        }
    }
}
