package com.debasish.nlp.ners

import com.debasish.nlp.tokenizers.Tokenizer
import opennlp.tools.namefind.{NameFinderME, TokenNameFinderModel}
import opennlp.tools.util.Span

/**
  * Created by Debasish Kaushik on 6/5/16.
  */

/**
  * I am only creating the location match It can
  * easily be extended to other types of entity recognizer
  */

class OpenNlpNER extends NER {

  private[this] val modelIn = getClass.getResource("/models/en-ner-location.bin")
  private[this] val model = new TokenNameFinderModel(modelIn)
  private[this] val nameFinder = new NameFinderME(model)

  def process(string: String): List[(String, String)] = {

    val tokens = Tokenizer(string)
    val finds = nameFinder.find(tokens.toArray)

    Span.spansToStrings(finds, tokens.toArray).map(loc => ("LOCATION", loc)).toList
  }
}
