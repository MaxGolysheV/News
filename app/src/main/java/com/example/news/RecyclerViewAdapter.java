package com.example.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<Model> news;

    public RecyclerViewAdapter(Context context, List<Model> news )
    {
        this.news = news;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Model model = news.get(position);
        holder.RVtitle.setText(model.getTitle());
        holder.RVtext.setText(model.getText());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView RVtitle;
        final TextView RVtext;

        ViewHolder(View view)
        {
            super(view);
            RVtitle = view.findViewById(R.id.tv_title);
            RVtext = view.findViewById(R.id.tv_text);
        }
    }
}
