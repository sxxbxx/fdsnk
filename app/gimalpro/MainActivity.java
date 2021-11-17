package org.app.gimalpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    Button button;
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private fragment1 frag1;
    private fragment2 frag2;
    private fragment3 frag3;
    private fragment4 frag4;
    private fragment5 frag5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView =findViewById(R.id.bottomnavi);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.a_home:
                        setFrag(0);
                        break;
                    case R.id.a_ar:
                        setFrag(1);
                        break;
                    case R.id.a_video:
                        setFrag(2);
                        break;
                    case R.id.a_cap:
                        setFrag(3);
                        break;
                    case R.id.a_line:
                        setFrag(4);
                        break;
                }
                return true;
            }
        });
        frag1 = new fragment1();
        frag2 = new fragment2();
        frag3 = new fragment3();
        frag4 = new fragment4();
        frag5 = new fragment5();
        setFrag(0); //첫화면 지정 선택함수

    }

    private void setFrag(int n){
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        switch (n){
            case 0:
                ft.replace(R.id.main_frame,frag1);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame,frag2);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame,frag3);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame,frag4);
                ft.commit();
                break;
            case 4:
                ft.replace(R.id.main_frame,frag5);
                ft.commit();
                break;
        }
    }
}