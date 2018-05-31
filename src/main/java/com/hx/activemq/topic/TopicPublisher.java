package com.hx.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.Date;
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class TopicPublisher {
  public static void main(String[] args) throws JMSException {
    ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
    Connection connection = factory.createConnection();
    connection.start();

    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    Destination destination = session.createTopic("myTopic.messages");

    MessageProducer producer = session.createProducer(null);//topic上建立producer
    producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

    while(true) {

      System.out.println("输入发送内容:");
      Scanner sc = new Scanner(System.in);
      String line = sc.nextLine();

      TextMessage message = session.createTextMessage(line);
      producer.send(destination,message);
      System.out.println(new Date());
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
