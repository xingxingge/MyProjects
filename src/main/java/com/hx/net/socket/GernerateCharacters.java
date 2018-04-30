package com.hx.net.socket;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by hx on 18-4-29.
 */
public class GernerateCharacters {

  public static void main(String[] args) throws IOException {
    OutputStream out = System.out;
    generateCharacters1(out);
  }

  //一个字节一个字节的输出
  public static void generateCharacters1(OutputStream out) throws IOException {
    int firstPrintableCharacter = 33; // ASCII = 33 即 ‘!’
    int numberOfPrintableCharacters = 94;
    int numberOfChatactersPerLine = 72;//每行输出的个数

    int start = firstPrintableCharacter;
    int lines = 15;//输出15行
    while (true) {
      for (int i = start; i < start + numberOfChatactersPerLine; i++) {
        out.write((
                (i - firstPrintableCharacter) % numberOfPrintableCharacters)
                + firstPrintableCharacter);
      }
      out.write('\r');
      out.write('\n');
      start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters
              + firstPrintableCharacter;
      lines--;
      if (lines == 0) {
        break;
      }
    }
    //后序处理
    //刷新流
    out.flush();
    //关闭流
    if (out != null) {
      out.close();
    }
    out = null;
  }

  public static void generateCharacters2(OutputStream out) throws IOException{
    int firstPrintableCharacter = 33; // ASCII = 33 即 ‘!’
    int numberOfPrintableCharacters = 94;
    int numberOfChatactersPerLine = 72;

    int start = firstPrintableCharacter;
    byte[] buffer = new byte[numberOfChatactersPerLine + 2];  //2是给回车和换行符的
    int lines = 15;
    while(true) {
      for(int i = start; i < start + numberOfChatactersPerLine; i++) {
        buffer[i - start] = (byte)((i - firstPrintableCharacter) % numberOfPrintableCharacters
                + firstPrintableCharacter);
      }
      buffer[72] = (byte) ('\r');
      buffer[73] = (byte) ('\n');
      out.write(buffer);
      start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters
              + firstPrintableCharacter;
      lines--;
      if(lines == 0) {
        break;
      }
    }
    out.flush();
    if(out != null) {
      out.close();
    }
    out = null;
  }
}
