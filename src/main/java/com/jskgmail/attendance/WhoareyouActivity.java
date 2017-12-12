package com.jskgmail.attendance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class WhoareyouActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whoareyou);
        Button stu=(Button)findViewById(R.id.button9);
        Button tea=(Button)findViewById(R.id.button12);
        stu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences("who", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("iam", "student");
                editor.commit();
                Intent i=new Intent(WhoareyouActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        tea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences("who", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("iam", "teacher");
                editor.commit();
                Intent i=new Intent(WhoareyouActivity.this,MainteachersActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}
