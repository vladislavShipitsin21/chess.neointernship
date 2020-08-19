package neointernship.bots.probability;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestProbability {
    @Test
    public void testUniform(){
        int size = 10;
        double expected = 0.1;

        double actual = Probability.uniform.apply(size);

        assertEquals(expected,actual);
    }
}
