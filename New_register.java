package com.example.himanshugupta.blood_vital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import serverConnectivity.singeltonClass;

public class New_register extends Activity {

    String[] Spinnerlist={"A+","A-","B+","B+","O+","O-","AB+","AB-"};


    public EditText userName;
    public EditText pass;
    public String Bgroup;
    public EditText phonenumber;
    public Spinner betterspinner1;
    public Button signUp;
    public String serverURL = "http://192.168.43.219/app/signUp.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(New_register.this,
                android.R.layout.simple_list_item_1, Spinnerlist);
        betterspinner1=(Spinner)findViewById(R.id.spinner3);
        betterspinner1.setAdapter(myAdapter);

        //mapping the xml widgets to java objects
        userName = (EditText) findViewById(R.id.userName);
        pass = (EditText) findViewById(R.id.password);
        phonenumber = (EditText) findViewById(R.id.phoneNumber);
        signUp = (Button) findViewById(R.id.btn_signUp2);

        betterspinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Bgroup = parent.getItemAtPosition(position).toString(); }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }



    public void doSomething(View v)
    {

        final String username, password,phoneNumber;

        username = userName.getText().toString();
        password = pass.getText().toString();

        phoneNumber = phonenumber.getText().toString();

        singeltonClass queue = new singeltonClass(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, serverURL,

                new Response.Listener<String>() {




                    @Override
                    public void onResponse(String response) {

                       Toast.makeText(New_register.this, response, Toast.LENGTH_SHORT).show();

                        //opening new activity


                        Intent i = new Intent(New_register.this, menu.class);
                        startActivity(i);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {

            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                params.put("userName",username);
                params.put("password",password);
                params.put("bgroup",Bgroup);
                params.put("phoneNumber",phoneNumber);

                return params;

            }

        };

        queue.getRequestQueue().add(stringRequest);


    }
}
