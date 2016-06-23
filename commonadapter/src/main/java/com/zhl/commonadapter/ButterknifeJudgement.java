package com.zhl.commonadapter;

/**
 * Created by zhl on 16/6/23.
 * 判断是否支持ButterKnife
 */
public class ButterKnifeJudgement {

    public static boolean IS_SUPPORT = false;

    static {
        boolean isSupport;
        try {
            Class.forName("butterknife.ButterKnife");
            isSupport = true;
        } catch (ClassNotFoundException e) {
            isSupport = false;
        }

        IS_SUPPORT = isSupport;
    }
}
