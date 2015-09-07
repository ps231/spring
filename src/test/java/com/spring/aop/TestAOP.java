package com.spring.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class TestAOP {

    @Resource(name = "instrumentalPerformance")
    private Performance instrumental;

    @Resource(name = "vocalPerformance")
    private Performance vocal;

    @Test
    public void shouldInvokeAspectWhenInvokingInstrumental() {
        instrumental.perform();
    }

    @Test
    public void shouldNotInvokeAspectWhenInvokingVocal() {
        vocal.perform();
    }

}
