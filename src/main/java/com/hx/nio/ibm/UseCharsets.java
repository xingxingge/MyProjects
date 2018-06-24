package com.hx.nio.ibm;// $Id$

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class UseCharsets
{
  static public void main( String args[] ) throws Exception {
    String inputFile = "samplein.txt";
    String outputFile = "sampleout.txt";

    RandomAccessFile inf = new RandomAccessFile( inputFile, "r" );
    RandomAccessFile outf = new RandomAccessFile( outputFile, "rw" );
    long inputLength = new File( inputFile ).length();

    FileChannel inc = inf.getChannel();
    FileChannel outc = outf.getChannel();

    MappedByteBuffer inputData =
      inc.map( FileChannel.MapMode.READ_ONLY, 0, inputLength );

//    Charset latin1 = Charset.forName( "UTF-8" );
//    CharsetDecoder decoder = latin1.newDecoder();
//    CharsetEncoder encoder = latin1.newEncoder();
//
//    CharBuffer cb = decoder.decode( inputData );
//
//    // Process char data here
//
//    ByteBuffer outputData = encoder.encode( cb );

    outc.write( inputData );

    inf.close();
    outf.close();
  }
}
