package com.makroid.result.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.makroid.result.informationclass.ListItem;
import com.makroid.result.R;

import java.util.List;


public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private int lastPosition = -1;
    private List<ListItem> listData;
    private LayoutInflater inflater;
    public ResultAdapter(List<ListItem> dataFromlist, Context c){
        this.context = c;
        inflater = LayoutInflater.from(c);
        this.listData = dataFromlist;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == 1) {
            view = inflater.inflate(R.layout.score_card_layout, parent, false);
            MyHolderSecond holderSecond = new MyHolderSecond(view);
            return holderSecond;
        }
        else{
            view = inflater.inflate(R.layout.list_row, parent, false);
            MyHolder holder = new MyHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder customHolder, int position) {
        ListItem item = listData.get(position);
        if(customHolder.getItemViewType() == 1){
            MyHolderSecond holder;
            holder = (MyHolderSecond) customHolder;
            holder.percentage.setText(item.getPercentage());
            holder.cPercentage.setText(item.getcPercentage());
            holder.uRank.setText(item.getuRank());
            holder.cRank.setText(item.getcRank());
            holder.credit.setText(item.getCredit());
            holder.total.setText(item.getTotal());
        }
            else{
            MyHolder holder;
            holder = (MyHolder) customHolder;
            holder.exam_layout.setText(item.getexam());
            holder.marks_layout.setText(item.getmarks());
            holder.internal.setText(item.getinternal());
            holder.external.setText(item.getexternal());
            Animation animation = AnimationUtils.loadAnimation(context,
                    (position > lastPosition) ? R.anim.up_from_bottom
                            : R.anim.down_from_top);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(listData.get(position).getmarks() == null)
            return 1;
        else
            return 0;
    }

    @Override
    public int getItemCount() {
        return listData.size()-1;
    }
    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView exam_layout,marks_layout,internal,external;

        MyHolder(View itemView) {
            super(itemView);
            exam_layout = (TextView)itemView.findViewById(R.id.exam_layout);
            marks_layout = (TextView)itemView.findViewById(R.id.marks_layout);
            internal = (TextView)itemView.findViewById(R.id.internal);
            external = (TextView)itemView.findViewById(R.id.external);
        }
    }
    public class MyHolderSecond extends RecyclerView.ViewHolder{
        private TextView percentage,cPercentage,total,credit,cRank,uRank;
        MyHolderSecond(View itemView) {
            super(itemView);
            percentage = (TextView) itemView.findViewById(R.id.score_percentage);
            cPercentage = (TextView) itemView.findViewById(R.id.score_cpercentage);
            total = (TextView) itemView.findViewById(R.id.score_total);
            credit = (TextView) itemView.findViewById(R.id.score_credit);
            cRank = (TextView) itemView.findViewById(R.id.score_crank);
            uRank = (TextView) itemView.findViewById(R.id.score_urank);
        }
    }

}
