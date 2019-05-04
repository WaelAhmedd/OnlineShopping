package com.example.android.onlineshopping2.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.android.onlineshopping2.Models.Customer;
import com.example.android.onlineshopping2.Models.UserAccount;

@Database(entities = {UserAccount.class, Customer.class}, version = 55,exportSchema = false)
public  abstract  class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;
    private static final String DATABASE_NAME="OnlineShopping";

    public static AppDatabase getsInstance(Context context)
    {
        if(sInstance==null)
        {
            sInstance= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DATABASE_NAME).fallbackToDestructiveMigration()
                    .build();
        }
        return sInstance;
    }
    public abstract UserDao getUserDao();
    public abstract CustomerDao getCustomerDao();

}
