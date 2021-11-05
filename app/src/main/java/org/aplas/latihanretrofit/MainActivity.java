package org.aplas.latihanretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.aplas.latihanretrofit.databinding.ActivityMainBinding;

import java.util.List;

import org.aplas.latihanretrofit.models.Repo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import org.aplas.latihanretrofit.services.GithubServices;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;
    GithubServices service;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GithubServices.class);
     }

    public void handleSend(View view){
        Call<List<Repo>> repos = service.listRepos(binding.getUsername());

        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> repoList) {
                List<Repo> results = repoList.body();
                if(results != null && repoList.isSuccessful()){
                    binding.setRepo (results.get(0));
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.e(MainActivity.class.getCanonicalName(), t.getMessage());
            }
        });
    }
}