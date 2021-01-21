
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tacos.Order;

@Component
public class RabbitOrderReceiver {
    @Autowired
    private RabbitTemplate rabbit;
    public Order receiveOrder(){
        Message message=rabbit.receive("tacocloud.orders");
        MessageConverter converter=rabbit.getMessageConverter();
        return message !=null?(Order)converter.fromMessage(message):null;
    }
}
