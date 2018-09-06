 package com.example.himanshugupta.blood_vital;

import android.app.Activity;
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

public class bloodinneed extends Activity {


    String myJSON;

    private static final String TAG_RESULTS="result";
    private static final String TAG_USERNAME = "userName";
    private static final String TAG_CONTACT = "contact";
    private static final String TAG_BGROUP = "bgroup";
    private static final String TAG_ADD ="address";

    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodinneed);
        list = (ListView) findViewById(R.id.listView1);
        personList = new ArrayList<HashMap<String,String>>();
        getData();

    }

    protected void showList(String response){

        try {
            JSONObject jsonObj = new JSONObject(response);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String userName = c.getString(TAG_USERNAME);
                String contact = c.getString(TAG_CONTACT);
                String bgroup = c.getString(TAG_BGROUP);
                String address = c.getString(TAG_ADD);

                HashMap<String,String> persons = new HashMap<String,String>();

                persons.put(TAG_USERNAME,userName);
                persons.put(TAG_CONTACT,contact);
                persons.put(TAG_BGROUP,bgroup);
                persons.put(TAG_ADD,address);

                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    bloodinneed.this, personList, R.layout.listview2,
                    new String[]{TAG_USERNAME,TAG_CONTACT,TAG_BGROUP,TAG_ADD},
                    new int[]{R.id.userName,R.id.contact,R.id.bgroup,R.id.address}
            );

            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void getData() {

        String url ="http://192.168.43.219/app/getData.php";
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
                        Toast.makeText(bloodinneed.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}


