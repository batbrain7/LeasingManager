package com.example.mohitkumar.internapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohitkumar.internapp.Activities.HomeScreen;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    GoogleApiClient googleApiClient;
    GoogleSignInOptions gso;
    LinearLayout layout;
    int signInCheckInt = 0;
    private static final String TAG = "MAIN_ACTIVITY";
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    String UID;
    TextView wel,log,signin;
    AutoCompleteTextView autoCompleteTextView;
    SignInButton signInButton;
    Button next;
    String address;
    ProgressDialog progressDialog;
    String items[] = {"449 Palo Verde Road, Gainesville, FL","6731 Thompson Street, Gainesville, FL",
            "8771 Thomas Boulevard, Orlando, FL","1234 Verano Place, Orlando, FL"};

    public static final int RC_SIGN_IN = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        next = (Button) findViewById(R.id.next);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.auto_text);
        ArrayAdapter<String> autocompleteAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,items);
        autoCompleteTextView.setAdapter(autocompleteAdapter);
        log = (TextView) findViewById(R.id.text_enter);
        wel = (TextView) findViewById(R.id.welcome);
        signin = (TextView) findViewById(R.id.goog_text);
        Typeface typeface = Typeface.createFromAsset(MainActivity.this.getAssets(),"fonts/Lato-Regular.ttf");
        wel.setTypeface(typeface);
        log.setTypeface(typeface);
        signin.setTypeface(typeface);

        layout = (LinearLayout) findViewById(R.id.button_google);

        currentUser = mAuth.getCurrentUser();
        if(currentUser!=null) {
            UID = currentUser.getUid();
            layout.setVisibility(View.GONE);
            autoCompleteTextView.setVisibility(View.VISIBLE);
            log.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
        }

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this).
                enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getApplicationContext(),"Unable to Login!",Toast.LENGTH_LONG).show();
                    }
                }).
                addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();






       // signInButton = (SignInButton)findViewById(sign_in);


        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("Logging in");
                progressDialog.setMessage("Wait...");
                progressDialog.show();
                signIn();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("User").child(UID).child("first_name").setValue(mAuth.getCurrentUser().getDisplayName().toString());
                databaseReference.child("User").child(UID).child("last_name").setValue(mAuth.getCurrentUser().getDisplayName().toString());
                databaseReference.child("User").child(UID).child("place_id").setValue(autoCompleteTextView.getText().toString());

                databaseReference.child("Places").child(UID).child("id").setValue(UID);
                databaseReference.child("Places").child(UID).child("address").setValue(autoCompleteTextView.getText().toString());
                Intent intent = new Intent(getApplicationContext(),HomeScreen.class);
                intent.putExtra("address",autoCompleteTextView.getText().toString());
                intent.putExtra("email",mAuth.getCurrentUser().getEmail().toString());
                startActivity(intent);
                finish();
            }
        });
    }



    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
                Log.d("IN","In RESULT");
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            UID = user.getUid();
                            progressDialog.dismiss();
                            UID = user.getUid();
                            layout.setVisibility(View.GONE);
                            autoCompleteTextView.setVisibility(View.VISIBLE);
                            log.setVisibility(View.VISIBLE);
                            next.setVisibility(View.VISIBLE);
                           // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                         //   updateUI(null);
                        }

                        // ...
                    }
                });
    }
}
