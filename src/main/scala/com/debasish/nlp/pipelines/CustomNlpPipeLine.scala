package com.debasish.nlp.pipelines

import com.debasish.nlp.lemmatizers.Lemmatizer
import com.debasish.nlp.posTaggers.POSTagger
import com.debasish.nlp.sentenceDetectors.Sentences
import com.debasish.nlp.stemmers.Stemmer
import com.debasish.nlp.stopWordsRemovers.StopWordRemover

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/**
  * Created by Debasish Kaushik on 5/27/16.
  */

private[pipelines] class CustomNlpPipeLine extends NlpPipeLine{

  def Process(string: String): Array[String] = {

    val finalString = ArrayBuffer.empty[String]

    for (sentence <- Sentences(string)) {

      val tagsToBeAllowed = Array("JJ", "JJR", "JJS",
        "MD", "NN", "NNS", "NNP", "NNPS",
        "RB", "RBR", "RBS", "RP", "UH",
        "VB", "VBZ", "VBP", "VBD", "VBN", "VBG")

      val wordsOnTagsFilter = POSTagger(sentence).filter{
        case (pair, tag) => tagsToBeAllowed contains tag
      }

      val lemmatizedWords = Lemmatizer(wordsOnTagsFilter)
      val stemmedWords = Stemmer(lemmatizedWords)
      val filteredStopWords = StopWordRemover(stemmedWords)

      finalString ++=  filteredStopWords
    }

    finalString.toArray
  }
}
