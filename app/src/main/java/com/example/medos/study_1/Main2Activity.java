package com.example.medos.study_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Main2Activity extends AppCompatActivity {
    Firebase readRef;
    ArrayList<String> array=new ArrayList<String>();
    ArrayAdapter mAdapter;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Firebase.setAndroidContext(this);

        readRef = new Firebase("https://yourdocto.firebaseio.com/0N1gRgGdCscbsQd7lOhv3fkXW3u1/Profile");
        list=(ListView)findViewById(R.id.listView2);

         FirebaseListAdapter<Doctors>fireadapter=new FirebaseListAdapter<Doctors>(
                 this,
                 Doctors.class,
                 android.R.layout.simple_list_item_1,
                 readRef
         ) {
             @Override
             protected void populateView(View view, Doctors s, int i) {
                TextView text=(TextView)view.findViewById(android.R.id.text1);
                 TextView text2=(TextView)view.findViewById(android.R.id.text2);
                 String nameof_user=s.getName();
                 text.setText(nameof_user);
                // String addressof_user=s.getAddress();
                // text2.setText(addressof_user);
             }
         };

        list.setAdapter(fireadapter);
       // final ArrayAdapter arrayAdapter=new ArrayAdapter<Doctors>(this,android.R.layout.simple_list_item_1,array);
      //  list.setAdapter(arrayAdapter);



    }
}
