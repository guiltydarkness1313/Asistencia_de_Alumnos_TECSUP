package com.shibuyaxpress.asistenciadealumnostecsup.Adaptador;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.ObservableSnapshotArray;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.shibuyaxpress.asistenciadealumnostecsup.Clases.Asistencia;
import com.shibuyaxpress.asistenciadealumnostecsup.Clases.Curso;
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
    protected void populateViewHolder(final AsistenciaHolder viewHolder, Asistencia model, int position) {
        viewHolder.Horallegada.setText(model.getHora_llegada());
        viewHolder.Estado.setText(model.getEstado());
        //viewHolder.Fechallegada.setText(model.getFecha());
        viewHolder.nombreCurso.setText(model.getCurso());


        /*FirebaseDatabase basedatos= FirebaseDatabase.getInstance();
        DatabaseReference ref=basedatos.getReference("Curso");
        Query busqueda=ref.orderByChild("id").equalTo(model.getCurso());
        busqueda.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Curso cursos=dataSnapshot.getValue(Curso.class);
                //viewHolder.nombreCurso.setText(cursos.getNombre());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }
}
