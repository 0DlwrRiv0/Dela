package com.example.dlwr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    Button guardar, inicio;
    EditText name, usuarioo, contraseñaa, eemail, ttelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        name = findViewById(R.id.NombreID);
        usuarioo = findViewById(R.id.UsuarioID);
        contraseñaa = findViewById(R.id.ContraseñaID);
        eemail = findViewById(R.id.EmailID);
        ttelefono = findViewById(R.id.PhoneID);

        guardar = findViewById(R.id.GuardarID);
        inicio = findViewById(R.id.InicioSesionID);
        guardar.setOnClickListener(this);
        inicio.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String cadenita=((Button) v).getText().toString();
        if(cadenita.equals("Guardar datos")){
            Base admin = new Base(this, "administracion", null, 1);
            SQLiteDatabase baseDatos= admin.getWritableDatabase();

            String nombre = name.getText().toString();
            String usuario = usuarioo.getText().toString();
            String contraseña = contraseñaa.getText().toString();
            String email = eemail.getText().toString();
            String telefono = ttelefono.getText().toString();

            if(!nombre.isEmpty() && !usuario.isEmpty() && !contraseña.isEmpty() && !email.isEmpty()
            && !telefono.isEmpty()){
                ContentValues registro = new ContentValues();
                registro.put("nombre", nombre);
                registro.put("usuario", usuario);
                registro.put("contraseña", contraseña);
                registro.put("email", email);
                registro.put("telefono", telefono);

                baseDatos.insert("usuarios", null, registro);
                baseDatos.close();

                name.setText("");
                usuarioo.setText("");
                contraseñaa.setText("");
                eemail.setText("");
                ttelefono.setText("");

                Toast.makeText(this, "registrado", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            }
        }
        if(cadenita.equals("Inicia sesión")){
            Intent intento =new Intent(this, Login.class);
            startActivity(intento);
        }
    }
}