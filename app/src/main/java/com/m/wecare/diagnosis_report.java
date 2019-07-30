package com.m.wecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.m.wecare.remainder.Database.MedicineModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class diagnosis_report extends AppCompatActivity {


 private ArrayList<String> diseaseName=new ArrayList<>();
 private ArrayList<String> diseaseProb=new ArrayList<>();
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagnosis_report);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please Wait..");
        progressDialog.show();

        Intent intent = getIntent();
        String[] predicatedDiseaseName = intent.getStringArrayExtra("symptomslist");
        System.out.println("predicate disease list via intent");
        System.out.println(predicatedDiseaseName);

        getData(predicatedDiseaseName);



    }

    private void getData(String[] userSymptomsList) {

        diagnosis(userSymptomsList, new DiagnosisCallback() {
                    @Override
                    public void onSuccessResponse(String predicatedDiseaseName) {
                        progressDialog.hide();
                        System.out.println("predicated disease iom diagnosis report ");
                        System.out.println(predicatedDiseaseName);
                        initResult();
                    }
                });






    }

    private void initResult() {
        System.out.println("recycle init");

        RecyclerView recyclerView=findViewById(R.id.report_recycleView);
        Report_recycleView_adapter adapter = new Report_recycleView_adapter(this,diseaseName,diseaseProb);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    public interface DiagnosisCallback{
        void onSuccessResponse(String predicatedDiseaseName);
    }

    private void diagnosis(String[] symptoms, final DiagnosisCallback callback) {
        JSONObject postparams = new JSONObject();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url= MedicineModel.API_URL+"/api/diagnosis";
        try {
            postparams.put("symptoms", new JSONArray(symptoms) );

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,
                url,
                postparams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        Iterator<String> iter = response.keys();
                        while (iter.hasNext()) {
                            String key = iter.next();
                            diseaseName.add(key);
                            System.out.println("key"+key);
                            try {
                                Object value = response.get(key);
                                float percent=Float.parseFloat(value.toString());
                                System.out.println("float percentage"+percent);
                               // float percent=(float)value;
                               // System.out.println("percentage"+percent);

                                diseaseProb.add(Math.round(percent)+"%");
                                System.out.println("response"+value);
                            } catch (JSONException e) {
                                // Something went wrong!
                                e.printStackTrace();
                            }
                        }

                        callback.onSuccessResponse("ok");

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
}
