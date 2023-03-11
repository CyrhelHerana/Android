package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class PizzaMain extends AppCompatActivity  implements PizzaMenu.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_main);


        Log.d("DEBUG", getResources().getConfiguration().orientation + "");

        if (savedInstanceState == null) {
            // Instance of first fragment
            PizzaMenu firstFragment = new PizzaMenu();

            // Add Fragment to FrameLayout (flContainer), using FragmentManager
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.flContainer, firstFragment);
            ft.commit();
        }

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            PizzaDetails secondFragment = new PizzaDetails();
            Bundle args = new Bundle();
            args.putInt("position", 0);
            secondFragment.setArguments(args);          // (1) Communicate with Fragment using Bundle
            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
            ft2.add(R.id.flContainer2, secondFragment);
            ft2.commit();
        }
    }

    @Override
    public void onPizzaItemSelected(int position) {
        Toast.makeText(this, "Called By Fragment A: position - "+ position, Toast.LENGTH_SHORT).show();

        // Load Pizza Detail Fragment
        PizzaDetails secondFragment = new PizzaDetails();

        Bundle args = new Bundle();
        args.putInt("position", position);
        secondFragment.setArguments(args);


        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContainer2, secondFragment)
                    //.addToBackStack(null)
                    .commit();
        }else{
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContainer, secondFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}