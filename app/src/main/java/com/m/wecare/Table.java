package com.m.wecare;

import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TableLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class Table extends AppCompatActivity {

        private boolean table_flg=false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.emergency);
        }

        public void collapseTable(View view){
            TableLayout table = findViewById(R.id.table);
            Button switchBtn = findViewById(R.id.switchBtn);
            //setColumnCollapsed
            table.setColumnCollapsed(1, table_flg);
            table.setColumnCollapsed(1, table_flg);

            if(table_flg){
                //close
                table_flg= false;
                switchBtn.setText("Show Details");
            } else {
                //open
               table_flg= true;
               switchBtn.setText("Hide Details");
            }
        }

    }
