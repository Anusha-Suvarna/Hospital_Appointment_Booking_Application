package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages= {
            {"Aspirin", "", "", "", "50"},
            {"Ibuprofen", "", "", "", "50"},
            {"Acetaminophen (Paracetamol)", "", "", "", "50"},
            {"Lisinopril", "", "", "", "50"},
            {"Atorvastatin", "", "", "", "50"},
            {" Metformin", "", "", "", "50"},
            {"Amoxicillin", "", "", "", "50"},
            {" Omeprazole", "", "", "", "50"},

    };
    private String[] package_details= {
            "Pain Relief:Medications like ibuprofen and acetaminophen are used to relieve pain and reduce fever.\n" +
    "Blood Pressure Control:Drugs like lisinopril and hydrochlorothiazide help control high blood pressure.\n"+"Cholesterol Management: Atorvastatin is used to lower high cholesterol levels and reduce the risk of heart disease.\n"+
    "Diabetes Management:Metformin is prescribed to manage blood sugar levels in people with type 2 diabetes.\n"+"Antibiotic:Amoxicillin is an antibiotic used to treat various bacterial infections.\n"+
    "Gastrointestinal Issues:Omeprazole can be used to reduce stomach acid and treat conditions like acid reflux and ulcers.\n"+
    "Anti-Inflammatory:Prednisone is a corticosteroid that reduces inflammation and is used to treat various inflammatory conditions.\n"+
    "Asthma Management:Inhalers like albuterol are used to relieve asthma symptoms and open up airways.\n"+
    "Allergy Relief:Antihistamines like loratadine can alleviate allergy symptoms such as sneezing and itching.\n"+
    "Antidepressant:Medications like sertraline and fluoxetine are used to treat depression and various mood disorders."

    };
    HashMap<String,String>item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,btnGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst=findViewById(R.id.listViewBM);
        btnBack=findViewById(R.id.buttonBMCBack);
        btnGoToCart=findViewById(R.id.buttonBMToCart);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });

        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total cost:"+packages[i][4]+"/");
            list.add(item);

            sa= new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                    new int[]{R.id.line1_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
            lst.setAdapter(sa);

            lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent it=new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                    it.putExtra("text1",packages[i][0]);
                    it.putExtra("text2",package_details[0]);
                    it.putExtra("text3",packages[i][4]);
                    startActivity(it);
                }
            });


        }
    }

}