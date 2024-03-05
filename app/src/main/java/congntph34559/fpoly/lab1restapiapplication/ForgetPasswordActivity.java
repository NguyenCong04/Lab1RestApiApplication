package congntph34559.fpoly.lab1restapiapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    ImageView ivBack;
    EditText edEmail;
    AppCompatButton btnForget;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        //Ánh xạ view
        ivBack = findViewById(R.id.ivBackFroget);
        edEmail = findViewById(R.id.edEmailForget);
        btnForget = findViewById(R.id.btnforget);


        firebaseAuth = FirebaseAuth.getInstance();


        //Bắt sự kiện cho nút back
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPasswordActivity.this, LoginActivity.class));
                finish();
            }
        });

        //Bắt sự kiện cho nút forget
        btnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (check()) {
                    firebaseAuth.sendPasswordResetEmail(edEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgetPasswordActivity.this, "Kiểm tra email để cập nhật mật khẩu mới", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgetPasswordActivity.this,LoginActivity.class));
                                finish();
                            } else {
                                Toast.makeText(ForgetPasswordActivity.this, "Forget email failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }

    private boolean check() {
        if (edEmail.getText().toString().equals("")) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}