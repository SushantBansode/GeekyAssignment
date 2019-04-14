package com.example.assignment.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.assignment.R;
import com.example.assignment.presenter.RecyclerItemClickListener;

import java.util.ArrayList;

public class ListAdaptor extends RecyclerView.Adapter<ListAdaptor.EmployeeViewHolder> {

    private ArrayList<String> dataList;
    private RecyclerItemClickListener recyclerItemClickListener;

    public ListAdaptor(ArrayList<String> dataList , RecyclerItemClickListener recyclerItemClickListener) {
        this.dataList = dataList;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }


    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txtNoticeTitle.setText(dataList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(dataList.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView txtNoticeTitle, txtNoticeBrief, txtNoticeFilePath;

        EmployeeViewHolder(View itemView) {
            super(itemView);
            txtNoticeTitle =  itemView.findViewById(R.id.txt_notice_title);


        }
    }
}