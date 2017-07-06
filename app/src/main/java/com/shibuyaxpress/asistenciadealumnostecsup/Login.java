package com.shibuyaxpress.asistenciadealumnostecsup;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.shibuyaxpress.asistenciadealumnostecsup.Clases.Alumno;

public class Login extends AppCompatActivity {

    Button btnlog;
    EditText correotxt, passwordtxt;

    //creacion de cuenta de firebase
    private FirebaseAuth autoridad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //incio de variables
        btnlog=(Button)findViewById(R.id.btnLogin);
        correotxt=(EditText)findViewById(R.id.txtcorreo);
        passwordtxt=(EditText)findViewById(R.id.txtpassword);

        //incio de variables firebase
        autoridad=FirebaseAuth.getInstance();

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.CAMERA},1);
        }
        //función del boton logearse
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email= correotxt.getText().toString();
                final String contra=passwordtxt.getText().toString();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Ingresa el correo",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(contra)){
                    Toast.makeText(getApplicationContext(),"Ingrese su contraseña",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(email.contains("@tecsup.edu.pe")!=true){
                    correotxt.setError("el correo debe pertenecer a TECSUP");
                }else{
                    autoridad.signInWithEmailAndPassword(email,contra).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                if(email.contains("@tecsup.edu.pe")){
                                    correotxt.setError("el correo debe pertenecer a TECSUP");
                                }else{
                                    Toast.makeText(Login.this,"error al ingresar la contraseña",Toast.LENGTH_SHORT).show();
                                }
                                //hubo un error
                                if(contra.length()<6){
                                    passwordtxt.setError("La contraseña debe ser mayor a 6 caracteres");
                                }else{
                                    Toast.makeText(Login.this,"fallo al identificarse",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Intent launcher=new Intent(getApplicationContext(),menuHome.class);
                                startActivity(launcher);
                                finish();
                            }
                        }
                    });
                }

            }
        });



    }
}
