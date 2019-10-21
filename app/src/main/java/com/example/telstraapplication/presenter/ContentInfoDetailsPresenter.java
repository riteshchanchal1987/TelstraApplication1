/**
 * @file ContentInfoDetailsPresenter.java
 * @brief This is the presenter class for contentInfo details functionality, it will act as
 * an intermediate between views and model
 * @author Ritesh
 * @date 19/10/2019
 */

package com.example.telstraapplication.presenter;

import com.example.telstraapplication.model.ContentInfo;

import java.util.List;

public class ContentInfoDetailsPresenter implements ContentInfoDetailsContract.Presenter, ContentInfoDetailsContract.Model.OnFinishedListener {


    private ContentInfoDetailsContract.View infoDetailView;
    private ContentInfoDetailsContract.Model infoDetailsModel;



    public ContentInfoDetailsPresenter (ContentInfoDetailsContract.View infoDetailView) {
        this.infoDetailView = infoDetailView;
        infoDetailsModel = new ContentInfoDetailsModel();
    }

    @Override
    public void onFinished(String mainTitle , List<ContentInfo> contentInfoList) {

        infoDetailView.setDataToRecyclerView(mainTitle, contentInfoList);
        if (infoDetailView != null) {
            infoDetailView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {

        infoDetailView.onResponseFailure(t);
        if (infoDetailView != null) {
            infoDetailView.hideProgress();
        }
    }

    @Override
    public void onDestroy() {
        this.infoDetailView = null;
    }

    @Override
    public void requestDataFromServer() {
        if (infoDetailView != null) {
            infoDetailView.showProgress();
        }
        infoDetailsModel.getContentInfoList(this);
    }
    }

