package gob.ice.crashreportsc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import gob.ice.crashreportsc.R;
import gob.ice.crashreportsc.Utils;
import gob.ice.crashreportsc.models.Involved;

/**
 * Created by lgmguadama on 12/7/2016.
 */

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Utils.listInvolucrados = new ArrayList<Involved>();
        ButterKnife.bind(this);

    }

    @OnClick(R.id.login_btnpolice)
    void formpolice() {
        startActivity(new Intent(LoginActivity.this, FormPoliceActivity.class));
        finish();
    }

    @OnClick(R.id.login_btnpedestrian)
    void formpeaton() {
        startActivity(new Intent(LoginActivity.this, FormPedestrian_activity.class));
        finish();
    }
}
