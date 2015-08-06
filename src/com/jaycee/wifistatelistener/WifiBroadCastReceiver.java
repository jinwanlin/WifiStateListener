package com.jaycee.wifistatelistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

public class WifiBroadCastReceiver extends BroadcastReceiver {
	private static final String TAG = "WifiBroadCastReceiver";
//	private Context appContext = App.mInstance;
	@Override
	public void onReceive(Context appContext, Intent intent) {
		String action = intent.getAction();
		if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)) {// 在此监听wifi有无
			int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
//			Log.i(TAG, "" + wifiState);
			switch (wifiState) {
			case WifiManager.WIFI_STATE_DISABLED:
				Toast.makeText(appContext, R.string.wifi_disable, Toast.LENGTH_LONG).show();
//				Log.i(TAG, "wifi不可用");
				break;
			case WifiManager.WIFI_STATE_DISABLING:
				Toast.makeText(appContext, R.string.wifi_disabling, Toast.LENGTH_LONG).show();
//				Log.i(TAG, "wifi正在断开");
				break;
			case WifiManager.WIFI_STATE_ENABLED:
				Toast.makeText(appContext, R.string.wifi_enable, Toast.LENGTH_LONG).show();
//				Log.i(TAG, "wifi可用");
				break;
			case WifiManager.WIFI_STATE_ENABLING:
				Toast.makeText(appContext, R.string.wifi_enabling, Toast.LENGTH_LONG).show();
//				Log.i(TAG, "wifi正在连接");
				break;
			case WifiManager.WIFI_STATE_UNKNOWN:
				Toast.makeText(appContext, R.string.wifi_unknown, Toast.LENGTH_LONG).show();
//				Log.i(TAG, "wifi处于未知状态");
				break;
			}
		}
		
		// 另外一种方式
//		WifiManager mWifiMng = (WifiManager) appContext
//				.getSystemService(appContext.WIFI_SERVICE);
//
//		switch (mWifiMng.getWifiState()) {
//		case WifiManager.WIFI_STATE_DISABLED:
//			Toast.makeText(appContext, R.string.wifi_disable, Toast.LENGTH_LONG)
//					.show();
//			break;
//		case WifiManager.WIFI_STATE_DISABLING:
//			break;
//		case WifiManager.WIFI_STATE_ENABLED:
//			Toast.makeText(appContext, R.string.wifi_enable, Toast.LENGTH_LONG)
//					.show();
//			break;
//		case WifiManager.WIFI_STATE_ENABLING:
//			break;
//		case WifiManager.WIFI_STATE_UNKNOWN:
//			break;
//		}

		if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(action)) {
			Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
			if (null != parcelableExtra) {
				NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
				
				DetailedState state = networkInfo.getDetailedState();
				Log.i(TAG, state.toString());
				
				boolean isConnected = networkInfo.isAvailable();
				if(isConnected){
					Toast.makeText(appContext, R.string.wifi_connect, Toast.LENGTH_LONG).show();
//					Log.i(TAG, "网络已连接");
				}else{
					Toast.makeText(appContext, R.string.wifi_unconnect, Toast.LENGTH_LONG).show();
//					Log.i(TAG, "无网络连接");
				}
				
			}
		}

	}

}
