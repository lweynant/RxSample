package com.lweynant.rxsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.lweynant.rxsample.components.DaggerRegisterFragmentComponent;
import com.lweynant.rxsample.components.RegisterFragmentComponent;
import com.lweynant.rxsample.modules.ValidatorModule;

import timber.log.Timber;


public class MainActivity extends AppCompatActivity implements RegisterFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.d("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Timber.d("onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Timber.d("onOptionsItemSelected");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public RegisterFragmentComponent getComponent() {
        RegisterFragmentComponent registerFragmentComponent = DaggerRegisterFragmentComponent.builder()
                .validatorModule(new ValidatorModule())
                .build();
        return registerFragmentComponent;
    }
}
