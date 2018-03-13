package com.example.lenovo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static String dataServer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* String dd = "{\"a\":\"Ali\",\"m\":\"Mojtaba\",\"me\":\"Hossein\"}";


        try {
            JSONObject object = new JSONObject(dd);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        Button buttonAddShortcut = (Button) findViewById(R.id.buttonAddShortcut);
        Button buttonRemoveShortcut = (Button) findViewById(R.id.buttonRemoveShortcut);



        //in onCreate
        if (getIntent() != null) {
            //if we are launching from launcher shortcut
            Bundle bundel = getIntent().getExtras();
            if (bundel != null) {
                String name = bundel.getString("extra");
                Log.i("name" , name);
               // onSearchPressed(url);
            }
        }

        buttonAddShortcut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addShortcut();
            }
        });
        buttonRemoveShortcut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeShortcut();
            }
        });


    }

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // do what you want to be done on home button click event
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    private void addShortcut() {
        //Adding shortcut for MainActivity
        //on Home screen
        Intent shortcutIntent = new Intent(getApplicationContext(),
                MainActivity.class);
        shortcutIntent.putExtra("extra", "shortCutTest ");
        shortcutIntent.setAction(Intent.ACTION_MAIN);

        Intent addIntent = new Intent();
        addIntent
                .putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "HelloWorldShortcut");
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(getApplicationContext(),
                        R.mipmap.ic_launcher));

        addIntent
                .setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(addIntent);


    }

    private void removeShortcut() {

        //Deleting shortcut for MainActivity
        //on Home screen
        Intent shortcutIntent = new Intent(getApplicationContext(),
                MainActivity.class);
        shortcutIntent.setAction(Intent.ACTION_MAIN);

        Intent addIntent = new Intent();
        addIntent
                .putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "HelloWorldShortcut");

        addIntent
                .setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(addIntent);
    }

}
