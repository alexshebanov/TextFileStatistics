import com.statistics.txt.entity.TextFileStatistics;
import com.statistics.txt.processing.LineHandler;
import com.statistics.txt.processing.TxtFileHandler;
import com.statistics.txt.processing.TxtFileTextExtractor;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileTest {
    TxtFileHandler txtFileHandler = new TxtFileHandler(new TxtFileTextExtractor()
    , new LineHandler());

    @Test
    public void simpleFileTesting() throws IOException {
        File file = new File("simply_file.txt");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("One two three four \n" +
                "five six seven \n" +
                "eight nine ten \n");
        fileWriter.close();
        TextFileStatistics statistics = txtFileHandler.textFileStatistics(file);
        assert statistics.getLineStatistics().size() == 3;
        assert statistics.getName().equals("simply_file.txt");
        assert statistics.getLineStatistics().get(1).getLongestWord().equals("seven");
    }
}
