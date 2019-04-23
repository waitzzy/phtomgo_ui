package NewIdea;
import ISMCTS.InformationSet;
import ISMCTS.State;

public class Value {
    //private int basicValue = 1412;
    //double [][] valueForm ;
    double sumValue;
    public Value(){
         sumValue = 0;
    }


    private int [][]ChessEffect = {
            {210,105,42,14,4,1},
            {105,77,38,8,1,0},
            {42,38,11,2,0,0},
            {14,8,2,1,0,0},
            {4,1,0,0,0,0},
            {1,0,0,0,0,0}
    };

    transient private double[][] Compensate_Area={
            {999,999,0.5,0.2,0.067,0.02},
            {999,999,0.367,0.181,0.038,0},
            {0.5,0.367,0.181,0.052,0,0},
            {0.067,0.038,0,0,0,0},
            {0.02,0,0,0,0,0},
    };

    /*
    transient private int[][] Effect_Area = {
            {0, 1},{0,2},{0,3},{0,4},{0,5},
            {1, 0},{2,0},{3,0},{4,0},{5,0},
            {0, -1},{0,-2},{0,-3},{0,-4},{0,-5},
            {-1, 0},{-2,0},{-3,0},{-4,0},{-5,0},
            {1, 1}, {2, 2}, {3, 3},
            {-1, 1},{-2, 2}, {-3, 3},
            {-1, -1},{-2, -2}, {-3, -3},
            {1, -1},{2, -2},{3, -3},
            {2, 1}, {2,3},{2,4},{3,4},{1,2},{3,2},{4,2},{4,3},
            {-2, 1}, {-2,3},{-2,4},{-3,4},{-1,2},{-3,2},{-4,2},{-4,3},
            {-2, -1}, {-2,-3},{-2,-4},{-3,-4},{-1,-2},{-3,-2},{-4,-2},{-4,-3},
            {2, -1}, {2,-3},{2,-4},{3,-4},{1,-2},{3,-2},{4,-2},{4,-3},
    };
    */
    transient private int[][] Effect_Area = {
            {0, 1},{0,2},{0,3},
            {1, 0},{2,0},{3,0},
            {0, -1},{0,-2},{0,-3},
            {-1, 0},{-2,0},{-3,0},
            {1, 1}, {2, 2},
            {-1, 1},{-2, 2},
            {-1, -1},{-2, -2},
            {1, -1},{2, -2},
            {2, 1},{1,2},
            {-2, 1}, {-1,2},
            {-2, -1}, {-1,-2},
            {2, -1}, {1,-2},
    };


    public double GetMaxValue(State state, String player,int xx,int yy,InformationSet set){
        double Value = 0;
        for(int i=1;i<10;i++){
            for(int j=1;j<10;j++){
                if(state.board[i][j]==player){
                    Value = Value+GetChessValue(i,j);
                }
                else if(state.board[i][j]!=player&&state.board[i][j]!="0"){
                    Value = Value-GetChessValue(i,j)*set.probForm.form[i][j];
                }
            }
        }
        Value = Value+GetChessValue(xx,yy);
        return Value;
    }



    /*

    public double GetChessValue(int xx,int yy){
        double sum = 0;
        for(int [] para :Effect_Area){
            if(xx+para[0]>0&&xx+para[0]<10&&yy+para[1]>0&&yy+para[1]<10){
                if(xx+para[0]==1||xx+para[0]==9){
                    if(para[0]<0){
                        para[0] = 0 -para[0];
                    }
                    if(para[1]<0){
                        para[1]= 0-para[1];
                    }
                    sum = sum + ChessEffect[para[0]][para[1]];
                }
                if(yy+para[1]==1||yy+para[1]==9){
                    if(para[0]<0){
                        para[0] = 0 -para[0];
                    }
                    if(para[1]<0){
                        para[1]= 0-para[1];
                    }
                    sum = sum + ChessEffect[para[0]][para[1]];
                }
                if(xx+para[0]==2||xx+para[0]==8){
                    if(para[0]<0){
                        para[0] = 0 -para[0];
                    }
                    if(para[1]<0){
                        para[1]= 0-para[1];
                    }
                    sum = sum + 0.5*ChessEffect[para[0]][para[1]];
                }
                if(yy+para[1]==2||yy+para[1]==8){
                    if(para[0]<0){
                        para[0] = 0 -para[0];
                    }
                    if(para[1]<0){
                        para[1]= 0-para[1];
                    }
                    sum = sum + 0.5*ChessEffect[para[0]][para[1]];
                }
                if(xx+para[0]>2&&xx+para[0]<8&&yy+para[1]>2&&yy+para[1]<8){
                    if(para[0]<0){
                        para[0] = 0 -para[0];
                    }
                    if(para[1]<0){
                        para[1]= 0-para[1];
                    }
                    sum = sum + ChessEffect[para[0]][para[1]];
                }


            }
        }
        return sum;

    }
*/
    public double GetChessValue(int xx,int yy){
        double ChessValue = 0;
        for(int [] para :Effect_Area) {
            if (xx + para[0] > 0 && xx + para[0] < 10 && yy + para[1] > 0 && yy + para[1] < 10) {
                if((xx + para[0] == 1||xx + para[0]==9)&&(yy + para[1] == 1||yy + para[1]==9)){
                    ChessValue = ChessValue+ 4*GetEffectValue(xx + para[0],yy + para[1],xx,yy);
                }
                else if((xx + para[0] == 1||xx + para[0]==9)&&(yy + para[1] == 2||yy + para[1]==8)){
                    ChessValue =ChessValue+ 3.5*GetEffectValue(xx + para[0],yy + para[1],xx,yy);
                }
                else if((xx + para[0] == 2||xx + para[0]==8)&&(yy + para[1] == 2||yy + para[1]==8)){
                    ChessValue =ChessValue+ 3*GetEffectValue(xx + para[0],yy + para[1],xx,yy);
                }
                else if((xx + para[0] == 2||xx + para[0]==8)&&(yy + para[1] == 1||yy + para[1]==9)){
                    ChessValue =ChessValue+ 3.5*GetEffectValue(xx + para[0],yy + para[1],xx,yy);
                }
                else {
                    ChessValue = ChessValue + GetEffectValue(xx + para[0],yy + para[1],xx,yy);
                }
            }
        }
        return ChessValue;
    }



    /*
    double [][] effect_form;
    private int center_Effect =210;
    transient private int[][] ZhiXian = {
            {0, 1},{0,2},{0,3},{0,4},{0,5},
            {1, 0},{2,0},{3,0},{4,0},{5,0},
            {0, -1},{0,-2},{0,-3},{0,-4},{0,-5},
            {-1, 0},{-2,0},{-3,0},{-4,0},{-5,0},
    };
    transient private int[][] XieXian = {
            {1, 1}, {2, 2}, {3, 3},
            {-1, 1},{-2, 2}, {-3, 3},
            {-1, -1},{-2, -2}, {-3, -3},
            {1, -1},{2, -2}, {3, -3},
    };
    transient private int[][] QiTa = {
            {2, 1}, {2,3},{2,4},{3,4},{1,2},{3,2},{4,2},{4,3},
            {-2, 1}, {-2,3},{-2,4},{-3,4},{-1,2},{-3,2},{-4,2},{-4,3},
            {-2, -1}, {-2,-3},{-2,-4},{-3,-4},{-1,-2},{-3,-2},{-4,-2},{-4,-3},
            {2, -1}, {2,-3},{2,-4},{3,-4},{1,-2},{3,-2},{4,-2},{4,-3},
    };

    public int GetChessEffect(int xx,int yy){
        effect_form[xx][yy] = center_Effect;
        double temp = center_Effect;
        for(int i = 1;i<6;i++) {
            if (xx + i < 10) {
                effect_form[xx + i][yy] = temp / (1.5 + 0.5 * i);
                temp = effect_form[xx + i][yy];
            }
        }

            for(int i =1;i<6;i++){
        }

    }

    public int GetChessValue(){

    }

    private void SetZhiXian(int xx, int yy,String player){
        boolean flag = true;

            for(int i =1;i<6;i++){
                if(xx+i<10){
                    effect_form[xx+i][yy] = effect_form[xx+i-1][yy]/(1.5+0.5*i);
                }
                if(xx-i>0){
                    effect_form[xx-i][yy] = effect_form[xx-i+1][yy]/(1.5+0.5*i);
                }
                if(yy+i<10){
                    effect_form[xx][yy+i] = effect_form[xx][yy+i-1]/(1.5+0.5*i);
                }
                if(yy-i>0){
                    effect_form[xx][yy-i] = effect_form[xx][yy-i+1]/(1.5+0.5*i);
                }
            }
    }

    private void SetXieXian(int xx,int yy){
          for(int i = 1;i<4;i++){
              if(xx+i<10&&yy+i<10){
                  effect_form[xx+i][yy+i] = (effect_form[xx+i][yy]+effect_form[xx][yy+i])/Math.pow(Math.E,i);
              }
              if(xx-i>0&&yy-i>0){
                  effect_form[xx-i][yy-i] = (effect_form[xx-i][yy]+effect_form[xx][yy-i])/Math.pow(Math.E,i);
              }
              if(xx+i<10&&yy-i>0){
                  effect_form[xx+i][yy-i] = (effect_form[xx+i][yy]+effect_form[xx][yy-i])/Math.pow(Math.E,i);
              }
              if(xx-i>0&&yy+i<10){
                  effect_form[xx-i][yy+i] = (effect_form[xx-i][yy]+effect_form[xx][yy+i])/Math.pow(Math.E,i);
              }
          }
    }

    private void SetQiTa(int xx,int yy){

    }*/


    private double GetEffectValue(int xx,int yy,int x,int y){
        double distance = Math.sqrt((xx-x)*(xx-x)+(yy-y)*(yy-y));
        if(distance == 1){
            return 9;
        }
        else if(distance == Math.sqrt(2)){
            return 7;
        }
        else if(distance == 2){
            return 4;
        }
        else if(distance == Math.sqrt(5)){
            return 3;
        }
        else if(distance == 3){
            return 1;
        }
        /*
        else if(distance == Math.sqrt(8)){
            return 11;
        }
        else if(distance == Math.sqrt(10)){
            return 8;
        }
        else if(distance == 4){
            return 4;
        }
        else if(distance == 5){
            return 1;
        }
        */
        else
            return 0;
    }
}