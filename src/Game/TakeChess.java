package Game;
import UI.GameStart;

import java.util.ArrayList;

public class TakeChess {
    int debug = 1;
    static String [][]forestJudgerStatus={
            {"3","3","3","3","3","3","3","3","3","3","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","3","3","3","3","3","3","3","3","3","3"}
    };

    String [][]lifeTestStatus={
            {"3","3","3","3","3","3","3","3","3","3","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","0","0","0","0","0","0","0","0","0","3"},
            {"3","3","3","3","3","3","3","3","3","3","3"}
    };

    int xPlayerSave = 0;
    int yPlayerSave = 0;
    int xHunterSave = 0;
    int yHunterSave = 0;
    static boolean lifeState = false;

    public TakeChess(String[][] board){
        forestJudgerStatus = board;
    }
    public String Judgement(String color, int Sootx, int Sooty){ //先判断有没有提子，然后判断有没有非法，剩下的是合法

        String JudgerSpeak = "legal";
        lifeState = true;
        if(forestJudgerStatus[Sootx][Sooty].equals("1")||forestJudgerStatus[Sootx][Sooty].equals("2")){ //当前位置有子
            JudgerSpeak = "illegal";
            if(debug == 1){}
        }else if(Sootx == xPlayerSave && Sooty == yPlayerSave && color.equals("player") && //player方，在判断take前，检查是否打劫
                forestJudgerStatus[Sootx+1][Sooty].equals("2") && forestJudgerStatus[Sootx][Sooty+1].equals("2") &&
                forestJudgerStatus[Sootx-1][Sooty].equals("2") && forestJudgerStatus[Sootx][Sooty-1].equals("2")){
            JudgerSpeak = "illegal";
            if(debug == 1){}
        }else if(Sootx == xHunterSave && Sooty == yHunterSave && color.equals("hunter") && //hunter方，在判断take前，检查是否打劫
                forestJudgerStatus[Sootx+1][Sooty].equals("1") && forestJudgerStatus[Sootx][Sooty+1].equals("1") &&
                forestJudgerStatus[Sootx-1][Sooty].equals("1") && forestJudgerStatus[Sootx][Sooty-1].equals("1")){
            JudgerSpeak = "illegal";
            if(debug == 1){}
        }else if(forestJudgerStatus[Sootx][Sooty].equals("0") && color.equals("player")){ //player落子，递归找气，判断是不是take
            forestJudgerStatus[Sootx][Sooty] = "1"; //放上棋子
            // Player.forestPlayerStatus[Sootx][Sooty] = "1"; //放上棋子


            if(forestJudgerStatus[Sootx + 1][Sooty].equals("2")){
                lifeState = false;
                deadCheck("player", Sootx + 1, Sooty, "4");
                if(lifeState == true){
                    initia("4");
                }else if(lifeState == false){
                    JudgerSpeak = "take";
                }
            }

            if(forestJudgerStatus[Sootx - 1][Sooty].equals("2")){
                lifeState = false;
                deadCheck("player", Sootx - 1, Sooty, "5");
                if(lifeState == true){
                    initia("5");
                }else if(lifeState == false){
                    JudgerSpeak = "take";
                }
            }

            if(forestJudgerStatus[Sootx][Sooty + 1].equals("2")){
                lifeState = false;
                deadCheck("player", Sootx, Sooty + 1, "6");
                if(lifeState == true){
                    initia("6");
                }else if(lifeState == false){
                    JudgerSpeak = "take";
                }
            }

            if(forestJudgerStatus[Sootx][Sooty - 1].equals("2")){
                lifeState = false;
                deadCheck("player", Sootx, Sooty - 1, "7");
                if(lifeState == true){
                    initia("7");
                }else if(lifeState == false){
                    JudgerSpeak = "take";
                }
            }

        }else if(forestJudgerStatus[Sootx][Sooty].equals("0") && color.equals("hunter")){ //hunter落子，递归找气，判断是不是take
            forestJudgerStatus[Sootx][Sooty] = "2"; //放上棋子
            //Hunter.forestHunterStatus[Sootx][Sooty] = "2"; //放上棋子
            if(forestJudgerStatus[Sootx + 1][Sooty].equals("1")){
                lifeState = false;
                deadCheck("hunter", Sootx + 1, Sooty, "4");
                if(lifeState == true){
                    initia("4");
                }else if(lifeState == false){
                    JudgerSpeak = "take";
                }
            }

            if(forestJudgerStatus[Sootx - 1][Sooty].equals("1")){
                lifeState = false;
                deadCheck("hunter", Sootx - 1, Sooty, "5");
                if(lifeState == true){
                    initia("5");
                }else if(lifeState == false){
                    JudgerSpeak = "take";
                }
            }

            if(forestJudgerStatus[Sootx][Sooty + 1].equals("1")){
                lifeState = false;
                deadCheck("hunter", Sootx, Sooty + 1, "6");
                if(lifeState == true){
                    initia("6");
                }else if(lifeState == false){
                    JudgerSpeak = "take";
                }
            }

            if(forestJudgerStatus[Sootx][Sooty - 1].equals("1")){
                lifeState = false;
                deadCheck("hunter", Sootx, Sooty - 1, "7");
                if(lifeState == true){
                    initia("7");
                }else if(lifeState == false){
                    JudgerSpeak = "take";
                }
            }
        }
        if(forestJudgerStatus[Sootx+1][Sooty].equals("1") && forestJudgerStatus[Sootx][Sooty+1].equals("1") && //下到自己或对方眼里了，撞气也是禁入点
                forestJudgerStatus[Sootx-1][Sooty].equals("1") && forestJudgerStatus[Sootx][Sooty-1].equals("1")
                && JudgerSpeak.equals("take") == false){
            forestJudgerStatus[Sootx][Sooty] = "0";
            JudgerSpeak = "illegal";
            if(debug == 1){}
        }else if(forestJudgerStatus[Sootx+1][Sooty].equals("3") && forestJudgerStatus[Sootx][Sooty+1].equals("1") && //下到自己或对方眼里了，撞气也是禁入点
                forestJudgerStatus[Sootx-1][Sooty].equals("1") && forestJudgerStatus[Sootx][Sooty-1].equals("1")
                && JudgerSpeak.equals("take") == false){
            forestJudgerStatus[Sootx][Sooty] = "0";
            JudgerSpeak = "illegal";
            if(debug == 1){}
        }else if(forestJudgerStatus[Sootx+1][Sooty].equals("1") && forestJudgerStatus[Sootx][Sooty+1].equals("3") && //下到自己或对方眼里了，撞气也是禁入点
                forestJudgerStatus[Sootx-1][Sooty].equals("1") && forestJudgerStatus[Sootx][Sooty-1].equals("1")
                && JudgerSpeak.equals("take") == false){
            forestJudgerStatus[Sootx][Sooty] = "0";
            JudgerSpeak = "illegal";
            if(debug == 1){}
        }else if(forestJudgerStatus[Sootx+1][Sooty].equals("1") && forestJudgerStatus[Sootx][Sooty+1].equals("1") && //下到自己或对方眼里了，撞气也是禁入点
                forestJudgerStatus[Sootx-1][Sooty].equals("3") && forestJudgerStatus[Sootx][Sooty-1].equals("1")
                && JudgerSpeak.equals("take") == false){
            forestJudgerStatus[Sootx][Sooty] = "0";
            JudgerSpeak = "illegal";
            if(debug == 1){}
        }else if(forestJudgerStatus[Sootx+1][Sooty].equals("1") && forestJudgerStatus[Sootx][Sooty+1].equals("1") && //下到自己或对方眼里了，撞气也是禁入点
                forestJudgerStatus[Sootx-1][Sooty].equals("1") && forestJudgerStatus[Sootx][Sooty-1].equals("3")
                && JudgerSpeak.equals("take") == false){
            forestJudgerStatus[Sootx][Sooty] = "0";
            JudgerSpeak = "illegal";
            if(debug == 1){}
        }
        if(forestJudgerStatus[Sootx+1][Sooty].equals("2") && forestJudgerStatus[Sootx][Sooty+1].equals("2") && //下到自己或对方眼里了，撞气也是禁入点
                forestJudgerStatus[Sootx-1][Sooty].equals("2") && forestJudgerStatus[Sootx][Sooty-1].equals("2")
                && JudgerSpeak.equals("take") == false){
            forestJudgerStatus[Sootx][Sooty] = "0";
            JudgerSpeak = "illegal";
            if(debug == 1){}
        }else if(forestJudgerStatus[Sootx+1][Sooty].equals("3") && forestJudgerStatus[Sootx][Sooty+1].equals("2") && //下到自己或对方眼里了，撞气也是禁入点
                forestJudgerStatus[Sootx-1][Sooty].equals("2") && forestJudgerStatus[Sootx][Sooty-1].equals("2")
                && JudgerSpeak.equals("take") == false){
            forestJudgerStatus[Sootx][Sooty] = "0";
            JudgerSpeak = "illegal";
            if(debug == 1){}
        }else if(forestJudgerStatus[Sootx+1][Sooty].equals("2") && forestJudgerStatus[Sootx][Sooty+1].equals("3") && //下到自己或对方眼里了，撞气也是禁入点
                forestJudgerStatus[Sootx-1][Sooty].equals("2") && forestJudgerStatus[Sootx][Sooty-1].equals("2")
                && JudgerSpeak.equals("take") == false){
            forestJudgerStatus[Sootx][Sooty] = "0";
            JudgerSpeak = "illegal";
            if(debug == 1){}
        }else if(forestJudgerStatus[Sootx+1][Sooty].equals("2") && forestJudgerStatus[Sootx][Sooty+1].equals("2") && //下到自己或对方眼里了，撞气也是禁入点
                forestJudgerStatus[Sootx-1][Sooty].equals("3") && forestJudgerStatus[Sootx][Sooty-1].equals("2")
                && JudgerSpeak.equals("take") == false){
            forestJudgerStatus[Sootx][Sooty] = "0";
            JudgerSpeak = "illegal";
            if(debug == 1){}
        }else if(forestJudgerStatus[Sootx+1][Sooty].equals("2") && forestJudgerStatus[Sootx][Sooty+1].equals("2") && //下到自己或对方眼里了，撞气也是禁入点
                forestJudgerStatus[Sootx-1][Sooty].equals("2") && forestJudgerStatus[Sootx][Sooty-1].equals("3")
                && JudgerSpeak.equals("take") == false){
            forestJudgerStatus[Sootx][Sooty] = "0";
            JudgerSpeak = "illegal";
            if(debug == 1){}
        }
        //打劫

        if(color.equals("player")){ //为判断打劫，存储前次落子
            xPlayerSave = Sootx;
            yPlayerSave = Sooty;
        }else if(color.equals("hunter")){ //为判断打劫，存储前次落子
            xHunterSave = Sootx;
            yHunterSave = Sooty;
        }

        //	System.out.println("Input is" + JudgerSpeak);
        return JudgerSpeak;
    }
    //存矩阵

    int recursionCount = 0;
    String lifeFlag = null;
    String selfFlag = null;

    public void initia(String num){
        for(int i = 1; i < lifeTestStatus.length - 1; i++){ //生命状态矩阵归零
            for(int j = 1;j < lifeTestStatus.length - 1; j++){
                if(lifeTestStatus[i][j].equals(num)){
                    lifeTestStatus[i][j] = "0";
                }
            }
        }
    }

    public void deadCheck(String color, int x, int y, String num){ //递归判断死活
        if(color.equals("player")){
            lifeFlag = "2";
            selfFlag = "1";
        }else if(color.equals("hunter")){
            lifeFlag = "1";
            selfFlag = "2";
        }

        if(forestJudgerStatus[x][y].equals(lifeFlag) && lifeTestStatus[x][y].equals(num) == false){ //把找到的对方子，还未作标记的，作标记
            lifeTestStatus[x][y] = num;

            if(forestJudgerStatus[x+1][y].equals("0")||forestJudgerStatus[x][y+1].equals("0")||
                    forestJudgerStatus[x-1][y].equals("0")||forestJudgerStatus[x][y-1].equals("0")){
                lifeState = true; //如果有气，就是活棋

            }else{
                //	lifeState = false;

                if(forestJudgerStatus[x+1][y].equals(lifeFlag))
                    deadCheck(color, x+1, y, num);
                if(forestJudgerStatus[x-1][y].equals(lifeFlag))
                    deadCheck(color, x-1, y, num);
                if(forestJudgerStatus[x][y+1].equals(lifeFlag))
                    deadCheck(color, x, y+1, num);
                if(forestJudgerStatus[x][y-1].equals(lifeFlag))
                    deadCheck(color, x, y-1, num);
            }
        }
        if(GameStart.count == 0 ||GameStart.count == 1){
            lifeState = true;
        }
        if(debug == 1){

            for(int i = 1; i < lifeTestStatus.length - 1; i++){
                for(int j = 1;j < lifeTestStatus.length - 1; j++){
                    System.out.print(lifeTestStatus[i][j]+" ");
                }//System.out.print("\n");
            }//System.out.print("\n");
        }


    }
    public ArrayList killDeadLife(){ //提子，提去所有标记为4的子

        ArrayList deadList = new ArrayList();

        for(int i = 1; i < 10; i++ ){
            for(int j = 1; j < 10; j++ ){
                if(lifeTestStatus[i][j].equals("4")||lifeTestStatus[i][j].equals("5")
                        ||lifeTestStatus[i][j].equals("6")||lifeTestStatus[i][j].equals("7")){

                    lifeTestStatus[i][j] = "0";
                    forestJudgerStatus[i][j] = "0";

                    deadList.add(i*9+j); //传一个代表提子的数组到当前方

                }
            }
        }

        return deadList;
    }

}
