package com.app.caland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class easy1 extends AppCompatActivity implements View.OnClickListener {
    private TextView mCalculatorDisplay;
    private TextView mExpr;
    private Boolean userIsInTheMiddleOfTypingANumber = false;
    private com.app.caland.CalculatorBrain mCalculatorBrain;
  /**  private com.app.caland.InfixToPostfix useritp;
    private com.app.caland.Tree modeltree;
    private com.app.caland.Tree usertree;**/
    private static String eq;
    protected static ArrayList<String> temp1;
    private static final String DIGITS = "0123456789.";
    private static String expression="0";
    double finalm= 0.0;
    private static String temp;

    DecimalFormat df = new DecimalFormat("@###########");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy1);

        mCalculatorBrain = new com.app.caland.CalculatorBrain();
        mCalculatorDisplay = (TextView) findViewById(R.id.textView1);
        mExpr = (TextView) findViewById(R.id.textView2);
        temp1 = new ArrayList<String>();
       /** useritp = new com.app.caland.InfixToPostfix();
        modeltree = new com.app.caland.Tree();
        usertree = new com.app.caland.Tree();**/

        Bundle p = getIntent().getExtras();
        eq =p.getString("equn");
        mExpr.setText(eq);


        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(8);

        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);

        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonSubtract).setOnClickListener(this);
        findViewById(R.id.buttonMultiply).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);
        findViewById(R.id.buttonDecimalPoint).setOnClickListener(this);
        findViewById(R.id.buttonEquals).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this);
        findViewById(R.id.buttonClearMemory).setOnClickListener(this);
        findViewById(R.id.buttonAddToMemory).setOnClickListener(this);
        findViewById(R.id.buttonSubtractFromMemory).setOnClickListener(this);
        findViewById(R.id.buttonRecallMemory).setOnClickListener(this);
        findViewById(R.id.buttonReset).setOnClickListener(this);
        findViewById(R.id.end).setOnClickListener(this);



        // The following buttons only exist in layout-land (Landscape mode) and require extra attention.
        // The messier option is to place the buttons in the regular layout too and set android:visibility="invisible".
        if (findViewById(R.id.buttonSquareRoot) != null) {
            findViewById(R.id.buttonSquareRoot).setOnClickListener(this);
        }

        if (findViewById(R.id.buttonInvert) != null) {
            findViewById(R.id.buttonInvert).setOnClickListener(this);
        }

        if (findViewById(R.id.end) != null) {
            findViewById(R.id.end).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v)
    {
        String buttonPressed = ((Button) v).getText().toString();
        if(buttonPressed.equals("RESET")) {
            temp1.clear();
        }
        else if(!buttonPressed.equals("X")) {
            temp1.add(buttonPressed);
        }

        else{
           /**String model = eq;
            String modelPostfix = useritp.translate();
            modelPostfix = modelPostfix.trim();
            useritp.expression = useritp.toInfix(temp1);
           String userPostfix = useritp.translate();
            userPostfix=userPostfix.trim();
            modeltree.insert(modelPostfix);
            modeltree.ev();
            usertree.insert(userPostfix);
            usertree.ev();
            modeltree.cmp(usertree,model,useritp.expression);
            finalm = usertree.finmark();**/
            Intent i = new Intent(easy1.this, FinalActivity.class);
            i.putExtra("model", eq);
            if(temp1.isEmpty())
                temp1.add(" ");
        i.putExtra("keys",temp1);

            startActivity(i);
        }
        if (DIGITS.contains(buttonPressed)) {

            // digit was pressed
            if (userIsInTheMiddleOfTypingANumber) {

                if (buttonPressed.equals(".") && mCalculatorDisplay.getText().toString().contains(".")) {
                    // ERROR PREVENTION
                    // Eliminate entering multiple decimals
                } else {
                    mCalculatorDisplay.append(buttonPressed);

                }

            } else {

                if (buttonPressed.equals(".")) {
                    // ERROR PREVENTION
                    // This will avoid error if only the decimal is hit before an operator, by placing a leading zero
                    // before the decimal
                    mCalculatorDisplay.setText(0 + buttonPressed);
                    temp=temp+buttonPressed;
                } else {
                    mCalculatorDisplay.setText(buttonPressed);

                }

                userIsInTheMiddleOfTypingANumber = true;
            }

        } else {
            // operation was pressed
            if (userIsInTheMiddleOfTypingANumber) {

                mCalculatorBrain.setOperand(Double.parseDouble(mCalculatorDisplay.getText().toString()));
                userIsInTheMiddleOfTypingANumber = false;
            }

            mCalculatorBrain.performOperation(buttonPressed);
            mCalculatorDisplay.setText(df.format(mCalculatorBrain.getResult()));

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_easy1, menu);
        return true;
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save variables on screen orientation change
        outState.putDouble("OPERAND", mCalculatorBrain.getResult());
        outState.putDouble("MEMORY", mCalculatorBrain.getMemory());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore variables on screen orientation change
        mCalculatorBrain.setOperand(savedInstanceState.getDouble("OPERAND"));
        mCalculatorBrain.setMemory(savedInstanceState.getDouble("MEMORY"));
        mCalculatorDisplay.setText(df.format(mCalculatorBrain.getResult()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
