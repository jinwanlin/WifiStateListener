package com.jaycee.wifistatelistener;
import android.app.Application;


public class App extends Application {

	public static App mInstance;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		mInstance = this;
	}
}
