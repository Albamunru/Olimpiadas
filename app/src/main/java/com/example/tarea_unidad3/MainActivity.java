package com.example.tarea_unidad3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;


import com.example.tarea_unidad3.fragments.CulturasFragment;
import com.example.tarea_unidad3.fragments.EducationFragment;
import com.example.tarea_unidad3.fragments.MainFragment;
import com.example.tarea_unidad3.fragments.SaludFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    FragmentContainerView containerView;

    CulturasFragment culturasFragment = new CulturasFragment();
    MainFragment mainFragment = new MainFragment();
    EducationFragment educationFragment = new EducationFragment();
    SaludFragment saludFragment = new SaludFragment();

    BottomNavigationView navigationView;



    @SuppressLint({"WrongViewCast", "CutPasteId", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.containerView = findViewById(R.id.fragmentContainer);

        setNavMenu();







    }

    public void setNavMenu(){
        this.navigationView = findViewById(R.id.btnNavMenu);

        changeFragment(mainFragment);

        this.navigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.idhome:
                    changeFragment(mainFragment);
                    break;
                case R.id.idculture:
                    changeFragment(culturasFragment);
                    break;
                case R.id.ideducation:
                    changeFragment(educationFragment);
                    break;
                case R.id.idsalud:
                    changeFragment(saludFragment);
                    break;
                default:
                    changeFragment(mainFragment);
                    break;

            }

            return true;
        });
    }

    public void changeFragment(Fragment fragment){
    getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuopciones_main, menu);
        return super.onCreateOptionsMenu(menu);
    }







}