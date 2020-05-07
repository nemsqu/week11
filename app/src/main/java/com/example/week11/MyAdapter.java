package com.example.week11;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private OnEventListener globalOnEventListener;
    private ArrayList<String> languages;

    public MyAdapter(ArrayList<String> languages, OnEventListener onEventListener) {
        this.languages = languages;
        globalOnEventListener = onEventListener;
    }

    //sets click listeners
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        OnEventListener onEventListener;
        public MyViewHolder(TextView v, OnEventListener onEventListener) {
            super(v);
            textView = v;
            this.onEventListener = onEventListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onEventListener.onEventClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v, globalOnEventListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String lang = (String) languages.get(position);
        holder.textView.setText(lang);
    }


    @Override
    public int getItemCount() {
        return languages.size();
    }

        public interface OnEventListener{
            void onEventClick(int position);

        }

    }

