package com.example.explicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    EditText editText;
    Button button, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        editText = findViewById(R.id.editTextTextPersonName);
        button = findViewById(R.id.button);
        cancel = findViewById(R.id.cancel);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String surname = editText.getText().toString().trim();
                if (surname.isEmpty()) {
                    Toast.makeText(Activity2.this, "please enter your surname in the field", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("surname", surname);
                    setResult(RESULT_OK, intent);
                    Activity2.this.finish();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                Activity2.this.finish();
            }
        });

    }
}