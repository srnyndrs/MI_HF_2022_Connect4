public class StudentPlayer extends Player {
    static int maxInARow(Board board, int playerIndex) {
        int sum, empty, max = 0;
        for(int col = 0; col < 4; col++) {
            for(int row = 0; row < 6; row++){
                sum = 0;
                empty = 0;
                for(int window = 0; window < 4; window++) {
                    if(board.getState()[row][col + window] == playerIndex) {
                        sum++;
                    } else if(board.getState()[row][col + window] == 0) {
                        empty++;
                    } else {
                        sum = 0;
                        break;
                    }
                }
                if(max < sum && (empty + sum) > 3) {
                    max = sum;
                }
            }
        }
        return (int) Math.pow(10, max);
    }
    static int maxInACol(Board board, int playerIndex) {
        int sum, empty, max = 0;
        for(int col = 0; col < 7; col++) {
            for(int row = 0; row < 3; row++) {
                sum = 0;
                empty = 0;
                for(int window = 0; window < 4; window++) {
                    if(board.getState()[row + window][col] == playerIndex) {
                        sum++;
                    } else if(board.getState()[row + window][col] == 0)  {
                        empty++;
                    } else {
                        sum = 0;
                        break;
                    }
                }
                if(max < sum && (empty + sum) > 3) {
                    max = sum;
                }
            }
        }
        return (int) Math.pow(10, max);
    }
    static int maxInADiagonal(Board board, int playerIndex) {
        int sum, empty, max = 0;
        for(int col = 0; col < 4; col++) {
            for(int row = 0; row < 3; row++) {
                sum = 0;
                empty = 0;
                for(int window = 0; window < 4; window++) {
                    if(board.getState()[row + window][col + window] == playerIndex) {
                        sum++;
                    } else if(board.getState()[row + window][col + window] == 0) {
                        empty++;
                    } else  {
                        sum = 0;
                        break;
                    }
                }
                if(max < sum && (sum + empty) > 3) {
                    max = sum;
                }
            }
        }
        return (int) Math.pow(8, max);
    }
    static int maxInAnAntiDiagonal(Board board, int playerIndex) {
        int sum, empty, max = 0;
        for(int col = 0; col < 4; col++) {
            for(int row = 3; row < 6; row++) {
                sum = 0;
                empty = 0;
                for(int window = 0; window < 4; window++) {
                    if(board.getState()[row - window][col + window] == playerIndex) {
                        sum++;
                    } else if(board.getState()[row - window][col + window] == 0) {
                        empty++;
                    } else  {
                        sum = 0;
                        break;
                    }
                }
                if(max < sum && (sum + empty) > 3) {
                    max = sum;
                }
            }
        }
        return (int) Math.pow(7, max);
    }
    static int calculateAvail(Board board) {
        int max = maxInARow(board, 2) + maxInACol(board, 2) + maxInADiagonal(board,2) + maxInAnAntiDiagonal(board,2);
        int min = maxInARow(board, 1) + maxInACol(board, 1) + maxInADiagonal(board,1) + maxInAnAntiDiagonal(board,1);
        return max - min;
    }
    static int[] transposition = {0,0};
    static int[] minimax(Board board, int depth, int alpha, int beta, boolean maximizingPlayer) {
        if(depth == 0 || board.gameEnded()) {
            return new int[] { -1, calculateAvail(board)};
        }
        if(maximizingPlayer) {
            int maxEval = -10000;
            int maxCol = board.getValidSteps().get(0);
            for(int i : board.getValidSteps()) {
                Board temp = new Board(board);
                temp.step(2, i);
                transposition = minimax(temp, depth - 1, alpha, beta, false);
                if(maxEval < transposition[1]) {
                    maxEval = transposition[1];
                    maxCol = i;
                }
                alpha = Math.max(alpha, transposition[1]);
                if(beta <= alpha) {
                    break;
                }
            }
            return new int[] { maxCol, maxEval };
        } else {
            int minEval = 10000;
            int minCol = board.getValidSteps().get(0);
            for(int i : board.getValidSteps()) {
                Board temp = new Board(board);
                temp.step(1, i);
                transposition = minimax(temp, depth - 1, alpha, beta, true);
                if(minEval > transposition[1]) {
                    minEval = transposition[1];
                    minCol = i;
                }
                beta = Math.min(beta, transposition[1]);
                if(beta <= alpha) {
                    break;
                }
            }
            return new int[] { minCol, minEval };
        }
    }
    public StudentPlayer(int playerIndex, int[] boardSize, int nToConnect) {
        super(playerIndex, boardSize, nToConnect);
    }
    @Override
    public int step(Board board) {
        transposition = new int[]{0, 0};
        int[] score = minimax(board,6,-10000,10000,true);
        return score[0];
    }
}
