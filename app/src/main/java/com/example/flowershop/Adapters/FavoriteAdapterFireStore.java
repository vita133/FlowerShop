package com.example.flowershop.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flowershop.Product;
import com.example.flowershop.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class FavoriteAdapterFireStore extends FirestoreRecyclerAdapter<Product, FavoriteAdapterFireStore.FavoriteListHolder> {

    private Context context;

    public FavoriteAdapterFireStore(@NonNull FirestoreRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FavoriteListHolder holder, int position, @NonNull Product model) {
        holder.textViewName.setText(model.getName());
        holder.textViewPrice.setText(model.getPrice().toString());
        Glide.with(context).load(model.getImage()).into(holder.ViewImage);

    }

    @NonNull
    @Override
    public FavoriteListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_row, parent, false);
        context = parent.getContext();
        return new FavoriteListHolder(v);
    }

    class FavoriteListHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewPrice;
        ImageView ViewImage;

        public FavoriteListHolder(View itemView) {
            super(itemView);
            ViewImage = (ImageView) itemView.findViewById(R.id.imageFavoriteText);
            textViewPrice = itemView.findViewById(R.id.priceFavoriteText);
            textViewName = itemView.findViewById(R.id.nameFavoriteText);

        }
    }
}