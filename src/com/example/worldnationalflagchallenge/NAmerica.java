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

public class NAmerica extends Activity{
	int test=0, count, c, w, a, b, x;
	public static final String PREFS_NAME = "MyPrefsFile";
	Button ok;
	ImageView flag, title, wrong, right, namerica;
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
	        setContentView(R.layout.namerica);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.namerica);
	        break;
	    }

	    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0); 
		// Read a string value with key "LastUse"
	     HScore = settings.getInt("Score", 0);
	    //String lastUse = settings.getString("LastUse", "");
	    
        final ArrayList<Integer> rndm = new ArrayList<Integer>();
        for(int i=0; i<24; i++){
			rndm.add(i);
		}
		Collections.shuffle(rndm);
		
		x = rndm.get(0);
		a = rndm.indexOf(0); // Returns index of first occurrence of 0
		rndm.set(0, (rndm.get(a) ) ); //rndm.get(10) Returns the object at index 10.
		rndm.set(a, x );
		
		x = rndm.get(23);
		b = rndm.indexOf(23);
		rndm.set(23, (rndm.get(b) ) );
		rndm.set(b, x );
		
		ok = (Button)findViewById(R.id.button1);
		flag = (ImageView)findViewById(R.id.imageView2);
		title = (ImageView)findViewById(R.id.imageView1);
		wrong = (ImageView)findViewById(R.id.imageView4);
		right = (ImageView)findViewById(R.id.imageView3);
		namerica = (ImageView)findViewById(R.id.imageView5);
		group = (RadioGroup)findViewById(R.id.radioGroup1);
		up = (TextView)findViewById(R.id.textView1);
		down = (TextView)findViewById(R.id.textView2);
		highest = (TextView)findViewById(R.id.textView3);
	    value = (TextView)findViewById(R.id.textView4);
		
	    final String[] option1 = {"Jamaica","Antigua & Barbuda","Bahamas","U.S.A"};  //Antigua & Barbuda
		final String[] option2 = {"Bahamas","Belize","St.Lucia","Grenada"};	 //Bahamas
	   	final String[] option3 = {"Cuba","Haiti","Barbados","Panama"};	//Barbados
	   	final String[] option4 = {"Dominica","Belize","El Salvador","Cuba"};	//Belize
	   	final String[] option5 = {"St.Kitts & Nevis","Bermuda","Guatamala","Honduras"};	  //Bermuda
		final String[] option6 = {"Cuba","St.Vincent & Grenadines","Nicaragua","Canada"};	//Canada
		final String[] option7 = {"Haiti","Costa Rica","Panama","Belize"};	//Costa Rica
	   	final String[] option8 = {"Cuba","Mexico","Bahamas","St.Lucia"};	//Cuba
	   	final String[] option9 = {"U.S.A","Bermuda","Dominica","Bahamas"};	//Dominica
	   	final String[] option10 = {"Haiti","Greenland","Barbados","El Salvador"};	//El Salvador
		final String[] option11 = {"Guatemala","Greenland","Honduras","Belize"};	//Greenland
		final String[] option12 = {"Mexico","Nicaragua","Grenada","Cuba"};	 //Grenada
	   	final String[] option13 = {"Guatemala","Costa Rica","Dominica","Panama"};	//Guatemala
	   	final String[] option14 = {"Grenada","Belize","Jamaica","Haiti"};	//Haiti
	   	final String[] option15 = {"Antigua & Barbuda","Honduras","Jamaica","Mexico"};	 //Honduras
		final String[] option16 = {"Haiti","Cuba","Canada","Jamaica"};	//Jamaica
		final String[] option17 = {"Dominica","Mexico","U.S.A","Panama"};	//Mexico
	   	final String[] option18 = {"Honduras","Haiti","Nicaragua","Cuba"};		//Nicaragua
	   	final String[] option19 = {"Panama","U.S.A","Jamaica","Grenada"};	//Panama
	   	final String[] option20 = {"Canada","St.Kitts & Nevis","Cuba","Belize"};	 //St.Kitts & Nevis
		final String[] option21 = {"Haiti","Bermuda","Mexico","St.Lucia"};	//St.Lucia
	   	final String[] option22 = {"Dominica","St.Vincent & Grenadines","Cuba","U.S.A"};	//St.Vincent & Grenadines
	   	final String[] option23 = {"Panama","Canada","Belize","U.S.A"};	 //U.S.A
	    
	   	value.setText(Integer.toString(HScore));
	   	
	    ok.setOnClickListener(new View.OnClickListener(){
	   @Override
	  public void onClick(final View view) {
		   switch(rndm.get(test))
			{
		case 0:
			  namerica.setVisibility(View.INVISIBLE);
			  flag.setImageResource(R.drawable.antigua_and_barbuda);
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
	    		Answer = "Antigua & Barbuda";
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
			
			  flag.setImageResource(R.drawable.bahamas);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option2[i]);
		    		Answer = "Bahamas"; 
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
			  flag.setImageResource(R.drawable.barbados);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option3[i]);
	    			Answer = "Barbados";
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
	    		
	    	  flag.setImageResource(R.drawable.belize);	
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option4[i]);
		    		  Answer = "Belize";
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
	    			flag.setImageResource(R.drawable.bermuda);
			  for (int i = 0; i < group.getChildCount(); i++) {
					((RadioButton) group.getChildAt(i)).setText(option5[i]);
		    		  Answer = "Bermuda";
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
				
				  flag.setImageResource(R.drawable.canada);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option6[i]);
			    		Answer = "Canada"; 
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
				  flag.setImageResource(R.drawable.costa_rica);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option7[i]);
		    			Answer = "Costa Rica";
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
		    		
		    	  flag.setImageResource(R.drawable.cuba);	
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option8[i]);
			    		  Answer = "Cuba";
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
		    			flag.setImageResource(R.drawable.dominica);
				  for (int i = 0; i < group.getChildCount(); i++) {
						((RadioButton) group.getChildAt(i)).setText(option9[i]);
			    		  Answer = "Dominica";
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
					
					  flag.setImageResource(R.drawable.el_salvador);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option10[i]);
				    		Answer = "El Salvador"; 
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
					  flag.setImageResource(R.drawable.greenland);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option11[i]);
			    			Answer = "Greenland";
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
			    		
			    	  flag.setImageResource(R.drawable.grenada);	
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option12[i]);
				    		  Answer = "Grenada";
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
			    			flag.setImageResource(R.drawable.guatemala);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option13[i]);
				    		  Answer = "Guatemala";
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
						
						  flag.setImageResource(R.drawable.haiti);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option14[i]);
					    		Answer = "Haiti"; 
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
						  flag.setImageResource(R.drawable.honduras);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option15[i]);
				    			Answer = "Honduras";
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
				    		
				    	  flag.setImageResource(R.drawable.jamaica);	
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option16[i]);
					    		  Answer = "Jamaica";
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
				    			flag.setImageResource(R.drawable.mexico);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option17[i]);
					    		  Answer = "Mexico";
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
			    			flag.setImageResource(R.drawable.nicaragua);
					  for (int i = 0; i < group.getChildCount(); i++) {
							((RadioButton) group.getChildAt(i)).setText(option18[i]);
				    		  Answer = "Nicaragua";
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
						
						  flag.setImageResource(R.drawable.panama);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option19[i]);
					    		Answer = "Panama"; 
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
						  flag.setImageResource(R.drawable.saint_kitts_and_nevis);
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option20[i]);
				    			Answer = "St.Kitts & Nevis";
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
				    		
				    	  flag.setImageResource(R.drawable.saint_lucia);	
						  for (int i = 0; i < group.getChildCount(); i++) {
								((RadioButton) group.getChildAt(i)).setText(option21[i]);
					    		  Answer = "St.Lucia";
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
							
							  flag.setImageResource(R.drawable.saint_vincent_and_the_grenadines);
							  for (int i = 0; i < group.getChildCount(); i++) {
									((RadioButton) group.getChildAt(i)).setText(option22[i]);
						    		Answer = "St.Vincent & Grenadines"; 
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
							  flag.setImageResource(R.drawable.usa);
							  for (int i = 0; i < group.getChildCount(); i++) {
									((RadioButton) group.getChildAt(i)).setText(option23[i]);
					    			Answer = "U.S.A";
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
					    		    if (c < 8){	
					    			AlertDialog.Builder builder = new AlertDialog.Builder(NAmerica.this);
									  builder.setTitle("SCORE");
									  builder.setMessage("Your score is low, Please try again!");
									  builder.setCancelable(false);
								      builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								        public void onClick(DialogInterface dialog, int id) {
								        	Intent act2 = new Intent(view.getContext(), NAmerica.class);
								 	        startActivity(act2);
								                dialog.cancel();
								                NAmerica.this.finish();
								            }
								        });
								      builder.create().show();
					    		    }else{
					    		    	Africa.score = Africa.score + (c*3) - w ;
					    		    	if  (HScore < Africa.score){
						    		    	HScore = Africa.score;
						    		    	AlertDialog.Builder builder = new AlertDialog.Builder(NAmerica.this);
											  builder.setTitle("SCORE");
											  builder.setMessage("New High Score!!!\nYour score is:"+Africa.score);
											  builder.setCancelable(false);
										      builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
										        public void onClick(DialogInterface dialog, int id) {
										        	Intent act2 = new Intent(view.getContext(), Oceania.class);
										 	        startActivity(act2);
										                dialog.cancel();
										                NAmerica.this.finish();
										            }
										        });
										      builder.create().show();
						    		    	}else{
						    		    		AlertDialog.Builder builder = new AlertDialog.Builder(NAmerica.this);
												  builder.setTitle("SCORE");
												  builder.setMessage("Your score is:"+Africa.score);
												  builder.setCancelable(false);
											      builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
											        public void onClick(DialogInterface dialog, int id) {
											        	Intent act2 = new Intent(view.getContext(), Oceania.class);
											 	        startActivity(act2);
											                dialog.cancel();
											                NAmerica.this.finish();
											            }
											        });
											      builder.create().show();
						    		    	}
					    		    }
					    			flag.setImageResource(R.drawable.n);
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
	        setContentView(R.layout.namerica);
	        break;
	    case Configuration.ORIENTATION_PORTRAIT:
	        setContentView(R.layout.namerica);
	        break;
	    }
	}
				}

	
