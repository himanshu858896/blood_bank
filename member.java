package com.example.himanshugupta.blood_vital;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;
import serverConnectivity.singeltonClass;


public class member extends Activity {

    public EditText userName,phonenumber;
    public Button signUp;
    public Spinner betterspinner;
    public String bgroup;
    public String serverURL = "http://192.168.43.219/app/member.php";

    String[] Spinnerlist={"A+","A-","B+","B-","O+","O-","AB+","AB-"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(member.this,
                android.R.layout.simple_list_item_1, Spinnerlist);
        betterspinner=(Spinner)findViewById(R.id.spinner1);
        betterspinner.setAdapter(myAdapter);


        userName = (EditText) findViewById(R.id.nam);
        phonenumber = (EditText)findViewById(R.id.mobo);
        betterspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bgroup = parent.getItemAtPosition(position).toString(); }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void doSome(View v)
    {

        final String username,phoneNumber;

        username = userName.getText().toString();
        phoneNumber = phonenumber.getText().toString();

        singeltonClass queue = new singeltonClass(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, serverURL,

                new Response.Listener<String>() {




                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(member.this, response, Toast.LENGTH_SHORT).show();

                        //opening new activity


                        Intent i = new Intent(member.this, menu.class);
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

                return params;

            }

        };

        queue.getRequestQueue().add(stringRequest);


    }
}
