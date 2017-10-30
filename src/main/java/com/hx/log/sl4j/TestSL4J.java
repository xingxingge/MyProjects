package com.hx.log.sl4j;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hx on 17-1-8.
 */
public class TestSL4J  extends TestCase{
  public void test(){
    Logger logger = LoggerFactory.getLogger(TestSL4J.class.getName());
    logger.info("test");
  }


}
