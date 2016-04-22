package com.zhangzhenzhong1.tools.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import com.zhangzhenzhong1.tools.R;
import com.zhangzhenzhong1.tools.config.AppSetting;
import com.zhangzhenzhong1.tools.utils.ClearCacheTask;

/**
 *  设置界面
 */
public class SettingFragment extends PreferenceFragment
{


    public static SettingFragment newInstance()
    {
        return new SettingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getPreferenceManager().setSharedPreferencesName("setting");
        addPreferencesFromResource(R.xml.setting);

        //设置文字字体大小
        getPreferenceScreen().findPreference("FontSize").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue)
            {

                AppSetting.getSetting().setFontSize(Integer.parseInt((String)newValue));
                return true;
            }
        });
    }



    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference)
    {

        //如果选中了清除缓存
        if (preference.getKey().equals("clearCache"))
        {
            new ClearCacheTask().execute();
            return true;
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }


}
