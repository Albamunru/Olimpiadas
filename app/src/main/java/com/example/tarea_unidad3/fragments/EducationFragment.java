package com.example.tarea_unidad3.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.tarea_unidad3.AgendaPersonal;
import com.example.tarea_unidad3.MainActivityCalendario;
import com.example.tarea_unidad3.R;

import java.util.Arrays;
import java.util.List;


public class EducationFragment extends Fragment {


    private Toolbar tb;
    Spinner spinner;
    List<String> opciones;
    private ImageButton botonInfo;

    public EducationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        crearSpinner();

        botonInfo= view.findViewById(R.id.imageButtonInfo);


        botonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
                String mensaje= "Esta aplicación ha sido diseñada y creada por las alumnas de 2º Curso de DAM del IES Arroyo Harnina (Almendralejo):<br> <br>" +
                        "Elena Zambrano Zambrano<br>" +
                        "Inma Pérez Sánchez<br>" +
                        "Alba Muñoz Rueda";

            builder.setMessage(Html.fromHtml(mensaje)).setPositiveButton("Atrás",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.create().show();


            }
        });



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.education_fragment, container, false);
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
                Intent laucnhRayuela = getContext().getPackageManager().getLaunchIntentForPackage(rayuela);
                if(laucnhRayuela != null)
                    startActivity(laucnhRayuela);
            }
        }else if (seleccionado.equals("Encuesta profesorado")){
            Uri uri = Uri.parse("https://www.iesarroyoharnina.es/encprofesorado/");
            Intent intentoEncuesta = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intentoEncuesta);
        }else if (seleccionado.equals("Agenda personal")){
            Intent pasardatosAgenda = new Intent(getContext(), MainActivityCalendario.class);
            startActivity(pasardatosAgenda);
        }

    }

    private boolean isAppInstalled(String rute) {
        PackageManager pm = getContext().getPackageManager();
        try{
            pm.getPackageInfo(rute, PackageManager.GET_ACTIVITIES);
            return true;
        }catch (PackageManager.NameNotFoundException e){
            Log.i("rayuela", rute+" no instalada");
            return false;
        }
    }
    public void crearSpinner(){

        spinner = (Spinner) getView().findViewById(R.id.spinnerEducation);

        String [] op = {"Seleccione opcion: ", "Rayuela", "Classroom", "Encuesta profesorado","Agenda personal"};
        opciones = Arrays.asList(op);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item_cambio, opciones);
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