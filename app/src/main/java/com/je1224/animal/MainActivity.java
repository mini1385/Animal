package com.je1224.animal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.kakao.util.helper.Utility.getKeyHash;
import static com.kakao.util.helper.Utility.getPackageInfo;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    FragmentManager fragmentManager;
    Fragment[] fragments=new Fragment[5];

    SearchView searchView;
    TextView tv;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnv=findViewById(R.id.bottom_nav);
        tv=findViewById(R.id.tv);
//        adView=findViewById(R.id.ad);

        // 카카오 해시값
        String keyHash=getKeyHash(this);
        Log.i("TAG",keyHash);

        fragmentManager=getSupportFragmentManager();

        fragments[0]=new Fragment_home();
        fragments[1]=new Fragment_calendar();
        fragments[2]=new Fragment_photo();
        fragments[3]=new Fragment_hospital();
        fragments[4]=new Fragment_shop();

        FragmentTransaction tran=fragmentManager.beginTransaction();
        tran.add(R.id.container,fragments[0]);
        tran.commit();

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentTransaction tran=fragmentManager.beginTransaction();

                switch (menuItem.getItemId()){
                    case R.id.menu_home:
                        tran.replace(R.id.container,fragments[0]);
                        break;
                    case R.id.menu_calender:
                        tran.replace(R.id.container,fragments[1]);
                        break;
                    case R.id.menu_photo:
                        tran.replace(R.id.container,fragments[2]);
                        break;
                    case R.id.menu_hospital:
                        tran.replace(R.id.container,fragments[3]);
                        break;
                    case R.id.menu_shop:
                        tran.replace(R.id.container,fragments[4]);
                        break;
                }
                tran.commit();

                return true;
            }
        });

        Toolbar tb=findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

//        AdRequest adRequest=new AdRequest.Builder().build();
//        adView.loadAd(adRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.side_menu, menu);

        MenuItem item=menu.findItem(R.id.menu_search);
        searchView=(SearchView)item.getActionView();
        searchView.setQueryHint("검색");

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setVisibility(View.INVISIBLE);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Toast.makeText(MainActivity.this,query+"를 검색했습니다.",Toast.LENGTH_SHORT).show();
                searchView.setQuery("",true);
                searchView.onActionViewCollapsed();
                tv.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                tv.setVisibility(View.VISIBLE);
                return false;
            }
        });

        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_my:
                Intent intent1=new Intent(MainActivity.this,MyPageActivity.class);
                startActivity(intent1);
                break;
            case R.id.menu_pet:
                Intent intent2=new Intent(MainActivity.this,MyPetActivity.class);
                startActivity(intent2);
                break;
            case R.id.menu_review:
                Toast.makeText(this, "review", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static String getKeyHash(final Context context) {
        PackageInfo packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES);
        if (packageInfo == null)
            return null;

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                Log.w("TAG", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
        return null;
    }

}
