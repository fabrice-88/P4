package com.fabrice.mareeu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fabrice.mareeu.databinding.ActivityAddReunionBinding;
import com.fabrice.mareeu.di.Di;
import com.fabrice.mareeu.model.Reunion;
import com.fabrice.mareeu.service.ReunionApiService;

import java.util.Calendar;
import java.util.Date;

public class AddReunionActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityAddReunionBinding binding;
    private final ReunionApiService mReunionApiService = Di.getReunionApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI(){
        binding = ActivityAddReunionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        setDate();
        setHeure();
        getSupportActionBar().setTitle("Nouvelle réunion");
    }

    private void setButton(){
        binding.submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if (view == binding.submitButton){
            onSubmit();
        }
    }

    private void onSubmit(){
        String salle = binding.salle.getEditText().getText().toString();
        String sujet = binding.sujet.getEditText().getText().toString();
        String mail = binding.mail.getEditText().getText().toString();
        String date = binding.date.getText().toString();
        String heure = binding.heure.getText().toString();

        if (salle.isEmpty()){
            binding.salle.setError("Entrez une salle");
            return;
        }
        if (sujet.isEmpty()){
            binding.sujet.setError("Entrez un sujet");
            return;
        }
        if (mail.isEmpty()){
            binding.mail.setError("Entrez une adresse mail");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            binding.mail.setError("Entrer un email valid");
            return;
        }

        mReunionApiService.createReunion(new Reunion(sujet, salle, mail, date, heure));
        Toast.makeText(this, "Réunion ajoutée !", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    private void setDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        binding.date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DatePickerDialog date = new DatePickerDialog(AddReunionActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day){
                        String date = day + "/" + (month + 1) + "/" + year;
                        binding.date.setText(date);
                    }
                }, year, month, day);
                date.show();
            }
        });
    }

    private void setHeure(){
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        binding.heure.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TimePickerDialog heure = new TimePickerDialog(AddReunionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute){
                        String heure = hour + "H" + minute;
                        binding.heure.setText(heure);
                    }
                }, hour, minute, true);
                heure.show();
            }
        });
    }
}