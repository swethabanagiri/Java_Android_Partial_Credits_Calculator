package com.app.caland;

public class CalculatorBrain {
    // 3 + 6 = 9
    // 3 & 6 are called the operand.
    // The + is called the operator.
    // 9 is the result of the operation.
    private double mOperand;
    private double mWaitingOperand;
    private String mWaitingOperator;
    private double mCalculatorMemory;

    // operator types
    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";

    public static final String CLEAR = "C" ;
    public static final String CLEARMEMORY = "MC";
    public static final String ADDTOMEMORY = "M+";
    public static final String SUBTRACTFROMMEMORY = "M-";
    public static final String RECALLMEMORY = "MR";
    public static final String SQUAREROOT = "√";
    //public static final String SQUARED = "x²";
    public static final String INVERT = "1/x";
    public static final String TOGGLESIGN = "+/-";
    public static final String SINE = "sin";
    public static final String COSINE = "cos";
    public static final String TANGENT = "tan";
    public static final String end = "X";
    public static final String reset = "RESET";
    // public static String expression;
    //public static String temp;

    // public static final String EQUALS = "=";

    // constructor
    public CalculatorBrain() {
        // initialize variables upon start
        mOperand = 0;
        mWaitingOperand = 0;
        mWaitingOperator = "";
        mCalculatorMemory = 0;
        // expression="0";
        //temp="";
    }

    public void setOperand(double operand) {
        mOperand = operand;
    }

    public double getResult() {
        return mOperand;
    }

    // used on screen orientation change
    public void setMemory(double calculatorMemory) {
        mCalculatorMemory = calculatorMemory;
    }

    // used on screen orientation change
    public double getMemory() {
        return mCalculatorMemory;
    }

    public String toString() {
        return Double.toString(mOperand);
    }

    protected double performOperation(String operator) {
         
        /*
        * If you are using Java 7, then you can use switch in place of if statements
        *
        *     switch (operator) {
        *     case CLEARMEMORY:
        *         calculatorMemory = 0;
        *         break;
        *     case ADDTOMEMORY:
        *         calculatorMemory = calculatorMemory + operand;
        *         break;
        *     etc...
        *     }
        */

        if (operator.equals(CLEAR)) {
            mOperand = 0;
            mWaitingOperator = "";
            mWaitingOperand = 0;
            // expression="0";
            // mCalculatorMemory = 0;
        }
        else if(operator.equals(reset)){
            mCalculatorMemory = 0;

        }
        else if (operator.equals(CLEARMEMORY)) {
            mCalculatorMemory = 0;
        } else if (operator.equals(ADDTOMEMORY)) {
            // expression=expression+"+"+temp;
            //temp="";
            mCalculatorMemory = mCalculatorMemory + mOperand;

        } else if (operator.equals(SUBTRACTFROMMEMORY)) {
            mCalculatorMemory = mCalculatorMemory - mOperand;
            // expression=expression+"-"+temp;
            //temp="";
        } else if (operator.equals(RECALLMEMORY)) {
            mOperand = mCalculatorMemory;
        } else if (operator.equals(SQUAREROOT)) {
            mOperand = Math.sqrt(mOperand);
            //temp=temp+SQUAREROOT;
        }/* else if (operator.equals(SQUARED)) {
            mOperand = mOperand * mOperand;
            //temp=temp+SQUARED;
        } */
        else if (operator.equals(INVERT)) {
            if (mOperand != 0) {
                mOperand = 1 / mOperand;
            }
            // temp=temp+INVERT;
        } else if (operator.equals(TOGGLESIGN)) {
            mOperand = -mOperand;
        } else if (operator.equals(SINE)) {
            mOperand = Math.sin(Math.toRadians(mOperand));
            // temp=temp+SINE;// Math.toRadians(mOperand) converts result to degrees
        } else if (operator.equals(COSINE)) {
            mOperand = Math.cos(Math.toRadians(mOperand));
            //temp=temp+COSINE;// Math.toRadians(mOperand) converts result to degrees
        } else if (operator.equals(TANGENT)) {
            mOperand = Math.tan(Math.toRadians(mOperand));
            //  temp=temp+TANGENT;// Math.toRadians(mOperand) converts result to degrees
        }
        else if (operator.equals(end)) {
            mCalculatorMemory=0;
            mOperand = 0;
            mWaitingOperator = "";
            mWaitingOperand = 0;

        } else {
            performWaitingOperation();
            mWaitingOperator = operator;
            mWaitingOperand = mOperand;
        }

        return mOperand;
    }

    protected void performWaitingOperation() {

        if (mWaitingOperator.equals(ADD)) {
            //temp=temp+Integer.toString(mWaitingOperand)+ADD+Integer.toString(mOperand);
            mOperand = mWaitingOperand + mOperand;

        } else if (mWaitingOperator.equals(SUBTRACT)) {
            //temp=temp+Integer.toString(mWaitingOperand)+SUBTRACT+Integer.toString(mOperand);
            mOperand = mWaitingOperand - mOperand;

        } else if (mWaitingOperator.equals(MULTIPLY)) {
            //temp=temp+Integer.toString(mWaitingOperand)+MULTIPLY+Integer.toString(mOperand);
            mOperand = mWaitingOperand * mOperand;

        } else if (mWaitingOperator.equals(DIVIDE)) {
            if (mOperand != 0) {
                //temp=temp+Integer.toString(mWaitingOperand)+DIVIDE+Integer.toString(mOperand);
                mOperand = mWaitingOperand / mOperand;

            }
        }

    }

}