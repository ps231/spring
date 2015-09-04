package com.spring.wiring;

import com.spring.autowiring.modes.Company;
import com.spring.autowiring.modes.Party;
import com.spring.autowiring.modes.Person;
import com.spring.lifecycle.HelloService;
import com.spring.qualifiers.Dessert;
import com.spring.scope.PrototypeBean;
import com.spring.scope.Singleton;
import com.spring.scope.SingletonBean;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired private Person personAutowiredByType;
    @Autowired private Company companyAutowiredByType;

    @Autowired
    @Qualifier(value = "organization")
    private Party companyAutowiredByName;


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
    @Ignore
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

    /*
     * Since we have defined the PrototypeBean to be a AOP proxy, we get back the same proxy, but with separate bean implementations.
     * If we try to assertNotSame based on the proxy reference, it fails, since any getX call is going to give back the same proxy.
     * To be able to successfully see that separate instances are indeed created, we need to unwrap the proxy object.
     */
    @Test
    public void testPrototypeBeanWithAopScopedProxy() throws Exception {
        Assert.assertNotNull(autowiredSingleton);

        PrototypeBean prototypeBean = unWrapProxyObject(((SingletonBean) autowiredSingleton).getPrototype(), PrototypeBean.class);
        prototypeBean.setWelcomeMessage("hello world");

        autowiredSingleton.greet();

        Singleton contextSingleton = (Singleton) context.getBean("singletonBean");
        Assert.assertSame(autowiredSingleton, contextSingleton);

        PrototypeBean anotherPrototypeBean = unWrapProxyObject(((SingletonBean) contextSingleton).getPrototype(), PrototypeBean.class);
        anotherPrototypeBean.setWelcomeMessage("hello india");

        contextSingleton.greet();
        autowiredSingleton.greet();

        Assert.assertNotSame(prototypeBean, anotherPrototypeBean);

        PrototypeBean contextPrototypeBean = unWrapProxyObject(context.getBean("prototypeBean"), PrototypeBean.class);
        Assert.assertNotSame(prototypeBean, contextPrototypeBean);
    }

    @Test
    public void understandAutowiringBasics(){

        Assert.assertNotNull(personAutowiredByType);
        Assert.assertNotNull(companyAutowiredByType);

        personAutowiredByType.organizeParty();
        companyAutowiredByType.organizeParty();

        companyAutowiredByName.organizeParty();

    }

    @SuppressWarnings({"unchecked"})
    protected <T> T unWrapProxyObject(Object proxy, Class<T> targetClass) throws Exception {
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            return (T) ((Advised)proxy).getTargetSource().getTarget();
        } else {
            return (T) proxy; // expected to be cglib proxy then, which is simply a specialized class
        }
    }
}
