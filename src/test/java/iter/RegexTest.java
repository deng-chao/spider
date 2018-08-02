package iter;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {

    @Test
    public void testMatch() throws Exception {
        Pattern p = Pattern.compile("/20140411/7859\\d{2}_{0,1}\\d{0,1}\\.html");
        Matcher match = p.matcher("/20140411/785915.html");
        System.out.println(match.find());
    }
}
