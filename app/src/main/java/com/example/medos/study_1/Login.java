package com.example.medos.study_1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private Firebase RefRoot;
    private FirebaseAuth mAuth;
    private Button btn;
    private FirebaseAuth.AuthStateListener mAuthlistener;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);

        mAuth=FirebaseAuth.getInstance();
      //  mAuth.addAuthStateListener(mAuthlistener);
        et_password=(EditText)findViewById(R.id.et_password);
        et_username=(EditText)findViewById(R.id.et_username);
        progressDialog=new ProgressDialog(this);
        btn=(Button)findViewById(R.id.btn_log);

         mAuthlistener= new FirebaseAuth.AuthStateListener() {
             @Override
             public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                 if(firebaseAuth.getCurrentUser()!=null)
                 {
                     FirebaseUser user = firebaseAuth.getCurrentUser();
                     if (user != null) {

                        // mAuth.signOut();
                         String Doctor_ID = user.getUid();
                         Toast.makeText(Login.this, Doctor_ID, Toast.LENGTH_LONG).show();
                         // startActivity(new Intent(Login.this,MainActivity.class));
                     }
                 }
             }
         };
    }
    public void login(View view) {
     startSignin();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthlistener);
    }
    private void CreateAccount(){
        String email=et_username.getText().toString();
        String password=et_password.getText().toString();
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)) {
            Toast.makeText(Login.this, "Field Are Empty", Toast.LENGTH_LONG).show();
        }else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(Login.this, "Sign In Problem", Toast.LENGTH_LONG).show();
                       // Log.v("error",task.getException().toString());
                    }
                    else {
                        Toast.makeText(Login.this, "Successfull To Make Account", Toast.LENGTH_LONG).show();
                        mAuthlistener = new FirebaseAuth.AuthStateListener() {
                            @Override
                            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                if (firebaseAuth.getCurrentUser() != null) {
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    if (user != null) {
                                        String Doctor_ID = user.getUid();
                                        Log.d("id",Doctor_ID);
                                         RefRoot = new Firebase("https://yourdocto.firebaseio.com/"+Doctor_ID+"");
                                        String Name = "ali";
                                        String Address = "ahmed";
                                        String Specialist = "al";
                                        String CertificatesPIC = "ahmed";
                                        Firebase refChild = RefRoot.child("Profile");
                                        Firebase refChild2 = RefRoot.child("recieve");
                                        Doctors Doctor = new Doctors();
                                        Doctor.setAddress(Address);
                                        Doctor.setName(Name);
                                        Doctor.setSpecialist(Specialist);
                                        Doctor.setCertificatesPIC(CertificatesPIC);
                                        refChild.push().setValue(Doctor);
                                        refChild2.push().setValue(Doctor);
                                      //  Toast.makeText(Login.this, "Make Database", Toast.LENGTH_LONG).show();
                                   }
                              }
                            }
                        };
                    }
                }
            });
        }
        progressDialog.setMessage("Register Doctor......");
        progressDialog.show();
    }
    private void startSignin(){
        String email=et_username.getText().toString();
        String password=et_password.getText().toString();
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)) {
            Toast.makeText(Login.this, "Field Are Empty", Toast.LENGTH_LONG).show();
        }else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(Login.this, "Sign In Problem", Toast.LENGTH_LONG).show();
                        //Log.v("error",task.getException().toString());
                    }
                    else{
                        Toast.makeText(Login.this, "Sucssfull", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Login.this,MainActivity.class));
                    }
                }
            });
        }
        progressDialog.setMessage("Log In......");
        progressDialog.show();
    }

    public void Create(View view) {
        CreateAccount();
    }
}