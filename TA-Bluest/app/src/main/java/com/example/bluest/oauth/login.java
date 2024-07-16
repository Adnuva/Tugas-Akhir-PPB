package com.example.bluest.oauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bluest.MainActivity;
import com.example.bluest.R;

public class login extends AppCompatActivity {
    EditText username,password;
    ImageButton buttonRegister;
    ImageButton buttonLogin;
    TextView coba;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.loginUsername);
        password=findViewById(R.id.loginPassword);
        buttonLogin=findViewById(R.id.loginButton);
        buttonRegister=findViewById(R.id.registerButton);

        coba=findViewById(R.id.coba);
        databaseHelper=new DatabaseHelper(this);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                if (user.equals("")||pass.equals("")){
                    Toast.makeText(login.this, "Ada Yang Terlewat, Masukkan Keseluruhan!", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkPassword = databaseHelper.checkPassword(user,pass);
                    if (checkPassword==true){
                        Toast.makeText(login.this, "Login Sukses!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(login.this, MainActivity.class);
//                        intent.putExtra("user", user);
                        startActivity(intent);
                    }else {
                        Toast.makeText(login.this, "Login Gagal, Kesalahan Password/Username!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,register.class));
            }
        });
    }
}