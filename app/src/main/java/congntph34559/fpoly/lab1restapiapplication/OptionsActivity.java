package congntph34559.fpoly.lab1restapiapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OptionsActivity extends AppCompatActivity {


    AppCompatButton btnLoginEmail, btnLoginPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        btnLoginEmail = findViewById(R.id.btnLoginEmail);
        btnLoginPhone = findViewById(R.id.btnLoginPhone);
        btnLoginEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionsActivity.this, LoginActivity.class));

            }
        });

        btnLoginPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionsActivity.this, LoginPhoneActivity.class));

            }
        });


    }
}