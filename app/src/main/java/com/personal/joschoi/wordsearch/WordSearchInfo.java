package com.personal.joschoi.wordsearch;

import java.util.ArrayList;
import java.util.Map;

public class WordSearchInfo {

	String source_language;
	String word;
	ArrayList<ArrayList<String>> character_grid;
	Map<String, String> word_locations;
	String target_language;

	public ArrayList<ArrayList<String>> getCharacter_grid() {
		return character_grid;
	}

	public void setCharacter_grid(ArrayList<ArrayList<String>> character_grid) {
		this.character_grid = character_grid;
	}

	public Map<String, String> getWord_locations() {
		return word_locations;
	}

	public void setWord_locations(Map<String, String> word_locations) {
		this.word_locations = word_locations;
	}

	public String getTarget_language() {
		return target_language;
	}

	public void setTarget_language(String target_language) {
		this.target_language = target_language;
	}

	public String getSource_language() {
		return source_language;
	}

	public void setSource_language(String source_language) {
		this.source_language = source_language;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}
