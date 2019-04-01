package com.salesforce.demo.detectbackground;

import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class FirstActivity extends Activity {

    @OnClick(R.id.switch_activity)
    public void submit() {
        startActivity(new Intent(this, SecondActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        ButterKnife.bind(this);
    }
}
