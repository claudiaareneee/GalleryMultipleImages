package com.example.claudia.gallerymultipleimages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.util.UUID;

public class SandConeActivity extends AppCompatActivity {
    private static final String SAND_CONE_URL = "http://134.164.1.251/sandcone.php";
    private static final String CLEANING_URL = "http://134.164.1.251/clean.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sand_cone);

        Button sandconeBtn = (Button)findViewById(R.id.sandconeBtn);
        sandconeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("*\n\nI got to here\n\n");
                structureFromMotion();
            }
        });
    }

    private void structureFromMotion(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name");
            Toast.makeText(this,"Your name is "+ name, Toast.LENGTH_LONG);
            try{
                String uploadId = UUID.randomUUID().toString();
                new MultipartUploadRequest(this,uploadId,SAND_CONE_URL)
                        .addParameter("name", name)
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload();
            }catch (Exception e){ }
        }
    }
}
