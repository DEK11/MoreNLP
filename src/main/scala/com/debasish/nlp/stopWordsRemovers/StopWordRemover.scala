package com.debasish.nlp.stopWordsRemovers

/**
  * Created by Debasish Kaushik on 5/16/16.
  */

private[stopWordsRemovers] trait StopWordRemover {

  def remove(words: List[String]): List[String]

}


object StopWordRemover {

  private[this] val stopWordRemover = new CustomizedStopWordsRemover

  def apply(words: List[String]): List[String] = {

    stopWordRemover.remove(words)
  }
}