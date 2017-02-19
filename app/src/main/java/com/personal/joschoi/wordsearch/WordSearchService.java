package com.personal.joschoi.wordsearch;

import android.content.Context;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class WordSearchService {
	public static final String S3_DOMAIN = "https://s3.amazonaws.com/";

	private WordSearchApi wordSearchApi;

	public WordSearchService(Context context, String endpoint) {
		Retrofit restAdapter = new Retrofit.Builder()
			.baseUrl(endpoint)
			.client(ClientFactory.createClient(context))
			.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
			.addConverterFactory(GsonConverterFactory.create())
			.build();

		wordSearchApi = restAdapter.create(WordSearchApi.class);
	}

	public Observable<ResponseBody> getWordSearchTextFile() {
		return wordSearchApi.getWordSearchTextFile();
	}
}