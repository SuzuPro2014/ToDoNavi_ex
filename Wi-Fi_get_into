package com.example.wi_fi_get_info;

import android.app.*;
import android.os.Bundle;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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

}
