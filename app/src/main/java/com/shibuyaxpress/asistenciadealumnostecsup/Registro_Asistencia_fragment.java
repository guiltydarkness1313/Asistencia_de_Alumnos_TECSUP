package com.shibuyaxpress.asistenciadealumnostecsup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shibuyaxpress.asistenciadealumnostecsup.Adaptador.AsistenciaAdaptador;
import com.shibuyaxpress.asistenciadealumnostecsup.Clases.Asistencia;
import com.shibuyaxpress.asistenciadealumnostecsup.Holder.AsistenciaHolder;

import java.util.List;

/**
 * Created by paulf on 7/5/2017.
 */

public class Registro_Asistencia_fragment extends Fragment {
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView reciclador;
    private AsistenciaAdaptador asistenciaAdaptador;
    private DatabaseReference BDreference;
    private DatabaseReference refChild;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        return inflater.inflate(R.layout.fragment_registro_asistencia, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Registro de Asistencia");

       linearLayoutManager=new LinearLayoutManager(getActivity());
        reciclador=(RecyclerView)view.findViewById(R.id.reciclador_registro);
        reciclador.setHasFixedSize(true);

        BDreference=FirebaseDatabase.getInstance().getReference("Asistencia");

        asistenciaAdaptador=new AsistenciaAdaptador(Asistencia.class,R.layout.carta, AsistenciaHolder.class,BDreference,getContext());

        reciclador.setLayoutManager(linearLayoutManager);
        reciclador.setAdapter(asistenciaAdaptador);

    }
}
