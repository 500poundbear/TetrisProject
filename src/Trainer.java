/*
 * Trainer is the entry point to all the various parts of this tetris AI.
 */
public class Trainer{
  public static Boolean DISPLAY_GRAPHIC = false;
  public static int DISPLAY_GRAPHIC_NEW_PIECE_PERIOD = 300;

  public static void main(String[] args) {

    State s = new State();

    if (DISPLAY_GRAPHIC) new TFrame(s);

    PlayerSkeleton p = new PlayerSkeleton();

    while(!s.hasLost()) {

      s.makeMove(p.pickMove(s,s.legalMoves()));
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
    System.out.println("You have completed "+s.getRowsCleared()+" rows.");
  }
}
