package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userlist")
public class UserCtrl {
    public List<Message> userlist = new ArrayList<>();

    public  UserCtrl(){
        userlist.add(new Message("Kelvin",1));
        userlist.add(new Message("Amish",2));
        userlist.add(new Message("Sutam",3));
        userlist.add(new Message("Sanjay",4));
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllUsers(){return ResponseEntity.ok(userlist);}

    @PostMapping
    public ResponseEntity<Message> createUsers(@RequestBody Message user){
        userlist.add(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Message> deleteUsers(@PathVariable  int id)
    {
        for (int i = 0; i < userlist.size(); i++) {
            Message m = userlist.get(i);
            if (m.id == id) {
                userlist.remove(i);
                return ResponseEntity.ok(m);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Message> getUserById(@PathVariable int id ){
        for(int i=0;i<userlist.size();i++){
            Message m = userlist.get(i);
            if(m.id == id){
                return ResponseEntity.ok(m);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PutMapping("update/{id}")
    public ResponseEntity<Message> updateUserById(@PathVariable int id, @RequestBody Message updatedUser){
        for(int i=0;i<userlist.size();i++){
            Message m = userlist.get(i);
            if(m.id == id){
                m.name = updatedUser.name;
                return ResponseEntity.ok(m);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
