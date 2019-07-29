package com.m.wecare;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InputScreen extends AppCompatActivity {
    LinearLayout spinner_parent_layout;

      Button addSpinnerBtn,button2;
    List symptomsList=new ArrayList();
    final List<String> userSymptomsList=new ArrayList();


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
                        System.out.println("selected symptoms" + result.get(position));
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
                        System.out.println("selected symptoms" + result.get(position));
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


                Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        int index = parent.getSelectedItemPosition();
                        //selected element name
                        System.out.println("selected symptoms" + result.get(position));
                        userSymptomsList.add(result.get(position).toString());

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
        /*button2 = (Button)findViewById(R.id.btnAdd);
        button2.setOnClickListener(new View.OnClickListener() {
           @Override
                public void onClick(View v) {
                Spinner spinner= new Spinner(InputScreen.this);
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(InputScreen.this, android.R.layout.simple_spinner_dropdown_item, userSymptomsList);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerArrayAdapter);
                linear.addView(spinner);

                  }
               });*/


       /* button1 = (Button)findViewById(R.id.btnSubmit);
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
        */

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

            Spinner spinner=view.findViewById(R.id.spinner);
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(InputScreen.this, android.R.layout.simple_spinner_item, symptomsList);
            // Drop down layout style - list view with radio button
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter2);
            spinner.setId(0);
            final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height=196;
            params.setMargins(0,0,0,88);

            spinner_parent_layout.addView(spinner,params);








        }
    };

    public interface VolleyCallback {
        void onSuccessResponse(List result);
    }

    public interface DiagnosisCallback{
        void onSuccessResponse(String predicatedDiseaseName);
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

