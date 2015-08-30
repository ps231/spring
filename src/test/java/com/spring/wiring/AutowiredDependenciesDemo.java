package com.spring.wiring;

import com.spring.lifecycle.HelloService;
import com.spring.qualifiers.Dessert;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class AutowiredDependenciesDemo {

    @Autowired private HelloService helloService;
    @Autowired private MediaPlayer mediaPlayer;
    @Autowired private CD romanticSongsCD;
    @Autowired
//    @Qualifier(value = "iceCream")
    private Dessert dessert;

    @Test
    public void testSayHello() {
        Assert.assertEquals("Hello world!", helloService.sayHello());
    }

    @Test
    public void mediaPlayerMustBeInitialized(){
        Assert.assertNotNull(mediaPlayer);
        mediaPlayer.play();
    }

    @Test
    public void cdShouldPrintAllSongs(){
        romanticSongsCD.printAllSongs();
    }

    @Test
    public void dessertShouldBeInitialized(){
        Assert.assertNotNull(dessert);
        dessert.enjoyDessert();

    }
}
