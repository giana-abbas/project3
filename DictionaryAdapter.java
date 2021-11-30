package com.example.project3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class DictionaryAdapter extends  RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder> {
    private LayoutInflater mInflater;
    private LinkedList<String> mWord;
    private LinkedList<String> mDefinition;
    private Context context;

    //the constructor can take any parameters we need
    public DictionaryAdapter(Context context,
                         LinkedList<String> word,
                         LinkedList<String> definition) {
        //use this to create the layout
        mInflater = LayoutInflater.from(context);
        mWord = word;
        mDefinition = definition;
        this.context = context;
    }

    @NonNull
    @Override
    public DictionaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.dictionary_item, parent, false);
        return new DictionaryViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryViewHolder holder, int position) {
        String mCurrent = mWord.get(position);
        holder.mDictionaryWordView.setText(mCurrent);
        String mCurrentDesc = mDefinition.get(position);
        holder.mDictionaryDefView.setText(mCurrentDesc);
    }

    @Override
    public int getItemCount() {
        return mWord.size();
    }

    //The RecyclerView.ViewHolder class must be an inner class
    //of the adapter class.
    //DictionaryViewHolder is the Java class that represents the dictionary_item.xml layout

    //class DictionaryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    class DictionaryViewHolder extends RecyclerView.ViewHolder {
        //instantiate any views used in the item layout here
        private TextView mDictionaryWordView;
        private TextView mDictionaryDefView;
        private DictionaryAdapter adapter;

        public DictionaryViewHolder(View itemView, DictionaryAdapter adapter) {
            super(itemView);
            mDictionaryWordView = itemView.findViewById(R.id.word);
            mDictionaryDefView = itemView.findViewById(R.id.definition);
            this.adapter = adapter;
            //itemView.setOnClickListener(this);
        }

        //unsure if onClick functionality will be necessary; corresponding code commented out
        /*
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DictionaryDetailActivity.class);
            context.startActivity(intent);
        }*/
    }
}
