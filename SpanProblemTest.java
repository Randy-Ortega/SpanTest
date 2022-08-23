import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SpanProblemTest {

@Test /* Test to verify competitor Evaluator method with different Score*/
    public void competitorEvaluatorTestDifferentScore(){
    List<String> competitions = new ArrayList<>();
    competitions.add("Lions");
    competitions.add("Grouches");
    List<Integer> results  = new ArrayList<>();
    results.add(1);
    results.add(2);

    assertInstanceOf(HashMap.class, SpanProblem.competitorEvaluator(competitions, results));

}
    @Test /* Test to verify competitor Evaluator method with equal Score*/
    public void competitorEvaluatorTestSameScore(){
        List<String> competitions = new ArrayList<>();
        competitions.add("Lions");
        competitions.add("Snakes");
        List<Integer> results  = new ArrayList<>();
        results.add(2);
        results.add(2);

        assertInstanceOf(HashMap.class, SpanProblem.competitorEvaluator(competitions, results));

    }
}