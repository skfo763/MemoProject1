package com.example.user.memoproject.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.memoproject.DateShowActivity;
import com.example.user.memoproject.Models.DateModels;
import com.example.user.memoproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class datelist_fragment extends Fragment {

    List<DateModels.Dates> datesList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.datelist_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.datelistfragment_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        recyclerView.setAdapter(new GetDateListAdapter());
        return view;
    }

    private class GetDateListAdapter extends RecyclerView.Adapter {

        public GetDateListAdapter() {
            FirebaseDatabase.getInstance().getReference().child("datelist").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    datesList.clear();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        DateModels.Dates dates1 = snapshot.getValue(DateModels.Dates.class);
                        datesList.add(dates1);
                    }
                    notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            ((CustomViewHolder)holder).textView_title.setText(datesList.get(position).eventname);
            ((CustomViewHolder)holder).textView_date.setText(datesList.get(position).startday + " / " + datesList.get(position).startmonth + " / " + datesList.get(position).startyear + " ~ " + datesList.get(position).enddate);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DateShowActivity.class);
                    intent.putExtra("startdate", datesList.get(position).startday + " / " + datesList.get(position).startmonth + " / " + datesList.get(position).startyear);
                    intent.putExtra("enddate", datesList.get(position).enddate);
                    intent.putExtra("location", datesList.get(position).location);
                    intent.putExtra("eventname", datesList.get(position).eventname);
                    intent.putExtra("fatality", datesList.get(position).fatality);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return datesList.size();
        }
    }

    private class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView textView_title;
        public TextView textView_date;

        public CustomViewHolder(View view) {
            super(view);
            textView_title = view.findViewById(R.id.dateitem_title);
            textView_date = view.findViewById(R.id.dateitem_date);
        }
    }
}
