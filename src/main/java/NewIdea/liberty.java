package NewIdea;

import ISMCTS.InformationSet;

public class liberty {
    int []block;
    int blockLength;
    InformationSet set;
    public liberty(InformationSet set){
        this.set = set;
    }
    public void Getblock(int i, int j){

                    block = new int[81];
                    blockLength = 1;
                    block[0] = i*10+ j;
                    recursion(i,j);
    }

    public void recursion(int i,int j){
        //Left
        if(i-1 >= 1 && set.knownList[i-1][j] == set.knownList[i][j] && isInBlock((i-1)*10+j)){
            block[blockLength] = (i-1)*10 + j;
            blockLength++;
            recursion(i-1,j);
        }
        //Up
        if(j-1 >= 1 && set.knownList[i][j-1] == set.knownList[i][j] && isInBlock(i*10+j-1)){
            block[blockLength] = i*10 + j-1;
            blockLength++;
            recursion(i,j-1);
        }
        //Right
        if(i+1 < 10 && set.knownList[i+1][j] == set.knownList[i][j] && isInBlock((i+1)*10+j)){
            block[blockLength] = (i+1)*10 + j;
            blockLength++;
            recursion(i+1,j);
        }
        //Down
        if(j+1 < 19 && set.knownList[i][j+1] == set.knownList[i][j] && isInBlock(i*10+j+1)){
            block[blockLength] = i*10 + j+1;
            blockLength++;
            recursion(i,j+1);
        }
    }



    public boolean isInBlock(int neighbor){
        for(int i = 0;i < blockLength; i++)
        {
            if (block[i] == neighbor) return false;
        }
        return true;
    }

    public int GetOpponentsum(String player){
        int opponentsum=0;
        int xx;
        int yy;
        for(int i=0;i<blockLength;i++) {
            xx = block[i]/10;
            yy = block[i]%10;
            if (xx - 1 > 0 && set.knownList[xx - 1][yy] != player && set.knownList[xx - 1][yy] != "0" && set.probForm.form[xx - 1][yy] == 1) {
                opponentsum++;
            }
            if (yy - 1 > 0 && set.knownList[xx][yy - 1] != player && set.knownList[xx][yy - 1] != "0" && set.probForm.form[xx][yy - 1] == 1) {
                opponentsum++;
            }

            if (yy + 1 < 10 && set.knownList[xx][yy + 1] != player && set.knownList[xx][yy + 1] != "0" && set.probForm.form[xx][yy + 1] == 1) {
                opponentsum++;
            }
            if (xx + 1 < 10 && set.knownList[xx + 1][yy] != player && set.knownList[xx + 1][yy] != "0" && set.probForm.form[xx + 1][yy] == 1) {
                opponentsum++;
            }
        }
        return opponentsum;
    }

}
