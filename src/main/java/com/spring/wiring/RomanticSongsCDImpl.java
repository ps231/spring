package com.spring.wiring;

//@Component
public class RomanticSongsCDImpl implements CD {

    @Override public void play() {
        System.out.println("playing lovely romantic songs");
    }
}
