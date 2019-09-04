package com.collect.user_luo.mycollect.activity.picture.fresco;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.collect.user_luo.mycollect.R;
import com.facebook.drawee.view.SimpleDraweeView;


/*
<com.facebook.drawee.view.SimpleDraweeView
        android:id="@+id/my_image_view"
        android:layout_width="20dp"
        fresco:viewAspectRatio="1.33"   // 图片以特定的宽高比例显示，例如 4:3
        android:layout_height="20dp"
        fresco:fadeDuration="300"
        fresco:actualImageScaleType="focusCrop"
        fresco:placeholderImage="@color/wait_color"
        fresco:placeholderImageScaleType="fitCenter"
        fresco:failureImage="@drawable/error"
        fresco:failureImageScaleType="centerInside"
        fresco:retryImage="@drawable/retrying"
        fresco:retryImageScaleType="centerCrop"
        fresco:progressBarImage="@drawable/progress_bar"
        fresco:progressBarImageScaleType="centerInside"
        fresco:progressBarAutoRotateInterval="1000"
        fresco:backgroundImage="@color/blue"
        fresco:overlayImage="@drawable/watermark"
        fresco:pressedStateOverlayImage="@color/red"
        fresco:roundAsCircle="false"
        fresco:roundedCornerRadius="1dp"
        fresco:roundTopLeft="true"
        fresco:roundTopRight="false"
        fresco:roundBottomLeft="false"
        fresco:roundBottomRight="true"
        fresco:roundWithOverlayColor="@color/corner_color"
        fresco:roundingBorderWidth="2dp"
        fresco:roundingBorderColor="@color/border_color"
        />
*/
public class FrescoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);

        Uri uri = Uri.parse("http://img.zcool.cn/community/016c4f5721807732f875a3992ba4d6.jpg");
        SimpleDraweeView draweeView = findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);

        ImageView imageView= findViewById(R.id.imageView);

    }
}
