package com.example.explicitintent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText etName;
    Button btnAct1, btnAct2;
    TextView tvResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        btnAct1 = findViewById(R.id.btnAct1);
        btnAct2 = findViewById(R.id.btnAct2);
        tvResults = findViewById(R.id.tvResults);


        btnAct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "please enter all fields,", Toast.LENGTH_SHORT).show();
                } else {
                    String name = etName.getText().toString().trim();
                    Intent intent = new Intent(MainActivity.this, com.example.explicitintent.Activity1.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                }
            }
        });

        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Intent data = result.getData();

                        String surname =  data.getStringExtra("surname");
                        tvResults.setText("Hello!, " + surname);
                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        tvResults.setText("NO RESULT");
                    }
                });

        btnAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString().trim();
                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "please enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, com.example.explicitintent.Activity2.class);

                    someActivityResultLauncher.launch(intent);
                }

            }
        });

    }


}