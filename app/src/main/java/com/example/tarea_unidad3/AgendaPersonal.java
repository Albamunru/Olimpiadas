package com.example.tarea_unidad3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AgendaPersonal extends AppCompatActivity {
    private Toolbar tb;
    private EditText agenda;
    private Button guardar;
    private Button recuperar;
    private EditText fechaE;

    //para la lista
    private List<String> fileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_personal);


        tb = findViewById(R.id.toolbarAgenda);

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pasardatosEducationA = new Intent(getApplicationContext(), MainActivityEducation.class);
                startActivity(pasardatosEducationA);
                finish();
            }
        });

        guardar = findViewById(R.id.btnguardar);
        recuperar = findViewById(R.id.btnrecuperar);
        fechaE = findViewById(R.id.etFecha);
        agenda = findViewById(R.id.eTAgenda);
        //para la lista
        fileList = new ArrayList<>();

        for (File file : getFilesDir().listFiles()) {
            fileList.add(file.getName());
        }

        guardar.setOnClickListener(view -> {


            try {
                // Reemplazar caracteres especiales en la fecha
                String sinCaracteres = fechaE.getText().toString().replace('/', '_');

                // Construir el nombre del archivo con la fecha
                String fileName =  sinCaracteres + ".txt";

                OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(fileName, Context.MODE_PRIVATE));
                archivo.write(agenda.getText().toString());
                archivo.flush(); //borra el buffer para que quede vacio
                archivo.close();

                // Agregar el nombre del archivo a la lista
                fileList.add(fileName);

            }catch (Throwable t){
                Toast.makeText(getApplicationContext(), "Exception: " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        recuperar.setOnClickListener(view -> {



            try {
                // Recuperar datos de todos los archivos en la lista
                StringBuilder buf = new StringBuilder();

                for (String fileName : fileList) {
                    String sinCaracteres = fechaE.getText().toString().replace('/', '_');
                    String recuperado =  sinCaracteres + ".txt";
                    if (recuperado.equals(fileName)) {
                        InputStream in = openFileInput(fileName);
                        if (in != null) {
                            InputStreamReader tmp = new InputStreamReader(in);
                            BufferedReader reader = new BufferedReader(tmp);
                            String str;
                            while ((str = reader.readLine()) != null) {
                                buf.append(str).append("\n");
                                in.close();
                                agenda = findViewById(R.id.eTAgenda);
                                agenda.setText(buf.toString());
                            }
                    }

                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }

        });
    }
}