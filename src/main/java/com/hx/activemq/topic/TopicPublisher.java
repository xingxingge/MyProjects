package com.hx.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Scanner;

public class TopicPublisher {
  public static void main(String[] args) throws JMSException {
    ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
    Connection connection = factory.createConnection();
    connection.start();

    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    Topic topic = session.createTopic("myTopic.messages");

    MessageProducer producer = session.createProducer(topic);
    producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

    while(true) {

      System.out.println("输入发送内容:");
      Scanner sc = new Scanner(System.in);
      String line = sc.nextLine();

      TextMessage message = session.createTextMessage(line);
      producer.send(message);
//      session.commit();

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

//      session.close();
//      connection.stop();
//      connection.close();
  }
}
