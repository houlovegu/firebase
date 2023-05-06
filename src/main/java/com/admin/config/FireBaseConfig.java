package com.admin.config;

import cn.hutool.core.io.FileUtil;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class FireBaseConfig {
    static final String AUTH_FILENAME = "systemservice-617a7-firebase-adminsdk-c7bqc-ba1866595c.json";

    public static FirebaseMessaging FIREBASEMESSAGING = null;
    static {

        try {
            InputStream serviceAccount = new ByteArrayInputStream(getFirebaseJson());
            GoogleCredentials googleCredentials = null;

                googleCredentials = GoogleCredentials.fromStream(serviceAccount);

            FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(googleCredentials)
                        .setDatabaseUrl("https://fcmdb.firebaseio.com")
                        .build();
            FirebaseApp defaultApp= FirebaseApp.initializeApp(options);
            FIREBASEMESSAGING = FirebaseMessaging.getInstance(defaultApp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] getFirebaseJson() {
        try {
            Resource resource = new DefaultResourceLoader().getResource("classpath:crets/" + AUTH_FILENAME);
            BufferedInputStream  bis = new BufferedInputStream (resource.getInputStream());
            byte[] buffer = new byte[4096];
            int i = bis.read(buffer);
            while (i != -1) {
                i = bis.read(buffer);
            }
            if (bis != null) {
                bis.close();
            }
            return buffer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
