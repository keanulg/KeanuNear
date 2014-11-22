package com.keanu.nearSearch.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.keanu.nearSearch.R;
import com.keanu.nearSearch.model.KeanuNearDB;
import com.keanu.nearSearch.model.NaviOne;
import com.keanu.nearSearch.model.NaviTwo;

import java.util.List;

/**
 * Created by Administrator on 2014-11-22 .
 */
public class NaviTwoActivity extends Activity {
    private KeanuNearDB keanuNearDB;
    private SharedPreferences pref;
    private List<NaviTwo> naviTwoList;
    private ListView second_nvi_menusListView;
    private TextView second_navi_title;
    private Button second_nvi_backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_second_navi);

        second_navi_title = (TextView) findViewById(R.id.second_navi_title);
        second_nvi_backButton = (Button) findViewById(R.id.second_nvi_backButton);
        keanuNearDB = KeanuNearDB.getInstance(this);
        NaviOne naviOne = (NaviOne) getIntent().getSerializableExtra("navi_one");
        second_navi_title.setText(naviOne.getNaviOneName());
        pref = getSharedPreferences("navi_two",MODE_PRIVATE);
        boolean isWrited1 = pref.getBoolean("is_navi_two_writed1",false);
        boolean isWrited2 = pref.getBoolean("is_navi_two_writed2",false);
        boolean isWrited3 = pref.getBoolean("is_navi_two_writed3",false);
        boolean isWrited4 = pref.getBoolean("is_navi_two_writed4",false);
        boolean isWrited5 = pref.getBoolean("is_navi_two_writed5",false);
        boolean isWrited6 = pref.getBoolean("is_navi_two_writed6",false);
        boolean isWrited7 = pref.getBoolean("is_navi_two_writed7",false);
        if (isWrited1==false||isWrited2==false||isWrited3==false||isWrited4==false||isWrited5==false||isWrited6==false||isWrited7==false){
            initNaviTwoListData(naviOne);
        }

        naviTwoList = keanuNearDB.loadNaviTwo(naviOne.getNaviOneId());
        second_nvi_menusListView = (ListView) findViewById(R.id.second_nvi_menusListView);
        NaviTwoListAdapter adapter = new NaviTwoListAdapter(this,R.layout.navi_two_list_item,naviTwoList);
        second_nvi_menusListView.setAdapter(adapter);

        setBackButton();
    }

    /**
     * 自定义Adapter
     */

    class NaviTwoListAdapter extends ArrayAdapter<NaviTwo> {
        private int resourceId;
        NaviTwoListAdapter(Context context, int textViewResourceId, List<NaviTwo> objects) {
            super(context, textViewResourceId, objects);
            resourceId = textViewResourceId;
        }

        @Override
        public int getCount() {
            return super.getCount();
        }

        @Override
        public NaviTwo getItem(int position) {
            return super.getItem(position);
        }

        @Override
        public int getPosition(NaviTwo item) {
            return super.getPosition(item);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final NaviTwo naviTwo = getItem(position);
            View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            TextView menuItemText = (TextView) view.findViewById(R.id.menuItemText);
            menuItemText.setText(naviTwo.getNaviTwoName());
            return view;
        }
    }
    /**
     * 二级导航列表初始化
     */
    private void initNaviTwoListData(NaviOne naviOne){
        SharedPreferences.Editor editor = getSharedPreferences("navi_two",MODE_PRIVATE).edit();
        if (naviOne.getNaviOneName().equals("美食")){
            String[] menuArray = {"蛋糕","甜品饮料","自助餐","火锅","午市套餐",
                    "下午茶","日韩料理","西餐","小吃快餐","烧烤烤鱼","粤菜","茶餐厅","江浙菜","川湘菜",
                    "咖啡茶馆","酒吧","海鲜","东南亚菜","清真","东北菜","冰淇淋"};
            for (int i = 0; i < menuArray.length; i++) {
                NaviTwo naviTwo = new NaviTwo();
                naviTwo.setNaviOneId(naviOne.getNaviOneId());
                naviTwo.setNaviTwoName(menuArray[i]);
                keanuNearDB.saveNaviTwo(naviTwo);
            }
            editor.putBoolean("is_navi_two_writed1",true);
            editor.commit();
        }else if (naviOne.getNaviOneName().equals("休闲娱乐")){
            String[] menuArray ={"电影院","KTV","温泉","滑雪","公园","游乐园","景点","郊游","密室逃脱","桌游","电玩","网吧",
                    "酒吧","足疗","按摩","洗浴","演出","展览","采摘","运动健身","游泳","水上乐园"};
            for (int i = 0; i < menuArray.length; i++) {
                NaviTwo naviTwo = new NaviTwo();
                naviTwo.setNaviOneId(naviOne.getNaviOneId());
                naviTwo.setNaviTwoName(menuArray[i]);
                keanuNearDB.saveNaviTwo(naviTwo);
            }
            editor.putBoolean("is_navi_two_writed2",true);
            editor.commit();
        }else if (naviOne.getNaviOneName().equals("酒店")){
            String[] menuArray ={"经济酒店","客栈","旅馆","精品酒店","高档酒店"};
            for (int i = 0; i < menuArray.length; i++) {
                NaviTwo naviTwo = new NaviTwo();
                naviTwo.setNaviOneId(naviOne.getNaviOneId());
                naviTwo.setNaviTwoName(menuArray[i]);
                keanuNearDB.saveNaviTwo(naviTwo);
            }
            editor.putBoolean("is_navi_two_writed3",true);
            editor.commit();
        }else if (naviOne.getNaviOneName().equals("丽人")){
            String[] menuArray ={"美发","美甲","SPA","个性写真","瘦身纤体","瑜伽","舞蹈"};
            for (int i = 0; i < menuArray.length; i++) {
                NaviTwo naviTwo = new NaviTwo();
                naviTwo.setNaviOneId(naviOne.getNaviOneId());
                naviTwo.setNaviTwoName(menuArray[i]);
                keanuNearDB.saveNaviTwo(naviTwo);
            }
            editor.putBoolean("is_navi_two_writed4",true);
            editor.commit();
        }else if (naviOne.getNaviOneName().equals("购物")){
            String[] menuArray ={"服饰","鞋包","家居","数码","家电","食品","钟表","眼镜","珠宝","化妆","母婴","水果"};
            for (int i = 0; i < menuArray.length; i++) {
                NaviTwo naviTwo = new NaviTwo();
                naviTwo.setNaviOneId(naviOne.getNaviOneId());
                naviTwo.setNaviTwoName(menuArray[i]);
                keanuNearDB.saveNaviTwo(naviTwo);
            }
            editor.putBoolean("is_navi_two_writed5",true);
            editor.commit();
        }else if (naviOne.getNaviOneName().equals("生活服务")){
            String[] menuArray ={"汽车服务","体检保健","培训","鲜花婚庆","照片冲印","家具建材","装修设计","婚纱摄影","洗衣","家政"};
            for (int i = 0; i < menuArray.length; i++) {
                NaviTwo naviTwo = new NaviTwo();
                naviTwo.setNaviOneId(naviOne.getNaviOneId());
                naviTwo.setNaviTwoName(menuArray[i]);
                keanuNearDB.saveNaviTwo(naviTwo);
            }
            editor.putBoolean("is_navi_two_writed6",true);
            editor.commit();
        }else if (naviOne.getNaviOneName().equals("结婚")) {
            String[] menuArray = {"婚纱摄影", "婚纱礼服", "婚庆公司", "司仪主持", "彩妆照型"};
            for (int i = 0; i < menuArray.length; i++) {
                NaviTwo naviTwo = new NaviTwo();
                naviTwo.setNaviOneId(naviOne.getNaviOneId());
                naviTwo.setNaviTwoName(menuArray[i]);
                keanuNearDB.saveNaviTwo(naviTwo);
            }
            editor.putBoolean("is_navi_two_writed7",true);
            editor.commit();
        }

    }
    /**
     * 返回按钮
     */
    private void setBackButton(){
        second_nvi_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NaviTwoActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
