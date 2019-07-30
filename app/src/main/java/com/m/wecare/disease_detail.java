package com.m.wecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.Iterator;

public class disease_detail extends AppCompatActivity {

    private TextView diseaseName, diseaseSymptoms, diseaseCause, diseaseTreatment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_detail);


        String diseaseId = getIntent().getStringExtra("diseaseName");
        System.out.println("the disease name is" + diseaseId);

        diseaseName = findViewById(R.id.diseaseName);
        diseaseSymptoms = findViewById(R.id.diseaseSymptoms);
        diseaseCause = findViewById(R.id.diseaseCause);
        diseaseTreatment = findViewById(R.id.diseaseTreatment);

        getDiseaseDetail(diseaseId, new diseaseDetailCallback() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                try {
                        System.out.println("got valid response");
                        diseaseName.setText(result.getString("name"));
                        diseaseCause.setText(result.getString("cause"));
                        diseaseSymptoms.setText(result.getString("symptom"));
                        diseaseTreatment.setText(result.getString("treatment"));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void onErrorResponse() {
                //back to previous activity
                finish();

            }
        });


    }

    public interface diseaseDetailCallback {
        void onSuccessResponse(JSONObject result);
        void onErrorResponse();

    }

    private void getDiseaseDetail(String symptoms, final disease_detail.diseaseDetailCallback callback) {
        JSONObject postparams = new JSONObject();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = MedicineModel.API_URL + "/api/getDiseaseDetail";
        try {
            postparams.put("diseaseName", symptoms);

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
                        callback.onSuccessResponse(response);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("error response");
                        System.out.println(error);
                        callback.onErrorResponse();
                    }
                });
        queue.add(jsonObjReq);

    }
}
