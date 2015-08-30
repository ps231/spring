package com.spring.wiring;

/*
 * An XML configuration defining a list property can be used interchangebly in Java to define a List, Set or an Array.
 * In case of a Set, the duplicate elements were not added to the collection.
 * To be able to use the @Resource annotation on the property songs, we would have to define the List<String> bean in configuration.
 * This can be achieved by making use of <util:list> element.
 * We cannot use @Autowired here - http://stackoverflow.com/questions/1363310/auto-wiring-a-list-using-util-schema-gives-nosuchbeandefinitionexception
 */

import javax.annotation.Resource;
import java.util.List;

//@Component
public class RomanticSongsCDImpl implements CD {

    @Resource
    private List<String> songs;
//    private Set<String> songs;
//    private String[] songs;

    @Override public void play() {

        System.out.println("playing lovely romantic songs");
    }

    @Override public void printAllSongs() {
        System.out.println("printing all songs on this cd");
        // works with collections like list, set
        songs.stream().forEach(System.out::println);
//        Arrays.stream(songs).forEach(System.out::println);
    }

//    public void setSongs(final List<String> songs) {
//
//        this.songs = songs;
//    }

//    public void setSongs(Set<String> songs) {
//
//        this.songs = songs;
//    }

//    public void setSongs(String[] songs) {
//
//        this.songs = songs;
//    }
}
