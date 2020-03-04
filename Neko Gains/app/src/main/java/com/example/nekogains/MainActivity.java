package com.example.nekogains;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static Context context;
    DatabaseHelper dbh;
    SharedPreferences settings;
    User user;
    Intent intent;
    int id = 0;
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //for now, clears database on start until login system is uninitialized
        this.deleteDatabase("Workout.db");
        super.onCreate(savedInstanceState);

        context = getContext();
        dbh = DatabaseHelper.getInstance(this);
        settings = getSharedPreferences("preferences", MODE_PRIVATE);

        //If user is not in database create a new user
        if(!registered()) {
            System.out.println("Not registered, going to questionnaire");
            setContentView(R.layout.activity_init);
        } else {
            System.out.println("Registered, showing home");
            setContentView(R.layout.activity_main);
            BottomNavigationView bottomNavigationView = findViewById(R.id.bot_nav);
            bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        }

        //commented out because homefrag calls for user's xp when a user has not been created yet
        //perhaps solution would be to start questionnaire/login as the first activity instead?

        /*getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new HomeFrag()).commit();
        FloatingActionButton fab = findViewById(R.id.bot_fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Fragment selectedFragment = null;
                selectedFragment = new PreworkoutFrag();
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, selectedFragment).commit();
            }
        });*/

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFrag();
                            break;
                        case R.id.nav_setting:
                            selectedFragment = new SettingFrag();
                            break;
                        case R.id.nav_progress:
                            selectedFragment = new ProgressFrag();
                            break;
                        case R.id.nav_workout:
                            selectedFragment = new PreworkoutFrag();
                            break;
                        case R.id.nav_store:
                            selectedFragment = new StoreFrag();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, selectedFragment).commit();
                    return true;
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public int getUserId() {
        return id;
    }

    public static Context getContext() {
        return context;
    }

    public boolean registered() {
        return settings.getBoolean("registered", false);
    }

    public void startQuestionnaire(View view) {
        //run questionnaire and assign values to user
        intent = new Intent(this, Questionnaire.class);
        startActivityForResult(intent, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_FIRST_USER) {
                HashMap<String, String> results = (HashMap<String, String>)data.getExtras().getSerializable("RESULTS");
                id = dbh.insertEmptyUser();
                dbh.insertNewGame("100", "30600");
                for (HashMap.Entry<String, String> entry : results.entrySet()) {
                    System.out.println(entry.getKey());
                    System.out.println(entry.getValue());
                    dbh.updateUserData(id, entry.getKey(), entry.getValue());
                }
                //id = dbh.insertNewUser(results[0], results[1], results[2], results[3], results[4], results[5]);

                System.out.println(id);
                user = new User(dbh, id);

                //Set the view to main
                setContentView(R.layout.activity_main);
                BottomNavigationView bottomNavigationView = findViewById(R.id.bot_nav);
                bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
                Toolbar toolbar = findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

                //Change user settings to say it's registered
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("registered", true);
                editor.apply();
            }
        } else {
            throw new ActivityNotFoundException();
        }
    }
}
