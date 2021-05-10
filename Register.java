package com.example.proiect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText usernameR;
    private EditText passwordR;
    private Button registerR;
    private Button backR;
    private TextView status;
    private String URL = "http://10.0.2.2/PDM/register.php";

    private String fn, ln, em, us, pass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        usernameR = findViewById(R.id.usernameR);
        passwordR = findViewById(R.id.passwordR);
        registerR = findViewById(R.id.register);
        backR = findViewById(R.id.back);
        status = findViewById(R.id.status);

        fn = ln = em = us = pass = "";

    }

    public void save(View view) {
        fn = firstName.getText().toString().trim();
        ln = lastName.getText().toString().trim();
        em = email.getText().toString().trim();
        us = usernameR.getText().toString().trim();
        pass = passwordR.getText().toString().trim();

        if(!fn.isEmpty() && !ln.isEmpty() && !em.isEmpty() && !us.isEmpty() && !pass.isEmpty()){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
                if (response.equals("success")) {
                    status.setText("Successfully registered");
                    firstName.getText().clear();
                    lastName.getText().clear();
                    email.getText().clear();
                    usernameR.getText().clear();
                    passwordR.getText().clear();
                    //registerR.setClickable(false);

                } else if (response.equals("failure")) {
                    status.setText("Something went wrong! Please try again!");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Register.this.getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                    System.out.println(error.toString().trim());
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("FirstName", fn);
                    data.put("LastName", ln);
                    data.put("Email", em);
                    data.put("Username", us);
                    data.put("Password", pass);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
    private void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}








/*
        registerR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserModel userModel = null;
                try{
                    userModel = new UserModel(firstName.getText().toString(),
                            lastName.getText().toString(), email.getText().toString(), usernameR.getText().toString(),
                            encrypt(passwordR.getText().toString()));
                }
                catch (Exception e){
                    Toast.makeText(Register.this, "Error", Toast.LENGTH_SHORT).show();
                }
    // Verificam daca sunt goale campurile

                if(firstName.equals("")||lastName.equals("")||email.equals("")||
                        usernameR.equals("")||passwordR.equals("")){
                    Toast.makeText(Register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }else {
    // Daca nu sunt goale, atunci adauga datele introduse in baza de date

                    try {
                        db.connect();
                        db.insertIntoUser(userModel);
                        Toast.makeText(Register.this, "Register successfully", Toast.LENGTH_SHORT).show();
                        goBack();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        Toast.makeText(Register.this, "Register failed", Toast.LENGTH_SHORT).show();
                    }

                }


                // String fName = firstName.getText().toString();
                // String lName = lastName.getText().toString();
                // String Email = email.getText().toString();
                // String phone = phoneNumber.getText().toString();
                // String usern = usernameR.getText().toString();
                // String passw = passwordR.getText().toString();

            }

        });

        backR.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    public String encrypt(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }



 */