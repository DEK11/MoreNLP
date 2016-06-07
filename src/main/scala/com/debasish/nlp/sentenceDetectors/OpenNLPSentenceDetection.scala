package com.debasish.nlp.sentenceDetectors

import opennlp.tools.sentdetect.{SentenceDetectorME, SentenceModel}

/**
  * Created by Debasish Kaushik on 5/21/16.
  */

private[sentenceDetectors] class OpenNLPSentenceDetection extends Sentences {

  private[this] val modelIn = getClass.getResource("/models/en-sent.bin")
  private[this] val model = new SentenceModel(modelIn)
  private[this] val sentDetector = new SentenceDetectorME(model)

  def detect(string: String): List[String] = {

    val sentences = sentDetector.sentDetect(string)
    sentences.toList
  }
}
