package com.emre.crypto.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.emre.crypto.R;
import com.emre.crypto.adapter.RecycleAdapter;
import com.emre.crypto.model.CryptoModel;
import com.emre.crypto.service.CryptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<CryptoModel> cryptoModels;
    private String Base_URL="https://raw.githubusercontent.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecycleAdapter recycleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        //https://api.coinpaprika.com/v1/tickers
       Gson gson=new GsonBuilder().setLenient().create();
        retrofit=new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
         loadData();
    }
    private void loadData(){
        CryptoAPI cryptoAPI=retrofit.create(CryptoAPI.class);
        Call<List<CryptoModel>> call=cryptoAPI.getData();
        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                  if(response.isSuccessful()){
                      List<CryptoModel> responseList=response.body();
                      cryptoModels=new ArrayList<>(responseList);
                      //RecyclerView and Adapter
                      recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                      recycleAdapter=new RecycleAdapter(cryptoModels);
                      recyclerView.setAdapter(recycleAdapter);
                  }
                 /* for(CryptoModel cryptoModel:cryptoModels){
                      System.out.println(cryptoModel.currency);
                      System.out.println(cryptoModel.price);
                  } */
            }
            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                   t.printStackTrace();
            }
        });
    }
}