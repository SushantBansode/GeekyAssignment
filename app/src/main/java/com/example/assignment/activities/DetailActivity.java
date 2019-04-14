package com.example.assignment.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment.model.DatabaseHelper;
import com.example.assignment.R;
import com.example.assignment.model.GetDataIntractorImpl;
import com.example.assignment.presenter.MainContract;
import com.example.assignment.presenter.MainPresenterImpl;
import com.example.assignment.model.Order;
import com.example.assignment.model.Product;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements MainContract.MainView{


    private MainContract.presenter presenter;
    ImageView imageView;
    int category_id,product_id;
    TextView detailTextView;

    int[] img1={R.drawable.oven,R.drawable.tv,R.drawable.vcleaner
    };
    int[] img2={R.drawable.table,R.drawable.chair,R.drawable.almirah
    };

    int[][] arrImg={img1,img2};
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView=findViewById(R.id.image);
        detailTextView=findViewById(R.id.product_detail);

        Bundle bundle = getIntent().getExtras();

        //Extract the data…
         category_id = bundle.getInt("category_id");
         product_id = bundle.getInt("product_id");


        imageView.setImageResource(arrImg[category_id][product_id]);

        presenter = new MainPresenterImpl((MainContract.MainView) this, new GetDataIntractorImpl());
        presenter.requestDetailData(category_id,product_id);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatabaseHelper(getBaseContext()).addToCart(new Order(category_id,product_id,
                        toolbar.getTitle().toString(),
                        detailTextView.getText().toString()

                ));
                Snackbar.make(view, "Product is added to cart", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    @Override
    public void setDataToRecyclerView(ArrayList<String> noticeArrayList) {

    }


    @Override
    public void setDataToDetailView(Product noticeArrayList) {

        toolbar.setTitle(noticeArrayList.getProduct_name());
        detailTextView.setText(noticeArrayList.getProduct_detail());

    }


}
