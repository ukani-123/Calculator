package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        TextView resultTv,solutionTv;
        MaterialButton btn_c, btn_Lcous,btn_Rcous;
        MaterialButton btn_plus,btn_minus,btn_multi,btn_devid,btn_equals;
        MaterialButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;
        MaterialButton btn_ac,btn_dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(btn_c ,R.id.btn_c);
        assignId(btn_ac ,R.id.btn_ac);
        assignId(btn_plus ,R.id.btn_plus);
        assignId(btn_minus ,R.id.btn_minus);
        assignId(btn_devid ,R.id.btn_devid);
        assignId(btn_equals ,R.id.btn_equal);
        assignId(btn1 ,R.id.btn_1);
        assignId(btn2 ,R.id.btn_2);
        assignId(btn3 ,R.id.btn_3);
        assignId(btn4 ,R.id.btn_4);
        assignId(btn5 ,R.id.btn_5);
        assignId(btn6 ,R.id.btn_6);
        assignId(btn7 ,R.id.btn_7);
        assignId(btn8 ,R.id.btn_8);
        assignId(btn9 ,R.id.btn_9);
        assignId(btn0 ,R.id.btn_0);
        assignId(btn_dot ,R.id.btn_dot);
        assignId(btn_Rcous ,R.id.btn_Rcous);
        assignId(btn_Lcous ,R.id.btn_Lcous);
        assignId(btn_multi,R.id.btn_multi);

    }

    void assignId(MaterialButton btn , int id){
        btn=findViewById(id);
                btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MaterialButton btn = (MaterialButton) view;
        String btnText = btn.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (btnText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (btnText.equals("=")) {

            solutionTv.setText(resultTv.getText());
            return;
        }
        if (btnText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + btnText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }
    String getResult(String data){

        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            return finalResult;
        }catch (Exception e){
            return "Err";
        }

    }
}