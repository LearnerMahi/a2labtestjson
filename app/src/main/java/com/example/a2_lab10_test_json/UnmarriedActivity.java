package com.example.a2_lab10_test_json;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UnmarriedActivity extends AppCompatActivity {

    ListView listView;
    String company_name;
    TextView textView;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> hobbies = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unmarried);
        listView = findViewById(R.id.list2);
        //textView = findViewById(R.id.textview);

        Intent intent3 = getIntent();
        company_name = intent3.getStringExtra("company");



        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://api.myjson.online/v1/records/7ecc30a8-72cf-41bd-9280-ca60bdf8d439";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for(int i=0; i<jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String companyname = jsonObject1.getString("company");
                        if(company_name.equals(companyname)){
                            JSONObject newjsonObject1 = jsonArray.getJSONObject(i);
                            JSONArray people = jsonObject1.getJSONArray("people");
                            for(int j=0; j<people.length(); j++) {
                                JSONObject pep = people.getJSONObject(j);
                                Boolean check_married = pep.getBoolean("isMarried");
                                String hobbie = "";
                                if(!check_married){
                                    String name = pep.getString("name");
                                    names.add(name);
                                    //JSONObject h2 = pep.getJSONObject()
                                    JSONArray jsonArray1 = pep.getJSONArray("hobbies");


                                    //JSONObject hobbieobject = pep.getJSONObject("hobbies");
                                    //hobbie = hobbieobject.toString();
                                    //hobbies.add(hobbie);
                                    for(int k=0; k<jsonArray1.length(); k++){
                                        String test = jsonArray1.getString(k);
                                        //hobbie = test;
                                        hobbie = hobbie + test + "\n"; //String concatenation in java. Onek chestar por eta kaj korse.
                                        //System.out.println(hobbie);
                                        //hobbie.concat("/n");
                                        //hobbie.concat(hobbieobject)
                                    }
                                    hobbies.add(hobbie);
                                }

                            }

                            ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,names);
                            listView.setAdapter(arrayAdapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(UnmarriedActivity.this, HobbiesActivity.class);
                                    String test = hobbies.get(position);
                                    intent.putExtra("hobbie",test);
                                    startActivity(intent);
                                }
                            });
                                    //break;
                        }
                        /*if(company_name.equals("Robi")){
                            JSONObject newjsonObject1 = jsonArray.getJSONObject(i);
                            JSONArray people = jsonObject1.getJSONArray("people");
                            for(int j=0; j<people.length(); j++) {
                                JSONObject pep = people.getJSONObject(j);
                                Boolean check_married = pep.getBoolean("isMarried");
                                if(!check_married){
                                    String name = pep.getString("name");
                                    names.add(name);
                                }
                            }

                            ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,names);
                            listView.setAdapter(arrayAdapter);
                            //break;
                        }
                        if(company_name.equals("Banglalink")){
                            JSONObject newjsonObject1 = jsonArray.getJSONObject(i);
                            JSONArray people = jsonObject1.getJSONArray("people");
                            for(int j=0; j<people.length(); j++) {
                                JSONObject pep = people.getJSONObject(j);
                                Boolean check_married = pep.getBoolean("isMarried");
                                if(!check_married){
                                    String name = pep.getString("name");
                                    names.add(name);
                                }
                            }

                            ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,names);
                            listView.setAdapter(arrayAdapter);
                            //break;
                        }*/
                    }

                    //change here
                    /*ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,companies);
                    listView.setAdapter(arrayAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        }
                    });*/

                }
                catch (JSONException e){
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}