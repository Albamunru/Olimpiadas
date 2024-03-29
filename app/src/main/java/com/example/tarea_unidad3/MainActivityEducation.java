package com.example.tarea_unidad3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

public class MainActivityEducation extends AppCompatActivity {

    private Toolbar tb;
    Spinner spinner;
    List<String> opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_fragment);



    }
    public void mostrar(){
        String seleccionado = spinner.getSelectedItem().toString();

         if (seleccionado.equals("Classroom")){
            Uri uri = Uri.parse("https://classroom.google.com/");
            Intent intentoClass = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intentoClass);
        }else if (seleccionado.equals("Rayuela")){
            String rayuela= "es.educarex.rayuela";
            if(isAppInstalled(rayuela)){
                Intent laucnhRayuela = getPackageManager().getLaunchIntentForPackage(rayuela);
                if(laucnhRayuela != null)
                    startActivity(laucnhRayuela);
            }
        }else if (seleccionado.equals("Encuesta profesorado")){
            Uri uri = Uri.parse("https://www.iesarroyoharnina.es/encprofesorado/");
            Intent intentoEncuesta = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intentoEncuesta);
        }else if (seleccionado.equals("Agenda personal")){
            Intent pasardatosAgenda = new Intent(getApplicationContext(), MainActivityCalendario.class);
            startActivity(pasardatosAgenda);
            finish();
        }

    }
    private boolean isAppInstalled(String rute) {
        PackageManager pm = getPackageManager();
        try{
            pm.getPackageInfo(rute, PackageManager.GET_ACTIVITIES);
            return true;
        }catch (PackageManager.NameNotFoundException e){
            Log.i("rayuela", rute+" no instalada");
            return false;
        }
    }
    public void crearSpinner(){

        spinner = (Spinner) findViewById(R.id.spinnerEducation);

        String [] op = {"Seleccione opcion: ", "Rayuela", "Classroom", "Encuesta profesorado","Agenda personal"};
        opciones = Arrays.asList(op);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_cambio, opciones);
        spinner.setAdapter(adapter);

        // esta linea es la que hace que haya radiobutton en los items del spinner
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mostrar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}