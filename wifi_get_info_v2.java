package com.example.wi_fi_get_info;

import java.util.Timer;
import java.util.TimerTask;

import android.app.*;
import android.os.Bundle;
import android.os.Handler;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	Timer timer = null;

	// 現在接続しているWi-Fi情報の取得を開始する
	public void WiFi_info_get_Start() {

		final Handler handler = new Handler();

		this.timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				handler.post(new Runnable() {
					public void run() {
						// 接続開始
						get_WiFi_info();
					}
				});
			}
		}, 0, 1000);
	}

	// 情報取得を停止する
	// （※非実装。メモ程度に書きます。お好みでボタン等を追加する時の参考程度に…）
	public void iFi_info_get_stop() {
		this.timer.cancel();
	}

	// 現在接続中のWi-Fi情報を取得する
	public void get_WiFi_info() {
		WifiManager manager = (WifiManager) getSystemService(WIFI_SERVICE);
		WifiInfo info = manager.getConnectionInfo();

		String[] apInfo = new String[8];

		// SSIDを取得
		apInfo[0] = String.format("SSID : %s", info.getSSID());

		// ステルスSSIDを取得
		apInfo[1] = String.format("HiddenSSID : %s", info.getHiddenSSID());

		// IPアドレスを取得
		int ipAdr = info.getIpAddress();
		apInfo[2] = String.format("IP Adrress : %02d.%02d.%02d.%02d",
				(ipAdr >> 0) & 0xff, (ipAdr >> 8) & 0xff, (ipAdr >> 16) & 0xff, (ipAdr >> 24) & 0xff);

		// MACアドレスを取得
		apInfo[3] = String.format("MAC Address : %s", info.getMacAddress());

		// BSSIDを取得
		apInfo[4] = String.format("BSSID : %s", info.getBSSID());

		// ネットワークID取得
		int networkid = info.getNetworkId();
		apInfo[5] = String.format("NetworkID : %s", networkid);

		// 受信信号強度 ＆ 信号レベルを取得
		int rssi = info.getRssi();
		int level = WifiManager.calculateSignalLevel(rssi, 5);
		apInfo[6] = String.format("RSSI : %d / Level : %d/4", rssi, level);

		// リンクスピード取得
		int linkspeed = info.getLinkSpeed();
		apInfo[7] = String.format("LinkSpeed : %d", linkspeed);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, apInfo);

		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		WiFi_info_get_Start();

	}

}
