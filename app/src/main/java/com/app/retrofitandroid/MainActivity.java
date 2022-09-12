package com.app.retrofitandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.app.retrofitandroid.adapter.CustomRecyclerAdapter;
import com.app.retrofitandroid.api.ApiService;
import com.app.retrofitandroid.api.callback.CallbackResponse;
import com.app.retrofitandroid.databinding.ActivityMainBinding;
import com.app.retrofitandroid.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<User> itemList;
    CustomRecyclerAdapter adapter;
    Retrofit retrofit;
    ApiService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initRetrofit();
        initRecyclerView();
        loadUserList();

    }

    private void loadUserList() {

        Call<CallbackResponse> call = service.getUserList();
        call.enqueue(new Callback<CallbackResponse>() {
            @Override
            public void onResponse(Call<CallbackResponse> call, Response<CallbackResponse> response) {
                // success
                if(response.isSuccessful())
                {
                    CallbackResponse res = response.body();
                    itemList = (ArrayList<User>) res.getUserList();

                    if(itemList.size()>0){
                        adapter.setItems(itemList);
                    }
                }

            }

            @Override
            public void onFailure(Call<CallbackResponse> call, Throwable t) {
                // failure
            }
        });
    }

    private void initRetrofit() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiService.class);
    }

    private void initRecyclerView() {

        itemList = new ArrayList<>();
        adapter = new CustomRecyclerAdapter(this, itemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        binding.recyclerItem.setLayoutManager(layoutManager);
        binding.recyclerItem.setAdapter(adapter);

    }
}