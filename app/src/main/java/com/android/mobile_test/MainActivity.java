package com.android.mobile_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private String money;
    private String interest;
    private String term;

    private EditText edt_money;
    private EditText edt_interest;
    private EditText edt_term;

    private Button btn_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("NULL", edt_money.getText().toString().trim());

                if (edt_money.getText().toString().trim() != ""  && edt_interest.getText().toString().trim() != ""
                 && edt_term.getText().toString().trim() != ""){

                    money = edt_money.getText().toString().trim();
                    interest = edt_interest.getText().toString().trim();
                    term = edt_term.getText().toString().trim();

                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                    intent.putExtra("money", money);
                    intent.putExtra("interest", interest);
                    intent.putExtra("term", term);

                    startActivity(intent);

                }else {

                    Toast.makeText(getApplicationContext(), "Please input again !", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    public void init(){

        Bundle extras = getIntent().getExtras();

        edt_interest = findViewById(R.id.interet_val);
        edt_money = findViewById(R.id.money_val);
        edt_term = findViewById(R.id.term_val);

        btn_main = findViewById(R.id.btn);

        if(extras != null){
            money = extras.getString("money");
            interest = extras.getString("interest");
            term = extras.getString("term");

            edt_money.setText(money);
            edt_term.setText(term);
            edt_interest.setText(interest);
        }



    }


}