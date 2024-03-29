package leavesc.hello.databindingsamples;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import leavesc.hello.databindingsamples.databinding.ActivityMain2Binding;
import leavesc.hello.databindingsamples.model.ObservableGoods;
import leavesc.hello.databindingsamples.model.User;

/**
 * 作者：leavesC
 * 时间：2019/2/27 21:36
 * 描述：
 * GitHub：https://github.com/leavesC
 * Blog：https://www.jianshu.com/u/9df45b87cfdf
 */
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMain2Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_main2);

        User user = new User("leavesC", "123456");
        binding.setUserInfo(user);

        final ObservableGoods goods = new ObservableGoods("code", "hi", 23);
        binding.setGoods(goods);

        findViewById(R.id.btnTest2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goods.getDetails().set("详细细节");
            }
        });
    }


}
