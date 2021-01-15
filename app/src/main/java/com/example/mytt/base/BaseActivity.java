package com.example.mytt.base;

import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mytt.dialog.CommonDialog;
import com.example.mytt.okhttp.HttpApi;

public class BaseActivity extends AppCompatActivity implements BaseApplication.MessageCallBack{
    /**
     * 通用加载dialog
     */
    public CommonDialog mCommonLoadDialog;
    //通用Handle
    public BaseApplication.CommonHandler commonHandle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            com.example.mytt.manager.ActivityManager.getInstance().addActivity(BaseActivity.this);
            commonHandle=new BaseApplication.CommonHandler(this,this);
            mCommonLoadDialog=new CommonDialog(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        com.example.mytt.manager.ActivityManager.getInstance().removeActivity(BaseActivity.this);

    }
    public boolean checkResult(int tag, String result) {
        commonHandle.sendEmptyMessage(tag);//用于关闭dialog
        return true;
    }

    @Override
    public void messageCallBack(Message message) {
        switch (message.what){
            case HttpApi.TAG_REQ_ERR:
                mCommonLoadDialog.dismiss();
                Toast.makeText(this,"请求失败",Toast.LENGTH_SHORT).show();
                break;
            default:
                mCommonLoadDialog.dismiss();
                break;
        }
    }
}
