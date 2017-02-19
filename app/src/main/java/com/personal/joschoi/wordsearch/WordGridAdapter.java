package com.personal.joschoi.wordsearch;

import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Instead of setting a cell try to set a entire row
 */

public class WordGridAdapter extends RecyclerView.Adapter<WordGridAdapter.WordGridViewHolder> {

	Context context;
	ArrayList<ArrayList<String>> charGrid;

	public int colNumber = 0;

	public WordGridAdapter(Context context, ArrayList<ArrayList<String>> charGrid) {
		this.context = context;
		this.charGrid = charGrid;
	}

	@Override
	public WordGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent, false);
		return new WordGridAdapter.WordGridViewHolder(v);
	}

	@Override
	public void onBindViewHolder(WordGridViewHolder holder, int position) {
		if (position!=0 && ((position % 8)==0)) colNumber++;
		holder.setLetter(charGrid.get(colNumber).get(position%8));
	}

	@Override
	public int getItemCount() {
		return 64;
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
