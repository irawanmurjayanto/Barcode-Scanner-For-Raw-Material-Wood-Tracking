package com.example.barcodsawmillnew_scanzebra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LogNumber extends AppCompatActivity {
    TextView isilog;
    Button  ButClose_isilog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_number);

        isilog=findViewById(R.id.text_isilog);
        ButClose_isilog=findViewById(R.id.ButClose_Isilog);

        ButClose_isilog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}