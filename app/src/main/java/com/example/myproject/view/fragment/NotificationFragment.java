package com.example.myproject.view.fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myproject.HomeActivity;
import com.example.myproject.MainActivity;
import com.example.myproject.R;
import com.example.myproject.controller.EventController;
import com.example.myproject.controller.EventSiteCallback;
import com.example.myproject.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button notifyBtn;
    EventController eventController = new EventController();
    List<Event> events = new ArrayList<>();
    RecyclerView recyclerView;

    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
//        initEventTomorrow();
        Event event1 = new Event();

        event1.setLabel("Festival des Lémuriens");
        event1.setDescription("Un festival célébrant la diversité des lémuriens malgaches et la préservation de leur habitat naturel.");
        event1.setDate_event("2023-08-06");
        events.add(event1);

        Event event2 = new Event();
        event2.setLabel("Festival de la Vanille");
        event2.setDescription("Un événement dédié à la célèbre vanille de Madagascar, mettant en avant la culture, la cuisine et les utilisations de la vanille.");
        event2.setDate_event("2023-08-07");
        events.add(event2);

        recyclerView = view.findViewById(R.id.recyclerview_notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new NotifAdapter(requireContext(), events));
        return view;
    }

    public void initEventTomorrow() {
        eventController.getEventTomorrow(new EventSiteCallback() {
            @Override
            public void onSuccess(List<Event> events) {
                for (Event event : events) {
                    Event event1 = new Event(
                            event.getLabel(),
                            event.getDescription(),
                            event.getDate_event()
                    );
                    events.add(event1);
                }
            }

            @Override
            public void onFailure(Throwable error) {

            }
        });
    }

    public void pushNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = requireContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "My Notification");
        builder.setContentTitle("Mada Travel");
        builder.setContentText("De ahona zanjy");
        builder.setSmallIcon(R.drawable.baseline_event_available_24);
        builder.setAutoCancel(true);

        NotificationManagerCompat notificationCompat = NotificationManagerCompat.from(getContext());
        notificationCompat.notify(1, builder.build());
    }

}