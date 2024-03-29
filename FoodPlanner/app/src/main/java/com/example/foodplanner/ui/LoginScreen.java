package com.example.foodplanner.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
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

public class LoginScreen extends AppCompatActivity {

    EditText emailET;
    EditText passwordET;
    Button loginbtn;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView signupNow;
    ImageView googleimage;
    CheckBox rememberme;

    GoogleSignInClient googleSignInClient;
    SharedPreferences sharedPreferences;

    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                try {
                    GoogleSignInAccount googleSignInAccount = accountTask.getResult(ApiException.class);
                    AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                    mAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mAuth = FirebaseAuth.getInstance();
                                Toast.makeText(LoginScreen.this, "Login Successfully with Google", Toast.LENGTH_SHORT).show();
                                navigateToMainScreen();
                            } else {
                                Toast.makeText(LoginScreen.this, "Failed to Login with Google" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            navigateToMainScreen();
        }
    }

    private static final String TAG = "LoginScreen";
    private static final String PREF_NAME = "login_pref";
    private static final String KEY_REMEMBER_ME = "remember_me";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        FirebaseApp.initializeApp(this);
        progressBar = findViewById(R.id.progressbarlogin);
        emailET = findViewById(R.id.etemaillogin);
        passwordET = findViewById(R.id.etpasslogin);
        loginbtn = findViewById(R.id.loginbtn);
        signupNow = findViewById(R.id.signupnow);
        googleimage = findViewById(R.id.labeled);
        rememberme = findViewById(R.id.remember);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_id))
                .requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(LoginScreen.this, googleSignInOptions);
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        boolean rememberMe = sharedPreferences.getBoolean(KEY_REMEMBER_ME, false);
        rememberme.setChecked(rememberMe);
        if (rememberMe) {
            String email = sharedPreferences.getString(KEY_EMAIL, "");
            String password = sharedPreferences.getString(KEY_PASSWORD, "");
            emailET.setText(email);
            passwordET.setText(password);
        }

        googleimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = googleSignInClient.getSignInIntent();
                activityResultLauncher.launch(intent);
            }
        });

        signupNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getApplicationContext(), SignUpScreen.class);
                startActivity(myintent);
                finish();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(emailET.getText());
                password = String.valueOf(passwordET.getText());
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginScreen.this, "Please Enter EmailAddress", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginScreen.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.VISIBLE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    navigateToMainScreen();
                                } else {
                                    Log.d(TAG, "onComplete: " + task.getException());
                                    Toast.makeText(LoginScreen.this, "Login Failed" + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                if (rememberme.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(KEY_REMEMBER_ME, true);
                    editor.putString(KEY_EMAIL, email);
                    editor.putString(KEY_PASSWORD, password);
                    editor.apply();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(KEY_REMEMBER_ME, false);
                    editor.remove(KEY_EMAIL);
                    editor.remove(KEY_PASSWORD);
                    editor.apply();
                }
            }
        });
    }

    private void navigateToMainScreen() {
        Intent intent = new Intent(getApplicationContext(), MainScreen.class);
        startActivity(intent);
        finish();
    }
}