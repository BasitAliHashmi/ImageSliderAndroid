package com.oobsolutions.imagesliderandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.oobsolutions.imagesliderandroid.fragment.ImageSliderControl;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadSliderControl();
    }

    void LoadSliderControl(){
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ImageSliderControl slideShowFrag = new ImageSliderControl().newInstance(ImagesUrl());
        fragmentTransaction.add(R.id.fragment_container, slideShowFrag, "SlideShowProductImages");
        fragmentTransaction.commit();
    }

    ArrayList<String> ImagesUrl(){
        ArrayList<String> temp = new ArrayList<String>();
        temp.add("https://s-media-cache-ak0.pinimg.com/736x/6c/a1/f0/6ca1f08da1dc46ef8a0c146031a91ff0.jpg");
        temp.add("http://tgj.roccamedia.netdna-cdn.com/wp-content/uploads/2015/10/SocksandSandalsUglyShoes-TheGentlemansJournal.jpg");
        temp.add("http://farm8.staticflickr.com/7231/7005185964_f29fa3fed6_o.jpg");

        return  temp;
    }

}
