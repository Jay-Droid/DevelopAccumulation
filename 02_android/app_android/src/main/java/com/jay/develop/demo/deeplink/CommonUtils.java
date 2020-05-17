package com.jay.develop.demo.deeplink;

import android.app.Activity;

import com.mob.moblink.MobLink;

import java.util.HashMap;
import java.util.Map;

public class CommonUtils {

    /**
     * 标识, 连接是不是测试服
     */
    public static final boolean DEBUGGABLE = MobLink.DEBUGGABLE;

    public static final String[] MAIN_PATH_ARR = {"/demo/a", "/demo/b", "/demo/c", "/demo/d"};
    public static final String NEWS_PATH = "/scene/news";
    public static final String NOVEL_PATH = "/scene/novel";
    public static final String GAME_PATH = "/scene/game";
    public static final String MATCH_PATH = "/relationship";
    public static final String WAKEUP_PATH = "/demo";
    public static final String LOCAL_INVITE_PATH = "/invite/local";
    public static final String SHARE_INVITE_PATH = "/invite/share";

    public static final int SCENE_NEWS = 2001;
    public static final int SCENE_NOVEL = 2002;
    public static final int SCENE_GAME = 2003;
    public static final int SCENE_LOCAL_INVITE = 2004;
    public static final int SCENE_SHARE_INVITE = 2005;
    public static final int SCENE_MATCH = 2006;

    public static final Map<String, Class<? extends Activity>> PATH_SERVER_MAP = new HashMap<String, Class<? extends Activity>>();
    public static final Map<String, Class<? extends Activity>> PATH_MAP_LOCAL = new HashMap<String, Class<? extends Activity>>();

    static {
        PATH_MAP_LOCAL.put(NEWS_PATH, DeepLinkActivity.class);
        PATH_MAP_LOCAL.put(NOVEL_PATH, DeepLinkWebActivity.class);
        PATH_MAP_LOCAL.put(GAME_PATH, DeepLinkActivity.class);
        PATH_MAP_LOCAL.put(MATCH_PATH, DeepLinkActivity.class);
        PATH_MAP_LOCAL.put(WAKEUP_PATH, DeepLinkActivity.class);
        PATH_MAP_LOCAL.put(LOCAL_INVITE_PATH, DeepLinkActivity.class);
        PATH_MAP_LOCAL.put(SHARE_INVITE_PATH, DeepLinkActivity.class);
    }


}
