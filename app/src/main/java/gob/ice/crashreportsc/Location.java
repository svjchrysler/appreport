package gob.ice.crashreportsc;

import android.location.LocationListener;
import android.os.Bundle;

import gob.ice.crashreportsc.activities.FormPoliceActivity;

/**
 * Created by mvaldez on 15/12/2016.
 */

public class Location implements LocationListener {

    FormPoliceActivity formPoliceActivity = new FormPoliceActivity();

    public FormPoliceActivity getFormPoliceActivity() {
        return formPoliceActivity;
    }

    public void setFormPoliceActivity(FormPoliceActivity formPoliceActivity) {
        this.formPoliceActivity = formPoliceActivity;
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        formPoliceActivity.setLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
