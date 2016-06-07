package com.debasish.nlp.posTaggers

import edu.stanford.nlp.pipeline.DefaultPaths
import edu.stanford.nlp.tagger.maxent.MaxentTagger

/**
  * Created by Debasish Kaushik on 5/21/16.
  */

private[posTaggers] class StanfordPOSTagger extends POSTagger {

  private[this] val tagger = new MaxentTagger(DefaultPaths.DEFAULT_POS_MODEL)

  def process(string: String): List[(String, String)] = {

    val taggedString = tagger.tagString(string)
    taggedString.split(" ").map{ case pair =>
      val p = pair.split("_")
      (p(0), p(1))
    }.toList
  }
}
