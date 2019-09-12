package com.collect.user_luo.mycollect.dialog;

import android.content.Context;
import android.graphics.Color;
import android.widget.PopupWindow;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * VM  不用移动
 * Created by Longer on 2016/10/26.
 */
public  class BaseDialog {

    private SweetAlertDialog mSweetAlertDialog;

    private Context context;

    public BaseDialog(Context context) {
        this.context = context;
    }

    private PopupWindow mPopupWindow;
    public void showLoading(String titleText, int type) {
        if(mSweetAlertDialog!=null){
            mSweetAlertDialog.dismiss();
        }
        mSweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        mSweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mSweetAlertDialog.setTitleText(titleText);
        mSweetAlertDialog.setCancelable(true);
        mSweetAlertDialog.show();
    }

    public void showLoading() {
        if(mSweetAlertDialog!=null){
            if(mSweetAlertDialog.isShowing()){
                mSweetAlertDialog.dismiss();
            }
            mSweetAlertDialog=null;
        }
        mSweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        mSweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mSweetAlertDialog.setCancelable(true);
        mSweetAlertDialog.show();
    }

    public void showLoading(String titleText,String confirmText) {
        if(mSweetAlertDialog!=null){
            mSweetAlertDialog.dismiss();
        }
        mSweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        mSweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mSweetAlertDialog.setTitleText(titleText);
        mSweetAlertDialog.setConfirmText(confirmText);

        mSweetAlertDialog.setCancelable(true);
        mSweetAlertDialog.show();
    }

        public void showLoading(String titleText) {
        SweetAlertDialog mSweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        mSweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mSweetAlertDialog.setTitleText(titleText);


        mSweetAlertDialog.setCancelable(true);
        mSweetAlertDialog.show();
    }

    public void closeLoading() {
        if (mSweetAlertDialog != null && mSweetAlertDialog.isShowing()) {
            mSweetAlertDialog.dismissWithAnimation();
        }
    }








}
