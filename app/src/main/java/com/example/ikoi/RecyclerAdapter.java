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
    private Context context;
    private ArrayList<Riwayat> riwayat;

    public RecyclerAdapter(Context c, ArrayList<Riwayat> r){
        context = c;
        riwayat = r;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDay.setText(riwayat.get(position).getDay());
        holder.tvTime.setText(riwayat.get(position).getTime());
        holder.tvStatus.setText(riwayat.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return (riwayat != null) ? riwayat.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDay, tvTime, tvStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay = (TextView) itemView.findViewById(R.id.tv_day);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvStatus = (TextView) itemView.findViewById(R.id.tv_status);
        }
    }
}
