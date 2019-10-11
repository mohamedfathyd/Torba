package com.khalej.Boutique.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.khalej.Boutique.R;
import com.khalej.Boutique.model.Apiclient_home;
import com.khalej.Boutique.model.apiinterface_home;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_tager extends AppCompatActivity {
    TextInputLayout textInputLayoutname,textInputLayoutaddress,textInputLayoutphone,
            textInputLayoutpassword,textInputLayoutconfirmpassword;
    TextInputEditText textInputEditTextname,textInputEditTextaddress,textInputEditTextphone,textInputEditTextdate,
            textInputEditTextpassword,textInputEditTextconfirmpassword;
    AppCompatButton regesiter;
    Spinner spinner;
    AppCompatTextView openlogin;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    Call<ResponseBody> call = null;
    String code,mVerificationId;
    ProgressDialog progressDialog;
    ProgressDialog progressDialog1;
    Spinner spin;
    login_ login_;
    String codee =null;
    private FirebaseAuth mAuth;
    private apiinterface_home apiinterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tager);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Nasser.otf", true);
          inisialize();
        regesiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regesiter.setClickable(false);
                if (textInputEditTextaddress.getText().toString().equals("") || textInputEditTextaddress.getText().toString() == null) {

                    textInputLayoutaddress.setError("أدخل العنوان");

                } else if (textInputEditTextname.getText().toString().equals("") || textInputEditTextname.getText().toString() == null) {

                    textInputLayoutname.setError("أدخل اسم المستخدم");

                } else if (textInputEditTextphone.getText().toString().equals("") || textInputEditTextphone.getText().toString() == null) {

                    textInputLayoutphone.setError("أدخل رقم الموبيل");

                } else {


                    fetchInfo();
                }
            }
        });


    }
    public void fetchInfo() {
        progressDialog = ProgressDialog.show(Register_tager.this, "جاري ارسال طلبك", "Please wait...", false, false);
        progressDialog.show();
        String phone=textInputEditTextphone.getText().toString();

        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontacts_newaccount_tager(textInputEditTextname.getText().toString(),
                textInputEditTextaddress.getText().toString()
                ,phone);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(Register_tager.this);
                dlgAlert.setMessage("تم ارسال طلبك سنتواصل معك قريبا شكرا لثقتك فينا ");
                dlgAlert.setTitle("Boutique App");
                dlgAlert.setPositiveButton("حسنا", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Register_tager.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void inisialize() {
        textInputLayoutname = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutaddress = (TextInputLayout) findViewById(R.id.textInputLayoutaddress);
        textInputLayoutphone = (TextInputLayout) findViewById(R.id.textInputLayoutphone);

        textInputEditTextname = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextphone = (TextInputEditText) findViewById(R.id.textInputEditTextphone);
        textInputEditTextaddress = (TextInputEditText) findViewById(R.id.textInputEditTextaddress);
        regesiter = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        openlogin = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);
        mAuth = FirebaseAuth.getInstance();
    }
}
