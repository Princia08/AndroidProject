<<<<<<<< Updated upstream:app/src/main/java/com/example/myproject/view/fragment/notification/NotificationFragment.java
package com.example.myproject.view.fragment.notification;
========
package com.example.myproject.vieww.fragmentt;
>>>>>>>> Stashed changes:app/src/main/java/com/example/myproject/vieww/fragmentt/NotificationFragment.java

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myproject.HomeActivity;
import com.example.myproject.R;
import com.example.myproject.controller.EventController;
import com.example.myproject.controller.EventSiteCallback;
import com.example.myproject.model.Event;
import com.example.myproject.view.fragment.home.SiteAdapter;

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
        initEventTomorrow();

        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new NotifAdapter(requireContext(), events));

        return view;
    }

    public void initEventTomorrow() {
        eventController.getEventTomorrow(new EventSiteCallback() {
            @Override
            public void onSuccess(List<Event> fecthedEvents) {
                for (Event event : fecthedEvents) {
                    Event event1 = new Event(
                            event.getLabel(),
                            event.getDescription(),
                            event.getDate_event()
                    );
                    events.add(event1);
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateAdapter();
                    }
                });
            }

            @Override
            public void onFailure(Throwable error) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void updateAdapter() {
        if (getActivity() != null) {
            NotifAdapter adapter = new NotifAdapter(requireContext(),events);
            recyclerView.setAdapter(adapter);
        }
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