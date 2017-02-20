package com.personal.joschoi.wordsearch;

public interface ItemTouchHelperAdapter {

	void onItemMove(int fromPosition, int toPosition);

	void onItemDismiss(int position);
}