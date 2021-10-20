package com.fabrice.mareeu.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fabrice.mareeu.databinding.ActivityMainBinding;
import com.fabrice.mareeu.R;
import com.fabrice.mareeu.di.Di;
import com.fabrice.mareeu.model.Reunion;
import com.fabrice.mareeu.service.ReunionApiService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private ActivityMainBinding binding;
    private List<Reunion> mReunions = new ArrayList();
    private final ReunionApiService mReunionApiService = Di.getReunionApiService();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initUi();
    }

    private void initUi() {
        this.binding = ActivityMainBinding.inflate(this.getLayoutInflater());
        View view = this.binding.getRoot();
        this.setContentView(view);
        this.initData();
        this.setButton();
        this.initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        this.binding.recyclerview.setLayoutManager(layoutManager);
        this.binding.recyclerview.addItemDecoration(new DividerItemDecoration(this.binding.recyclerview.getContext(), layoutManager.getOrientation()));
        this.binding.recyclerview.setAdapter(new ReunionAdapter(this.mReunions));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.filtrer_salle);
        SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            public boolean onQueryTextChange(String newText) {
                MainActivity.this.mReunions.clear();
                MainActivity.this.mReunions.addAll(MainActivity.this.mReunionApiService.getFiltreSalle(newText));
                MainActivity.this.binding.recyclerview.setAdapter(new ReunionAdapter(MainActivity.this.mReunions));
                return false;
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.filtrer_date:
                this.filtrerParDate();
                return true;
            case R.id.filtrer_salle:
                return true;
            case R.id.reset:
                this.resetFilter();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void filtrerParDate() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog mDatePickerDialog = new DatePickerDialog(this, (view, year, month, day) -> {
            Calendar cal = Calendar.getInstance();
            cal.set(day, month, year);
            String date = day + "/" + (month + 1) + "/" + year;
            this.binding.recyclerview.setAdapter(new ReunionAdapter(this.mReunionApiService.getFiltreDate(date)));
        }, calendar.get(1), calendar.get(2), calendar.get(5));
        mDatePickerDialog.show();
    }

    private void resetFilter() {
        this.mReunions.clear();
        this.mReunions.addAll(this.mReunionApiService.getReunion());
        this.initData();
    }

    private void initData() {
        this.mReunions = new ArrayList(this.mReunionApiService.getReunion());
        this.binding.recyclerview.setAdapter(new ReunionAdapter(this.mReunions));
    }

    private void setButton() {
        this.binding.add.setOnClickListener(this);
    }

    public void onClick(View view) {
        this.startActivity(new Intent(this, AddReunionActivity.class));
    }

    public void onResume() {
        super.onResume();
        this.initData();
    }
}
