package com.example.tanmay.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.String;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    protected void calculate(View v)
    {
        EditText num1=(EditText)findViewById(R.id.operand1);
        EditText num2=(EditText)findViewById(R.id.operand2);
        EditText op=(EditText)findViewById(R.id.operator);
        TextView tv=(TextView)findViewById(R.id.tv);
        int n1=Integer.valueOf(num1.getText().toString());
        int n2=Integer.valueOf(num2.getText().toString());
        String ch=op.getText().toString();
        int res;
        if(ch.equalsIgnoreCase("+"))
            res=n1+n2;
        else if(ch.equalsIgnoreCase("-"))
            res=n1-n2;
        else if(ch.equalsIgnoreCase("*"))
            res=n1*n2;
        else if(ch.equalsIgnoreCase("/"))
            res=n1/n2;
        else
            res=0;
        tv.setText(String.valueOf(res));
    }
}
