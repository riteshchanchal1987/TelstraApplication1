/**
 * @file MainActivity.java
 * @brief This activity is responsible for showing all data  in the listview.
 * @author Ritesh
 * @date 18/10/2019
 */
package com.example.telstraapplication.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.telstraapplication.model.ContentInfo;
import com.example.telstraapplication.R;
import com.example.telstraapplication.adapter.ContentInfoAdapter;
import com.example.telstraapplication.presenter.ContentInfoDetailsContract;
import com.example.telstraapplication.presenter.ContentInfoDetailsPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContentInfoDetailsContract.View, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "MainActivity";
    private ContentInfoDetailsPresenter contentInfoDetailsPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private ContentInfoAdapter adapter;
    private List<ContentInfo> contentInfoList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUI();
        onRefresh();
    }

    /**
     * This method will initialize the UI components
     */
    private void initializeUI() {
        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.pb_loading);
        swipeRefreshLayout = findViewById(R.id.layout_swipeRefresh);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        contentInfoList = new ArrayList<>();
        adapter = new ContentInfoAdapter(this, contentInfoList);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

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
       setTitle(mainTitle);
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
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contentInfoDetailsPresenter.onDestroy();
    }

}
