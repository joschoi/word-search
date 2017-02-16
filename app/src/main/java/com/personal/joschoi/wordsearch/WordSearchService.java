package com.personal.joschoi.wordsearch;

import java.io.IOException;

import android.content.Context;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

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
//		mBookingsService.getBookingsData(lob, pos, startDate, endDate)
//			.subscribeOn(Schedulers.io())
//			.observeOn(AndroidSchedulers.mainThread())
//			.subscribe(getBookingsDataObserver());
//		Call<String> call = wordSearchApi.getWordSearchTxtFile();
//		call.enqueue(new Callback<String>() {
//			@Override
//			public void onResponse(Call<String> call, Response<String> response) {
//				System.out.println("hello it's me");
//			}
//
//			@Override
//			public void onFailure(Call<String> call, Throwable t) {
//				System.out.println("hello");
//			}
//		});
	}
}