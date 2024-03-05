package congntph34559.fpoly.lab1restapiapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextView tvSigUp,tvForgePass;
    EditText edEmail, edPass;
    AppCompatButton btnLogin;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvSigUp = findViewById(R.id.tvSigUp);
        edEmail = findViewById(R.id.edEmail);
        edPass = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgePass = findViewById(R.id.tvForgetPass);
        firebaseAuth = FirebaseAuth.getInstance();

        String mail = getIntent().getStringExtra("email");
        String pass = getIntent().getStringExtra("pass");

        edEmail.setText(mail);
        edPass.setText(pass);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mail == null || pass == null) {
                    firebaseAuth.signInWithEmailAndPassword(edEmail.getText().toString(), edPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    firebaseAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });

        //Chuyển màn sang màn đăng ký
        tvSigUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SigUpActivity.class));
                finish();
            }
        });


        //Chuyển đến màn quên mật khẩu
        tvForgePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new
                        Intent(LoginActivity.this,ForgetPasswordActivity.class));
                finish();
            }
        });


    }
}