package com.example.assignment.presenter;


import android.content.Context;

import com.example.assignment.model.Product;

import java.util.ArrayList;



public interface MainContract {


    interface presenter{

        void onDestroy();

        void requestDataFromServer();

        void requestDataFromServer1(int category_id);

        void requestDetailData(int category_id,int product_id);

        void onFinished1(Product product_detail);
    }


    interface MainView {

        void setDataToRecyclerView(ArrayList<String> noticeArrayList);

        void setDataToDetailView(Product noticeArrayList);

    }


    interface GetDataIntractor {

        void getNoticeArrayList1(MainPresenterImpl mainPresenter, Context mainView, int category_id);

        void getDetail(MainPresenterImpl mainPresenter, Context mainView, int category_id, int product_id);

        interface OnFinishedListener {
            void onFinished(ArrayList<String> noticeArrayList);

        }

        void getNoticeArrayList(OnFinishedListener onFinishedListener, Context context);
    }

}
