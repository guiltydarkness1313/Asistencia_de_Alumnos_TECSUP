package com.shibuyaxpress.asistenciadealumnostecsup;

import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shibuyaxpress.asistenciadealumnostecsup.Clases.Asistencia;
import com.shibuyaxpress.asistenciadealumnostecsup.Clases.Horario;

import java.util.Date;
import java.util.Random;

public class decodificadorQR extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener {

    private TextView resultTextView;
    private QRCodeReaderView qrCodeReaderView;
    FirebaseDatabase basedatos;
    DatabaseReference ref;
    Query busqueda;
    Asistencia asis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decodificador_qr);

        basedatos=FirebaseDatabase.getInstance();
        ref=basedatos.getReference("Horario");


        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setOnQRCodeReadListener(this);

        // Use this function to enable/disable decoding
        qrCodeReaderView.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(2000L);

        // Use this function to enable/disable Torch
        //qrCodeReaderView.setTorchEnabled(true);

        // Use this function to set front camera preview
        //qrCodeReaderView.setFrontCamera();

        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
        qrCodeReaderView.setQRDecodingEnabled(false);
        busqueda=ref.orderByChild("id").equalTo(text);
        final String claseid=text;

        busqueda.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Horario horas = dataSnapshot.getValue(Horario.class);

                asis=new Asistencia();

                //Toast.makeText(getApplicationContext(),horas.getHorario_clase(),Toast.LENGTH_SHORT).show();

                String horact=horas.getHorario_clase();
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

                Date horaClases = null;
                try {
                    horaClases = dateFormat.parse(horact);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Date horaNOW = null;
                try {
                    horaNOW = dateFormat.parse(dateFormat.format(new Date()));
                    //Toast.makeText(getApplicationContext(),String.valueOf(horaNOW),Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (horaNOW.after(horaClases))
                {
                    //System.out.println("timeeee end ");
                    Toast.makeText(getApplicationContext(),"tardanza",Toast.LENGTH_SHORT).show();
                    asis.setEstado("tardanza");
                }else{
                    Toast.makeText(getApplicationContext(),"temprano",Toast.LENGTH_SHORT).show();
                    asis.setEstado("regular");
                }
                asis.setCurso(claseid);
                try {
                    asis.setHora_llegada(String.valueOf(horaNOW));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //creacion random de valores
                Random r=new Random();
                int n=10000;
                n=r.nextInt(n);
                asis.setId(String.valueOf(n));
                FirebaseUser usuario= FirebaseAuth.getInstance().getCurrentUser();
                asis.setAlumno(usuario.getEmail());

                //envio de datos
                DatabaseReference reference;
                reference=FirebaseDatabase.getInstance().getReference();

                reference.child("Asistencia").child(asis.getId()).setValue(asis);

                //int seconds=c.get(Calendar.SECOND);
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
        });




        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        qrCodeReaderView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        qrCodeReaderView.stopCamera();
    }
}
