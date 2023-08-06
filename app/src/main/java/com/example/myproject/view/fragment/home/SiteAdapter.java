package com.example.myproject.view.fragment.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.HomeActivity;
import com.example.myproject.R;
import com.example.myproject.model.Site;

import java.io.Serializable;
import java.util.List;

public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.SiteViewHolder> {


    Context context;
    List<Site> items;
    private OnSiteItemClickListener itemClickListener;

    public SiteAdapter(Context context, List<Site> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public SiteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SiteViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_item_site, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SiteViewHolder holder, int position) {
        Site site = items.get(position);

        holder.name.setText(items.get(position).getLabel());
        holder.description.setText(items.get(position).getDescription());

        String imageName = items.get(position).getImage();
        int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        holder.imageView.setImageResource(resourceId);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("itemCLick **************************", "click dehors");
                if (itemClickListener != null) {
                    Log.d("itemCLick **************************", "click interieur");
                    itemClickListener.onSiteItemClick(items.get(position)); // Pass the clicked site
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class SiteViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, description;

        public SiteViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.site_imageview);
            name = itemView.findViewById(R.id.site_name);
            description = itemView.findViewById(R.id.site_description);
        }
    }

    public interface OnSiteItemClickListener {
        void onSiteItemClick(Site site);
    }

    public void setOnSiteItemClickListener(OnSiteItemClickListener listener) {
        itemClickListener = listener;
    }

}
