package ru.startandroid.kinopoiskappclocks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    TextView goToSignIn;
    EditText email, firstName, lastName, password;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();

        goToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, SignIn.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(firstName.getText().toString())
                        || TextUtils.isEmpty(lastName.getText().toString()) || TextUtils.isEmpty(password.getText().toString()))
                {
                    Toast.makeText(SignUp.this, "Inputs required", Toast.LENGTH_LONG).show();
                }
                else{
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setEmail(email.getText().toString());
                    registerRequest.setFirstName(firstName.getText().toString());
                    registerRequest.setLastName(lastName.getText().toString());
                    registerRequest.setPassword(password.getText().toString());
                    SignUpUser(registerRequest);
                }
            }
        });
    }

    public void SignUpUser(RegisterRequest registerRequest){
        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(SignUp.this, "Successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SignUp.this, SignIn.class));
                    finish();
                }
                else{
                    Toast.makeText(SignUp.this, "An error occurred please try again later...", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(SignUp.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void init(){
        goToSignIn = (TextView) findViewById(R.id.tvGoToSignIn);
        email = (EditText) findViewById(R.id.etEmail);
        firstName = (EditText) findViewById(R.id.etFirstName);
        lastName = (EditText) findViewById(R.id.etLastName);
        password = (EditText) findViewById(R.id.etPassword);
        signUp = (Button) findViewById(R.id.btnSignUp);
    }
}
