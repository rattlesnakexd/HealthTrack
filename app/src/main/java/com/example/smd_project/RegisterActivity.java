package com.example.smd_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText username, name, password, confirm_password;
    Button register_btn;
    TextView login;

    User newUser;
    DataBase db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.editTextRegisterUsername);
        name = findViewById(R.id.editTextRegisterFullName);
        password = findViewById(R.id.editTextRegisterPassword);
        confirm_password = findViewById(R.id.editTextRegisterConfirmPassword);
        register_btn = findViewById(R.id.buttonRegisterButton);
        login = findViewById(R.id.textView3Register);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newUser = new User();
                newUser.setUsername(username.getText().toString());
                newUser.setName(name.getText().toString());
                newUser.setPassword(password.getText().toString());
                String confrim_pass = confirm_password.getText().toString();

                if (newUser.getUsername().length() == 0 || newUser.getName().length() == 0 || newUser.getPassword().length() == 0 || confrim_pass.length() == 0){
                    Toast.makeText(getApplicationContext(), "Complete the entire form " , Toast.LENGTH_SHORT).show();
                }
                else if (newUser.getPassword().length() < 6){
                    Toast.makeText(getApplicationContext(), "Password should be atleast 6 characters long " , Toast.LENGTH_SHORT).show();
                }
                else if (newUser.getPassword().compareTo(confrim_pass) != 0) {
                    Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_SHORT).show();
                }
                else{
                    db = new DataBase(getApplicationContext());
                    db.Register_User(newUser);
                    Toast.makeText(getApplicationContext(), "Registered ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });



    }
}