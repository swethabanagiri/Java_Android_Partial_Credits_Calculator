package com.app.caland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class med extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_med, menu);
        return true;
    }
    public void med1(View v) {
        Intent i = new Intent(med.this, easy1.class);
        String equn = getResources().getString(R.string.meq1);
        i.putExtra("equn", equn);
        startActivity(i);


    }
    public void med2(View v) {
        Intent i = new Intent(med.this, easy1.class);
        String equn = getResources().getString(R.string.meq2);
        i.putExtra("equn", equn);
        startActivity(i);


    }
    public void med3(View v) {
        Intent i = new Intent(med.this, easy1.class);
        String equn = getResources().getString(R.string.meq3);
        i.putExtra("equn",equn);
        startActivity(i);


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
