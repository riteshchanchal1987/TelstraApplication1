
package com.example.telstraapplication.network;

import com.example.telstraapplication.model.DropBoxContent;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("facts.json")
    Call<DropBoxContent> getContent();

}
