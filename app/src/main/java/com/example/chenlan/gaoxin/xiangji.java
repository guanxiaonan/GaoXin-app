package com.example.chenlan.gaoxin;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
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
 * Created by chenlan on 2018/3/13.
 */

public class xiangji extends AppCompatActivity {
    EditText tv_date,editText,editText1,editText2,editText3;
    String m,a,b,c,d,e,f,g,h,i,j;
    CheckBox c0,c1,c2,c3;
    int mYear, mMonth, mDay;
    final int DATE_DIALOG = 1;
    private List<CheckBox> checkBoxList = new ArrayList<CheckBox>();
    StringBuffer sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiangji);
        final EditText editText=(EditText)findViewById(R.id.editText);
        final EditText editText1=(EditText)findViewById(R.id.editText1);
        final EditText editText2=(EditText)findViewById(R.id.editText2);
        final EditText editText3=(EditText)findViewById(R.id.editText3);
        final CheckBox c0=(CheckBox)findViewById(R.id.c0);
        final CheckBox c1=(CheckBox)findViewById(R.id.c1);
        final CheckBox c2=(CheckBox)findViewById(R.id.c2);
        final CheckBox c3=(CheckBox)findViewById(R.id.c3);
        checkBoxList.add(c0);
        checkBoxList.add(c1);
        checkBoxList.add(c2);
        checkBoxList.add(c3);
        Button b0=findViewById(R.id.b0);
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m= String.valueOf(editText.getText());
                a= String.valueOf(editText2.getText());
                b= String.valueOf(editText3.getText());
                c= String.valueOf(editText1.getText());
                d= String.valueOf(c0.getText());
                e= String.valueOf(c1.getText());
                f= String.valueOf(c2.getText());
                g= String.valueOf(c3.getText());
                i=String.valueOf(tv_date.getText());


                StringBuffer sb = new StringBuffer();

                //遍历集合中的checkBox,判断是否选择，获取选中的文本
                for (CheckBox checkbox : checkBoxList) {
                    if (checkbox.isChecked()){
//                        Log.d("gyn","sb:"+checkbox.getText().toString());
                        sb.append(checkbox.getText().toString() + " ");
                        Toast.makeText(xiangji.this,"提交成功",Toast.LENGTH_LONG).show();
                    }
                }

//                Log.d("gyn","sb=======:"+sb.toString());
                if (sb!=null && "".equals(sb.toString())){
                    Toast.makeText(getApplicationContext(), "请至少选择一个", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_SHORT).show();
                }

                h= sb.toString();
//                Log.d("ghg","==========最终h============"+h);
                postDataDiaocha(view);



            }

        });




        tv_date=(EditText) findViewById(R.id.tv_date);
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG);
            }
        });
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);


    }
    private void postDataDiaocha(View view) {
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        Log.d("shuchu","监测h的值："+h);
        String jsonStr = "{\"前三年项目累计\":"+"\""+c+"\",\"项目名称\":"+"\""+a+"\",\"投入资金\":"+"\""+b+"\",\"立项时间\":\""+i+"\",\"内容用途领域成果\":\""+m+"\"," +
                "\"材料\":\""+h+"\"}";//json数据.\
        Log.e("11","==============jsonStr="+jsonStr);
        RequestBody body = RequestBody.create(JSON,jsonStr);
        Request request = new Request.Builder()
                .url("http://120.78.3.68:2018/project_tijiao")
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
    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, mYear, mMonth, mDay);
        }
        return null;
    }
    public void display() {
        tv_date.setText(new StringBuffer().append(mMonth + 1).append("-").append(mDay).append("-").append(mYear).append(" "));
    }

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            display();
        }
    };

}



