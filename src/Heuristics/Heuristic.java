package Heuristics;

public class Heuristic {
  private static boolean VERBOSE = true;
  
  private int[][] field;
  private int[] top;
  private int rows;
  private int cols;

  public Heuristic(int[][] field) {
    this.field = field;
    rows = field.length;
    cols = field[0].length;
    
    top = new int[cols];
    
    for(int c = 0; c < cols; c++) {
      top[c] = columnHeight(c);
    }
  }

  public int maximumColumnHeight() {
    int maxHeight = -1;
    for(int c = 0; c < cols; c++) {
      maxHeight = Math.max(maxHeight, top[c]);
    }
    
    if (VERBOSE) {
      System.out.printf("maximum column height: %d\n", maxHeight);
    }
    return maxHeight;
  }

  public int cumulColumnHeightDiff() {
    int diffHeight = 0;
    for(int c = 1; c < cols; c++) {
      diffHeight += Math.abs(top[c] - top[c - 1]);
    }
    
    if (VERBOSE) {
      System.out.printf("culm sum of column difference: %d\n", diffHeight);
    }
    return diffHeight;
  }

  /*
   * Returns the number of 0 fields that are not in the top column
   */
  public int numberofHoles() {
    int numHoles = 0;
    for(int c = 0; c < cols; c++) {
      for(int r = top[c] - 1; r >=0; r--) {
        if (field[r][c] == 0) numHoles++;
      }
    }
    
    if (VERBOSE) {
      System.out.printf("number of holes: %d\n", numHoles);
    }
    
    return numHoles;
  }

  private int columnHeight(int c) {
    int r = -1;
    for(r = rows - 1; r >=0; r--) {
      if (field[r][c] != 0) {
        break;
      }
    }
    return r;
  }
}
