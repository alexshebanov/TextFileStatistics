import com.statistics.txt.entity.LineStatistics;
import com.statistics.txt.processing.LineHandler;
import org.junit.Test;

public class LineTest {
    LineHandler handler = new LineHandler();

    @Test
    public void number() {
        String text = "I have broke up with my ex girl. Here is a number!";
        LineStatistics statistics = handler.result(text);
        assert (statistics.getLongestWord().equals("number"));
        assert (statistics.getShortestWord().equals("I"));
    }

    @Test
    public void punctuation() {
        String text = "  @324?., a safhjk word";
        LineStatistics statistics = handler.result(text);
        assert (statistics.getLongestWord().equals("safhjk"));
        assert (statistics.getShortestWord().equals("a"));
    }

    @Test
    public void empty() {
        LineStatistics statistics = handler.result("");
        assert (statistics.getLength() == 0);
    }
}
