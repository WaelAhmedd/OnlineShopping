package com.example.android.onlineshopping2.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "customers")
public class Customer {


    @ForeignKey(entity = UserAccount.class,parentColumns = "id",childColumns = "customerId")
    @ColumnInfo(name = "customerId")
    public int customerId;

    public String address;
@PrimaryKey
    @NonNull
    public String phone;


    public Customer(int customerId, String address, String phone, String age, String gender) {
        this.customerId = customerId;
        this.address = address;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {

        return age;
    }

    public String getGender() {
        return gender;
    }

    private String age;
    private String gender;




    public int getCustomerId() {
        return customerId;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setCustomerId(int customerId) {

        this.customerId = customerId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



}
