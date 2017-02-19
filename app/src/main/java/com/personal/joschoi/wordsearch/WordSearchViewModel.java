package com.personal.joschoi.wordsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Observer;

public class WordSearchViewModel {

	public ArrayList<WordSearchInfo> wordsFromTxtFile = new ArrayList<WordSearchInfo>();

	@Bind(R.id.word_search_grid) RecyclerView wordGrid;
	@Bind(R.id.test_word) TextView testWord;
	@Bind(R.id.lang) TextView lang;
	@Bind(R.id.next_word) Button nextWordBtn;

	public int wordIndex;
	public Context context;

	public WordSearchViewModel(Context context, int wordIndex) {
		this.context = context;
		this.wordIndex = wordIndex;
	}

	public void bind(View view) {
		ButterKnife.bind(this, view);
	}

	private WordSearchInfo getCurrentWordInfo() {
		return wordsFromTxtFile.get(wordIndex);
	}

	public void setUpWordGrid() {
		GridLayoutManager layoutManager = new GridLayoutManager(context, getCurrentWordInfo().getCharacter_grid().size());
		wordGrid.setLayoutManager(layoutManager);
		wordGrid.setHasFixedSize(true);
		wordGrid.setAdapter(new WordGridAdapter(context, getCurrentWordInfo().getCharacter_grid()));
	}

	public void setTestWordInfo() {
		testWord.setText(context.getString(R.string.word, getCurrentWordInfo().getWord()));
		lang.setText(context.getString(R.string.lang, getCurrentWordInfo().getTarget_language()));
	}

	@OnClick(R.id.next_word)
	public void onClickNextWordBtn(View view) {
		view.getContext().startActivity(WordSearchActivity.createIntent(context, ++wordIndex));
	}


	//////////////////////
	// DATA-ORIENTED LOGIC

	public Observer<ResponseBody> getWordsFromTxtFileObserver() {
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
				wordsFromTxtFile = extractWordSearchInfo(reader);
				setUpWordGrid();
				setTestWordInfo();
			}
		};
	}

	// TODO: UNIT TEST
	public ArrayList<WordSearchInfo> extractWordSearchInfo(BufferedReader reader) {
		ArrayList<WordSearchInfo> listToReturn = new ArrayList<WordSearchInfo>();
		Gson gson = new Gson();

		try {
			String str = reader.readLine();

			while (str != null) {
				WordSearchInfo info = gson.fromJson(str, WordSearchInfo.class);
				listToReturn.add(info);
				str = reader.readLine();
			}

			reader.close();

		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return listToReturn;
	}
}
