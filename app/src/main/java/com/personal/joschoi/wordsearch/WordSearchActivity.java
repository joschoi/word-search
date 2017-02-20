package com.personal.joschoi.wordsearch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WordSearchActivity extends AppCompatActivity {

	public final static String WORDINDEX = "wordCount";

	WordSearchViewModel wordSearchViewModel;
	WordSearchService wordSearchService;

	public static Intent createIntent(Context context, int wordIndex) {
		Intent intent = new Intent();
		intent.setClass(context, WordSearchActivity.class);
		intent.putExtra(WORDINDEX, wordIndex);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_word_search);

		wordSearchViewModel = new WordSearchViewModel(this, getCurrentWordIndex());
		wordSearchViewModel.bind(this.findViewById(R.id.activity_word_search));

		wordSearchService = new WordSearchService(this, WordSearchService.S3_DOMAIN);
		wordSearchService.getWordSearchTextFile()
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(wordSearchViewModel.getWordsFromTxtFileObserver());
	}

	public int getCurrentWordIndex() {
		return getIntent().getIntExtra(WORDINDEX, 0);
	}
}
