package com.app.caland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class easy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);
    }

        public void easy1(View v) {
            Intent i = new Intent(easy.this, easy1.class);
            String equn = getResources().getString(R.string.eeq1);
            i.putExtra("equn", equn);
            startActivity(i);


        }
    public void easy2(View v) {
        Intent i = new Intent(easy.this, easy1.class);
        String equn = getResources().getString(R.string.eeq2);
        i.putExtra("equn",equn);
        startActivity(i);


    }
    public void easy3(View v) {
        Intent i = new Intent(easy.this, easy1.class);
        String equn = getResources().getString(R.string.eeq3);
        i.putExtra("equn",equn);
        startActivity(i);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_easy, menu);
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
