package com.debasish.nlp.sentenceDetectors

import opennlp.tools.sentdetect.{SentenceDetectorME, SentenceModel}

/**
  * Created by Debasish Kaushik on 5/21/16.
  */

private[sentenceDetectors] class OpenNLPSentenceDetection extends Sentences {

  val modelIn = getClass.getResource("/models/en-sent.bin")
  private[this] val model = new SentenceModel(modelIn)

  def detect(string: String): List[String] = {

    val sentDetector = new SentenceDetectorME(model)
    val sentences = sentDetector.sentDetect(string)
    sentences.toList
  }
}
