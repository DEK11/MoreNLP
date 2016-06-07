package com.debasish.nlp.stemmers

import opennlp.tools.stemmer.snowball.SnowballStemmer
import scala.collection.mutable.ListBuffer

/**
  * Created by Debasish Kaushik on 5/21/16.
  */

private[stemmers] class OpenNLPSnowballStemmer extends Stemmer {

  private[this] val stemmer = new SnowballStemmer(SnowballStemmer.ALGORITHM.ENGLISH)

  def process(words: List[String]): List[String] = {

    val stems = ListBuffer[String]()
    for(w <- words) {
      try {
        stems += stemmer.stem(w).toString
      } catch {
        case _: Throwable => stems += w
      }
    }

    stems.toList
  }
}