package com.personal.joschoi.wordsearch;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

/**
 * Created by joschoi on 2/19/17.
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

	private final WordGridAdapter mAdapter;
	public SimpleItemTouchHelperCallback(WordGridAdapter adapter) {
		mAdapter = adapter;
	}

	@Override
	public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder current,
		RecyclerView.ViewHolder target) {
		return (target.getAdapterPosition() == mAdapter.end);
	}

	@Override
	public boolean isLongPressDragEnabled() {
		return true;
	}

	@Override
	public boolean isItemViewSwipeEnabled() {
		return false;
	}

	@Override
	public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
	}

	@Override
	public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
		int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
		int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
		return makeMovementFlags(dragFlags, swipeFlags);
	}

	@Override
	public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
		RecyclerView.ViewHolder target) {
		mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
		return true;
	}
}