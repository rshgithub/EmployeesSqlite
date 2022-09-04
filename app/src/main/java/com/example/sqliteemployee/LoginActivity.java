package com.example.sqliteemployee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqliteemployee.Database.Database_Access;
import com.example.sqliteemployee.SuperClasses.BaseActivity;
import com.example.sqliteemployee.databinding.ActivityHomePageBinding;
import com.example.sqliteemployee.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    public static final String Name_Key = "name_key";
    public static final int SHOW_ITEM_REQ_CODE = 2 ;
    public AlertDialog.Builder errorDialog;

    private ActivityLoginBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBA = Database_Access.getInstance(this);

        binding.loginLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.loginEmailEt.getText().toString();
                String password = binding.loginPassEt.getText().toString();

                if (email.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please Enter your Email", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please Enter your Password", Toast.LENGTH_SHORT).show();
                } else {

                    DBA.open();

                    String empName = DBA.loginCheck(email,password);

                    if(empName != ""){

                        Intent intent = new Intent(LoginActivity.this, HomePage.class);
                        intent.putExtra(Name_Key, empName);
                        startActivityForResult(intent,SHOW_ITEM_REQ_CODE);

                    }else{
                        showErrorDialog();
                        Toast.makeText(LoginActivity.this, "your Data is wrong", Toast.LENGTH_SHORT).show();
                    }


                    DBA.close();
                }
            }
        });

        binding.loginRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }


    public void showErrorDialog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("you data is wrong");
        builder1.setTitle("Error");
        builder1.setCancelable(true);

        builder1.setNegativeButton(
                "ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


//    public void showErrorDialog() {
//        errorDialog = new AlertDialog.Builder(this);
//        errorDialog.setMessage("you data is wrong");
//        errorDialog.setTitle("Error");
//        errorDialog.setCancelable(true);
//        errorDialog.show();
//    }



}




