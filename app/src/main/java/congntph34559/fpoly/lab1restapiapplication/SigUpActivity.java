package congntph34559.fpoly.lab1restapiapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigUpActivity extends AppCompatActivity {

    ImageView ivBack;
    AppCompatButton btnSigUp;
    EditText edEmail, edPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig_up);
        //Ánh xạ view
        ivBack = findViewById(R.id.ivBack);
        btnSigUp = findViewById(R.id.btnSigUp);
        edEmail = findViewById(R.id.edEmailSigUp);
        edPassword = findViewById(R.id.edPasswordSigUp);
        firebaseAuth = FirebaseAuth.getInstance();

        //back
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SigUpActivity.this, LoginActivity.class));
                finish();
            }
        });

        // Sự kiện nút nhấn đăng ký
        btnSigUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SigUp();
            }
        });


    }

    //Hàm tạo tài khoản
    private void SigUp() {

        String email = edEmail.getText().toString();
        String pass = edPassword.getText().toString();
//        Toast.makeText(this, "Email: "+email+"----"+"Pass: "+pass, Toast.LENGTH_SHORT).show();
        if (checkSigUp()) {

            firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SigUpActivity.this, "Sig up successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SigUpActivity.this,LoginActivity.class);
                        intent.putExtra("email",email);
                        intent.putExtra("pass",pass);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SigUpActivity.this, "Sig up failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }


    }

    //Hàm check SigUp
    private boolean checkSigUp() {
        if (edPassword.getText().toString().equals("") || edEmail.getText().toString().equals("")) {
            Toast.makeText(this, "Please enter information", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }
}