package com.debasish.nlp.posTaggers

/**
  * Created by Debasish Kaushik on 5/21/16.
  */

private[posTaggers] trait POSTagger {

  def process(string: String): List[(String, String)]
}

object POSTagger {

  val posTagger = new StanfordPOSTagger

  def apply(string: String): List[(String, String)] = {

    posTagger.process(string)
  }
}
