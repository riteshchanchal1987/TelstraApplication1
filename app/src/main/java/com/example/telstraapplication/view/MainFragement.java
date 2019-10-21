/**
 * @file MainFragment.java
 * @brief This fragment is responsible for showing all data  in the listview.
 * @author Ritesh
 * @date 21/10/2019
 */

package com.example.telstraapplication.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.telstraapplication.R;
import com.example.telstraapplication.adapter.ContentInfoAdapter;
import com.example.telstraapplication.model.ContentInfo;
import com.example.telstraapplication.presenter.ContentInfoDetailsContract;
import com.example.telstraapplication.presenter.ContentInfoDetailsPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainFragement extends Fragment implements ContentInfoDetailsContract.View, SwipeRefreshLayout.OnRefreshListener{



    private static final String TAG = "MainFragment";
    private ContentInfoDetailsPresenter contentInfoDetailsPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private ContentInfoAdapter adapter;
    private List<ContentInfo> contentInfoList ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        initializeUI(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onRefresh();
    }

    /**
     * This method will initialize the UI components
     */
    private void initializeUI(View view) {
        recyclerView = view.findViewById(R.id.recyclerview);
        progressBar = view.findViewById(R.id.pb_loading);
        swipeRefreshLayout = view.findViewById(R.id.layout_swipeRefresh);
        layoutManager = new LinearLayoutManager(((MainActivity)this.getActivity()),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        contentInfoList = new ArrayList<>();
        adapter = new ContentInfoAdapter( ((MainActivity)this.getActivity()), contentInfoList);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * This method is called when user swipe to refresh/load the content
     */
    @Override
    public void onRefresh() {
        //Initializing presenter
        contentInfoDetailsPresenter = new ContentInfoDetailsPresenter(this);
        contentInfoDetailsPresenter.requestDataFromServer();
    }

    @Override
    public void showProgress() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(String mainTitle, List<ContentInfo> contentInfos) {
        ((MainActivity)this.getActivity()).setTitle(mainTitle);
        contentInfoList.clear();
        contentInfoList.addAll(contentInfos);
        Log.d(TAG , "contentInfoList size : " + contentInfoList.size());
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(((MainActivity)this.getActivity()), getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        contentInfoDetailsPresenter.onDestroy();
    }

}
