package eu.zderadicka.spoiler;

import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;
import java.util.stream.IntStream;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class Tests 
{

    private static final Logger LOG= Logger.getLogger(Test.class.getName());

    @Test
    public void neverFails()
    {
        Spoiler s = new NeverFailingSpoiler();
        s.spoil();
    }

    @Test(expected = SpoilerFailure.class)
    public void alwaysEventuallyFails() {
        Spoiler s = new FailingSpoiler(20);
        IntStream.range(0, 1000).forEach(n -> s.spoil());
    }

    private int runToFailure(Spoiler s, int maxNums) {
        for (int i=0; i< maxNums; i++) {
            try {
                s.spoil();
            }
            catch (SpoilerFailure e) {
                return i;
            }
        }
        return maxNums;
    }

    @Test 
    public void meanTime() {
        double mtbf = 100;
        double avg =IntStream.range(0, 1000).map(_n -> {
           
            Spoiler s = new FailingSpoiler(mtbf);
            return runToFailure(s, (int) mtbf * 10);
        }).average().orElse(0);

        assertTrue(String.format("Average is not closed - expected %f, got %s", mtbf, avg), 
            Math.abs(mtbf-avg) < 0.15 * mtbf);
    }

    


}
