package com.personal.joschoi.wordsearch;

import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by joschoi on 2/13/17.
 */

public class ClientFactory {

	@NonNull
	public static OkHttpClient createClient(@NonNull Context context) {

		okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();

		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);
		builder.addInterceptor(logging);

		builder.readTimeout(5, TimeUnit.SECONDS);
		builder.connectTimeout(5, TimeUnit.SECONDS);

		return builder.build();
	}
}
