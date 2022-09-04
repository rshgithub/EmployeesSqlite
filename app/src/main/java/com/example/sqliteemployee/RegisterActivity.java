package com.example.sqliteemployee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sqliteemployee.Database.Database_Access;
import com.example.sqliteemployee.Model.EmployeeModel;
import com.example.sqliteemployee.SuperClasses.BaseActivity;
import com.example.sqliteemployee.databinding.ActivityLoginBinding;
import com.example.sqliteemployee.databinding.ActivityRegisterBinding;

public class RegisterActivity extends BaseActivity {


     private EmployeeModel employee ;

    private ActivityRegisterBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBA = Database_Access.getInstance(this);

        binding.registerRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullName = binding.registerFullNameEt.getText().toString();
                String email = binding.registerEmailEt.getText().toString();
                String password = binding.registerPassEt.getText().toString();

                if (fullName.equals("")) {
                    Toast.makeText(getBaseContext(), "Please Enter your fullName", Toast.LENGTH_SHORT).show();
                } else if (email.equals("")) {
                    Toast.makeText(getBaseContext(), "Please Enter your Password", Toast.LENGTH_SHORT).show();
                } else if (password.equals("") || password.length() < 6) {
                    Toast.makeText(getBaseContext(), "Please Enter your Password , or check password length must be 6 digits or more ", Toast.LENGTH_SHORT).show();
                } else {

                    DBA.open();
                    employee = new EmployeeModel(fullName,email,password);

                    Boolean result = DBA.insertEmployee(employee);
                    // if process done successfully
                    if (result) {

                        Toast.makeText(getBaseContext(), "employee Added successfully ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();

                    } else {
                        Toast.makeText(getBaseContext(), "employee Addition failed ", Toast.LENGTH_SHORT).show();

                    }

                    DBA.close();

                }
            }
        });
    }
}