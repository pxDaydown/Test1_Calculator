package com.example.calculatortest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SystemActivity extends AppCompatActivity {

    private EditText edBinarySystem;
    private EditText edOctalSystem;
    private EditText edDecimalSystem;
    private EditText edHexSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);

        Button converse = findViewById(R.id.convert);
        Button clear = findViewById(R.id.clear);
        Button calculate = findViewById(R.id.calculate);
        Button system = findViewById(R.id.system);
        Button triangle = findViewById(R.id.triangle);
        Button unit = findViewById(R.id.unit);
        edBinarySystem = findViewById(R.id.edBinarySystem);
        edOctalSystem = findViewById(R.id.edOctalSystem);
        edDecimalSystem = findViewById(R.id.edDecimalSystem);
        edHexSystem = findViewById(R.id.edHexSystem);

        calculate.setOnClickListener(view -> onClickCalculate());
        system.setOnClickListener(view -> onClickSystem());
        unit.setOnClickListener(view -> onClickUnit());
        triangle.setOnClickListener(view -> onClickTriangle());

        converse.setOnClickListener(view -> onClickConvert());
        clear.setOnClickListener(view -> onClickClear());

        edBinarySystem.setOnClickListener(view -> onClickEditText(2));
        edOctalSystem.setOnClickListener(view -> onClickEditText(8));
        edDecimalSystem.setOnClickListener(view -> onClickEditText(10));
        edHexSystem.setOnClickListener(view -> onClickEditText(16));


    }

    @SuppressLint("SetTextI18n")
    private void onClickConvert() {
        String binary = edBinarySystem.getText().toString();
        String octal = edOctalSystem.getText().toString();
        String decimal = edDecimalSystem.getText().toString();
        String hex = edHexSystem.getText().toString();

        if (!binary.equals("")) {
            int _binary = Integer.valueOf(binary,2);
            edOctalSystem.setText(Integer.toOctalString(_binary));
            edDecimalSystem.setText("" + _binary);
            edHexSystem.setText(Integer.toHexString(_binary));
        } else if (!octal.equals("")) {
            int _octal = Integer.valueOf(octal,8);
            edBinarySystem.setText(Integer.toBinaryString(_octal));
            edDecimalSystem.setText("" + _octal);
            edHexSystem.setText(Integer.toHexString(_octal));
        } else if (!decimal.equals("")) {
            edBinarySystem.setText(Integer.toBinaryString(Integer.parseInt(decimal)));
            edOctalSystem.setText(Integer.toOctalString(Integer.parseInt(decimal)));
            edHexSystem.setText(Integer.toHexString(Integer.parseInt(decimal)));
        } else if (!hex.equals("")) {
            int _hex = Integer.valueOf(hex,16);
            edBinarySystem.setText(Integer.toBinaryString(_hex));
            edOctalSystem.setText(Integer.toOctalString(_hex));
            edDecimalSystem.setText("" + _hex);
        } else {
            Toast.makeText(SystemActivity.this,"输入为空",Toast.LENGTH_LONG).show();
        }

    }

    private void onClickClear(){
        edBinarySystem.setText("");
        edOctalSystem.setText("");
        edDecimalSystem.setText("");
        edHexSystem.setText("");
    }

    private void onClickSystem(){
        Toast.makeText(SystemActivity.this,"This is System!",Toast.LENGTH_LONG).show();
    }

    private void onClickCalculate() {
        Intent intent = new Intent(SystemActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void onClickUnit(){
        Intent intent=new Intent(SystemActivity.this,UnitActivity.class);
        startActivity(intent);
    }

    private void onClickTriangle(){
        Intent intent=new Intent(SystemActivity.this,TriangleActivity.class);
        startActivity(intent);
    }

    private void onClickEditText(int system){
            if(system == 2){
                edOctalSystem.setText("");
                edDecimalSystem.setText("");
                edHexSystem.setText("");
            }else if(system == 8){
                edBinarySystem.setText("");
                edDecimalSystem.setText("");
                edHexSystem.setText("");
            }else if(system == 10){
                edBinarySystem.setText("");
                edOctalSystem.setText("");
                edHexSystem.setText("");
            }else if(system == 16){
                edBinarySystem.setText("");
                edOctalSystem.setText("");
                edDecimalSystem.setText("");
            }
    }

}