package com.example.himanshugupta.blood_vital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class bneg extends AppCompatActivity {

    String myJSON;

    private static final String TAG_RESULTS="result";
    private static final String TAG_NAME = "Name";
    private static final String TAG_CONTACT = "contact";
    private static final String TAG_BLOOD_GROUP = "blood_group";


    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bneg);
        list = (ListView) findViewById(R.id.listView10);
        personList = new ArrayList<HashMap<String,String>>();
        getData();
    }
    protected void showList(String response){

        try {
            JSONObject jsonObj = new JSONObject(response);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String Name = c.getString(TAG_NAME);
                String contact = c.getString(TAG_CONTACT);
                String blood_group = c.getString(TAG_BLOOD_GROUP);


                HashMap<String,String> persons = new HashMap<String,String>();

                persons.put(TAG_NAME,Name);
                persons.put(TAG_CONTACT,contact);
                persons.put(TAG_BLOOD_GROUP,blood_group);


                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    bneg.this, personList, R.layout.listview4,
                    new String[]{TAG_NAME,TAG_CONTACT,TAG_BLOOD_GROUP},
                    new int[]{R.id.Name,R.id.contact,R.id.blood_group}
            );

            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void getData() {

        String url ="http://192.168.43.219/app/bn.php";
        // Toast.makeText(s_profile.this, url, Toast.LENGTH_LONG).show();
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //  Toast.makeText(bloodinneed.this, response + "response here", Toast.LENGTH_LONG).show();
                showList(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(bneg.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}



