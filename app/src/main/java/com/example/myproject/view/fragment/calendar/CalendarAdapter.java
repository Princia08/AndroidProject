package com.example.myproject.view.fragment.calendar;

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

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {
    Context context;
    List<Event> items;

    public CalendarAdapter(Context context, List<Event> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CalendarAdapter.CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CalendarAdapter.CalendarViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_item_event,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.CalendarViewHolder holder, int position) {
        holder.titre.setText(items.get(position).getLabel());
        holder.description.setText(items.get(position).getDescription());
        holder.date_event.setText(items.get(position).getDate_event());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class CalendarViewHolder extends RecyclerView.ViewHolder{
        TextView  titre, description, date_event;
        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            titre =  itemView.findViewById(R.id.event_titre);
            description =  itemView.findViewById(R.id.event_description);
            date_event =  itemView.findViewById(R.id.event_date);
        }
    }
}
