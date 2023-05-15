package com.example.smd_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button login_btn;
    TextView register;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.editTextRegisterFullName);
        password = findViewById(R.id.editTextRegisterPassword);
        login_btn = findViewById(R.id.buttonRegisterButton);
        register = findViewById(R.id.textView3Register);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                db = new DataBase(getApplicationContext());
                if(pass.length() == 0 || user.length() ==0){
                    Toast.makeText(getApplicationContext(), "Enter Username and Password", Toast.LENGTH_SHORT).show();
                }
                else{
                    String name = db.Login_User(user,pass);
                    if ( name != ""){
                        Toast.makeText(getApplicationContext(), "Welcome " + name , Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("SharePrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name", name);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "User Not Found " , Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}