package com.personal.joschoi.wordsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.ResponseBody;
import rx.Observer;

public class WordSearchViewModel {

	public ArrayList<WordSearchInfo> words = new ArrayList<WordSearchInfo>();

	public Observer<ResponseBody> getWordOfTheDayObserver() {
		return new Observer<ResponseBody>() {
			@Override
			public void onCompleted() {
			}

			@Override
			public void onError(Throwable e) {
			}

			@Override
			public void onNext(ResponseBody responseBody) {
				BufferedReader reader = new BufferedReader(responseBody.charStream());

				try {
					String str = reader.readLine();

					while (str != null) {
						str = reader.readLine();
						words.add(getWordInfo(str));
					}

					reader.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
	}

	private WordSearchInfo getWordInfo(String str) {
		WordSearchInfo target = new WordSearchInfo();
		try {
			JSONObject word = new JSONObject(str);
			Iterator<?> keys = word.keys();
			while( keys.hasNext() ) {
				String key = (String)keys.next();

				if (key.equals("source_language")) {
					target.setSource_language(word.get(key).toString());
				}

				else if (key.equals("word")) {
					target.setWord(word.get(key).toString());
				}

				else if (key.equals("character_grid")) {
					ArrayList<ArrayList<String>> charGrid = new ArrayList<ArrayList<String>>();
					JSONArray arr = word.getJSONArray("character_grid");
					for(int i=0; i<arr.length(); i++){
						ArrayList<String> childCharGrid = new ArrayList<String>();
						JSONArray childArr = (JSONArray) arr.get(0);
						if (childArr != null) {
							int len = childArr .length();
							for (int j=0;j<len;j++){
								childCharGrid.add(childArr.get(j).toString());
							}
						}
						charGrid.add(childCharGrid);
					}
					target.setCharacter_grid(charGrid);
				}

				else if (key.equals("word_locations")) {
					Map<String, String> map = new Gson()
						.fromJson(word.get(key).toString(),
							new TypeToken<HashMap<String, String>>() {}.getType());
					target.setWord_locations(map);
				}

				else if (key.equals("target_language")) {
					target.setTarget_language(word.get(key).toString());
				}
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return target;
	}

}
