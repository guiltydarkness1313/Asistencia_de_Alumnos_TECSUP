package com.shibuyaxpress.asistenciadealumnostecsup.Adaptador;

import android.content.Context;
import android.support.annotation.LayoutRes;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.ObservableSnapshotArray;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.shibuyaxpress.asistenciadealumnostecsup.Clases.Asistencia;
import com.shibuyaxpress.asistenciadealumnostecsup.Holder.AsistenciaHolder;

/**
 * Created by paulf on 7/6/2017.
 */

public class AsistenciaAdaptador extends FirebaseRecyclerAdapter<Asistencia,AsistenciaHolder> {
    private Context context;
    public AsistenciaAdaptador(Class<Asistencia>modelClass, int modelLayout, Class<AsistenciaHolder>viewHolderClass, DatabaseReference buscar, Context context){
        super(modelClass,modelLayout,viewHolderClass,buscar);
        this.context=context;

    }

    @Override
    protected void populateViewHolder(AsistenciaHolder viewHolder, Asistencia model, int position) {
        viewHolder.Horallegada.setText(model.getHora_llegada());
        viewHolder.Estado.setText(model.getEstado());
        viewHolder.Fechallegada.setText(model.getFecha());
        viewHolder.nombreCurso.setText(model.getCurso());
    }
}
