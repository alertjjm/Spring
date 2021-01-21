import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import tacos.Order;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class JmsOrderMessagingService implements OrderMessagingService{
    @Autowired
    private JmsTemplate jms;
    @Bean
    public Destination orderQueue(){
        return new ActiveMQQueue("tacocloud.order.queue");
    }
    @Autowired
    Destination orderQueue;
    @Override
    public void sendOrder(Order order) {
        jms.convertAndSend(orderQueue);
    }
}
