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

public class Oceania extends Activity{
	int test=0, count, c, w, a, b, x;
	public static final String PREFS_NAME = "MyPrefsFile";
	Button ok;
	ImageView flag, title, wrong, right, oceania;
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
	        setContentView(R.layout.oceania);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.oceania);
	        break;
	    }

	    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0); 
		// Read a string value with key "LastUse"
	     HScore = settings.getInt("Score", 0);
	    //String lastUse = settings.getString("LastUse", "");
	    
        final ArrayList<Integer> rndm = new ArrayList<Integer>();
        for(int i=0; i<15; i++){
			rndm.add(i);
		}
		Collections.shuffle(rndm);
		
		x = rndm.get(0);
		a = rndm.indexOf(0); // Returns index of first occurrence of 0
		rndm.set(0, (rndm.get(a) ) ); //rndm.get(10) Returns the object at index 10.
		rndm.set(a, x );
		
		x = rndm.get(14);
		b = rndm.indexOf(14);
		rndm.set(14, (rndm.get(b) ) );
		rndm.set(b, x );
		
		ok = (Button)findViewById(R.id.button1);
		flag = (ImageView)findViewById(R.id.imageView2);
		title = (ImageView)findViewById(R.id.imageView1);
		wrong = (ImageView)findViewById(R.id.imageView4);
		right = (ImageView)findViewById(R.id.imageView3);
		oceania = (ImageView)findViewById(R.id.imageView5);
		group = (RadioGroup)findViewById(R.id.radioGroup1);
		up = (TextView)findViewById(R.id.textView1);
		down = (TextView)findViewById(R.id.textView2);
		highest = (TextView)findViewById(R.id.textView3);
	    value = (TextView)findViewById(R.id.textView4);
		
	    final String[] option1 = {"Fiji","Australia","Nauru","New Zealand"}; //Australia
		final String[] option2 = {"Tuvalu","Tonga","Kiribati","Fiji"};	//Fiji
	   	final String[] option3 = {"Solomon Island","Kiribati","Samoa","Marshall Islands"};	//Kiribati
	   	final String[] option4 = {"Marshall Islands","Palau","Tuvalu","Nauru"};	//Marshall Islands
	   	final String[] option5 = {"Australia","Micronesia","Vanuatu","Fiji"};	//Micronesia
		final String[] option6 = {"Kiribati","Palau","Nauru","Tuvalu"};	 //Nauru
		final String[] option7 = {"New Zealand","Samoa","Australia","Tonga"};	//New Zealand
	   	final String[] option8 = {"Vanuatu","Samoa","Fiji","Palau"};	//Palau
	   	final String[] option9 = {"Papua New Guinea","Micronesia","Tonga","Tuvalu"};	//Papua New Guinea
	   	final String[] option10 = {"Palau","Vanuatu","Samoa","Kiribati"};	//Samoa
		final String[] option11 = {"Solomon Islands","Fiji","Tonga","Nauru"};	//Solomon Islands
		final String[] option12 = {"Nauru","Samoa","Tonga","Vanuatu"};	//Tonga
	   	final String[] option13 = {"Palau","Tuvalu","Vanuatu","Australia"};	 //Tuvalu
	   	final String[] option14 = {"Vanuatu","Micronesia","Tonga","Kiribati"};	//Vanuatu
	    
	   	value.setText(Integer.toString(HScore));
	   	
	    ok.setOnClickListener(new View.OnClickListener(){
	   @Override
	  public void onClick(final View view) {
		   switch(rndm.get(test))
			{
		case 0:
			  oceania.setVisibility(View.INVISIBLE);
			  flag.setImageResource(R.drawable.australia);
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
	    		Answer = "Australia";
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
			
			  flag.setImageResource(R.drawable.fiji);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option2[i]);
		    		Answer = "Fiji"; 
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
			  flag.setImageResource(R.drawable.kiribati);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option3[i]);
	    			Answer = "Kiribati";
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
	    		
	    	  flag.setImageResource(R.drawable.marshall_islands);	
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option4[i]);
		    		  Answer = "Marshall Islands";
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
	    			flag.setImageResource(R.drawable.micronesia);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option5[i]);
		    		  Answer = "Micronesia";
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
				
				  flag.setImageResource(R.drawable.nauru);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option6[i]);
			    		Answer = "Nauru"; 
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
				  flag.setImageResource(R.drawable.new_zealand);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option7[i]);
		    			Answer = "New Zealand";
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
		    		
		    	  flag.setImageResource(R.drawable.palau);	
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option8[i]);
			    		  Answer = "Palau";
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
		    			flag.setImageResource(R.drawable.papua_new_guinea);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option9[i]);
			    		  Answer = "Papua New Guinea";
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
					
					  flag.setImageResource(R.drawable.samoa);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option10[i]);
				    		Answer = "Samoa"; 
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
					  flag.setImageResource(R.drawable.solomon_islands);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option11[i]);
			    			Answer = "Solomon Islands";
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
			    		
			    	  flag.setImageResource(R.drawable.tonga);	
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option12[i]);
				    		  Answer = "Tonga";
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
			    			flag.setImageResource(R.drawable.tuvalu);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option13[i]);
				    		  Answer = "Tuvalu";
						}
			    		  test++;
				break;
				case 13:

					  int selected13 = group.getCheckedRadioButtonId();
						option = (RadioButton) findViewById(selected13);
						Ans = (String)option.getText();
						
			   			if( (Answer).equals (Ans) ){
			   				c++;
			   				up.setText(Integer.toString(c));
			   			}else
			   			{
			   				w++;
			   				down.setText(Integer.toString(w));
								}
						
						  flag.setImageResource(R.drawable.vanuatu);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option14[i]);
					    		Answer = "Vanuatu"; 
							}
				    		test++;
					break;
				case 14:
					 int selected14 = group.getCheckedRadioButtonId();
		   			option = (RadioButton) findViewById(selected14);
		   			Ans = (String)option.getText();

			    			if( (Answer).equals (Ans) ){
			    				c++;
			    				up.setText(Integer.toString(c));
			    			}else
			    			{
			    				w++;
			    				down.setText(Integer.toString(w));
			    			}
			    		    if (c < 5){	
			    			AlertDialog.Builder builder = new AlertDialog.Builder(Oceania.this);
							  builder.setTitle("SCORE");
							  builder.setMessage("Your score is low, Please try again!");
							  builder.setCancelable(false);
						      builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						        public void onClick(DialogInterface dialog, int id) {
						        	Intent act2 = new Intent(view.getContext(), Oceania.class);
						 	        startActivity(act2);
						                dialog.cancel();
						                Oceania.this.finish();
						            }
						        });
						      builder.create().show();
			    		    }else{
			    		    	Africa.score = Africa.score + (c*3) - w ;
			    		    	if  (HScore < Africa.score){
				    		    	HScore = Africa.score;
				    		    	AlertDialog.Builder builder = new AlertDialog.Builder(Oceania.this);
									  builder.setTitle("SCORE");
									  builder.setMessage("New High Score!!!\nYour score is:"+Africa.score);
									  builder.setCancelable(false);
								      builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
								        public void onClick(DialogInterface dialog, int id) {
								        	Intent act2 = new Intent(view.getContext(), SAmerica.class);
								 	        startActivity(act2);
								                dialog.cancel();
								                Oceania.this.finish();
								            }
								        });
								      builder.create().show();
				    		    	}else{
				    		    		AlertDialog.Builder builder = new AlertDialog.Builder(Oceania.this);
										  builder.setTitle("SCORE");
										  builder.setMessage("Your score is:"+Africa.score);
										  builder.setCancelable(false);
									      builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
									        public void onClick(DialogInterface dialog, int id) {
									        	Intent act2 = new Intent(view.getContext(), SAmerica.class);
									 	        startActivity(act2);
									                dialog.cancel();
									                Oceania.this.finish();
									            }
									        });
									      builder.create().show();
				    		    	}
			    		    }
			    			flag.setImageResource(R.drawable.oceania);
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
	        setContentView(R.layout.oceania);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.oceania);
	        break;
	    }
	}
		}
