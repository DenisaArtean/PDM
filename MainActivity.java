package com.example.proiect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;
    private Button register;
    private TextView statusLogin;
    private String URL = "http://10.0.2.2/PDM/login.php";
    private String us, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        us = pass = "";

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnRegister);
        statusLogin = findViewById(R.id.statusLogin);


    }


    public void login(View view) {
        us = username.getText().toString().trim();
        pass = password.getText().toString().trim();


        if (us.isEmpty() || pass.isEmpty()) {
            Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT);
            statusLogin.setText("Fields are empty");

        } else {

            if (!us.equals("") && !pass.equals("")) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
                    if (response.equals("success")) {
                        Intent intent = new Intent(MainActivity.this, User.class);
                        intent.putExtra("username", us);
                        startActivity(intent);
                    } else if (response.equals("failure")) {
                        Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                    }
                }, error -> Toast.makeText(MainActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show()) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();
                        data.put("Username", us);
                        data.put("Password", pass);
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

            } else {

                if (us.equals("admin") && pass.equals("admin")) {
                    Intent intent = new Intent(MainActivity.this, Admin.class);
                    startActivity(intent);
                }
            }
        }
    }


        public void register (View view){
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        }

    public String getUsername(){
        return us;
    }
    }






    /*
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = username.getText().toString();
                String inputPassword = password.getText().toString();

    // Verificam daca sunt goale campurile

                if(inputName.equals("")||inputPassword.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }else {
    // Daca nu sunt goale atunci Login admin

                    if (inputName.equals("admin") && inputPassword.equals("admin")) {
                        Intent intent = new Intent(MainActivity.this, Admin.class);
                        startActivity(intent);
                    } else {
    // Daca nu sunt goale si nu Login admin atunci Login user

                        Boolean checkUserPass = false;
                        if (checkUserPass == true) {
                            Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT);
                            Intent intent = new Intent(MainActivity.this, User.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_SHORT);
                        }
                    }
                }
            }
        });
    // Open register

        register.setOnClickListener(v -> {
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        });

*/