package com.example.administrator.cookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RegisterpageActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerpage);
        Button handin=(Button)findViewById(R.id.handin);
        handin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterpageActivity.this.finish();
            }
        });
    }
}
