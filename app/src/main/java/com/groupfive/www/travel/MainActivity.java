package com.groupfive.www.travel;

import view.UserLoginView;
import view.UserResigeView;



import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class MainActivity extends Activity implements UserLoginView,UserResigeView{
	private TextView register;
	private View v1,v2;
	
	AlertDialog ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
	      //ȡ��״̬��
	      //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	      //WindowManager.LayoutParams.FLAG_FULLSCREEN); 
        setContentView(R.layout.activity_main);
        
        register=(TextView)findViewById(R.id.register);
        
        register.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder ab=new AlertDialog.Builder(MainActivity.this);			
				v2=LayoutInflater.from(MainActivity.this).inflate(R.layout.register,null);			
				ab.setView(v2);
				ad=ab.create();
				Window window = ad.getWindow();			
				window.setGravity(Gravity.CENTER);
			    WindowManager.LayoutParams lp = window.getAttributes();
			    lp.y =0;
			    lp.alpha = 0.9f;    
			    window.setAttributes(lp); 
				ad.setCancelable(true);
				ad.setCanceledOnTouchOutside(true);
				ad.show();
			}
        });
    }
    

	@Override
	public void loginSuccess() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void loginFall() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void registerSuccess() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void registerFall(String message) {
		// TODO Auto-generated method stub
		ad.dismiss();
	}
}