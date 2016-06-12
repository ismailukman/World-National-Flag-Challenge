package com.example.worldnationalflagchallenge;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.lukman.worldnationalflagchallenge.R;

public class Home extends Activity {
	static MediaPlayer player;
	ImageView audio, help, about;
	Button africa, asia, europe, oceania, namerica, samerica;
	int p=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int ot = getResources().getConfiguration().orientation;
	    switch (ot) {
	    case Configuration.ORIENTATION_LANDSCAPE:
	        setContentView(R.layout.home);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.home);
	        break;
	    }
        
        audio = (ImageView) findViewById(R.id.imageView2);
        help = (ImageView) findViewById(R.id.imageView3);
        about = (ImageView) findViewById(R.id.imageView4);
        player = MediaPlayer.create(this, R.raw.best);
		player.setLooping(true);
		player.start();
	    asia = (Button)findViewById(R.id.button2);
	    samerica = (Button)findViewById(R.id.button6);
	    europe = (Button)findViewById(R.id.button3);
	    namerica = (Button)findViewById(R.id.button4);
	    oceania = (Button)findViewById(R.id.button5);
	    africa =(Button)findViewById(R.id.button1);
	    
	    
	    africa.setOnClickListener(new View.OnClickListener(){
	 	   @Override
	 	  public void onClick(View view) {
	 	        Intent act2 = new Intent(view.getContext(), Africa.class);
	 	        startActivity(act2);
	 	      }
	 	    });
	    asia.setOnClickListener(new View.OnClickListener(){
		 	   @Override
		 	  public void onClick(View view) {
		 	        Intent act2 = new Intent(view.getContext(), Asia.class);
		 	        startActivity(act2);
		 	      }
		 	});
	    europe.setOnClickListener(new View.OnClickListener(){
		 	   @Override
		 	  public void onClick(View view) {
		 	        Intent act2 = new Intent(view.getContext(), Europe.class);
		 	        startActivity(act2);
		 	     }
		 	});
	    namerica.setOnClickListener(new View.OnClickListener(){
		 	   @Override
		 	  public void onClick(View view) {
		 	        Intent act2 = new Intent(view.getContext(), NAmerica.class);
		 	        startActivity(act2);
		 	      }
		 	});
	    oceania.setOnClickListener(new View.OnClickListener(){
		 	   @Override
		 	  public void onClick(View view) {
		 	        Intent act2 = new Intent(view.getContext(), Oceania.class);
		 	        startActivity(act2);
		 	      }
		 	});
	    samerica.setOnClickListener(new View.OnClickListener(){
		 	   @Override
		 	  public void onClick(View view) {
		 	        Intent act2 = new Intent(view.getContext(), SAmerica.class);
		 	        startActivity(act2);
		 	      }
		   });
	    
	    audio.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	player.pause();
	        	switch(p){
				case 1:
					  p++;
					  audio.setImageResource(R.drawable.audionot);
					  player.pause();
				
				break;
				case 2:
					  p--;
					  audio.setImageResource(R.drawable.audio);
					  player.start();
				break;
					}
				}
			});
	    
	    about.setOnClickListener(new View.OnClickListener() {
	    	@Override
	        public void onClick(View v) {
	        	Intent act2 = new Intent(v.getContext(), About.class);
		        startActivity(act2);
	        }
	    });
	    
	    
	    
	    help.setOnClickListener(new View.OnClickListener() {
	    	@Override
	        public void onClick(View v) {
	        	Intent act2 = new Intent(v.getContext(), Help.class);
		        startActivity(act2);
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
	        setContentView(R.layout.home);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.home);
	        break;
	    }
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    
    @Override
  	public boolean onOptionsItemSelected(MenuItem item){
  		switch(item.getItemId()){
  		case R.id.exit:
  	          Home.this.finish();
  	          stopMusic();
  	        super.onDestroy();
  	          return true;        
  	    default:
  	          return super.onOptionsItemSelected(item);
  	        } 
  	    }
    
    @Override
   	public void onDestroy ()
   	{
   		super.onDestroy();
   		if(player != null)
   		{
   		try{
   		 player.stop();
   		 player.release();
   			}finally {
   				player = null;
   		}}
   	}
    			public void stopMusic()
    			{
    				player.stop();
    				player.release();
    				player = null;
    			}
    
}
