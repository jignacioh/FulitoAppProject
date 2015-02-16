package com.studiodevelopers.fulito.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.android.Facebook;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.octo.android.robospice.Jackson2SpringAndroidSpiceService;
import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.studiodevelopers.fulito.R;
import com.studiodevelopers.fulito.application.ObjectPreference;
import com.studiodevelopers.fulito.model.Campeonato;
import com.studiodevelopers.fulito.request.CampeonatoRequest;
import com.studiodevelopers.fulito.request.JsonSpiceService;
import com.studiodevelopers.fulito.util.ComplexPreferences;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends ActionBarActivity {

    UiLifecycleHelper uiHelper;
    private GraphUser user;
    ProgressBar mProgressBar;
    private ObjectPreference objectPreference;
    public static String TAG=LoginActivity.class.getSimpleName();
    protected SpiceManager spiceManager = new SpiceManager(JsonSpiceService.class);

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        objectPreference = (ObjectPreference) this.getApplication();
        mProgressBar=(ProgressBar)findViewById(R.id.progressBar);
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisibility(View.INVISIBLE);
        //performRequest();

        LoginButton authButton = (LoginButton)findViewById(R.id.authButton);
        authButton.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
            @Override
            public void onUserInfoFetched(GraphUser user) {
                LoginActivity.this.user = user;
                if (user != null) {
                    Toast.makeText(getApplicationContext(),"Hello, " + user.getName(),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),"You are not logged",Toast.LENGTH_LONG).show();

                }
            }
        });
        showHashKey(getApplicationContext());
    }
    public static void showHashKey(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo("com.studiodevelopers.fulito", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }
    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }
    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Log.i(TAG, "Logged in...");
        } else if (state.isClosed()) {
            Log.i(TAG, "Logged out...");
        }
    }
    private void updateUI() {
        Session session = Session.getActiveSession();
        boolean enableButtons = (session != null && session.isOpened());


    }

    private void performRequest() {

        CampeonatoRequest request = new CampeonatoRequest();
        spiceManager.execute(request, new CampeonatoRequestListener());
        mProgressBar.setVisibility(View.VISIBLE);

    }
    class CampeonatoRequestListener implements RequestListener<Campeonato> {


        public void onRequestFailure(SpiceException e) {
            mProgressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),"Error al llamar al servicio.",Toast.LENGTH_LONG).show();

        }


        public void onRequestSuccess(Campeonato campeonato) {
            mProgressBar.setVisibility(View.INVISIBLE);
            saveData(campeonato);

        }
    }
    public void saveData(Campeonato campeonato){

        ComplexPreferences complexPrefenreces = objectPreference.getComplexPreference();
        if(complexPrefenreces != null) {
            complexPrefenreces.putObject("campeonato", campeonato);
            complexPrefenreces.commit();
            Log.i(TAG,"Preference created");
            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
        } else {
            android.util.Log.e(TAG, "Preference is null");
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        uiHelper.onSaveInstanceState(savedState);
    }
}
