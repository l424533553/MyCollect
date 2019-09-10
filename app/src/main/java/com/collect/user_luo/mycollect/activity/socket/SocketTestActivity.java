package com.collect.user_luo.mycollect.activity.socket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.collect.user_luo.R;


public class SocketTestActivity extends AppCompatActivity {


    private Button b;
    private TextView t;
    public  static String s;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_test);
        b = (Button) findViewById(R.id.send);
        t = (TextView) findViewById(R.id.textview);
        editText = (EditText) findViewById(R.id.edittext);
        b.setOnClickListener(new View.OnClickListener() {  //设置点击按钮
            public void onClick(View v) {
                s = editText.getText().toString(); //获取editText的文本
                new Thread(new Runnable() {   //新的线程来执行客户端
                    @Override
                    public void run() {    //运行run方法
//                        Client c = new Client(s); //新建客户端对象
//                        c.send();   //调用send方法
                    }
                }).start();   //开始线程
            }
        });
    }


}
