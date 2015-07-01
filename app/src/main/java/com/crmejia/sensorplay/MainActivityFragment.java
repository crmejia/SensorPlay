package com.crmejia.sensorplay;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mLightSensor;
    private ArrayAdapter<String> mSensorAdapter;
    private TextView mLuxTextView = null;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mLightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<Sensor> availableSensors=  mSensorManager.getSensorList(Sensor.TYPE_ALL);
        List<String> sensorDetail = new ArrayList<String>();
        for(Sensor sensor: availableSensors){
            sensorDetail.add(String.format("Name:%s  power:%s res:%s",
                    sensor.getName(), sensor.getPower(), sensor.getResolution()));
        }
        mSensorAdapter =
                new ArrayAdapter<String>(
                        getActivity(),
                        R.layout.all_sensor_list_view,
                        R.id.sensorNameTextView,
                        sensorDetail
                );

        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);

        mLuxTextView = (TextView)rootView.findViewById(R.id.luxTextView);
        ListView listView = (ListView)rootView.findViewById(R.id.allSensorsListView);
        listView.setAdapter(mSensorAdapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,mLightSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Float lux  = event.values[0];
        if(mLuxTextView!=null){
            mLuxTextView.setText(Float.toString(lux));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
