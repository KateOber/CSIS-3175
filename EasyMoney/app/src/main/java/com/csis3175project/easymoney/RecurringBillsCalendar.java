package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RecurringBillsCalendar extends AppCompatActivity {
    EMDatabase databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recurring_bills_calendar);

        List<EventDay> events = new ArrayList<>();
        databaseHelper = new EMDatabase(this);
        Cursor expenses = databaseHelper.getEXPENSEData();
        if(expenses.getCount()>0){
            while(expenses.moveToNext()){
<<<<<<< HEAD
                if (expenses.getString(1).equals("username")){
                    Calendar calendar = Calendar.getInstance();
                    String[] arr = expenses.getString(4).split("/");
                    int yy;
                    int mm;
                    String dd;
                    calendar.set(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
                    events.add(new EventDay(calendar, R.drawable.day_picker_item_background, Color.parseColor("#228B22")));
                }
            }}
=======
            if (expenses.getString(1).equals("username")){
                Calendar calendar = Calendar.getInstance();
                String[] arr = expenses.getString(4).split("/");
                int yy;
                int mm;
                String dd;
                calendar.set(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
                events.add(new EventDay(calendar, R.drawable.day_picker_item_background, Color.parseColor("#228B22")));
            }
        }}
>>>>>>> parent of 100111f (Merge branch 'main' of https://github.com/Koberpunk/CSIS-3175 into main)

        Button btnAddRecurring = findViewById(R.id.btn_addRecurringBills);
        Button backButton = findViewById(R.id.btnBackBills);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        List<Calendar> calendars = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 02, 7);
        calendars.add(calendar);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2021,02,15);
        calendars.add(calendar1);

        calendarView.setHighlightedDays(calendars);

        calendarView.setEvents(events);

        btnAddRecurring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecurringBillsCalendar.this, AddExpense.class));

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }
}