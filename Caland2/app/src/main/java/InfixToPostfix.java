/**
 * Created by Swetha Banagiri on 12-11-2015.
 */
package com.app.caland;
import com.app.caland.CalculatorBrain;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Swetha Banagiri on 12-11-2015.
 */
public class InfixToPostfix {
    protected String expression;
    private  Stack<String> stack = new Stack<>();
    CalculatorBrain cb = new CalculatorBrain();
    public InfixToPostfix()
    {
        expression = " ";
    }
    public String toInfix(ArrayList<String> temp)
    {
        int i=0,l=0,c=0;
        String k="";
        String s = "";

        while(i<temp.size()+1)
        {
            if(i==temp.size())
            {
                s=s+k;
                break;
            }

            String s1=temp.get(i);



            if(s1.equals("1")||s1.equals("2")||s1.equals("3")||s1.equals("4")||s1.equals("5")||s1.equals("6")||s1.equals("7")||s1.equals("8")||s1.equals("9")||s1.equals("0")||s1.equals("."))
            {
                String r ="";

                if(i<temp.size()-1)
                    r=temp.get(i+1);
                if(!r.equals(cb.SQUAREROOT))
                    k=k+s1;
                else{
                    k=k+r+s1;
                    s=s+k;
                    k="";}

            }
            else if(s1.equals("*")||s1.equals("/")||s1.equals("+")||s1.equals("-"))
            {
                c++;

                if(c==2)
                {
                    k="("+k+")";
                    c=1;
                }
                k=k+s1;
            }

            else if(s1.equals(cb.INVERT))
            {
                String r="",n="";
                if (i<=temp.size()-1){
                    r=temp.get(i-1);

                }

                if (r.equals("MR"))
                    s="1"+"/"+s;
                else
                    k="1/"+k;

            }

            else if(s1.equals(cb.SQUAREROOT))
            {
                //System.out.println("6");
                String r="",n="";
                if (i<=temp.size()-1){
                    r=temp.get(i-1);

                }
                if (i<temp.size()-1){
                    n=temp.get(i+1);

                }
                if(r.equals("=") )
                    k=s1+k;
                if(r.equals("MR") ){
                    s=s1+s;
                }
                if(n.equals(cb.SQUAREROOT)){
                    k=s1+k;

                }

            }
            else if(s1.equals("=")&& (!k.equals("")|| !k.equals(" ")))
            {
                //System.out.println("6");
                k="("+k+")";


            }

            else if(s1.equals("M+"))
            {
                l++;
                if(l==1)
                    s=s+k;
                else
                    s=s+"+"+k;
                k="";

            }
            else if(s1.equals("M-"))
            {
                l++;
                if(l==1)
                    s=s+"0-"+k;
                else
                    s=s+"-"+k;
                k=" ";

            }
            else if(s1.equals("MR"))
            {
                s="("+s+k+")";
                //s="("+s+")";

            }
            i++;

        }


        return s;
    }

    public String translate()
    {

        String output = "";
        expression=putSpace(expression);
        String [] k=expression.split("\\s+");
        for(String x:k){
            if(isOp(x)){

                while(!stack.empty() && precedence(stack.peek())>=precedence(x))
                    output+=" "+stack.pop();

                stack.push(x);
            }

            else if (x.equals(cb.SQUAREROOT)){
                stack.push(x);
            }

            else if(x.equals("(")){
                stack.push(x);
            }

            else if(x.equals(")")){
                while(!stack.peek().equals("(")){
                    output=output+" "+stack.pop();
                }
                stack.pop();
                while(!stack.isEmpty() && stack.peek().equals(cb.SQUAREROOT)){
                    output=output+" "+stack.pop();
                }

            }
            else{
                output=output+" "+x;
            }

        }

        while(!stack.empty())
        {
            output += " "+stack.pop() ;
        }

        output=output.trim();
        return output;
    }

    //to check priority
    public static int precedence(String op){
        if(op.equals("+") || op.equals("-"))
            return 1;
        else if(op.equals("*") || op.equals("/"))
            return 2;
        else
            return 0;
    }

    public static int precedence(char operator)
    {
        if(operator == '+' || operator =='-')
            return 1;
        else if(operator == '*' || operator == '/')
            return 2;
        else
            return 0;
    }

    public boolean isOp(String ele){
        return ele.equals("+") || ele.equals("-") || ele.equals("*") || ele.equals("/");
    }

    public boolean isOperator(char element)
    {
        return element == '*' || element == '-' || element == '/' || element == '+';
    }
    //make 2*3 as 2 * 3
    public String putSpace(String s){

        int len=s.length();
        int i=0;
        String op="";
        char ch;
        while(i<len){
            ch=s.charAt(i);
            if (isOperator(ch) || ch=='(' || ch==')' || ch==cb.SQUAREROOT.charAt(0)){
                op=op+" "+ch+" ";
            }
            else
                op=op+ch;
            i+=1;

        }
        op=op.trim();
        return op;
    }

}



