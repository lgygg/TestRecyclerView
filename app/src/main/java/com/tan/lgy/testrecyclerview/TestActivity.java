package com.tan.lgy.testrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGY on 2017-6-16.
 */

public class TestActivity extends Activity {
    private RecyclerView recyclerView = null;
    private List<Bean> beans = null;
    private LinearLayoutManager linearLayoutManager = null;
    private MyAdapter myAdapter = null;
    private Button bt_add = null;
    private static int id = 100;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        initData();
        initView();


    }

    private void initView()
    {
        recyclerView = (RecyclerView) findViewById(R.id.list_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        myAdapter = new MyAdapter(this);
        myAdapter.setBeans(beans);
        myAdapter.setDelAndAddListner(new MyAdapter.DelAndAddListner() {

            @Override
            public void delAction() {

            }
        });
        recyclerView.setAdapter(myAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        bt_add = (Button) findViewById(R.id.add);
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bean bean = new Bean();
                bean.setAge(100);
                bean.setName("lgy");
                bean.setSex("m");
                myAdapter.addItem(bean);
                recyclerView.scrollToPosition(0);//recyclerview滚动到新加item处
            }
        });
    }

    private void initData()
    {
        beans = new ArrayList<Bean>();
        for (int i = 0; i < 99; i++) {
            Bean bean = new Bean();
            bean.setName("lgy" + i);
            bean.setAge(i);
            bean.setSex("男人");
            beans.add(bean);
        }
    }
}
