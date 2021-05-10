package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User extends AppCompatActivity {

    private EditText address;
    private EditText City;
    private EditText zipCode;
    private EditText County;
    private EditText Date;
    private EditText phoneNumber;
    private EditText foodLeft;
    private TextView statusInfo;
    private Button send;
    private String user;
    private String URL = "http://10.0.2.2/PDM/info.php";

    private String addr, city, code, county, phone, food, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2);

        address = findViewById(R.id.Address);
        City = findViewById(R.id.City);
        zipCode = findViewById(R.id.ZipCode);
        County = findViewById(R.id.County);
        Date = findViewById(R.id.Date);
        phoneNumber = findViewById(R.id.phoneNumber);
        foodLeft = findViewById(R.id.food);
        statusInfo = findViewById(R.id.statusInfo);
        send = findViewById(R.id.Send);

        Bundle extras = getIntent().getExtras();
        user = extras.getString("username");

        addr = city = code = county = date = phone = food = "";

    }



    public void send(View view) {

        addr = address.getText().toString().trim();
        city = City.getText().toString().trim();
        code = zipCode.getText().toString().trim();
        county = County.getText().toString().trim();
        date = Date.getText().toString().trim();
        phone = phoneNumber.getText().toString().trim();
        food = foodLeft.getText().toString().trim();

        if(!addr.isEmpty() && !city.isEmpty() && !code.isEmpty() && !county.isEmpty() && !date.isEmpty() && !phone.isEmpty() && !food.isEmpty()){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
                if (response.equals("success")) {
                    statusInfo.setText("Your data was send successfully");
                    address.getText().clear();
                    City.getText().clear();
                    zipCode.getText().clear();
                    County.getText().clear();
                    Date.getText().clear();
                    phoneNumber.getText().clear();
                    foodLeft.getText().clear();
                    //registerR.setClickable(false);

                } else if (response.equals("failure")) {
                    statusInfo.setText("Something went wrong! Please try again!");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(User.this.getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                    System.out.println(error.toString().trim());
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("Username", user);
                    data.put("Address", addr);
                    data.put("City", city);
                    data.put("ZipCode", code);
                    data.put("County", county);
                    data.put("Date", date);
                    data.put("PhoneNumber", phone);
                    data.put("FoodLeft", food);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void logout() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}