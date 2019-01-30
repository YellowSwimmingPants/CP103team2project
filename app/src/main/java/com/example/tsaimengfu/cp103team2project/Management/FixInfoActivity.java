package com.example.tsaimengfu.cp103team2project.Management;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tsaimengfu.cp103team2project.FunctionActivity;
import com.example.tsaimengfu.cp103team2project.R;
import com.example.tsaimengfu.cp103team2project.task.Common;
import com.example.tsaimengfu.cp103team2project.task.CommonTask;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class FixInfoActivity extends AppCompatActivity {
    private static final String TAG = "FixInfoActivity";
    private Button btCommit, btCancel;
    private TextView tvUserName, tvInputUserPassword, tvInputNewPassword, tvInputNewPasswordAgain, tvMessage;
    private EditText etUserName, etInputUserPassword, etInputNewPassword, etInputNewPasswordAgain;
    private CommonTask userUpdateTask, userPasswordExistTask;
    private boolean userPasswordExist = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_info);
        show();
    }

    private void show() {
        btCommit = findViewById(R.id.btCommit);
        btCancel = findViewById(R.id.btCancel);
        tvUserName = findViewById(R.id.tvUserAccount);
        tvInputUserPassword = findViewById(R.id.tvInputUserPassword);
        tvInputNewPassword = findViewById(R.id.tvInputNewPassword);
        tvInputNewPasswordAgain = findViewById(R.id.tvInputNewPasswordAgain);
        etUserName = findViewById(R.id.etUserAccount);
        etInputUserPassword = findViewById(R.id.etInputUserPassword);
        etInputNewPassword = findViewById(R.id.etInputNewPassword);
        etInputNewPasswordAgain = findViewById(R.id.etInputNewPasswordAgain);
        tvMessage = findViewById(R.id.tvMessage);

//        檢查密碼是否正確
        etInputUserPassword.setOnFocusChangeListener(new  View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    if (Common.networkConnected(FixInfoActivity.this)){
                        String url = Common.URL + "/UserServlet";
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("action", "userPasswordExist");
                        jsonObject.addProperty("userId", etInputUserPassword.getText().toString());
                        String jsonOut = jsonObject.toString();
                        userPasswordExistTask = new CommonTask(url, jsonOut);
                        try {
                            String result = userPasswordExistTask.execute().get();
                            userPasswordExist = Boolean.valueOf(result);
                        } catch (Exception e) {
                            Log.e(TAG, e.toString());
                        }
                        if (userPasswordExist) {
//                            tvMessage.setText(R.string.msg_UserIdExist);
                        } else {
                            tvMessage.setText(null);
                        }
                    }
                }
            }
        });
    }

    public void onUpdateClick(View view){
        String userName = etUserName.getText().toString().trim();
        String password = etInputUserPassword.getText().toString().trim();
        String newPassword = etInputNewPassword.getText().toString().trim();
        String newPasswordAgain = etInputNewPasswordAgain.getText().toString().trim();

        class NewUser  {
            private String  name,password;
            public NewUser(String name, String password) {
                this.name = name;
                this.password = password;
            }
        }

        String message = "";
        boolean isInputValid = true;
        if(userName.isEmpty()){
            isInputValid = false;
        }
        if(password.isEmpty()){
            isInputValid = false;
        }
        if(newPassword.isEmpty()){
            isInputValid = false;
        }
        if(newPasswordAgain.isEmpty()){
            isInputValid = false;
        }
        if(!newPasswordAgain.equals(newPassword)){
            isInputValid = false;
        }

        NewUser newUser = new NewUser(userName, password);
        if (isInputValid){
            if (Common.networkConnected(FixInfoActivity.this)){
                String url = Common.URL + "/UserServlet";
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("action", "update");
                jsonObject.addProperty("user", new Gson().toJson(newUser));
                String jsonOut = jsonObject.toString();
                userUpdateTask = new CommonTask(url, jsonOut);
                int count = 0;
                try {
                    String result = userUpdateTask.execute().get();
                    count = Integer.valueOf(result);
                }catch (Exception e) {
                        Log.e(TAG, e.toString());
                    }
                if (count == 0) {
                    tvMessage.setText(R.string.msg_FailUpdateAccount);
                } else {
                    SharedPreferences preferences = getSharedPreferences(
                            Common.PREF_FILE, MODE_PRIVATE);
                    preferences.edit().putBoolean("login", true)
                            .putString("userName", userName)
                            .putString("password", newPassword).apply();
                }
            }
        }
    }




    public static class AlertDialogFragment
            extends DialogFragment implements DialogInterface.OnClickListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.textAlerDialogTitle)
                    .setMessage(R.string.textFixInfoCancel)
                    .setPositiveButton(R.string.textYes, this)
                    .setNegativeButton(R.string.textNo, this)
                    .create()
                    ;
        }

        @Override
        public void onClick (DialogInterface dialog, int which){
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    /* 結束此Activity頁面 */
                    if (getActivity() != null) {
                        Intent intent = new Intent(getActivity(), FunctionActivity.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    }
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    /* 關閉對話視窗 01 */
                    dialog.cancel();
                    break;
                default:
                    break;
            }
        }
    }

    public void onCancelClick(View view){
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        alertDialogFragment.show(fragmentManager, "alert");
    }



}
