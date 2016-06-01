package com.debasish.nlp.ners

/**
  * Created by Debasish Kaushik on 5/29/16.
  */

trait NER {

  def process(string: String): List[String]
}

object NER {

  def apply(string: String): List[String] = {

    List.empty[String]
  }
}