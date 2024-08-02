package com.example.leaf_disease_detection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.leaf_disease_detection.ml.ModelDiseaseClass;
import com.example.leaf_disease_detection.ml.ModelLeafClassification;
//import com.example.leaf_disease_detection.ml.ModelObjectClassification;
import com.example.leaf_disease_detection.ml.ModelUnquant;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class disease_detection extends AppCompatActivity {

    private static final int pic_id = 123;
    int imagesize= 224;
    int SELECT_PICTURE = 200;
    ImageView imageView;
    Button capture, upload, results;

    TextView predc;
    String predicted_class;
    float maxConfidence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_detection);
        getSupportActionBar().setTitle("Disease Detection");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.upload_sc_color)));
        getWindow().setStatusBarColor(ContextCompat.getColor(disease_detection.this,R.color.upload_sc_color));

        imageView=findViewById(R.id.image_iv);
        capture=findViewById(R.id.capture_btn);
        upload=findViewById(R.id.upload_btn);
        results=findViewById(R.id.Results_btn);
        predc=findViewById(R.id.predclass);

        results.setVisibility(View.GONE);

//        if(ContextCompat.checkSelfPermission(disease_detection.this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(disease_detection.this,new String[]{Manifest.permission.CAMERA},101);
//        }

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Start the activity with camera_intent, and request pic id
                startActivityForResult(camera_intent, pic_id);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });

        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(disease_detection.this,Prediction_Results.class);
                intent.putExtra("Predicted_class", predicted_class );
                intent.putExtra("Confidence", maxConfidence);
                startActivity(intent);
            }
        });

    }
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if(requestCode == pic_id && resultCode == RESULT_OK){
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            int dimen=Math.min(photo.getWidth(),photo.getHeight());
//            photo=ThumbnailUtils.extractThumbnail(photo,dimen,dimen);
//            imageView.setImageBitmap(photo);
//            capture.setVisibility(View.GONE);
//            upload.setVisibility(View.GONE);
//
//            photo=Bitmap.createScaledBitmap(photo,imagesize,imagesize,false);
//            classifyImage(photo);
//
//        }


        // Match the request 'pic id with requestCode
        if (requestCode == pic_id) {
            // BitMap is data structure of image file which store the image in memory
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            int dimen=Math.min(photo.getWidth(),photo.getHeight());
            photo=ThumbnailUtils.extractThumbnail(photo,dimen,dimen);
            imageView.setImageBitmap(photo);
//            capture.setVisibility(View.GONE);
//            upload.setVisibility(View.GONE);

//            predc.setVisibility(View.GONE);
//            results.setVisibility(View.VISIBLE);

            photo=Bitmap.createScaledBitmap(photo,imagesize,imagesize,false);


            String leaf=Leaf_classify(photo);
            if(!(leaf.equals("Others"))){
                classifyImage(photo);
                predc.setVisibility(View.GONE);
                results.setVisibility(View.VISIBLE);
            }
            else{
                predc.setText(R.string.wrong_image);
            }

//            classifyImage(photo);
        }
        else if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {

                    Bitmap selectedImageBitmap;
                    try {
                        selectedImageBitmap
                                = MediaStore.Images.Media.getBitmap(
                                this.getContentResolver(),
                                selectedImageUri);
                        int dimen=Math.min( selectedImageBitmap.getWidth(),selectedImageBitmap.getHeight() );
                        selectedImageBitmap=ThumbnailUtils.extractThumbnail(selectedImageBitmap,dimen,dimen);
                        imageView.setImageBitmap(selectedImageBitmap);
//                        capture.setVisibility(View.GONE);
//                        upload.setVisibility(View.GONE);

//                        predc.setVisibility(View.GONE);
//                        results.setVisibility(View.VISIBLE);

                        selectedImageBitmap=Bitmap.createScaledBitmap(selectedImageBitmap,imagesize,imagesize,false);

                        String leaf=Leaf_classify(selectedImageBitmap);
                        if(!(leaf.equals("Others"))){
                            classifyImage(selectedImageBitmap);
                            predc.setVisibility(View.GONE);
                            results.setVisibility(View.VISIBLE);
                        }
                        else{
                            predc.setText(R.string.wrong_image);
                        }



                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void classifyImage(Bitmap image){

        try{
            ModelUnquant model = ModelUnquant.newInstance(getApplicationContext());

            //create input reference
            TensorBuffer inputFeature0= TensorBuffer.createFixedSize(new int[]{1,224,224,3}, DataType.FLOAT32);
            ByteBuffer bytebuffer=ByteBuffer.allocateDirect(4* imagesize * imagesize* 3);
            bytebuffer.order(ByteOrder.nativeOrder());

            int[] intValue= new int[imagesize* imagesize];
            image.getPixels(intValue,0,image.getWidth(),0,0,image.getWidth(),image.getHeight());

            //iterate over pixels
            int pixel=0;
            for( int i=0;i<imagesize;i++){
                for(int j=0;j<imagesize;j++){
                    int val=intValue[pixel++];
                    bytebuffer.putFloat(((val >>16) & 0xFF) * (1.f / 255.f));
                    bytebuffer.putFloat(((val >>8) & 0xFF) * (1.f / 255.f));
                    bytebuffer.putFloat((val & 0xFF) * (1.f / 255.f));

                }
            }

            inputFeature0.loadBuffer(bytebuffer);

            //run model intreface and get results
            ModelUnquant.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeatures0= outputs.getOutputFeature0AsTensorBuffer();

            float[] confidence=outputFeatures0.getFloatArray();

            //largest confidence
            int maxPos=0;
            maxConfidence=0;
            for(int i=0;i<confidence.length; i++){
                if(confidence[i] > maxConfidence){
                    maxConfidence=confidence[i];
                    maxPos=i;
                }
            }
            String[] classes = {"Cordana_BananaLeaf_disease","Pestalotiopsis_BananaLeaf_Disease" ,"Sigatoka_BananaLeaf_Disease","Healthy _BananaLeaf","Anthracnose_PapayaLeaf_Disease","BacterialSpot_PapayaLeaf_Disease","Curl_PapayaLeaf_Disease","RingSpot_PapayaLeaf_Disease","Healthy_PapayaLeaf"};

//            String[] classes = {"Banana-Cut Fruit","Banana- Stem Panama Wilt","Banana-Leaf Cordana","Banana- Leaf Sigatoka","Papaya - Leaf Mosaic Virus",
//                    "Papaya- Leaf BacterialSpot",
//                    "Papaya- Leaf Curl",
//                    "Papaya- Stem Foot Rot",
//                    "Banana- Leaf Pestalotiopsis"};


            predicted_class= classes[maxPos];

//            String fin_res= predicted_class+  maxConfidence;
//            predc.setText(fin_res);


        }catch(IOException e){

        }
    }

    private String Leaf_classify(Bitmap image){

        try{
            ModelLeafClassification model = ModelLeafClassification.newInstance(getApplicationContext());

            //create input reference
            TensorBuffer inputFeature0= TensorBuffer.createFixedSize(new int[]{1,224,224,3}, DataType.FLOAT32);
            ByteBuffer bytebuffer=ByteBuffer.allocateDirect(4* imagesize * imagesize* 3);
            bytebuffer.order(ByteOrder.nativeOrder());

            int[] intValue= new int[imagesize* imagesize];
            image.getPixels(intValue,0,image.getWidth(),0,0,image.getWidth(),image.getHeight());

            //iterate over pixels
            int pixel=0;
            for( int i=0;i<imagesize;i++){
                for(int j=0;j<imagesize;j++){
                    int val=intValue[pixel++];
                    bytebuffer.putFloat(((val >>16) & 0xFF) * (1.f / 255.f));
                    bytebuffer.putFloat(((val >>8) & 0xFF) * (1.f / 255.f));
                    bytebuffer.putFloat((val & 0xFF) * (1.f / 255.f));

                }
            }

            inputFeature0.loadBuffer(bytebuffer);

            //run model intreface and get results
            ModelLeafClassification.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidence=outputFeature0.getFloatArray();

            //largest confidence
            String leaf_pred;
            int maxPos=0;
            float maxConf=0;
            for(int i=0;i<confidence.length; i++){
                if(confidence[i] > maxConf){
                    maxConf=confidence[i];
                    maxPos=i;
                }
            }
            String[] classes = {"Banana_leaf","Papaya_leaf","Others"};

//            String[] classes = {"Banana","Papaya","Others"};
            leaf_pred= classes[maxPos];
            return leaf_pred;


        }catch(IOException e){

        }
        return "";
    }

}