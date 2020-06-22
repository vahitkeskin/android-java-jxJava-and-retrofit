package com.vahitkeskin.countryandlanguage.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vahitkeskin.countryandlanguage.R;
import com.vahitkeskin.countryandlanguage.adapter.RecyclerViewAdapter;
import com.vahitkeskin.countryandlanguage.model.CountryModel;
import com.vahitkeskin.countryandlanguage.service.CountryServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CountryModel> countryModels;
    private String BASE_URL = "https://raw.githubusercontent.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        uploadToData();
    }

    private void uploadToData() {

        CountryServices countryServices = retrofit.create(CountryServices.class);

        Call<List<CountryModel>> call = countryServices.getMyData();
        call.enqueue(new Callback<List<CountryModel>>() {
            @Override
            public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {
                if (response.isSuccessful()) {
                    List<CountryModel> responseList = response.body();
                    countryModels = new ArrayList<>(responseList);

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(countryModels);
                    recyclerView.setAdapter(recyclerViewAdapter);

                    int sayac = 0;
                    for (CountryModel countryModel : countryModels) {
                        sayac++;
                        System.out.println(sayac+". Country  : "+countryModel.myCountry);
                        System.out.println(sayac+". Language : "+countryModel.myLanguage);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CountryModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Hata : "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countryModels.clear();
    }
}