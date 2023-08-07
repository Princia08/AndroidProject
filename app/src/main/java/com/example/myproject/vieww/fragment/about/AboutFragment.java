package com.example.myproject.vieww.fragment.about;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match

    private static final String THEME_PREFS = "themePrefs";
    private static final String THEME_KEY = "themeKey";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Switch switchMode;
    private SharedPreferences sharedPreferences;

    public AboutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(THEME_PREFS, param1);
        args.putString(THEME_KEY, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(THEME_PREFS);
            mParam2 = getArguments().getString(THEME_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);

        TextView htmlTextView = rootView.findViewById(R.id.htmlTextView);
        String htmlContent = "<h3>À Propos de MadagascarTravel</h3><br>" +
                "<i>Copyright © 2023 MadagascarTravel. Tous droits réservés.</i><br>" +
                "<br>" +
                "Bienvenue sur MadagascarTravel, votre compagnon numérique pour découvrir les trésors cachés de Madagascar, la perle de l'Océan Indien. Notre application vous offre une expérience immersive unique, conçue pour les voyageurs avides d'aventures, de culture et de paysages à couper le souffle.<br>" +
                "<br>" +
                "Notre Mission :<br>" +
                "Chez MadagascarTravel, notre mission est de vous guider à travers les merveilles naturelles et culturelles de Madagascar. Nous souhaitons partager notre passion pour cette île enchanteresse en vous proposant des informations fiables et complètes sur les lieux incontournables, les activités inédites et les événements à ne pas manquer.<br>" +
                "<br>" +
                "Caractéristiques Clés :<br>" +
                "<br>" +
                "Exploration Interactive : Plongez-vous dans une exploration interactive des sites touristiques les plus prisés et des joyaux cachés de Madagascar. Notre application vous offre des itinéraires personnalisés, adaptés à vos préférences, afin de vous assurer une expérience enrichissante et inoubliable.<br>" +
                "<br>" +
                "Recommandations Locales : Bénéficiez des recommandations authentiques émanant des habitants de Madagascar eux-mêmes. Découvrez les meilleurs restaurants, les boutiques d'artisanat local, les activités traditionnelles et les événements culturels pour une immersion complète dans la vie malgache.<br>" +
                "<br>" +
                "Remarque sur les droits d'auteur :<br>" +
                "Toutes les images et le contenu utilisés dans cette application sont la propriété de MadagascarTravel ou de tiers ayant accordé les autorisations nécessaires. Toute reproduction, distribution ou utilisation non autorisée du contenu de cette application est strictement interdite.<br>" +
                "<br>" +
                "Rejoignez-nous dans cette aventure exceptionnelle à Madagascar et découvrez la magie de cette île enchanteresse. Téléchargez MadagascarTravel dès aujourd'hui et commencez à planifier votre voyage de rêve !<br>" +
                "<br>" +
                "Pour toute question ou commentaire, veuillez nous contacter à l'adresse suivante : <a style=\"color:blue\"><b>contact@madagascarexplorer.com</b></a><br>" +
                "<br>" +
                "Bon voyage,<br>" +
                "<br>" +
                "L'équipe <b><i>MadagascarTravel</i></b>";
        htmlTextView.setText(Html.fromHtml(htmlContent));

        return rootView;
    }
}