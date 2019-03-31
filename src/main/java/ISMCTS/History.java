package ISMCTS;

public class History {
    public State state;
    /* 判断用价值优先还是MCTS */
    public boolean flag;

    public History() {
        this.state = new State();
        this.flag = true;
    }
}
