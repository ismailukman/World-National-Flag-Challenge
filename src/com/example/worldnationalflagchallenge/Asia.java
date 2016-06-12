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

public class Asia extends Activity{
	int test=0, count, c, w, a, b, x;
	Button ok;
	public static final String PREFS_NAME = "MyPrefsFile";
	ImageView flag, title, wrong, right, asia;
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
	        setContentView(R.layout.asia);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.asia);
	        break;
	    }
	    
	    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0); 
		// Read a string value with key "LastUse"
	     HScore = settings.getInt("Score", 0);
	    //String lastUse = settings.getString("LastUse", "");

        final ArrayList<Integer> rndm = new ArrayList<Integer>();
        for(int i=0; i<45; i++){
			rndm.add(i);
		}
		Collections.shuffle(rndm);
		
		x = rndm.get(0);
		a = rndm.indexOf(0); // Returns index of first occurrence of 0
		rndm.set(0, (rndm.get(a) ) ); //rndm.get(10) Returns the object at index 10.
		rndm.set(a, x );
		
		x = rndm.get(44);
		b = rndm.indexOf(44);
		rndm.set(44, (rndm.get(b) ) );
		rndm.set(b, x );
		
		ok = (Button)findViewById(R.id.button1);
		flag = (ImageView)findViewById(R.id.imageView2);
		title = (ImageView)findViewById(R.id.imageView1);
		wrong = (ImageView)findViewById(R.id.imageView4);
		right = (ImageView)findViewById(R.id.imageView3);
		asia = (ImageView)findViewById(R.id.imageView5);
		group = (RadioGroup)findViewById(R.id.radioGroup1);
		up = (TextView)findViewById(R.id.textView1);
		down = (TextView)findViewById(R.id.textView2);
		highest = (TextView)findViewById(R.id.textView3);
	    value = (TextView)findViewById(R.id.textView4);
		
	    final String[] option1 = {"Afghanistan","Algeria","Armenia","Azerbaijan"};   //Afghanistan
		final String[] option2 = {"East Timor","Armenia","Bahrain","India"};	//Armenia
	   	final String[] option3 = {"Brunei","Iran","Indonesia","Azerbaijan"};	//Azerbaijan
	   	final String[] option4 = {"Nepal","Bhutan","Bahrain","Laos"};	//Bahrain
	   	final String[] option5 = {"Bangladesh","Kazakhstan","Singapore","Sri Lanka"};	//Bangladesh
		final String[] option6 = {"Thailand","Maldives","Bhutan","Brunei"};		//Bhutan
		final String[] option7 = {"Nepal","Mongolia","Brunei","Kuwait"};	//Brunei
	   	final String[] option8 = {"Iran","Jordan","China","Iraq"};	//China
	   	final String[] option9 = {"U.A.E","Brunei","East Timor","Oman"};	//East Timor
	   	final String[] option10 = {"Laos","India","Lebanon","Syria"};	//India
		final String[] option11 = {"Kuwait","Sri Lanka","Indonesia","Lebanon"};	  //Indonesia
		final String[] option12 = {"India","Singapore","Pakistan","Iran"};	//Iran
	   	final String[] option13 = {"Iraq","Qatar","Saudi Arabia","Yemen"};	//Iraq
	   	final String[] option14 = {"Mongolia","Israel","Philippines","Uzbekistan"};	 //Israel
	   	final String[] option15 = {"Palestine","Syria","Japan","Korea"};	  //Japan
		final String[] option16 = {"Tajikistan","Turkmenistan","Vietnam","Jordan"};	  //Jordan
		final String[] option17 = {"Kazakhstan","Tajikistan","Uzbekistan","Turkey"};		//Kazakhstan
	   	final String[] option18 = {"Qatar","Nepal","Kuwait","Yemen"};	//Kuwait
	   	final String[] option19 = {"Singapore","Oman","Lebanon","Kyrgyzstan"};	//Kyrgyzstan
	   	final String[] option20 = {"Lebanon","Laos","Tajikistan","Syria"};	 //Laos
		final String[] option21 = {"Armenia","Mongolia","Lebanon","N. Korea"};	//Lebanon
	   	final String[] option22 = {"Malaysia","Azerbaijan","Jordan","Pakistan"};	//Malaysia
	   	final String[] option23 = {"Thailand","U.A.E","Maldives","Sri Lanka"};	//Maldives
	   	final String[] option24 = {"Kyrgyzstan","Mongolia","Singapore","Laos"};	//Mongolia
		final String[] option25 = {"Oman","Jordan","Japan","Nepal"};	  //Nepal		
	   	final String[] option26 = {"N. Korea","S. Korea","Turkey","Turkmenistan"};	 //N. Korea
	   	final String[] option27 = {"Yemen","Thailand","Oman","Qatar"};	//Oman
	   	final String[] option28 = {"Uzbekistan","Pakistan","Saudi Arabia","Yemen"};		//Pakistan
		final String[] option29 = {"Mongolia","Thailand","Kuwait","Palestine"};		//Palestine
		final String[] option30 = {"Palestine","Maldives","East Timor","Philippines"};		//Philippines
	   	final String[] option31 = {"Yemen","Qatar","Indonesia","Bahrain"};	 //Qatar
	   	final String[] option32 = {"Saudi Arabia","Iraq","Pakistan","U.A.E"};	//Saudi Arabia
	   	final String[] option33 = {"East Timor","Maldives","Singapore","Thailand"};		//Singapore
		final String[] option34 = {"S. Korea","N. Korea","China","Japan"};   //S. Korea
		final String[] option35 = {"Vietnam","Jordan","Sri Lanka","Bhutan"};	//Sri Lanka
	   	final String[] option36 = {"Iran","Yemen","Qatar","Syria"};		//Syria
	   	final String[] option37 = {"Laos","Tajikistan","Lebanon","Israel"};		//Tajikistan
	   	final String[] option38 = {"Kuwait","Armenia","Thailand","Afghanistan"};		//Thailand
		final String[] option39 = {"Turkey","N. Korea","Mongolia","Oman"};		//Turkey
		final String[] option40 = {"Syria","Yemen","Turkmenistan","U.A.E"};		//Turkmenistan
	   	final String[] option41 = {"Philippines","U.A.E","Kuwait","Nepal"};		//U.A.E
	   	final String[] option42 = {"Singapore","Syria","Lebanon","Uzbekistan"};	//Uzbekistan
	   	final String[] option43 = {"Sri Lanka","Turkey","Vietnam","Yemen"};	  //Vietnam
		final String[] option44 = {"Yemen","Bahrain","Malaysia","Thailand"};	 //Yemen
	    
		value.setText(Integer.toString(HScore));
		
	    ok.setOnClickListener(new View.OnClickListener(){

	   @Override
	  public void onClick(final View view) {
		   switch(rndm.get(test))
			{
		case 0:
			  asia.setVisibility(View.INVISIBLE);
			  flag.setImageResource(R.drawable.afghanistan);
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
	    		Answer = "Afghanistan";
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
			
			  flag.setImageResource(R.drawable.armenia);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option2[i]);
		    		Answer = "Armenia"; 
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
			  flag.setImageResource(R.drawable.azerbaijan);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option3[i]);
	    			Answer = "Azerbaijan";
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
	    		
	    	  flag.setImageResource(R.drawable.bahrain);	
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option4[i]);
		    		  Answer = "Bahrain";
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
	    			flag.setImageResource(R.drawable.bangladesh);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option5[i]);
		    		  Answer = "Bangladesh";
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
				
				  flag.setImageResource(R.drawable.bhutan);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option6[i]);
			    		Answer = "Bhutan"; 
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
				  flag.setImageResource(R.drawable.brunei);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option7[i]);
		    			Answer = "Brunei";
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
		    		
		    	  flag.setImageResource(R.drawable.china);	
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option8[i]);
			    		  Answer = "China";
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
		    			flag.setImageResource(R.drawable.east_timor);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option9[i]);
			    		  Answer = "East Timor";
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
					
					  flag.setImageResource(R.drawable.india);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option10[i]);
				    		Answer = "India"; 
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
					  flag.setImageResource(R.drawable.indonesia);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option11[i]);
			    			Answer = "Indonesia";
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
			    		
			    	  flag.setImageResource(R.drawable.iran);	
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option12[i]);
							Answer = "Iran";
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
			    			flag.setImageResource(R.drawable.iraq);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option13[i]);
				    		  Answer = "Iraq";
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
						
						  flag.setImageResource(R.drawable.israel);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option14[i]);
					    		Answer = "Israel"; 
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
						  flag.setImageResource(R.drawable.japan);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option15[i]);
				    			Answer = "Japan";
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
				    		
				    	  flag.setImageResource(R.drawable.jordan);	
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option16[i]);
					    		  Answer = "Jordan";
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
				    			flag.setImageResource(R.drawable.kazakhstan);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option17[i]);
					    		  Answer = "Kazakhstan";
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
			    			flag.setImageResource(R.drawable.kuwait);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option18[i]);
				    		  Answer = "Kuwait";
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
						
						  flag.setImageResource(R.drawable.kyrgyzstan);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option19[i]);
					    		Answer = "Kyrgyzstan"; 
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
						  flag.setImageResource(R.drawable.laos);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option20[i]);
				    			Answer = "Laos";
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
				    		
				    	  flag.setImageResource(R.drawable.lebanon);	
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option21[i]);
					    		  Answer = "Lebanon";
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
							
							  flag.setImageResource(R.drawable.malaysia);
							  for (int i = 0; i < group.getChildCount(); i++) {
									((RadioButton) group.getChildAt(i)).setText(option22[i]);
						    		Answer = "Malaysia"; 
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
							  flag.setImageResource(R.drawable.maldives);
							  for (int i = 0; i < group.getChildCount(); i++) {
									((RadioButton) group.getChildAt(i)).setText(option23[i]);
					    			Answer = "Maldives";
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
					    		
					    	  flag.setImageResource(R.drawable.mongolia);	
							  for (int i = 0; i < group.getChildCount(); i++) {
									((RadioButton) group.getChildAt(i)).setText(option24[i]);
						    		  Answer = "Mongolia";
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
					    			flag.setImageResource(R.drawable.nepal);
							  for (int i = 0; i < group.getChildCount(); i++) {
									((RadioButton) group.getChildAt(i)).setText(option25[i]);
						    		  Answer = "Nepal";
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
								
								  flag.setImageResource(R.drawable.north_korea);
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option26[i]);
							    		Answer = "N. Korea"; 
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
								  flag.setImageResource(R.drawable.oman);
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option27[i]);
						    			Answer = "Oman";
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
						    		
						    	  flag.setImageResource(R.drawable.pakistan);	
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option28[i]);
							    		  Answer = "Pakistan";
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
						    			flag.setImageResource(R.drawable.palestine);
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option29[i]);
							    		  Answer = "Palestine";
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
									
									  flag.setImageResource(R.drawable.philippines);
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option30[i]);
								    		Answer = "Philippines"; 
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
									  flag.setImageResource(R.drawable.qatar);
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option31[i]);
							    			Answer = "Qatar";
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
							    		
							    	  flag.setImageResource(R.drawable.saudi_arabia);	
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option32[i]);
								    		  Answer = "Saudi Arabia";
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
							    			flag.setImageResource(R.drawable.singapore);
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option33[i]);
								    		  Answer = "Singapore";
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
										
										  flag.setImageResource(R.drawable.south_korea);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option34[i]);
									    		Answer = "S. Korea"; 
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
										  flag.setImageResource(R.drawable.sri_lanka);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option35[i]);
								    			Answer = "Sri Lanka";
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
								    		
								    	  flag.setImageResource(R.drawable.syria);	
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option36[i]);
									    		  Answer = "Syria";
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
								    			flag.setImageResource(R.drawable.tajikistan);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option37[i]);
									    		  Answer = "Tajikistan";
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
							    			flag.setImageResource(R.drawable.thailand);
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option38[i]);
								    		  Answer = "Thailand";
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
										
										  flag.setImageResource(R.drawable.turkey);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option39[i]);
									    		Answer = "Turkey"; 
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
										  flag.setImageResource(R.drawable.turkmenistan);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option40[i]);
								    			Answer = "Turkmenistan";
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
								    		
								    	flag.setImageResource(R.drawable.uae);	
										for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option41[i]);
									    Answer = "U.A.E";
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
								
								  flag.setImageResource(R.drawable.uzbekistan);
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option42[i]);
							    		Answer = "Uzbekistan"; 
									}
						    		test++;
							break;
							
							case 42:  
								
								int selected42 = group.getCheckedRadioButtonId();
								option = (RadioButton) findViewById(selected42);
								Ans  = (String)option.getText();

						    			if( (Answer).equals (Ans) ){
						    				c++;
						    				up.setText(Integer.toString(c));
						    			}else
						    			{
						    				w++;
						    				down.setText(Integer.toString(w));
					 					}
								  flag.setImageResource(R.drawable.vietnam);
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option43[i]);
						    			Answer = "Vietnam";
									}
						    		test++;
							break;
							case 43:  
								  int selected43 = group.getCheckedRadioButtonId();
					   			option = (RadioButton) findViewById(selected43);
					   			Ans = (String)option.getText();

						    			if( (Answer).equals (Ans) ){
						    				c++;
						    				up.setText(Integer.toString(c));
						    			}else
						    			{
						    				w++;
						    				down.setText(Integer.toString(w));
					 					}
						    		
						    	  flag.setImageResource(R.drawable.yemen);	
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option44[i]);
							    		  Answer = "Yemen";
									} 
					   			test++;
							break;
							case 44:
								 int selected44 = group.getCheckedRadioButtonId();
					   			option = (RadioButton) findViewById(selected44);
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
						    			AlertDialog.Builder builder = new AlertDialog.Builder(Asia.this);
										  builder.setTitle("SCORE");
										  builder.setMessage("Your score is low, Please try again!");
										  builder.setCancelable(false);
									      builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
									        public void onClick(DialogInterface dialog, int id) {
									        	Intent act2 = new Intent(view.getContext(), Asia.class);
									 	        startActivity(act2);
									                dialog.cancel();
									                Asia.this.finish();
									            }
									        });
									      builder.create().show();
						    		    }else{
						    		    	Africa.score = Africa.score + (c*3) - w ;
						    		    	if  (HScore < Africa.score){
							    		    	HScore = Africa.score;
							    		    	AlertDialog.Builder builder = new AlertDialog.Builder(Asia.this);
												  builder.setTitle("SCORE");
												  builder.setMessage("New High Score!!!\nYour score is:"+Africa.score);
												  builder.setCancelable(false);
											      builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
											        public void onClick(DialogInterface dialog, int id) {
											        	Intent act2 = new Intent(view.getContext(), Europe.class);
											 	        startActivity(act2);
											                dialog.cancel();
											                Asia.this.finish();
											            }
											        });
											      builder.create().show();
							    		    	}else{
							    		    		AlertDialog.Builder builder = new AlertDialog.Builder(Asia.this);
													  builder.setTitle("SCORE");
													  builder.setMessage("Your score is:"+Africa.score);
													  builder.setCancelable(false);
												      builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
												        public void onClick(DialogInterface dialog, int id) {
												        	Intent act2 = new Intent(view.getContext(), Europe.class);
												 	        startActivity(act2);
												                dialog.cancel();
												                Asia.this.finish();
												            }
												        });
												      builder.create().show();
							    		    	}
						    		    }
						    			flag.setImageResource(R.drawable.asia);
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
	        setContentView(R.layout.asia);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.asia);
	        break;
	    }
	}
					}
