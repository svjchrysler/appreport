package gob.ice.crashreportsc.activities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.eminayar.panter.DialogType;
import com.eminayar.panter.PanterDialog;
import com.eminayar.panter.interfaces.OnSingleCallbackConfirmListener;

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
import gob.ice.crashreportsc.adapters.RiskAdapter;
import gob.ice.crashreportsc.interfaces.OnClickRisk;
import gob.ice.crashreportsc.models.Involved;
import gob.ice.crashreportsc.models.Risk;


public class FormPoliceActivity extends AppCompatActivity implements gob.ice.crashreportsc.interfaces.OnClick, OnClickRisk {

    private Context context;

    @BindView(R.id.groupTag)
    TagView tagView;

    @BindView(R.id.groupTagFactores)
    TagView tagViewRisk;

    @BindView(R.id.spWeather)
    TextView spWeather;

    @BindView(R.id.lbldate)
    TextView lblDate;

    @BindView(R.id.lblhour)
    TextView lblHour;

    private ArrayList<Involved> listAddItems;
    private ArrayList<Risk> listItemRisk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_police);
        context = this;
        ButterKnife.bind(this);
        configInit();
    }

    private void configInit() {
        setTitle("Formulario de Registro");
        loadArray();
        loadArrayRisk();
        getDateTime();
    }

    private void loadArrayRisk() {
        listItemRisk = new ArrayList<>();
        listItemRisk.add(new Risk("Alcohol", false));
        listItemRisk.add(new Risk("Velocidad", false));
        listItemRisk.add(new Risk("Sin Casco", false));
        listItemRisk.add(new Risk("Menor de Edad", false));
        listItemRisk.add(new Risk("Cinturon de Seguridad", false));
        listItemRisk.add(new Risk("Cansancio", false));
        listItemRisk.add(new Risk("Celular", false));
        listItemRisk.add(new Risk("Distraccion", false));
    }

    private void showOptionsClima() {

        new PanterDialog(this)
                .setDialogType(DialogType.SINGLECHOICE)
                .isCancelable(false)
                .items(R.array.clima, new OnSingleCallbackConfirmListener() {
                    @Override
                    public void onSingleCallbackConfirmed(PanterDialog dialog, int pos, String text) {
                        spWeather.setText(text);
                    }
                })
                .show();
    }

    @OnClick(R.id.spWeather)
    void showDialogClima() {
        showOptionsClima();
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

        this.listAddItems = null;
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
            if (involved.getCount() > 0) {

                Tag tag = new Tag(involved.getName() + " " + involved.getCount());
                tagView.addTag(tag);
            }
        }

        for (Involved involved: Utils.listInvolucrados) {
            if (involved.getSwObject()) {
                Tag tag = new Tag(involved.getName());
                tagView.addTag(tag);
            }
        }

        configEventTag();
    }

    private void configEventTag() {

        tagView.setOnTagLongClickListener(new TagView.OnTagLongClickListener() {
            @Override
            public void onTagLongClick(Tag tag, int i) {
                String[] parts = tag.text.split(" ");
                deleteItemListAdd(parts[0].trim());
                changeStateItem(tag.text);
                tagView.remove(i);
                addTags();
            }
        });
    }

    private void changeStateItem(String text) {
        for (Involved involved : Utils.listInvolucrados) {
            String nameOrigin = new String(involved.getName());
            String nameItem = new String(text);
            if (nameOrigin.compareTo(nameItem) == 0) {
                involved.setSwObject(false);
                return;
            }
        }
    }

    private void deleteItemListAdd(String name) {
        for (Involved involved : listAddItems) {
            String nameOrigin = new String(involved.getName());
            String nameItem = new String(name);
            if (nameOrigin.compareTo(nameItem) == 0) {
                listAddItems.remove(involved);
                return;
            }
        }
    }

    @OnClick(R.id.btnShowInvolved)
    void showInvolved() {
        showDialogInvolved();
    }

    @OnClick(R.id.btnShowRiesgo)
    void showRisk() {
        showDialogRisk();
    }

    private void showDialogRisk() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_DialogWhenLarge);

        dialog.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_risk_factor);
        RecyclerView recyclerRisk = (RecyclerView) dialog.findViewById(R.id.recyclerRisk);

        Button btnAceptar = (Button) dialog.findViewById(R.id.btnAceptarRisk);
        Button btnCancelar = (Button) dialog.findViewById(R.id.btnCancelarRisk);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                addTagsRisk();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        RiskAdapter riskAdapter = new RiskAdapter(context, listItemRisk, this);

        recyclerRisk.setAdapter(riskAdapter);
        recyclerRisk.setLayoutManager(new LinearLayoutManager(this));

        dialog.show();
    }

    private void addTagsRisk() {
        tagViewRisk.removeAllViewsInLayout();
        tagViewRisk.removeAllViews();
        tagViewRisk.removeAll();

        for (Risk risk : this.listItemRisk) {
            if (risk.getSwSelected()) {
                Tag tag = new Tag(risk.getName());
                tagViewRisk.addTag(tag);
            }
        }

        configEventTagRisk();
    }

    private void configEventTagRisk() {
        tagViewRisk.setOnTagLongClickListener(new TagView.OnTagLongClickListener() {
            @Override
            public void onTagLongClick(Tag tag, int i) {
                changeStateItemRisk(tag.text);
                tagViewRisk.remove(i);
                addTagsRisk();
            }
        });
    }

    private void changeStateItemRisk(String name) {
        for (Risk risk : listItemRisk) {
            String riskSource = new String(risk.getName());
            String riskOrigin = new String(name);
            if (riskSource.compareTo(riskOrigin) == 0) {
                risk.setSwSelected(false);
                return;
            }

        }
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
        if (position >= 0) {
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
            String nameRest = new String(listAddItems.get(i).getName());
            String nameParam = new String(name);
            if (nameRest.compareTo(nameParam) == 0)
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

    @Override
    public void onClickSwitchObject(Switch component, String name) {
        if (component.isChecked()) {
            addElement(name, component.isChecked());
            component.setText(component.getTextOn());
        }
        else {
            addElement(name, component.isChecked());
            component.setText(component.getTextOff());
        }

    }

    private void addElement(String name, Boolean state) {
        for (Involved involved : Utils.listInvolucrados) {
            String involvedSource = new String(involved.getName());
            String involvedOrigin = new String(name);

            if (involvedSource.compareTo(involvedOrigin) == 0) {
                involved.setSwObject(state);
                return;
            }
        }
    }

    @Override
    public void onclickSwith(TextView textView, Boolean state) {
        addItemRisk(textView.getText().toString(), state);
    }

    private void addItemRisk(String name, Boolean state) {
        for (int i = 0; i < listItemRisk.size(); i++) {
            String riskSource = new String(listItemRisk.get(i).getName());
            String riskOriginal = new String(name);
            if (riskSource.compareTo(riskOriginal) == 0) {
                listItemRisk.get(i).setSwSelected(state);
            }
        }
    }

}
