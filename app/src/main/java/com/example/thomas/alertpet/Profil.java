package com.example.thomas.alertpet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class Profil extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button signOut;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_main);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {

                    startActivity(new Intent(Profil.this, LoginActivity.class));
                    finish();
                }
            }
        };
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(Profil.this, MainActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.profil) {
            Intent intent = new Intent(Profil.this, Profil.class);
            startActivity(intent);
        }
        else if (id == R.id.messages) {
            Intent intent = new Intent(Profil.this, Messages.class);
            startActivity(intent);
        }
        else if (id == R.id.pictures) {
            Intent intent = new Intent(Profil.this, Albums.class);
            startActivity(intent);
        }
        else if (id == R.id.parameters) {
            Intent intent = new Intent(Profil.this, Parametre.class);
            startActivity(intent);
        }
        else if (id == R.id.about) {
            Intent intent = new Intent(Profil.this, About.class);
            startActivity(intent);
        }
        else if (id == R.id.disconnect){
            auth.signOut();
        }
        return true;
    }

    //sign out method
    public void signOut() {
        auth.signOut();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
