package com.example.temanhijrah.giantplay.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.example.temanhijrah.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class MediaService extends Service {

    private MediaPlayer mediaPlayer;
    private final IBinder mBinder = new MediaBinder();

    //    private Surah surah;
    private Boolean isPlaying = false;
    private int currentPosition;

    private JSONObject jsonObject;
    private JSONArray jsonArrayAyahs;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            initMediaPlayer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {
        // Tell the user we stopped.
        Toast.makeText(this, "Destroyed", Toast.LENGTH_SHORT).show();
        mediaPlayer.stop();
    }

    public class MediaBinder extends Binder {
        public MediaService getService(){
            return MediaService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    public void initMediaPlayer() throws IOException {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.surah_001);
        currentPosition = 0;
    }

    public void changePlayerState(){
        if (mediaPlayer.isPlaying()){
            isPlaying = false;
            mediaPlayer.pause();
            currentPosition = mediaPlayer.getCurrentPosition();
        }else{
            isPlaying = true;
            mediaPlayer.seekTo(currentPosition);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                    currentPosition = 0;
                    //send some random data that media has been stopped
                    Log.i("LocalService", "Media Stopped");
                    Intent intent = new Intent("media_service_com");
                    intent.putExtra("message", "media_stopped");
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                }
            });
        }
    }

    public Boolean getIsPlaying(){
        return isPlaying;
    }

    public int getMaxDuration(){
        return mediaPlayer.getDuration();
    }

    public int getCurrentPosition(){
        return mediaPlayer.getCurrentPosition();
    }

    public void seekTo(int i){
        currentPosition = i;
        mediaPlayer.seekTo(currentPosition);
    }

    public MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }

}
