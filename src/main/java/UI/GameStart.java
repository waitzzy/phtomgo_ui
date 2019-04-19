package UI;

import bot.ISMCTBot;
import Game.Judger;

import java.util.List;

import bot.BasicBot;

public class GameStart {

    /* 对手下的棋子数 */
    public static int count = 0;
    public static int playerWin = 0;
    public static int hunterWin = 0;

    /* 初始化2个对象和1个裁判 */
    ISMCTBot ismctBot = new ISMCTBot("2");
    BasicBot basicBot = new BasicBot("1");
    //BasicBot ismctBot = new BasicBot("2");
    Judger judger = new Judger();

    /* 循环标记 */
    boolean flagProcesser = true;
    boolean flagPlayer = true;
    boolean flagBot = true;

    /* 双方落子坐标 */
    String inputSoot = null;
    String ismctSoot = null;

    GameStart() {
        GameStart.count = 0;
    }

    public void forestHunting() {//游戏过程
        while (flagProcesser) {//游戏循环，两个pass结束
            flagPlayer = true;
            flagBot = true;

            while (flagBot) {//hunter落子循环
                ismctSoot = ismctBot.botRun(500);//hunter落子
                if (ismctSoot == "pass") {
                    flagBot = false;
                } else {
                    String[] hunterSootCoor = ismctSoot.split(",");
                    int xx = Integer.parseInt(hunterSootCoor[0]);
                    int yy = Integer.parseInt(hunterSootCoor[1]);
                    String judgeHunterSoot = judger.judgement("hunter", xx, yy);//裁判判断输入
                    flagBot = ismctBot.obtainHunterJF(judgeHunterSoot, xx, yy);//判断结果传给hunter
                    if (judgeHunterSoot.equals("legal")) {
                        //do nothing
                    }
                    if (judgeHunterSoot.equals("take")) {//如果出现take，则运行killDeadLife取得死亡名单，传给双方
                        List deadList = judger.killDeadLife();//裁判更新局面
                        ismctBot.killTake(deadList);//hunter更新局面
                        basicBot.killTake(deadList);//player更新局面
                    }
                }
            }
            count++;//对局数增加

            while (flagPlayer) {//玩家落子循环，legal或take结束
                inputSoot = basicBot.botRun(500);
                if ("pass".equals(inputSoot)) {
                    flagPlayer = false;
                } else {//判断输入是否合法
                    String[] inputSootCoor = inputSoot.split(",");//识别玩家输入
                    int xx = Integer.parseInt(inputSootCoor[0]);
                    int yy = Integer.parseInt(inputSootCoor[1]);
                    String judgePlayerSoot = judger.judgement("player", xx, yy);//裁判判断输入
                    flagPlayer = basicBot.obtainHunterJF(judgePlayerSoot, xx, yy);//判断结果传给玩家
                    if (judgePlayerSoot.equals("legal")) {
                        //do nothing
                    }
                    if (judgePlayerSoot.equals("take")) {//如果出现take，则运行killDeadLife取得死亡名单，传给双方
                        List deadList = judger.killDeadLife();
                        ismctBot.killTake(deadList);
                        basicBot.killTake(deadList);
                    }
                }

                if (inputSoot == "pass" && ismctSoot == "pass") {//双方pass，跳出循环
                    flagProcesser = false;
                }
            }

            String whoWin = judger.whoWin();//判胜负
            if (whoWin == "player") {
                GameStart.playerWin++;
            }
            if (whoWin == "hunter") {
                GameStart.hunterWin++;
            }
        }
    }

    public static void main(String[] args) {
        GameStart newGameStart;
        for (int i = 0; i < 500; i++) {
            newGameStart = new GameStart();
            newGameStart.forestHunting();
        }
        System.out.println("成了！");
        System.out.println("hunter获胜次数：" + GameStart.hunterWin);
        System.out.println("player获胜次数：" + GameStart.playerWin);
    }
}


