package com.m.wecare;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

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

public class InputScreen extends AppCompatActivity {
    LinearLayout spinner_parent_layout;

      Button addSpinnerBtn,examineBtn;
    List symptomsList=new ArrayList();
    final List<String> userSymptomsList=new ArrayList();
    final List<String> symptomList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_input);
        getSymptoms(new VolleyCallback() {
            @Override
            public void onSuccessResponse(final List result) {

                symptomsList = result;
                symptomsList.add(0,"Select Symptom");

                //spinner element
                Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        int index = parent.getSelectedItemPosition();
                        //selected element name

                        //not adding default value at index 0
                        System.out.println("selected symptoms" + symptomsList.get(position));
                            if(position!=0){
                                userSymptomsList.add(symptomsList.get(position).toString());
                            }




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
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        int index = parent.getSelectedItemPosition();
                        //selected element name
                        System.out.println("selected symptoms" + symptomsList.get(position));
                        if(position!=0){
                            userSymptomsList.add(symptomsList.get(position).toString());
                        }

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


                Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        int index = parent.getSelectedItemPosition();
                        //selected element name
                        System.out.println("selected symptoms" + symptomsList.get(position));
                        if(position!=0){
                            userSymptomsList.add(symptomsList.get(position).toString());
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                // Creating adapter for spinner
                ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<>(InputScreen.this, android.R.layout.simple_spinner_item, symptomsList);
                // Drop down layout style - list view with radio button
                dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                spinner3.setAdapter(dataAdapter3);


            }
        });

        examineBtn = findViewById(R.id.btnSubmit);
        examineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("examine btn click");
                //user selected symptomslist
                System.out.println("user selected Symptoms");
                System.out.println(userSymptomsList);
                //calling api by passing user symptoms

                //diagnosis(userSymptomsList);


                        //opening disease_details activity by passing predicated disease name
                        //opening output activity
                        Intent intent = new Intent(InputScreen.this, diagnosis_report.class);
                       // intent.putExtra("predicatedDiseaseName",predicatedDiseaseName);
                        intent.putExtra("symptomslist",userSymptomsList.toArray(new String[userSymptomsList.size()]));
                        startActivity(intent);







                //openMain3Activity();
            }
        });


        addSpinnerBtn=findViewById(R.id.addSpinnerBtn);
        addSpinnerBtn.setOnClickListener(addSpinnerHandler);






    }

    private View.OnClickListener addSpinnerHandler=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //adding new spinner dynammically
            System.out.println("dynamica called");
            spinner_parent_layout=findViewById(R.id.spinner_parent_layout);
            LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = vi.inflate(R.layout.spinner_item,null);

            Spinner dynamicSpinner=view.findViewById(R.id.spinner);
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(InputScreen.this, android.R.layout.simple_spinner_item, symptomsList);
            // Drop down layout style - list view with radio button
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dynamicSpinner.setAdapter(dataAdapter2);
            dynamicSpinner.setId(0);

            dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int index = parent.getSelectedItemPosition();
                    //selected element name
                    System.out.println(" dynamic selected symptoms" + symptomsList.get(position));
                    if(position!=0){
                        userSymptomsList.add(symptomsList.get(position).toString());
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });






            final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height=196;
            params.setMargins(0,0,0,88);

            spinner_parent_layout.addView(dynamicSpinner,params);








        }
    };

    public interface VolleyCallback {
        void onSuccessResponse(List result);
    }






    public void  getSymptoms(final VolleyCallback callback) {
        System.out.println("api called");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:5000/api/getsymptoms";

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

