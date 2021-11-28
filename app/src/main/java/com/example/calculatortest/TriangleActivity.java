package com.example.calculatortest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TriangleActivity extends AppCompatActivity {

    private EditText edInputRadian;
    private EditText edInputDegrees;
    private TextView edOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            Log.i("info", "landscape"); // 横屏
            setContentView(R.layout.activity_triangle_land);

        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            Log.i("info", "portrait"); // 竖屏
            setContentView(R.layout.activity_triangle_port);
        }

        edInputRadian = findViewById(R.id.edInputRadian);
        edInputDegrees = findViewById(R.id.edInputDegrees);

        edOutput = findViewById(R.id.edOutput);

        Button sin = findViewById(R.id.sin);
        Button cos = findViewById(R.id.cos);
        Button tan = findViewById(R.id.tan);
        Button cot = findViewById(R.id.cot);
        Button clear = findViewById(R.id.clear);

        Button calculate = findViewById(R.id.calculate);
        Button system = findViewById(R.id.system);
        Button triangle = findViewById(R.id.triangle);
        Button unit = findViewById(R.id.unit);

        sin.setOnClickListener(view -> onClickSin());
        cos.setOnClickListener(view -> onClickCos());
        tan.setOnClickListener(view -> onClickTan());
        cot.setOnClickListener(view -> onClickCot());
        clear.setOnClickListener(view -> onClickClear());

        calculate.setOnClickListener(view -> onClickCalculate());
        system.setOnClickListener(view -> onClickSystem());
        unit.setOnClickListener(view -> onClickUnit());
        triangle.setOnClickListener(view -> onClickTriangle());
    }

    private void onClickCalculate() {
        Intent intent = new Intent(TriangleActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void onClickSystem() {
        Intent intent = new Intent(TriangleActivity.this, SystemActivity.class);
        startActivity(intent);
    }

    private void onClickUnit() {
        Intent intent = new Intent(TriangleActivity.this, UnitActivity.class);
        startActivity(intent);
    }

    private void onClickTriangle() {
        Toast.makeText(TriangleActivity.this, "This is Triangle!", Toast.LENGTH_LONG).show();
    }

    @SuppressLint("DefaultLocale")
    private void onClickSin() {
        this.convertDegreesAndRadian();
        edOutput.setText(String.format("%.3f", Math.sin(Double.parseDouble(edInputRadian.getText().toString()))));
    }

    @SuppressLint("DefaultLocale")
    private void onClickCos() {
        this.convertDegreesAndRadian();
        edOutput.setText(String.format("%.3f", Math.cos(Double.parseDouble(edInputRadian.getText().toString()))));
    }

    @SuppressLint("DefaultLocale")
    private void onClickTan() {
        this.convertDegreesAndRadian();
        if (edInputDegrees.getText().toString().equals("90"))
            edOutput.setText("不存在");
        else
            edOutput.setText(String.format("%.3f", Math.tan(Double.parseDouble(edInputRadian.getText().toString()))));
    }

    @SuppressLint("DefaultLocale")
    private void onClickCot() {
        this.convertDegreesAndRadian();
        if (edInputDegrees.getText().toString().equals("0"))
            edOutput.setText("不存在");
        else
            edOutput.setText(String.format("%.3f", this.cot(Math.sin(Double.parseDouble(edInputRadian.getText().toString())),
                    Math.cos(Double.parseDouble(edInputRadian.getText().toString())))));
    }

    private void onClickClear() {
        edInputRadian.setText("");
        edInputDegrees.setText("");
        edOutput.setText("");
    }

    private double cot(double sin, double cos) {
        return cos / sin;
    }

    @SuppressLint("DefaultLocale")
    private void convertDegreesAndRadian() {
        String radian = edInputRadian.getText().toString();
        String degrees = edInputDegrees.getText().toString();

        if (!radian.equals("")) {
            double _radian = Double.parseDouble(radian);
            edInputDegrees.setText(String.format("%.1f", Math.toDegrees(_radian)));
        } else if (!degrees.equals("")) {
            double _degrees = Double.parseDouble(degrees);
            edInputRadian.setText(String.format("%.3f", Math.toRadians(_degrees)));
        } else {
            Toast.makeText(TriangleActivity.this, "输入为空", Toast.LENGTH_LONG).show();
        }
    }
}