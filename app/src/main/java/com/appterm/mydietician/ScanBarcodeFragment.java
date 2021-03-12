package com.appterm.mydietician;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zbar.BarcodeFormat;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.hardware.camera2.CaptureResult.FLASH_STATE;


public class ScanBarcodeFragment extends Fragment{

    private ZBarScannerView mScannerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_scan_barcode, container, false);
        Button scan = root.findViewById(R.id.scan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent intent = new Intent(v.getContext(),ScannerActivity.class);
            startActivityForResult(intent,123);
            }
        });


        return root;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123 && resultCode == AppCompatActivity.RESULT_OK) {
            if (data.hasExtra("code")) {
                Toast.makeText(getActivity(), "Content", Toast.LENGTH_SHORT).show();
            }
        }
    }
}