package com.debasish.nlp.pipelines

/**
  * Created by Debasish Kaushik on 5/27/16.
  */

private[pipelines] trait NlpPipeLine {

  def Process(string: String): Array[String]
}


object NlpPipeLine {

  private[this] val pipeline = new StanfordNlpPipeLine

  def apply(string: String): Array[String] = {

    pipeline.Process(string)
  }
}