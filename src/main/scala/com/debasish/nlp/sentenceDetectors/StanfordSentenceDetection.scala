package com.debasish.nlp.sentenceDetectors

import java.io.StringReader
import edu.stanford.nlp.ling.Sentence
import edu.stanford.nlp.process.DocumentPreprocessor
import scala.collection.mutable.ListBuffer

/**
  * Created by Debasish Kaushik on 5/21/16.
  */

private[sentenceDetectors] class StanfordSentenceDetection extends Sentences {

  def detect(string: String): List[String] = {

    val dp = new DocumentPreprocessor(new StringReader(string)).iterator

    val sentenceList = ListBuffer[String]()
    while(dp.hasNext) {
      val sentenceString = Sentence.listToString(dp.next)
      sentenceList += sentenceString.toString
    }

    sentenceList.toList
  }
}