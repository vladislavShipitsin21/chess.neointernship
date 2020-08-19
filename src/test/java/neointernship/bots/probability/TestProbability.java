package neointernship.bots.probability;

import org.junit.Test;

import java.util.Random;

import static junit.framework.TestCase.assertEquals;

public class TestProbability {
    @Test
    public void testUniform() {
        final int size = 10;
        final double expected = 0.1;

        final double actual = Probability.uniform.apply(size);

        assertEquals(expected, actual);
    }
    @Test
    public void test(){
        final Random random = new Random();
        System.out.println(random.nextInt(100));
        System.out.println(random.nextInt(100));
    }
}
