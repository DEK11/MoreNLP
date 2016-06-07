package com.debasish.nlp.lemmatizers

import edu.stanford.nlp.ling.WordTag
import edu.stanford.nlp.process.Morphology
import scala.collection.mutable.ListBuffer

/**
  * Created by Debasish Kaushik on 5/21/16.
  */

private[lemmatizers] class StanfordLemma extends Lemmatizer {

  private[this] val morph = new Morphology

  def process(wordTags: List[(String, String)]): List[String] = {

    val lemmas = ListBuffer[String]()

    for((word, tag) <- wordTags) {

      val result = morph.lemmatize(new WordTag(word, tag))
      lemmas.append(result.word())
    }

    lemmas.toList
  }
}
