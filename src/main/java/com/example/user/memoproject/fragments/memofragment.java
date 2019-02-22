package com.example.user.memoproject.fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.memoproject.MemoShowActivity;
import com.example.user.memoproject.Models.MemoModels;
import com.example.user.memoproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class memofragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.memo_fragment, container, false);
        RecyclerView recyclerview = view.findViewById(R.id.itemmemo_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        recyclerview.setAdapter(new MyLayoutAdapter());

        return view;
    }

    private class MyLayoutAdapter extends RecyclerView.Adapter {

        List<MemoModels.Memos> memosArrayList = new ArrayList<>();

        public MyLayoutAdapter() {

            FirebaseDatabase.getInstance().getReference().child("memolist").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    memosArrayList.clear();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        MemoModels.Memos memos1 = snapshot.getValue(MemoModels.Memos.class);
                        memosArrayList.add(memos1);
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

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

            ((CustomViewHolder)holder).textView_title.setText(memosArrayList.get(position).title);
            ((CustomViewHolder)holder).textView_time.setText(memosArrayList.get(position).time);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MemoShowActivity.class);
                    intent.putExtra("destinationuid",memosArrayList.get(position).title);
                    intent.putExtra("time", memosArrayList.get(position).time);
                    intent.putExtra("memo", memosArrayList.get(position).memo);
                    ActivityOptions activityOptions = ActivityOptions.makeCustomAnimation(view.getContext(), R.anim.from_right, R.anim.to_left);
                    startActivity(intent, activityOptions.toBundle());
                }
            });
        }

        @Override
        public int getItemCount() {
            return memosArrayList.size();
        }
    }

    private class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView textView_title;
        public TextView textView_time;

        public CustomViewHolder(View view) {
            super(view);
            textView_title = view.findViewById(R.id.item_title);
            textView_time = view.findViewById(R.id.item_time);
        }
    }
}

