
public class Main
{

    public static final int SIZE = 9;

    public static void main(String[] args)
    {

        int[][] board = new int[][]{
                {0, 0, 5, 0, 0, 3, 0, 4, 0},
                {0, 0, 7, 0, 0, 9, 1, 2, 0},
                {0, 2, 0, 0, 0, 9, 0, 0, 0},
                {0, 5, 0, 8, 1, 6, 0, 0, 3},
                {4, 0, 0, 3, 0, 7, 0, 0, 6},
                {1, 0, 0, 4, 9, 2, 0, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 3, 0},
                {0, 3, 4, 5, 0, 0, 8, 0, 0},
                {0, 8, 0, 2, 0, 0, 9, 0, 0}
        };

        long timeBefore = System.nanoTime();
        boolean solved = solve(board);
        long elapsedTime = System.nanoTime() - timeBefore;

        System.out.println("Execution time: " + elapsedTime + " nanoseconds");

        if (!solved)
        {
            System.out.println("The board has no solution :(");
        }
    }

    public static boolean solve(int[][] board)
    {
        // start checking top left cell
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                // if cell is already filled, check next one
                if (board[i][j] != 0)
                {
                    continue;
                }

                // try to fill each cell with value between 1 and 9
                for (int k = 1; k <= 9; k++)
                {
                    if (insertNumberInCell(board, i, j, k))
                    {
                        // set number to cell
                        board[i][j] = k;

                        // check if board is correct after setting number in previous line
                        // Here we do the Backtracking
                        if (solve(board))
                        {
                            return true;
                        }

                        // previous number was not correct, 0 is set again and we try next number
                        board[i][j] = 0;
                    }
                }

                return false;
            }
        }

        System.out.println("A solution was found! :)\n");
        print(board);

        return true;
    }

    public static boolean insertNumberInCell(int[][] board, int row, int column, int value)
    {

        // Look for duplicates in row and column
        for (int i = 0; i < SIZE; i++)
        {
            if ((i != row && board[i][column] == value) || (i != column && board[row][i] == value))
            {
                return false;
            }
        }

        int y = (row / 3) * 3;
        int x = (column / 3) * 3;

        // Look for duplicates in current square (3x3)
        for (int i = 0; i < SIZE / 3; i++)
        {
            for (int j = 0; j < SIZE / 3; j++)
            {
                if (i != row && j != column && board[y + i][x + j] == value)
                {
                    return false;
                }
            }
        }

        // Number can be inserted in that cell
        return true;
    }

    public static void print(int[][] board)
    {
        for (int i = 0; i < SIZE; i++)
        {
            if (i % 3 == 0)
            {
                System.out.println(" -------------------------");
            }
            for (int j = 0; j < SIZE; j++)
            {
                if (j % 3 == 0)
                {
                    System.out.print(" |");
                }
                System.out.print(" " + board[i][j]);
            }
            System.out.print(" |");
            System.out.println();
        }
        System.out.println(" -------------------------");
        System.out.println();
    }

}
