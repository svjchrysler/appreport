package gob.ice.crashreportsc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gob.ice.crashreportsc.R;
import gob.ice.crashreportsc.interfaces.OnClick;
import gob.ice.crashreportsc.models.Involved;

public class InvolvedAdapter extends RecyclerView.Adapter<InvolvedAdapter.ViewHolder> {

    private Context context;
    private List<Involved> listInvolved;
    private List<Involved> listInvolvedAdd;
    private OnClick onClick;

    public InvolvedAdapter(Context context, List<Involved> listInvolved, OnClick onClick, List<Involved> listInvolvedAdd) {
        this.context = context;
        this.listInvolved = listInvolved;
        this.listInvolvedAdd = listInvolvedAdd;
        this.onClick = onClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_involved, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Involved involvedOrigin = this.listInvolved.get(position);
        Involved involved = updateLisElementExist(position);

        if (!involvedOrigin.getSw()) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            holder.linearContentBtn.setVisibility(View.GONE);
            holder.linearContentTxt.setLayoutParams(params);
        } else {
            if (involvedOrigin.getSwGenero()) {
                holder.swGenero.setVisibility(View.VISIBLE);
            }
            else {
                holder.swGenero.setVisibility(View.GONE);
            }
        }

        holder.txtName.setText(involvedOrigin.getName());

        if (involved != null)
            holder.txtCount.setText(String.valueOf(involved.getCount()));

        holder.setOnClickListener(holder.txtCount, holder.txtName.getText().toString(), onClick);
        holder.setOnClickListenerSwitch(holder.swGenero, onClick);

    }

    private Involved updateLisElementExist(int position) {
        for (Involved involved: this.listInvolvedAdd) {
            if (involved.getName().toString().equals(listInvolved.get(position).getName().toString())) {
                return involved;
            }
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return this.listInvolved.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtName)
        TextView txtName;

        @BindView(R.id.txtCount)
        TextView txtCount;

        @BindView(R.id.imgAdd)
        ImageView imgAdd;

        @BindView(R.id.imgRemove)
        ImageView imgRemove;

        @BindView(R.id.linearContentBtn)
        LinearLayout linearContentBtn;

        @BindView(R.id.linearContentTxt)
        LinearLayout linearContentTxt;

        @BindView(R.id.linearCenter)
        LinearLayout linearCenter;

        @BindView(R.id.swgenero)
        Switch swGenero;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnClickListener(final View component, final String text , final OnClick onClick) {

            imgAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.onClick(component, 1, text);
                }
            });

            imgRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.onClick(component, -1, text);
                }
            });

        }

        public void setOnClickListenerSwitch(final View component, final OnClick onClick) {
            swGenero.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.onClickSwitch(component);
                }
            });
        }
    }
}
