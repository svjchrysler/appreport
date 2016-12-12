package gob.ice.crashreportsc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import gob.ice.crashreportsc.R;

/**
 * Created by lgmguadama on 12/7/2016.
 */

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.login_btnpolice)
    void formpolice() {
        startActivity(new Intent(LoginActivity.this, FormPoliceActivity.class));
        finish();
    }

    @OnClick(R.id.login_btnpedestrian)
    void formpeaton() {
        startActivity(new Intent(LoginActivity.this, FormPoliceActivity.class));
        finish();
    }
}
