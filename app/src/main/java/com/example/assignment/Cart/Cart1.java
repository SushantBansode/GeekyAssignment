package com.example.assignment.Cart;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment.model.DatabaseHelper;
import com.example.assignment.R;
import com.example.assignment.model.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class Cart1 extends Activity {

    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
    TextView textTotalPrice,gstPrice;
    Button payment;
    List<Order> cart=new ArrayList<>();
    CartAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart1);

        recycler_menu=(RecyclerView)findViewById(R.id.listCart);
        payment=(Button) findViewById(R.id.placeOrder);
        recycler_menu.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);
        textTotalPrice=(TextView)findViewById(R.id.total);

        loadOrderList();

    }

    public void loadOrderList() {

        cart=new DatabaseHelper(this).getCarts();
        adaptor=new CartAdaptor(cart,this);
        recycler_menu.setAdapter(adaptor);

        int finalTotal=0;
        for(Order order:cart)

            finalTotal+= (Integer.parseInt(order.getPrice()));
            Locale locale = new Locale("en", "US");
            NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
            textTotalPrice.setText(fmt.format(finalTotal));


    }

    public void placeOrder(View view) {

        //Intent i1=new Intent(Cart1.this, PlaceOrder.class);
       // startActivity(i1);
    }



}