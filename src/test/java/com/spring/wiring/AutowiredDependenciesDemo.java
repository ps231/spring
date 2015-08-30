package com.spring.wiring;

import com.spring.lifecycle.HelloService;
import com.spring.qualifiers.Dessert;
import com.spring.scope.PrototypeBean;
import com.spring.scope.Singleton;
import com.spring.scope.SingletonBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
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

    @Autowired private Singleton autowiredSingleton;
    @Autowired ConfigurableApplicationContext context;

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

    @Test
    public void testPrototypeBeanInitialization(){
        Assert.assertNotNull(autowiredSingleton);

        PrototypeBean prototypeBean = (PrototypeBean) ((SingletonBean) autowiredSingleton).getPrototype();
        prototypeBean.setWelcomeMessage("hello world");

        autowiredSingleton.greet();

        Singleton contextSingleton = (Singleton) context.getBean("singletonBean");
        Assert.assertSame(autowiredSingleton, contextSingleton);

        PrototypeBean anotherPrototypeBean = (PrototypeBean) ((SingletonBean)contextSingleton).getPrototype();
        anotherPrototypeBean.setWelcomeMessage("hello india");

        contextSingleton.greet();
        autowiredSingleton.greet();

        Assert.assertSame(prototypeBean, anotherPrototypeBean);

        PrototypeBean contextPrototypeBean = (PrototypeBean) context.getBean("prototypeBean");
        Assert.assertNotSame(prototypeBean, contextPrototypeBean);


    }
}
