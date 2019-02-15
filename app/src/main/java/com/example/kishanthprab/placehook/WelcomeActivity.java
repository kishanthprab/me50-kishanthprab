package com.example.kishanthprab.placehook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kishanthprab.placehook.DataObjects.User;
import com.example.kishanthprab.placehook.Utility.FireAuthUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG = "WelcomeActivity";

    MaterialButton signin,signup;

    RelativeLayout rootLayout;

    FirebaseAuth FireAuth;
    FirebaseAuth.AuthStateListener FireAuthStateListener;
    FirebaseDatabase FireDB;
    DatabaseReference UsersRef;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/opensans_regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        setContentView(R.layout.activity_welcome);
        //getSupportActionBar().hide();

        TextView txtV = (TextView)findViewById(R.id.txtV);
        ImageView blueback = (ImageView)findViewById(R.id.blueback);
        final ImageView logo = (ImageView)findViewById(R.id.logo);
        signin = (MaterialButton)findViewById(R.id.signIn);
        signup = (MaterialButton)findViewById(R.id.signup);
        rootLayout = (RelativeLayout)findViewById(R.id.rootLayout);

        //blueback.setTranslationY(-500f);
        //blueback.animate().translationYBy(300f).setDuration(1000);

        txtV.setTranslationY(-500f);
        txtV.animate().translationYBy(500f).setDuration(1000);

        logo.setTranslationY(1500f);
        logo.animate().translationYBy(-1500f).setDuration(1000).setStartDelay(100);

        signin.setTranslationX(-800);
        signin.animate().translationXBy(800).setDuration(500).setStartDelay(900);

        signup.setTranslationX(800);
        signup.animate().translationXBy(-800).setDuration(500).setStartDelay(900);


        //initialize firebase
        FireAuth = FirebaseAuth.getInstance();
        FireAuthStateListener = FireAuthUtil.SettingFirebaseAuthStateListener();

        FireDB = FirebaseDatabase.getInstance();
        UsersRef = FireDB.getReference("Users");


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);

                Log.d(TAG +" signup","click event");


            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);

                Log.d(TAG +" signin","click event");

              //sign out code  //FirebaseAuth.getInstance().signOut();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FireAuth.addAuthStateListener(FireAuthStateListener);

        FirebaseUser CurrentUser = FireAuthUtil.getmUser();
        //after this it should go to the

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (FireAuthStateListener != null){
            FireAuth.removeAuthStateListener(FireAuthStateListener);
        }
    }


}
