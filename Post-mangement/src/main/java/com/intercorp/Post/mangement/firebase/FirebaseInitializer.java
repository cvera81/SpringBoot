package com.intercorp.Post.mangement.firebase;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.IOException;

@Service
public class FirebaseInitializer {

    @PostConstruct
    private void iniFirestore() throws IOException {
        InputStream serviceAccount =getClass().getClassLoader().getResourceAsStream("FirebaseKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://post-management-581e4.firebaseio.com/")
                .build();
        if(FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }
    public Firestore getFirestore(){
        return FirestoreClient.getFirestore();

    }
}
