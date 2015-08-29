package com.spring.wiring;

import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class CDPlayer implements MediaPlayer {

    @Autowired private CD cd;

    @Override public void play() {
        System.out.println("cd player is going to play the cd");
        cd.play();
    }

//    public void setCd(final CD cd) {
//        this.cd = cd;
//    }
}
