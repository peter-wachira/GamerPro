package com.moringaschool.gamerpro.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.gamerpro.Constants.Constants;
import com.moringaschool.gamerpro.R;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,View.OnClickListener{

    @BindView(R.id.trigger)
    Button trigger;
    @BindView(R.id.platforms)
    EditText platforms;
    @BindView(R.id.savedGamesButton) Button mSavedGamesButton;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private DatabaseReference mSearchedPlatformReference;

    private ValueEventListener mSearchedPlatformReferenceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedPlatformReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_PLATFORM);//pinpoint platform node

       mSearchedPlatformReferenceListener = mSearchedPlatformReference.addValueEventListener(new ValueEventListener() { //attach listener
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                for (DataSnapshot platformSnapshot : dataSnapshot.getChildren()) {
                    String platformz = platformSnapshot.getValue().toString();
                    Log.d("Locations updated", "location: " + platformz); //log
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.

            }

        });




        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
        trigger.setOnClickListener(this);
        mSavedGamesButton.setOnClickListener(this);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        String email = intent.getStringExtra("useremail");

        //Update header email address with the email from previous activity
        View headerView =navigationView.getHeaderView(0);
        TextView emailTextView =  headerView.findViewById(R.id.useremail);
        emailTextView.setText(email);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
    @Override
    public void onClick(View v) {
        if( v == trigger){

            String platformz = platforms.getText().toString();

            savePlatformToFirebase(platformz);

//            if(!(platformz).equals("")) {
//                addToSharedPreferences(platformz);
//            }
            Intent intent = new Intent(HomeActivity.this, GamesListActivity.class);
            intent.putExtra("platforms",platformz);
            startActivity(intent);
        }
        if (v == mSavedGamesButton) {
            Intent intent = new Intent(HomeActivity.this, SavedGameListActivity.class);
            startActivity(intent);
        }
    }

    public void savePlatformToFirebase(String platformz) {
        mSearchedPlatformReference.push().setValue(platformz);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedPlatformReference.removeEventListener(mSearchedPlatformReferenceListener);
    }



//    private void addToSharedPreferences(String platformz) {
//        mEditor.putString(Constants.PREFERENCES_PLATFORM_KEY, platformz).apply();
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_mygames) {
            Intent intent = new Intent(HomeActivity.this, SavedGameListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_platforms) {
            Intent intent = new Intent(HomeActivity.this, GamesListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_backup) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_help) {

        }else if (id == R.id.nav_rate) {

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
