package com.example.tarea_unidad3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivityVideo extends AppCompatActivity {
    private VideoView miVideo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_video);

        miVideo=findViewById(R.id.videoViewVideo);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.consejos;
        miVideo.setVideoURI(Uri.parse(videoPath));
        miVideo.start();

        MediaController mediaController = new MediaController(this);

        mediaController.setAnchorView(miVideo);
        miVideo.setMediaController(mediaController);

    }
}