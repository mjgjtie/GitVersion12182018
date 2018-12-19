package com.example.tabtodo;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class Main3Activity extends AppCompatActivity {

    ProfilePictureView profilePictureView;
    LoginButton loginButton;
    Button btndangxuat,btnchucnang;
    TextView txtname,txtemail,txtfirstname,txtCreate;
    CallbackManager callbackManager;
    String name,email,firstname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main3);
        Anhxa();

        btnchucnang.setVisibility(View.INVISIBLE);
        txtname.setVisibility(View.INVISIBLE);
        txtemail.setVisibility(View.INVISIBLE);
        txtfirstname.setVisibility(View.INVISIBLE);
        btndangxuat.setVisibility(View.INVISIBLE);

        loginButton.setReadPermissions(Arrays.asList("public_profile","email"));
        setLogin_Button();
        setLogout_Button();
        changeWindown();
        createAccount();



//        btnchucnang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Main3Activity.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });

//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.example.tabtodo",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }
    }

    private void setLogout_Button() {
        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                btndangxuat.setVisibility(View.INVISIBLE);
                btnchucnang.setVisibility(View.INVISIBLE);
                txtname.setVisibility(View.INVISIBLE);
                txtemail.setVisibility(View.INVISIBLE);
                txtfirstname.setVisibility(View.INVISIBLE);
                txtemail.setText("");
                txtname.setText("");
                txtfirstname.setText("");
                profilePictureView.setProfileId(null);
                loginButton.setVisibility(View.VISIBLE);

            }
        });
    }

    private void setLogin_Button() {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                loginButton.setVisibility(View.INVISIBLE);
                btnchucnang.setVisibility(View.VISIBLE);
                txtname.setVisibility(View.VISIBLE);
                txtemail.setVisibility(View.VISIBLE);
                txtfirstname.setVisibility(View.VISIBLE);
                btndangxuat.setVisibility(View.VISIBLE);
                result();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }


    private void result() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("JSON",response.getJSONObject().toString());
                try {
                    email = object.getString("email");
                    name = object.getString("name");
                    firstname = object.getString("first_name");

                    profilePictureView.setProfileId(Profile.getCurrentProfile().getId());
                    txtname.setText(name);
                    txtemail.setText(email);
                    txtfirstname.setText(firstname);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields","name,email,first_name");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }


    public void Anhxa(){
        profilePictureView = (ProfilePictureView) findViewById(R.id.imageprofilepictureview);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        btnchucnang = (Button) findViewById(R.id.btn_chucnang);
        btndangxuat = (Button) findViewById(R.id.btn_dangxuat);
        txtname = (TextView) findViewById(R.id.textviewname);
        txtemail = (TextView) findViewById(R.id.textviewemail);
        txtfirstname = (TextView) findViewById(R.id.textviewfirstname);
        txtCreate = (TextView) findViewById(R.id.createAccount);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }

    public void changeWindown(){
        btnchucnang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void createAccount(){
        txtCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this,Create1account.class);
                startActivity(intent);
            }
        });
    }
}
