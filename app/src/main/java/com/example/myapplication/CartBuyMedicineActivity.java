package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class CartBuyMedicineActivity extends AppCompatActivity {
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    ArrayAdapter<String> adp;
    TextView tvTotal;
    ListView lst;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton,timeButton,btnCheckout,btnBack;
    private String[][] packages={};

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);
        dateButton=findViewById(R.id.buttonBMCartDate);
        btnCheckout=findViewById(R.id.buttonBMToCart);
        btnBack=findViewById(R.id.buttonBMCBack);
        tvTotal = findViewById(R.id.textViewBMCTotalCost);
        lst=findViewById(R.id.listViewBMCart);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();

        Database db = new Database(getApplicationContext(),"healthcare",null,1);

        float totalAmount = 0;
        ArrayList dbData = db.getCartData(username,"Lab");
        Toast.makeText(getApplicationContext(),""+dbData,Toast.LENGTH_LONG).show();

        packages = new String[dbData.size()][];
        for(int i=0;i<packages.length;i++){
            packages[i] = new String[5];
        }

        for(int i=0;i<dbData.size();i++){
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(Pattern.quote("$"));
            packages[i][0] = strData[0];
            packages[i][4] = "Cost : "+strData[1]+"/";
            totalAmount = totalAmount + Float.parseFloat(strData[1]);
        }

        tvTotal.setText("Total Cost : "+totalAmount);

        list = new ArrayList();
        for(int i=0;i<=packages.length;i++){
            item = new HashMap<String,String>();
            item.put( "Line1", packages[i][0]);
            item.put( "Line2", packages[i][1]);
            item.put( "Line3", packages[i][2]);
            item.put( "Line4", packages[i][3]);
            item.put( "Line5", packages[i][4]);
            list.add(item);
        }
            adp=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,R.id.listViewBMCart,list);
//        sa = new SimpleAdapter(this, list,
//                R.layout.multi_lines,
//                new String[] {"line1","line2","line3","line4","line5"},
//                new int[] {R.id.line1_a,R.id.line_b, R.id.line_c, R.id.line_d,R.id.line_e});

        lst.setAdapter(adp);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartBuyMedicineActivity.this,BuyMedicineActivity.class));
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(CartBuyMedicineActivity.this,BuyMedicineBookActivity.class);
                it.putExtra("price",tvTotal.getText());
                it.putExtra("date",dateButton.getText());
                it.putExtra("time",timeButton.getText());
                startActivity(it);
            }
        });

        //datePicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });


    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dayOfMonth=dayOfMonth+1;
                dateButton.setText(dayOfMonth+"/"+month+"/"+year);
            }
        };
        Calendar cal = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cal = Calendar.getInstance();
        }
        int year = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            year = cal.get(Calendar.YEAR);
        }
        int month = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            month = cal.get(Calendar.MONTH);
        }
        int day = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            day = cal.get(Calendar.DAY_OF_MONTH);
        }

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog= new DatePickerDialog(this,style,dateSetListener,year,month,day);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
        }

    }

}
