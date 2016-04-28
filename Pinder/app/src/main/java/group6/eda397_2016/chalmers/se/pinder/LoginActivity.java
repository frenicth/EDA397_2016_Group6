package group6.eda397_2016.chalmers.se.pinder;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.app.ActionBar;
import android.widget.Toast;

import group6.eda397_2016.chalmers.se.pinder.TrelloInteraction.TrelloAPIConsumer;
import group6.eda397_2016.chalmers.se.pinder.TrelloInteraction.TrelloAuth;

/**
 * Created by fredrikholmdahl on 16-04-20.
 */
public class LoginActivity extends Activity {

    public ActionBar actionBar;
    public LoginFragment loginFragment;
    public RegisterFragment registerFragment;
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*actionBar = getActionBar();
        actionBar.hide();*/

        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();

        fragmentManager.beginTransaction().add(R.id.activity_login, loginFragment).commit();

    }

    public void register(View view){

        fragmentTransaction= getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_login, registerFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    public void login(View view) {
        if (authorizeUserWithTrello()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }


    }

    public void cancelRegister(View view){
        fragmentManager.popBackStack();
    }

    public boolean authorizeUserWithTrello(){
        sharedPreferences = this.getSharedPreferences("authorizeprefs", Context.MODE_PRIVATE);
        String authtoken = sharedPreferences.getString("authtoken", "empty");
        boolean isAuthenticated = false;
        if (!authtoken.equals("empty")) {
            Toast.makeText(this, "already authorized", Toast.LENGTH_LONG).show();
            TrelloAPIConsumer.setAuthToken(authtoken);
            isAuthenticated = true;
        }
            else {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(TrelloAuth.trelloAuthorizeUrl)));
                isAuthenticated = checkOAuthReturn(getIntent());

            }
            catch (Exception ex) {
                Toast.makeText(this, "Invalid", Toast.LENGTH_LONG).show();
                Log.e("Dashboard no auth", "Cannot initiate communication to get the request token\nException: " + ex.getClass().getName() + "\nMessage: " + ex.getMessage());
            }
        }
        return isAuthenticated;

    }
    @Override
    protected void onNewIntent(Intent intent) {
        checkOAuthReturn(intent);
    }

    private boolean checkOAuthReturn(Intent intent) {
        boolean returnFromAuth = false;
        Uri uri = intent.getData();
        String code = "";

        if (uri != null && uri.toString().startsWith("ase://oauthresponse")) {
            String[] uriParts = uri.toString().split("#token=");
            if(uriParts.length > 0){
                sharedPreferences = this.getSharedPreferences("authorizeprefs", Context.MODE_PRIVATE);
                code = uriParts[1];
                sharedPreferences.edit().putString("authtoken", code).apply();
                TrelloAPIConsumer.setAuthToken(code);
                returnFromAuth = true;
            }
            Toast.makeText(this, "Login successful.", Toast.LENGTH_LONG).show();
            Toast.makeText(this, code, Toast.LENGTH_LONG).show();
        }
        return returnFromAuth;
    }


}

