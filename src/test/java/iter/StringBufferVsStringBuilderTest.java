package iter;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringBufferVsStringBuilderTest {

  @Test
  public void testPerformance() throws Exception {
    StringBuffer stf = new StringBuffer();
    StringBuilder stb = new StringBuilder();
    int times = 10_000_000;
    long tick0 = System.currentTimeMillis();
    for (int i = 0; i < times; i++) {
      stf.append(i);
    }
    long tick1 = System.currentTimeMillis();
    for (int i = 0; i < times; i++) {
      stb.append(i);
    }
    long tick2 = System.currentTimeMillis();
    System.out.println(tick1 - tick0);
    System.out.println(tick2 - tick1);
    assertTrue((tick1 - tick0) > (tick2 - tick1));
  }
}
