import javax.swing.*;

class Main {
    public static void initGame() {
        JFrame game = new JFrame("Az Laf Çok İş");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Board board = new Board();
        game.add(board);
        game.addMouseListener(board);
        game.pack();
        game.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initGame();
            }
        });
    }
}

