package com.personal.joschoi.wordsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

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
				Gson gson = new Gson();

				try {
					String str = reader.readLine();

					while (str != null) {
						str = reader.readLine();
						WordSearchInfo info = gson.fromJson(str, WordSearchInfo.class);
						words.add(info);
					}

					reader.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
	}
}
