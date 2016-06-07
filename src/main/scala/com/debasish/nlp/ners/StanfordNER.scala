package com.debasish.nlp.ners

import edu.stanford.nlp.ie.crf.CRFClassifier
import edu.stanford.nlp.ling.{CoreAnnotations, CoreLabel}
import edu.stanford.nlp.pipeline.DefaultPaths
import scala.collection.mutable.ListBuffer

/**
  * Created by Debasish Kaushik on 6/5/16.
  */

private[ners] class StanfordNER extends NER {

  private[this] val classifier = CRFClassifier.getClassifierNoExceptions(DefaultPaths.DEFAULT_NER_MUC_MODEL)

  def process(string: String): List[(String, String)] = {

    val keywords = new ListBuffer[(String, String)]

    // no need to run through sentence detector. The NER classifier detects sentences
    val dp = classifier.classify(string).iterator()

    while (dp.hasNext) {

      def recognized(word: CoreLabel): Boolean = {
        !word.get(classOf[CoreAnnotations.AnswerAnnotation]).equals("O") &&
          !word.get(classOf[CoreAnnotations.AnswerAnnotation]).equals("")
      }

      def annotation(word: CoreLabel) = word.get(classOf[CoreAnnotations.AnswerAnnotation])

      val words = dp.next().iterator

      val filteredWords = ListBuffer[CoreLabel]()

      while(words.hasNext) {

        val word = words.next.asInstanceOf[CoreLabel]
        if(recognized(word)) {

          filteredWords += word
        }
      }

      val wordsNER = filteredWords.map(word => (word.word, annotation(word)))

      for(w <- wordsNER) {

        keywords += w
      }
    }

    keywords.toList
  }
}