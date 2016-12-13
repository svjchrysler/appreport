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
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.mlsdev.rximagepicker.RxImagePicker;
import com.mlsdev.rximagepicker.Sources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gob.ice.crashreportsc.R;
import gob.ice.crashreportsc.Utils;
import gob.ice.crashreportsc.adapters.InvolvedAdapter;
import gob.ice.crashreportsc.models.Involved;
import rx.functions.Action1;


public class FormPoliceActivity extends AppCompatActivity implements gob.ice.crashreportsc.interfaces.OnClick {

    private Context context;


    @BindView(R.id.groupTag)
    TagView tagView;

    @BindView(R.id.imgCamera)
    ImageView imgCamera;

    @BindView(R.id.sp_weather)
    Spinner spWeather;

    @BindView(R.id.lbldate)
    TextView lblDate;

    @BindView(R.id.lblhour)
    TextView lblHour;

    @BindView(R.id.toolbarForm)
    Toolbar toolbarForm;

    private ArrayList<Involved> listAddItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_police);
        context = this;
        ButterKnife.bind(this);
        configInit();
    }

    private void configInit() {
        loadArray();
        cargarSpinnerClima();
        getDateTime();
        configToolbar();
    }

    private void configToolbar() {
        setSupportActionBar(toolbarForm);
        toolbarForm.removeAllViews();
        toolbarForm.removeAllViewsInLayout();
    }

    private void cargarSpinnerClima() {
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.clima,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spWeather.setAdapter(adapter);
    }

    private void getDateTime() {
        DateFormat dfd = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dfh = new SimpleDateFormat("HH:mm");
        dfd.setLenient(false);
        dfh.setLenient(false);
        Date today = new Date();
        String sd = dfd.format(today);
        String sh = dfh.format(today);
        lblDate.setText(sd);
        lblHour.setText(sh);
    }

    private void loadArray() {
        Utils.listInvolucrados.add(new Involved("Auto", true, false));
        Utils.listInvolucrados.add(new Involved("Bicicleta", true, false));
        Utils.listInvolucrados.add(new Involved("Motocicleta", true, false));
        Utils.listInvolucrados.add(new Involved("Bus", true, false));
        Utils.listInvolucrados.add(new Involved("Taxi", true, false));
        Utils.listInvolucrados.add(new Involved("Camion", true, false));
        Utils.listInvolucrados.add(new Involved("Peaton", true, true));
        Utils.listInvolucrados.add(new Involved("Otro (Barda, Poste, Arbol, etc.)", false, false));

        this.listAddItems = new ArrayList<>();
    }

    private void showDialogInvolved() {

        final Dialog dialog = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_DialogWhenLarge);

        dialog.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_involved);
        RecyclerView recyclerItemInvolved = (RecyclerView) dialog.findViewById(R.id.recyclerItemInvolved);

        Button btnAceptar = (Button) dialog.findViewById(R.id.btndialogaceptar);
        Button btnCancelar = (Button) dialog.findViewById(R.id.btndialogcancelar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                addTags();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        InvolvedAdapter involvedAdapter = new InvolvedAdapter(this, Utils.listInvolucrados, this, this.listAddItems);

        recyclerItemInvolved.setAdapter(involvedAdapter);
        recyclerItemInvolved.setLayoutManager(new LinearLayoutManager(this));

        dialog.show();
    }

    private void addTags() {
        tagView.removeAllViewsInLayout();
        tagView.removeAllViews();
        tagView.removeAll();

        for (Involved involved : this.listAddItems) {
            Tag tag = new Tag(involved.getName() + " " + involved.getCount());
            tagView.addTag(tag);
        }

        configEventTag();
    }

    @OnClick(R.id.btnRegister)
    void register() {


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
        startActivity(new Intent(context, LoginActivity.class));
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

    @Override
    public void onClick(View component, int number, String item) {
        int count = Integer.parseInt(((TextView) component).getText().toString());
        if (count > 0 || number > 0) {
            count += number;
            ((TextView) component).setText(String.valueOf(count));
            addElementList(item, count);
        }
    }

    private void addElementList(String name, int count) {
        int position = validarExistencia(name);
        if (position > 0) {
            listAddItems.get(position).setCount(count);
        } else {
            Involved involved = new Involved();
            involved.setName(name);
            involved.setCount(count);
            this.listAddItems.add(involved);
        }
    }

    private int validarExistencia(String name) {
        for (int i = 0; i < this.listAddItems.size(); i++) {
            if (this.listAddItems.get(i).getName().trim().equals(name.trim().toString()))
                return i;
        }
        return -1;
    }

    @Override
    public void onClickSwitch(View component) {
        Switch swith = (Switch) component;

        if (swith.getText().toString().trim().equals("M"))
            ((Switch) component).setText(swith.getTextOn());
        else
            ((Switch) component).setText(swith.getTextOff());
    }
}
