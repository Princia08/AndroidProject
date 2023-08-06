package com.example.myproject.view.fragment.calendar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.controller.EventController;
import com.example.myproject.controller.EventSiteCallback;
import com.example.myproject.model.Event;
import com.example.myproject.view.fragment.home.SiteAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EventController eventController = new EventController();
    List<Event> events = new ArrayList<>();
    RecyclerView recyclerView;

    public CalendarFragment() {
    }

    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
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
        initAllCalendar();
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_calendrier);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new CalendarAdapter(requireContext(), events));
        return view;
    }

    public void initAllCalendar() {
        eventController.getAll(new EventSiteCallback() {
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
            CalendarAdapter adapter = new CalendarAdapter(requireContext(), events);
            recyclerView.setAdapter(adapter);
        }
    }
}