package com.example.user.showlocation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.showlocation.Config;
import com.example.user.showlocation.MapsActivity;
import com.example.user.showlocation.R;
import com.example.user.showlocation.interfaces.ApiInterface;
import com.example.user.showlocation.model.LoginResponse;
import com.example.user.showlocation.model.User;
import com.example.user.showlocation.retrofit.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ApiInterface apiInterface;
    private EditText userIdEditText;
    private EditText passwordEditText;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        userIdEditText = (EditText) findViewById(R.id.login_id);
        passwordEditText = (EditText) findViewById(R.id.login_password);
        btnLogin= (Button) findViewById(R.id.login_button);
        if (Config.DEBUG) {
            userIdEditText.setText("jordi22@example.com");
            passwordEditText.setText("secret");
            btnLogin.performClick();
        }
    }

    public void buttonClickEvent(View view){
        String userId;
        String password;
        User user = new User();

        userId = userIdEditText.getText().toString();
        password = passwordEditText.getText().toString();

        user.setUserId(userId);
        user.setPassword(password);
        checkUserValidity(user);

    }


    private void checkUserValidity(User userCredential){
        Call<LoginResponse> call=apiInterface.getUserValidity(userCredential);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Intent intent=new Intent(MainActivity.this,GetLocation.class);
                intent.putExtra("token", response.body().getData().getToken());
                startActivity(intent);



               // Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void map(View view){
        Intent i=new Intent(MainActivity.this, MapsActivity.class);
        startActivity(i);
    }
}
