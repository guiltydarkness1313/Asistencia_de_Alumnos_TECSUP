package com.shibuyaxpress.asistenciadealumnostecsup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * Created by paulf on 7/5/2017.
 */

public class Asistencia_fragment extends Fragment {
    Button btnEscanear;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_asistencia, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //titulos para el action bar
        getActivity().setTitle("Asistencia");
        btnEscanear=(Button)view.findViewById(R.id.btnEscanear);



        btnEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"funciona XD",Toast.LENGTH_SHORT).show();
                Intent launcher=new Intent(getActivity(),decodificadorQR.class);
                startActivity(launcher);
            }
        });

    }


}
