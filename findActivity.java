package com.example.himanshugupta.blood_vital;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import serverConnectivity.singeltonClass;



public class findActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
    }
    public void dosomething15(View V)
    {
        startActivity(new Intent(this,showdata.class));
    }
    public void dosomething16(View V)
    {
        startActivity(new Intent(this,aneg.class));
    }
    public void dosomething17(View V)
    {
        startActivity(new Intent(this,bpos.class));
    }
    public void dosomething18(View V)
    {
        startActivity(new Intent(this,bneg.class));
    }
    public void dosomething19(View V)
    {
        startActivity(new Intent(this,opos.class));
    }
    public void dosomething20(View V)
    {
        startActivity(new Intent(this,oneg.class));
    }
    public void dosomething21(View V)
    {
        startActivity(new Intent(this,abpos.class));
    }
    public void dosomething22(View V)
    {
        startActivity(new Intent(this,abneg.class));
    }









}