package com.example.equubbg.first;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TextWatcher{
    private EditText edittext;
    private Button buttonLogin;
    private Button buttonregistration;
    private EditText edittextPhoneNumber;
    private EditText edittextUserName;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private String phoneNumberPattern = "^(\\+91|0)[7-9][0-9]{10,13}$";
    String phoneNumberValidated = Boolean.toString(false);
    String userNameValidated=Boolean.toString(false);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//
        setContentView(R.layout.activity_main);
        addKeyListener();
    }

    public void addKeyListener() {
        buttonLogin = (Button) findViewById(R.id.button2);
        buttonregistration=(Button)findViewById(R.id.registrationButton);
        edittextUserName = (EditText) findViewById(R.id.editText3);
        edittextPhoneNumber = (EditText) findViewById(R.id.editText4);
        buttonLogin.setEnabled(false);
        edittextUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                 }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("afterTextChanged EMAIL","afterTextChanged: within userName validation " );
                Log.d("Invalid EMAIL","EMAIL ID   -- >" +edittextUserName.getText());
                if (edittextUserName.getText().toString().matches(emailPattern)){
                    userNameValidated=Boolean.toString(true);
                    Log.d("set userNameValidated", "afterTextChanged: setting value to true ");
                    Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
               }
                else{
                    if(buttonLogin.isEnabled()){
                        Log.d("set userNameValidated", "afterTextChanged: setting value to false ");
                        buttonLogin.setEnabled(false);
                    }
                    Log.d("set userNameValidated", "afterTextChanged: userName invalid ");
                    Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                }
            }
        });
        edittextPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                Log.d("afterTextChanged PHONE","afterTextChanged: within phone validation -->" +edittextPhoneNumber.getText());
                if (isValidPhoneNumber(edittextPhoneNumber.getText()) && Boolean.valueOf(userNameValidated)) {
                    buttonLogin.setEnabled(true);
                    Log.d("Valid PHONE", "Valid phone address");
                    Toast.makeText(getApplicationContext(), "valid phone address", Toast.LENGTH_SHORT).show();
                }else{
                    if(buttonLogin.isEnabled()){
                        buttonLogin.setEnabled(false);
                    }
                    Log.d("Invalid PHONE","Invalid phone address" );
                    Toast.makeText(getApplicationContext(),"Invalid phone address",Toast.LENGTH_SHORT).show();
                }
            }
            private final boolean isValidPhoneNumber(CharSequence target) {
                if (target == null) {
                    return false;
                } else {
                    if (target.length() < 6 || target.length() > 13) {
                        return false;
                    } else {
                        return android.util.Patterns.PHONE.matcher(target).matches();
                    }
                }
            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.producer_register_main);
            }
        });

        buttonregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Log.d("buttonregistration","buttonregistration listener");
              setContentView(R.layout.consumer_register_main);
                ConsumerRegistrerActivity consumerRegistry =  new ConsumerRegistrerActivity();
                consumerRegistry.addKeyListener();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
