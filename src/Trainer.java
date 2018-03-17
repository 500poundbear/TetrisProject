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
      System.out.printf("CURRENT BOARD: \n");
      printBoard(field);
      
      // Generate score for each possibility
      double[] scores = new double[5];/*calculateMoveScores(field, nextPiece, legalMoves,
                                            weights);*/

      scores[0] = calculateMoveScore(s, nextPiece, legalMoves[0], weights);
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
  private double[] calculateMoveScores(State s, int piece,
                                       int[][] moves, double weights) {
    double[] results = new double[moves.length];

    return results;
  }

  /*
   * Calculate score based on applying a move
   */
  private double calculateMoveScore(State s, int piece, int[] move,
                                    double[] weights) {
    int rowToAddPiece = findRowForPiece(s, piece, move);
    
    if (rowToAddPiece == -1) {
      return Integer.MIN_VALUE;
    }
                                   
    int[][] newField = addToField(s, piece, move, rowToAddPiece);
    printBoard(newField);
    
    Score moveScore = new Score(newField, weights);
    return moveScore.getScore();
  }
  
  /*
   * Find the row to add piece to field
   */
  
  private int findRowForPiece(State s, int piece, int[] move) {
    int moveOrient = move[State.ORIENT];
    int moveSlot = move[State.SLOT];
    
    int[] topColumns = s.getTop();
    int pieceWidth = s.getpWidth()[piece][moveOrient];
    int pieceHeight = s.getpHeight()[piece][moveOrient];
    
    int minColumnHeight = Integer.MAX_VALUE;
    // Find the lowest column height within the width span
    // The lowest possible spot for this piece is lower bounded by the above value.
    for (int q = moveSlot; q < (moveSlot + pieceWidth); q++) {
       minColumnHeight = Math.min(minColumnHeight, topColumns[q]);
    }
    
    // Check, for any of the tile's potential spot, if it is already occupied
    
    int[] pBottom = s.getpBottom()[piece][moveOrient];
    int[] pTop = s.getpTop()[piece][moveOrient];
    int[][] field = s.getField();
    
    boolean collision = true;
    
    while(minColumnHeight + pieceHeight < State.ROWS) {
      collision = false;
    
      for(int pw = 0; pw < pieceWidth; pw++) {
        for(int pr = pBottom[pw]; pr < pTop[pw]; pr++) {
  
          // Check field position
          if (field[minColumnHeight + pr][moveSlot + pw] != 0) {
            collision = true;
          }
        }
      }
      
      if (!collision) break;
      
      // If there are collisions, raise minimum and check again
      minColumnHeight++;
    }
    
    return collision ? -1 : minColumnHeight;
  }


  /*
   * Add piece to field
   */
  private int[][] addToField(State s, int piece, int[] move, int rowToAddPiece) {
    int moveOrient = move[State.ORIENT];
    int moveSlot = move[State.SLOT];
    
    int pieceWidth = s.getpWidth()[piece][moveOrient];
    int[] pBottom = s.getpBottom()[piece][moveOrient];
    int[] pTop = s.getpTop()[piece][moveOrient];
    
    int[][] oldField = s.getField();
    int[][] newField = oldField.clone();
    
    int turnNumber = s.getTurnNumber() + 1;
    for(int pw = 0; pw < pieceWidth; pw++) {
      for(int pr = pBottom[pw]; pr < pTop[pw]; pr++) {
        if (rowToAddPiece + pr >= State.ROWS) break;
        newField[rowToAddPiece + pr][moveSlot + pw] = turnNumber;
      }
    }
    
    return newField;
  }


  private void printBoard(int[][] field) {
    System.out.println("");
    for(int q = State.ROWS - 2; q >= 0; q--) {
      for(int w = 0; w < State.COLS; w++) {
        System.out.printf("%d ", field[q][w]);
      }
      System.out.printf("\n");
    }
    System.out.println("===================");
  }

}
