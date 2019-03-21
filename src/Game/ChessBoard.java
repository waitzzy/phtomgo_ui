package Game;

import java.util.ArrayList;
import java.util.List;


public class ChessBoard {
    public static Chess[][] board;

    public static Chess[][] cloneBoard(Chess[][] srcBoard) throws CloneNotSupportedException {
        Chess[][] temp = new Chess[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                temp[i][j] = srcBoard[i][j].clone();
            }
        }
        return temp;
    }

    public static void print(Chess[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i == 0) {
                System.out.print("   ");
                for (int j = 0; j < 9; j++)
                    System.out.print((char) (j + 'A') + "  ");
                System.out.println();
            }
            for (int j = 0; j < 9; j++) {
                if (j == 0)
                    System.out.print(i + 1 + "  ");
                System.out.print(board[i][j].status + "  ");
            }
            System.out.println();
        }
    }

    public ChessBoard() {
        board = new Chess[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new Chess(i, j, '0');
            }
        }
    }

    public static Chess getChess(int x, int y) {
        return board[x][y];
    }

    public static List<Chess> getChesses(List<Coord> coords) {
        List<Chess> result = new ArrayList<>();
        coords.forEach(x -> result.add(getChess(x.x, x.y)));
        return result;
    }

    public boolean isLegal(int x, int y) {
        Chess chess = getChess(x, y);
        if (chess.status == 'e')
            return true;
        else
            return false;
    }

    public void SetChess(Coord coord, char status) {
        Chess chess = new Chess(coord.x, coord.y, status);
        if (coord.isLegal() && isLegal(coord.x, coord.y))
            board[coord.x][coord.y] = chess;
    }

    public boolean equals(ChessBoard other) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (other.board[i][j] != this.board[i][j])
                    return false;
            }
        }
        return true;
    }
}

