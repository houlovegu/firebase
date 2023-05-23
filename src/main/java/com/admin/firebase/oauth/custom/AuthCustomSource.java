package com.admin.firebase.oauth.custom;

import me.zhyd.oauth.config.AuthSource;

public enum AuthCustomSource implements AuthSource {

    /**
     * 亚马逊授权
     **/
    AMAZON {
        @Override
        public String authorize() {
            return "https://www.amazon.com/ap/oa";
        }

        @Override
        public String accessToken() {
            return "https://api.amazon.com/auth/o2/token";
        }

        @Override
        public String userInfo() {
            return "https://api.amazon.com/user/profile";
        }
    },

    /**
     * google认证
     **/
    GOOGLE {
        @Override
        public String authorize() {
            return "https://accounts.google.com/o/oauth2/auth";
        }

        @Override
        public String accessToken() {
            return "https://www.googleapis.com/oauth2/v3/token";
        }

        @Override
        public String userInfo() {
            return "https://www.googleapis.com/oauth2/v2/userinfo";
        }
    }
}
