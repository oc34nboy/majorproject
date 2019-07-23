package com.m.wecare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button,btnEmergency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Main Activity is launched");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.btnFindMe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button click");
                openMain1Activity();
            }
        });
    }
    public void  openMain1Activity(){
        System.out.println("Function clicked");
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
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
            case R.id.item2:
                Intent intent = new Intent(this, Table.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);

        }
    }


}












