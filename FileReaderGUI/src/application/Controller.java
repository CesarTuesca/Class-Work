package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
/**
 * 
 * @author Cesar
 *
 */
public class Controller implements Initializable {
	/**
	 *  Observable List for keys "words"
	 */
	public ObservableList<String> keys = FXCollections.observableArrayList();
	/**
	 *  Observable List for values "frequencies"
	 */
	public ObservableList<Integer> values = FXCollections.observableArrayList();
	@FXML
	/**
	 *  Create ListView for words
	 */
	public ListView<String> wordsListView;
	/**
	 *  Create ListView for frequencies
	 */
	public ListView<Integer> frequencyListView;
	/**
	 *  Create label to display the file address to ensure the right file was selected
	 */
	public Label labSingleFile;
	/**
	 *  Create button to clear the ListViews to enable the user to read in another file
	 */
	public Button clearButton;
	/**
	 *  Create button to open directory for file choosing
	 */
	public Button selectFileButton;
	
	
	@Override
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		wordsListView.getItems().addAll(keys);
		frequencyListView.getItems().addAll(values);
		clearButton.setOnAction(this::handleClearButton);
		
	}	
	
	@FXML
	/**
	 * @param e
	 * @throws IOException
	 */
	public void textSelect(ActionEvent e) throws IOException {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		File f = fc.showOpenDialog(null);
		
		if (f != null) {
			labSingleFile.setText("Top 20 Words in Selected File: " + f.getAbsolutePath());
			BufferedReader bufferedReader = new BufferedReader(new FileReader(f.getAbsolutePath()));
			
			/**
				Hash Map used to store words as it's keys and frequencies as it's values. Keys will be stored as Strings, and Values stored as Integers
			*/
			Map<String, Integer> wordCount = new HashMap<>();

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

			/**
			   Print out the Hash Map using printf(format, object) %-18s (column left aligned with 18 trailing spaces) %15s\n (column with 15 leading spaces new line)
			
			System.out.printf("%-18s%15s\n", "     Word", "Frequency");

			System.out.printf("%-20s%15s\n", "╰──── ✩ ────╯", "╰──── ✩ ────╯");
			*/
			
			for (Map.Entry<String, Integer> entry : sortedWordCount.entrySet()) {
				
			
				//System.out.printf("%-20s%10s\n", "     " + entry.getKey(), entry.getValue());
				
				keys.add(entry.getKey());
				values.add(entry.getValue());
				
				
	
				
				
			}
			
			wordsListView.getItems().addAll(keys.subList(0, Math.min(20, keys.size())));
			frequencyListView.getItems().addAll(values.subList(0, Math.min(20, values.size())));
			
			System.out.println(keys);
			System.out.println(values);
			bufferedReader.close();
		}

		}
	
	@FXML
	/**
	 * 
	 * @param event for clearing the label, hashmap, and listviews
	 */
	public void handleClearButton(ActionEvent event) {
		keys.clear();
		values.clear();
		wordsListView.getItems().clear();
		frequencyListView.getItems().clear();
		labSingleFile.setText("");
	}
			
		
		
	

	

}

