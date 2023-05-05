//package com.admin.firebase.fdb.service.impl;
//
//
//import com.admin.common.response.Result;
//import com.admin.firebase.fdb.service.FDBService;
//import com.admin.firebase.user.entity.User;
//import com.google.firebase.database.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//@Slf4j
//@Service
//public class FDBServiceImpl implements FDBService {
//
//    @Override
//    public Result save() {
//        DatabaseReference reference = FireBaseSingleton.getInstance().getFirebaseDatabase().getReference("/saving-data/firebaselog");
//        DatabaseReference usersRef = reference.child("users");
//
//        Map<String, User> users = new HashMap<>();
//        users.put("zhangsan", new User("123","123@qq.com","1999-12-30","zhangsan","nice"));
//        users.put("lisi", new User("456","456@qq.com","1960-08-22","lisi","tigger"));
//        usersRef.setValueAsync(users);
//        return Result.ok();
//    }
//
//    @Override
//    public Result get() {
//        List<User> entity = new ArrayList<>();
//        DatabaseReference reference = FireBaseSingleton.getInstance().getFirebaseDatabase().getReference("/saving-data/firebaselog/users");
//
//        reference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                User value = dataSnapshot.getValue(User.class);
//                entity.add(value);
//                System.out.println(value.toString());
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                User value = dataSnapshot.getValue(User.class);
//                entity.add(value);
//                System.out.println(value.toString());
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        return Result.ok(entity);
//    }
//}
