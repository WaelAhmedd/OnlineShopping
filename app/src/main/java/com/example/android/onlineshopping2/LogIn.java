package com.example.android.onlineshopping2;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.onlineshopping2.Database.AppDatabase;
import com.example.android.onlineshopping2.Database.UserDao;
import com.example.android.onlineshopping2.Models.UserAccount;

import java.util.List;

public class LogIn extends AppCompatActivity {

    private EditText emailInput,passwordInput;
    private Button signInBTN,signUpBTN;
   private AppDatabase database;

    private UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        defineDataSources();
        defineViews();

        signUpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LogIn.this,SignUp.class);
                startActivity(intent);
            }
        });

        signInBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(!emptyValidation())
               {
                       String email,password;
                       email=emailInput.getText().toString();
                       password=passwordInput.getText().toString();



                     final LiveData < UserAccount> account=database.getUserDao().getUser(email,password);
                     account.observe(LogIn.this, new Observer<UserAccount>() {
                         @Override
                         public void onChanged(@Nullable UserAccount userAccount) {
                             if(account!=null)
                             {
                                 Intent intent=new Intent(LogIn.this,MainActivity.class);
                                 intent.putExtra("ACID", userAccount.getId());
                                 startActivity(intent);
                                 finish();
                             }
                             else{
                                 Toast.makeText(LogIn.this, "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show();
                             }

                         }
                     });






               }
               else
               {
                   Toast.makeText(LogIn.this,"empty Feilds",Toast.LENGTH_SHORT).show();
               }

            }
        });
    }

    void defineViews()
    {
        emailInput=findViewById(R.id.emailinput);
        passwordInput=findViewById(R.id.passwordinput);
        signInBTN=findViewById(R.id.btSignIn);
        signUpBTN=findViewById(R.id.btSignUp);
    }

    void defineDataSources()
    {
        //database and the dao
        database=AppDatabase.getsInstance(getApplicationContext());
        userDao=database.getUserDao();


    }
    private boolean emptyValidation()
    {
        if(TextUtils.isEmpty(emailInput.getText().toString())||TextUtils.isEmpty(passwordInput.getText().toString()))
        {
            return true;
        }
        else
            return false;
    }
}
