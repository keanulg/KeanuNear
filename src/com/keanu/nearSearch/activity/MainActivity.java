package com.keanu.nearSearch.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.keanu.nearSearch.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014-11-22 .
 */
public class MainActivity extends Activity {
    private ListView index_menusListView;
    private Button index_locationButton;
    private LocationClient locationClient = null;
    private BDLocationListener myListener = new MyLocationListener();
    private List<String> menuList = new ArrayList<String>();;
    private String[] menuArray = {"餐饮服务","购物服务","生活服务","体育休闲服务","住宿服务","医疗保健服务","科教文化服务","交通设施服务"};
    private ProgressDialog progressDialog;
    private TextView index_dangQianLocText;
    private TextView index_currentLocationArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        index_dangQianLocText = (TextView) findViewById(R.id.index_dangQianLocText);
        index_menusListView = (ListView) findViewById(R.id.index_menusListView);
        index_locationButton = (Button) findViewById(R.id.index_locationButton);
        index_currentLocationArea = (TextView) findViewById(R.id.index_currentLocationArea);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("定位中，请稍等...");
        index_dangQianLocText.setText("定位中，");
        index_currentLocationArea.setText("请稍等...");
        for (int i = 0; i < menuArray.length; i++) {
            menuList.add(menuArray[i]);
        }
        MenuListAdapter adapter = new MenuListAdapter(this,R.layout.index_menu_list_item,menuList);
        index_menusListView.setAdapter(adapter);

        locationClient = new LocationClient(this);
        locationClient.registerLocationListener(myListener);
        setLocationOption();
        locationClient.start();
        progressDialog.show();
        setLocationButton();
    }

    /**
     * 自定义Adapter
     */

    class MenuListAdapter extends ArrayAdapter<String>{
        private int resourceId;
        MenuListAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            resourceId = textViewResourceId;
        }

        @Override
        public int getCount() {
            return super.getCount();
        }

        @Override
        public String getItem(int position) {
            return super.getItem(position);
        }

        @Override
        public int getPosition(String item) {
            return super.getPosition(item);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String s = getItem(position);
            View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            TextView menuItemText = (TextView) view.findViewById(R.id.menuItemText);
            menuItemText.setText(s);
            return view;
        }
    }
    /**
     * 定位
     */
    //定位按钮
    private void setLocationButton(){
        index_locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locationClient != null && locationClient.isStarted()){
                    locationClient.requestLocation();
                    progressDialog.show();
                    index_dangQianLocText.setText("定位中，");
                    index_currentLocationArea.setText("请稍等...");
                }else {
                    Log.d("msg","buxing");
                }
            }
        });
    }
    private void setLocationOption(){
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setIsNeedAddress(true);//返回的定位结果包含地址信息
        option.setCoorType("gcj02");//返回的定位结果是百度经纬度,默认值gcj02
        option.setScanSpan(5000);//设置发起定位请求的间隔时间为5000ms
        locationClient.setLocOption(option);

    }

    @Override
    protected void onDestroy() {
        locationClient.stop();
        super.onDestroy();
    }

    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation == null) {
                return;
            }
            Log.d("bdLocation", bdLocation.getCity() + " ");
            String name = bdLocation.getStreet();
            String name1 = bdLocation.getAddrStr();
            Log.d("bdLocation",bdLocation.getAddrStr()+"");
            progressDialog.dismiss();
            index_dangQianLocText.setText("当前位置：");
            index_currentLocationArea.setText(name);
        }
    }
}
