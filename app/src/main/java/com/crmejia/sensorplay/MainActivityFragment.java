package com.crmejia.sensorplay;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private SensorManager mSensorManager;
    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);

        TextView availableSensorTextView = (TextView) rootView.findViewById(R.id.available_sensors_text_view);
        List<Sensor> availableSensors=  mSensorManager.getSensorList(Sensor.TYPE_ALL);
        String sensorNames = null;
        for(Sensor sensor : availableSensors){
            sensorNames += sensor.getName() + " | ";
        }
        availableSensorTextView.setText(sensorNames);

        return rootView;
    }
}
