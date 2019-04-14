package com.example.assignment.model;

import android.content.Context;

import com.example.assignment.presenter.MainContract;
import com.example.assignment.presenter.MainPresenterImpl;

import java.util.ArrayList;


public class GetDataIntractorImpl implements MainContract.GetDataIntractor {

    DatabaseHelper databaseHelper;
    ArrayList<String> list=new ArrayList<>() ;


    @Override
    public void getNoticeArrayList1(MainPresenterImpl mainPresenter, Context mainView, int category_id) {

        databaseHelper=new DatabaseHelper(mainView);
        list=databaseHelper.getProductlist(category_id);
        mainPresenter.onFinished(list);

    }

    @Override
    public void getDetail(MainPresenterImpl mainPresenter, Context mainView, int category_id, int product_id) {

        databaseHelper=new DatabaseHelper(mainView);
        Product product=databaseHelper.getProductDetail(category_id+1,product_id+1);
        mainPresenter.onFinished1(product);

    }

    @Override
    public void getNoticeArrayList(final OnFinishedListener onFinishedListener, Context context) {

        databaseHelper=new DatabaseHelper(context);
        list=databaseHelper.getCategorylist();
        onFinishedListener.onFinished(list);

    }

}
