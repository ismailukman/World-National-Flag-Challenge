package com.example.worldnationalflagchallenge;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import com.lukman.worldnationalflagchallenge.R;

public class Help extends Activity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int ot = getResources().getConfiguration().orientation;
	    switch (ot) {
	    case Configuration.ORIENTATION_LANDSCAPE:
	        setContentView(R.layout.help);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.help);
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
	        setContentView(R.layout.help);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.help);
	        break;
	    }
	}
}
