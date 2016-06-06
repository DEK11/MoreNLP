package com.debasish.nlp.posTaggers

import com.debasish.nlp.tokenizers.Tokenizer
import opennlp.tools.postag.{POSModel, POSTaggerME}

/**
  * Created by Debasish Kaushik on 5/21/16.
  */

private[posTaggers] class OpenNlpPOSTagger extends POSTagger {

  private[this] val modelIn = getClass.getResource("/models/en-pos-maxent.bin")
  private[this] val model = new POSModel(modelIn)
  private[this] val tagger = new POSTaggerME(model)

  def process(string: String): List[(String, String)] = {

    val tokens = Tokenizer(string)
    val tags = tagger.tag(tokens.toArray)
    tokens.zip(tags)
  }
}