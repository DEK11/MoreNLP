package com.debasish.nlp.tokenizers

/**
  * Created by Debasish Kaushik on 5/16/16.
  */

private[tokenizers] trait Tokenizer {

  def Tokenize(sentence: String): List[String]
}

object Tokenizer {

  private[this] val tokenizer = new OpenNLPTokenizer

  def apply(sentence: String): List[String] = {

    tokenizer.Tokenize(sentence)
  }
}