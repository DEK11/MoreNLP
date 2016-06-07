package com.debasish.nlp.stemmers

import com.debasish.nlp.stemmers.stanford.PorterStemmer
import scala.collection.mutable.ListBuffer

/**
  * Created by Debasish Kaushik on 5/20/16.
  */

private[stemmers] class StanfordPorterStemmer extends Stemmer {

  private[this] val stemmer = new PorterStemmer

  def process(words: List[String]): List[String] = {

    val wordStems = ListBuffer[String]()
    for (w <- words) {
      try {
        if (!w.isEmpty)
          wordStems += stemmer.stem(w)
      } catch {
        case _: Throwable => wordStems += w
      }
    }

    wordStems.toList
  }
}