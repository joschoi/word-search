package com.personal.joschoi.wordsearch;

import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WordSearchActivity extends AppCompatActivity {

	WordSearchViewModel wordSearchViewModel;
	WordSearchService wordSearchService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_word_search);

		wordSearchViewModel = new WordSearchViewModel();

		wordSearchService = new WordSearchService(this, WordSearchService.S3_DOMAIN);

		wordSearchService.getWordSearchTextFile()
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(wordSearchViewModel.getWordOfTheDayObserver());

	}
}
