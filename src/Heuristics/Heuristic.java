package Heuristics;

public class Heuristic {
  private int[][] field;
  private int rows;
  private int cols;

  public Heuristic(int[][] field) {
    this.field = field;
    rows = field.length;
    cols = field[0].length;
  }

  public int maximumColumnHeight() {
    int maxHeight = -1;
    for(int c = 0; c < cols; c++) {
      int cHeight = columnHeight(c);
      maxHeight = Math.max(maxHeight, cHeight);
    }
    return maxHeight;
  }

  public int cumulColumnHeightDiff() {
    int diffHeight = 0;
    int prevHeight = columnHeight(0);
    int height;

    for(int c = 1; c < cols; c++) {
      height = columnHeight(c);
      diffHeight += height - prevHeight;
      prevHeight = height;
      System.out.printf("prev: %d curr: %d\n", prevHeight, height);
    }
    return diffHeight;
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
