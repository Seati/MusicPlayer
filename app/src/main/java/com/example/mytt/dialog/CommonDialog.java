package com.example.mytt.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mytt.R;

public class CommonDialog extends Dialog implements View.OnClickListener {
    private View mLayout;

    public CommonDialog(Context context) {
        super(context);
        mLayout= LayoutInflater.from(context).inflate(R.layout.dialog_common,null);
        ImageView iv_loading=(ImageView)mLayout.findViewById(R.id.iv_loading);
        Glide.with(context).load(R.mipmap.gif_load).into(iv_loading);
        setCanceledOnTouchOutside(false);
        getWindow().setBackgroundDrawable(new ColorDrawable());
        getWindow().setDimAmount(0);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void show() {
        super.show();
        getWindow().setContentView(mLayout);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
