package group6.eda397_2016.chalmers.se.pinder;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.app.ActionBar;
import android.widget.Toast;

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
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        sharedPreferences = this.getSharedPreferences(
                "authorizeprefs", Context.MODE_PRIVATE);
        String authtoken = sharedPreferences.getString("authtoken", "empty");
        if (!authtoken.equals("empty")) {
            Toast.makeText(this, "already authorized", Toast.LENGTH_LONG).show();

        }
    }

    public void cancelRegister(View view){
        fragmentManager.popBackStack();
    }

}
