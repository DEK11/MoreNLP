package com.debasish.nlp.ners

/**
  * Created by Debasish Kaushik on 5/29/16.
  */

private[ners] trait NER {

  def process(string: String): List[(String, String)]
}

object NER {

  private[this] val ner = new OpenNlpNER

  def apply(string: String): List[(String, String)] = {

    ner.process(string)
  }
}