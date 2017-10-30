package com.hx.designpatterns.structure.adapter.xmlAdapter;

import org.w3c.css.sac.*;

import java.io.InputStream;

/**
 * Created by hx on 16-8-19.
 */
public class XMLParser implements DocumentHandler {
  public XMLParser(InputStream in, XMLProperties xmlProperties) {

  }

  @Override
  public void startDocument(InputSource inputSource) throws CSSException {

  }

  @Override
  public void endDocument(InputSource inputSource) throws CSSException {

  }

  @Override
  public void comment(String s) throws CSSException {

  }

  @Override
  public void ignorableAtRule(String s) throws CSSException {

  }

  @Override
  public void namespaceDeclaration(String s, String s1) throws CSSException {

  }

  @Override
  public void importStyle(String s, SACMediaList sacMediaList, String s1) throws CSSException {

  }

  @Override
  public void startMedia(SACMediaList sacMediaList) throws CSSException {

  }

  @Override
  public void endMedia(SACMediaList sacMediaList) throws CSSException {

  }

  @Override
  public void startPage(String s, String s1) throws CSSException {

  }

  @Override
  public void endPage(String s, String s1) throws CSSException {

  }

  @Override
  public void startFontFace() throws CSSException {

  }

  @Override
  public void endFontFace() throws CSSException {

  }

  @Override
  public void startSelector(SelectorList selectorList) throws CSSException {

  }

  @Override
  public void endSelector(SelectorList selectorList) throws CSSException {

  }

  @Override
  public void property(String s, LexicalUnit lexicalUnit, boolean b) throws CSSException {

  }
}
