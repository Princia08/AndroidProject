package com.example.myproject.vieww.fragment.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.example.myproject.model.Event;

import java.util.List;

public class NotifAdapter extends RecyclerView.Adapter<NotifAdapter.NotifViewHolder> {

    Context context;
    List<Event> items;

    public NotifAdapter(Context context, List<Event> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public NotifAdapter.NotifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotifViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_item_notif,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotifAdapter.NotifViewHolder holder, int position) {
        holder.titre.setText(items.get(position).getLabel());
        holder.contenu.setText("Nouveau : "+ items.get(position).getLabel()+" prévu le "+items.get(position).getDate_event());
        holder.date_time.setText(items.get(position).getDate_event());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class NotifViewHolder extends RecyclerView.ViewHolder{

        TextView titre,contenu,date_time;
        public NotifViewHolder(@NonNull View itemView) {
            super(itemView);
            titre =  itemView.findViewById(R.id.notification_titre);
            contenu =  itemView.findViewById(R.id.notification_contenu);
            date_time =  itemView.findViewById(R.id.notification_date);
        }
    }
}
