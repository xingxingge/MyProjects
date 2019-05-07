package com.hx.nio.ibm;// $Id$

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class UseCharsets
{
  static public void main( String args[] ) throws Exception {
    String inputFile = "data/nio/samplein.txt";
    String outputFile = "data/nio/sampleout.txt";

    RandomAccessFile inf = new RandomAccessFile( inputFile, "r" );
    RandomAccessFile outf = new RandomAccessFile( outputFile, "rw" );
    long inputLength = new File( inputFile ).length();

    FileChannel inc = inf.getChannel();
    FileChannel outc = outf.getChannel();

    MappedByteBuffer inputData =
      inc.map( FileChannel.MapMode.READ_ONLY, 0, inputLength );
    System.out.println(inputData.get(1));

//    Charset latin1 = Charset.forName( "ISO-8859-1" );
    Charset utf8 = Charset.forName("UTF-8");
    CharsetDecoder decoder = utf8.newDecoder();
    CharsetEncoder encoder = utf8.newEncoder();


    //解码
    CharBuffer cb = decoder.decode( inputData );

    // Process char data here

    //编码
    ByteBuffer outputData = encoder.encode( cb );
//    outputData.flip();
    outc.write( outputData );

    inf.close();
    outf.close();
  }
}
