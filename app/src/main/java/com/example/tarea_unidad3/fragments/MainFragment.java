package com.example.tarea_unidad3.fragments;

import static android.content.Context.MODE_PRIVATE;



import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarea_unidad3.Datos;
import com.example.tarea_unidad3.Lectura;
import com.example.tarea_unidad3.MainActivityVideo;
import com.example.tarea_unidad3.R;
import com.example.tarea_unidad3.TaskCompleted;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainFragment extends Fragment implements TaskCompleted {

    BottomNavigationView nv;
    Button llamar;
    Button email;
    Button web;
    Button video,recarga;
    private TextView textoTiempo, datosTemp, datosGases, datosHumedad, datosCalidadAire, datosFecha,
            datosHora;
    Lectura lectura;



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        llamar=view.findViewById(R.id.imageButtonTelefono);
        email=view.findViewById(R.id.imageButtonCorreoe);
        web= view.findViewById(R.id.imageButtonWeb);
        video= view.findViewById(R.id.buttonVideo);
        textoTiempo=view.findViewById(R.id.textViewContadorTiempo);

        datosTemp = view.findViewById(R.id.datoTemp);
        datosGases = view.findViewById(R.id.datoGases);
        datosHumedad = view.findViewById(R.id.datoHumedad);
        datosCalidadAire = view.findViewById(R.id.datoCalidadA);
        datosFecha = view.findViewById(R.id.tvDatoFecha);
        datosHora = view.findViewById(R.id.tvDatoHora);
        recarga=view.findViewById(R.id.buttonRecargar);


        contadorTiempoUso();

        Ejecutar();

        if (!isInternetConnected()) {
            Toast.makeText(getContext(), "No hay conexión a Internet", Toast.LENGTH_SHORT).show();
            return;
        }


        recarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Ejecutar();
            }
        });





















        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pasardatosVideo = new Intent(view.getContext(), MainActivityVideo.class);
                startActivity(pasardatosVideo);
            }
        });


        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeb("https://iesarroyoharnina.educarex.es/");
            }
        });
        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamarAlCentro();
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarEmailConGmail();

            }
        });

    }


    private boolean isInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnected();
        }
        return false;
    }


    private void Ejecutar() {
        lectura=new Lectura(this);
        lectura.execute();
    }

    private void contadorTiempoUso() {
        //Creo el hilo

        Handler hilo= new Handler();
        Runnable runnable;


        /*  Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivity(intent);*/


        UsageStatsManager tiempoUso = (UsageStatsManager) getActivity().getSystemService(Context.USAGE_STATS_SERVICE);
        SharedPreferences preferences = getActivity().getSharedPreferences("Tiempo Uso Preferencess", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        long tiempoInicioDiaAnterior = preferences.getLong("Tiempo del dia Antes", -1);
        long tiempoUsoHoy = preferences.getLong("Tiempo de hoy", 0);


        //Aqui es a la hora que seteo los valores que estaban antes guardado para poner el comtador a 0 a las 00:00:00h

        Calendar calendario = Calendar.getInstance();
        calendario.setTimeInMillis(System.currentTimeMillis());

        calendario.set(Calendar.HOUR_OF_DAY, 0);
        calendario.set(Calendar.MINUTE, 0);
        calendario.set(Calendar.SECOND, 0);
        calendario.set(Calendar.MILLISECOND, 0);

        long inicioHoy = calendario.getTimeInMillis();

        //Aqui reinicio el  contador para un nuevo día era la parte que me faltaba
        if (tiempoInicioDiaAnterior < inicioHoy) {

            editor.putLong("Tiempo Inicio DiaAnterior", inicioHoy);
            editor.putLong("TiempoUsoHoy", 0);
            editor.apply();
        }

        long fin = System.currentTimeMillis();

        List<UsageStats> miListaUso = tiempoUso.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, inicioHoy, fin);

        //Me pide que lo convierta a un array
        final long[] totalMiliSeg = {0};
        for (UsageStats miUso : miListaUso) {
            totalMiliSeg[0] =  totalMiliSeg[0] + miUso.getTotalTimeInForeground();
        }


        editor.putLong("Tiempo Uso Hoy", totalMiliSeg[0]);
        editor.apply();


        runnable=new Runnable() {
            @Override
            public void run() {
                //Meto el proceso del contador del temporizador dentro del hilo que lo que se irá actualizando cada segundo
                //por lo cual se me va a ir mostrando en tiempo real en el movil
                totalMiliSeg[0] = totalMiliSeg[0] +1000;
                long horas = TimeUnit.MILLISECONDS.toHours(totalMiliSeg[0]);
                long minutos = TimeUnit.MILLISECONDS.toMinutes(totalMiliSeg[0]) % 60;
                long segundos = TimeUnit.MILLISECONDS.toSeconds(totalMiliSeg[0]) % 60;

                String tiempoDeUso = "Llevas usando esta app: " + horas + " h " + minutos + " min " + segundos + " seg";
                textoTiempo.setText(tiempoDeUso);


                hilo.postDelayed(this,1000);
            }
        };
        //Este es el que actualiza el tiempo osea el temporizador que actualiza el tiempo le doy un seg y cada seg se actualiza
        //Por lo cual hace que se vaya cambiando en tiempo de ejecución
        hilo.postDelayed(runnable,1000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    public void openWeb(String url) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        startActivity(intent);
    }

    private void llamarAlCentro() {
        Intent intentoLlamada= new Intent(Intent.ACTION_VIEW,Uri.parse("tel:924017778"));
        startActivity(intentoLlamada);
    }


    public void enviarEmailConGmail() {
        String[] direccionesEmail = {"ies.arroyoharnina@edu.juntaex.es"};
       /* String asunto = "Diploma ";
        String cuerpoMensaje = "Buenos días me pongo en contacto con ustedes para recoger el título de 2º DAM";*/

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, direccionesEmail);
        // intent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        //intent.putExtra(Intent.EXTRA_TEXT, cuerpoMensaje);


        intent.setPackage("com.google.android.gm");

        try {
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) {

            Toast.makeText(getView().getContext(), "La aplicación Gmail no está instalada.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onTaskCompleted(String s) {
        List<Datos> datosList=new ArrayList<>();

        JSONObject json = null;

        try {
            json = new JSONObject(s);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


            try {

                Datos datos=new Datos(json.getString("temperatura"),json.getString("humedad"), json.getString("calidadaire"),json.getString("gasespeligrosos"), json.getString("fecha"), json.getString("hora"));
                datosList.add(datos);

                Float temp= Float.parseFloat(datos.getTemperatura());
                Float gas=Float.parseFloat(datos.getGasespeligrosos());
                Integer hum= Integer.parseInt(datos.getHumedad());
                Float air=Float.parseFloat(datos.getCalidadaire());
                datosTemp.setText(datos.getTemperatura()+" "+"ºC");
                if(temp<25){

                    datosTemp.setTextColor(Color.BLUE);
                }else{
                    datosTemp.setTextColor(Color.RED);
                }
                datosGases.setText(datos.getGasespeligrosos() + " " + "PPM");
                if (gas<0.5f){


                    datosGases.setTextColor(Color.GREEN);
                }else{
                    datosGases.setTextColor(Color.RED);
                }
                datosHumedad.setText(datos.getHumedad()+" "+"%");
                if (hum<30 || hum>60){

                    datosHumedad.setTextColor(Color.RED);

                }else {
                    datosHumedad.setTextColor(Color.GREEN);
                }



                datosCalidadAire.setText(datos.getCalidadaire()+" "+"µg/m³");


                if(air<25){


                    datosCalidadAire.setTextColor(Color.GREEN);
                }else {
                    datosCalidadAire.setTextColor(Color.RED);
                }

                datosFecha.setText(datos.getFecha());
                datosHora.setText(datos.getHora());



            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

}