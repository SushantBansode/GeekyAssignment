package com.example.assignment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.assignment.Cart.Cart1;
import com.example.assignment.R;
import com.example.assignment.adapter.ListAdaptor;
import com.example.assignment.model.GetDataIntractorImpl;
import com.example.assignment.presenter.MainContract;
import com.example.assignment.presenter.MainPresenterImpl;
import com.example.assignment.presenter.RecyclerItemClickListener;
import com.example.assignment.model.Product;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity
        implements MainContract.MainView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private MainContract.presenter presenter;

    FloatingActionButton cart;
    int category_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeToolbarAndRecyclerView();
        initProgressBar();

        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        category_id = bundle.getInt("category_id");
        presenter = new MainPresenterImpl(this, new GetDataIntractorImpl());
        presenter.requestDataFromServer1(category_id+1);


        cart=(FloatingActionButton)findViewById(R.id.cart);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ProductListActivity.this, Cart1.class);
                startActivity(intent);

            }
        });


    }


    /**
     * Initializing Toolbar and RecyclerView
     */
    private void initializeToolbarAndRecyclerView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ProductListActivity.this);
        recyclerView.setLayoutManager(layoutManager);


    }


    /**
     * Initializing progressbar programmatically
     * */
    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
    }


    /**
     * RecyclerItem click event listener
     * */
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(String notice, int position) {


            Intent intent=new Intent(ProductListActivity.this, DetailActivity.class);
            intent.putExtra("category_id",category_id);
            intent.putExtra("product_id",position);
            startActivity(intent);


        }
    };





    @Override
    public void setDataToRecyclerView(ArrayList<String> noticeArrayList) {

        ListAdaptor adapter = new ListAdaptor(noticeArrayList , recyclerItemClickListener);

        recyclerView.setAdapter(adapter);

    }


    @Override
    public void setDataToDetailView(Product noticeArrayList) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // if (id == R.id.action_refresh) {
        //   presenter.onRefreshButtonClick();
        // }

        return super.onOptionsItemSelected(item);
    }

}
