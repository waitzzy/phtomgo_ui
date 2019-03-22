package ISMCTS;

import Game.Chess;
import Game.ChessBoard;
import Game.Coord;

public class Action {

    public int x;
    public int y;

    Action(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Action o) {
        if (this.x == o.x && this.y == o.y)
            return true;
        else
            return false;
    }
}
