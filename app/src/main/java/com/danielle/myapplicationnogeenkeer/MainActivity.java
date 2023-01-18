package com.danielle.myapplicationnogeenkeer;

import android.os.Bundle;
import android.util.Log;

import com.danielle.myapplicationnogeenkeer.models.Student;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.danielle.myapplicationnogeenkeer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static FragmentManager fm;
    private ActivityMainBinding binding;
    private static Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.fm = this.getSupportFragmentManager();

        Bundle extras = getIntent().getExtras();
        student = new Student(extras.getInt("student_id"), extras.getString("naam"), extras.getString("email"));
        System.out.println(student.getId());

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public static void ReplaceFragment(Fragment fragment) {
        FragmentTransaction fT = fm.beginTransaction();
        fT.replace(R.id.nav_host_fragment_activity_main, fragment);
        fT.addToBackStack(fragment.getTag());
        fT.commit();
    }

    public static void PreviousFragment() {
        fm.popBackStackImmediate();
    }

    public static Student getLoggedInStudent() {
        return student;
    }
}