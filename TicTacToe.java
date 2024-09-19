/*<applet code="TicTacToe.class" width="300" height="300"> </applet>*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JApplet implements ActionListener {

    private JButton[][] grid = new JButton[3][3];
    private JButton resetButton;
    private int count;
    private String currentPlayer;

    @Override
    public void init() {
        count = 0;
        currentPlayer = "X";
        setBackground(Color.white);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        gridPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = new JButton();
                grid[i][j].setBackground(Color.white);
                grid[i][j].setForeground(Color.black);
                grid[i][j].setFont(new Font("Serif", Font.BOLD, 48));
                grid[i][j].addActionListener(this);
                gridPanel.add(grid[i][j]);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        add(resetButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("")) {
                button.setText(currentPlayer);
                button.setEnabled(false);
                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                count++;
                checkWin();
            }
        } else if (e.getSource() == resetButton) {
            resetGame();
        }
    }

    private void checkWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRow(i) || checkColumn(i)) {
                JOptionPane.showMessageDialog(this, currentPlayer + " wins!");
                disableGrid();
                return;
            }
        }
        if (checkDiagonals()) {
            JOptionPane.showMessageDialog(this, currentPlayer + " wins!");
            disableGrid();
            return;
        }
        if (count == 9) {
            JOptionPane.showMessageDialog(this, "It's a tie!");
            disableGrid();
        }
    }

    private boolean checkRow(int row) {
        for (int i = 0; i < 3; i++) {
            if (!grid[row][i].getText().equals(currentPlayer)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int column) {
        for (int i = 0; i < 3; i++) {
            if (!grid[i][column].getText().equals(currentPlayer)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonals() {
        for (int i = 0; i < 3; i++) {
            if (!grid[i][i].getText().equals(currentPlayer)) {
                return false;
            }
        }
        return true;
    }

    private void disableGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j].setText("");
                grid[i][j].setEnabled(true);
            }
        }
        count = 0;
        currentPlayer = "X";
    }
}