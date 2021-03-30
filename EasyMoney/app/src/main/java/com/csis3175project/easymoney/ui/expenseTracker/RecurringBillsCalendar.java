package com.csis3175project.easymoney.ui.expenseTracker;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recurring_bills_calendar);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        List<Calendar> calendars = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 02, 7);
        calendars.add(calendar);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2021,02,15);
        calendars.add(calendar1);

        calendarView.setHighlightedDays(calendars);

        List<EventDay> events = new ArrayList<>();
        events.add(new EventDay(calendar, R.drawable.day_picker_item_background, Color.parseColor("#228B22")));

        calendarView.setEvents(events);
    }
}