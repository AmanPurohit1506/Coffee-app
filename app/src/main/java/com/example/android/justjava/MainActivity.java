/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */
package com.example.android.justjava;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import  java.text.NumberFormat;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.justjava.R.id.choc;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public  void increment(View view)
    { if (quantity==100)
    {
        Toast.makeText(this, "MAX 100 CUPS", Toast.LENGTH_SHORT).show();
        return;
    }
        quantity=quantity+1;
        display(quantity);
    }
    private  int calculateprice(boolean cream,boolean choclate) {
        int price = 5;
        if (cream)
            price += 1;
        if (choclate)
            price += 2;
        price = price * quantity;
        return price;
    }

    public void decrement(View view)
    { if (quantity==1)
    {
        Toast.makeText(this, "MIN 1 CUP IS REQUIRED", Toast.LENGTH_SHORT).show();
        return;
    }
       quantity=quantity-1;
        display(quantity);
    }
    public void submitOrder(View view) {
        CheckBox whip=(CheckBox) findViewById(R.id.whip);
        CheckBox choc=(CheckBox)findViewById(R.id.choc);
        EditText name=(EditText)findViewById(R.id.name);
        String nam=name.getText().toString();
        boolean cream=whip.isChecked();
        Boolean choclate=choc.isChecked();
        int price=calculateprice(cream,choclate);
        String msg=createordersummary(price,cream,choclate,nam);
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"JUST JAVA ORDER FOR "+nam);
        intent.putExtra(Intent.EXTRA_TEXT,msg);

        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivity(intent);
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private String createordersummary(int price,boolean cream,boolean choc,String name)
    {
        String mes="Name:"+name+"\nQuantity:"+quantity+"\nTOTAL:"+price+"\n"+"HAS WHIPPED CREAM?"+cream +"\nThank YOU";
        mes+="\nCHOCLATE TOPPING:"+choc;
        return mes;
    }

}