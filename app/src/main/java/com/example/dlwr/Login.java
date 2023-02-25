package com.example.dlwr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText usuar, name;
    TextView nam;
    Button resgistro, ingreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.NombreID);
        usuar = findViewById(R.id.UsuarioID);

        ingreso = findViewById(R.id.IngresaID);
        resgistro = findViewById(R.id.IniciaID);
        ingreso.setOnClickListener(this);
        resgistro.setOnClickListener(this);



    }
    @Override
    public void onClick(View v) {
        String cadenita=((Button) v).getText().toString();
        if(cadenita.equals("Registrate")){
            Intent intentito = new Intent(this, Registro.class);
            startActivity(intentito);
        }

        if(cadenita.equals("Ingresa")){
            Base admin = new Base(this, "administracion", null, 1);
            SQLiteDatabase baseDatos = admin.getWritableDatabase();

            String nombre = name.getText().toString();
            String usuario = usuar.getText().toString();

            if(!nombre.isEmpty() && !usuario.isEmpty()){
                Cursor fila = baseDatos.rawQuery("select nombre from usuarios where usuario ="+usuario, null);

                if(fila.moveToFirst()){
                      Intent intento1 = new Intent(this, Pagina.class);
                      startActivity(intento1);
                  }
                }else{
                    Toast.makeText(this, "El registro no existe", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
