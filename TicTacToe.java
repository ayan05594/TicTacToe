import java.util.Scanner;

class TicTacToe {
    static char[][] board = new char[3][3];
    static char currentPlayer = 'X';

    public static void main(String[] args) {
        initBoard();
        printBoard();

        Scanner scanner = new Scanner(System.in);

        while (!isGameOver()) {
            System.out.println("Player " + currentPlayer + "'s turn.");
            System.out.print("Enter row (0-2): ");
            int row = scanner.nextInt();
            System.out.print("Enter column (0-2): ");
            int col = scanner.nextInt();

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                printBoard();
                if (hasWon()) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                } else if (isBoardFull()) {
                    System.out.println("It's a draw!");
                    break;
                }
                switchPlayer();
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
    }

    static void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    static boolean isBoardFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean hasWon() {
        return checkRowsForWin() || checkColsForWin() || checkDiagonalsForWin();
    }

    static boolean checkRowsForWin() {
        for (char[] row : board) {
            if (row[0] == row[1] && row[1] == row[2] && row[0] != '-') {
                return true;
            }
        }
        return false;
    }

    static boolean checkColsForWin() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
                return true;
            }
        }
        return false;
    }

    static boolean checkDiagonalsForWin() {
        return (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-')
                || (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-');
    }

    static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    static boolean isGameOver() {
        return hasWon() || isBoardFull();
    }
}
