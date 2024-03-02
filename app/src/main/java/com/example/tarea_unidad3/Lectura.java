package com.example.tarea_unidad3;

import android.os.AsyncTask;

import com.example.tarea_unidad3.fragments.MainFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Lectura extends AsyncTask<Object,Object,String> {

    private String linea=null;
    private TaskCompleted listener;

    public Lectura(MainFragment listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Object... objects) {
        try{
            URL url=new URL("https://iesarroyoharnina.es/sensoresharnina/get.php");
            HttpURLConnection clientehttp=(HttpURLConnection) url.openConnection();
            clientehttp.setRequestMethod("GET");
            clientehttp.setRequestProperty("Content-Type","application/json ");
            //LEEMOS EL STRING QUE NOS DEVUELVE EL SERVICIO WEB
            InputStream is=clientehttp.getInputStream();
            InputStreamReader isReades=new InputStreamReader(is, "UTF-8");
            BufferedReader reader=new BufferedReader(isReades);
            linea=reader.readLine();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linea;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.onTaskCompleted(linea);
    }
}
