package com.example.bluest.oauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bluest.R;

public class register extends AppCompatActivity {

    EditText username,password,confirmPassword;
    ImageButton buttonRegister;
    ImageButton buttonLogin;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.registerUsername);
        password=findViewById(R.id.registerPassword);
        confirmPassword=findViewById(R.id.registerConfirmPassword);
        buttonRegister=findViewById(R.id.registerButton);
        buttonLogin=findViewById(R.id.loginButton);
        databaseHelper=new DatabaseHelper(this);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String conPass = confirmPassword.getText().toString();

                if (user.equals("")||pass.equals("")||conPass.equals("")){
                    Toast.makeText(register.this, "Isi Username dan Password dengan Baik!", Toast.LENGTH_SHORT).show();
                }else {
                    if (pass.equals(conPass)){
                        Boolean checkUsername = databaseHelper.checkUsername(user);
                        if (checkUsername==false){
                            Boolean insert = databaseHelper.insertData(user, pass);
                            if (insert==true){
                                Toast.makeText(register.this, "Register Berhasil!, Silahkan Login!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),login.class));
                            }else {
                                Toast.makeText(register.this, "Registrasi Gagal!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(register.this, "User Sudah Ada Sebelumnya!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(register.this, "Password Tidak Sama, Ulangi Masukkan Konfirmasi!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register.this,login.class));
            }
        });
    }
}