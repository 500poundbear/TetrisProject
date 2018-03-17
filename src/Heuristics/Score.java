/*
 * Heuristics.Score calculates the heuristic score based on the field (2D array)
 * given.
 */
package Heuristics;

public class Score{
  private double score;

  private static final int HEURISTICS_COUNT = 5;
  private static final Boolean VERBOSE = true;

  public Score(int[][] field, double[] weights) {
    
  }

  public double getScore() {
    return score;
  }

  private double calculateV(double[] weights) {
/*
    int[] phi = new int[100];
    int[][] field = s.getField();

    phi[0] = 1;

    int[] top = s.getTop();

    // Maximum column height
    phi[20] = -Integer.MAX_VALUE;

    // Column heights
    for(int q = 1; q <= 10; q++) {
      phi[q] = top[q - 1];
      if (VERBOSE) System.out.println(phi[q]);

      phi[20] = Math.max(phi[20], phi[q]);
    }

    // Difference in column heights
    for(int q = 11; q <= 19; q++) {
      phi[q] = Math.abs(top[q - 11] - top[q - 10]);
      if (VERBOSE) System.out.println(phi[q]);
    }

    if (VERBOSE) System.out.println(phi[20]);

    // Number of holes. Holes are defined as positions that are empty
    phi[21] = 0;
    for(int w = 0; w < 10; w++) {
      if (VERBOSE) System.out.printf("TOP[%d] = %d\n", w, top[w]);
      for(int q = 0; q < top[w] - 1; q++) {
        if(field[q][w] == 0) {
          phi[21]++;
          if (VERBOSE) System.out.printf("Hole in [%d,%d]\n", w, q);
        }
      }
    }

    double v = 0;
    // Multiply weights by heuristics
    for (int q = 0; q < HEURISTICS_COUNT; q++) {
      v += weights[q] * phi[q];
    }
*/
    return 0;
  }
}
