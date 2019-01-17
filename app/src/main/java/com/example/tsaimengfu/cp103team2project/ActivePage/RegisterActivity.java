package com.example.tsaimengfu.cp103team2project.ActivePage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tsaimengfu.cp103team2project.QRCode.qrcode.CreateTeamActivity;
import com.example.tsaimengfu.cp103team2project.R;
import com.example.tsaimengfu.cp103team2project.task.Common;
import com.example.tsaimengfu.cp103team2project.task.CommonTask;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

public class RegisterActivity  extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private EditText etUserId;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etName;
    private EditText etEmail;
    private TextView tvMessage;
    private boolean userExist = false;
    private CommonTask userExistTask, userRegisterTask;
    private List<UserInfo> user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        createViews();
    }

    private void createViews() {
        etUserId = findViewById(R.id.etUserId);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        tvMessage = findViewById(R.id.tvMessage);

        //檢查id是否有被使用 01
        etUserId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (Common.networkConnected(RegisterActivity.this)) {
                        String url = Common.URL + "/UserServlet";
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("action", "userExist");
                        jsonObject.addProperty("userId", etUserId.getText().toString());
                        String jsonOut = jsonObject.toString();
                        userExistTask = new CommonTask(url, jsonOut);
                        try {
                            String result = userExistTask.execute().get();
                            userExist = Boolean.valueOf(result);
                        } catch (Exception e) {
                            Log.e(TAG, e.toString());
                        }
                        // show an error message if the id exists;
                        // otherwise, the error message should be clear
                        if (userExist) {
                            tvMessage.setText(R.string.msg_UserIdExist);
                        } else {
                            tvMessage.setText(null);
                        }
                    }
                }
            }
        });
    }

    public void onSubmitClick(View view) {

        String userId = etUserId.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        String message = "";
        boolean isInputValid = true;
        if (userExist) {
            message += getString(R.string.msg_UserIdExist) + "\n";
            isInputValid = false;
        }
        if (userId.isEmpty()) {
            message += getString(R.string.text_UserId) + " "
                    + getString(R.string.msg_InputEmpty) + "\n";
            isInputValid = false;
        }
        if (password.isEmpty()) {
            message += getString(R.string.hint_etPassword) + " "
                    + getString(R.string.msg_InputEmpty) + "\n";
            isInputValid = false;
        }
        if (!confirmPassword.equals(password)) {
            message += getString(R.string.msg_ConfirmPasswordNotSameAsPassword);
            isInputValid = false;
        }
        if (name.isEmpty()) {
            message += getString(R.string.text_etName) + " "
                    + getString(R.string.msg_InputEmpty) + "\n";
            isInputValid = false;
        }
        if (email.isEmpty()) {
            message += getString(R.string.text_etEmail) + " "
                    + getString(R.string.msg_InputEmpty) + "\n";
            isInputValid = false;
        }
        tvMessage.setText(message);
       UserInfo user = new UserInfo(userId, password, name, email);
        if (isInputValid) {
            if (Common.networkConnected(RegisterActivity.this)) {
                String url = Common.URL + "/UserServlet";
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("action", "insert");
                jsonObject.addProperty("user", new Gson().toJson(user));
                String jsonOut = jsonObject.toString();
                userRegisterTask = new CommonTask(url, jsonOut);
                int count = 0;
                try {
                    String result = userRegisterTask.execute().get();
                    count = Integer.valueOf(result);
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
                if (count == 0) {
                    tvMessage.setText(R.string.msg_FailCreateAccount);
                } else {
                    // user ID and password will be saved in the preferences file
                    // and starts UserActivity
                    // while the user account is created successfully
                    SharedPreferences preferences = getSharedPreferences(
                            Common.PREF_FILE, MODE_PRIVATE);
                    preferences.edit().putBoolean("login", true)
                            .putString("userId", userId)
                            .putString("password", password).apply();
                    Common.showToast(this, R.string.msg_SuccessfullyCreateAccount);
                    Intent intent = new Intent(this, CreateTeamActivity.class);
                    startActivity(intent);
                }
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (userExistTask != null) {
            userExistTask.cancel(true);
        }
        if (userRegisterTask != null) {
            userRegisterTask.cancel(true);
        }
    }
}
