package com.example.sqliteemployee.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sqliteemployee.Model.EmployeeModel;
import com.example.sqliteemployee.R;
import com.example.sqliteemployee.databinding.ListItemBinding;

import java.util.ArrayList;

public class EmployeesAdapter extends BaseAdapter {

    private Context c ;
    private  int resource;
    private ArrayList<EmployeeModel> employees ;

    public EmployeesAdapter(Context c , int resource , ArrayList<EmployeeModel> employees){
        this.c = c ;
        this.resource = resource ;
        this.employees = employees ; }

    public void addEmployee (EmployeeModel employee){
        this.employees.add(employee);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public EmployeeModel getItem(int i) {
        return employees.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;
        if(v == null ){ v = LayoutInflater.from(c).inflate(resource,null,false);}


        TextView name = v.findViewById(R.id.name);

        EmployeeModel e = getItem(i);

        name.setText(e.getname());


        return v;
    }
}
