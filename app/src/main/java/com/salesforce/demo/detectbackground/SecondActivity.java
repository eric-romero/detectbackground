package com.salesforce.demo.detectbackground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends Activity {

    @OnClick(R.id.switch_activity)
    public void submit() {
        startActivity(new Intent(this, FirstActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        ButterKnife.bind(this);
    }
}
