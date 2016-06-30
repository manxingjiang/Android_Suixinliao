package com.tencent.qcloud.timchat.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;

import com.tencent.TIMManager;
import com.tencent.TIMOfflinePushSettings;
import com.tencent.TIMValueCallBack;
import com.tencent.qcloud.timchat.R;
import com.tencent.qcloud.ui.LineControllerView;

public class MessageNotifySettingActivity extends Activity {

    private String TAG = "MessageNotifySettingActivity";
    TIMOfflinePushSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_notify_setting);
        final Uri notifyMusic = Uri.parse("android.resource://com.tencent.qcloud.timchat/" + R.raw.dudulu);
        TIMManager.getInstance().getOfflinePushSettings(new TIMValueCallBack<TIMOfflinePushSettings>() {
            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "get offline push setting error " + s);
            }

            @Override
            public void onSuccess(TIMOfflinePushSettings timOfflinePushSettings) {
                settings = timOfflinePushSettings;
            }
        });
        LineControllerView messagePush = (LineControllerView) findViewById(R.id.messagePush);
        messagePush.setCheckListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (settings != null) {
                    settings.setEnabled(isChecked);
                    TIMManager.getInstance().initOfflinePushSettings(settings);
                }
            }
        });
        LineControllerView c2cMusic = (LineControllerView) findViewById(R.id.c2cMusic);
        c2cMusic.setCheckListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (settings != null){
                    settings.setC2cMsgRemindSound(isChecked ? notifyMusic : null);
                    TIMManager.getInstance().initOfflinePushSettings(settings);
                }
            }
        });
        LineControllerView groupMusic = (LineControllerView) findViewById(R.id.groupMusic);
        groupMusic.setCheckListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (settings != null){
                    settings.setGroupMsgRemindSound(isChecked?notifyMusic:null);
                    TIMManager.getInstance().initOfflinePushSettings(settings);
                }
            }
        });
    }
}
