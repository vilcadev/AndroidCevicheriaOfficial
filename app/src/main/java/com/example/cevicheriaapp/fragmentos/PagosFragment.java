package com.example.cevicheriaapp.fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cevicheriaapp.R;
import com.example.cevicheriaapp.clases.Mesa;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PagosFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class PagosFragment extends Fragment {

    private Mesa mesa;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PagosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PagosFragment newInstance(String param1, String param2) {
        PagosFragment fragment = new PagosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PagosFragment() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pagos, container, false);

        // Obtener el objeto Mesa desde el Bundle
        if (getArguments() != null) {
            String mesaJson = getArguments().getString("mesa");
            String totalGeneral = getArguments().getString("totalGeneral");

            Gson gson = new Gson();
            mesa = gson.fromJson(mesaJson, Mesa.class);

            // Aquí puedes utilizar los datos de 'mesa', por ejemplo mostrar el nombre de la mesa
            TextView mesaTextView = view.findViewById(R.id.nombreMesaTextView);
            mesaTextView.setText(mesa.getNombreMesa());

            TextView totalId = view.findViewById(R.id.totalId);
            totalId.setText(totalGeneral);

            Log.d("ProductosSeleccionados", "Longitud del array: " + totalId);
        }

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Asignar evento click al botón btnSave
        Button productosButton = view.findViewById(R.id.btnProductos);
        productosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navegarOrder(v);
            }
        });

    }


    public void navegarOrder(View view){

        Gson gson = new Gson();
        String mesaJson = gson.toJson(mesa);

        // Enviar el JSON al PagosFragment a través de un Bundle
        Bundle bundle = new Bundle();
        bundle.putString("mesa", mesaJson);


        OrderFragment orderFragment = new OrderFragment();

        orderFragment.setArguments(bundle);


        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, orderFragment)
                .addToBackStack(null)
                .commit();
    }
}