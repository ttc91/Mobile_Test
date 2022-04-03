package com.android.mobile_test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.mobile_test.databinding.ActivityResultBinding;

public class MainActivity2 extends AppCompatActivity {

    String money = "";
    String interest = "";
    String term = "";

    private Button btn_back;
    private Button btn_result;

    private TextView interest_result;
    private TextView total_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        init();

    }

    public void init(){
        btn_back = findViewById(R.id.btn_back);
        btn_result = findViewById(R.id.btn_result);

        interest_result = findViewById(R.id.interest_result);
        total_result = findViewById(R.id.total_result);

        compute();
    }

    public void compute(){

        //Số tiền lãi = Số tiền gửi x lãi suất (%/năm) x số ngày thực gửi/360

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            money = extras.getString("money");
            interest = extras.getString("interest");
            term = extras.getString("term");

            if(money.equals("") || interest.equals("") || term.equals("")){

                interest_result.setText("Nhập lại !");
                total_result.setText(String.valueOf(0.000));

                return;
            }

            Log.e("TAG", "'"+ money+"'");

            double d_money = Double.parseDouble(money);
            double d_interest = Double.parseDouble(interest);
            double d_term = Double.parseDouble(term);

            double int_money = (d_money * (d_interest / 100) * (d_term * 30)) / 360;

            interest_result.setText(String.valueOf(int_money));
            total_result.setText(String.valueOf(int_money + d_money));


        }




    }

    public void backBtn(View view){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                intent.putExtra("money", String.valueOf(money));
                intent.putExtra("interest", String.valueOf(interest));
                intent.putExtra("term", String.valueOf(term));

                startActivity(intent);
            }
        });
    }

    public void resultBtn(View view){
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 121);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 121){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}