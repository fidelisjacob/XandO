import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;


public class XandO {
    JFrame tictac = new JFrame("XandO");
    JButton btnOne = new JButton();
    JButton btnTwo = new JButton();
    JButton btnThree = new JButton();
    JButton btnFour = new JButton();
    JButton btnFive = new JButton();
    JButton btnSix = new JButton();
    JButton btnSeven = new JButton();
    JButton btnEight = new JButton();
    JButton btnNine = new JButton();
    boolean xTurn = true;

    public XandO() {
        drawUI();
        addActionListeners();
    }

    void drawUI() {
        tictac.setLayout(new GridLayout(3, 3));
        tictac.add(btnOne);
        tictac.add(btnTwo);
        tictac.add(btnThree);
        tictac.add(btnFour);
        tictac.add(btnFive);
        tictac.add(btnSix);
        tictac.add(btnSeven);
        tictac.add(btnEight);
        tictac.add(btnNine);

        tictac.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tictac.setSize(400, 400);
        tictac.setVisible(true);
    }

    void addActionListeners() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton buttonClicked = (JButton) e.getSource();
                if (!buttonClicked.getText().equals("")) {
                    return;
                }
            
                if (xTurn) {
                    buttonClicked.setText("X");
                } else {
                    buttonClicked.setText("O");
                }
            
                //buttonClicked.setBackground(Color.GREEN);
                buttonClicked.setFont(buttonClicked.getFont().deriveFont(20f));
            
                xTurn = !xTurn;
                checkForWin();
            }
        };

        btnOne.addActionListener(listener);
        btnTwo.addActionListener(listener);
        btnThree.addActionListener(listener);
        btnFour.addActionListener(listener);
        btnFive.addActionListener(listener);
        btnSix.addActionListener(listener);
        btnSeven.addActionListener(listener);
        btnEight.addActionListener(listener);
        btnNine.addActionListener(listener);
    }

    void checkForWin() {
        String[][] board = {
                { btnOne.getText(), btnTwo.getText(), btnThree.getText() },
                { btnFour.getText(), btnFive.getText(), btnSix.getText() },
                { btnSeven.getText(), btnEight.getText(), btnNine.getText() }
        };

        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals("")) {
                showWinner(board[i][0]);
                return;
            }
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals("")) {
                showWinner(board[0][i]);
                return;
            }
        }

        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals("")) {
            showWinner(board[0][0]);
            return;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals("")) {
            showWinner(board[0][2]);
            return;
        }

        boolean draw = true;
        for (JButton btn : new JButton[]{btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine}) {
            if (btn.getText().equals("")) {
                draw = false;
                break;
            }
        }
        if (draw) {
            JOptionPane.showMessageDialog(tictac, "It's a draw!");
            resetBoard();
        }
    }

    void showWinner(String winner) {
        JOptionPane.showMessageDialog(tictac, "Player " + winner + " wins!");
        resetBoard();
    }

    void resetBoard() {
        JButton[] buttons = {btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine};
        for (JButton btn : buttons) {
            btn.setText("");
        }
        xTurn = true;
    }
}
