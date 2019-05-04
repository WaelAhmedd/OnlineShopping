package com.example.android.onlineshopping2.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.android.onlineshopping2.Models.Customer;

@Dao
public interface CustomerDao {

    @Query("SELECT * FROM customers WHERE customerId=:id")
    LiveData<Customer>getCustomerInfo(int id);

@Insert
    void insertCustomer(Customer  customer);
    }
