package com.debasish.nlp.tokenizers

import java.io.StringReader
import edu.stanford.nlp.process.PTBTokenizer

/**
  * Created by Debasish Kaushik on 5/16/16.
  */

private[tokenizers] class StanfordTokenizer extends Tokenizer{

  val tokenizerFactory = PTBTokenizer.factory()

  def Tokenize(sentence: String): List[String] = {

    val rawWords = tokenizerFactory.getTokenizer(new StringReader(sentence)).tokenize()
    rawWords.toArray.toList.map(_.toString)
  }
}