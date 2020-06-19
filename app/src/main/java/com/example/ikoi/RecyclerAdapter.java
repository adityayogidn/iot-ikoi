package com.example.ikoi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    Context context;
    ArrayList<Riwayat> riwayat;

    public RecyclerAdapter(Context c, ArrayList<Riwayat> r){
        context = c;
        riwayat = r;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_list,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDate.setText(riwayat.get(position).getDate());
        holder.tvTime.setText(riwayat.get(position).getTime());
        holder.tvStatus.setText(riwayat.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return riwayat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDate, tvTime, tvStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvStatus = (TextView) itemView.findViewById(R.id.tv_status);
        }
    }
}
