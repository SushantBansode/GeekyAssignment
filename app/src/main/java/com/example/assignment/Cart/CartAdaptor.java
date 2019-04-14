package com.example.assignment.Cart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.assignment.model.DatabaseHelper;
import com.example.assignment.R;
import com.example.assignment.activities.DetailActivity;
import com.example.assignment.model.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



/**
 * Created by user on 11/22/2017.
 */

public class CartAdaptor extends RecyclerView.Adapter<CartAdaptor.CartViewHolder>{

     public List<Order> listData=new ArrayList<>();
     public Context context;
     DatabaseHelper dbHelper;

    public CartAdaptor(List<Order> listData1, Context context) {
        this.listData = listData1;
        this.dbHelper = new DatabaseHelper(context.getApplicationContext());
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R
                .layout.cart_menu,parent,false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CartViewHolder holder, final int position) {
       // TextDrawable drawable=TextDrawable.builder().buildRound(""+listData.get(position).getQuantity(), Color.GREEN);
      //  holder.img_cart_count.setImageDrawable(drawable);

       //price(listData.get(position));

        Locale locale=new Locale("en","US");
        NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);
        int price=(Integer.parseInt(listData.get(position).getPrice()));
        holder.txt_price.setText(fmt.format(price));
        holder.txt_cart_name.setText(listData.get(position).getProductName());


        holder.deleteRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(listData.get(position));
            }
        });
    }

    private void delete(Order order) {
        int Currentposition=listData.indexOf(order);
        listData.remove(Currentposition);
        notifyItemRemoved(Currentposition);
        dbHelper.cleanCart(order.getProductId(),order.getCategoryId());

        ((Activity)context).finish();
        ((Activity)context).overridePendingTransition( 0, 0);
         context.startActivity(((Activity)context).getIntent());
        ((Activity)context).overridePendingTransition( 0, 0);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

  class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

     public TextView txt_cart_name, txt_price;
     public Button deleteRow;


     public CartViewHolder(View itemView) {
         super(itemView);
         txt_cart_name = (TextView) itemView.findViewById(R.id.cart_item_name);
         txt_price = (TextView) itemView.findViewById(R.id.cart_item_price);
         deleteRow = (Button) itemView.findViewById(R.id.delete);
         itemView.setOnClickListener(this);

     }

      @Override
      public void onClick(View view) {
          Intent intent=new Intent(context, DetailActivity.class);
          intent.putExtra("category_id",listData.get(getAdapterPosition()).getCategoryId());
          intent.putExtra("product_id",listData.get(getAdapterPosition()).getProductId());
          context.startActivity(intent);
      }
  }

}
