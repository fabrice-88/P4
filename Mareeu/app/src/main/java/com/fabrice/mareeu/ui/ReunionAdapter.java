package com.fabrice.mareeu.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.fabrice.mareeu.R;
import com.fabrice.mareeu.di.Di;
import com.fabrice.mareeu.model.Reunion;
import com.fabrice.mareeu.service.ReunionApiService;

import java.util.List;

public class ReunionAdapter extends Adapter<ReunionAdapter.ReunionViewHolder> {
    private final List<Reunion> reunions;

    public ReunionApiService mApiService = Di.getReunionApiService();

    public ReunionAdapter(List<Reunion> reunion) {
        this.reunions = reunion;
    }

    @NonNull
    public ReunionAdapter.ReunionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reunion, parent, false);
        return new ReunionAdapter.ReunionViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ReunionAdapter.ReunionViewHolder holder, int position) {
        Reunion reunion = (Reunion)this.reunions.get(position);
        holder.salle.setText(reunion.getSujet() + " - " + reunion.getHeure() + " - " + reunion.getSalle());
        holder.mail.setText(reunion.getMail());
        holder.delButton.setOnClickListener((v) -> {
            reunions.remove(reunion);
            mApiService.deleteReunion(reunion);
            notifyDataSetChanged();
        });
    }

    public int getItemCount() {
        return this.reunions.size();
    }

    public static class ReunionViewHolder extends ViewHolder {
        private final TextView salle;
        private final TextView mail;
        private final ImageButton delButton;

        public ReunionViewHolder(View view) {
            super(view);
            this.salle = (TextView)view.findViewById(R.id.salle);
            this.mail = (TextView)view.findViewById(R.id.mail);
            this.delButton = (ImageButton)view.findViewById(R.id.delete);
        }
    }
}
