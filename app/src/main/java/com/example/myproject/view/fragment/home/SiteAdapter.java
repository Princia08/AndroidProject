package com.example.myproject.view.fragment.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.example.myproject.model.Site;

import java.util.List;

public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.SiteViewHolder>{


    Context context;
    List<Site> items;

    public SiteAdapter(Context context, List<Site> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public SiteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SiteViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_item_site,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SiteViewHolder holder, int position) {
        holder.name.setText(items.get(position).getLabel());
        holder.description.setText(items.get(position).getDescription());
//        holder.imageView.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class SiteViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,description;
        public SiteViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.site_imageview);
            name = itemView.findViewById(R.id.site_name);
            description = itemView.findViewById(R.id.site_description);
        }
    }
}
