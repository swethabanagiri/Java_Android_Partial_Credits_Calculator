package com.app.caland;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FinalActivity extends AppCompatActivity {

    private TextView model;
    private TextView user;
    private TextView pts;
    private static ArrayList<String> u;
    private static String m;
    private static String p;
    private com.app.caland.InfixToPostfix useritp;
    private com.app.caland.Tree modeltree;
    private com.app.caland.Tree usertree;
    private double finalm;
    private String userinfix;
    private String finalans;
    private String userPostfix;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        model = (TextView) findViewById(R.id.textView4);
        user = (TextView) findViewById(R.id.textView5);
        pts = (TextView) findViewById(R.id.textView6);
        useritp = new com.app.caland.InfixToPostfix();
        modeltree = new com.app.caland.Tree();
        usertree = new com.app.caland.Tree();
        u= new ArrayList<String>();


        Bundle p = getIntent().getExtras();
        u =p.getStringArrayList("keys");
        //user.setText(u);

        m =p.getString("model");
       // model.setText(m);
        useritp.expression=m;
        String modelPostfix = useritp.translate();
        modelPostfix = modelPostfix.trim();
        modeltree.insert(modelPostfix);
        modeltree.ev();
        useritp.expression = useritp.toInfix(u);
        userinfix = useritp.expression;
        userPostfix = useritp.translate();
        userPostfix=userPostfix.trim();
        usertree.insert(userPostfix);
        usertree.ev();
        modeltree.cmp(usertree, modelPostfix, userPostfix);
        finalm = com.app.caland.Tree.finmark;
        model.setText(m);
        user.setText(userinfix);
        pts.setText(new DecimalFormat("##.##").format(finalm));

        }

    public void goBack()
    {
        Intent i = new Intent(FinalActivity.this, MainActivity.class);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_final, menu);
        return true;
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
