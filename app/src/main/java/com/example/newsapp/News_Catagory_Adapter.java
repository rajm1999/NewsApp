package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Locale;

public class News_Catagory_Adapter extends RecyclerView.Adapter<News_Catagory_Adapter.ViewHolder> {
    private ArrayList<CatagoryRVModal> catagoryRVModals;
    private Context context;
    private CatagoryClickInterface catagoryClickInterface;

    public News_Catagory_Adapter(ArrayList<CatagoryRVModal> catagoryRVModals,Context context, CatagoryClickInterface catagoryClickInterface){
        this.catagoryRVModals=catagoryRVModals;
        this.context=context;
        this.catagoryClickInterface=catagoryClickInterface;
    }
    @Override
    public News_Catagory_Adapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catagories_rv_item,parent,false);
        return new News_Catagory_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  News_Catagory_Adapter.ViewHolder holder, int position) {
    CatagoryRVModal catagoryRVModal = catagoryRVModals.get(position);
    holder.catagoryTV.setText(catagoryRVModal.getCatagory());
    Glide.with(context).load(catagoryRVModal.getCatagoryImageUrl()).into(holder.catagoryIV);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            catagoryClickInterface.onCatagoryClicked(position);
        }
    });
    }

    @Override
    public int getItemCount() {
        return catagoryRVModals.size();
    }
    public interface CatagoryClickInterface {
        void onCatagoryClicked(int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView catagoryTV;
        public ImageView catagoryIV;
            public ViewHolder(@NonNull View itemView){
                super(itemView);
                catagoryTV = itemView.findViewById(R.id.idTVCatagory);
                catagoryIV = itemView.findViewById(R.id.idIVCatagory);
            }
    }
}
