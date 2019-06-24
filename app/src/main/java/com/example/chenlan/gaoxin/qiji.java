package com.example.chenlan.gaoxin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;



import com.bigkoo.pickerview.OptionsPickerView;
import com.example.chenlan.gaoxin.bean.JsonBean;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

import javax.sql.StatementEvent;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by chenlan on 2018/2/25.
 */

public class qiji extends AppCompatActivity {

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    EditText editText,editText2,editText3,editText4,editText6,editText7,editText8; String m,a,b,c,d,e,f,g,h,i,j;
    TextView textView10;
    Button b0;

    TextView selectAddressTv;
    private boolean isLoaded;



    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    Toast.makeText(qiji.this,"正在加载地址选择器，请稍后再试",Toast.LENGTH_SHORT).show();

                    break;
                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    Toast.makeText(qiji.this,"地址选择器加载失败",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qiji);

        final EditText editText=(EditText)findViewById(R.id.editText);
        final EditText editText2=(EditText)findViewById(R.id.editText2);
        final EditText editText3=(EditText)findViewById(R.id.editText3);
        final EditText editText4=(EditText)findViewById(R.id.editText4);
        final EditText editText6=(EditText)findViewById(R.id.editText6);
        final EditText editText7=(EditText)findViewById(R.id.editText7);
        final EditText editText8=(EditText)findViewById(R.id.editText8);
        final TextView textView10=(TextView)findViewById(R.id.textView10);



        final Button b0=findViewById(R.id.b0);
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m= String.valueOf(editText.getText());
                a= String.valueOf(editText2.getText());
                b= String.valueOf(editText3.getText());
                c= String.valueOf(editText4.getText());
                d= String.valueOf(editText6.getText());
                e= String.valueOf(editText7.getText());
                f= String.valueOf(editText8.getText());
                g= String.valueOf(textView10.getText());
                i= String.valueOf(selectAddressTv.getText());
                postDataDiaocha(view);
                Toast.makeText(qiji.this,"提交成功",Toast.LENGTH_LONG).show();


            }
        });


        selectAddressTv = findViewById(R.id.select_address_tv);
        selectAddressTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickerView();
            }
        });
        initdata();
        initView();

    }
    private void postDataDiaocha(View view) {
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        String jsonStr = "{\"单位名称\":"+"\""+m+"\",\"法定代表人\":"+"\""+a+"\",\"通讯地址\":"+"\""+i+"\",\"详细地址\":\""+b+"\"," +
                "\"联系人\":\""+c+"\",\"联系电话\":"+"\""+d+"\",\"注册资金\":\""+e+"\"," +
                "\"上一年销售额\":\""+f+"\",\"企业规模\":\""+g+"\"}";//json数据.\
        Log.e("11","==============jsonStr="+jsonStr);
        RequestBody body = RequestBody.create(JSON,jsonStr);
        Request request = new Request.Builder()
                .url("http://120.78.3.68:2018/company_tijiao")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("==","==========response="+response.toString());
                if(response.isSuccessful()){//回调的方法执行在子线程。
                    Log.d("kwwl","获取数据成功了");
                    Log.d("kwwl","response.code()=="+response.code());
                    Log.d("kwwl","response.body().string()=="+response.body().string());
                }else{
                    Log.d("kwwl","========false==========");
                }
            }
        });
    }
    private void initView() {
        editText8 = (EditText) this.findViewById(R.id.editText8);
       textView10 = (TextView) this.findViewById(R.id.textView10);
        //设置EditText文本框输入监听事件
       editText8.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void afterTextChanged(Editable editable) {
               String string=editable.toString();
               if (!TextUtils.isEmpty(string)) {
                   long b = Long.parseLong(string);
                   if (b < 50000000) {
                       textView10.setText("五千万以下");
                   } else if (b >= 50000000 && b <= 200000000) {
                       textView10.setText("5千万-2亿元");
                   } else {
                       textView10.setText("2亿以上");
                   }
               }else {
                   textView10.setText("");

               }

           }
       });
    }




        private void initdata() {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                // 写子线程中的操作,解析省市区数据
                initJsonData();
            }
        });
        thread.start();
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this,"province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i=0;i<jsonBean.size();i++){//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c=0; c<jsonBean.get(i).getCityList().size(); c++){//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        ||jsonBean.get(i).getCityList().get(c).getArea().size()==0) {
                    City_AreaList.add("");
                }else {

                    for (int d=0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);

        }
        return detail;
    }



    private void showPickerView() {// 弹出选择器

        OptionsPickerView  pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()+
                        options2Items.get(options1).get(options2)+
                        options3Items.get(options1).get(options2).get(options3);

                selectAddressTv.setText(tx);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
        pvOptions.show();
    }
}