package com.makroid.result.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.makroid.result.model.RankModel;
import com.makroid.result.MainActivity;
import com.makroid.result.R;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.MyHolder> {
    private List<RankModel> listData;
    private LayoutInflater inflater;
    private Context context;
    private int lastPosition = -1;
    public RankAdapter(List<RankModel> dataFromlist, Context c){
        this.context = c;
        inflater = LayoutInflater.from(c);
        this.listData = dataFromlist;
    }
    @Override
    public RankAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_row, parent, false);
        return new MyHolder(view);
    }
    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        RankModel item = listData.get(position);
        holder.rank.setText("#"+item.getRank());
        holder.crank.setText("#"+item.getCrank());
        holder.percentage.setText("Percetage : " + String.valueOf(item.getPercentage()) + "%");
        holder.creditpercentage.setText("Credit Percentage : " + String.valueOf(item.getCreditPercentage()) + "%");
        holder.name.setText(item.getName());
        holder.collegename.setText(item.getCollege());
        holder.marks.setText("Total Marks: "+String.valueOf(item.getTotalMarks()));
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        if(position>1) {
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }

    }
    @Override
    public int getItemCount() {
        return listData.size();
    }



    public void setFilter(List<RankModel> countryModels) {
        listData = new ArrayList<>();
        listData.addAll(countryModels);
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name,marks,percentage,creditpercentage,rank,collegename,crank;
        private CardView cardView;
        public MyHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            crank = (TextView) itemView.findViewById(R.id.crank);
            rank = (TextView) itemView.findViewById(R.id.rank);
            marks = (TextView)itemView.findViewById(R.id.marks_layout);
            creditpercentage = (TextView)itemView.findViewById(R.id.creditpercentage);
           percentage = (TextView)itemView.findViewById(R.id.percentage);
            name = (TextView)itemView.findViewById(R.id.name_layout);
            collegename = (TextView)itemView.findViewById(R.id.college_name);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }

        @Override
        public void onClick(View view) {
            Intent intent =  new Intent(context,MainActivity.class);

            Log.e(TAG, "onClick: " + listData.get(getAdapterPosition()).getRoll());
            intent.putExtra("roll",listData.get(getAdapterPosition()).getRoll());
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            ( (Activity)(context)).finish();
        }
    }
    @Override
    public void onViewDetachedFromWindow(MyHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }
}
