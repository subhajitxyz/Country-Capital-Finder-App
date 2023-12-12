package com.mastercodiing.retrofitpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.mastercodiing.retrofitpractice.adapter.CountryAdapter;
import com.mastercodiing.retrofitpractice.model.CountryModel;
import com.mastercodiing.retrofitpractice.model.Result;
import com.mastercodiing.retrofitpractice.service.GetCountryDataService;
import com.mastercodiing.retrofitpractice.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CountryAdapter countryAdapter;
    ArrayList<CountryModel> countries;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView=findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        GetCountries();



    }

    private void filterList(String newText) {
        ArrayList<CountryModel>filteredList=new ArrayList<>();
        for(CountryModel model:countries){
            if(model.getName().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(model);

            }

        }
        if(filteredList.isEmpty()){
            Toast.makeText(this,"No data found",Toast.LENGTH_LONG).show();
        }else{
            countryAdapter.setFilteredList(filteredList);
        }

    }

    private Object GetCountries() {
        GetCountryDataService getCountryDataService= RetrofitInstance.getService();
        Call<Result> call=getCountryDataService.getResult();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                Result result= response.body();

                if(result!=null && result.getResult()!=null){
                    countries=(ArrayList<CountryModel>) result.getResult();
                }

                ViewData();

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
        return countries;
    }

    private void ViewData() {
        recyclerView=findViewById(R.id.recyclerView);
        countryAdapter=new CountryAdapter(countries);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(countryAdapter);
        recyclerView.addItemDecoration(new
                DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

    }
}