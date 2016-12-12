package gob.ice.crashreportsc.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mlsdev.rximagepicker.RxImagePicker;
import com.mlsdev.rximagepicker.Sources;


import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gob.ice.crashreportsc.ApplicationReportCollision;
import gob.ice.crashreportsc.R;
import gob.ice.crashreportsc.Utils;
import gob.ice.crashreportsc.adapters.InvolvedAdapter;
import gob.ice.crashreportsc.models.Involved;
import rx.functions.Action1;


public class FormPoliceActivity extends AppCompatActivity {

    private DatabaseReference df;
    private FirebaseDatabase firebaseDatabase;
    private Context context;

    @BindView(R.id.linearInvolucrados)
    LinearLayout linearInvolucrados;
    @BindView(R.id.groupTag)
    TagView tagView;

    @BindView(R.id.imgCamera)
    ImageView imgCamera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_police);
        context=this;
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
        //final Dialog dialog = new Dialog(this, android.R.style.Theme_DeviceDefault_DialogWhenLarge_NoActionBar);
        Dialog dialog = null;
        dialog  = new Dialog(this, android.R.style.Theme_DeviceDefault_DialogWhenLarge);

        dialog.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_involved);
        RecyclerView recyclerItemInvolved = null;
        recyclerItemInvolved = (RecyclerView) dialog.findViewById(R.id.recyclerItemInvolved);

        Button btnAceptar = (Button)dialog.findViewById(R.id.btndialogaceptar);
        Button btnCancelar = (Button)dialog.findViewById(R.id.btndialogcancelar);

        final Dialog finalDialog = dialog;
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalDialog.dismiss();
            }
        });


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalDialog.dismiss();
            }
        });
        recyclerItemInvolved.setAdapter(null);
        InvolvedAdapter involvedAdapter = new InvolvedAdapter(this, Utils.listInvolucrados);
        recyclerItemInvolved.setAdapter(involvedAdapter);
        recyclerItemInvolved.setLayoutManager(new LinearLayoutManager(this));

        dialog.show();
    }

    @OnClick(R.id.btnRegister)
    void register() {

        Tag tag = new Tag("Automovil " + new Random().nextInt() * 2);

        tagView.addTag(tag);

        configEventTag();

    }

    private void configEventTag() {
        tagView.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(Tag tag, int i) {
                Toast.makeText(context, tag.text, Toast.LENGTH_SHORT).show();
            }
        });

        tagView.setOnTagLongClickListener(new TagView.OnTagLongClickListener() {
            @Override
            public void onTagLongClick(Tag tag, int i) {
                tagView.remove(i);
            }
        });
    }

    @OnClick(R.id.btnCancel)
    void cancel() {
        startActivity(new Intent(context,LoginActivity.class));
        finish();
    }

    @OnClick(R.id.btnShowInvolved)
    void showInvolved() {
        showDialogInvolved();
    }

    @OnClick(R.id.rxcamera)
    void uploadImage() {
        RxImagePicker.with(context).requestImage(Sources.CAMERA).subscribe(new Action1<Uri>() {
            @Override
            public void call(Uri uri) {
                Utils.image = uri;
                imgCamera.setImageURI(Utils.image);
            }
        });
    }

}
