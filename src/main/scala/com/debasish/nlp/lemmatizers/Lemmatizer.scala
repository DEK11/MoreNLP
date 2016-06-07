package com.debasish.nlp.lemmatizers

/**
  * Created by Debasish Kaushik on 5/21/16.
  */

private[lemmatizers] trait Lemmatizer {

  def process(wordTags: List[(String, String)]): List[String]
}

object Lemmatizer {

  /**
    *  This reads a file. So, initializing only once
    */

  private[this] val lemma = new OpenNLPLemma

  def apply(wordTags: List[(String, String)]): List[String] = {

    lemma.process(wordTags)
  }
}