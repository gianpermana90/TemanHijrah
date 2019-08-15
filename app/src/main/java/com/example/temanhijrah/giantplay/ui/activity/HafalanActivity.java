package com.example.temanhijrah.giantplay.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.temanhijrah.R;

import java.io.IOException;

public class HafalanActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRecord, btnPlayStop, btnDelete;

    private boolean isPlaying = false;
    private boolean isRecording = false;
    private boolean isAudioExist = false;

    private MediaRecorder hafalanRecorder;
    private MediaPlayer hafalanPlayer;

    private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static String fileName = null;

    //requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();

    }

    private void onRecord(boolean start){
        if (start){
            startRecording();
        }else{
            stopRecording();
        }
    }

    private void onPlay(boolean start){
        if(start){
            startPlaying();
        }else{
            stopPlaying();
        }
    }

    private void startPlaying(){
        hafalanPlayer = new MediaPlayer();
        try {
            hafalanPlayer.setDataSource(fileName);
            hafalanPlayer.prepare();
            hafalanPlayer.start();

            //listener on complete play media
            hafalanPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    onPlay(!isPlaying);
                    if(isPlaying){
                        btnPlayStop.setBackgroundResource(R.drawable.ic_hafalan_play);
                    }else {
                        btnPlayStop.setBackgroundResource(R.drawable.ic_hafalan_pause);
                    }
                    isPlaying = !isPlaying;
                }
            });

        }catch (IOException e){
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying(){
        hafalanPlayer.release();
        hafalanPlayer = null;
    }

    private void startRecording(){
        hafalanRecorder = new MediaRecorder();
        hafalanRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        hafalanRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        hafalanRecorder.setOutputFile(fileName);
        hafalanRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            hafalanRecorder.prepare();
        }catch (IOException e){
            Log.e(LOG_TAG, "prepare() failed");
        }

        hafalanRecorder.start();
    }

    private void stopRecording(){
        hafalanRecorder.stop();
        hafalanRecorder.release();
        hafalanRecorder = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hafalan);

        fileName = getExternalCacheDir().getAbsolutePath();
        fileName += "/audiorecordtes.3gp";

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        btnRecord = (Button) findViewById(R.id.btn_hafalan_record_active);
        btnRecord.setOnClickListener(this);

        btnPlayStop = (Button) findViewById(R.id.btn_hafalan_media);
        btnPlayStop.setOnClickListener(this);

        btnDelete = (Button) findViewById(R.id.btn_hafalan_delete);
        btnDelete.setOnClickListener(this);

    }



    @Override
    public void onStop(){
        super.onStop();
        if (hafalanRecorder != null){
            hafalanRecorder.release();
            hafalanRecorder = null;
        }

        if(hafalanPlayer != null){
            hafalanPlayer.release();
            hafalanPlayer = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_hafalan_record_active:
                onRecord(!isRecording);
                if (isRecording){
                    btnRecord.setBackgroundResource(R.drawable.ic_hafalan_mic_active);
                }else{
                    btnRecord.setBackgroundResource(R.drawable.ic_hafalan_mic_stop);
                }
                isRecording = !isRecording;
                break;
            case R.id.btn_hafalan_media:
                onPlay(!isPlaying);
                if(isPlaying){
                    btnPlayStop.setBackgroundResource(R.drawable.ic_hafalan_play);
                }else {
                    btnPlayStop.setBackgroundResource(R.drawable.ic_hafalan_pause);
                }
                isPlaying = !isPlaying;
                break;
            case R.id.btn_hafalan_delete:
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
