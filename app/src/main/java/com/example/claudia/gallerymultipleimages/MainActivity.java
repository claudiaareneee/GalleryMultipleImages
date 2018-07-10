package com.example.claudia.gallerymultipleimages;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.asksira.bsimagepicker.BSImagePicker;
import com.bumptech.glide.Glide;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;
import net.gotev.uploadservice.UploadService;
import net.gotev.uploadservice.http.HttpConnection;

import static net.gotev.uploadservice.UploadService.*;

public class MainActivity extends AppCompatActivity implements BSImagePicker.OnMultiImageSelectedListener{

    private ImageView ivImage1, ivImage2, ivImage3, ivImage4, ivImage5, ivImage6;
    private EditText editTextName;

    public List<Uri> uriPaths = new ArrayList<>();
    public boolean uriPathsFull = false;

//    private static final String UPLOAD_URL ="http://192.168.64.2/UploadExample/upload.php";
//    private static final String UPLOAD_URL = getApplicationContext().getResources().getString(R.string.indexURL); //have to move this into the upload func
    private static final String UPLOAD_URL = "http://134.164.1.251/upload.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //server demo
        UploadService.NAMESPACE = BuildConfig.APPLICATION_ID;

        Button selectBtn, uploadBtn;
        //multiple image demo
        ivImage1 = findViewById(R.id.iv_image1);
        ivImage2 = findViewById(R.id.iv_image2);
        ivImage3 = findViewById(R.id.iv_image3);
        ivImage4 = findViewById(R.id.iv_image4);
        ivImage5 = findViewById(R.id.iv_image5);
        ivImage6 = findViewById(R.id.iv_image6);
        selectBtn = findViewById(R.id.selectBtn);
        uploadBtn = findViewById(R.id.uploadBtn);
        editTextName = findViewById(R.id.editTextName);

        //This is the file path
        Context context = getBaseContext();
        System.out.println("\n\n************************" + context.getFilesDir());

        //asynchronous method
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BSImagePicker pickerDialog = new BSImagePicker.Builder("com.asksira.imagepickersheetdemo.fileprovider")
                        .setMaximumDisplayingImages(Integer.MAX_VALUE)
                        .isMultiSelect()
                        .setMinimumMultiSelectCount(1)
                        .setMaximumMultiSelectCount(3)
                        .build();
                pickerDialog.show(getSupportFragmentManager(), "picker");
            }
        });

        //asynchronous method
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int j = 0; j < uriPaths.size(); j++){
                    uploadImage(j);
                }
            }
        });

    }
    //Multiple images
    @Override
    public void onMultiImageSelected(List<Uri> uriList) {
        uriPaths.clear();
        uriPathsFull = false;
        for (int i=0; i < uriList.size(); i++) {
            if (i >= 6) return;
            ImageView iv;
            switch (i) {
                case 0:
                    iv = ivImage1;
                    break;
                case 1:
                    iv = ivImage2;
                    break;
                case 2:
                    iv = ivImage3;
                    break;
                case 3:
                    iv = ivImage4;
                    break;
                case 4:
                    iv = ivImage5;
                    break;
                case 5:
                default:
                    iv = ivImage6;
            }
            String path = uriList.get(i).toString();
            path = path.substring(path.lastIndexOf("//")+1);
            System.out.println("*\n******\n" + path + "\n******\n");

            uriPaths.add(Uri.parse(path)); //Todo: change this to a string list;
            Glide.with(this).load(uriList.get(i)).into(iv);
        }
        uriPathsFull = true;
        System.out.println("\n\nThe uriPaths are: " + uriPaths + "\n\n*****");
    }

    private void uploadImage(int i){    //TODO: change this to a volley upload service
        String name = editTextName.getText().toString().trim();//TODO: make this non-edtable, and check whether the project name exists already
        String path = null;
        try{                                   
            path = uriPaths.get(i).toString();
        }catch (NullPointerException e){
            Toast.makeText(this,"There are no files here!!", Toast.LENGTH_LONG).show();
            System.out.println("There are no files here!!!!!");
        }
        try{
            String uploadId = UUID.randomUUID().toString();
            new MultipartUploadRequest(this,uploadId,UPLOAD_URL)
                .addFileToUpload(path,"image")
                .addParameter("name", name)
                .setNotificationConfig(new UploadNotificationConfig())
                .setMaxRetries(2)
                .startUpload();
            Toast.makeText(this,"Uploading !!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), SandConeActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);

        }catch (Exception e){
            Toast.makeText(this,"Upload failed :(", Toast.LENGTH_LONG).show();
        }
    }

}
