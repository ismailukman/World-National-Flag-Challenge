package com.example.worldnationalflagchallenge;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.lukman.worldnationalflagchallenge.R;

public class SAmerica extends Activity{
	int test=0, count, c, w, a, b, x;
	public static final String PREFS_NAME = "MyPrefsFile";
	Button ok;
	ImageView flag, title, wrong, right, samerica;
	RadioGroup group;
	RadioButton option;
	TextView up, down, highest, value;
	int HScore;
	SharedPreferences sharedpreferences;
	String Ans,Answer;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int ot = getResources().getConfiguration().orientation;
	    switch (ot) {
	    case Configuration.ORIENTATION_LANDSCAPE:
	        setContentView(R.layout.samerica);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.samerica);
	        break;
	    }

	    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0); 
		// Read a string value with key "LastUse"
	     HScore = settings.getInt("Score", 0);
	    //String lastUse = settings.getString("LastUse", "");
	    
        final ArrayList<Integer> rndm = new ArrayList<Integer>();
        for(int i=0; i<13; i++){
			rndm.add(i);
		}
		Collections.shuffle(rndm);
		
		x = rndm.get(0);
		a = rndm.indexOf(0); // Returns index of first occurrence of 0
		rndm.set(0, (rndm.get(a) ) ); //rndm.get(10) Returns the object at index 10.
		rndm.set(a, x );
		
		x = rndm.get(12);
		b = rndm.indexOf(12);
		rndm.set(12, (rndm.get(b) ) );
		rndm.set(b, x );
		
		ok = (Button)findViewById(R.id.button1);
		flag = (ImageView)findViewById(R.id.imageView2);
		title = (ImageView)findViewById(R.id.imageView1);
		wrong = (ImageView)findViewById(R.id.imageView4);
		right = (ImageView)findViewById(R.id.imageView3);
		samerica = (ImageView)findViewById(R.id.imageView5);
		group = (RadioGroup)findViewById(R.id.radioGroup1);
		up = (TextView)findViewById(R.id.textView1);
		down = (TextView)findViewById(R.id.textView2);
		highest = (TextView)findViewById(R.id.textView3);
	    value = (TextView)findViewById(R.id.textView4);
		
	    final String[] option1 = {"Ecuador","Argentina","Chile","Brazil"};   //Argentina
		final String[] option2 = {"Bolivia","Peru","Colombia","Paraguay"};	//Bolivia
	   	final String[] option3 = {"Chile","Uruguay","Brazil","Venezuela"};	 //Brazil
	   	final String[] option4 = {"Chile","Falkland Islands","Peru","Guyana"};	//Chile
	   	final String[] option5 = {"Peru","Colombia","Argentina","Guyana"};	//Colombia
		final String[] option6 = {"Chile","Uruguay","Venezuela","Ecuador"};	//Ecuador
		final String[] option7 = {"Falkland Islands","Brazil","Bolivia","Uruguay"};	 //Falkland Islands
	   	final String[] option8 = {"Ecuador","Peru","Guyana","Colombia"};	//Guyana
	   	final String[] option9 = {"Chile","Paraguay","Brazil","Uruguay"};	 //Paraguay
	   	final String[] option10 = {"Ecuador","Brazil","Venezuela","Peru"};	//Peru
		final String[] option11 = {"Bolivia","Guyana","Uruguay","Chile"};	//Uruguay
		final String[] option12 = {"Venezuela","Argentina","Ecuador","Guyana"};	  //Venezuela
	    
		value.setText(Integer.toString(HScore));
		
	    ok.setOnClickListener(new View.OnClickListener(){
	   @Override
	  public void onClick(final View view) {
		   switch(rndm.get(test))
			{
		case 0:
			  samerica.setVisibility(View.INVISIBLE);
			  flag.setImageResource(R.drawable.argentina);
		      title.setVisibility(View.VISIBLE);
			  flag.setVisibility(View.VISIBLE);
			  wrong.setVisibility(View.VISIBLE);
			  right.setVisibility(View.VISIBLE);
			  group.setVisibility(View.VISIBLE);
			  up.setVisibility(View.VISIBLE);
			  down.setVisibility(View.VISIBLE);
			  highest.setVisibility(View.VISIBLE);
			  value.setVisibility(View.VISIBLE);
			for (int i = 0; i < group.getChildCount(); i++) {
				((RadioButton) group.getChildAt(i)).setText(option1[i]);
	    		Answer = "Argentina";
			}
	    		test++;
		break;
			  
		case 1:

		  int selected1 = group.getCheckedRadioButtonId();
			option = (RadioButton) findViewById(selected1);
			Ans = (String)option.getText();
			
  			if( (Answer).equals (Ans) ){
  				c++;
  				up.setText(Integer.toString(c));
  			}else
  			{
  				w++;
  				down.setText(Integer.toString(w));
					}
			
			  flag.setImageResource(R.drawable.bolivia);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option2[i]);
		    		Answer = "Bolivia"; 
				}
	    		test++;
		break;
		
		case 2:  
			
			int selected2 = group.getCheckedRadioButtonId();
			option = (RadioButton) findViewById(selected2);
			Ans  = (String)option.getText();

	    			if( (Answer).equals (Ans) ){
	    				c++;
	    				up.setText(Integer.toString(c));
	    			}else
	    			{
	    				w++;
	    				down.setText(Integer.toString(w));
					}
			  flag.setImageResource(R.drawable.brazil);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option3[i]);
	    			Answer = "Brazil";
				}
	    		test++;
		break;
		case 3:  
			  int selected3 = group.getCheckedRadioButtonId();
  			option = (RadioButton) findViewById(selected3);
  			Ans = (String)option.getText();

	    			if( (Answer).equals (Ans) ){
	    				c++;
	    				up.setText(Integer.toString(c));
	    			}else
	    			{
	    				w++;
	    				down.setText(Integer.toString(w));
					}
	    		
	    	  flag.setImageResource(R.drawable.chile);	
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option4[i]);
		    		  Answer = "Chile";
				} 
  			test++;
		break;
		
		case 4:
			 int selected4 = group.getCheckedRadioButtonId();
  			option = (RadioButton) findViewById(selected4);
  			Ans = (String)option.getText();

	    			if( (Answer).equals (Ans) ){
	    				c++;
	    				up.setText(Integer.toString(c));
	    			}else
	    			{
	    				w++;
	    				down.setText(Integer.toString(w));
	    			}
	    			flag.setImageResource(R.drawable.colombia);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option5[i]);
		    		  Answer = "Colombia";
				}
	    		  test++;
		break;
		case 5:

			  int selected5 = group.getCheckedRadioButtonId();
				option = (RadioButton) findViewById(selected5);
				Ans = (String)option.getText();
				
	   			if( (Answer).equals (Ans) ){
	   				c++;
	   				up.setText(Integer.toString(c));
	   			}else
	   			{
	   				w++;
	   				down.setText(Integer.toString(w));
						}
				
				  flag.setImageResource(R.drawable.ecuador);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option6[i]);
			    		Answer = "Ecuador"; 
					}
		    		test++;
			break;
			
			case 6:  
				
				int selected6 = group.getCheckedRadioButtonId();
				option = (RadioButton) findViewById(selected6);
				Ans  = (String)option.getText();

		    			if( (Answer).equals (Ans) ){
		    				c++;
		    				up.setText(Integer.toString(c));
		    			}else
		    			{
		    				w++;
		    				down.setText(Integer.toString(w));
	 					}
				  flag.setImageResource(R.drawable.falkland_islands);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option7[i]);
		    			Answer = "Falkland Islands";
					}
		    		test++;
			break;
			case 7:  
				  int selected7 = group.getCheckedRadioButtonId();
	   			option = (RadioButton) findViewById(selected7);
	   			Ans = (String)option.getText();

		    			if( (Answer).equals (Ans) ){
		    				c++;
		    				up.setText(Integer.toString(c));
		    			}else
		    			{
		    				w++;
		    				down.setText(Integer.toString(w));
	 					}
		    		
		    	  flag.setImageResource(R.drawable.guyana);	
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option8[i]);
			    		  Answer = "Guyana";
					} 
	   			test++;
			break;
			
			case 8:
				 int selected8 = group.getCheckedRadioButtonId();
	   			option = (RadioButton) findViewById(selected8);
	   			Ans = (String)option.getText();

		    			if( (Answer).equals (Ans) ){
		    				c++;
		    				up.setText(Integer.toString(c));
		    			}else
		    			{
		    				w++;
		    				down.setText(Integer.toString(w));
		    			}
		    			flag.setImageResource(R.drawable.paraguay);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option9[i]);
			    		  Answer = "Paraguay";
					}
		    		  test++;
			break;
			case 9:

				  int selected9 = group.getCheckedRadioButtonId();
					option = (RadioButton) findViewById(selected9);
					Ans = (String)option.getText();
					
		   			if( (Answer).equals (Ans) ){
		   				c++;
		   				up.setText(Integer.toString(c));
		   			}else
		   			{
		   				w++;
		   				down.setText(Integer.toString(w));
							}
					
					  flag.setImageResource(R.drawable.peru);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option10[i]);
				    		Answer = "Peru"; 
						}
			    		test++;
				break;
				
				case 10:  
					
					int selected10 = group.getCheckedRadioButtonId();
					option = (RadioButton) findViewById(selected10);
					Ans  = (String)option.getText();

			    			if( (Answer).equals (Ans) ){
			    				c++;
			    				up.setText(Integer.toString(c));
			    			}else
			    			{
			    				w++;
			    				down.setText(Integer.toString(w));
		 					}
					  flag.setImageResource(R.drawable.uruguay);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option11[i]);
			    			Answer = "Uruguay";
						}
			    		test++;
				break;
				case 11:  
					  int selected11 = group.getCheckedRadioButtonId();
		   			option = (RadioButton) findViewById(selected11);
		   			Ans = (String)option.getText();

			    			if( (Answer).equals (Ans) ){
			    				c++;
			    				up.setText(Integer.toString(c));
			    			}else
			    			{
			    				w++;
			    				down.setText(Integer.toString(w));
		 					}
			    		
			    	  flag.setImageResource(R.drawable.venezuela);	
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option12[i]);
				    		  Answer = "Venezuela";
						} 
		   			test++;
				break;   
		   
	  case 12:
			 int selected12 = group.getCheckedRadioButtonId();
			option = (RadioButton) findViewById(selected12);
			Ans = (String)option.getText();

	    			if( (Answer).equals (Ans) ){
	    				c++;
	    				up.setText(Integer.toString(c));
	    			}else
	    			{
	    				w++;
	    				down.setText(Integer.toString(w));
	    			}
	    			    Africa.score = Africa.score + (c*3) - w ;
	    		    	if  (HScore < Africa.score){
		    		    	HScore = Africa.score;
		    		    	AlertDialog.Builder builder = new AlertDialog.Builder(SAmerica.this);
							  builder.setTitle("SCORE");
							  builder.setMessage("New High Score!!!\nYour score is:"+Africa.score);
							  builder.setCancelable(false);
						      builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
						        public void onClick(DialogInterface dialog, int id) {
						        	Intent act2 = new Intent(view.getContext(), Africa.class);
						 	        startActivity(act2);
						                dialog.cancel();
						                SAmerica.this.finish();
						            }
						        });
						      builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
							        public void onClick(DialogInterface dialog, int id) {
							                dialog.cancel();	
							                Africa.score = 0;
							    	        SAmerica.this.finish();
							            	}
					      	    		});
						      builder.create().show();
		    		    	}else{
		    		    		AlertDialog.Builder builder = new AlertDialog.Builder(SAmerica.this);
								  builder.setTitle("SCORE");
								  builder.setMessage("Your score is:"+Africa.score);
								  builder.setCancelable(false);
							      builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
							        public void onClick(DialogInterface dialog, int id) {
							        	Intent act2 = new Intent(view.getContext(), Africa.class);
							 	        startActivity(act2);
							                dialog.cancel();
							                SAmerica.this.finish();
							            }
							        });
							      builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
								        public void onClick(DialogInterface dialog, int id) {
								                dialog.cancel();	
								                Africa.score = 0;
								    	        SAmerica.this.finish();
								            	}
						      	    		});
							      builder.create().show();
		    		    	}
	    			flag.setImageResource(R.drawable.s);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option5[i]);
				}
	    		  test++;
		break;
		default:
			}
	    }
   }); 
 }	  
	
	@Override
    protected void onStop(){
      super.onStop();
      SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
      SharedPreferences.Editor editor = settings.edit();		// Editor object to make preference changes.
      editor.putInt("Score", HScore);						// Write "LastUse" key with value				
      editor.commit();
   }
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    // TODO Auto-generated method stub
	    super.onConfigurationChanged(newConfig);

	    int ot = getResources().getConfiguration().orientation;
	    switch (ot) {
	    case Configuration.ORIENTATION_LANDSCAPE:
	        setContentView(R.layout.samerica);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.samerica);
	        break;
	    }
	}
}
