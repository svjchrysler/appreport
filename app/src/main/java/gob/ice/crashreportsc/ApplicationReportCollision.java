package gob.ice.crashreportsc;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by jcsalguero on 08/12/2016.
 */

public class ApplicationReportCollision extends Application {

    private FirebaseDatabase firebaseDatabase;
    @Override
    public void onCreate() {
        super.onCreate();
        configFirebase();
    }

    private void configFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();

    }
}
