/**
 * Created by Swetha Banagiri on 12-11-2015.
 */
package com.app.caland;
public class Node{
        public String data;
        public Node leftChild;
        public Node rightChild;
        public Node prev;
        public double expval;
        public double mark;

        public Node(String x)
        {
            data = x;
            leftChild=null;
            rightChild=null;
            prev=null;
            expval=0;
        }

       /** public void displayNode()
        {
            System.out.print(data + " ");
        }

        public void getExpval(){
            System.out.print(expval+" ");
        }

        public void getMark(){
            System.out.print(mark+" ");
        }**/
    }


