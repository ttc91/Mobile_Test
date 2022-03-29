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
import android.view.View;
import android.widget.Button;

import com.android.mobile_test.databinding.ActivityResultBinding;

public class MainActivity2 extends AppCompatActivity {

    String money;
    String interest;
    String term;

    private Button btn_back;
    private Button btn_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        init();



    }

    public void init(){
        btn_back = findViewById(R.id.btn_back);
        btn_result = findViewById(R.id.btn_result);

        compute();
    }

    public void compute(){

        //Số tiền lãi = Số tiền gửi x lãi suất (%/năm) x số ngày thực gửi/360

        Bundle extras = getIntent().getExtras();

        money = extras.getString("money");
        interest = extras.getString("interest");
        term = extras.getString("term");


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