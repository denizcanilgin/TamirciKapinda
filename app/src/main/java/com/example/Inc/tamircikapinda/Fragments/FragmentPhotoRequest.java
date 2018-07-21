package com.example.Inc.tamircikapinda.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.Inc.tamircikapinda.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FragmentPhotoRequest extends android.support.v4.app.Fragment {

    private View view;
    public AutoCompleteTextView Entered_Problem;
    private String getTextProb;
    private Button uploaded_image;
    private ImageView ivImage;
    private String userChoosenTask;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    public GlobalClass globalClass;
    private Bitmap thumbnail;
    private LinearLayout image_layout;
    private TextView image_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_photo_request, container, false);


        uploaded_image = view.findViewById(R.id.uploaded_image);
        ivImage = view.findViewById(R.id.ivImage);
        image_layout = view.findViewById(R.id.image_layout);
        image_text = view.findViewById(R.id.image_text);


        uploaded_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        return view;


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = {"Fotoğraf Çek","İptal"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Fotoğraf Ekle!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(getContext());

                if (items[item].equals("Fotoğraf Çek")) {
                    userChoosenTask = "Fotoğraf Çek";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("İptal")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");


        String s = destination.toString();
       String imagepath = s.substring(Math.max(s.length(),17) - 17);
        Log.i("requestttt",s);

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String imageString = imageToString(thumbnail);
        GlobalClass.setImage(imageString);
        GlobalClass.setImage_path(imagepath);

        uploaded_image.setVisibility(View.INVISIBLE);
        image_text.setVisibility(View.INVISIBLE);
        Log.i("requesttt",""+imageString.length());
        image_layout.setVisibility(View.VISIBLE);
        ivImage.setImageBitmap(thumbnail);


    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        thumbnail = null;
        if (data != null) {
            try {
                thumbnail = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String imageString = imageToString(thumbnail);
        GlobalClass.setImage(imageString);
        Log.i("ssssssss", imageString);
        Log.i("ssssssss", "-----------" + imageString.length());
        uploaded_image.setVisibility(View.INVISIBLE);
        image_text.setVisibility(View.INVISIBLE);
        image_layout.setVisibility(View.VISIBLE);
        Log.i("requesttt",""+imageString.length());
        ivImage.setImageBitmap(thumbnail);

    }

    public String imageToString(Bitmap thumbnail) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byt = byteArrayOutputStream.toByteArray();
        String base64String = Base64.encodeToString(byt, Base64.DEFAULT);

        return base64String;
    }

}
