/**
 * @file ContentInfoDetailsModel.java
 * @brief This is model class for content list screen, it will handle all business logic.
 * @author Ritesh
 * @date 19/10/2019
 */


package com.example.telstraapplication.presenter;

import android.util.Log;

import com.example.telstraapplication.model.ContentInfo;
import com.example.telstraapplication.model.DropBoxContent;
import com.example.telstraapplication.network.ApiClient;
import com.example.telstraapplication.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentInfoDetailsModel implements ContentInfoDetailsContract.Model {

    private final String TAG = "ContentInfoDetailsModel";


    /**
     * This function will fetch content
     * @param onFinishedListener
     */
    @Override
    public void getContentInfoList(final OnFinishedListener onFinishedListener) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<DropBoxContent> call = apiService.getContent();

        call.enqueue(new Callback<DropBoxContent>() {
            @Override
            public void onResponse(Call<DropBoxContent> call, Response<DropBoxContent> response) {
                List<ContentInfo> contentInfoArrayList = response.body().getRows();
                String mainTitle = response.body().getMainTitle();
                Log.d(TAG, "contentInfo count : " + contentInfoArrayList.size());
                onFinishedListener.onFinished(mainTitle, contentInfoArrayList);
            }

            @Override
            public void onFailure(Call<DropBoxContent> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });

    }

     public boolean isSuccessful(DropBoxContent content) {
        return content.getRows() != null && content.getRows().size() > 0;
    }

    public String getErrorMessage() {
        return "Error in getting data. Please try again later.";
    }
}
