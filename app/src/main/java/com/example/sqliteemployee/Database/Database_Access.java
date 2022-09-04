package com.example.sqliteemployee.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqliteemployee.Model.EmployeeModel;

import java.util.ArrayList;

public class Database_Access {
    // access DB clas
    public SQLiteDatabase database; // to make instace / obj from the DB and get writeable and readable
    private SQLiteOpenHelper openHelper; // to open and close DB
    private static Database_Access instance;


    // Singleton Design Pattern :
    // this method stops user from making obj's from DB the known way .
    // create the database from this class .
    private Database_Access(Context context) {
        this.openHelper = new My_Database(context);
    }

    // the method will be used from user to create BD obj's
    // if there's no obj from Db then Create it , if there's one already then use it .
    public static Database_Access getInstance(Context context) {
        // if there's no obj already created then Create one
        if (instance == null) {
            instance = new Database_Access(context);
        }
        // if there's obj already created then use it
        return instance;
    }

    // open Db to use it and close it to able another user to use it .
    public void open() {
        this.database = this.openHelper.getWritableDatabase();
    }

    public void close() {
        // if Db obj exists and opened to write in .
        if (this.database != null) {
            this.database.close();
        }
    }

//-------------------------------------------------------------------------
//        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);


    public boolean insertEmployee(EmployeeModel employee) {

        ContentValues CV = new ContentValues();

        CV.put(My_Database.EMP_TB_NAME, employee.getname());
        CV.put(My_Database.EMP_TB_EMAIL, employee.getEmail());
        CV.put(My_Database.EMP_TB_PASS, employee.getPassword());

        long result = database.insert(My_Database.EMPLOYEES_TB, null, CV);
        // if inserting return true > 1 if false = -1
        return result != -1;
    }


    public boolean DeleteEmployee(EmployeeModel employee) {
        // "where clause is obligatory in delete query "
        // delete rows as condition ( delete where id = X ) :
        String args[] = {String.valueOf(employee.getId())};
        long result = database.delete(My_Database.EMPLOYEES_TB, "id=?", args);
        // number of rows deleted
        return result > 0;
    }


    public ArrayList<EmployeeModel> getAllEmployees() {
        // return all cars :
        ArrayList<EmployeeModel> EmployeesList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + My_Database.EMPLOYEES_TB, null);
        //cursor.moveToFirst(); >>> returns boolean , if there's an obj returns true , else returns false .

        // to know if cursor contains data or not :
        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String emp_name = cursor.getString(cursor.getColumnIndex(My_Database.EMP_TB_NAME));
                @SuppressLint("Range") String emp_email = cursor.getString(cursor.getColumnIndex(My_Database.EMP_TB_EMAIL));
                @SuppressLint("Range") String emp_pass = cursor.getString(cursor.getColumnIndex(My_Database.EMP_TB_PASS));

                // call constructor :
                EmployeeModel Employee = new EmployeeModel(emp_name,emp_email,emp_pass);

                EmployeesList.add(Employee);

            } while (cursor.moveToNext());
            cursor.close();
        }

        return EmployeesList;
    }

    //------------------------------------------------------------------------------------------------------------------------

    // get item details in details activity from posts activity
    public EmployeeModel getEmployee(int empId) {

        Cursor cursor = database.rawQuery("SELECT * FROM " + My_Database.EMPLOYEES_TB + " WHERE " + My_Database.EMP_TB_ID + " =? ", new String[]{String.valueOf(empId)});
        //cursor.moveToFirst(); >>> returns boolean , if there's an obj returns true , else returns false .

        // to know if cursor contains data or not :
        if (cursor != null && cursor.moveToFirst()) {

            @SuppressLint("Range") String emp_name = cursor.getString(cursor.getColumnIndex(My_Database.EMP_TB_NAME));
            @SuppressLint("Range") String emp_email = cursor.getString(cursor.getColumnIndex(My_Database.EMP_TB_EMAIL));
            @SuppressLint("Range") String emp_pass = cursor.getString(cursor.getColumnIndex(My_Database.EMP_TB_PASS));

            // call constructor :
            EmployeeModel Employee = new EmployeeModel(emp_name,emp_email,emp_pass);

            cursor.close();
            return Employee;
        }

        return null;
    }

    //------------------------------------------------------------------------------------------------------------------------


    // Read Section
//    public Boolean checkEmail(String emailTxt) {
//        Cursor cursor = database.rawQuery("SELECT * FROM " + My_Database.EMPLOYEES_TB + " WHERE " + My_Database.EMP_TB_EMAIL + " =? ", new String[]{emailTxt});
//
//        if (cursor != null && cursor.moveToFirst()) {
//            return true;
//        }
//        return false;
//    }

    //------------------------------------------------------------------------------------------------------------------------


    @SuppressLint("Range")
    public String loginCheck(String emailTxt , String PasslTxt) {


        String args [] = {emailTxt,PasslTxt};
        String columns [] = {My_Database.EMP_TB_NAME};
        Cursor cursor = database.query( My_Database.EMPLOYEES_TB,columns,"email=? AND password=? ",args,null,null,null,null);

        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(My_Database.EMP_TB_NAME));
        }

        return "";
    }


}
