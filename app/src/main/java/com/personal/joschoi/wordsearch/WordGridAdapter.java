package com.personal.joschoi.wordsearch;

import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WordGridAdapter extends RecyclerView.Adapter<WordGridAdapter.WordGridViewHolder> {

	Context context;
	ArrayList<ArrayList<String>> charGrid;

	public int colIndex = 0;
	public int rowIndex = 0;
	public int numRow;
	public int numCol;

	public int start;
	public int end;
	public int wordIndex;

	public WordGridAdapter(Context context, ArrayList<ArrayList<String>> charGrid, int start, int end, int wordIndex) {
		this.context = context;
		this.charGrid = charGrid;
		this.numRow = charGrid.size();
		this.numCol = charGrid.get(0).size();
		this.start = start;
		this.end = end;
		this.wordIndex = wordIndex;
	}

	@Override
	public WordGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent, false);
		return new WordGridAdapter.WordGridViewHolder(v);
	}

	@Override
	public void onBindViewHolder(WordGridViewHolder holder, int position) {
		if (position != 0 && position % numCol != 0) {
			colIndex++;
		}
		if (position != 0 && position % numRow == 0 ){
			colIndex = 0;
			rowIndex++;
		}

		holder.setLetter(charGrid.get(colIndex).get(rowIndex));
	}

	@Override
	public int getItemCount() {
		return numRow*numCol;
	}

	public void onItemMove(int fromPosition, int toPosition) {
		if (fromPosition == start && toPosition == end) {
			Toast.makeText(context, context.getString(R.string.correct), Toast.LENGTH_SHORT).show();
			return;
		}
	}

	class WordGridViewHolder extends RecyclerView.ViewHolder {

		@Bind(R.id.letter) TextView letter;

		public WordGridViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

		public void setLetter(String letter) {
			this.letter.setText(letter);
		}
	}
}
