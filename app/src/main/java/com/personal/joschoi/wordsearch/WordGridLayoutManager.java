package com.personal.joschoi.wordsearch;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by joschoi on 2/19/17.
 */

public class WordGridLayoutManager extends GridLayoutManager {

	public int start;
	public int end;

	public WordGridLayoutManager(Context context, int spanCount, int start, int end) {
		super(context, spanCount);
		this.start = start;
		this.end = end;
	}

//	@Override
//	public void prepareForDrop(View view,
//		View target,
//		int x,
//		int y) {
//		super.prepareForDrop(view, target, x, y);
//	}

}
