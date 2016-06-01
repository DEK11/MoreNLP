package com.debasish.nlp.sentenceDetectors

/**
  * Created by Debasish Kaushik on 5/21/16.
  */

private[sentenceDetectors] trait Sentences {

  def detect(string: String): List[String]
}

object Sentences {

  val sentDetector = new OpenNLPSentenceDetection

  def apply(string: String): List[String] = {

    sentDetector.detect(string)
  }
}