package com.fabrice.mareeu.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.fabrice.mareeu.databinding.ActivityAddReunionBinding;
import com.fabrice.mareeu.di.Di;
import com.fabrice.mareeu.model.Reunion;
import com.fabrice.mareeu.service.ReunionApiService;
import java.util.Calendar;

public class AddReunionActivity extends AppCompatActivity implements OnClickListener {
    private ActivityAddReunionBinding binding;
    private final ReunionApiService mReunionApiService = Di.getReunionApiService();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initUI();
    }

    private void initUI() {
        this.binding = ActivityAddReunionBinding.inflate(this.getLayoutInflater());
        View view = this.binding.getRoot();
        this.setContentView(view);
        this.setButton();
        this.setDate();
        this.setHeure();
        this.getSupportActionBar().setTitle("Nouvelle réunion");
    }

    private void setButton() {
        this.binding.submitButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == this.binding.submitButton) {
            this.onSubmit();
        }
    }

    private void onSubmit() {
        String salle = this.binding.salle.getEditText().getText().toString();
        String sujet = this.binding.sujet.getEditText().getText().toString();
        String mail = this.binding.mail.getEditText().getText().toString();
        String date = this.binding.date.getText().toString();
        String heure = this.binding.heure.getText().toString();
        if (salle.isEmpty()) {
            this.binding.salle.setError("Entrez une salle");
        } else if (sujet.isEmpty()) {
            this.binding.sujet.setError("Entrez un sujet");
        } else {
            if (mail.isEmpty()) {
                this.binding.mail.setError("Entrez une adresse mail");
            } else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                this.binding.mail.setError("Entrer un email valid");
                return;
            }

            this.mReunionApiService.createReunion(new Reunion(sujet, salle, mail, date, heure));
            Toast.makeText(this, "Réunion ajoutée !", Toast.LENGTH_SHORT).show();
            this.startActivity(new Intent(this, MainActivity.class));
            this.finish();
        }
    }

    private void setDate() {
        Calendar cal = Calendar.getInstance();
        final int year = cal.get(1);
        final int month = cal.get(2);
        final int day = cal.get(5);
        this.binding.date.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DatePickerDialog date = new DatePickerDialog(AddReunionActivity.this, new OnDateSetListener() {
                    public void onDateSet(DatePicker view, int yearx, int monthx, int dayx) {
                        String date = dayx + "/" + (monthx + 1) + "/" + yearx;
                        AddReunionActivity.this.binding.date.setText(date);
                    }
                }, year, month, day);
                date.show();
            }
        });
    }

    private void setHeure() {
        Calendar cal = Calendar.getInstance();
        final int hour = cal.get(11);
        final int minute = cal.get(12);
        this.binding.heure.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TimePickerDialog heure = new TimePickerDialog(AddReunionActivity.this, new OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourx, int minutex) {
                        String heure = hourx + "H" + minutex;
                        AddReunionActivity.this.binding.heure.setText(heure);
                    }
                }, hour, minute, true);
                heure.show();
            }
        });
    }
}
