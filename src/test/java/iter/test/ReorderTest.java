package iter.test;

public class ReorderTest {

  volatile static int x = 0;
  volatile static int y = 0;
  volatile static int a = 0;
  volatile static int b = 0;

  public static void main(String[] args) {
    for (int i = 0; i < 100000; i++) {
      Thread one = new Thread(new Runnable() {
        public void run() {
          a = 1;
          x = b;
        }
      });
      Thread other = new Thread(new Runnable() {
        public void run() {
          b = 1;
          y = a;
        }
      });

      x = 0;
      y = 0;
      a = 0;
      b = 0;

      one.start();
      other.start();
      try {
        one.join();
        other.join();
      } catch (Exception e) {
        System.out.println("exception");
      }
      if ((x == 0) && (y == 0)) {
        System.out.println("pass");
      }
    }
    System.out.println("end");
  }
}
