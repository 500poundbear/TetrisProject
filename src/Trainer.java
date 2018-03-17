/*
 * Trainer is the entry point to all the various parts of this tetris AI.
 */

import java.lang.Math.*;
import java.util.ArrayList;
import java.util.Collections;

import Heuristics.Score;

public class Trainer{
  public static Boolean DISPLAY_GRAPHIC = false;
  public static int DISPLAY_GRAPHIC_NEW_PIECE_PERIOD = 300;

  public static void main(String[] args) {
    Trainer x = new Trainer();
    double[] dd = new double[4];
    dd[0] = 1;
    dd[1] = 1;
    dd[2] = 1;

    x.runGame(dd);
  }

  /*
   * Run a complete game and return the highest score
   * A list of heuristic weights
   */
  private long runGame(double[] weights) {
    State s = new State();

    if (DISPLAY_GRAPHIC) new TFrame(s);

    while(!s.hasLost()) {

      int[][] field = s.getField();
      int nextPiece = s.getNextPiece();
      int[][] legalMoves = s.legalMoves();

      // Generate score for each possibility
      double[] scores = new double[5];/*calculateMoveScores(field, nextPiece, legalMoves,
                                            weights);*/

      scores[0] = calculateMoveScore(field, nextPiece, legalMoves[0], weights);
      System.out.printf("scores[0] = %f\n", scores[0]);
      s.makeMove(movePicker(scores));

      if (DISPLAY_GRAPHIC) {
        s.draw();
        s.drawNext(0,0);

        try {
          Thread.sleep(DISPLAY_GRAPHIC_NEW_PIECE_PERIOD);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    return s.getRowsCleared();
  }

  /*
   * Returns the highest score given a list of heuristic scores
   */
  private int movePicker(double[] scores) {
    double max = scores[0];
    int maxInd = 0;
    for(int i = 1; i < scores.length; i++){
        if(scores[i] > max){
            max = scores[i];
            maxInd = i;
        }
    }
    return maxInd;
  }

  /*
   * Calculate scores based on applying each move permutation to the field
   */
  private double[] calculateMoveScores(int[][] field, int piece,
                                       int[][] moves, double weights) {
    double[] results = new double[moves.length];

    return results;
  }

  /*
   * Calculate score based on applying a move
   */
  private double calculateMoveScore(int[][] field, int piece, int[] move,
                                    double[] weights) {
    int[][] newField = addToField(field, piece, move);

    Score moveScore = new Score(newField, weights);
    return moveScore.getScore();
  }

  /*
   * Add piece to field
   */
  private int[][] addToField(int[][] field, int piece, int[] move) {
    int moveOrient = move[State.ORIENT];
    int moveSlot = move[State.SLOT];

    printBoard(field);
    return field;
  }


  private void printBoard(int[][] field) {
    System.out.println("");
    for(int q = State.ROWS - 1; q >= 0; q--) {
      for(int w = 0; w < State.COLS; w++) {
        System.out.printf("%d ", field[q][w]);
      }
      System.out.printf("\n");
    }
    System.out.println("===================");
  }

}
