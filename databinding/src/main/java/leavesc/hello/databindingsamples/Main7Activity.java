package leavesc.hello.databindingsamples;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Toast;

import java.util.Objects;

import leavesc.hello.databindingsamples.databinding.ActivityMain7Binding;
import leavesc.hello.databindingsamples.databinding.ViewStubBinding;
import leavesc.hello.databindingsamples.model.User;

/**
 * 作者：leavesC
 * 时间：2019/2/27 21:36
 * 描述：
 * GitHub：https://github.com/leavesC
 * Blog：https://www.jianshu.com/u/9df45b87cfdf
 */
public class Main7Activity extends    AppCompatActivity {

    private ActivityMain7Binding binding;

    private User user;

    private static final String TAG = "Main7Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main7);
        binding.setHandler(new Handler());
        user = new User("leavesC", "123456");
        binding.setUserInfo(user);

        binding.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                //如果在 xml 中没有使用 bind:userInfo="@{userInf}" 对 viewStub 进行数据绑定
                //那么可以在此处进行手动绑定
                ViewStubBinding viewStubBinding = DataBindingUtil.bind(inflated);
                Toast.makeText(Main7Activity.this,"填充 一",Toast.LENGTH_SHORT).show();
                if (viewStubBinding != null) {
                    User user = new User("leavesBBBB", "123456");
                    // bind:userInfo="@{userInf} 如果配置了，xml文件中的值会覆盖 代码中设置的值
                    viewStubBinding.setUserInfo(user);
//                    Toast.makeText(Main7Activity.this,"填充 二",Toast.LENGTH_SHORT).show();
                }
                Log.e(TAG, "onInflate");
            }
        });
    }

    public class Handler {

        public void onClick(View v) {
            if (!binding.viewStub.isInflated()) {
                //填充数据
                Objects.requireNonNull(binding.viewStub.getViewStub()).inflate();
            }
//            Toast.makeText(Main7Activity.this,"点击了按钮",Toast.LENGTH_SHORT).show();
        }

    }

}
