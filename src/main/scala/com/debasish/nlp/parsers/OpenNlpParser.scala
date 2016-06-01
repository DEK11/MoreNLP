package com.debasish.nlp.parsers

import opennlp.tools.cmdline.parser.ParserTool
import opennlp.tools.parser.{ParserFactory, ParserModel}

/**
  * Created by debasish on 5/21/16.
  */

private[parsers] class OpenNlpParser extends Parser {

  private[this] val path = getClass.getResource("/models/en-parser-chunking.bin")
  private[this] val model = new ParserModel(path)

  def process(sentence: String): Unit = {

    val parser = ParserFactory.create(model)
    val parsedTree = ParserTool.parseLine(sentence, parser, 1)

    for(p <- parsedTree) p.show()
  }
}
