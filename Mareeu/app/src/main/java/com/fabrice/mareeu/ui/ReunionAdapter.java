package com.fabrice.mareeu.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fabrice.mareeu.R;
import com.fabrice.mareeu.event.DeleteReunionEvent;
import com.fabrice.mareeu.model.Reunion;
import com.google.android.material.textfield.TextInputEditText;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ReunionAdapter extends RecyclerView.Adapter<ReunionAdapter.ReunionViewHolder> {

    private final List<Reunion> reunions;

    public ReunionAdapter(List<Reunion> reunion) {
        this.reunions = reunion;
    }

    @NonNull
    @Override
    public ReunionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reunion, parent, false);
        return new ReunionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReunionViewHolder holder, int position) {
        Reunion reunion = reunions.get(position);
        holder.salle.setText(reunion.getSujet()+" - "+reunion.getHeure()+" - " +reunion.getSalle());
        holder.mail.setText(reunion.getMail());
        holder.delButton.setOnClickListener(v -> EventBus.getDefault().post(new DeleteReunionEvent(reunion)));
    }


    @Override
    public int getItemCount() {
        return reunions.size();
    }

    public static class ReunionViewHolder extends RecyclerView.ViewHolder{

        private final TextView salle;
        private final TextView mail;
        private final ImageButton delButton;

        public ReunionViewHolder(View view){
            super(view);
            salle = view.findViewById(R.id.item_list_tittle);
            mail = view.findViewById(R.id.item_list_mail);
            delButton = view.findViewById(R.id.item_list_delete_button);
        }
    }
}
