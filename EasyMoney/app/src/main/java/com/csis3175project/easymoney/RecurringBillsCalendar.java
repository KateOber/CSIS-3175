package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;



import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import com.csis3175project.easymoney.R;

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
    }
}