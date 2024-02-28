package com.example.tarea_unidad3.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tarea_unidad3.R;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SaludFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class SaludFragment extends Fragment {
     TextView textoTiempo;
     private Button alante;
     private Button atras;
     private ImageView imagenView;
     private int[]imagenes;
     int indice=0;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private float mLastTouchX;
    private float mLastTouchY;
    private int mActivePointerId;

    public SaludFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        alante = view.findViewById(R.id.buttonImgSiguiente);
        atras = view.findViewById(R.id.buttonImgAtras);
        imagenView = view.findViewById(R.id.imageViewBienestar);


        imagenes = new int[]{R.drawable.bienestar, R.drawable.consejo};


        imagenView.setImageResource(imagenes[indice]);
        atras.setVisibility(view.INVISIBLE);

        scaleGestureDetector=new ScaleGestureDetector(getActivity(), new ScaleListener() );
        imagenView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scaleGestureDetector.onTouchEvent(event);
                final int action = event.getAction();
                switch (action & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN: {
                        final float x = event.getX();
                        final float y = event.getY();

                        mLastTouchX = x;
                        mLastTouchY = y;
                        mActivePointerId = event.getPointerId(0);
                        break;
                    }

                    case MotionEvent.ACTION_MOVE: {
                        final int pointerIndex = event.findPointerIndex(mActivePointerId);
                        final float x = event.getX(pointerIndex);
                        final float y = event.getY(pointerIndex);

                        // Only move if the ScaleGestureDetector isn't processing a gesture.
                        if (!scaleGestureDetector.isInProgress()) {
                            final float dx = x - mLastTouchX;
                            final float dy = y - mLastTouchY;

                            imagenView.setTranslationX(imagenView.getTranslationX() + dx);
                            imagenView.setTranslationY(imagenView.getTranslationY() + dy);
                        }

                        mLastTouchX = x;
                        mLastTouchY = y;
                        break;
                    }

                    case MotionEvent.ACTION_UP: {
                        mActivePointerId = MotionEvent.INVALID_POINTER_ID;
                        // Restablecer la posición de la imagen
                        imagenView.animate().translationX(0).translationY(0);
                        break;
                    }

                    case MotionEvent.ACTION_CANCEL: {
                        mActivePointerId = MotionEvent.INVALID_POINTER_ID;
                        break;
                    }

                    case MotionEvent.ACTION_POINTER_UP: {
                        final int pointerIndex = (action & MotionEvent.ACTION_POINTER_INDEX_MASK)
                                >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                        final int pointerId = event.getPointerId(pointerIndex);
                        if (pointerId == mActivePointerId) {
                            // This was our active pointer going up. Choose a new
                            // active pointer and adjust accordingly.
                            final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                            mLastTouchX = event.getX(newPointerIndex);
                            mLastTouchY = event.getY(newPointerIndex);
                            mActivePointerId = event.getPointerId(newPointerIndex);
                        }
                        break;
                    }
                }

                return true;
            }
        });
        alante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if index is within bounds
                if (indice < imagenes.length - 1) {
                    indice++;
                } else {
                    indice = 0; // Reset to the first image
                }
                // Display the next image
                imagenView.setImageResource(imagenes[indice]);
            }
        });



       /* atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if index is within bounds
                if (indice > 0) {
                    indice--;
                } else {
                    indice = imagenes.length - 1; // Move to the last image
                }
                // Display the previous image
                imagenView.setImageResource(imagenes[indice]);
            }
        });*/
    }
        //AQUI VA EL ON CREATE DEL FRAGMENT!!!!!




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_salud, container, false);
    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            // Obtener la escala actual
            mScaleFactor *= detector.getScaleFactor();

            // Limitar el zoom dentro de ciertos límites
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));

            // Aplicar la escala a la ImageView
            imagenView.setScaleX(mScaleFactor);
            imagenView.setScaleY(mScaleFactor);

            return true;
        }
    }



}