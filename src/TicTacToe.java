import java.util.Scanner;

public class TicTacToe {
    final static int ROWS = 3;
    final static int COLS = 3;        

    static void drawBoard(char[][] board) {
        for (char[] row: board) {
            for (char c: row) {
                System.out.format("%c ", c);
            }
            System.out.println("");
        }
    }

    static boolean winRow(char[][] board, int row) {
        if (board[row][0] == '.') {
            return false;
        }
        for (int col = 1; col < COLS; ++col) {
             if (board[row][col] != board[row][0]) {
                return false;
             }
        }
        return true;
    }

    static boolean winCol(char[][] board, int col) {
        if (board[0][col] == '.') {
            return false;
        }
        for (int row = 1; row < ROWS; ++row) {
             if (board[row][col] != board[0][col]) {
                return false;
             }
        }
        return true;
    }

    static boolean win(char[][] board) {
        for (int row = 0; row < ROWS; ++row) {
            if (winRow(board, row)) {
                return true;
            }
        }

        for (int col = 0; col < COLS; ++col) {
            if (winCol(board, col)) {
                return true;
            }
        }

        // check diaganol top left to bottom right
        if (board[0][0] != '.' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return true;
        }

        // check diaganol top right to bottom left
        if (board[0][2] != '.' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return true;
        }

        return false;
    }

    static void test() {
        {
            char[][] board = {
                {'X', '.', 'X'},
                {'.', 'O', 'O'},
                {'.', 'O', '.'},
            };
            assert !win(board);
        }
        {
            char[][] board = {
                {'X', 'X', 'X'},
                {'.', 'O', 'O'},
                {'.', 'O', '.'},
            };
            assert !winCol(board, 0);
            assert winRow(board, 0);
            assert !winRow(board, 1);
            assert win(board);
        }
        {
            char[][] board = {
                {'X', 'O', 'X'},
                {'.', 'O', 'O'},
                {'.', 'O', '.'},
            };
            assert win(board);
        }
        {
            char[][] board = {
                {'X', '.', 'X'},
                {'.', 'X', 'O'},
                {'.', 'O', 'X'},
            };
            assert win(board);
        }
        {
            char[][] board = {
                {'X', '.', 'O'},
                {'.', 'O', 'O'},
                {'O', 'O', '.'},
            };
            assert win(board);
        }
        {
            char[][] board = {
                {'X', '.', 'X'},
                {'.', 'O', 'O'},
                {'O', 'O', 'O'},
            };
            assert win(board);
        }
        {
            char[][] board = {
                {'X', '.', 'X'},
                {'.', 'O', 'X'},
                {'.', 'O', 'X'},
            };
            assert win(board);
        }
        {
            char[][] board = {
                {'X', '.', 'O'},
                {'.', 'X', 'O'},
                {'.', 'O', 'X'},
            };
            assert win(board);
        }
    }

    public static void main(String[] args) {
        boolean gameOver = false;
        char[][] board = new char[ROWS][COLS];
        Scanner input = new Scanner(System.in);
        char[] players = {'O', 'X'};
        int curPlayer = 0;
        int row, col;

        test();

        for (row = 0; row < ROWS; ++row) {
            for (col = 0; col < COLS; ++col) {
                board[row][col] = '.';
            }
        }

        do {
            curPlayer = 1 - curPlayer;
            drawBoard(board);
            // ask input
            System.out.format("Input player %c row: ", players[curPlayer]);
            row = input.nextInt();
            System.out.format("Input player %c col: ", players[curPlayer]);
            col = input.nextInt();
            board[row][col] = players[curPlayer];
            gameOver = win(board);
        } while (!gameOver);
        if (gameOver) {
            System.out.format("Player %c won!", players[curPlayer]);
        }

        input.close();
    }
}
