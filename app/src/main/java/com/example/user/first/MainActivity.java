package com.example.user.first;

import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {


    public TextView txtlat,txtlong,txtalt,txstate;
    public Button btLoc,btGMap;
    public LocationManager locationMan;
    public MyLocationListener myLocationLx;
    public double latitude,longtitude,altitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtalt=(TextView)findViewById(R.id.altitude);
        txtlat=(TextView)findViewById(R.id.latitude);
        txtlong=(TextView)findViewById(R.id.longtitude);
        txstate=(TextView)findViewById(R.id.state   );

        btLoc=(Button)findViewById(R.id.btLoc);
        btGMap=(Button)findViewById(R.id.btGMap);

        locationMan=(LocationManager) getSystemService(LOCATION_SERVICE);
        myLocationLx=new MyLocationListener();
        long minTime=1000;
        float minDistance=0;

        locationMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,minTime,minDistance,myLocationLx);
        //특정 위치 접근이기 때문에 퍼미션이 필수

        btLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txstate.setText("Getting location...");
                latitude=myLocationLx.latitude;
                longtitude=myLocationLx.longtitude;
                altitude=myLocationLx.altitude;

                txtlat.setText(String.format("%g",latitude));
                txtalt.setText(String.format("%g",altitude));
                txtlong.setText(String.format("%g",longtitude));
                txstate.setText("Done retreiving");
            }
        });

        btGMap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("geo:%g,%g,%g",altitude,longtitude,latitude)));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
