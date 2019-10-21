/**
 * @file ContentInfoDetailsContract.java
 * @brief This is the contract class, it will have interfaces for model, view and presenter.
 * @author Ritesh
 * @date 19/10/2019
 */

package com.example.telstraapplication.presenter;

import com.example.telstraapplication.model.ContentInfo;

import java.util.List;

public class ContentInfoDetailsContract {


    public interface Model {

        interface OnFinishedListener {
            void onFinished(String mainTitle, List<ContentInfo> contentInfoList);

            void onFailure(Throwable t);
        }

        void getContentInfoList(OnFinishedListener onFinishedListener);

    }


   public interface View {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(String mainTitle, List<ContentInfo> contentInfoList);

        void onResponseFailure(Throwable throwable);

    }


    public interface Presenter {

        void onDestroy();

        void requestDataFromServer();

    }

}
