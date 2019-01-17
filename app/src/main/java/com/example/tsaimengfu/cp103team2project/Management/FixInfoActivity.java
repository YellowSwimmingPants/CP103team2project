package com.example.tsaimengfu.cp103team2project.Management;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tsaimengfu.cp103team2project.FunctionActivity;
import com.example.tsaimengfu.cp103team2project.R;

public class FixInfoActivity extends AppCompatActivity {
    private Button btCommit, btCancel;
    private TextView tvUserName, tvInputUserPassword, tvInputNewPassword, tvInputNewPasswordAgain;
    private EditText etUserName, etInputUserPassword, etInputNewPassword, etInputNewPasswordAgain;

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

        btCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etUserName.getText().toString().trim();
                if (name.length() <= 0) {

                }
                String password = etInputUserPassword.getText().toString().trim();
                if (password.length() <= 0) {

                }
                String newPassword = etInputNewPassword.getText().toString().trim();
                if (newPassword.length() <= 0) {

                }
                String newPasswordAgain = etInputNewPasswordAgain.getText().toString().trim();
                if (newPasswordAgain != newPassword) {

                }
//                if(Common.networkConnected(activity)){
//                    String url = Common.URL + "/UserServlet";
//                    NewUser newUser = new NewUser(0, name, password);
//                    JsonObject jsonObject = new JsonObject();
//                    jsonObject.addProperty("action","userUpdate");
//                    jsonObject.addProperty("user", new Gson().toJson(newUser));
//                    try {
//                        String result = new CommonTask(url, jsonObject.toString()).execute().get();
////                        count = Integer.valueOf(result);
//                    } catch (Exception e) {
////                        Log.e(TAG, e.toString());
//                    }
//                }

            }
        });

    }

    private class NewUser  {
        private int id;
        private String  userName,newPassword;
        public NewUser(int id, String userName, String newPassword) {
            this.id = id;
            this.userName = userName;
            this.newPassword = newPassword;
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
