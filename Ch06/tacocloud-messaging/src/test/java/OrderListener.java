import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tacos.Order;

@Component
public class OrderListener {
    @Autowired
    private KitchenUI ui;
    @KafkaListener(topics = "tacocloud.orders" +
            ".topic")
    public void receiveOrder(Order order){
        ui.displayOrder(order);
    }
}
