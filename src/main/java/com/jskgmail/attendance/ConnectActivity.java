package com.jskgmail.attendance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConnectActivity extends AppCompatActivity {
  static   String mynaam="",usernamee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        final EditText name=(EditText)findViewById(R.id.editText3);
        final EditText username=(EditText)findViewById(R.id.editText4);
        Button button=(Button)findViewById(R.id.button7) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mynaam=name.getText().toString();
                usernamee=username.getText().toString();
            }
        });

    }
}
