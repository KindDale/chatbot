package com.aichatbot.configuration;

import java.util.logging.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseInitialization {
    
    private static final Logger LOGGER = Logger.getLogger(FirebaseInitialization.class.getName());

    private static FirebaseApp firebaseApp;

    static{
        init();
    }

    public static void init(){
        if(firebaseApp == null){
            try{
                FileInputStream serviceAccount = new FileInputStream("src/main/java/com/aichatbot/ai-chatbot.json");

                FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId("your_project_id")
                .build();


                firebaseApp = FirebaseApp.initializeApp(options);
            } catch(IOException ie){
                LOGGER.log(Level.SEVERE, "Error initializing Firebase " + ie.getMessage(), ie);
            } 
        } else {
            LOGGER.warning("FirebaseApp already initialized. Skipping Initialization");
        }
    }

    public static FirebaseAuth getFirebaseAuth(){
        if(firebaseApp == null){
            throw new IllegalStateException("FirebaseApp has not been initialized. Call init() first.");
        }
        return FirebaseAuth.getInstance(firebaseApp);
    }

    public static Firestore getFireStore(){
        if(firebaseApp == null){
            throw new IllegalStateException("FirebaseApp has not been initialized. Call init() first.");
        }
        return FirestoreClient.getFirestore(firebaseApp);
    }
}


