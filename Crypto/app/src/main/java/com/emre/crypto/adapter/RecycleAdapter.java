package com.emre.crypto.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emre.crypto.R;
import com.emre.crypto.model.CryptoModel;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RowHolder>{
     ArrayList<CryptoModel> cryptoModelArrayList;
     public RecycleAdapter(ArrayList<CryptoModel> cryptoModelArrayList){
         this.cryptoModelArrayList=cryptoModelArrayList;
     }
    public RowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.row,parent,false);
        return new RowHolder(view);
    }

    public void onBindViewHolder(RowHolder holder, int position) {
          holder.bind(cryptoModelArrayList.get(position),position);
     }
    public int getItemCount() {
        return cryptoModelArrayList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder{
        TextView nameText;
        TextView priceText;
        ImageView imageView;
         public RowHolder(View itemView) {
            super(itemView);
            nameText=itemView.findViewById(R.id.nameText);
            priceText=itemView.findViewById(R.id.priceText);
            imageView=itemView.findViewById(R.id.imageCoin);
        }
        public void bind(CryptoModel cryptoModel,int position){
            nameText=itemView.findViewById(R.id.nameText);
            priceText=itemView.findViewById(R.id.priceText);
            nameText.setText(cryptoModel.currency);
            priceText.setText(cryptoModel.price+"$");
        }
    }
}
