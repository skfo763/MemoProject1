package com.example.user.memoproject.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.user.memoproject.DateActivity;
import com.example.user.memoproject.R;

public class datefragment extends Fragment {

    private CalendarView calendarView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.date_fragment, container, false);
        calendarView = view.findViewById(R.id.datefragment_calendarview);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            private long touchPressedTime = 0;
            private long resetTime = 300; // 리셋 타임 설정 - 2초
            private Activity activity;

            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {

                if(System.currentTimeMillis() > touchPressedTime + resetTime){
                    touchPressedTime = System.currentTimeMillis();
                    return;
                }else {
                    Intent intent = new Intent(view.getContext(), DateActivity.class);
                    intent.putExtra("year", (String.valueOf(year)));
                    intent.putExtra("month", (String.valueOf(month)));
                    intent.putExtra("day", (String.valueOf(day)));
                    startActivity(intent);
                }

            }
        });
        getFragmentManager().beginTransaction().replace(R.id.datefragment_listview, new datelist_fragment()).commit();
        return view;

    }
}
