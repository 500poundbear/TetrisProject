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
  
  public int minimumColumnHeight() {
    int minHeight = top[0];
    for(int c = 1; c < cols; c++) {
      minHeight = Math.min(minHeight, top[c]);
    }
    
    minHeight += 1; // To get an output [0, State.ROWS - 1]
    
    if (VERBOSE) {
      System.out.printf("minimum column height: %d\n", minHeight);
    }
    return minHeight; 
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

  /*
   * Returns the absolute difference between the highest and lowest column
   */
  public int rangeColumnHeight() {
    int maxHeight = -1;
    int minHeight = Integer.MAX_VALUE;
    
    for(int c = 0; c < cols; c++) {
      maxHeight = Math.max(maxHeight, top[c]);
      minHeight = Math.min(minHeight, top[c]);
    }
    
    int absoluteDifference = Math.abs(maxHeight - minHeight);
    if (VERBOSE) {
      System.out.printf("abs(highest - lowest): %d\n", absoluteDifference);
    }
    return absoluteDifference;
  }

  public int cumulativeColumnHeight() {
    int heightSum = 0;
    for(int c = 0; c < cols; c++) {
      heightSum = Math.max(heightSum, top[c]);
    }
    
    if (VERBOSE) {
      System.out.printf("total column height: %d\n", heightSum);
    }
    return heightSum;
  }
  
  public int rowsCleared() {
    int rowsCleared = 0;
    boolean rowFull;
    for(int r = 0; r < rows; r++) {
      rowFull = true;
      for(int c = 0; c < cols; c++) {
        if (field[r][c] == 0) {
          rowFull = false;
          break;
        }
      }
      if (rowFull) rowsCleared++;
    }
    
    if (VERBOSE) {
      System.out.printf("total rows cleared: %d\n", rowsCleared);
    }
    return rowsCleared;
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
