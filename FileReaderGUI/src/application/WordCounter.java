package application;
/** 
	Write a text analyzer that reads a file and outputs statistics about that file. 
 	Output: Word frequencies sorted greatest to least. Output will be pairs. EX) text : 4
 	Display top 20 words 
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javafx.scene.control.ListView;
/**
 * 
 * @author Cesar
 *
 */
public class WordCounter {

/**
 *  Create ListView for words
 */
	public ListView<String> wordsListView;
/**
 *  Create ListView for frequencies
 */
	public ListView<Integer> frequencyListView;
	
/**
 * 
 * @param f a file parameter
 * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
 */
	public void WordCount(File f) throws IOException {
		
		/**
			Hash Map used to store words as it's keys and frequencies as it's values. Keys will be stored as Strings, and Values stored as Integers
		*/
		Map<String, Integer> wordCount = new HashMap<>();
	
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(f))) {
			String currentLine;
			/**
			   While the reader's current line is not null, create an array of words using split() to get rid of regular expressions i.e. !@#$%^&*()_+
			*/
			while ((currentLine = bufferedReader.readLine()) != null) {

				String[] words = currentLine.split("[\\s.;,+:—?!()\"'—]", -5);
				/**
				   For every word in the "words" array, trim spaces and input into hash map "wordCounts"
				*/
				for (String word : words) {
					/**
					   Using trim() to eliminate lead and trail space. Java does this by checking for the Unicode value of a space which is \u0020
					*/
					word = word.trim();

					if (word.length() > 0) {
						/**
						   if the word is already in the hash map wordCounts add 1 else put 1.
						*/
						if (wordCount.containsKey(word)) {
							wordCount.put(word, wordCount.get(word) + 1);
						} else {
							wordCount.put(word, 1);
						}
					}
				}
			}

			/**
			   Get the set of entries in wordCounts and get the stream of entries using entrySet() and stream().
			   Sort the entries by comparingByValue() which is ascending order. To get descending order use reverseOrder().
			   then collecting and returning a new LinkedHashMap. 
			*/
			Map<String, Integer> sortedWordCount = wordCount.entrySet().stream()
					.sorted(Collections.reverseOrder(Entry.comparingByValue()))
					.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
			
			List<String> keys = new ArrayList<>(sortedWordCount.keySet());
			List<Integer> values = new ArrayList<>(sortedWordCount.values());
			
			//Add top 20 words and their frequencies to the list views
			wordsListView.getItems().addAll(keys.subList(0, Math.min(20, keys.size())));
			frequencyListView.getItems().addAll(values.subList(0, Math.min(20, values.size())));
			
			System.out.println(keys);
		    System.out.println(values);
		    bufferedReader.close();
		}
		
		

		
	}

}
