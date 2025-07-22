package com.aichatbot.dao;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.ArrayList;

import com.aichatbot.configuration.FirebaseInitialization;
import com.aichatbot.model.Note;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;

public class NotesDao {
    public static Firestore c2w_ai_db;
    static {
        c2w_ai_db = FirebaseInitialization.getFireStore();
    }

    public void addData(String c2w_ai_collection, String c2w_ai_document, Note c2w_ai_data)
        throws InterruptedException, ExecutionException{
            DocumentReference c2w_ai_docRef = c2w_ai_db.collection(c2w_ai_collection).document(c2w_ai_document);
            ApiFuture<com.google.cloud.firestore.WriteResult> c2w_pi_result = c2w_ai_docRef.set(c2w_ai_data);
            c2w_pi_result.get();
        }

    public List<Note> getDataList(String c2w_ai_collection, String c2w_ai_userName)
        throws ExecutionException,InterruptedException{
            try{
                CollectionReference c2w_ai_colRef = c2w_ai_db.collection(c2w_ai_collection);
                Query query =  c2w_ai_colRef.whereEqualTo("userName",c2w_ai_userName);
                ApiFuture<QuerySnapshot> c2w_ai_querySnapshotFuture = query.get();
                QuerySnapshot c2w_ai_QuerySnapshot = c2w_ai_querySnapshotFuture.get();
                List<QueryDocumentSnapshot> c2w_ai_documents = c2w_ai_QuerySnapshot.getDocuments();
                List<Note> c2w_ai_noteList = new ArrayList<>();

                for(QueryDocumentSnapshot c2w_ai_document : c2w_ai_documents){
                    Note note = c2w_ai_document.toObject(Note.class);
                    c2w_ai_noteList.add(note);
                }
                return c2w_ai_noteList;
            } catch(Exception e){
                e.printStackTrace();
                throw e;
            }
        }
}
