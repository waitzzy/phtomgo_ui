package NewIdea;

public class prob_form {
    public double[][] form;
    public prob_form(){
        this.form = new double[11][11];
        for(int i = 0;i<=10;i++){
            for(int j = 0;j<=10;j++){
                form[i][j] = 0;
            }
        }
    }
}
