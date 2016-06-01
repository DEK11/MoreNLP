package com.debasish.nlp.parsers

import java.io.StringReader
import edu.stanford.nlp.parser.lexparser.LexicalizedParser
import edu.stanford.nlp.pipeline.DefaultPaths
import edu.stanford.nlp.process.PTBTokenizer

/**
  * Created by Debasish Kaushik on 5/21/16.
  */

private[parsers] class StanfordLexicalParser extends Parser {

  private[this] val lp = LexicalizedParser.loadModel(DefaultPaths.DEFAULT_PARSER_MODEL)

  def process(sentence: String): Unit = {

    val tokenizerFactory = PTBTokenizer.factory()
    val rawWords = tokenizerFactory.getTokenizer(new StringReader(sentence)).tokenize()
    val parsedTree = lp.parse(rawWords)

    println(parsedTree)
  }
}