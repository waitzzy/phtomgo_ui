package UI;

import bot.ISMCTbot;
import Game.Judger;
import bot.Player;
import util.FileUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import bot.basicBot;
public class GameStart {

    // static A BW = new A();
    //	static boolean flag11 = true;
    public static int count = 0;


    ISMCTbot ISMCTbot = new ISMCTbot("2"); //初始化三个角色
    basicBot ISMCTbot_test = new basicBot("1");
    //Player newPlayer = new Player();
    Judger newJudger = new Judger();


    boolean flagProcesser = true; //循环标记
    boolean flagPlayer = true;
    boolean flagbot = true;

    public String inputSoot = null; //双方落子坐标
    String ISMCTSoot = null;
    public static int playerWin = 0;
    public static int hunterWin = 0;

    GameStart() {
        GameStart.count = 0;
    }

    public void forestHunting() {  //游戏过程
        /*
        BoardPanel.setLayout(null);
        frame.setResizable(false); //固定框架尺寸

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BoardPanel);


        BoardPanel.add(label1); //文本域1加到面板
        label1.setBounds(53, 85, 53, 90);
        BoardPanel.add(label2); //文本域2加到面板
        label2.setBounds(606, 85, 606, 90);
        //	BoardPanel.add(text3); //文本框3加到面板
        //	text3.setBounds(27, 420, 91, 25);
        BoardPanel.add(text4); //文本框4加到面板
        text4.setBounds(583, 420, 91, 25);

        text1.setLineWrap(true); //滚动条
        JScrollPane scroller1 = new JScrollPane(text1);
        scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        BoardPanel.add(scroller1);
        scroller1.setBounds(30, 160, 85, 240);

        text2.setLineWrap(true); //滚动条
        JScrollPane scroller2 = new JScrollPane(text2);
        scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        BoardPanel.add(scroller2);
        scroller2.setBounds(586, 160, 85, 240);

        text1.setEditable(false); //文本域1不可输入
        text2.setEditable(false); //文本域2不可输入
        //	text3.setEditable(false); //文本框3不可输入

        frame.setSize(700,500);
        frame.setVisible(true);
        */
        // System.out.println("Wellcome to DarkForest!");
        /*
        class checkActionListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                //	 if(flag11 == true){
                if("white".equals(BW.color)){

                    MyDrawPanelBoardWhiteCheck WhiteCheck = new MyDrawPanelBoardWhiteCheck();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().add(WhiteCheck);
                    frame.setSize(700,500);
                    frame.setVisible(true);

                }else if("black".equals(BW.color)){

                    MyDrawPanelBoardBlackCheck BlackCheck = new MyDrawPanelBoardBlackCheck();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().add(BlackCheck);
                    frame.setSize(700,500);
                    frame.setVisible(true);
                    //	}
                    //	flag11 = false;
                }
            }


        }
        */
        //  String role = "white";

       /*
        if("white".equals(role)){

            MyDrawPanelPiecesWhite PanelWhite = new MyDrawPanelPiecesWhite();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(PanelWhite);
            frame.setSize(700,500);
            frame.setVisible(true);

        }else if("black".equals(role)){

            MyDrawPanelPiecesBlack PanelBlack = new MyDrawPanelPiecesBlack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(PanelBlack);
            frame.setSize(700,500);
            frame.setVisible(true);
        }

        if ("white".equals(role)) { //先后手标记
            ISMCTSoot = ISMCTbot.bot_run(100); //hunter落子
            System.out.println(ISMCTSoot);
            String[] hunterSootCoor = ISMCTSoot.split(",");
            int xx = new Integer(hunterSootCoor[0]);
            int yy = new Integer(hunterSootCoor[1]);
            String judgeHunterSoot = newJudger.Judgement("hunter", xx, yy);  //裁判判断输入
            flagbot = ISMCTbot.obtainHunterJF(judgeHunterSoot, xx, yy);  //判断结果传给bot
        }
        count = 0;


        text4.addActionListener(new text4ActionListener()); //文本框4响应

        JButton checkButton = new JButton("see");
        frame.getContentPane().add(checkButton);
        checkButton.setBounds(27, 420, 91, 25);
        checkButton.addActionListener(new checkActionListener()); //查看局面按钮
         */
        while (flagProcesser) {  //游戏循环，两个pass结束
            flagPlayer = true;
            flagbot = true;

          /*  while (flagPlayer) {  //玩家落子循环，legal或take结束
                inputFlag = false;
                TextInput = true;
                System.out.print(count + " Please input your soot:");
                inputSoot = newPlayer.getPlayerSoot();
                if ("pass".equals(inputSoot)) {
                } else if (inputSoot.length() == 3 && java.lang.Character.isDigit(inputSoot.charAt(0))
                        && java.lang.Character.isDigit(inputSoot.charAt(2))
                        && inputSoot.contains(",")) { //判断输入是否合法
                    String[] inputSootCoor = inputSoot.split(","); //识别玩家输入
                    int xx = new Integer(inputSootCoor[0]);
                    int yy = new Integer(inputSootCoor[1]);
                    if (xx < 10 && xx > 0 && yy < 10 && yy > 0) {

                        String judgePlayerSoot = newJudger.Judgement("player", xx, yy);  //裁判判断输入
                        flagPlayer = newPlayer.obtainPlayerJF(judgePlayerSoot, xx, yy);  //判断结果传给玩家
                        if (judgePlayerSoot.equals("legal")) {
                            playerInput += inputSoot;
                            playerInput += "         ";
                            System.out.println(playerInput);
                            //text2.setText(playerInput); //更新输入列表
                        }
                        if (judgePlayerSoot.equals("take")) { //如果出现take，则运行killDeadLife取得死亡名单，传给双方
                            ArrayList deadList = new ArrayList();
                            deadList = newJudger.killDeadLife();
                            ISMCTbot.killTake(deadList);
                            newPlayer.killTake(deadList);
                            //frame.repaint();
                        }
                    } else {
                        System.out.println("Your soot posture is wrong, please try again!");
                    }
                } else {
                    System.out.println("Your soot posture is wrong, please try again!");
                }
                */


            while (flagbot) { //hunter落子循环
                //System.out.print(count + " Hunter's turn:");
                ISMCTSoot = ISMCTbot.bot_run(200); //hunter落子
                if (ISMCTSoot == "pass") {
                    flagbot = false;
                } else {
                    String[] hunterSootCoor = ISMCTSoot.split(",");
                    int xx = new Integer(hunterSootCoor[0]);
                    int yy = new Integer(hunterSootCoor[1]);
                    String judgeHunterSoot = newJudger.Judgement("hunter", xx, yy);  //裁判判断输入
                    //System.out.println("player："+ISMCTbot.player+"  result:"+judgeHunterSoot);
                    flagbot = ISMCTbot.obtainHunterJF(judgeHunterSoot, xx, yy);  //判断结果传给hunter
                    if (judgeHunterSoot.equals("legal")) {
                        // hunterInput += "Bang!";
                        // hunterInput += "       ";
                        // System.out.println(hunterInput);
                        //  text1.setText(hunterInput); //更新输入列表
                    }
                    if (judgeHunterSoot.equals("take")) { //如果出现take，则运行killDeadLife取得死亡名单，传给双方
                        ArrayList deadList = new ArrayList();
                        deadList = newJudger.killDeadLife(); //裁判更新局面
                        ISMCTbot.killTake(deadList); //hunter更新局面
                        ISMCTbot_test.killTake(deadList); //player更新局面
                        // frame.repaint();
                    }
                }
                /*
                if("white".equals(role)){
                    MyDrawPanelBoardWhite drawPanel1 = new MyDrawPanelBoardWhite();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().add(drawPanel1);
                    frame.setSize(700,500);
                    frame.setVisible(true);

                }else if("black".equals(role)){
                    MyDrawPanelBoardBlack drawPanel2 = new MyDrawPanelBoardBlack();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().add(drawPanel2);
                    frame.setSize(700,500);
                    frame.setVisible(true);
                }
               */
            }
            count = count + 1;

            while (flagPlayer) {  //玩家落子循环，legal或take结束
                inputSoot = ISMCTbot_test.bot_run(200);
                if ("pass".equals(inputSoot)) {
                    flagPlayer = false;
                } else { //判断输入是否合法
                    String[] inputSootCoor = inputSoot.split(","); //识别玩家输入
                    int xx = new Integer(inputSootCoor[0]);
                    int yy = new Integer(inputSootCoor[1]);
                    String judgePlayerSoot = newJudger.Judgement("player", xx, yy);  //裁判判断输入
                    //System.out.println("player："+ISMCTbot_test.player+"  result:"+judgePlayerSoot);
                    flagPlayer = ISMCTbot_test.obtainHunterJF(judgePlayerSoot, xx, yy);  //判断结果传给玩家
                    if (judgePlayerSoot.equals("legal")) {

                    }
                    if (judgePlayerSoot.equals("take")) { //如果出现take，则运行killDeadLife取得死亡名单，传给双方
                        ArrayList deadList = new ArrayList();
                        deadList = newJudger.killDeadLife();
                        ISMCTbot.killTake(deadList);
                        ISMCTbot_test.killTake(deadList);
                        //frame.repaint();
                    }
                }

                /*
                if("white".equals(role)){
                    MyDrawPanelBoardWhite drawPanel = new MyDrawPanelBoardWhite();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().add(drawPanel);
                    frame.setSize(700,500);
                    frame.setVisible(true);

                }else if("black".equals(role)){
                    MyDrawPanelBoardBlack drawPanel = new MyDrawPanelBoardBlack();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().add(drawPanel);
                    frame.setSize(700,500);
                    frame.setVisible(true);
                }
                */
            }

            //		try {
            //			Thread.sleep(1000);
            //		} catch (InterruptedException e) {
            //			// TODO Auto-generated catch block
            //			e.printStackTrace();
            //		}


            if (inputSoot == "pass" && ISMCTSoot == "pass") //双方pass，跳出循环
                flagProcesser = false;
        }

        /*
        MyDrawPanelBlackJudger drawPanelJudger = new MyDrawPanelBlackJudger();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(drawPanelJudger);
        frame.setSize(700,500);
        frame.setVisible(true);
        */

        String whowin = newJudger.whoWin(); //判胜负
        // finishGame(); //游戏结束
        if (whowin == "player") {
            GameStart.playerWin++;
        }
        if (whowin == "hunter") {
            GameStart.hunterWin++;
        }
        //frame.getContentPane().setLayout(null);
    }

    public static void main(String[] args) {
        int debug = 1;
        GameStart newGameStart;
        for(int i=0;i<100;i++) {
            newGameStart = new GameStart();
            newGameStart.forestHunting();
        }
        System.out.println("成了！");
        System.out.println("hunter获胜次数：" + GameStart.hunterWin);
        System.out.println("player获胜次数：" + GameStart.playerWin);
//        会输出到final.out文件，就没必要使用File了
//        String result = "成了！\n最终获胜\nhunter获胜次数: " + GameStart.hunterWin
//                + "\nplayer获胜次数: " + GameStart.playerWin + "\n";
//        FileUtils.writeToFile(result);
    }
}


