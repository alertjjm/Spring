import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import tacos.Order;

public class RabbitOrderMessagingService implements OrderMessagingService{
    @Autowired
    private RabbitTemplate rabbit;
    @Override
    public void sendOrder(Order order) {
        MessageConverter converter=rabbit.getMessageConverter();
        MessageProperties props=new MessageProperties();
        props.setHeader("X_ORDER_SOURCE", "WEB");
        Message message =converter.toMessage(order, props);
        rabbit.send("tacocloud.order",message);
    }
}
