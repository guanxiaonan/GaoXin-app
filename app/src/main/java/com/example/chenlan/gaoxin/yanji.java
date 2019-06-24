package com.example.chenlan.gaoxin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by chenlan on 2018/3/28.
 */

public class yanji extends AppCompatActivity {
    private List<CheckBox> checkBoxList_ly = new ArrayList<CheckBox>();
    private List<CheckBox> checkBoxList1 = new ArrayList<CheckBox>();
    private List<CheckBox> checkBoxList2 = new ArrayList<CheckBox>();
    private List<CheckBox> checkBoxList3 = new ArrayList<CheckBox>();
    private List<CheckBox> checkBoxList4 = new ArrayList<CheckBox>();
    StringBuffer sb, gl1, gl2, gl3, gl4;
    String a, b, c, d, e, f, g, h, i, j, k, l, m, n, p, n1, n2, n3, n4;
    CheckBox c0, c1, c2, c3, c4, c5, c6, c7, a0, a1, a2, a3, a4, a5, a6, a7;
    EditText editText, editText2, editText3, editText4, editText6, editText7, editText9, editText10, editText11, editText12, editText13, editText14, editText15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yanji);
        Button b0 = findViewById(R.id.b0);
        final EditText editText = (EditText) findViewById(R.id.editText);
        final EditText editText2 = (EditText) findViewById(R.id.editText2);
        final EditText editText3 = (EditText) findViewById(R.id.editText3);
        final EditText editText4 = (EditText) findViewById(R.id.editText4);
        final EditText editText6 = (EditText) findViewById(R.id.editText6);
        final EditText editText7 = (EditText) findViewById(R.id.editText7);
        final EditText editText9 = (EditText) findViewById(R.id.editText9);
        final EditText editText10 = (EditText) findViewById(R.id.editText10);
        final EditText editText11 = (EditText) findViewById(R.id.editText11);
        final EditText editText12 = (EditText) findViewById(R.id.editText12);
        final EditText editText13 = (EditText) findViewById(R.id.editText13);
        final EditText editText14 = (EditText) findViewById(R.id.editText14);
        final EditText editText15 = (EditText) findViewById(R.id.editText15);
        //领域的多选框
        final CheckBox c0 = (CheckBox) findViewById(R.id.c0);
        final CheckBox c1 = (CheckBox) findViewById(R.id.c1);
        final CheckBox c2 = (CheckBox) findViewById(R.id.c2);
        final CheckBox c3 = (CheckBox) findViewById(R.id.c3);
        final CheckBox c4 = (CheckBox) findViewById(R.id.c4);
        final CheckBox c5 = (CheckBox) findViewById(R.id.c5);
        final CheckBox c6 = (CheckBox) findViewById(R.id.c6);
        final CheckBox c7 = (CheckBox) findViewById(R.id.c7);
        //管理制度1
        final CheckBox a0 = (CheckBox) findViewById(R.id.a0);
        final CheckBox a1 = (CheckBox) findViewById(R.id.a1);
        final CheckBox a2 = (CheckBox) findViewById(R.id.a2);
        //管理制度2
        final CheckBox a3 = (CheckBox) findViewById(R.id.a3);
        final CheckBox a4 = (CheckBox) findViewById(R.id.a4);
        //管理制度3
        final CheckBox a5 = (CheckBox) findViewById(R.id.a5);
        final CheckBox a6 = (CheckBox) findViewById(R.id.a6);
        //管理制度4
        final CheckBox a7 = (CheckBox) findViewById(R.id.a7);
        checkBoxList_ly.add(c0);
        checkBoxList_ly.add(c1);
        checkBoxList_ly.add(c2);
        checkBoxList_ly.add(c3);
        checkBoxList_ly.add(c4);
        checkBoxList_ly.add(c5);
        checkBoxList_ly.add(c6);
        checkBoxList_ly.add(c7);

        checkBoxList1.add(a0);
        checkBoxList1.add(a1);
        checkBoxList1.add(a2);

        checkBoxList2.add(a3);
        checkBoxList2.add(a4);

        checkBoxList3.add(a5);
        checkBoxList3.add(a6);

        checkBoxList4.add(a7);

        final RadioGroup sexRadioGroup = (RadioGroup) findViewById(R.id.radioGroupID);
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = String.valueOf(editText.getText());
                b = String.valueOf(editText2.getText());
                c = String.valueOf(editText3.getText());
                d = String.valueOf(editText4.getText());
                e = String.valueOf(editText6.getText());
                f = String.valueOf(editText7.getText());
                g = String.valueOf(editText9.getText());
                h = String.valueOf(editText10.getText());
                i = String.valueOf(editText11.getText());
                j = String.valueOf(editText12.getText());
                k = String.valueOf(editText13.getText());
                l = String.valueOf(editText14.getText());
                m = String.valueOf(editText15.getText());
                StringBuffer sb = new StringBuffer();
                StringBuffer gl1 = new StringBuffer();
                StringBuffer gl2 = new StringBuffer();
                StringBuffer gl3 = new StringBuffer();
                StringBuffer gl4 = new StringBuffer();
                for (int i = 0; i < sexRadioGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) sexRadioGroup.getChildAt(i);
                    if (radioButton.isChecked()) {
                        //                        dx.append(radioButton.getText().toString() + " ");
                        Log.i("tag", "lsn 单选按钮，您的程度是：" + radioButton.getText());
                        p = radioButton.getText().toString();
                    }
                }
                Log.d("-----gyn----", "==============run==============");
                //                p=dx.toString();
                //领域的复选框
                for (CheckBox checkbox : checkBoxList_ly) {
                    if (checkbox.isChecked()) {
                        //                        Log.d("gyn","sb:"+checkbox.getText().toString());
                        sb.append(checkbox.getText().toString() + " ");
                        Toast.makeText(yanji.this, "提交成功", Toast.LENGTH_LONG).show();
                    }
                }
                //                Log.d("gyn","sb=======:"+sb.toString());
                if (sb != null && "".equals(sb.toString())) {
                    Toast.makeText(getApplicationContext(), "请至少选择一个", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_SHORT).show();
                }
                n = sb.toString();
                for (CheckBox checkbox : checkBoxList1) {
                    if (checkbox.isChecked()) {
                        //                        Log.d("gyn","sb:"+checkbox.getText().toString());
                        gl1.append(checkbox.getText().toString() + " ");
                        Toast.makeText(yanji.this, "提交成功", Toast.LENGTH_LONG).show();
                    }
                }
                n1 = gl1.toString();
                for (CheckBox checkbox : checkBoxList2) {
                    if (checkbox.isChecked()) {
                        //                        Log.d("gyn","sb:"+checkbox.getText().toString());
                        gl2.append(checkbox.getText().toString() + " ");
                        Toast.makeText(yanji.this, "提交成功", Toast.LENGTH_LONG).show();
                    }
                }
                n2 = gl2.toString();
                for (CheckBox checkbox : checkBoxList3) {
                    if (checkbox.isChecked()) {
                        //                        Log.d("gyn","sb:"+checkbox.getText().toString());
                        gl3.append(checkbox.getText().toString() + " ");
                        Toast.makeText(yanji.this, "提交成功", Toast.LENGTH_LONG).show();
                    }
                }
                n3 = gl3.toString();
                for (CheckBox checkbox : checkBoxList4) {
                    if (checkbox.isChecked()) {
                        //                        Log.d("gyn","sb:"+checkbox.getText().toString());
                        gl4.append(checkbox.getText().toString() + " ");
                        Toast.makeText(yanji.this, "提交成功", Toast.LENGTH_LONG).show();
                    }
                }
                n4 = gl4.toString();

                //                Log.d("ghg","==========最终h============"+h);
                postDataDiaocha(view);

            }
        });


    }

    private void postDataDiaocha(View view) {
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        Log.d("shuchu", "监测h的值：" + h);
        String jsonStr = "{\"领域\":" + "\"" + n + "\",\"程度\":\"" + p + "\",\"博士\":" + "\"" + a + "\",\"硕士\":" + "\"" + b + "\",\"本科\":\"" + c + "\",\"大专及以下\":\"" + d + "\"," +
                "\"资产增长率\":\"" + e + "\",\"销售增长率\":\"" + f + "\",\"国外专利\":\"" + g + "\",\"国内发明专利\":\"" + h + "\"," +
                "\"实用新型\":\"" + i + "\",\"软著\":\"" + j + "\",\"外观设计\":\"" + k + "\",\"集成电路\":\"" + l + "\",\"新品种\":\"" + m + "\"," +
                "\"管理制度1\":\"" + n1 + "\",\"管理制度2\":\"" + n2 + "\",\"管理制度3\":\"" + n3 + "\",\"管理制度4\":\"" + n4 + "\"}";//json数据.\
        Log.e("11", "==============jsonStr=" + jsonStr);
        RequestBody body = RequestBody.create(JSON, jsonStr);
        Request request = new Request.Builder()
                .url("http://192.168.1.110:2018/yanji_tijiao")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("==", "==========response=" + response.toString());
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    Log.d("kwwl", "获取数据成功了");
                    Log.d("kwwl", "response.code()==" + response.code());
                    Log.d("kwwl", "response.body().string()==" + response.body().string());
                } else {
                    Log.d("kwwl", "========false==========");
                }
            }
        });
    }

    private void postDataWithParame(View view) {
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        String jsonStr = "{\"username\":\"lisi\",\"nickname\":\"李四\"}";//json数据.\
        RequestBody body = RequestBody.create(JSON, jsonStr);
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
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    Log.d("kwwl", "获取数据成功了");
                    Log.d("kwwl", "response.code()==" + response.code());
                    Log.d("kwwl", "response.body().string()==" + response.body().string());
                } else {
                    Log.d("kwwl", "========false==========");
                }
            }
        });
    }
}
