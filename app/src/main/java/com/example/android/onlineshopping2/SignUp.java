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
import com.example.android.onlineshopping2.Database.UserDao;
import com.example.android.onlineshopping2.Models.UserAccount;

public class SignUp extends AppCompatActivity {


  private   EditText name,lastname,email,password;
  private Button register,cancel;
    private AppDatabase appDatabase;
    private UserAccount userAccount;
    private UserDao userDao;
    private  int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
            defineViews();
            defineDataSource();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this,LogIn.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emptyValidation())
                {
                    Toast.makeText(getApplicationContext(),"empty feilds",Toast.LENGTH_SHORT).show();
                }
                else
                {
                  userAccount=extractFromViews();
                  AppExecutors.getInstance().diskIO().execute(new Runnable() {
                      @Override
                      public void run() {
                          userDao.insert(userAccount);
                      }
                  });
                 Intent intent=new Intent(SignUp.this,AddCustomer.class);
                    intent.putExtra("AccountId",id);

                 startActivity(intent);
                }
            }
        });
    }

    private void defineViews()
    {
        name=findViewById(R.id.nameinput);
        lastname=findViewById(R.id.lastnameinput);
        email=findViewById(R.id.emailinput_S);
        password=findViewById(R.id.passwordinput_S);
        register=findViewById(R.id.register);
        cancel=findViewById(R.id.btCancel);

    }
    private UserAccount extractFromViews(){
        String name_S,lastname_S,email_S,password_S;
        name_S=name.getText().toString();
        lastname_S=lastname.getText().toString();
        email_S=email.getText().toString();
        password_S=password.getText().toString();
        UserAccount userAccount=new UserAccount(id,name_S,lastname_S,email_S,password_S);
        id+=1;
        return userAccount;

    }
    private void defineDataSource()
    {
        appDatabase=AppDatabase.getsInstance(getApplicationContext());
        userDao=appDatabase.getUserDao();

    }

    private boolean emptyValidation()
    {
        if(TextUtils.isEmpty(name.getText().toString())||TextUtils.isEmpty(lastname.getText().toString())||TextUtils.isEmpty(email.getText().toString())
        ||TextUtils.isEmpty(password.getText().toString()))
        {
            return true;
        }
        else return false;

    }
}
