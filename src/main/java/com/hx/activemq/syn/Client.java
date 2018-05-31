package com.hx.activemq.syn;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.Scanner;
import java.util.UUID;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Client {
  public static void main(String[] args) throws JMSException {
    ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
    Connection connection = factory.createConnection();
    connection.start();

    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    Destination destination = session.createTopic("myTopic.messages");

    MessageProducer messageProducer = session.createProducer(destination);
    messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

    Destination tempDest = session.createTemporaryTopic();

    //临时topic接收信息
    MessageConsumer responseConsumer = session.createConsumer(tempDest);

    responseConsumer.setMessageListener(new MessageListener() {
      public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
          System.out.println("Response message: " + tm.getText());
        } catch (JMSException e) {
          e.printStackTrace();
        }
      }
    });

    while (true) {

      System.out.println("输入发送内容:");
      Scanner sc = new Scanner(System.in);
      String line = sc.nextLine();

      TextMessage textMessage = session.createTextMessage(line);
      //响应到temp topic上
      textMessage.setJMSReplyTo(tempDest);
      textMessage.setJMSCorrelationID(UUID.randomUUID().toString());
      messageProducer.send(textMessage);
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
