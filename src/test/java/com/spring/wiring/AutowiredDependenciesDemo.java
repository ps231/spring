package com.spring.wiring;

import com.spring.lifecycle.HelloService;
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

    @Test
    public void testSayHello() {
        Assert.assertEquals("Hello world!", helloService.sayHello());
    }

    @Test
    public void mediaPlayerMustBeInitialized(){
        Assert.assertNotNull(mediaPlayer);
        mediaPlayer.play();
    }
}
