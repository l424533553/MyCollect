package leavesc.hello.databindingdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import leavesc.hello.databindingsamples.R;
import leavesc.hello.databindingsamples.databinding.ActivityDatademoBinding;


/**
 * DataBinding
 */
public class DataDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatademoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_datademo);
        binding.setOnClickListener(this);
        mContext = this;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.basic_btn) {
            startActivity(new Intent(mContext, BasicActivity.class));
        } else if (i == R.id.double_bind_btn) {
            startActivity(new Intent(mContext, DoubleBindActivity.class));
        } else if (i == R.id.recycler_view_bind_btn) {
            startActivity(new Intent(mContext, RecyclerViewActivity.class));
        }
    }
}
