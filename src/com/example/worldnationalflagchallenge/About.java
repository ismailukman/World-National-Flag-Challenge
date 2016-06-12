package com.example.worldnationalflagchallenge;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import com.lukman.worldnationalflagchallenge.R;

public class About extends Activity{
		@Override
    	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int ot = getResources().getConfiguration().orientation;
	    switch (ot) {
	    case Configuration.ORIENTATION_LANDSCAPE:
	        setContentView(R.layout.about);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.about);
	        break;
	    }
        
	}
		@Override
		public void onConfigurationChanged(Configuration newConfig) {
		    // TODO Auto-generated method stub
		    super.onConfigurationChanged(newConfig);

		    int ot = getResources().getConfiguration().orientation;
		    switch (ot) {
		    case Configuration.ORIENTATION_LANDSCAPE:
		        setContentView(R.layout.about);
		        break;
		    case Configuration.ORIENTATION_PORTRAIT:
		        setContentView(R.layout.about);
		        break;
		    }
		}
}
