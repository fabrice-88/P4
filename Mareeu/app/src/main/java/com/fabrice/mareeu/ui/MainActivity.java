package com.fabrice.mareeu.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fabrice.mareeu.R;
import com.fabrice.mareeu.databinding.ActivityMainBinding;
import com.fabrice.mareeu.di.Di;
import com.fabrice.mareeu.event.DeleteReunionEvent;
import com.fabrice.mareeu.model.Reunion;
import com.fabrice.mareeu.service.ReunionApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding binding;
    private List<Reunion> mReunions = new ArrayList<>();
    private final ReunionApiService mReunionApiService = Di.getReunionApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initUi();
    }

    private void initUi(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initData();
        setButton();
        initRecyclerView();
    }

    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
        binding.recyclerview.addItemDecoration(new DividerItemDecoration(binding.recyclerview.getContext(),
                layoutManager.getOrientation()));
        binding.recyclerview.setAdapter(new ReunionAdapter(mReunions));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.filtre_lieu);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText){
                mReunions.clear();
                mReunions.addAll(mReunionApiService.getFiltreSalle(newText));
                /*binding.recyclerview.getAdapter().notifyDataSetChanged();*/
                binding.recyclerview.setAdapter(new ReunionAdapter(mReunions));
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.filtre_lieu:
                return true;
            case R.id.filtre_date:
                filtrerParDate();
                return true;
            case R.id.filtre_reset:
                resetFilter();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void filtrerParDate(){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog mDatePickerDialog;
        mDatePickerDialog = new DatePickerDialog(MainActivity.this,
                (view, year, month, day) -> {
                    Calendar cal = Calendar.getInstance();
                    cal.set(day, month, year);
                    String date = day + "/" + (month + 1) +"/" + year;
                    binding.recyclerview.setAdapter (new ReunionAdapter(mReunionApiService.getFiltreDate(date)));
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.show();
    }

    private void resetFilter(){
        mReunions.clear();
        mReunions.addAll(mReunionApiService.getReunion());
        initData();
    }

    private void initData(){
        mReunions = new ArrayList<>(mReunionApiService.getReunion());
        binding.recyclerview.setAdapter(new ReunionAdapter(mReunions));
    }

    private void setButton(){
        binding.startAddActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        startActivity(new Intent(this,AddReunionActivity.class));
    }

    @Override
    public void onResume(){
        super.onResume();
        initData();
    }

    @Override
    public void onStart(){
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop(){
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteNeighbour(DeleteReunionEvent event){
        mReunionApiService.deleteReunion(event.reunion);
        initData();
    }
}