package com.example.myproject.view.fragment.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.controller.SiteController;
import com.example.myproject.controller.SiteListCallback;
import com.example.myproject.model.Site;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    SiteController siteController = new SiteController();
    List<Site> sites = new ArrayList<>();

    RecyclerView recyclerView;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new SiteAdapter(requireContext(), sites));
        initListSite();
        return view;
    }

    public void initListSite() {
        siteController.getSiteList(new SiteListCallback() {
            @Override
            public void onSuccess(List<Site> siteList) {
                for (Site siteModel : siteList) {
                    Site site = new Site(
                            siteModel.getLabel(),
                            siteModel.getDescription(),
                            siteModel.getImage(),
                            siteModel.getVideo()
                    );
                    sites.add(site);
                }
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateAdapter();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Throwable error) {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }

    private void updateAdapter() {
        if (getActivity() != null) {
            SiteAdapter adapter = new SiteAdapter(requireContext(), sites);
            recyclerView.setAdapter(adapter);
        }
    }

    public void testFiltre() {
//        Spinner filterSpinner = view.findViewById(R.id.filter_spinner);
//        String[] filterOptions = {"Option 1", "Option 2", "Option 3"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, filterOptions);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        filterSpinner.setAdapter(adapter);
    }
}