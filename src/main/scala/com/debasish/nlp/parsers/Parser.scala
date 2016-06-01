package com.debasish.nlp.parsers

/**
  * Created by Debasish Kaushik on 5/21/16.
  */

private[parsers] trait Parser {

  def process(sentence: String): Unit
}


object Parser {

  private[this] val parser = new OpenNlpParser

  def apply(sentence: String): Unit = {

    parser.process(sentence)
  }
}