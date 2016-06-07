package com.debasish.nlp.tokenizers

import org.apache.lucene.analysis.standard.StandardTokenizer
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import scala.collection.mutable.ListBuffer
import java.io.StringReader

/**
  * Created by Debasish Kaushik on 5/20/16.
  */

private[tokenizers] class LuceneTokenizer extends Tokenizer{

  private[this] val tokenizer = new StandardTokenizer

  def Tokenize(sentence: String): List[String] = {

    tokenizer.setReader(new StringReader(sentence))

    val term = tokenizer.addAttribute(classOf[CharTermAttribute])
    tokenizer.reset()

    val tokens = ListBuffer[String]()
    while(tokenizer.incrementToken) {
      tokens += term.toString
    }
    tokens.toList
  }
}
