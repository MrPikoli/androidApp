package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int quantity = 1;
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    double price;
    TextView textViewPrice;
    TextView textViewQ;
    Spinner spinner;
    String spinnerItem;
    EditText editTextName;
    EditText editEmail;
    ArrayList<String> products = new ArrayList(Arrays.asList("Coca-Cola","Sprite","Pepsi","Fanta","Borjomi",
            "BonAqua","7up","Schweppes","Dr.Pepper","Angry Birds"));
    HashMap<String,Double> hashMap = new HashMap<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPrice= findViewById(R.id.textPrice);
        textViewQ = findViewById(R.id.textQuantity);
        editTextName = findViewById(R.id.Name);
        spinner = findViewById(R.id.spin);


        hashMap.put("Coca-Cola",2.39);
        hashMap.put("Sprite",2.29);
        hashMap.put("Fanta",2.49);
        hashMap.put("Pepsi",2.59);
        hashMap.put("BonAqua",1.59);
        hashMap.put("Borjomi",3.29);
        hashMap.put("Angry Birds",4.19);
        hashMap.put("7up",2.25);
        hashMap.put("Schweppes",2.45);
        hashMap.put("Dr.Pepper",5.29);


        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,products);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void increase(View view){
        quantity+=1;
        textViewQ.setText("" + quantity);
        textViewPrice.setText("" + decimalFormat.format(quantity * price));
    }

    public void decrease(View view){
        quantity-=1;
        if(quantity<1){
            quantity = 1;
        }
        textViewQ.setText("" + quantity);
        textViewPrice.setText("" + decimalFormat.format(quantity * price));
    }

    public void addToCart(View view){
        Order order = new Order();
        order.userName = editTextName.getText().toString();
        order.items = spinnerItem;
        order.quantity = quantity;
        order.orderPrice = quantity * price;
        order.price = price;

        Intent orderIntent = new Intent(MainActivity.this,OrderActivity.class);
        orderIntent.putExtra("userNameOrder",order.userName);
        orderIntent.putExtra("priceOrder",order.orderPrice);
        orderIntent.putExtra("quantity",order.quantity);
        orderIntent.putExtra("items",order.items);
        orderIntent.putExtra("price",order.price);
        startActivity(orderIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnerItem = adapterView.getSelectedItem().toString();
        price = (double) hashMap.get(spinnerItem);
        textViewPrice.setText("" + decimalFormat.format(quantity * price));

        ImageView imageView = findViewById(R.id.image);
        switch (spinnerItem){
            case "Coca-Cola":
                imageView.setImageResource(R.drawable.cocacola);
                break;
            case "Fanta":
                imageView.setImageResource(R.drawable.fanta);
                break;
            case "Pepsi":
                imageView.setImageResource(R.drawable.pepsi);
                break;
            case "Schweppes":
                imageView.setImageResource(R.drawable.schweppes);
                break;
            case "7up":
                imageView.setImageResource(R.drawable.sevenup);
                break;
            case "Sprite":
                imageView.setImageResource(R.drawable.sprite);
                break;
            case "Angry Birds":
                imageView.setImageResource(R.drawable.angrybirds);
                break;
            case "BonAqua":
                imageView.setImageResource(R.drawable.bonaqua);
                break;
            case "Borjomi":
                imageView.setImageResource(R.drawable.borjomi);
                break;
            case "Dr.Pepper":
                imageView.setImageResource(R.drawable.drpepper);
                break;



        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}