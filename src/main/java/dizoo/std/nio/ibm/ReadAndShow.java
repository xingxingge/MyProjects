package dizoo.std.nio.ibm;// $Id$

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class ReadAndShow
{
  static public void main( String args[] ) throws Exception {
    FileInputStream fin = new FileInputStream( "/f/JavaHome/code/MyProjects/MyProjects/src/main/java/com/hx/nio/ibm/ReadAndShow.java" );
    FileChannel fc = fin.getChannel();

    ByteBuffer buffer = ByteBuffer.allocate( 1024 );

    fc.read( buffer );

    buffer.flip();

    int i=0;
    while (buffer.remaining()>0) {
      byte b = buffer.get();
      System.out.print(((char)b) );
      i++;
    }

    fin.close();
  }
}
