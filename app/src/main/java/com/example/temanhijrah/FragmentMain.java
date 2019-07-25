package com.example.temanhijrah;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.temanhijrah.jadwalModel.ApiClient;
import com.example.temanhijrah.jadwalModel.ApiInterface;
import com.example.temanhijrah.jadwalModel.Items;
import com.example.temanhijrah.jadwalModel.Jadwal;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentMain extends Fragment {
    private FusedLocationProviderClient fusedLocationClient;

    final Thread thread = new Thread() {
        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    Log.d("thread", "not interrupted");
                    Thread.sleep(1000);
                    Objects.requireNonNull(FragmentMain.this.getActivity()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView hoursRemaining = FragmentMain.this.getActivity().findViewById(R.id.hour_remain);
                            TextView minuteRemaining = FragmentMain.this.getActivity().findViewById(R.id.minute_remain);
                            TextClock currentTime = FragmentMain.this.getActivity().findViewById(R.id.current_time);
                            String currentTimeString = currentTime.toString();
                            DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                            try {
                                Calendar cal = Calendar.getInstance();
                                Date now = cal.getTime();
                                String[] jadwal = new String[6];
                                TextView waktuSholat = FragmentMain.this.getActivity().findViewById(R.id.time_imsak);
                                jadwal[0] = waktuSholat.getText().toString();
                                waktuSholat = FragmentMain.this.getActivity().findViewById(R.id.time_subuh);
                                jadwal[1] = waktuSholat.getText().toString();
                                waktuSholat = FragmentMain.this.getActivity().findViewById(R.id.time_dzuhur);
                                jadwal[2] = waktuSholat.getText().toString();
                                waktuSholat = FragmentMain.this.getActivity().findViewById(R.id.time_ashar);
                                jadwal[3] = waktuSholat.getText().toString();
                                waktuSholat = FragmentMain.this.getActivity().findViewById(R.id.time_maghrib);
                                jadwal[4] = waktuSholat.getText().toString();
                                waktuSholat = FragmentMain.this.getActivity().findViewById(R.id.time_isya);
                                jadwal[5] = waktuSholat.getText().toString();

                                boolean isImsak = false;
                                String shalatSoon = "";
                                for (int i = 0; i < jadwal.length; i++) {
                                    Date jadwalTime = timeFormat.parse(jadwal[i]);
                                    jadwalTime.setYear(now.getYear());
                                    jadwalTime.setMonth(now.getMonth());
                                    jadwalTime.setDate(now.getDate());
                                    Log.i("time", String.valueOf(jadwalTime));
                                    if (jadwalTime.after(now)) {
                                        Log.i("now", String.valueOf(now));
                                        shalatSoon = jadwal[i];
                                        setColor(i);
                                        break;
                                    } else if (i == 5) {
                                        isImsak = true;
                                        shalatSoon = jadwal[0];
                                        setColor(0);
                                    }
                                }
                                int hours, mins;
                                String sNow = now.getHours() + ":" + now.getMinutes();
                                hours = timeFormat.parse(shalatSoon).getHours() - timeFormat.parse(sNow).getHours();
                                mins = timeFormat.parse(shalatSoon).getMinutes() - timeFormat.parse(sNow).getMinutes();
                                if (isImsak) {
                                    hours += 24;
                                }
                                if (mins < 0) {
                                    hours--;
                                    mins += 60;
                                }
                                /*
                                long mills = timeFormat.parse(shalatSoon).getTime() - timeFormat.parse(sNow).getTime();
                                hours = (int) (mills/(1000 * 60 * 60));
                                mins = (int) ((mills/(1000*60)) % 60);
                                */
                                String h, m;
                                h = String.valueOf(hours);
                                m = String.valueOf(mins);
                                hoursRemaining.setText(h);
                                minuteRemaining.setText(m);
                                Log.i("SoonNew", shalatSoon);
                                Log.i("nowNew", String.valueOf(cal.getTime()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                Log.d("thread", "interrupted");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    };

    private void setColor(int i) {
        TextView[] viewJadwal = new TextView[12];
        viewJadwal[0] = getActivity().findViewById(R.id.imsak);
        Log.i("imsak", viewJadwal[0].getText().toString());
        viewJadwal[1] = getActivity().findViewById(R.id.time_imsak);
        viewJadwal[2] = getActivity().findViewById(R.id.subuh);
        viewJadwal[3] = getActivity().findViewById(R.id.time_subuh);
        viewJadwal[4] = getActivity().findViewById(R.id.dzuhur);
        viewJadwal[5] = getActivity().findViewById(R.id.time_dzuhur);
        viewJadwal[6] = getActivity().findViewById(R.id.ashar);
        viewJadwal[7] = getActivity().findViewById(R.id.time_ashar);
        viewJadwal[8] = getActivity().findViewById(R.id.maghrib);
        viewJadwal[9] = getActivity().findViewById(R.id.time_maghrib);
        viewJadwal[10] = getActivity().findViewById(R.id.isya);
        viewJadwal[11] = getActivity().findViewById(R.id.time_isya);
        for (int idx = 0; idx < viewJadwal.length; idx++) {
            viewJadwal[idx].setTextColor(getResources().getColor(R.color.textGrey));
        }
        viewJadwal[2 * i].setTextColor(getResources().getColor(R.color.colorPrimary));
        viewJadwal[2 * i + 1].setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //String txt = getArguments().getString("name");

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.getActivity());
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TOD: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.e("Error", "Permission not granted");
        } else {
            final double[] currentLat = new double[1];
            final double[] currentLong = new double[1];
            final Activity activity = this.getActivity();
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                Log.i("Location", location.toString());
                                currentLat[0] = location.getLatitude();
                                currentLong[0] = location.getLongitude();
                                try {
                                    Calendar c = Calendar.getInstance();

                                    Geocoder geo = new Geocoder(activity.getApplicationContext(), Locale.getDefault());
                                    Log.i("Latitude", String.valueOf(currentLat[0]));
                                    Log.i("Longitude", String.valueOf(currentLong[0]));
                                    List<Address> addresses = geo.getFromLocation(currentLat[0], currentLong[0], 1);
                                    Log.i("Address", addresses.get(0).getSubAdminArea());
                                    Log.i("Address", addresses.get(0).getAdminArea());
                                    TextView kota = activity.findViewById(R.id.kota);
                                    kota.setText(addresses.get(0).getSubAdminArea());
                                    TextView provinsi = activity.findViewById(R.id.provinsi);
                                    provinsi.setText(addresses.get(0).getAdminArea());

                                    final String loc = addresses.get(0).getSubAdminArea();
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    String time = sdf.format(c.getTime());

                                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                                    Call<Items> call = apiInterface.getJadwalSholat(loc, time);
                                    call.enqueue(new Callback<Items>() {
                                        @Override
                                        public void onResponse(Call<Items> call, Response<Items> response) {
                                            Log.d("Data ", " respon" + response.toString());
                                            Jadwal jadwal = response.body().getData();
                                            Log.d("respon data ", "" + new Gson().toJson(jadwal));

                                            if (jadwal != null) {
                                                String zuhur = jadwal.getDhuhr();
                                                String ashar = jadwal.getAsr();
                                                String magrib = jadwal.getMaghrib();
                                                String isya = jadwal.getIsha();
                                                String subuh = jadwal.getFajr();
                                                String imsak = jadwal.getImsak();
                                                Log.d("respon :", "" + zuhur);
                                                TextView txtDzuhur = activity.findViewById(R.id.time_dzuhur);
                                                txtDzuhur.setText(zuhur);
                                                TextView txtAshar = activity.findViewById(R.id.time_ashar);
                                                txtAshar.setText(ashar);
                                                TextView txtMaghrib = activity.findViewById(R.id.time_maghrib);
                                                txtMaghrib.setText(magrib);
                                                TextView txtIsya = activity.findViewById(R.id.time_isya);
                                                txtIsya.setText(isya);
                                                TextView txtSubuh = activity.findViewById(R.id.time_subuh);
                                                txtSubuh.setText(subuh);
                                                TextView txtImsak = activity.findViewById(R.id.time_imsak);
                                                txtImsak.setText(imsak);

                                                Log.d("", "lokasi" + loc);
                                            }
// loadData(jadwal);
                                        }

                                        @Override
                                        public void onFailure(Call<Items> call, Throwable t) {
                                            Log.d("Data ", "" + t.getMessage());
                                        }
                                    });

                                    thread.start();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Log.i("Location", "null");
                            }
                        }
                    });
        }
        return inflater.inflate(R.layout.fragment_main, null);
    }
}
