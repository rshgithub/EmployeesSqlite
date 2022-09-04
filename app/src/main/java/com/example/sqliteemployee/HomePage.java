package com.example.sqliteemployee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqliteemployee.Adapter.EmployeesAdapter;
import com.example.sqliteemployee.Database.Database_Access;
import com.example.sqliteemployee.Model.EmployeeModel;
import com.example.sqliteemployee.SuperClasses.BaseActivity;
import com.example.sqliteemployee.databinding.ActivityHomePageBinding;
import com.example.sqliteemployee.databinding.ActivityLoginBinding;

import java.util.ArrayList;

public class HomePage extends BaseActivity {

    private ActivityHomePageBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBA = Database_Access.getInstance(this);

        // استقبال البيانات
        if (getIntent() != null) {
            String empName = getIntent().getStringExtra(LoginActivity.Name_Key);

            // if there's no retrieved data from sign up activity :
            if (empName != null) {
                binding.homePageEmpName.setText(empName);
            } else {
                Toast.makeText(getBaseContext(), "No employee name", Toast.LENGTH_SHORT).show();
            }
        }

        DBA.open();
        ArrayList<EmployeeModel> employees = DBA.getAllEmployees();
        DBA.close();

        EmployeesAdapter adapter = new EmployeesAdapter(this, R.layout.list_item, employees);
        binding.homePageList.setAdapter(adapter);

    }
}