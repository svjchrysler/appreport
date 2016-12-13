package gob.ice.crashreportsc.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gob.ice.crashreportsc.R;
import gob.ice.crashreportsc.models.Involved;

public class InvolvedAdapter extends RecyclerView.Adapter<InvolvedAdapter.ViewHolder> {

    private Context context;
    private List<Involved> listInvolved;


    public InvolvedAdapter(Context context, List<Involved> listInvolved) {
        this.context = context;
        this.listInvolved = listInvolved;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_involved, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (!this.listInvolved.get(position).getSw()) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            holder.linearContentBtn.setVisibility(View.GONE);
            holder.linearContentTxt.setLayoutParams(params);
        } else {
            if (this.listInvolved.get(position).getSwGenero()) {
                holder.swGenero.setVisibility(View.VISIBLE);
            }
            else {
                holder.swGenero.setVisibility(View.GONE);
            }
        }



        holder.txtName.setText(this.listInvolved.get(position).getName());
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
    }
}
