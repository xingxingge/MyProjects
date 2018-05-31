package com.hx.activemq.syn;

import org.apache.activemq.ActiveMQConnectionFactory;

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

public class Server {
  public static void main(String[] args) throws JMSException {
    String messageBrokerUrl = "tcp://localhost:61616";
//    try {
//      //This message broker is embedded
//      BrokerService broker = new BrokerService();
//      broker.setPersistent(false);
//      broker.setUseJmx(false);
//      broker.addConnector(messageBrokerUrl);
//      broker.start();
//    } catch (Exception e) {
//    }
    //Handle the exception appropriately

    ConnectionFactory factory = new ActiveMQConnectionFactory(messageBrokerUrl);
    Connection connection = factory.createConnection();
    connection.start();

    final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    Destination destination = session.createTopic("myTopic.messages");

    //应答producer
    final MessageProducer replyProducer = session.createProducer(null);
    replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

    MessageConsumer consumer = session.createConsumer(destination);

    consumer.setMessageListener(new MessageListener() {
      public void onMessage(Message message) {
        try {
          TextMessage response = session.createTextMessage();
          if (message instanceof TextMessage) {
            TextMessage txtMsg = (TextMessage) message;
            String messageText = txtMsg.getText();
            System.out.println("recevie: "+messageText);
            response.setText("response:" + messageText);
          }

          //Set the correlation ID from the received message to be the correlation id of the response message
          //this lets the client identify which message this is a response to if it has more than
          //one outstanding message to the server
          response.setJMSCorrelationID(message.getJMSCorrelationID());

          //Send the response to the Destination specified by the JMSReplyTo field of the received message,
          //this is presumably a temporary queue created by the client
          Thread.sleep(3000);
          replyProducer.send(message.getJMSReplyTo(), response);
        } catch (JMSException e) {
          //Handle the exception appropriately
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
//      session.close();
//      connection.stop();
//      connection.close();
  }
}
