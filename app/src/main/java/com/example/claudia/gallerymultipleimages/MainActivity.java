package com.example.claudia.gallerymultipleimages;

//   Context context1 = getBaseContext(); //App context and Base context were the same?
//        System.out.println("THE BASE CONTEXT IS " + context1.getExternalFilesDir(null));

//Button chooseBtn = (Button)findViewById(R.id.chooseBtn);
//        Button uploadBtn = (Button)findViewById(R.id.uploadBtn);

//import android.content.Context;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.asksira.bsimagepicker.BSImagePicker;
import com.bumptech.glide.Glide;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BSImagePicker.OnMultiImageSelectedListener{

    private ImageView ivImage1, ivImage2, ivImage3, ivImage4, ivImage5, ivImage6;
    private Button selectBtn, uploadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivImage1 = findViewById(R.id.iv_image1);
        ivImage2 = findViewById(R.id.iv_image2);
        ivImage3 = findViewById(R.id.iv_image3);
        ivImage4 = findViewById(R.id.iv_image4);
        ivImage5 = findViewById(R.id.iv_image5);
        ivImage6 = findViewById(R.id.iv_image6);

        selectBtn = (Button)findViewById(R.id.selectBtn);
        uploadBtn = (Button)findViewById(R.id.uploadBtn);

        System.out.println("\n\n************************");
        Context context = getBaseContext();
        System.out.println(context.getFilesDir());

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BSImagePicker pickerDialog = new BSImagePicker.Builder("com.asksira.imagepickersheetdemo.fileprovider")
                        .setMaximumDisplayingImages(Integer.MAX_VALUE)
                        .isMultiSelect()
                        .setMinimumMultiSelectCount(2)
                        .setMaximumMultiSelectCount(6)
                        .build();
                pickerDialog.show(getSupportFragmentManager(), "picker");

            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do Something
            }
        });

    }

    @Override
    public void onMultiImageSelected(List<Uri> uriList) {
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
            System.out.println("\n\n************************");
            System.out.println(uriList.get(i).getPath());
            System.out.println("\n\n************************");

            Glide.with(this).load(uriList.get(i)).into(iv);
        }
        uploadBtn.setVisibility(View.VISIBLE);
    }
}
