package com.m.wecare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InputScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

      Button button1;
    List symptomsList=new ArrayList();
    final List<String> userSymptomsList=new ArrayList();
    //String[] users = { "Suresh Dasari0001", "Trishika Dasari", "Rohini Alavala", "Praveen Kumar", "Madhav Sai" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_input);
        getSymptoms(new VolleyCallback() {
            @Override
            public void onSuccessResponse(final List result) {

                symptomsList=result;
                //just a commet
                //second commet
                //spinner element
                Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        int index = parent.getSelectedItemPosition();
                        //selected element name
                        System.out.println("selected symptoms"+ result.get(position));
                        userSymptomsList.add(result.get(position).toString());


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                // Creating adapter for spinner
                ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<>(InputScreen.this, android.R.layout.simple_spinner_item, symptomsList);
                // Drop down layout style - list view with radio button
                dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                spinner1.setAdapter(dataAdapter1);


                Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        int index = parent.getSelectedItemPosition();
                        //selected element name
                        System.out.println("selected symptoms"+ result.get(position));
                        userSymptomsList.add(result.get(position).toString());

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                // Creating adapter for spinner
                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(InputScreen.this, android.R.layout.simple_spinner_item, symptomsList);
                // Drop down layout style - list view with radio button
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                spinner2.setAdapter(dataAdapter2);




            }
        });




        button1 = (Button)findViewById(R.id.btnSubmit);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button click");
                //user selected symptomslist
                System.out.println("user selected Symptoms");

                System.out.println(userSymptomsList);
                //calling api by passing user symptoms

                //diagnosis(userSymptomsList);
                diagnosis(userSymptomsList, new DiagnosisCallback() {
                    @Override
                    public void onSuccessResponse(String predicatedDiseaseName) {


                        //opening report activity by passing predicated disease name
                        //opening output activity
                        Intent intent = new Intent(InputScreen.this, Main3Activity.class);
                        intent.putExtra("predicatedDiseaseName",predicatedDiseaseName);
                        startActivity(intent);

                    }
                });





                //openMain3Activity();
            }
        });
        System.out.println("New clicked");





    }

    public interface VolleyCallback {
        void onSuccessResponse(List result);
    }

    public interface DiagnosisCallback{
        void onSuccessResponse(String predicatedDiseaseName);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:

                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void diagnosis(List symptoms,final DiagnosisCallback callback) {
        JSONObject postparams = new JSONObject();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url="https://lapzap98.pythonanywhere.com/api/diagnosis";
        try {
            postparams.put("symptoms", new JSONArray(symptoms) );

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,
                url,
                postparams,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        System.out.println("got response");
                        System.out.println("array length:"+response);
                        String diagnosis=response.toString().split("\\:")[1];
                        String predicateDiseaseName=diagnosis.replaceAll("[^a-zA-Z0-9\\s+]", "");
                        callback.onSuccessResponse(predicateDiseaseName);




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("error response");
                        System.out.println(error);
                    }
                });
        queue.add(jsonObjReq);

    }

    public void  getSymptoms(final VolleyCallback callback) {
        System.out.println("api called");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://lapzap98.pythonanywhere.com/api/getsymptoms";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest (
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray  response) {
                        System.out.println("got response");
                        System.out.println("array length:"+response.length());
                        List<String> ls=new ArrayList<>();
                        for(int i=0;i<response.length();i++) {
                            try {
                                String jo = response.getString(i);
                                ls.add(jo);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        callback.onSuccessResponse(ls);



                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("error response");
                        System.out.println(error);
                    }
                });

        queue.add(jsonObjectRequest);



    }
}

