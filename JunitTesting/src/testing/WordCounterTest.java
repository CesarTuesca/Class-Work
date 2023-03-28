package testing;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;

public class WordCounterTest {

    @Test
    public void testWordCount() throws IOException {
        WordCounter wordCounter = new WordCounter();

        // create temporary file
        File file = File.createTempFile("testfile", ".txt");
        file.deleteOnExit();

        // write text to file
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("hello world hello");
            writer.println("world hello");
            writer.println("hello world");
        }

        // call WordCount method on file
        wordCounter.WordCount(file);

        // check list views contain expected values
        assertEquals("hello", wordCounter.wordsListView.getItems().get(0));
        assertEquals("world", wordCounter.wordsListView.getItems().get(1));
        assertEquals((Integer)4, wordCounter.frequencyListView.getItems().get(0));
        assertEquals((Integer)3, wordCounter.frequencyListView.getItems().get(1));
    }
}