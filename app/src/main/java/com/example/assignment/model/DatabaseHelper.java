package com.example.assignment.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.assignment.model.Order;
import com.example.assignment.model.Product;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteAssetHelper {

	private static final int New_DB_Version = 1;
	private static final String DB_Name = "products.db";
	SharedPreferences shared, pref;
	int key, keys;

	public DatabaseHelper(Context context) {
		super(context, DB_Name, null, New_DB_Version);
		shared = PreferenceManager.getDefaultSharedPreferences(context);

	}

	public ArrayList<String> getCategorylist() {
		ArrayList<String> namelist = new ArrayList<String>();
		String query = "SELECT categories FROM categories";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				namelist.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		return namelist;
	}



	public Cursor getAllData(String s1)
	{
		SQLiteDatabase db2 = this.getWritableDatabase();
		String que1 = "SELECT * FROM tblhn WHERE hword ='"+s1+"'";
		Cursor cursor2=db2.rawQuery(que1, null);
		return cursor2;
	}

	public Cursor getAllData1(String s1)
	{
		SQLiteDatabase db2 = this.getWritableDatabase();
		String que1 = "SELECT rowid FROM tblhn WHERE hword ='"+s1+"'";
		String que2 = "SELECT * FROM tblmeans WHERE rowid ='"+que1+"'";
		Cursor cursor2=db2.rawQuery(que2, null);
		return cursor2;
	}

	public ArrayList<String> getProductlist(int category_id) {
		ArrayList<String> namelist = new ArrayList<String>();
		String query = "SELECT product_name FROM products where category_id ="+category_id;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				namelist.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		return namelist;
	}

	public ArrayList<Integer> getProductid(int category_id) {

		ArrayList<Integer> namelist = new ArrayList<Integer>();
		String query = "SELECT product_id FROM products where category_id ="+category_id;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				namelist.add(cursor.getInt(0));
			} while (cursor.moveToNext());
		}
		return namelist;
	}

	public Product getProductDetail(int category_id, int product_id) {

		String product_detail=null,product_name = null;
		Product product=null;

		String query = "SELECT product_name,product_details FROM products where category_id ="+category_id +" and product_id="+product_id;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				product_detail=(cursor.getString(1));
				product_name=(cursor.getString(0));
				product=new Product(product_name,product_detail);
				Log.d("detail",product_detail);
			} while (cursor.moveToNext());
		}
		return product;
	}


	public List<Order> getCarts()
	{
		SQLiteDatabase db=getReadableDatabase();
		SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
		String que2 = "SELECT * FROM OrderDetail";
		Cursor c = db.rawQuery(que2, null);


		final List<Order> result=new ArrayList<>();
		if(c.moveToFirst())
		{
			do{
				result.add(new Order(c.getInt(c.getColumnIndex("category_id")),
						c.getInt(c.getColumnIndex("product_id")),
						c.getString(c.getColumnIndex("product_name")),
						c.getString(c.getColumnIndex("product_price"))

				));
			}while ((c.moveToNext()));
		}
		return result;
	}

	public  void addToCart(Order order)
	{
		SQLiteDatabase db=getReadableDatabase();
		String query=String.format("INSERT INTO OrderDetail(category_id,product_id,product_name,product_price) VALUES ('%s','%s','%s','%s');",
				order.getCategoryId(),
				order.getProductId(),
				order.getProductName(),
				order.getPrice()
		);

		db.execSQL(query);

	}

	public void cleanCart(int i,int j)
	{
		SQLiteDatabase db=getWritableDatabase();

		String que2 = "DELETE FROM OrderDetail WHERE product_id ='" + i + "' AND category_id ='"+j+"'";

		db.execSQL(que2);

	}


	public int getOrders(int i,int j) {
		SQLiteDatabase db3 = this.getWritableDatabase();

		String que2 = "SELECT * FROM OrderDetail WHERE product_id ='" + i + "'AND category_id ='"+j+"'";
		Cursor cursor2 = db3.rawQuery(que2, null);

		if (cursor2 != null && cursor2.moveToFirst()) {
			return cursor2.getInt(4);
		} else {
			return 0;
		}
	}
}
