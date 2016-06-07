package com.debasish.nlp.stopWordsRemovers

/**
  * Created by Debasish Kaushik on 5/16/16.
  */

import org.apache.lucene.analysis.en.EnglishAnalyzer
import scala.collection.mutable.ListBuffer

private[stopWordsRemovers] class LuceneStopWordRemover extends StopWordRemover{

  private[this] val stopWords = EnglishAnalyzer.getDefaultStopSet

  def remove(words: List[String]): List[String] = {

    var newList = ListBuffer[String]()

    for(w <- words) {
      if (!(stopWords contains w)) {
        newList += w
      }
    }
    newList.toList
  }
}