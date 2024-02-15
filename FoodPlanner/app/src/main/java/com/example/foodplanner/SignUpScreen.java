package com.example.foodplanner;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignUpScreen extends AppCompatActivity {

    EditText emailET;
    EditText passwordET;
    Button Signupbtn;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView  loginNow;
    ImageView googleimage;

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(),MainScreen.class);
            startActivity(intent);
            finish();
        }
    }
    private static final String TAG = "SignUpScreen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        mAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressbar);
        emailET=findViewById(R.id.etemailsignup);
        passwordET=findViewById(R.id.etpasssignup);
        Signupbtn=findViewById(R.id.signuppbtn);
        loginNow=findViewById(R.id.loginnow);

        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent= new Intent(getApplicationContext(),LoginScreen.class);
                startActivity(myintent);
                finish();
            }
        });

        Signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
               String email,password;
               email=String.valueOf(emailET.getText());
               password=String.valueOf(passwordET.getText());
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignUpScreen.this, "Please Enter EmailAddress", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignUpScreen.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.VISIBLE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUpScreen.this, "Account Created.",
                                            Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "createUserWithEmail:success");
                                    Intent intent=new Intent(getApplicationContext(),MainScreen.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignUpScreen.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }
}