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

public class Europe extends Activity{
	int test=0, count, c, w, a, b, x;
	public static final String PREFS_NAME = "MyPrefsFile";
	Button ok;
	ImageView flag, title, wrong, right, europe;
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
	        setContentView(R.layout.europe);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.europe);
	        break;
	    }

	    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0); 
		// Read a string value with key "LastUse"
	     HScore = settings.getInt("Score", 0);
	    //String lastUse = settings.getString("LastUse", "");
	    
        final ArrayList<Integer> rndm = new ArrayList<Integer>();
        for(int i=0; i<43; i++){
			rndm.add(i);
		}
		Collections.shuffle(rndm);
		
		x = rndm.get(0);
		a = rndm.indexOf(0); // Returns index of first occurrence of 0
		rndm.set(0, (rndm.get(a) ) ); //rndm.get(10) Returns the object at index 10.
		rndm.set(a, x );
		
		x = rndm.get(42);
		b = rndm.indexOf(42);
		rndm.set(42, (rndm.get(b) ) );
		rndm.set(b, x );
		
		ok = (Button)findViewById(R.id.button1);
		flag = (ImageView)findViewById(R.id.imageView2);
		title = (ImageView)findViewById(R.id.imageView1);
		wrong = (ImageView)findViewById(R.id.imageView4);
		right = (ImageView)findViewById(R.id.imageView3);
		europe = (ImageView)findViewById(R.id.imageView5);
		group = (RadioGroup)findViewById(R.id.radioGroup1);
		up = (TextView)findViewById(R.id.textView1);
		down = (TextView)findViewById(R.id.textView2);
		highest = (TextView)findViewById(R.id.textView3);
	    value = (TextView)findViewById(R.id.textView4);
		
	    final String[] option1 = {"Monaco","Albania","Denmark","Austria"};    //Albania
		final String[] option2 = {"Austria","Belarus","France","Malta"};	//Austria
	   	final String[] option3 = {"Cyprus","Moldova","Belarus","Latvia"};	  //Belarus
	   	final String[] option4 = {"Germany","Russia","Norway","Belgium"};	//Belgium
	   	final String[] option5 = {"Malta","Bosnia & Herzegovina","Serbia","Poland"};	//Bosnia & Herzegovina
		final String[] option6 = {"Netherlands","Moldova","Bulgaria","Greece"};	//Bulgaria
		final String[] option7 = {"Croatia","Czech Rep.","Hungary","Latvia"};	//Croatia
	   	final String[] option8 = {"Slovenia","Iceland","Cyprus","Estonia"};  	//Cyprus
	   	final String[] option9 = {"Russia","Czech Rep.","Italy","Poland"};	//Czech Rep.
	   	final String[] option10 = {"Slovenia","Liechtenstein","Finland","Denmark"}; 	//Denmark
		final String[] option11 = {"Estonia","Iceland","Romania","Hungary"};	  //Estonia
		final String[] option12 = {"Denmark","San Mario","Finland","Netherlands"};	 //Finland
	   	final String[] option13 = {"Sweden","Norway","France","Luxembourg"};	 //France
	   	final String[] option14 = {"Malta","Germany","Macedonia","Switzerland"};	 //Germany
	   	final String[] option15 = {"Slovakia","Slovenia","Serbia","Greece"};	 //Greece
		final String[] option16 = {"Portugal","Ukraine","Hungary","Spain"};	  //Hungary
		final String[] option17 = {"Iceland","Finland","Denmark","Spain"};		//Iceland		
	   	final String[] option18 = {"Ukraine","Ireland","Sweden","Spain"};		//Ireland
	   	final String[] option19 = {"Russia","Luxembourg","Estonia","Italy"};	//Italy
	   	final String[] option20 = {"Malta","Serbia","Latvia","Macedonia"};	 //Latvia
		final String[] option21 = {"Liechtenstein","Slovenia","Portugal","U. Kingdom"};	  //Liechtenstein
	   	final String[] option22 = {"Monaco","Lithuania","Norway","Moldova"};	//Lithuania
	   	final String[] option23 = {"Belarus","Denmark","Belgium","Luxembourg"};	   //Luxembourg 
	   	final String[] option24 = {"Cyprus","Spain","Macedonia","Latvia"};	//Macedonia
		final String[] option25 = {"Malta","Lithuania","Iceland","Estonia"};	//Malta		
	   	final String[] option26 = {"Switzerland","Moldova","Spain","Hungary"};	 //Moldova
	   	final String[] option27 = {"Romania","Poland","Liechtenstein","Monaco"};	//Monaco
	   	final String[] option28 = {"Serbia","Latvia","Netherlands","Ukraine"};	//Netherlands
		final String[] option29 = {"Norway","Macedonia","San Mario","Greece"};		//Norway
		final String[] option30 = {"Slovakia","Poland","Albania","Croatia"};	//Poland
	   	final String[] option31 = {"Cyprus","Austria","Liechtenstein","Portugal"};		//Portugal
	   	final String[] option32 = {"Ireland","Greece","Romania","Sweden"};	 //Romania
	   	final String[] option33 = {"Russia","Ukraine","U. Kingdom","Bulgaria"};		//Russia
		final String[] option34 = {"Denmark","San Marino","Moldova","Cyprus"};   //San Marino
		final String[] option35 = {"Switzerland","Monaco","Ukraine","Serbia"};	 //Serbia
	   	final String[] option36 = {"Germany","France","Slovakia","Croatia"};	//Slovakia
	   	final String[] option37 = {"Malta","Ireland","Slovenia","Latvia"};		//Slovenia
	   	final String[] option38 = {"Portugal","Spain","Norway","Poland"};		//Spain
		final String[] option39 = {"Sweden","Czech Rep.","Germany","Lithuania"};		//Sweden
		final String[] option40 = {"Malta","Switzerland","Greece","Belarus"};		//Switzerland
	   	final String[] option41 = {"Serbia","Cyprus","San Mario","Ukraine"};		//Ukraine
	   	final String[] option42 = {"United Kingdom","Denmark","Finland","Ireland"};		//United Kingdom
	    
	   	value.setText(Integer.toString(HScore));
	   	
	  ok.setOnClickListener(new View.OnClickListener(){
	  @Override
	  public void onClick(final View view) {
		  switch(rndm.get(test))
			{
		case 0:
			  europe.setVisibility(View.INVISIBLE);
			  flag.setImageResource(R.drawable.albania);
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
	    		Answer = "Albania";
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
			
			  flag.setImageResource(R.drawable.austria);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option2[i]);
		    		Answer = "Austria"; 
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
			  flag.setImageResource(R.drawable.belarus);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option3[i]);
	    			Answer = "Belarus";
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
	    		
	    	  flag.setImageResource(R.drawable.belgium);	
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option4[i]);
		    		  Answer = "Belgium";
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
	    			flag.setImageResource(R.drawable.bosnia_and_herzegovina);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option5[i]);
		    		  Answer = "Bosnia & Herzegovina";
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
				
				  flag.setImageResource(R.drawable.bulgaria);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option6[i]);
			    		Answer = "Bulgaria"; 
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
				  flag.setImageResource(R.drawable.croatia);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option7[i]);
		    			Answer = "Croatia";
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
		    		
		    	  flag.setImageResource(R.drawable.cyprus);	
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option8[i]);
			    		  Answer = "Cyprus";
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
		    			flag.setImageResource(R.drawable.czech_republic);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option9[i]);
			    		  Answer = "Czech Rep.";
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
					
					  flag.setImageResource(R.drawable.denmark);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option10[i]);
				    		Answer = "Denmark"; 
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
					  flag.setImageResource(R.drawable.estonia);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option11[i]);
			    			Answer = "Estonia";
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
			    		
			    	  flag.setImageResource(R.drawable.finland);	
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option12[i]);
				    		  Answer = "Finland";
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
			    			flag.setImageResource(R.drawable.france);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option13[i]);
				    		  Answer = "France";
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
						
						  flag.setImageResource(R.drawable.germany);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option14[i]);
					    		Answer = "Germany"; 
							}
				    		test++;
					break;
					
					case 14:  
						
						int selected14 = group.getCheckedRadioButtonId();
						option = (RadioButton) findViewById(selected14);
						Ans  = (String)option.getText();

				    			if( (Answer).equals (Ans) ){
				    				c++;
				    				up.setText(Integer.toString(c));
				    			}else
				    			{
				    				w++;
				    				down.setText(Integer.toString(w));
			 					}
						  flag.setImageResource(R.drawable.greece);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option15[i]);
				    			Answer = "Greece";
							}
				    		test++;
					break;
					case 15:  
						  int selected15 = group.getCheckedRadioButtonId();
			   			option = (RadioButton) findViewById(selected15);
			   			Ans = (String)option.getText();

				    			if( (Answer).equals (Ans) ){
				    				c++;
				    				up.setText(Integer.toString(c));
				    			}else
				    			{
				    				w++;
				    				down.setText(Integer.toString(w));
			 					}
				    		
				    	  flag.setImageResource(R.drawable.hungary);	
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option16[i]);
					    		  Answer = "Hungary";
							} 
			   			test++;
				break;
					
				case 16:
						 int selected16 = group.getCheckedRadioButtonId();
			   			option = (RadioButton) findViewById(selected16);
			   			Ans = (String)option.getText();

				    			if( (Answer).equals (Ans) ){
				    				c++;
				    				up.setText(Integer.toString(c));
				    			}else
				    			{
				    				w++;
				    				down.setText(Integer.toString(w));
				    			}
				    			flag.setImageResource(R.drawable.iceland);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option17[i]);
					    		  Answer = "Iceland";
							}
				    		  test++;
			break;
			
				case 17:
					 int selected17 = group.getCheckedRadioButtonId();
		   			option = (RadioButton) findViewById(selected17);
		   			Ans = (String)option.getText();

			    			if( (Answer).equals (Ans) ){
			    				c++;
			    				up.setText(Integer.toString(c));
			    			}else
			    			{
			    				w++;
			    				down.setText(Integer.toString(w));
			    			}
			    			flag.setImageResource(R.drawable.ireland);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option18[i]);
				    		  Answer = "Ireland";
						}
			    		  test++;
				break;
				case 18:

					  int selected18 = group.getCheckedRadioButtonId();
						option = (RadioButton) findViewById(selected18);
						Ans = (String)option.getText();
						
			   			if( (Answer).equals (Ans) ){
			   				c++;
			   				up.setText(Integer.toString(c));
			   			}else
			   			{
			   				w++;
			   				down.setText(Integer.toString(w));
								}
						
						  flag.setImageResource(R.drawable.italy);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option19[i]);
					    		Answer = "Italy"; 
							}
				    		test++;
					break;
					
					case 19:  
						
						int selected19 = group.getCheckedRadioButtonId();
						option = (RadioButton) findViewById(selected19);
						Ans  = (String)option.getText();

				    			if( (Answer).equals (Ans) ){
				    				c++;
				    				up.setText(Integer.toString(c));
				    			}else
				    			{
				    				w++;
				    				down.setText(Integer.toString(w));
			 					}
						  flag.setImageResource(R.drawable.latvia);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option20[i]);
				    			Answer = "Latvia";
							}
				    		test++;
					break;
					case 20:  
						  int selected20 = group.getCheckedRadioButtonId();
			   			option = (RadioButton) findViewById(selected20);
			   			Ans = (String)option.getText();

				    			if( (Answer).equals (Ans) ){
				    				c++;
				    				up.setText(Integer.toString(c));
				    			}else
				    			{
				    				w++;
				    				down.setText(Integer.toString(w));
			 					}
				    		
				    	  flag.setImageResource(R.drawable.liechtenstein);	
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option21[i]);
					    		  Answer = "Liechtenstein";
							} 
			   			test++;
					break;
			
					case 21:

						  int selected21 = group.getCheckedRadioButtonId();
							option = (RadioButton) findViewById(selected21);
							Ans = (String)option.getText();
							
				   			if( (Answer).equals (Ans) ){
				   				c++;
				   				up.setText(Integer.toString(c));
				   			}else
				   			{
				   				w++;
				   				down.setText(Integer.toString(w));
									}
							
							  flag.setImageResource(R.drawable.lithuania);
							  for (int i = 0; i < group.getChildCount(); i++) {
									((RadioButton) group.getChildAt(i)).setText(option22[i]);
						    		Answer = "Lithuania"; 
								}
					    		test++;
						break;
						
						case 22:  
							
							int selected22 = group.getCheckedRadioButtonId();
							option = (RadioButton) findViewById(selected22);
							Ans  = (String)option.getText();

					    			if( (Answer).equals (Ans) ){
					    				c++;
					    				up.setText(Integer.toString(c));
					    			}else
					    			{
					    				w++;
					    				down.setText(Integer.toString(w));
				 					}
							  flag.setImageResource(R.drawable.luxembourg);
							  for (int i = 0; i < group.getChildCount(); i++) {
									((RadioButton) group.getChildAt(i)).setText(option23[i]);
					    			Answer = "Luxembourg";
								}
					    		test++;
						break;
						case 23:  
							  int selected23 = group.getCheckedRadioButtonId();
				   			option = (RadioButton) findViewById(selected23);
				   			Ans = (String)option.getText();

					    			if( (Answer).equals (Ans) ){
					    				c++;
					    				up.setText(Integer.toString(c));
					    			}else
					    			{
					    				w++;
					    				down.setText(Integer.toString(w));
				 					}
					    		
					    	  flag.setImageResource(R.drawable.macedonia);	
							  for (int i = 0; i < group.getChildCount(); i++) {
									((RadioButton) group.getChildAt(i)).setText(option24[i]);
						    		  Answer = "Macedonia";
								} 
				   			test++;
						break;
						
						case 24:
							 int selected24 = group.getCheckedRadioButtonId();
				   			option = (RadioButton) findViewById(selected24);
				   			Ans = (String)option.getText();

					    			if( (Answer).equals (Ans) ){
					    				c++;
					    				up.setText(Integer.toString(c));
					    			}else
					    			{
					    				w++;
					    				down.setText(Integer.toString(w));
					    			}
					    			flag.setImageResource(R.drawable.malta);
							  for (int i = 0; i < group.getChildCount(); i++) {
									((RadioButton) group.getChildAt(i)).setText(option25[i]);
						    		  Answer = "Malta";
								}
					    		  test++;
						break;
						case 25:

							  int selected25 = group.getCheckedRadioButtonId();
								option = (RadioButton) findViewById(selected25);
								Ans = (String)option.getText();
								
					   			if( (Answer).equals (Ans) ){
					   				c++;
					   				up.setText(Integer.toString(c));
					   			}else
					   			{
					   				w++;
					   				down.setText(Integer.toString(w));
										}
								
								  flag.setImageResource(R.drawable.moldova);
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option26[i]);
							    		Answer = "Moldova"; 
									}
						    		test++;
							break;
							
							case 26:  
								
								int selected26 = group.getCheckedRadioButtonId();
								option = (RadioButton) findViewById(selected26);
								Ans  = (String)option.getText();

						    			if( (Answer).equals (Ans) ){
						    				c++;
						    				up.setText(Integer.toString(c));
						    			}else
						    			{
						    				w++;
						    				down.setText(Integer.toString(w));
					 					}
								  flag.setImageResource(R.drawable.monaco);
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option27[i]);
						    			Answer = "Monaco";
									}
						    		test++;
							break;
							case 27:  
								  int selected27 = group.getCheckedRadioButtonId();
					   			option = (RadioButton) findViewById(selected27);
					   			Ans = (String)option.getText();

						    			if( (Answer).equals (Ans) ){
						    				c++;
						    				up.setText(Integer.toString(c));
						    			}else
						    			{
						    				w++;
						    				down.setText(Integer.toString(w));
					 					}
						    		
						    	  flag.setImageResource(R.drawable.netherlands);	
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option28[i]);
							    		  Answer = "Netherlands";
									} 
					   			test++;
							break;
							
							case 28:
								 int selected28 = group.getCheckedRadioButtonId();
					   			option = (RadioButton) findViewById(selected28);
					   			Ans = (String)option.getText();

						    			if( (Answer).equals (Ans) ){
						    				c++;
						    				up.setText(Integer.toString(c));
						    			}else
						    			{
						    				w++;
						    				down.setText(Integer.toString(w));
						    			}
						    			flag.setImageResource(R.drawable.norway);
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option29[i]);
							    		  Answer = "Norway";
									}
						    		  test++;
							break;
							case 29:

								  int selected29 = group.getCheckedRadioButtonId();
									option = (RadioButton) findViewById(selected29);
									Ans = (String)option.getText();
									
						   			if( (Answer).equals (Ans) ){
						   				c++;
						   				up.setText(Integer.toString(c));
						   			}else
						   			{
						   				w++;
						   				down.setText(Integer.toString(w));
											}
									
									  flag.setImageResource(R.drawable.poland);
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option30[i]);
								    		Answer = "Poland"; 
										}
							    		test++;
								break;
								
								case 30:  
									
									int selected30 = group.getCheckedRadioButtonId();
									option = (RadioButton) findViewById(selected30);
									Ans  = (String)option.getText();

							    			if( (Answer).equals (Ans) ){
							    				c++;
							    				up.setText(Integer.toString(c));
							    			}else
							    			{
							    				w++;
							    				down.setText(Integer.toString(w));
						 					}
									  flag.setImageResource(R.drawable.portugal);
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option31[i]);
							    			Answer = "Portugal";
										}
							    		test++;
								break;
								case 31:  
									  int selected31 = group.getCheckedRadioButtonId();
						   			option = (RadioButton) findViewById(selected31);
						   			Ans = (String)option.getText();

							    			if( (Answer).equals (Ans) ){
							    				c++;
							    				up.setText(Integer.toString(c));
							    			}else
							    			{
							    				w++;
							    				down.setText(Integer.toString(w));
						 					}
							    		
							    	  flag.setImageResource(R.drawable.romania);	
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option32[i]);
								    		  Answer = "Romania";
										} 
						   			test++;
								break;
								
								case 32:
									 int selected32 = group.getCheckedRadioButtonId();
						   			option = (RadioButton) findViewById(selected32);
						   			Ans = (String)option.getText();

							    			if( (Answer).equals (Ans) ){
							    				c++;
							    				up.setText(Integer.toString(c));
							    			}else
							    			{
							    				w++;
							    				down.setText(Integer.toString(w));
							    			}
							    			flag.setImageResource(R.drawable.russia);
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option33[i]);
								    		  Answer = "Russia";
										}
							    		  test++;
								break;
								case 33:

									  int selected33 = group.getCheckedRadioButtonId();
										option = (RadioButton) findViewById(selected33);
										Ans = (String)option.getText();
										
							   			if( (Answer).equals (Ans) ){
							   				c++;
							   				up.setText(Integer.toString(c));
							   			}else
							   			{
							   				w++;
							   				down.setText(Integer.toString(w));
												}
										
										  flag.setImageResource(R.drawable.san_marino);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option34[i]);
									    		Answer = "San Marino"; 
											}
								    		test++;
									break;
									
									case 34:  
										
										int selected34 = group.getCheckedRadioButtonId();
										option = (RadioButton) findViewById(selected34);
										Ans  = (String)option.getText();

								    			if( (Answer).equals (Ans) ){
								    				c++;
								    				up.setText(Integer.toString(c));
								    			}else
								    			{
								    				w++;
								    				down.setText(Integer.toString(w));
							 					}
										  flag.setImageResource(R.drawable.serbia);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option35[i]);
								    			Answer = "Serbia";
											}
								    		test++;
									break;
									case 35:  
										  int selected35 = group.getCheckedRadioButtonId();
							   			option = (RadioButton) findViewById(selected35);
							   			Ans = (String)option.getText();

								    			if( (Answer).equals (Ans) ){
								    				c++;
								    				up.setText(Integer.toString(c));
								    			}else
								    			{
								    				w++;
								    				down.setText(Integer.toString(w));
							 					}
								    		
								    	  flag.setImageResource(R.drawable.slovakia);	
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option36[i]);
									    		  Answer = "Slovakia";
											} 
							   			test++;
								break;
									
								case 36:
										 int selected36 = group.getCheckedRadioButtonId();
							   			option = (RadioButton) findViewById(selected36);
							   			Ans = (String)option.getText();

								    			if( (Answer).equals (Ans) ){
								    				c++;
								    				up.setText(Integer.toString(c));
								    			}else
								    			{
								    				w++;
								    				down.setText(Integer.toString(w));
								    			}
								    			flag.setImageResource(R.drawable.slovenia);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option37[i]);
									    		  Answer = "Slovenia";
											}
								    		  test++;
							break;
							
								case 37:
									 int selected37 = group.getCheckedRadioButtonId();
						   			option = (RadioButton) findViewById(selected37);
						   			Ans = (String)option.getText();

							    			if( (Answer).equals (Ans) ){
							    				c++;
							    				up.setText(Integer.toString(c));
							    			}else
							    			{
							    				w++;
							    				down.setText(Integer.toString(w));
							    			}
							    			flag.setImageResource(R.drawable.spain);
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option38[i]);
								    		  Answer = "Spain";
										}
							    		  test++;
								break;
								case 38:

									  int selected38 = group.getCheckedRadioButtonId();
										option = (RadioButton) findViewById(selected38);
										Ans = (String)option.getText();
										
							   			if( (Answer).equals (Ans) ){
							   				c++;
							   				up.setText(Integer.toString(c));
							   			}else
							   			{
							   				w++;
							   				down.setText(Integer.toString(w));
												}
										
										  flag.setImageResource(R.drawable.sweden);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option39[i]);
									    		Answer = "Sweden"; 
											}
								    		test++;
									break;
									
									case 39:  
										
										int selected39 = group.getCheckedRadioButtonId();
										option = (RadioButton) findViewById(selected39);
										Ans  = (String)option.getText();

								    			if( (Answer).equals (Ans) ){
								    				c++;
								    				up.setText(Integer.toString(c));
								    			}else
								    			{
								    				w++;
								    				down.setText(Integer.toString(w));
							 					}
										  flag.setImageResource(R.drawable.switzerland);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option40[i]);
								    			Answer = "Switzerland";
											}
								    		test++;
									break;
						case 40:  
						    int selected40 = group.getCheckedRadioButtonId();
							option = (RadioButton) findViewById(selected40);
							  Ans = (String)option.getText();

								   if( (Answer).equals (Ans) ){
								    	c++;
								    	up.setText(Integer.toString(c));
								    }else
								    {
								    	w++;
								    	down.setText(Integer.toString(w));
							 		}
								    		
								    	flag.setImageResource(R.drawable.ukraine);	
										for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option41[i]);
									    Answer = "Ukraine";
									} 
							   			test++;
						break;
						
						case 41:

							  int selected41 = group.getCheckedRadioButtonId();
								option = (RadioButton) findViewById(selected41);
								Ans = (String)option.getText();
								
					   			if( (Answer).equals (Ans) ){
					   				c++;
					   				up.setText(Integer.toString(c));
					   			}else
					   			{
					   				w++;
					   				down.setText(Integer.toString(w));
										}
								
								  flag.setImageResource(R.drawable.united_kingdom);
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option42[i]);
							    		Answer = "United Kingdom"; 
									}
						    		test++;
							break;
						case 42:
							 int selected42 = group.getCheckedRadioButtonId();
				   			option = (RadioButton) findViewById(selected42);
				   			Ans = (String)option.getText();

					    			if( (Answer).equals (Ans) ){
					    				c++;
					    				up.setText(Integer.toString(c));
					    			}else
					    			{
					    				w++;
					    				down.setText(Integer.toString(w));
					    			}
					    		    if (c < 10){	
					    			AlertDialog.Builder builder = new AlertDialog.Builder(Europe.this);
									  builder.setTitle("SCORE");
									  builder.setMessage("Your score is low, Please try again!");
									  builder.setCancelable(false);
								      builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								        public void onClick(DialogInterface dialog, int id) {
								        	Intent act2 = new Intent(view.getContext(), Europe.class);
								 	        startActivity(act2);
								                dialog.cancel();
								                Europe.this.finish();
								            }
								        });
								      builder.create().show();
					    		    }else{
					    		    	Africa.score = Africa.score + (c*3) - w ;
					    		    	if  (HScore < Africa.score){
						    		    	HScore = Africa.score;
						    		    	AlertDialog.Builder builder = new AlertDialog.Builder(Europe.this);
											  builder.setTitle("SCORE");
											  builder.setMessage("New High Score!!!\nYour score is:"+Africa.score);
											  builder.setCancelable(false);
										      builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
										        public void onClick(DialogInterface dialog, int id) {
										        	Intent act2 = new Intent(view.getContext(), NAmerica.class);
										 	        startActivity(act2);
										                dialog.cancel();
										                Europe.this.finish();
										            }
										        });
										      builder.create().show();
						    		    	}else{
						    		    		AlertDialog.Builder builder = new AlertDialog.Builder(Europe.this);
												  builder.setTitle("SCORE");
												  builder.setMessage("Your score is:"+Africa.score);
												  builder.setCancelable(false);
											      builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
											        public void onClick(DialogInterface dialog, int id) {
											        	Intent act2 = new Intent(view.getContext(), NAmerica.class);
											 	        startActivity(act2);
											                dialog.cancel();
											                Europe.this.finish();
											            }
											        });
											      builder.create().show();
						    		    	}
					    		    }
					    			flag.setImageResource(R.drawable.europe);
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
	        setContentView(R.layout.europe);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.europe);
	        break;
	    }
	}
				}
