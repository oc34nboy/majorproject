package com.m.wecare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EmergencyFragment extends Fragment {

    ListView listview;
    ArrayList<String> list=new ArrayList<String>();
    ArrayList<String> listNumber=new ArrayList<>();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(false);
       final View  v= inflater.inflate(R.layout.fragment_emergency,container,false);
       getActivity().setTitle("Emergency Contact");

        list.add("police");
        list.add("Fire Brigade");
        list.add("Aasara Drug Rehabilitation Center");
        list.add("Blood Bank");
        list.add("Nepal Eye Bank");
        list.add("Nepal Eye Hospital");
        list.add("Tilganga Eye Hospital");
        list.add("Bir Hospital");
        list.add("Nepal Police Hospital");
        list.add("TU Teaching Hospital");
        list.add("Maternity Hospital");
        list.add("Teku Hospital");
        list.add("Bhaktapur Hospital");
        list.add("B&B Hospital");
        list.add("Norvic Hospital");
        list.add("Gangalal National Heart Centre");
        list.add("National Kidney Centre");

        //adding number

        listNumber.add("100");
        listNumber.add("101");
        listNumber.add("01-4384881");
        listNumber.add("01-4225344");
        listNumber.add("01-4493684");
        listNumber.add("01-4250691");
        listNumber.add("01-4423684");
        listNumber.add("01-4223807");
        listNumber.add("01-4412430");
        listNumber.add("01-4412404");
        listNumber.add("01-4253276");
        listNumber.add("01-4253396");
        listNumber.add("01-6610676");
        listNumber.add("01-5533206");
        listNumber.add("01-4258554");
        listNumber.add("01-4371322");
        listNumber.add("01-4429866");










        listview=v.findViewById(R.id.listView);
        ArrayAdapter<String> listadapter = new ArrayAdapter<>(v.getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, list);
        listview.setAdapter(listadapter);

       listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               //getting number from respected item
               String number=listNumber.get(position);
               if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) ==
                       PackageManager.PERMISSION_GRANTED) {

                   Intent callIntent = new Intent(Intent.ACTION_CALL);
                   callIntent.setData(Uri.parse("tel:" + number));
                   startActivity(callIntent);
               }else{
                   System.out.println("no permission");
                   ActivityCompat.requestPermissions(getActivity(),
                           new String[]{Manifest.permission.CALL_PHONE},
                           1);
               }
           }
       });



        return v;
    }

}
