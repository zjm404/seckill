package cn.zhu4wp.seckill.rabbitmq;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * MQReceiver Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>09/02/2020</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MQReceiverTest {
    @Resource
    private MessageConsumer messageConsumer;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: receive(String message)
     */
    @Test
    public void testReceive() throws Exception {
    }


} 
