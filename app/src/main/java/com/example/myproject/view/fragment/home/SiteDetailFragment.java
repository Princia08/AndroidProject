package com.example.myproject.view.fragment.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.R;
import com.example.myproject.model.Site;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SiteDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SiteDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SiteDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SiteDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SiteDetailFragment newInstance(String param1, String param2) {
        SiteDetailFragment fragment = new SiteDetailFragment();
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
        Log.d("creeeeeeeeeeeeeeeeeeeeeeeeeeeataaaaaaaaaaaaaaaaaaaaaaaaaaateeeeeeeeee ","click");
//        Bundle args = getArguments();
//        if (args != null) {
//            Site selectedSite = (Site) args.getSerializable("selectedSite");
//            Log.d("value of siiitite **************:",selectedSite.getLabel());
//        }
        return inflater.inflate(R.layout.fragment_site_detail, container, false);
    }
}