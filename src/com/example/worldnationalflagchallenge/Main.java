package com.example.worldnationalflagchallenge;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.lukman.worldnationalflagchallenge.R;

public class Main extends Activity {
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int ot = getResources().getConfiguration().orientation;
	    switch (ot) {
	    case Configuration.ORIENTATION_LANDSCAPE:
	        setContentView(R.layout.main);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.main);
	        break;
	    }
        
        btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener(){
     	   @Override
     	  public void onClick(View view) {
     	        Intent main = new Intent(view.getContext(), Home.class);
     	        startActivity(main);
     	      }
     	    });
        }

    @Override
	public void onConfigurationChanged(Configuration newConfig) {
	    // TODO Auto-generated method stub
	    super.onConfigurationChanged(newConfig);

	    int ot = getResources().getConfiguration().orientation;
	    switch (ot) {
	    case Configuration.ORIENTATION_LANDSCAPE:
	        setContentView(R.layout.main);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.main);
	        break;
	    }
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
  	public boolean onOptionsItemSelected(MenuItem item){
  		switch(item.getItemId()){
  		case R.id.exit:
  	          Main.this.finish();
  	          return true;        
  	    default:
  	          return super.onOptionsItemSelected(item);
  	        } 
  	    }
    
}
