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

public class Africa extends Activity{
	int test=0, count, c, w, a, b, x;
	static int score;
	Button ok;
	public static final String PREFS_NAME = "MyPrefsFile";
	ImageView flag, title, wrong, right, africa;
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
	        setContentView(R.layout.africa);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.africa);
	        break;
	    }
	    
	    
	    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0); 
		// Read a string value with key "LastUse"
	     HScore = settings.getInt("Score", 0);
	    //String lastUse = settings.getString("LastUse", "");
	    
        final ArrayList<Integer> rndm = new ArrayList<Integer>();
        for(int i=0; i<55; i++){
			rndm.add(i);
		}
		Collections.shuffle(rndm);
		
		x = rndm.get(0);
		a = rndm.indexOf(0); // Returns index of first occurrence of 0
		rndm.set(0, (rndm.get(a) ) ); //rndm.get(10) Returns the object at index 10.
		rndm.set(a, x );
		
		x = rndm.get(54);
		b = rndm.indexOf(54);
		rndm.set(54, (rndm.get(b) ) );
		rndm.set(b, x );
		
		ok = (Button)findViewById(R.id.button1);
		flag = (ImageView)findViewById(R.id.imageView2);
		title = (ImageView)findViewById(R.id.imageView1);
		wrong = (ImageView)findViewById(R.id.imageView4);
		right = (ImageView)findViewById(R.id.imageView3);
		africa = (ImageView)findViewById(R.id.imageView5);
		group = (RadioGroup)findViewById(R.id.radioGroup1);
		up = (TextView)findViewById(R.id.textView1);
		down = (TextView)findViewById(R.id.textView2);
		highest = (TextView)findViewById(R.id.textView3);
	    value = (TextView)findViewById(R.id.textView4);
		
	    final String[] option1 = {"Libya","Algeria","Egypt","Morocco"};   //Algeria
		final String[] option2 = {"Lesotho","Chad","Liberia","Angola"};	//Angola
	   	final String[] option3 = {"Benin","Kenya","E. Guinea","Gabon"};	//Benin
	   	final String[] option4 = {"Ethiopia","Angola","Botswana","Burkina Faso"};	//Botswana
	   	final String[] option5 = {"Burkina Faso","Cote d'Ivoire","Rwanda","Tanzania"};	//Burkina Faso
		final String[] option6 = {"Kenya","Mauritania","Burundi","Mali"};	//Burundi
		final String[] option7 = {"Niger","Cameroon","Djibouti","D.R Congo"};	//Cameroon
	   	final String[] option8 = {"Madagascar","Cape Verde","Guinea Bissau","Gabon"};	//Cape Verde
	   	final String[] option9 = {"C. Africa Rep.","Uganda","Togo","Zambia"};	//C. Africa Rep.
	   	final String[] option10 = {"Chad","Burkina Faso","Guinea","Swaziland"};	//Chad
		final String[] option11 = {"Gambia","Chad","Nigeria","Cote d'Ivoire"};	//Cote d'Ivoire
		final String[] option12 = {"Namibia","Mauritius","D.R Congo","Djibouti"};	//D.R Congo
	   	final String[] option13 = {"Eritrea","Djibouti","Ethiopia","Mozambique"};	//Djibouti
	   	final String[] option14 = {"Gabon","Mali","E. Guinea","Egypt"};	//Egypt
	   	final String[] option15 = {"Lesotho","E. Guinea","Seychelles","Reunion"};	//E. Guinea
		final String[] option16 = {"R. Congo","South Africa","Eritrea","Tanzania"};	//Eritrea
		final String[] option17 = {"Sierra Leone","Senegal","Gabon","Ethiopia"};		//Ethiopia
	   	final String[] option18 = {"Tunisia","Rwanda","Ghana","Gabon"};		//Gabon
	   	final String[] option19 = {"Gambia","Niger","Reunion","R. Congo"};	//Gambia
	   	final String[] option20 = {"E. Guinea","Senegal","Ghana","Somalia"};	 //Ghana
		final String[] option21 = {"Mali","Sierra Leone","Guinea","Tunisia"};	//Guinea
	   	final String[] option22 = {"Zambia","Guinea Bissau","Sao Tome & P.","Gabon"};	//Guinea Bissau
	   	final String[] option23 = {"Kenya","Ethiopia","Somalia","Rwanda"};	//Kenya 
	   	final String[] option24 = {"Swaziland","Lesotho","S. Sudan","Reunion"};	//Lesotho
		final String[] option25 = {"Madagascar","South Africa","Libya","Liberia"};	//Liberia		
	   	final String[] option26 = {"Somalia","Morocco","Libya","Niger"};	 //Libya
	   	final String[] option27 = {"Togo","Swaziland","Seychelles","Madagascar"};	//Madagascar
	   	final String[] option28 = {"Uganda","Tanzania","Malawi","Zambia"};		//Malawi
		final String[] option29 = {"Mali","Sierra Leone","Tunisia","Burkina Faso"};		//Mali
		final String[] option30 = {"Sudan","Mauritania","Egypt","Nigeria"};		//Mauritania
	   	final String[] option31 = {"Zimbabwe","South Africa","Mozambique","Mauritius"};		//Mauritius
	   	final String[] option32 = {"Senegal","Morocco","Gambia","Sudan"};		//Morocco
	   	final String[] option33 = {"Mozambique","Madagascar","S. Sudan","Reunion"};		//Mozambique
		final String[] option34 = {"Togo","Zambia","Tanzania","Namibia"}; //Namibia
		final String[] option35 = {"Swaziland","Tunisia","Niger","Togo"};	//Niger
	   	final String[] option36 = {"Nigeria","Reunion","Somalia","Cote d'Ivoire"};		//Nigeria
	   	final String[] option37 = {"Mozambique","R. Congo","Gambia","Uganda"};		//R. Congo
	   	final String[] option38 = {"Reunion","Rwanda","Nigeria","Togo"};		//Reunion
		final String[] option39 = {"Tanzania","Uganda","Zimbabwe","Rwanda"};		//Rwanda
		final String[] option40 = {"Senegal","Sao Tome & P.","Ghana","Tunisia"};		//Sao Tome & P.
	   	final String[] option41 = {"Togo","Senegal","Reunion","Sudan"};		//Senegal
	   	final String[] option42 = {"Madagascar","S. Sudan","Seychelles","R. Congo"};		//Seychelles
	   	final String[] option43 = {"Zambia","Liberia","Nigeria","Sierra Leone"};	  //Sierra Leone
		final String[] option44 = {"Niger","Kenya","Somalia","Tanzania"};	 //Somalia
	   	final String[] option45 = {"Tunisia","South Africa","Uganda","Togo"};		//South Africa
	   	final String[] option46 = {"S. Sudan","Botswana","Gambia","Sudan"};		//S. Sudan
	   	final String[] option47 = {"Sudan","Togo","Egypt","S. Sudan"};		//Sudan
		final String[] option48 = {"Lesotho","Swaziland","Tanzania","Niger"};	//Swaziland
	   	final String[] option49 = {"Tanzania","Seychelles","Uganda","Swaziland"};	//Tanzania
	   	final String[] option50 = {"Zambia","Tunisia","Gambia","Togo"};	 //Togo
	   	final String[] option51 = {"Morocco","Sudan","Tunisia","Saudi Arabia"};	  //Tunisia
		final String[] option52 = {"Namibia","Gambia","Uganda","Zimbabwe"};    //Uganda
		final String[] option53 = {"Niger","Zambia","Rwanda","Gabon"};   //Zambia
	   	final String[] option54 = {"Seychelles","Zimbabwe","Togo","Algeria"};	 //Zimbabwe
	    
	   	value.setText(Integer.toString(HScore));
	   	
	  ok.setOnClickListener(new View.OnClickListener(){
	  @Override
	  public void onClick(final View view) {
		switch(rndm.get(test))
			{
		case 0:
			  africa.setVisibility(View.INVISIBLE);
			  flag.setImageResource(R.drawable.algeria);
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
	    		Answer = "Algeria";
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
			
			  flag.setImageResource(R.drawable.angola);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option2[i]);
		    		Answer = "Angola"; 
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
			  flag.setImageResource(R.drawable.benin);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option3[i]);
	    			Answer = "Benin";
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
	    		
	    	  flag.setImageResource(R.drawable.botswana);	
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option4[i]);
		    		  Answer = "Botswana";
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
	    			flag.setImageResource(R.drawable.burkina_faso);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option5[i]);
		    		  Answer = "Burkina Faso";
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
				
				  flag.setImageResource(R.drawable.burundi);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option6[i]);
			    		Answer = "Burundi"; 
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
				  flag.setImageResource(R.drawable.cameroon);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option7[i]);
		    			Answer = "Cameroon";
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
		    		
		    	  flag.setImageResource(R.drawable.cape_verde);	
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option8[i]);
			    		  Answer = "Cape Verde";
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
		    			flag.setImageResource(R.drawable.central_african_republic);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option9[i]);
			    		  Answer = "C. Africa Rep.";
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
					
					  flag.setImageResource(R.drawable.chad);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option10[i]);
				    		Answer = "Chad"; 
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
					  flag.setImageResource(R.drawable.cte_divoire);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option11[i]);
			    			Answer = "Cote d'Ivoire";
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
			    		
			    	  flag.setImageResource(R.drawable.democratic_republic_of_the_congo);	
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option12[i]);
				    		  Answer = "D.R Congo";
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
			    			flag.setImageResource(R.drawable.djibouti);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option13[i]);
				    		  Answer = "Djibouti";
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
						
						  flag.setImageResource(R.drawable.egypt);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option14[i]);
					    		Answer = "Egype"; 
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
						  flag.setImageResource(R.drawable.equatorial_guinea);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option15[i]);
				    			Answer = "E. Guinea";
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
				    		
				    	  flag.setImageResource(R.drawable.eritrea);	
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option16[i]);
					    		  Answer = "Eritrea";
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
				    			flag.setImageResource(R.drawable.ethiopia);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option17[i]);
					    		  Answer = "Ethiopia";
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
			    			flag.setImageResource(R.drawable.gabon);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option18[i]);
				    		  Answer = "Gabon";
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
						
						  flag.setImageResource(R.drawable.gambia);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option19[i]);
					    		Answer = "Gambia"; 
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
						  flag.setImageResource(R.drawable.ghana);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option20[i]);
				    			Answer = "Ghana";
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
				    		
				    	  flag.setImageResource(R.drawable.guinea);	
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option21[i]);
					    		  Answer = "Guinea";
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
							
							  flag.setImageResource(R.drawable.guinea_bissau);
							  for (int i = 0; i < group.getChildCount(); i++) {
									((RadioButton) group.getChildAt(i)).setText(option22[i]);
						    		Answer = "Guinea Bissau"; 
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
							  flag.setImageResource(R.drawable.kenya);
							  for (int i = 0; i < group.getChildCount(); i++) {
									((RadioButton) group.getChildAt(i)).setText(option23[i]);
					    			Answer = "Kenya";
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
					    		
					    	  flag.setImageResource(R.drawable.lesotho);	
							  for (int i = 0; i < group.getChildCount(); i++) {
									((RadioButton) group.getChildAt(i)).setText(option24[i]);
						    		  Answer = "Lesotho";
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
					    			flag.setImageResource(R.drawable.liberia);
							  for (int i = 0; i < group.getChildCount(); i++) {
									((RadioButton) group.getChildAt(i)).setText(option25[i]);
						    		  Answer = "Liberia";
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
								
								  flag.setImageResource(R.drawable.libya);
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option26[i]);
							    		Answer = "Libya"; 
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
								  flag.setImageResource(R.drawable.madagascar);
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option27[i]);
						    			Answer = "Madagascar";
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
						    		
						    	  flag.setImageResource(R.drawable.malawi);	
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option28[i]);
							    		  Answer = "Malawi";
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
						    			flag.setImageResource(R.drawable.mali);
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option29[i]);
							    		  Answer = "Mali";
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
									
									  flag.setImageResource(R.drawable.mauritania);
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option30[i]);
								    		Answer = "Mauritania"; 
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
									  flag.setImageResource(R.drawable.mauritius);
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option31[i]);
							    			Answer = "Mauritius";
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
							    		
							    	  flag.setImageResource(R.drawable.morocco);	
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option32[i]);
								    		  Answer = "Morocco";
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
							    			flag.setImageResource(R.drawable.mozambique);
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option33[i]);
								    		  Answer = "Mozambique";
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
										
										  flag.setImageResource(R.drawable.namibia);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option34[i]);
									    		Answer = "Namibia"; 
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
										  flag.setImageResource(R.drawable.niger);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option35[i]);
								    			Answer = "Niger";
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
								    		
								    	  flag.setImageResource(R.drawable.nigeria);	
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option36[i]);
									    		  Answer = "Nigeria";
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
								    			flag.setImageResource(R.drawable.republic_of_congo);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option37[i]);
									    		  Answer = "R. Congo";
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
							    			flag.setImageResource(R.drawable.runion);
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option38[i]);
								    		  Answer = "Reunion";
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
										
										  flag.setImageResource(R.drawable.rwanda);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option39[i]);
									    		Answer = "Rwanda"; 
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
										  flag.setImageResource(R.drawable.sao_tome_and_principe);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option40[i]);
								    			Answer = "Sao Tome & P.";
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
								    		
								    	flag.setImageResource(R.drawable.senegal);	
										for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option41[i]);
									    Answer = "Senegal";
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
								
								  flag.setImageResource(R.drawable.seychelles);
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option42[i]);
							    		Answer = "Seychelles"; 
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
								  flag.setImageResource(R.drawable.sierra_leone);
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option43[i]);
						    			Answer = "Sierra Leone";
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
						    		
						    	  flag.setImageResource(R.drawable.somalia);	
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option44[i]);
							    		  Answer = "Somalia";
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
						    			flag.setImageResource(R.drawable.south_africa);
								  for (int i = 0; i < group.getChildCount(); i++) {
										((RadioButton) group.getChildAt(i)).setText(option45[i]);
							    		  Answer = "South Africa";
									}
						    		  test++;
							break;
							case 45:

								  int selected45 = group.getCheckedRadioButtonId();
									option = (RadioButton) findViewById(selected45);
									Ans = (String)option.getText();
									
						   			if( (Answer).equals (Ans) ){
						   				c++;
						   				up.setText(Integer.toString(c));
						   			}else
						   			{
						   				w++;
						   				down.setText(Integer.toString(w));
											}
									
									  flag.setImageResource(R.drawable.south_sudan);
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option46[i]);
								    		Answer = "S. Sudan"; 
										}
							    		test++;
								break;
								
								case 46:  
									
									int selected46 = group.getCheckedRadioButtonId();
									option = (RadioButton) findViewById(selected46);
									Ans  = (String)option.getText();

							    			if( (Answer).equals (Ans) ){
							    				c++;
							    				up.setText(Integer.toString(c));
							    			}else
							    			{
							    				w++;
							    				down.setText(Integer.toString(w));
						 					}
									  flag.setImageResource(R.drawable.sudan);
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option47[i]);
							    			Answer = "Sudan";
										}
							    		test++;
								break;
								case 47:  
									  int selected47 = group.getCheckedRadioButtonId();
						   			option = (RadioButton) findViewById(selected47);
						   			Ans = (String)option.getText();

							    			if( (Answer).equals (Ans) ){
							    				c++;
							    				up.setText(Integer.toString(c));
							    			}else
							    			{
							    				w++;
							    				down.setText(Integer.toString(w));
						 					}
							    		
							    	  flag.setImageResource(R.drawable.swaziland);	
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option48[i]);
								    		  Answer = "Swaziland";
										} 
						   			test++;
								break;
								
								case 48:
									 int selected48 = group.getCheckedRadioButtonId();
						   			option = (RadioButton) findViewById(selected48);
						   			Ans = (String)option.getText();

							    			if( (Answer).equals (Ans) ){
							    				c++;
							    				up.setText(Integer.toString(c));
							    			}else
							    			{
							    				w++;
							    				down.setText(Integer.toString(w));
							    			}
							    			flag.setImageResource(R.drawable.tanzania);
									  for (int i = 0; i < group.getChildCount(); i++) {
											((RadioButton) group.getChildAt(i)).setText(option49[i]);
								    		  Answer = "Tanzania";
										}
							    		  test++;
								break;
								case 49:

									  int selected49 = group.getCheckedRadioButtonId();
										option = (RadioButton) findViewById(selected49);
										Ans = (String)option.getText();
										
							   			if( (Answer).equals (Ans) ){
							   				c++;
							   				up.setText(Integer.toString(c));
							   			}else
							   			{
							   				w++;
							   				down.setText(Integer.toString(w));
												}
										
										  flag.setImageResource(R.drawable.togo);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option50[i]);
									    		Answer = "Togo"; 
											}
								    		test++;
									break;
									
									case 50:  
										
										int selected50 = group.getCheckedRadioButtonId();
										option = (RadioButton) findViewById(selected50);
										Ans  = (String)option.getText();

								    			if( (Answer).equals (Ans) ){
								    				c++;
								    				up.setText(Integer.toString(c));
								    			}else
								    			{
								    				w++;
								    				down.setText(Integer.toString(w));
							 					}
										  flag.setImageResource(R.drawable.tunisia);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option51[i]);
								    			Answer = "Tunisia";
											}
								    		test++;
									break;
									case 51:  
										  int selected51 = group.getCheckedRadioButtonId();
							   			option = (RadioButton) findViewById(selected51);
							   			Ans = (String)option.getText();

								    			if( (Answer).equals (Ans) ){
								    				c++;
								    				up.setText(Integer.toString(c));
								    			}else
								    			{
								    				w++;
								    				down.setText(Integer.toString(w));
							 					}
								    		
								    	  flag.setImageResource(R.drawable.uganda);	
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option52[i]);
									    		  Answer = "Uganda";
											} 
							   			test++;
									break;
									
									case 52:
										 int selected52 = group.getCheckedRadioButtonId();
							   			option = (RadioButton) findViewById(selected52);
							   			Ans = (String)option.getText();

								    			if( (Answer).equals (Ans) ){
								    				c++;
								    				up.setText(Integer.toString(c));
								    			}else
								    			{
								    				w++;
								    				down.setText(Integer.toString(w));
								    			}
								    			flag.setImageResource(R.drawable.zambia);
										  for (int i = 0; i < group.getChildCount(); i++) {
												((RadioButton) group.getChildAt(i)).setText(option53[i]);
									    		  Answer = "Zambia";
											}
								    		  test++;
						break;
						case 53:

							int selected53 = group.getCheckedRadioButtonId();
								option = (RadioButton) findViewById(selected53);
								Ans = (String)option.getText();
								
							if( (Answer).equals (Ans) ){
								 c++;
								 up.setText(Integer.toString(c));
							}else
								 {
								 w++;
								 down.setText(Integer.toString(w));
								}
											
							flag.setImageResource(R.drawable.zimbabwe);
							for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option54[i]);
								Answer = "Zimbabwe"; 
								}
							test++;
				break;	
					
		case 54:
			 int selected54 = group.getCheckedRadioButtonId();
   			option = (RadioButton) findViewById(selected54);
   			Ans = (String)option.getText();

	    			if( (Answer).equals (Ans) ){
	    				c++;
	    				up.setText(Integer.toString(c));
	    			}else
	    			{
	    				w++;
	    				down.setText(Integer.toString(w));
	    			}
	    		    if (c < 15){	
	    			AlertDialog.Builder builder = new AlertDialog.Builder(Africa.this);
					  builder.setTitle("SCORE");
					  builder.setMessage("Your score is low, Please try again!");
					  builder.setCancelable(false);
				      builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				        	Intent act2 = new Intent(view.getContext(), Africa.class);
				 	        startActivity(act2);
				                dialog.cancel();
				                Africa.this.finish();
				            }
				        });
				      builder.create().show();
	    		    }else{
	    		    	
	    		    	score = (c*3) - w ;
	    		    if  (HScore < score){
	    		    	HScore = score;
	    		    	AlertDialog.Builder builder = new AlertDialog.Builder(Africa.this);
						  builder.setTitle("SCORE");
						  builder.setMessage("New High Score!!!\nYour score is:"+score);
						  builder.setCancelable(false);
					      builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface dialog, int id) {
					        	Intent act2 = new Intent(view.getContext(), Asia.class);
					 	        startActivity(act2);
					                dialog.cancel();
					                Africa.this.finish();
					            }
					        });
					      builder.create().show();
	    		    	}else{
	    		    		AlertDialog.Builder builder = new AlertDialog.Builder(Africa.this);
							  builder.setTitle("SCORE");
							  builder.setMessage("Your score is:"+score);
							  builder.setCancelable(false);
						      builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
						        public void onClick(DialogInterface dialog, int id) {
						        	Intent act2 = new Intent(view.getContext(), Asia.class);
						 	        startActivity(act2);
						                dialog.cancel();
						                Africa.this.finish();
						            }
						        });
						      builder.create().show();
	    		    	}
	    		    }
	    			flag.setImageResource(R.drawable.africa);
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
	
	/*@Override
	protected void	onSaveInstanceState(Bundle outState){		
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
	}*/
	
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
	        setContentView(R.layout.africa);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.africa);
	        break;
	    }
	}
}
