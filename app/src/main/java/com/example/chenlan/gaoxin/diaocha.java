package com.example.chenlan.gaoxin;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

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

public class diaocha extends AppCompatActivity {
     EditText editText;
     String m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diaocha);
//        Diaochadata diaocha=new Diaochadata();
        final TextView text6 = (TextView)findViewById(R.id.text6);
        final TextView text8 = (TextView)findViewById(R.id.text8);
        final EditText editText = (EditText) findViewById(R.id.editText);


        final Button b0 = findViewById(R.id.b0);
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                b0_OnClick(view);
                m= editText.getText().toString();
                postDataDiaocha(view);
                Toast.makeText(diaocha.this,"提交成功",Toast.LENGTH_LONG).show();

            }
        });


    }

    private void b0_OnClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                    Request request = new Request.Builder()
                            .url("http://192.168.1.110:2018/test")//请求接口。如果需要传参拼接到接口后面。
                            .build();//创建Request 对象
                    Response response = null;
                    response = client.newCall(request).execute();//得到Response 对象
                    if (response.isSuccessful()) {
                        Log.d("kwwl","response.code()=="+response.code());
                        Log.d("kwwl","response.message()=="+response.message());
                        Log.d("kwwl","res=="+response.body().string());
                        //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                    }else{
                        Log.d("kwwl","res=====================");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void postDataDiaocha(View view) {
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        String jsonStr = "{\"调查人员\":\"陈兰\",\"调查人员联系电话\":\"15990018354\",\"陪同人员\":\"官易楠\",\"陪同人员联系电话\":\"15988817534\",\"备注\":"+"\""+m+"\"}";//json数据.\
        Log.e("11","==============jsonStr="+jsonStr);
        RequestBody body = RequestBody.create(JSON,jsonStr);
        Request request = new Request.Builder()
                .url("http://120.78.3.68:2018/tijiao")
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
}
