package com.personal.joschoi.wordsearch;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import rx.Observable;

public interface WordSearchApi {

	@Streaming
	@GET("duolingo-data/s3/js2/find_challenges.txt#")
	Observable<ResponseBody> getWordSearchTextFile();

}
