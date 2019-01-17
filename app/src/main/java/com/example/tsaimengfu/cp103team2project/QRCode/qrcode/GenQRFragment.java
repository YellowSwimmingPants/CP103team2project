package com.example.tsaimengfu.cp103team2project.QRCode.qrcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tsaimengfu.cp103team2project.QRCode.qrcode.ReadLoadCode.Contents;
import com.example.tsaimengfu.cp103team2project.QRCode.qrcode.ReadLoadCode.QRCodeEncoder;
import com.example.tsaimengfu.cp103team2project.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;


import static android.content.ContentValues.TAG;

public class GenQRFragment extends Fragment {
    private Button btCreateCode;
    private EditText etQRCodeText;
    private ImageView myImage;
    // 01
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.create_qr_fragment, container, false);
        findViews(view);
        btCreateCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qrCodeText = etQRCodeText.getText().toString();
                Log.d(TAG, qrCodeText);

                // QR code image's length is the same as the width of the window,
                int dimension = getResources().getDisplayMetrics().widthPixels;

                // Encode with a QR Code image
                QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrCodeText, null,
                        Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(),
                        dimension);
                try {
                    Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
                    myImage.setImageBitmap(bitmap);

                } catch (WriterException e) {
                    Log.e(TAG, e.toString());
                }
            }
        });

        return view;
    }

    private void findViews(View view) {
        btCreateCode = view.findViewById(R.id.btGenerateQrCode);
        etQRCodeText = view.findViewById(R.id.etQRCodeText);
        myImage = view.findViewById(R.id.ivQRCode);
    }
}
