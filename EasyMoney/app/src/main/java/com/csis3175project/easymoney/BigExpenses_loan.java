package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BigExpenses_loan extends AppCompatActivity {

    String[] banks = {"TD bank","RBC", "Scotiabank"};
    int[] images = {R.drawable.tdwhite,R.drawable.rbc,R.drawable.scotiabank};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<3;i++){
            HashMap<String,String> hashMap = new HashMap<String,String>();
            hashMap.put("txt",banks[i]);
            hashMap.put("images",Integer.toString(images[i]));
            aList.add(hashMap);
        }

        String[] from = {"images","txt"};
        int[] to = {R.id.image,R.id.txtTravel};

        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(),aList,
                R.layout.bigex_loan_listview_layout,from,to);

        ListView listView = findViewById(R.id.listView);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://www.td.com/")));
                        break;
                    case 1:
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://www.rbcroyalbank.com/personal.html")));
                        break;
                    case 2:
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://www.scotiabank.com/ca/en/personal.html")));
                        break;

                }
            }
        });
    }
}