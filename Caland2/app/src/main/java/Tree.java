package com.app.caland;
import java.util.*;
import com.app.caland.Node;
class Tree
{
    private Node root;
    private Stack<String> st;
    private Stack<Double> ml;
    private Stack<Double>ul;
   protected static double finmark=0.0;
    private List<String>uls;
    private List<String>mls;
    private static int denom=0;
    private static int mark=0;
    private final static int m = 1;
    private final static int n = 2;
    private final static int o = 3;
    private com.app.caland.CalculatorBrain clb;

    public Tree()
    {
        root = null;
        st=new Stack<>();
        ml=new Stack<>();
        ul=new Stack<>();
        uls=new ArrayList<>();
        mls=new ArrayList<>();
        clb = new com.app.caland.CalculatorBrain();
    }

    public void insert(String s)
    {
        // InfixToPostfixs c = new InfixToPostfixs(s);
        // s = c.translate();
        Stack<Node> stk = new Stack<>();
        // System.out.println("s="+s);
        s = s + " "+"#";

        // System.out.println("s="+s);
        //int i = 0;
        String[] sy = s.split(" +");
        //for(String k:sy)
        //   System.out.print(k+" ");
        //System.out.println(sy.length);

        //if(sy[0].equals("22"))
        //  System.out.println("yes 22");
        //char checkSpace;
        Node newNode;
        for(String symbol:sy)
        {
            if(symbol.equals("#"))
                break;

            // System.out.println(symbol);
            if(symbol.equals(clb.SQUAREROOT)){
                Node p=stk.pop();
                newNode=new Node(symbol);
                newNode.leftChild=null;
                newNode.rightChild=p;
                p.prev=newNode;
                stk.push(newNode);
            }
            else if(symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/")){
                Node p=stk.pop();
                Node q=stk.pop();
                newNode=new Node(symbol);
                newNode.leftChild=q;
                newNode.rightChild=p;
                q.prev=newNode;
                p.prev=newNode;
                stk.push(newNode);
            }

            else{
                newNode=new Node(symbol);
                stk.push(newNode);
            }
            //symbol="";
            //++i;
        }
        root = stk.pop();
        //System.out.println(root.data);
    }

   /** public void check(){
        //System.out.println("root mark "+this.root.mark);
        System.out.println("finalmark "+mark);
        System.out.println("denominator "+denom);
        //  System.out.println(root.rightChild.expval);
    }**/

    // tree traversal method
   /** public void traverse(int type)
    {

        switch (type)
        {
            case 1:
                System.out.print("Preorder Traversal:   ");
                preOrder(root);
                break;
            case 2:
                System.out.print("Inorder Traversal:     ");
                inOrder(root);
                break;
            case 3:
                System.out.print("Postorder Traversal:   ");
                postOrder(root);
                break;

            // case 4:
            default:
                System.out.println("Invalid Choice");
        }
    }

    private void preOrder(Node localRoot)
    {
        if (localRoot != null)
        {
            System.out.print(localRoot.data);
            localRoot.displayNode();
            localRoot.getExpval();
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    private void inOrder(Node localRoot)
    {
        if (localRoot != null)
        {
            inOrder(localRoot.leftChild);
            localRoot.displayNode();
            localRoot.getExpval();
            inOrder(localRoot.rightChild);
        }
    }

    private void postOrder(Node localRoot)
    {
        if (localRoot != null)
        {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            localRoot.displayNode();
            localRoot.getExpval();
            localRoot.getMark();
        }
    }**/




    public void ev(){
        evaluate(root);
    }
    private void evaluate(Node t){

        if(t!=null){
            evaluate(t.leftChild);
            evaluate(t.rightChild);

            if(t.data.equals(clb.SQUAREROOT)){
                String s=st.pop();
                t.expval=Math.sqrt(t.rightChild.expval);
                //double x=Math.sqrt(Double.parseDouble(s));
                //System.out.println(x);
                //t.expval=x;
                // System.out.println("Sqrt "+t.expval);
                st.push(Double.toString(t.expval));
            }

            else if(!isOp(t.data)){
                st.push(t.data);
                t.expval=Double.parseDouble(t.data);
            }

            else{
                String x=st.pop();
                String y=st.pop();
                double z=Double.parseDouble(x);
                double a=Double.parseDouble(y);
                String d=t.data;
                switch (d) {
                    case "+":
                        z=z+a;
                        break;
                    case "-":
                        z=a-z;
                        break;
                    case "*":
                        z=z*a;
                        break;
                    case "/":
                        z=a/z;
                        break;
                }

                st.push(Double.toString(z));
                t.expval=z;
            }
        }

    }
    public boolean isOp(String ele){
        return ele.equals("+") || ele.equals("-") || ele.equals("*") || ele.equals("/") ;
    }
    //List<Double> ml=new ArrayList<>();
    //List<Double> ul=new ArrayList<>();
    //public double comapre
    public void excmp(String ms,String us){

        // System.out.println("Precedance not considered");
        // ul.clear();
        //ml.clear();
        double max = 0.0;

        // excm(this.root,v.root);
        String [] usrStrAr=us.split("\\s+");
        ArrayList<String> usal=new ArrayList<>();
        for(String x:usrStrAr){
            usal.add(x);
        }

        String [] mlsStrAr=ms.split("\\s+");
        if(usal.size()==mlsStrAr.length) //if precedence is not considered
            max = 50.0;
        else                            //if user string is more or less than model string
            max = 40.0;
        double count=0;
        //int i = 0;
        //System.out.println("len "+mlsStrAr.length);
        for (String x:mlsStrAr){

            if (!usal.isEmpty() && usal.contains(x)){
                ++count;
                usal.remove(x);
            }
            else if(usal.isEmpty())
                break;

        }
        /**if(usal.isEmpty() && count==mlsStrAr.length)
            max=100;**/
        // System.out.println("len "+mlsStrAr.length);

        finmark = count/mlsStrAr.length*max;
        //System.out.println("max "+max);
        //System.out.println(finmark);
        //System.out.println("count"+count);
        // return finmark;
    }
    public void excmp1(String ms,String us){

        // System.out.println("Precedance not considered");
        // ul.clear();
        //ml.clear();
        double max = 0.0;


        // excm(this.root,v.root);
        String [] usrStrAr=us.split("\\s+");
        ArrayList<String> usal=new ArrayList<>();
        ArrayList<String> usal1=new ArrayList<>();
        for(String x:usrStrAr){
            usal.add(x);
            usal1.add(x);
        }

        String [] mlsStrAr=ms.split("\\s+");
        double count=0;
        int mslen=ms.length();
        int uslen=us.length();
        //int i = 0;
        //System.out.println("len "+mlsStrAr.length);
        for (String x:mlsStrAr){

            if (!usal.isEmpty() && usal.contains(x)){
                ++count;
                usal.remove(x);
            }
            else if(usal.isEmpty())
                break;

        }
        if(usal.isEmpty() && count==mlsStrAr.length)
            max=100;
        else if (usal1.size() >= mlsStrAr.length)
            max=100;
        else if (usal.size()==1 && usal.contains("0"))
            max = 100;
       else if(usal1.size() < mlsStrAr.length)
           max=40;


        //if()
        // System.out.println("len "+mlsStrAr.length);

        finmark = count/mlsStrAr.length*max;
        //System.out.println("max "+max);
        //System.out.println(finmark);
        //System.out.println("count"+count);
        // return finmark;
    }



    public void cmp(Tree v,String ms,String us) throws EmptyStackException {


       try{
           if(this.root.expval==v.root.expval){
               excmp1(ms,us);
               exend();
           }
           else {
               compare(this.root, v.root);
               end();
           }

        }catch(EmptyStackException n){
            excmp(ms,us);
            exend();
        }



    }

    public void compare(Node mt,Node ut)  {


        if(mt!=null && ut!=null ){


            compare(mt.leftChild,ut.leftChild);
            compare(mt.rightChild,ut.rightChild);

            if(mt.data.equals(clb.SQUAREROOT) && ut.data.equals(clb.SQUAREROOT)){

                ml.pop();
                ul.pop();

                if(mt.expval==ut.expval){
                    mark+=n;
                }
                else
                    mark+=m;

                ml.push(mt.expval);
                ul.push(ut.expval);

                denom+=n;
                // System.out.println("denom= "+denom);

            }

            if(isOp(mt.data) && isOp(ut.data)&& mt.expval==ut.expval){

                mark+=o;
                denom=denom+3;
                ml.pop();
                ml.pop();
                ul.pop();
                ul.pop();
                ml.push(mt.expval);
                ul.push(ut.expval);

                //   System.out.println("denom= "+denom);
                //throw EmptyStackException e;

            }


            else if(!isOp(mt.data) && !isOp(ut.data)){
                ml.push(mt.expval);
                ul.push(ut.expval);
                // denom+=1;
            }

            else if(isOp(mt.data) && isOp(ut.data)){

                double r=ml.pop();
                double s=ml.pop();
                double t=ul.pop();
                double u=ul.pop();
                String v=mt.data;
                String w=ut.data;
                denom+=o;

                //  System.out.println("r="+r+" s="+s +" t="+t+" u="+u+" v="+v +" w="+w);
                boolean f=v.equals(w);
                //System.out.println("f="+f);

                if((r==t && s==u && f) || (f && r==u && s==t))
                    mark+=o;

                else if((r==t && s==u) || (r==u && s==t) || (f && s==u) || (f && s==t) || (f && r==u) || (f && r==t))
                    mark+=n;

                else if(f || r==t || r==u || s==t || s==u )
                    mark+=m;

                else
                    mark+=0;

                //denom+=1;

                ml.push(mt.expval);
                ul.push(ut.expval);

            }


        }

    }
    
    
   /* public void mark(){
        
        if(root.mark==1)
            return;
        finmark();
    }*/

   /** public void mrk(Node n){

        if(n!=null){

            mrk(n.leftChild);
            mrk(n.rightChild);
            //System.out.print(n.mark+ " ");
        }
    }

    public double finmark(){

        if(finmark==-1){
            finmark=1.0;
            return finmark;
        }
        // finmark=0.0;
        // finm(this.root);
        // System.out.println("final mark="+finmark);

        end();
        return finmark;
    }

    /*public void finm(Node n){
        
        if(n!=null){
            finm(n.leftChild);
            finm(n.rightChild);
            
            if(isOp(n.data)){
                finmark+=n.mark;
            
            }
        }
    }**/
    public void end(){
       /* if(this.root.mark==1){
            finmark=1.0;
        return;
        }*/
        double x=mark;
        finmark=x/denom*100;
       // System.out.println("Finallllllllllll "+finmark);
    }
    public double finmark1()
    {
        return finmark;
    }

    public void exend(){
        //System.out.println("Exception finalmark "+finmark );
    }

}
 
