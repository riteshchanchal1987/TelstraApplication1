/**
 * @file ApiInterface.java
 * @brief This is the api interface class, it will contain all the Api call references
 * @author Ritesh
 * @date 18/10/2019
 */
package com.example.telstraapplication.network;

import com.example.telstraapplication.model.DropBoxContent;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("facts.json")
    Call<DropBoxContent> getContent();

}
