package com.example.android.onlineshopping2;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.onlineshopping2.Database.AppDatabase;
import com.example.android.onlineshopping2.Database.CustomerDao;
import com.example.android.onlineshopping2.Models.Customer;
import com.example.android.onlineshopping2.Models.UserAccount;

public class MainActivity extends AppCompatActivity {

    AppDatabase database;
    CustomerDao customerDao;
    TextView t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defineDataSources();
        defineViews();
        Intent inten=getIntent();
       int id= inten.getIntExtra("ACID",0);
        retriveData(id);
    }
    void defineDataSources()
    {
        //database and the dao
        database=AppDatabase.getsInstance(getApplicationContext());
        customerDao=database.getCustomerDao();
    }
    void defineViews()
    {
        t1=findViewById(R.id.textView);
        t3=findViewById(R.id.textView3);
        t2=findViewById(R.id.textView2);
    }
    void retriveData(int id)
    {
        LiveData<Customer>customerLiveData=database.getCustomerDao().getCustomerInfo(id);
        customerLiveData.observe(this, new Observer<Customer>() {
            @Override
            public void onChanged(@Nullable Customer customer) {
                assert customer != null;
                t1.setText(customer.getAddress());
                t2.setText(customer.getPhone());

            }
        });
    }
}
