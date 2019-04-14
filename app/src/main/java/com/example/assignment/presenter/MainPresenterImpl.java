package com.example.assignment.presenter;


import android.content.Context;

import com.example.assignment.model.Product;

import java.util.ArrayList;


public class MainPresenterImpl implements MainContract.presenter, MainContract.GetDataIntractor.OnFinishedListener{

    private MainContract.MainView mainView;
    private MainContract.GetDataIntractor getNoticeIntractor;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetDataIntractor getNoticeIntractor) {
        this.mainView = mainView;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void onDestroy() {

        mainView = null;

    }


    @Override
    public void requestDataFromServer() {
        getNoticeIntractor.getNoticeArrayList(this, (Context) mainView);
    }

    @Override
    public void requestDataFromServer1(int category_id) {
        getNoticeIntractor.getNoticeArrayList1(this, (Context) mainView,category_id);


    }

    @Override
    public void requestDetailData(int category_id, int product_id) {
        getNoticeIntractor.getDetail(this, (Context) mainView,category_id,product_id);


    }

    @Override
    public void onFinished1(Product product_detail) {

        if(mainView != null){
            mainView.setDataToDetailView(product_detail);
        }

    }


    @Override
    public void onFinished(ArrayList<String> noticeArrayList) {
        if(mainView != null){
            mainView.setDataToRecyclerView(noticeArrayList);
        }
    }




}
