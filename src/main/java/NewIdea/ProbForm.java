package NewIdea;

public class ProbForm {
    public double[][] form;

    public ProbForm() {
        form = new double[11][11];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                form[i][j] = 0;
            }
        }
    }
}
