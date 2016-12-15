package gob.ice.crashreportsc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gob.ice.crashreportsc.R;
import gob.ice.crashreportsc.interfaces.OnClickRisk;
import gob.ice.crashreportsc.models.Risk;

/**
 * Created by SALGUERO on 14/12/2016.
 */

public class RiskAdapter extends RecyclerView.Adapter<RiskAdapter.ViewHolder> {

    private Context context;
    private List<Risk> listRisk;
    private OnClickRisk onClickRisk;


    public RiskAdapter(Context context, List<Risk> listRisk, OnClickRisk onClickRisk) {
        this.context = context;
        this.listRisk = listRisk;
        this.onClickRisk = onClickRisk;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_risk_factor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (listRisk.get(position).getSwSelected())
            holder.checkRisk.setChecked(true);
        else
            holder.checkRisk.setChecked(false);

        holder.txtName.setText(listRisk.get(position).getName());
        holder.setOnClickSwitch(holder.txtName, this.onClickRisk);
    }

    @Override
    public int getItemCount() {
        return this.listRisk.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtNameRisk)
        TextView txtName;

        @BindView(R.id.checkRisk)
        CheckBox checkRisk;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void setOnClickSwitch(final TextView textView, final OnClickRisk onClickRisk) {
            checkRisk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickRisk.onclickSwith(textView, checkRisk.isChecked());
                }
            });
        }
    }
}
