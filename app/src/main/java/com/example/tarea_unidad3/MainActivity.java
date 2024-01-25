package com.example.tarea_unidad3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView nv;

    TextView pagWeb;
     ImageButton llamar;
     ImageButton email;


    @SuppressLint({"WrongViewCast", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llamar=findViewById(R.id.imageButtonTelefono);
        email=findViewById(R.id.imageButtonCorreo);


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


        pagWeb = findViewById(R.id.tvcp);

        pagWeb.setOnClickListener(e -> {
            openWeb("https://iesarroyoharnina.educarex.es/");
        });

        nv = findViewById(R.id.btnNavMenu);
        nv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.idculture){
                    Intent pasardatos1 = new Intent(getApplicationContext(), MainActivityCulturas.class);
                    startActivity(pasardatos1);
                }else if(item.getItemId() == R.id.ideducation){
                    Intent pasardatos2 = new Intent(getApplicationContext(), MainActivityEducation.class);
                    startActivity(pasardatos2);
                }
                return false;
            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuopciones_main, menu);
        return super.onCreateOptionsMenu(menu);
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
        String asunto = "Diploma ";
        String cuerpoMensaje = "Buenos días me pongo en contacto con ustedes para recoger el título de 2º DAM";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, direccionesEmail);
        intent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        intent.putExtra(Intent.EXTRA_TEXT, cuerpoMensaje);


        intent.setPackage("com.google.android.gm");

        try {
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) {

            Toast.makeText(this, "La aplicación Gmail no está instalada.", Toast.LENGTH_SHORT).show();
        }
    }





}