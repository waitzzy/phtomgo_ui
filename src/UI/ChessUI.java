package UI;

import java.awt.*;
import java.awt.event.*;


class ChessPad extends Panel implements MouseListener, ActionListener {

    int x = -1, y = -1, 棋子颜色 = 1; //控制棋子颜色的成员变量。

    Button button = new Button("重新开局"); //控制重新开局的按钮。

    TextField text_1 = new TextField("请黑棋下子"),

    text_2 = new TextField(" "); //提示下棋的两个文本框。

    ChessPad() {

        setSize(500, 500);

        setLayout(null);

        setBackground(Color.pink);

        addMouseListener(this);

        add(button);

        button.setBounds(10, 5, 60, 26);

        button.addActionListener(this);

        add(text_1);
        text_1.setBounds(90, 5, 90, 24);

        add(text_2);
        text_2.setBounds(290, 5, 90, 24);

        text_1.setEditable(false);

        text_2.setEditable(false);

    }

    public void paint(Graphics g) {

        for (int i = 40; i <= 390; i = i + 50) {

            g.drawLine(40, i, 390, i);

        }

        // g.drawLine(40,400,390,400);

        for (int j = 40; j <= 390; j = j + 50) {
            g.drawLine(j, 40, j, 390);
        }

        // g.drawLine(400, 40, 400, 400);


    }

    public void mousePressed(MouseEvent e) //当按下鼠标左键时下棋子

    {

        if (e.getModifiers() == InputEvent.BUTTON1_MASK) {

            x = (int) e.getX();
            y = (int) e.getY(); //获取按下鼠标时的坐标位置

            ChessPoint_black chesspoint_black = new ChessPoint_black(this);

            ChessPoint_white chesspoint_white = new ChessPoint_white(this);

            int a = (x + 25) / 50, b = (y + 25) / 50;

            if (x / 50 < 1 || y / 50 < 1 || x / 50 > 9 || y / 50 > 9) //棋盘外不下子

            {
            } else {
                if (棋子颜色 == 1) //当棋子颜色是1时下黑棋子

                {
                    this.add(chesspoint_black);

                    chesspoint_black.setBounds(a * 50 - 20, b * 50 - 20, 30, 30);

                    棋子颜色 = 棋子颜色 * (-1);

                    text_2.setText("请白棋下子");

                    text_1.setText("");

                } else if (棋子颜色 == -1) //当棋子颜色是-1时下白棋子

                {

                    this.add(chesspoint_white);

                    chesspoint_white.setBounds(a * 50 - 20, b * 50 - 20, 30, 30);

                    棋子颜色 = 棋子颜色 * (-1);

                    text_2.setText("请黑棋下子");

                    text_1.setText("");

                }

            }

        }

    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void actionPerformed(ActionEvent e) {

        this.removeAll();
        棋子颜色 = 1;

        add(button);
        button.setBounds(10, 5, 60, 26);

        add(text_1);
        text_1.setBounds(90, 5, 90, 24);

        text_2.setText("");
        text_1.setText("请黑棋子下子");

        add(text_2);
        text_2.setBounds(290, 5, 90, 24);

    }

}

//负责创建黑色棋子的类

class ChessPoint_black extends Canvas implements MouseListener {

    ChessPad chesspad = null;

    ChessPoint_black(ChessPad p) {

        setSize(30, 30);
        chesspad = p;
        addMouseListener(this);

    }

    public void paint(Graphics g) {

        g.setColor(Color.black);
        g.fillOval(0, 0, 30, 30);

    }

    public void mousePressed(MouseEvent e) {

        if (e.getModifiers() == InputEvent.BUTTON3_MASK) {

            chesspad.remove(this); //当用鼠标右击棋子时，从棋盘中去掉棋子（悔棋）。

            chesspad.棋子颜色 = 1;

            chesspad.text_2.setText("");
            chesspad.text_1.setText("请黑棋子下子");

        }

    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

        if (e.getClickCount() >= 2)

            chesspad.remove(this); //当双击该棋子时，吃掉该棋子

    }

}

//负责创建白色棋子的类

class ChessPoint_white extends Canvas implements MouseListener {

    ChessPad chesspad = null;

    ChessPoint_white(ChessPad p) {

        setSize(30, 30);
        addMouseListener(this);

        chesspad = p;

    }

    public void paint(Graphics g) {

        g.setColor(Color.white);

        g.fillOval(0, 0, 30, 30);

    }

    public void mousePressed(MouseEvent e) {

        if (e.getModifiers() == InputEvent.BUTTON3_MASK) {

            chesspad.remove(this);
            chesspad.棋子颜色 = -1;

            chesspad.text_2.setText("请白棋下子");

            chesspad.text_1.setText("");

        }

    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

        if (e.getClickCount() >= 2)

            chesspad.remove(this);

    }

}

public class ChessUI extends Frame //添加棋盘窗口

{

    ChessPad chesspad = new ChessPad();

    ChessUI() {

        setSize(600, 600);

        setVisible(true);

        setLayout(null);

        Label label = new Label("单击下棋子，双击吃棋子，右击棋子悔棋", Label.CENTER);

        add(label);
        label.setBounds(70, 55, 440, 26);

        label.setBackground(Color.orange);

        add(chesspad);
        chesspad.setBounds(70, 90, 440, 440);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });

    }

    public static void main(String args[]) {

        ChessUI chess = new ChessUI();

    }

}
