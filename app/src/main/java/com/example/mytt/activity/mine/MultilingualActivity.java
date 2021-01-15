package com.example.mytt.activity.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mytt.R;
import com.example.mytt.activity.MainActivity;
import com.example.mytt.adapter.MultilingualAdapter;
import com.example.mytt.base.BaseActivity;
import com.example.mytt.manager.ActivityManager;
import com.example.mytt.utils.AppUtils;
import com.example.mytt.utils.LanguageUtils;

public class MultilingualActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener {
    private String[]datalist={"简体中文","English"};
    private MultilingualAdapter multilingualAdapter;
    private ListView laguage_list;
    private ImageView back;
    private TextView tv_title,tv_select_finish;
    private int language;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multilingual);
        language= LanguageUtils.getLocal(this);
        init();
    }

    private void init() {
        back=(ImageView)findViewById(R.id.ic_back);
        back.setOnClickListener(this);
        tv_title=(TextView) findViewById(R.id.tv_title);
        tv_title.setText(R.string.mine_language);
        tv_select_finish=(TextView) findViewById(R.id.tv_select_finish);
        tv_select_finish.setVisibility(View.VISIBLE);
        tv_select_finish.setOnClickListener(this);
        laguage_list=(ListView)findViewById(R.id.language_list);
        multilingualAdapter=new MultilingualAdapter(this,datalist);
        laguage_list.setAdapter(multilingualAdapter);
        laguage_list.setOnItemClickListener(this);
        multilingualAdapter.setSelect(LanguageUtils.getLocal(this));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        multilingualAdapter.setSelect(i);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ic_back:
                finish();
                break;
            case R.id.tv_select_finish://选择完成
                if (language!=multilingualAdapter.getIsSelect()){
                    LanguageUtils.updateLocal(this,multilingualAdapter.getIsSelect());
                    LanguageUtils.setLocal(this,multilingualAdapter.getIsSelect());
                    ActivityManager.getInstance().finishAllActivity();
                    startActivity(new Intent(MultilingualActivity.this, MainActivity.class));
                }
                break;
        }
    }
}
