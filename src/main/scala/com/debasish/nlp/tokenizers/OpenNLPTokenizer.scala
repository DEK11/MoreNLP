package com.debasish.nlp.tokenizers

import opennlp.tools.tokenize.{TokenizerME, TokenizerModel}

/**
  * Created by Debasish Kaushik on 5/21/16.
  */

private[tokenizers] class OpenNLPTokenizer extends Tokenizer{

  private[this] val modelIn = getClass.getResource("/models/en-token.bin")
  private[this] val model = new TokenizerModel(modelIn)
  private[this] val tokenizer = new TokenizerME(model)

  def Tokenize(sentence: String): List[String] = {

    tokenizer.tokenize(sentence).toList
  }
}