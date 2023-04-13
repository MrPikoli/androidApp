package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class OrderActivity extends AppCompatActivity {
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    String subject = "Order from store";
    String mess;
    String addresses = "adress@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();

        String userName = intent.getStringExtra("userNameOrder");
        String items = intent.getStringExtra("items");
        double orderPrice = intent.getDoubleExtra("priceOrder",0);
        int quantity = intent.getIntExtra("quantity",0);
        double price = intent.getDoubleExtra("price",0);
        mess = "Customer name: " + userName +"\n"+
                "Item: " + items +"\n" +
                "Quantity: " + quantity +"\n" +
                "Price: " + price + "\n" +
                "Order price: " + decimalFormat.format(orderPrice) +"\n";

        TextView textName = findViewById(R.id.nameText);
        textName.setText(mess);
    }

    public void submitOrder(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: "));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, mess);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}