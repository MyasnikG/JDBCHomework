package com.digi;

import com.digi.model.User;
import com.digi.repository.UserRepository;

public class Main {

    public static void main(String[] args) {

//        UserRepository.insert(9,"Meri","Hovakimyan",22,"mery23@gmai.com","784jky5k");
//        UserRepository.update(3,"Milena","Santrosyan",25);

//        UserRepository.addUser(new User(10,"Hovhannes","Sahakyan",36,"hovsak@gmail.com", "tyj8t4y1j5"));
        UserRepository.update(new User(3,null,"Santrosyan",0,"avo@gmail.com","lhouy625"));
        System.out.println(UserRepository.getAll().toString());

    }
}
