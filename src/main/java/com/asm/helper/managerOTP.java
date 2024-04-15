package com.asm.helper;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class managerOTP {
	
	int otp =0;
	public String mOTP="";
	
	public String generateOTP(int n){
        String so = "0123456789";
        Random ran = new Random();
        StringBuilder otpBul = new StringBuilder();
        
        for(int i = 0; i < n; i++){
            otp = ran.nextInt(so.length());
            otpBul.append(so.charAt(otp));
        }
        mOTP = otpBul.toString();
        return mOTP;
    } 
	
	 public String getmaOTP(){
	        return mOTP;
	    }
	    
    public void startOTP(long expirationTimeMillls){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				mOTP = "0";
                timer.cancel();
			}
        }, expirationTimeMillls);
           
    }
}
