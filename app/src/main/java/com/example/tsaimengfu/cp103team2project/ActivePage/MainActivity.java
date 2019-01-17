package com.example.tsaimengfu.cp103team2project.ActivePage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tsaimengfu.cp103team2project.FunctionActivity;
import com.example.tsaimengfu.cp103team2project.QRCode.qrcode.CreateTeamActivity;
import com.example.tsaimengfu.cp103team2project.R;
import com.example.tsaimengfu.cp103team2project.task.Common;
import com.example.tsaimengfu.cp103team2project.task.CommonTask;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.JsonObject;

public class MainActivity extends AppCompatActivity {

    private Button fb_login, bt_register, bt_login, logoutButton, bt_home;
    private EditText et_account, et_password;
    CallbackManager callbackManager;
    private ProgressDialog pDialog;
    private static final String TAG = "MainActivity";
    private CommonTask userValidTask;
    private TextView tvMessage;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fb_login = (Button) findViewById(R.id.fb_login_button);
        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_login = (Button) findViewById(R.id.bt_login);
        tvMessage = findViewById(R.id.tvMessage);
        bt_register = (Button) findViewById(R.id.bt_register);
        bt_home = (Button) findViewById(R.id.bt_home);
        setResult(RESULT_CANCELED);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //trim是去掉首尾空格
                String account = et_account.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                // Check for empty data in the form
                if (!account.isEmpty() && !password.isEmpty()) {
                 showMessage(R.string.msg_InvalidUserOrPassword);
                }
                if (isUser(account, password)) {
                    SharedPreferences preferences = getSharedPreferences(
                            Common.PREF_FILE, MODE_PRIVATE);
                    preferences.edit().putBoolean("login", true)
                            .putString("userId", account)
                            .putString("password", password).apply();
                    setResult(RESULT_OK);

                } else {
                showMessage(R.string.msg_InvalidUserOrPassword);
                }
                Intent intent = new Intent(MainActivity.this, CreateTeamActivity.class);
                startActivity(intent);
                // login user
            }

        });
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextpage = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(nextpage);
            }
        });


        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(MainActivity.this, FunctionActivity.class);
                startActivity(home);
            }
        });
//for facebook source code
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code 01
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }


    private boolean isUser(final String userId, final String password) {
        boolean isUser = false;
        if (Common.networkConnected(MainActivity.this)) {
            String url = Common.URL + "/UserServlet";
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("action", "userValid");
            jsonObject.addProperty("userId", userId);
            jsonObject.addProperty("password", password);
            String jsonOut = jsonObject.toString();
            userValidTask = new CommonTask(url, jsonOut);
            try {
                String result = userValidTask.execute().get();
                isUser = Boolean.valueOf(result);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
                isUser = false;
            }
        } else {
            Common.showToast(this, R.string.msg_NoNetwork);
        }
        return isUser;
    }
    private void showMessage(int msgResId) {
        tvMessage.setText(msgResId);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (userValidTask != null) {
            userValidTask.cancel(true);
        }
    }

}




