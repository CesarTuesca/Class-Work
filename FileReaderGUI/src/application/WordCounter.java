package application;
/** 
	Write a text analyzer that reads a file and outputs statistics about that file. 
 	Output: Word frequencies sorted greatest to least. Output will be pairs. EX) text : 4
 	Display top 20 words 
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WordCounter {
	
	public void WordCount(String[] args) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\application\\gutenberg.txt"));
		
		/**
			Hash Map used to store words as it's keys and frequencies as it's values. Keys will be stored as Strings, and Values stored as Integers
		*/
		Map<String, Integer> wordCount = new HashMap<>();

		String currentLine;
		/**
		   While the reader's current line is not null, create an array of words using split() to get rid of regular expressions i.e. !@#$%^&*()_+
		*/
		while ((currentLine = bufferedReader.readLine()) != null) {

			String[] words = currentLine.split("[\\s.;”“,+:—?!()\"'—]", -5);
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

		/**
		   Print out the Hash Map using printf(format, object) %-18s (column left aligned with 18 trailing spaces) %15s\n (column with 15 leading spaces new line)
		
		System.out.printf("%-18s%15s\n", "     Word", "Frequency");

		System.out.printf("%-20s%15s\n", "╰──── ✩ ────╯", "╰──── ✩ ────╯");
		*/
		
		for (Map.Entry<String, Integer> entry : sortedWordCount.entrySet()) {
			
			/**
			System.out.printf("%-20s%10s\n", "     " + entry.getKey(), entry.getValue());
			*/
			
		}

		bufferedReader.close();
	}

}
