package dizoo.std.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

public class TopicSubscriber {
  public static void main(String[] args) throws JMSException {
    ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
    Connection connection = factory.createConnection();
    connection.start();

    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    Destination destination = session.createTopic("myTopic.messages");

    MessageConsumer consumer = session.createConsumer(destination);
//    Message receive = consumer.receive();
//    System.out.println(((TextMessage)receive).getText());
    consumer.setMessageListener(new MessageListener() {
      public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
          System.out.println("Received message: " + tm.getText());
          System.out.println(new Date());
        } catch (JMSException e) {
          e.printStackTrace();
        }
      }
    });
//      session.close();
//      connection.stop();
//      connection.close();
  }
}
