package com.karacasoft.hipointernshipentry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.karacasoft.hipointernshipentry.photos.PhotosFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.contentLayout, PhotosFragment.newInstance())
                .commit();
    }
}
