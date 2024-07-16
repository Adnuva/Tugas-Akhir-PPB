package com.example.bluest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluest.R;
import com.example.bluest.data.Place;
import com.example.bluest.data.Pulau;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PulauAdapter extends RecyclerView.Adapter<PulauAdapter.PulauViewHolder> {
    private List<Pulau> pulauList;
    public interface OnItemClickListener {
        void onItemClick(Pulau pulau);
    }
    private PulauAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    public PulauAdapter(List<Pulau> pulauList) {
        this.pulauList = pulauList;
    }
    @NonNull
    @Override
    public PulauAdapter.PulauViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pulau, parent, false);
        return new PulauViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PulauAdapter.PulauViewHolder holder, int position) {
        Pulau pulau = pulauList.get(position);
        holder.textPulau.setText(pulau.nama);
        Picasso.get().load(pulau.foto).into(holder.imagePulau);
        holder.cardPulau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(pulau);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pulauList.size();
    }

    public class PulauViewHolder extends RecyclerView.ViewHolder {
        TextView textPulau;
        ImageView imagePulau;
        RelativeLayout cardPulau;
        public PulauViewHolder(@NonNull View itemView) {
            super(itemView);
            textPulau = itemView.findViewById(R.id.textPulau);
            imagePulau = itemView.findViewById(R.id.imagePulau);
            cardPulau = itemView.findViewById(R.id.cardPulau);
        }
    }
}
