package com.example.admin.navigationdemo;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Admin on 9/11/2017.
 */

public class ImportImage extends Fragment {

    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;

    Button imageselect;
    ImageView imageView;
    View view;
    private static final int CAMERA_PIC_REQUEST = 1337;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.importimage, container, false);

        imageView = view.findViewById(R.id.imageView);
        imageselect = view.findViewById(R.id.button_pic_image);
        imageselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageView.setVisibility(View.VISIBLE);

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.pickupimage);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                Button camera = dialog.findViewById(R.id.button_open_camera);
                Button gallery = dialog.findViewById(R.id.button_open_gallery);

                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
                        dialog.dismiss();

                    }
                });

                gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent photopickintent = new Intent();
                        photopickintent.setType("image/*");
                        photopickintent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(photopickintent, "Select Picture"), SELECT_PICTURE);
                        dialog.dismiss();

                    }
                });


            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(image);

        } else if (resultCode == RESULT_OK || requestCode == SELECT_PICTURE) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                Log.i("TAG", "Some exception " + e);
            }
        }


    }
/*
    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private Cursor managedQuery(Uri uri, String[] projection, Object o, Object o1, Object o2) {
        throw new RuntimeException("Stub!");
    }*/


}
