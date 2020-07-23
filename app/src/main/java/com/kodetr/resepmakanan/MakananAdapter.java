package com.kodetr.resepmakanan;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.MakananViewHolder> {

    private OnClikMakananInterface onClik;
    private List<ResepMakanan> makananList;

    public MakananAdapter(OnClikMakananInterface onClik, List<ResepMakanan> makananList) {
        this.makananList = makananList;
        this.onClik = onClik;
    }

    @NonNull
    @Override
    public MakananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_activity, parent, false);
        return new MakananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MakananViewHolder holder, final int position) {
        final ResepMakanan makanan = makananList.get(position);

        holder.tvNamaMakanan.setText(makanan.getNama_makanan());
        holder.tvHarga.setText("Rp " + makanan.getHarga());
        holder.tvDiskon.setText(makanan.getDiskon() + "%");
        Picasso.get().load(makanan.getGambar()).into(holder.ivGambar);

        holder.llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClik.onClikMakanan(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return makananList.size();
    }

    class MakananViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LinearLayout llContainer;
        private ImageView ivGambar;
        private TextView tvNamaMakanan, tvHarga, tvDiskon;

        public MakananViewHolder(@NonNull View itemView) {
            super(itemView);
            llContainer = itemView.findViewById(R.id.llContainer);
            ivGambar = itemView.findViewById(R.id.ivGambar);
            tvNamaMakanan = itemView.findViewById(R.id.tvNamaMakanan);
            tvHarga = itemView.findViewById(R.id.tvHarga);
            tvDiskon = itemView.findViewById(R.id.tvDiskon);
            llContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClik.onClikMakanan(getLayoutPosition());
        }
    }

    interface OnClikMakananInterface {
        void onClikMakanan(int position);
    }
}
