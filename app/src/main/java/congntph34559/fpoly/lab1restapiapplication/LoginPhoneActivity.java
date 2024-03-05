package congntph34559.fpoly.lab1restapiapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginPhoneActivity extends AppCompatActivity {


    AppCompatButton btnLoginPhone, btnGetOpt;
    EditText edPhone,edOTP;
    private FirebaseAuth firebaseAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private String mVeriId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone);
        btnLoginPhone = findViewById(R.id.btnLoginPhoneRea);
        btnGetOpt = findViewById(R.id.btnGetOtp);
        edPhone = findViewById(R.id.edPhoneNumber);
        edOTP = findViewById(R.id.edOTP);

        firebaseAuth = FirebaseAuth.getInstance();

        btnGetOpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = edPhone.getText().toString();

                if (phoneNumber.equals("")) {
                    Toast.makeText(LoginPhoneActivity.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    getOtp(phoneNumber);
                }
            }
        });

        btnLoginPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = edOTP.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVeriId,code);
                sigInWithCredential(credential);
            }
        });





        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
               sigInWithCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(LoginPhoneActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(verificationID, forceResendingToken);
                mVeriId = verificationID;

            }
        };

    }

    private void sigInWithCredential(PhoneAuthCredential phoneAuthCredential) {

        firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(LoginPhoneActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginPhoneActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginPhoneActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(LoginPhoneActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void getOtp(String phoneNumber) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber("+84" + phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}