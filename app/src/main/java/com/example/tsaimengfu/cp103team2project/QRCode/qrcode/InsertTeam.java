package com.example.tsaimengfu.cp103team2project.QRCode.qrcode;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tsaimengfu.cp103team2project.ActivePage.MainActivity;
import com.example.tsaimengfu.cp103team2project.ActivePage.RegisterActivity;
import com.example.tsaimengfu.cp103team2project.FunctionActivity;
import com.example.tsaimengfu.cp103team2project.QRCode.qrcode.ReadLoadCode.Team;
import com.example.tsaimengfu.cp103team2project.R;
import com.example.tsaimengfu.cp103team2project.task.Common;
import com.example.tsaimengfu.cp103team2project.task.CommonImage;
import com.example.tsaimengfu.cp103team2project.task.CommonTask;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static com.example.tsaimengfu.cp103team2project.task.Common.showToast;


public class InsertTeam extends AppCompatActivity {

    private Button btEnter;
    private EditText etTeamName;
    private static final int REQUEST_PICK_PICTURE = 1;
    private static final int REQ_CROP_PICTURE = 2;
    private byte[] image;
    private static final String TAG = "insertTeamActivity";
    private CommonTask teamExistTask;
    private boolean teamExist = false;
    private Uri  croppedImageUri;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamregister);
        imageView = findViewById(R.id.iv_photo);
        etTeamName = findViewById(R.id.etTeamName);
        etTeamName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (Common.networkConnected(InsertTeam.this)) {
                        String url = Common.URL + "/TeamServlet";
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("action", "teamExist");
                        jsonObject.addProperty("teamname", etTeamName.getText().toString());
                        String jsonOut = jsonObject.toString();
                        teamExistTask = new CommonTask(url, jsonOut);
                        try {
                            String result = teamExistTask.execute().get();
                            teamExist = Boolean.valueOf(result);
                        } catch (Exception e) {
                            Log.e(TAG, e.toString());
                        }
//                         show an error message if the id exists;
//                         otherwise, the error message should be clear
                        if (teamExist) {
                            showToast(InsertTeam.this, R.string.msg_TeamIdExist);
                        } else {

                        }
                    }

                }

            }


        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_PICK_PICTURE);

            }

//            public boolean isIntentAvailable(Context context, Intent intent) {
//                PackageManager packageManager = context.getPackageManager();
//                List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
//                        PackageManager.MATCH_DEFAULT_ONLY);
//                return list.size() > 0;


//            }


        });


    }

    public void onEnterClick(View view) {
        String TeamName = etTeamName.getText().toString().trim();
        
        if (TeamName.length() <= 0) {
            Common.showToast(InsertTeam.this, R.string.msg_NameIsInvalid);
            return;
        }
        if (image == null) {
            Common.showToast(InsertTeam.this, R.string.msg_NoImage);
            return;
        }
        if (Common.networkConnected(InsertTeam.this
        )) {
            String url = Common.URL + "/TeamServlet";
            Team team = new Team(0,TeamName);
            String imageBase64 = Base64.encodeToString(image, Base64.DEFAULT);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("param", "teamInsert");
            jsonObject.addProperty("team", new Gson().toJson(team));
            jsonObject.addProperty("imageBase64", imageBase64);
            int count = 0;
            try {
                String result = new CommonTask(url, jsonObject.toString()).execute().get();
                count = Integer.valueOf(result);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            if (count == 0) {
                Common.showToast(InsertTeam.this, R.string.msg_InsertFail);
            } else {
                Common.showToast(InsertTeam.this, R.string.msg_InsertSuccess);
            }
        } else {
            Common.showToast(InsertTeam.this, R.string.msg_NoNetwork);
        }
    }

    public static void showToast(Context context, int messageResId) {
        Toast.makeText(context, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PICK_PICTURE:
                    Uri uri = intent.getData();
                    crop(uri);
                    break;
                case REQ_CROP_PICTURE:
                    Log.d(TAG, "REQ_CROP_PICTURE: " + croppedImageUri.toString());
                    try {
                        Bitmap picture = BitmapFactory.decodeStream(
                                InsertTeam.this.getContentResolver().openInputStream(croppedImageUri));
                        imageView.setImageBitmap(picture);
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        picture.compress(Bitmap.CompressFormat.JPEG, 100, out);
                        image = out.toByteArray();
                    } catch (FileNotFoundException e) {
                        Log.e(TAG, e.toString());
                    }
                    break;
            }

        }
    }

    private void crop(Uri sourceImageUri) {
       File file = InsertTeam.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        file = new File(file, "picture_cropped.jpg");
        croppedImageUri = Uri.fromFile(file);
        // take care of exceptions
        try {
            // call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // the recipient of this Intent can read soruceImageUri's data
            cropIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            // set image source Uri and type
            cropIntent.setDataAndType(sourceImageUri, "image/*");
            // send crop message
            cropIntent.putExtra("crop", "true");
            // aspect ratio of the cropped area, 0 means user define
            cropIntent.putExtra("aspectX", 0); // this sets the max width
            cropIntent.putExtra("aspectY", 0); // this sets the max height
            // output with and height, 0 keeps original size
            cropIntent.putExtra("outputX", 0);
            cropIntent.putExtra("outputY", 0);
            // whether keep original aspect ratio
            cropIntent.putExtra("scale", true);
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, croppedImageUri);
            // whether return data by the intent
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, REQ_CROP_PICTURE);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            Common.showToast(InsertTeam.this, "This device doesn't support the crop action!");
        }
    }

}