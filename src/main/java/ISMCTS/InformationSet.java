package ISMCTS;

import Game.ChessBoard;
import NewIdea.ProbForm;
import UI.GameStart;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class InformationSet {

    public ProbForm probForm;
    public String player;
    private transient Random random;
    private List<Integer> updatecoord;

    /* 状态矩阵 */
    public String[][] knownList = {
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

    public InformationSet(String player) {
        this.random = new Random();
        this.player = player;
        this.probForm = new ProbForm();
        this.updatecoord = new ArrayList<>();
    }

    public void initialProbForm(int max) {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (this.knownList[i][j] != this.player && this.knownList[i][j] != "0") {
                    this.probForm.form[i][j] = max;
                } else this.probForm.form[i][j] = 0;
            }
        }
    }

    public ChessBoard getBoard() {
        return new ChessBoard();
    }

    public History sampleState() {
        History history = new History();
        updatecoord.clear();
        String opponent;
        if (player.equals("1")) {
            opponent = "2";
        } else opponent = "1";

        List<Integer> coord = new ArrayList<>();
        int sum = 0, knownOpponentChessCount = 0;
        State state = new State(knownList, player);
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (knownList[i][j].equals("0")) {
                    coord.add(i * 10 + j);
                    sum++;
                }
                if (knownList[i][j] != this.player && knownList[i][j] != "0") {
                    knownOpponentChessCount++;
                }
            }
        }

        int need = GameStart.count - knownOpponentChessCount;
        if (GameStart.count < 10) {
            history.flag = false;
        }

        if (sum > 0) {
            for (int i = 0; i < need; i++) {
                int index = random.nextInt(sum);
                int temp = coord.get(index);
                state.board[temp / 10][temp % 10] = opponent;
                updatecoord.add(temp);
                coord.remove(index);
                sum--;
                if (sum < 1) break;
            }
        }

        history.state.clone(state);
        return history;
    }

    public void updateProForm(int max) {
        for (int i = 0; i < updatecoord.size(); i++) {
            if (probForm.form[updatecoord.get(i) / 10][updatecoord.get(i) % 10] != max)
                probForm.form[updatecoord.get(i) / 10][updatecoord.get(i) % 10]++;
        }
    }

    public void standardForm(int max) {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                probForm.form[i][j] = probForm.form[i][j] / max;
            }
        }
    }
}
