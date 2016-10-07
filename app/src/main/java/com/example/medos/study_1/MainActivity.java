package com.example.medos.study_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.realtime.util.StringListReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Firebase readRef;
    ArrayList<String>array=new ArrayList<String>();
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        readRef = new Firebase("https://yourdocto.firebaseio.com/0N1gRgGdCscbsQd7lOhv3fkXW3u1/Profile");
        list=(ListView)findViewById(R.id.listView);
        final ArrayAdapter arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        list.setAdapter(arrayAdapter);
       // Firebase usertest = readRef.child("users");
        LayoutInflater inflater=getLayoutInflater();

        readRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot shot:dataSnapshot.getChildren()) {
                    Map<String, Objects> map = shot.getValue(Map.class);
                    Doctors obj=shot.getValue(Doctors.class);
                   String name= obj.getName();
                  String address= obj.getAddress();
                    String full = "Name : "+ name+ "    "+"/n" +"address : "+address;
                    // txt_user.setText(username);
                    array.add(full);
                    arrayAdapter.notifyDataSetChanged();
                }

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
