package com.example.android.onlineshopping2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.onlineshopping2.Database.AppDatabase;
import com.example.android.onlineshopping2.Database.CustomerDao;
import com.example.android.onlineshopping2.Models.Customer;

public class AddCustomer extends AppCompatActivity {

    Button save;
    private AppDatabase database;
    private Customer customer;
    private CustomerDao customerDao;
    private int id;

    private EditText phone,gender,address,age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        defineDataSource();
        defineViews();

        if(!emptyValidation())
        {
            Toast.makeText(getApplicationContext(),"empty feilds",Toast.LENGTH_SHORT).show();

        }
        else {


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Customer customer1= fetchCustomerData();
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        customerDao.insertCustomer(customer1);
                        Intent intent=new Intent(AddCustomer.this,MainActivity.class);
                        intent.putExtra("phone",customer.getPhone());
                        intent.putExtra("address",customer.getAddress());
                        intent.putExtra("age",customer.getAge());
                        intent.putExtra("ACID",id);
                        startActivity(intent);
                    }
                });


            }

        });

        }

    }


    private void defineViews()
    {
        save=findViewById(R.id.customer_register);
        phone=findViewById(R.id.customer_phone);
        age=findViewById(R.id.customer_age);
        gender=findViewById(R.id.customer_gender);
        address=findViewById(R.id.customer_address);
    }
    private void defineDataSource()
    {
        database=AppDatabase.getsInstance(getApplicationContext());
        customerDao=database.getCustomerDao();
    }
    private Customer fetchCustomerData()
    {
        Intent intent=getIntent();
        if(intent.hasExtra("AccountId"))
        {

             id=intent.getIntExtra("AccountId",0);
            customer=new Customer(id,address.getText().toString(),phone.getText().toString(),age.getText().toString(),gender.getText().toString());
        }
       return customer;

    }
    private boolean emptyValidation()
    {
        if(TextUtils.isEmpty(phone.getText().toString())||TextUtils.isEmpty(gender.getText().toString())||TextUtils.isEmpty(age.getText().toString())
                ||TextUtils.isEmpty(address.getText().toString()))
        {
            return true;
        }
        else return false;

    }
}
