package com.example.temanhijrah.giantplay.ui.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.temanhijrah.R;
import com.example.temanhijrah.giantplay.service.MediaService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlayerSurahActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaService mService;
    private boolean mBound = false;
    private boolean isReady = false;

    private Button play, next, prev;
    private TextView surah,reciter,duration_min,duration_sec,current_min,current_sec;
    private SeekBar seekBar;
    private Spinner list_reciters;

    private JSONObject jsonObject;
    private JSONArray jsonArrayAyahs;

    private ListView listAyahs;
    private ArrayAdapter adapter;

    private Runnable runnable;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_surah);

        play = (Button) findViewById(R.id.btn_player_play);

        surah = (TextView) findViewById(R.id.current_surah);
        reciter= (TextView) findViewById(R.id.current_reciter);
        duration_min = (TextView) findViewById(R.id.media_player_duration_min);
        duration_sec = (TextView) findViewById(R.id.media_player_duration_sec);
        current_min = (TextView) findViewById(R.id.media_player_current_min);
        current_sec = (TextView) findViewById(R.id.media_player_current_sec);

        listAyahs = (ListView) findViewById(R.id.list_ayahs);

        seekBar = (SeekBar)findViewById(R.id.seekBar2);

        play.setOnClickListener(this);

        handler = new Handler();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mBound) {
                    if (fromUser)
                        mService.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        initializeDisplay();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MediaService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter("media_service_com"));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_player_play:
                if (mBound && isReady){
                    mService.changePlayerState();
                    checkPlayButton();
                    changeSeekBar();
                }
                break;
            case R.id.btn_player_next:

                break;
            case R.id.btn_player_prev:

                break;
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MediaService.MediaBinder binder = (MediaService.MediaBinder) service;
            mService = binder.getService();
            mBound = true;
            //Play Button
            checkPlayButton();
            //Set Duration
            seekBar.setMax(mService.getMaxDuration());
            maxDurationMedia(mService.getMaxDuration());
            setCurrentMedia(mService.getCurrentPosition());
            changeSeekBar();
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getStringExtra("message").equals("media_stopped")){
                play.setBackgroundResource(R.drawable.ic_player_play);
            }
        }
    };

    public void initializeDisplay(){
        String URL = "https://api.alquran.cloud/v1/surah/1";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            jsonObject = response.getJSONObject("data");
                            surah.setText(jsonObject.getString("englishName"));
                            reciter.setText(jsonObject.getString("englishNameTranslation"));

                            jsonArrayAyahs = jsonObject.getJSONArray("ayahs");
                            List<String> ayahs = new ArrayList<String>();
                            for (int i = 0; i < jsonArrayAyahs.length(); i++) {
                                JSONObject jo = jsonArrayAyahs.getJSONObject(i);
                                ayahs.add(jo.getString("text"));
                            }
                            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout._list_ayahs, ayahs);
                            listAyahs.setAdapter(adapter);

                            isReady = true;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("REST Response", error.toString());
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    public void checkPlayButton(){
        if (mService.getIsPlaying())
            play.setBackgroundResource(R.drawable.ic_player_pause);
        else
            play.setBackgroundResource(R.drawable.ic_player_play);
    }

    public void changeSeekBar(){
        if (mBound){
            setCurrentMedia(mService.getCurrentPosition());
            seekBar.setProgress(mService.getCurrentPosition());
            if (mService.getMediaPlayer().isPlaying()){
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        changeSeekBar();
                    }
                };

                handler.postDelayed(runnable, 1000);
            }
        }
    }

    public void maxDurationMedia(int duration){
        String minute = String.format("%02d",TimeUnit.MILLISECONDS.toMinutes(duration));
        duration_min.setText(minute);
        String seconds = String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
        duration_sec.setText(seconds);
    }

    public void setCurrentMedia(int duration){
        String minute = String.format("%02d",TimeUnit.MILLISECONDS.toMinutes(duration));
        current_min.setText(minute);
        String seconds = String.format("%02d",TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
        current_sec.setText(seconds);
    }

}
