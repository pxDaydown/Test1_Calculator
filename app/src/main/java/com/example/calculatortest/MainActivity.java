package com.example.calculatortest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.LinkedList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private TextView _calculate;//算数表达式
    private TextView _result;//结果


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_0 = findViewById(R.id.number0);
        Button btn_1 = findViewById(R.id.number1);
        Button btn_2 = findViewById(R.id.number2);
        Button btn_3 = findViewById(R.id.number3);
        Button btn_4 = findViewById(R.id.number4);
        Button btn_5 = findViewById(R.id.number5);
        Button btn_6 = findViewById(R.id.number6);
        Button btn_7 = findViewById(R.id.number7);
        Button btn_8 = findViewById(R.id.number8);
        Button btn_9 = findViewById(R.id.number9);
        // .小数点
        Button btn_point = findViewById(R.id.sign_point);
        // ac
        Button btn_ac = findViewById(R.id.sign_ac);
        // del
        Button btn_del = findViewById(R.id.sign_del);
        // +
        Button btn_add = findViewById(R.id.sign_add);
        // -
        Button btn_sub = findViewById(R.id.sign_sub);
        // *
        Button btn_multply = findViewById(R.id.sign_multiply);
        // /
        Button btn_divide = findViewById(R.id.sign_divide);
        // =
        Button btn_equal = findViewById(R.id.sign_equal);
        _calculate = findViewById(R.id.num_calculate);
        _result = findViewById(R.id.num_result);
        Button calculate = findViewById(R.id.calculate);
        Button system = findViewById(R.id.system);
        Button triangle = findViewById(R.id.triangle);
        Button unit = findViewById(R.id.unit);

        btn_0.setOnClickListener(ClickInHere);
        btn_1.setOnClickListener(ClickInHere);
        btn_2.setOnClickListener(ClickInHere);
        btn_3.setOnClickListener(ClickInHere);
        btn_4.setOnClickListener(ClickInHere);
        btn_5.setOnClickListener(ClickInHere);
        btn_6.setOnClickListener(ClickInHere);
        btn_7.setOnClickListener(ClickInHere);
        btn_8.setOnClickListener(ClickInHere);
        btn_9.setOnClickListener(ClickInHere);
        btn_point.setOnClickListener(ClickInHere);
        btn_ac.setOnClickListener(ClickInHere);
        btn_del.setOnClickListener(ClickInHere);
        btn_add.setOnClickListener(ClickInHere);
        btn_sub.setOnClickListener(ClickInHere);
        btn_multply.setOnClickListener(ClickInHere);
        btn_divide.setOnClickListener(ClickInHere);
        btn_equal.setOnClickListener(ClickInHere);

        calculate.setOnClickListener(view -> onClickCalculate());
        system.setOnClickListener(view -> onClickSystem());
        unit.setOnClickListener(view -> onClickUnit());
        triangle.setOnClickListener(view -> onClickTriangle());

    }

    private void onClickCalculate(){
        Toast.makeText(MainActivity.this,"This is Calculate!",Toast.LENGTH_LONG).show();
    }

    private void onClickSystem(){
        Intent intent=new Intent(MainActivity.this,SystemActivity.class);
        startActivity(intent);
    }

    private void onClickUnit(){
        Intent intent=new Intent(MainActivity.this,UnitActivity.class);
        startActivity(intent);
    }

    private void onClickTriangle(){
        Intent intent=new Intent(MainActivity.this,TriangleActivity.class);
        startActivity(intent);
    }

    private final OnClickListener ClickInHere = new OnClickListener() {
        @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
        @Override
        public void onClick(View v) {
            String input = _calculate.getText().toString();

            switch (v.getId()) {
                case R.id.number0:
                case R.id.number1:
                case R.id.number2:
                case R.id.number3:
                case R.id.number4:
                case R.id.number5:
                case R.id.number6:
                case R.id.number7:
                case R.id.number8:
                case R.id.number9:
                    _calculate.setText(input + ((Button) v).getText());
                    break;
                case R.id.sign_point:
                case R.id.sign_add:
                case R.id.sign_sub:
                case R.id.sign_multiply:
                case R.id.sign_divide:
                    if (!input.equals("")) {

//不允许连续输入多个运算符
                        char c = input.charAt(input.length() - 1);
                        if ((byte) c > 41 && (byte) c < 48) {
//acsii的42-47为* + , - . /
                            _calculate.setText(input.substring(0, input.length() - 1)
                                    + ((Button) v).getText());
                        } else {
                            _calculate.setText(input + ((Button) v).getText());
                        }
                    }
                    break;
                case R.id.sign_ac:
//第一次ac清空_calculate，第二次清空_result
                    if (input.equals("")) {
//_calculate为空则清空计算结果
                        _result.setText("");
                    }
                    input = "";
                    _calculate.setText(input);
                    break;
                case R.id.sign_equal:
//_result显示运算结果
                    _result.setText(getResult());
                    break;
                case R.id.sign_del:
                    if (!input.equals("")) {
                        _calculate.setText(input.substring(0, input.length() - 1));
                    }
                    break;
                default:
// %和( )未实现
                    break;
            }
        }
    };

    private String getResult() {
        String cal = _calculate.getText().toString();
        if (cal.equals(""))
            return "";
        double resnum;
        resnum = calRst(backTempcal(cal));
//resnum小数位为0输出整形int
        String resstr;
        if (resnum % 1 == 0) {
            resstr = String.valueOf((int) resnum);
        } else {
            resstr = String.valueOf(resnum);
        }
        return resstr;
    }
//leetcode的四则运算算法，将数字类型改为double并在其中加入小数的运算

    public static double calRst(LinkedList<String> tempBackcal) {
        Stack<Double> calStk = new Stack<>();
        for (String c : tempBackcal) {
// 1.数字，入栈
            if (isNumeric(c)) {
                calStk.push(Double.valueOf(c)); // string to int
            }
// 2.非数字，则为符号，出栈两个元素计算出结果然后再入栈该计算值

            else {
                Double a = calStk.pop();
                Double b = calStk.pop();
                switch (c.toCharArray()[0]) {
// 注意减法和除法时，注意出栈的顺序与原表达式是相反的
                    case '+':
                        calStk.push(b + a);
                        continue;
                    case '-':
                        calStk.push(b - a);
                        continue;
                    case '*':
                        calStk.push(b * a);
                        continue;
                    case '/':
                        calStk.push(b / a);
                }
            }
        }
        return calStk.pop();
    }

    // 计算后缀表达式
    public static LinkedList<String> backTempcal(String cal) {
        Stack<String> stkEles = new Stack<>();
        LinkedList<String> tempBackcal = new LinkedList<>();
        for (int i = 0; i < cal.length(); i++) {
// 1.遇到了数字
            if (Character.isDigit(cal.charAt(i))) {
// 注意多位数的获取
                int k = i + 1;
//小数点也加入数字中
                for (; k < cal.length() && (Character.isDigit
                        (cal.charAt(k)) || cal.charAt(k) == '.'); k++) {
                }
                tempBackcal.add(cal.substring(i, k));
                i = k - 1;// 更新 i
                continue;
            }
// 2.遇到了乘除运算符
            if (cal.charAt(i) == '/' || cal.charAt(i) == '*') {
                while (!stkEles.isEmpty() && (stkEles.lastElement().equals("/")
                        || stkEles.lastElement().equals("*"))) {
                    tempBackcal.add(stkEles.pop()); // 弹出优先级相同或以上的栈内运算符
                }
                stkEles.add(String.valueOf(cal.charAt(i))); // 运算符入栈
                continue;
            }
// 3.遇到了加减运算符
            if (cal.charAt(i) == '+' || cal.charAt(i) == '-') {
                while (!stkEles.isEmpty() && !isNumeric(stkEles.lastElement())) {
                    tempBackcal.add(stkEles.pop()); // 弹出优先级相同或以上的栈内运算符
                }
                stkEles.add(String.valueOf(cal.charAt(i))); // 运算符入栈
            }
        }
// 4.最后弹出栈内所有元素到表达式
        while (stkEles.size() > 0) {
            tempBackcal.add(stkEles.pop());
        }
        return tempBackcal;
    }

    //判断是否为数字
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
//是否为数字或者小数点
            if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.') {
                return false;
            }
        }
        return true;
    }
}