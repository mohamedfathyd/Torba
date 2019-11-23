package com.khalej.Turba.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.khalej.Turba.R;
import com.khalej.Turba.model.Apiclient_home;
import com.khalej.Turba.model.apiinterface_home;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_second_category_tager extends AppCompatActivity {
    Intent intent;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<Integer> arrayList_id=new ArrayList<>();
    apiinterface_home apiinterface;
    AppCompatButton regesiter;
    private  static final int IMAGE = 100;
    ImageView imageView;
    Toolbar toolbar;
    int category_id,marketid;
    String tagername;
    Bitmap bitmap;
    ProgressDialog progressDialog;
    Spinner spinner;
    TextInputLayout textInputLayoutdetails,textInputLayoutname,textInputLayoutaddress,textInputLayoutphone;
    TextInputEditText textInputEditTextlink,textInputEditTextname,textInputEditTexttext,textInputEditTextnametager;
   int category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_second_category_tager);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        initializer();
        this.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );

        intent = getIntent();
        arrayList = intent.getStringArrayListExtra("category_list");

        marketid=intent.getIntExtra("marketid",0);
        tagername=intent.getStringExtra("name");
        initializer();

        //Toast.makeText(Add_second_category.this,arrayList.get(1),Toast.LENGTH_LONG).show();


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinner.getSelectedItem().equals("حراج منوع")){
                    category_id=44;
                }
                if(spinner.getSelectedItem().equals("حراج عقارات")){
                    category_id=43;
                }
                if(spinner.getSelectedItem().equals("حراج مواشي")){
                    category_id=42;
                }
                if(spinner.getSelectedItem().equals("حراج أثاث")){
                    category_id=41;
                }
                if(spinner.getSelectedItem().equals("حراج جوالات")){
                    category_id=39;
                }
                if(spinner.getSelectedItem().equals("حراج ألكترونيات")){
                    category_id=40;
                }
                if(spinner.getSelectedItem().equals("حراج سيارات")){
                    category_id=38;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        regesiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchInfo();
                regesiter.setClickable(false);
            }
        });
    }
    public void initializer(){
        spinner=(Spinner)findViewById(R.id.spin);
        imageView=(ImageView)findViewById(R.id.image);

        textInputLayoutname=(TextInputLayout)findViewById(R.id.textInputLayoutName);

        textInputEditTextname=(TextInputEditText)findViewById(R.id.textInputEditTextName);
        textInputEditTextnametager=findViewById(R.id.textInputEditTexttager);
        textInputEditTextlink=(TextInputEditText)findViewById(R.id.textInputEditTextLink);
        textInputEditTexttext=(TextInputEditText)findViewById(R.id.textInputEditTexttext);

        regesiter=(AppCompatButton)findViewById(R.id.appCompatButtonRegister);
    }
    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE);
    }
    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    private String convertToString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,70,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== IMAGE && resultCode==RESULT_OK && data!=null)
        {
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public void fetchInfo(){

        String image="";
        try{
            image = convertToString();}
        catch (Exception e){
            Toast.makeText(Add_second_category_tager.this,"من فضلك اختر صورة" , Toast.LENGTH_LONG).show();
            regesiter.setClickable(true);
            return;

        }
        progressDialog = ProgressDialog.show(Add_second_category_tager.this,"جارى تسجيل الاعلان الجديد","Please wait...",false,false);
        progressDialog.show();

        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontacts_add_category(category_id,textInputEditTextname.getText().toString()
                ,image,textInputEditTextlink.getText().toString(),textInputEditTexttext.getText().toString(),textInputEditTextnametager.getText().toString()


        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();

                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(Add_second_category_tager.this);
                dlgAlert.setMessage("تم تسجيل الاعلان الجديد بنجاح ");
                dlgAlert.setTitle("Torba App");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                regesiter.setClickable(true);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Add_second_category_tager.this,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
