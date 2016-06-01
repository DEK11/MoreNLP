package com.debasish.nlp.lemmatizers

import opennlp.tools.lemmatizer.SimpleLemmatizer

import scala.collection.mutable.ListBuffer

/**
  * Created by Debasish Kaushik on 5/21/16.
  */

private[lemmatizers] class OpenNLPLemma extends Lemmatizer {

  private[this] val file = getClass.getResourceAsStream("/models/elasticsearch-lemmatizer.dict")
  private[this] val lemmatizer = new SimpleLemmatizer(file)
  file.close()

  def process(wordTags: List[(String, String)]): List[String] = {

    val lemma = ListBuffer[String]()
    for (w <- wordTags) {

      lemma += lemmatizer.lemmatize(w._1, w._2)
    }
    lemma.toList
  }
}