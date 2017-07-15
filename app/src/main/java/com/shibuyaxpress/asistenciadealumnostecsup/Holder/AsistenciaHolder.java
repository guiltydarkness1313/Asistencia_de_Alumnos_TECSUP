package com.shibuyaxpress.asistenciadealumnostecsup.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shibuyaxpress.asistenciadealumnostecsup.R;

import org.w3c.dom.Text;

/**
 * Created by paulf on 7/6/2017.
 */

public class AsistenciaHolder extends RecyclerView.ViewHolder {

    public TextView nombreCurso,Estado,Horallegada,Fechallegada;


    public AsistenciaHolder(View itemView) {
        super(itemView);

        nombreCurso=(TextView)itemView.findViewById(R.id.nombre_curso_txt);
        //Fechallegada=(TextView)itemView.findViewById(R.id.fecha_registro_txt);
        Estado=(TextView)itemView.findViewById(R.id.estado_txt);
        Horallegada=(TextView)itemView.findViewById(R.id.hora_llegada_txt);




    }
}
