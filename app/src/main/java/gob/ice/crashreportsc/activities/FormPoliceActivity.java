package gob.ice.crashreportsc.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gob.ice.crashreportsc.ApplicationReportCollision;
import gob.ice.crashreportsc.R;
import gob.ice.crashreportsc.Utils;
import gob.ice.crashreportsc.adapters.InvolvedAdapter;
import gob.ice.crashreportsc.models.Involved;

public class FormPoliceActivity extends AppCompatActivity {

    private DatabaseReference df;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_police);
        ButterKnife.bind(this);
        configInit();
    }

    private void configInit() {
        loadArray();
        configFirebase();
    }

    private void loadArray() {
        Utils.listInvolucrados.add(new Involved("Auto", true));
        Utils.listInvolucrados.add(new Involved("Bicicleta", true));
        Utils.listInvolucrados.add(new Involved("Motocicleta", true));
        Utils.listInvolucrados.add(new Involved("Bus", true));
        Utils.listInvolucrados.add(new Involved("Taxi", true));
        Utils.listInvolucrados.add(new Involved("Camion", true));
        Utils.listInvolucrados.add(new Involved("Peaton", true));
        Utils.listInvolucrados.add(new Involved("Otro (Barda, Poste, Arbol, etc.)", false));
    }

    private void configFirebase() {
        configFirebaseDatabase();
    }

    private void configFirebaseDatabase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        df = firebaseDatabase.getReference("severidad");
    }

    private void showDialogInvolved() {
        Dialog dialog = new Dialog(this);

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        dialog.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_involved);

        RecyclerView recyclerItemInvolved = (RecyclerView) dialog.findViewById(R.id.recyclerItemInvolved);

        InvolvedAdapter involvedAdapter = new InvolvedAdapter(this, Utils.listInvolucrados);
        recyclerItemInvolved.setAdapter(involvedAdapter);
        recyclerItemInvolved.setLayoutManager(new LinearLayoutManager(this));

        dialog.show();
    }

    @OnClick(R.id.btnRegister)
    void register() {
    }

    @OnClick(R.id.btnShowInvolved)
    void showInvolved() {
        showDialogInvolved();
    }

}
