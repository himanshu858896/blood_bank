package com.example.himanshugupta.blood_vital;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class menu extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void findpage(View V)
    {
        startActivity(new Intent(this,findActivity.class));
    }

    public void addmember(View V)
    {
        startActivity(new Intent(this,member.class));
    }

    public void need(View V)
    {
        startActivity(new Intent(this,bloodinneed.class));
    }
    public void xyz(View V) {startActivity(new Intent(menu.this,request.class));}
    public void Contactme(View V)
    {
        startActivity(new Intent(this,contactActivity.class));
    }
    public void log(View V)
    {
        startActivity(new Intent(this,login_page1.class));
    }
}
