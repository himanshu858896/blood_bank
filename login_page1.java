package com.example.himanshugupta.blood_vital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import serverConnectivity.singeltonClass;

public class login_page1 extends Activity {

    private String serverURL = "http://192.168.43.219/app/signIn.php";
    private EditText username, password;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page1);
        Log.e("inisede", "inside main");
        //Linking xml to java objects

        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        signIn = (Button) findViewById(R.id.btn_login);

    }




    public void LogIn_function(View v) throws JSONException {

        final String Username, Password;

        Username = username.getText().toString();
        Password = password.getText().toString();

        //  Toast.makeText(this, Username + Password, Toast.LENGTH_SHORT).show();
        Log.e("inside", "inside dosomething");
        //creating request
        singeltonClass queue = new singeltonClass(this);

        //creating request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, serverURL,


                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        // Toast.makeText(login_page1.this, "inside onResponse", Toast.LENGTH_SHORT).show();
                        Log.e("inside ", "sending data request onresponse");



                        if (response.equals("failed")) {

                            //same activity
                            Toast.makeText(login_page1.this, "LogIn Failed, Try Again", Toast.LENGTH_SHORT).show();
                            Toast.makeText(login_page1.this, "inside onResponse", Toast.LENGTH_SHORT).show();

                        } else {


                            //moving to next activity

                            Intent i = new Intent(login_page1.this, menu.class);
                            i.putExtra("username", response);
                            startActivity(i);
                            Toast.makeText(login_page1.this, "login successful", Toast.LENGTH_SHORT).show();
///
                        }
                        Log.e("", "inside exception");

                    } //catch (JSONException e) {

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(login_page1.this, "inside exception end dosomething" + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("", "inside exception" + error.getMessage());

            }

        }) {

            protected Map<String, String> getParams() {

                Log.e("inside params", "sending data");
                Map<String, String> params = new HashMap<>();
                params.put("username", Username);
                params.put("password", Password);
                Log.e("inside params", "ending sending data");
                return params;

            }
        };


        queue.getRequestQueue().add(stringRequest);


    }

    public JSONObject get2SendData(final String username, final String password) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);

        return jsonObject;

    }

    public void NewRegister(View V)
    {
        startActivity(new Intent(this,New_register.class));
    }
}
