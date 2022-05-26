package com.example.VaccinationCentre2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    EditText editText, editText2, editText3;
    Button btn;
    TextView textView;
    dbHelper DB;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editText = (EditText) findViewById(R.id.email);
        editText2 = (EditText) findViewById(R.id.password);
        editText3 = (EditText) findViewById(R.id.confirmpassword);
        btn = (Button) findViewById(R.id.btnregister);
        textView = findViewById(R.id.signintext);
        DB = new dbHelper(this);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void Register() {
        String useremail = editText.getText().toString();
        String pass = editText2.getText().toString();
        String confirmpass = editText3.getText().toString();

        if (TextUtils.isEmpty(useremail)) {
            editText.setError("Enter your email");
            return;
        } else if (TextUtils.isEmpty(pass)) {
            editText2.setError("Enter your password");
            return;
        } else if (TextUtils.isEmpty(confirmpass)) {
            editText3.setError("Confirm your password");
            return;
        } else if (!pass.equals(confirmpass)) {
            editText3.setError("Different password");
            return;
        } else if (pass.length() < 4) {
            editText2.setError("Password should be more than 4 characters");
            return;
        } else if (!isValidEmail(useremail)) {
            editText.setError("Invalid email");
            return;
        }
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(useremail, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(register.this, "Successfully registered", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(register.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(register.this, "Sign up failed", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();

            }
        });
    }

    private Boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}