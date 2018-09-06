package com.example.himanshugupta.blood_vital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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


public class request extends Activity {

    String[] Spinnerlist={"A+","A-","B+","B+","O+","O-","AB+","AB-"};

    public EditText userName, addres,phonenumber;
    public String bgroup;
    public Spinner betterspinner1;
    public String serverURL = "http://192.168.43.219/app/need.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(request.this,
                android.R.layout.simple_list_item_1, Spinnerlist);
        betterspinner1=(Spinner)findViewById(R.id.spinner2);
        betterspinner1.setAdapter(myAdapter);
        userName = (EditText) findViewById(R.id.userName);
        phonenumber = (EditText)findViewById(R.id.cont);
        addres = (EditText)findViewById(R.id.addr);
        betterspinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bgroup = parent.getItemAtPosition(position).toString(); }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void doSom(View v)
    {

        final String username,phoneNumber,address;

        username = userName.getText().toString();
        phoneNumber = phonenumber.getText().toString();
        address = addres.getText().toString();

        singeltonClass queue = new singeltonClass(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, serverURL,

                new Response.Listener<String>() {




                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(request.this, response, Toast.LENGTH_SHORT).show();

                        //opening new activity


                        Intent i = new Intent(request.this, menu.class);
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

                params.put("userName", username);
                params.put("bgroup", bgroup);
                params.put("phoneNumber", phoneNumber);
                params.put("address", address);
                return params;

            }

        };

        queue.getRequestQueue().add(stringRequest);


    }


    }






