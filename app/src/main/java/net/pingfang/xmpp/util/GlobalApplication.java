package net.pingfang.xmpp.util;

import android.app.Application;

import net.pingfang.xmpp.R;

import java.util.Locale;

/**
 * Created by gongguopei87@gmail.com on 2015/7/28.
 */
public class GlobalApplication extends Application {

    public static final String ACTION_INTENT_MESSAGE_INCOMING = "ACTION_INTENT_MESSAGE_INCOMING";
    public static final String ACTION_INTENT_IMAGE_INCOMING = "ACTION_INTENT_IMAGE_INCOMING";
    public static final String ACTION_INTENT_VOICE_INCOMING = "ACTION_INTENT_VOICE_INCOMING";
    public static final String ACTION_INTENT_VIDEO_INCOMING = "ACTION_INTENT_VIDEO_INCOMING";

    public static final String VOICE_FILE_NAME_PREFIX = "VOICE_";
    public static final String VOICE_FILE_NAME_SUFFIX = ".3gp";

    private Locale myLocale;
    SharedPreferencesHelper helper;

    @Override
    public void onCreate() {
        super.onCreate();

        helper = SharedPreferencesHelper.newInstance(getApplicationContext());
        loadLocale();
    }

    public void changeLang(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    public void saveLocale(String lang) {
        String langPref = getResources().getString(R.string.prefs_language);
        helper.putStringValue(langPref,lang);
    }


    public void loadLocale() {
        String langPref = getResources().getString(R.string.prefs_language);
        String language = helper.getStringValue(langPref,"zh");
        changeLang(language);
    }
}
