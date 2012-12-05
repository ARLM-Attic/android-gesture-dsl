package com.hfk.android;

import com.hfk.android.gestures.R;
import com.hfk.android.gestures.R.layout;

import android.app.Activity;
import android.os.Bundle;

public class AndroidGestureDSLActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        view = new AndroidGestureDSLView(this);
        
        setContentView(view);
    }
    
    AndroidGestureDSLView view;
}