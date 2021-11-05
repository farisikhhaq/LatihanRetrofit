package org.aplas.latihanretrofit.services;

import java.util.List;

import org.aplas.latihanretrofit.models.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubServices {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
