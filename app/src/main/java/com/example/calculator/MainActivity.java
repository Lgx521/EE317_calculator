package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentNumber = "";
    private String operator = "";
    private double firstNumber = 0;
    private double memory = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // 设置数字按钮
        setNumberButtonListener(R.id.btn_0, "0");
        setNumberButtonListener(R.id.btn_1, "1");
        setNumberButtonListener(R.id.btn_2, "2");
        setNumberButtonListener(R.id.btn_3, "3");
        setNumberButtonListener(R.id.btn_4, "4");
        setNumberButtonListener(R.id.btn_5, "5");
        setNumberButtonListener(R.id.btn_6, "6");
        setNumberButtonListener(R.id.btn_7, "7");
        setNumberButtonListener(R.id.btn_8, "8");
        setNumberButtonListener(R.id.btn_9, "9");
        setNumberButtonListener(R.id.btn_dot, ".");

        // 设置操作按钮
        setOperatorButtonListener(R.id.btn_plus, "+");
        setOperatorButtonListener(R.id.btn_minus, "−");
        setOperatorButtonListener(R.id.btn_multiply, "×");
        setOperatorButtonListener(R.id.btn_divide, "÷");

        // 清除按钮
        findViewById(R.id.btn_clear).setOnClickListener(v -> {
            currentNumber = "";
            operator = "";
            firstNumber = 0;
            display.setText("0");
        });

        // 退格按钮
        findViewById(R.id.btn_backspace).setOnClickListener(v -> {
            if (currentNumber.length() > 0) {
                currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
                display.setText(currentNumber.isEmpty() ? "0" : currentNumber);
            }
        });

        // 百分号按钮
        findViewById(R.id.btn_percent).setOnClickListener(v -> {
            if (!currentNumber.isEmpty()) {
                double number = Double.parseDouble(currentNumber) / 100;
                currentNumber = String.valueOf(number);
                display.setText(currentNumber);
            }
        });

        // 等于按钮
        findViewById(R.id.btn_equals).setOnClickListener(v -> {
            if (!currentNumber.isEmpty() && !operator.isEmpty()) {
                double secondNumber = Double.parseDouble(currentNumber);
                double result = calculate(firstNumber, secondNumber, operator);
                currentNumber = String.valueOf(result);
                operator = "";
                display.setText(currentNumber);
            }
        });

        // 内存操作按钮
        findViewById(R.id.btn_mc).setOnClickListener(v -> memory = 0); // 清除内存
        findViewById(R.id.btn_m_plus).setOnClickListener(v -> {
            if (!currentNumber.isEmpty()) {
                memory += Double.parseDouble(currentNumber);
            }
        });
        findViewById(R.id.btn_m_minus).setOnClickListener(v -> {
            if (!currentNumber.isEmpty()) {
                memory -= Double.parseDouble(currentNumber);
            }
        });
        findViewById(R.id.btn_mr).setOnClickListener(v -> {
            currentNumber = String.valueOf(memory);
            display.setText(currentNumber);
        });
    }

    private void setNumberButtonListener(int buttonId, final String number) {
        findViewById(buttonId).setOnClickListener(v -> {
            currentNumber += number;
            display.setText(currentNumber);
        });
    }

    private void setOperatorButtonListener(int buttonId, final String op) {
        findViewById(buttonId).setOnClickListener(v -> {
            if (!currentNumber.isEmpty()) {
                firstNumber = Double.parseDouble(currentNumber);
                operator = op;
                currentNumber = "";
            }
        });
    }

    private double calculate(double first, double second, String op) {
        switch (op) {
            case "+":
                return first + second;
            case "−":
                return first - second;
            case "×":
                return first * second;
            case "÷":
                return second != 0 ? first / second : Double.NaN;
            default:
                return 0;
        }
    }
}