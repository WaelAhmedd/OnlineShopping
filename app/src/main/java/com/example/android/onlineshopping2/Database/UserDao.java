package com.example.android.onlineshopping2.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.android.onlineshopping2.Models.UserAccount;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users where email= :mail and password= :password")
   LiveData< UserAccount>getUser(String mail, String password);

@Insert
    void insert(UserAccount user);

}
