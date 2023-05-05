package com.admin.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class FireBaseConfig {
    static final String AUTH_FILENAME = "systemservice-617a7-firebase-adminsdk-c7bqc-ba1866595c.json";

    public static FirebaseMessaging FIREBASEMESSAGING = null;
    static {

        try {
            InputStream serviceAccount = new ByteArrayInputStream(getFirebaseJson().getBytes(StandardCharsets.UTF_8));
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

    private static String getFirebaseJson() {
        File file;
        BufferedReader br = null;
        try {
            file = ResourceUtils.getFile("classpath:crets/" + AUTH_FILENAME);
            br = new BufferedReader(new FileReader(file));
            String string = null;
            StringBuffer sb = new StringBuffer();
            while((string = br.readLine()) != null){
                sb.append(string);
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
