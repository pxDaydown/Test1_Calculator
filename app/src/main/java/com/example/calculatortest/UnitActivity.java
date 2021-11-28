package com.example.calculatortest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class UnitActivity extends AppCompatActivity {
    RadioGroup radioGroup1;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;

    Spinner spinner1;
    Spinner spinner2;
    List<String> list1 = new ArrayList<>();
    List<String> list2 = new ArrayList<>();
    List<String> list3 = new ArrayList<>();

    TextView editText1;
    TextView editText2;

    int firstChoice1 = 0;
    int secondChoice1 = 0;

    int CButton1 = 0;

    ArrayAdapter<String> adapter;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);

        radioGroup1 = findViewById(R.id.choice1);
        radioButton1 = findViewById(R.id.radio1);
        radioButton2 = findViewById(R.id.radio2);
        radioButton3 = findViewById(R.id.radio3);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        editText1 = findViewById(R.id.num_calculate);
        editText2 = findViewById(R.id.num_result);

        Button btn_6 = findViewById(R.id.number6);
        Button btn_7 = findViewById(R.id.number7);
        Button btn_8 = findViewById(R.id.number8);
        Button btn_9 = findViewById(R.id.number9);
        Button btn_2 = findViewById(R.id.number2);
        Button btn_3 = findViewById(R.id.number3);
        Button btn_4 = findViewById(R.id.number4);
        Button btn_5 = findViewById(R.id.number5);
        Button btn_1 = findViewById(R.id.number1);
        Button btn_0 = findViewById(R.id.number0);
        Button sing_change = findViewById(R.id.change);
        Button sign_ac = findViewById(R.id.sign_ac);
        Button sign_del = findViewById(R.id.sign_del);
        Button sign_point = findViewById(R.id.sign_point);

        Button calculate = findViewById(R.id.calculate);
        Button system = findViewById(R.id.system);
        Button triangle = findViewById(R.id.triangle);
        Button unit = findViewById(R.id.unit);

        calculate.setOnClickListener(view -> onClickCalculate());
        system.setOnClickListener(view -> onClickSystem());
        unit.setOnClickListener(view -> onClickUnit());
        triangle.setOnClickListener(view -> onClickTriangle());

        sign_point.setOnClickListener(view -> editText1.setText(editText1.getText() + "."));
        btn_6.setOnClickListener(view -> editText1.setText(editText1.getText() + "6"));
        btn_7.setOnClickListener(view -> editText1.setText(editText1.getText() + "7"));
        btn_8.setOnClickListener(view -> editText1.setText(editText1.getText() + "8"));
        btn_9.setOnClickListener(view -> editText1.setText(editText1.getText() + "9"));
        btn_2.setOnClickListener(view -> editText1.setText(editText1.getText() + "2"));
        btn_3.setOnClickListener(view -> editText1.setText(editText1.getText() + "3"));
        btn_4.setOnClickListener(view -> editText1.setText(editText1.getText() + "4"));
        btn_5.setOnClickListener(view -> editText1.setText(editText1.getText() + "5"));
        btn_1.setOnClickListener(view -> editText1.setText(editText1.getText() + "1"));
        btn_0.setOnClickListener(view -> editText1.setText(editText1.getText() + "0"));
        sing_change.setOnClickListener(view -> {
            if(CButton1 == 1){
                editText2.setText(show1(firstChoice1,secondChoice1));

            }
            else if (CButton1 == 2){
                editText2.setText(show2(firstChoice1,secondChoice1));

            }
            else {
                editText2.setText(show3(firstChoice1,secondChoice1));

            }
        });
        sign_ac.setOnClickListener(view -> editText1.setText(""));
        sign_del.setOnClickListener(view -> {
            char[] cc = editText1.getText().toString().toCharArray();
            StringBuilder ss = new StringBuilder();
            for(int i = 0;i<cc.length-1;i++){
                ss.append(cc[i]);
            }
            editText1.setText(ss.toString());
        });

        list1.add("元");
        list1.add("角");
        list1.add("分");

        list2.add("千米");
        list2.add("米");
        list2.add("厘米");
        list2.add("毫米");

        list3.add("吨");
        list3.add("千克");
        list3.add("克");


        radioGroup1.setOnCheckedChangeListener(new OnCheckedChangeListenerImp());

        adapter = new ArrayAdapter<>(UnitActivity.this, android.R.layout.simple_spinner_item, list1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                firstChoice1 = i;
                adapterView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                secondChoice1 = i;
                adapterView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void onClickCalculate() {
        Intent intent = new Intent(UnitActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void onClickSystem(){
        Intent intent=new Intent(UnitActivity.this,SystemActivity.class);
        startActivity(intent);
    }

    private void onClickUnit(){
        Toast.makeText(UnitActivity.this,"This is Unit!",Toast.LENGTH_LONG).show();
    }

    private void onClickTriangle(){
        Intent intent=new Intent(UnitActivity.this,TriangleActivity.class);
        startActivity(intent);
    }


    private class OnCheckedChangeListenerImp implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if (radioButton1.getId() == checkedId) {
                CButton1 = 1;
                adapter = new ArrayAdapter<>(UnitActivity.this, android.R.layout.simple_spinner_item, list1);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter);
                spinner2.setAdapter(adapter);

                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        firstChoice1 = i;
                        adapterView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        secondChoice1 = i;
                        adapterView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            } else if (radioButton2.getId() == checkedId) {
                CButton1 = 2;
                adapter = new ArrayAdapter<>(UnitActivity.this, android.R.layout.simple_spinner_item, list2);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter);
                spinner2.setAdapter(adapter);

                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        firstChoice1 = i;
                        adapterView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        secondChoice1 = i;
                        adapterView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            } else{
                CButton1 = 3;
                adapter = new ArrayAdapter<>(UnitActivity.this, android.R.layout.simple_spinner_item, list3);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter);
                spinner2.setAdapter(adapter);

                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        firstChoice1 = i;
                        adapterView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        secondChoice1 = i;
                        adapterView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

        }
    }

    public String show1(int f,int s){
        double d1 = Double.parseDouble(editText1.getText().toString());
        double d2;
        if(f == 0){
            if(s == 0){
                d2 = 1.0;
            }
            else if(s == 1){
                d2 = 10.0;
            }
            else {
                d2 = 100.0;
            }
        }
        else if(f == 1){
            if(s == 0){
                d2 = 0.1;
            }
            else if(s == 1){
                d2 = 1.0;
            }
            else {
                d2 = 10.0;
            }
        }
        else {
            if(s == 0){
                d2 = 0.01;
            }
            else if(s == 1){
                d2 = 0.1;
            }
            else {
                d2 = 1.0;
            }
        }
        return (d1 * d2 ) + "";
    }

    public String show2(int f,int s){
        double d1 = Double.parseDouble(editText1.getText().toString());
        double d2;

        if(f == 0){
            if(s == 0){
                d2 = 1.0;
            }
            else if(s == 1){
                d2 = 1000.0;
            }
            else if(s == 2){
                d2 = 100000.0;
            }
            else {
                d2 = 1000000.0;
            }
        }
        else  if(f == 1){
            if(s == 0){
                d2 = 0.001;
            }
            else if(s == 1){
                d2 = 1.0;
            }
            else if(s == 2){
                d2 = 1000.0;
            }
            else {
                d2 = 1000000.0;
            }
        }
        else if(f == 2){
            if(s == 0){
                d2 = 0.000001;
            }
            else if(s == 1){
                d2 = 0.001;
            }
            else if(s == 2){
                d2 = 1.0;
            }
            else {
                d2 = 1000.0;
            }
        }
        else {
            if(s == 0){
                d2 = 0.000000001;
            }
            else if(s == 1){
                d2 = 0.000001;
            }
            else if(s == 2){
                d2 = 0.001;
            }
            else {
                d2 = 1.0;
            }
        }

        return (d1 * d2 ) + "";
    }

    public String show3(int f,int s){
        double d1 = Double.parseDouble(editText1.getText().toString());
        double d2;
        if(f == 0){
            if (s == 0){
                d2 = 1.0;
            }
            else if(s == 1){
                d2 = 1000.0;
            }
            else {
                d2 = 1000000.0;
            }
        }
        else if(f == 1){
            if (s == 0){
                d2 = 0.001;
            }
            else if(s == 1){
                d2 = 1.0;
            }
            else {
                d2 = 1000.0;
            }
        }
        else {
            if (s == 0){
                d2 = 0.000001;
            }
            else if(s == 1){
                d2 = 0.001;
            }
            else {
                d2 = 1.0;
            }
        }
        return (d1 * d2 ) + "";
    }


}
