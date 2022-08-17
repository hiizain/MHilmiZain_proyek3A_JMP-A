package com.example.myapplicationpertemuan9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class RegisterActivity extends AppCompatActivity {

    EditText et_username, et_password, et_email, et_nama, et_asal, et_alamat;

    Button b_simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.setTitle("Register");

        et_username = findViewById(R.id.etUsernameR);
        et_password = findViewById(R.id.etPasswordR);
        et_email = findViewById(R.id.etEmailR);
        et_nama = findViewById(R.id.etNamaR);
        et_asal = findViewById(R.id.etAsalR);
        et_alamat = findViewById(R.id.etAlamatR);

        b_simpan = findViewById(R.id.bSimpan);

        b_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidation()){
                    simpanFileData();
                } else {
                    Toast.makeText(RegisterActivity.this, "Lengkapi seluruh data!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean isValidation(){
        if (et_username.getText().toString().equals("")||
                et_password.getText().toString().equals("")||
                et_email.getText().toString().equals("")||
                et_nama.getText().toString().equals("")||
                et_asal.getText().toString().equals("")||
                et_alamat.getText().toString().equals("")){
            return false;
        } else {
            return true;
        }
    }

    public void simpanFileData(){
        String isiFile = et_username.getText().toString()+";"+
                et_password.getText().toString()+";"+
                et_email.getText().toString()+";"+
                et_nama.getText().toString()+";"+
                et_asal.getText().toString()+";"+
                et_alamat.getText().toString();
        File file = new File(getFilesDir(), et_username.getText().toString());
        FileOutputStream fileOutputStream = null;

        try {
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file, false);
            fileOutputStream.write(isiFile.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show();
//        onBackPressed();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}