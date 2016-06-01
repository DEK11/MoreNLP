package com.debasish.nlp.stemmers

/**
  * Created by Debasish Kaushik on 5/20/16.
  */

private[stemmers] trait Stemmer {

  def process(words: List[String]): List[String]
}

object Stemmer {

  val stem = new StanfordPorterStemmer

  def apply(words: List[String]): List[String] = {

    stem.process(words)
  }
}