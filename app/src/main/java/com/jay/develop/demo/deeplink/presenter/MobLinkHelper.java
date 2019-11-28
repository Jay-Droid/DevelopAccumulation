package com.jay.develop.demo.deeplink.presenter;

import com.mob.moblink.ActionListener;
import com.mob.moblink.MobLink;
import com.mob.moblink.Scene;
import com.mob.moblink.SceneRestorable;

import java.util.HashMap;

public class MobLinkHelper {

    private OnGetMobIdListener listener;

    public MobLinkHelper(OnGetMobIdListener listener) {
        this.listener = listener;
    }

    public void getMobId(String path, HashMap<String, Object> params) {
        Scene s = new Scene();
        s.setPath(path);
        s.setParams(params);
        MobLink.getMobID(s, new ActionListener<String>() {
            @Override
            public void onResult(String mobID) {
                if (listener != null) {
                    listener.onMobIdGot(mobID);
                }
            }

            @Override
            public void onError(Throwable t) {
                if (listener != null) {
                    listener.onMobIdError(t);
                }
            }
        });
    }


    public interface OnGetMobIdListener extends SceneRestorable {

        void onMobIdGot(String mobId);

        void onMobIdError(Throwable t);
    }


}
