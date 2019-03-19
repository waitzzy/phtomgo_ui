package Game;


import java.io.Serializable;


public class Chess implements Serializable, Cloneable {
    Coord coord;
    int health;
    char status;       //b黑, w白, e为空, ?为空但不可用


    Chess(int x, int y, char status) {
        this.coord = new Coord(x, y);
        this.status = status;
    }

    //更新本棋子的信息
    public void update() {
        if (status == 'b' || status == 'w') {
            updateHealth();
        } else {
            health = -1;
        }

    }

    //更新本棋子的Group信息

    /**
     * 更新本棋子的气
     */
    private void updateHealth() {
        health = 0;
        ChessBoard.getChesses(coord.getNear4Coord(true)).stream().filter(x -> (x.status != 'b' && x.status != 'w')).forEach(x -> health++);
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chess)) return false;
        Chess chess = (Chess) o;
        return health == chess.health &&
                status == chess.status &&
                coord.equals(chess.coord);
    }



    @Override
    protected Chess clone() throws CloneNotSupportedException {
        Chess clone = (Chess) super.clone();

        return clone;
    }

    @Override
    public String toString() {
        return "Chess{" +
                "coord=" + coord +
                ", health=" + health +
                ", status=" + status +
                '}';
    }
}
