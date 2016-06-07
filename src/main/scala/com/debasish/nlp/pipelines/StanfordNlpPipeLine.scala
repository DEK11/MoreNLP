package com.debasish.nlp.pipelines

import java.util.Properties

import com.debasish.nlp.stopWordsRemovers.StopWordRemover
import edu.stanford.nlp.ling.CoreAnnotations._
import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions._

/**
  * Created by Debasish Kaushik on 5/27/16.
  */

private[pipelines] class StanfordNlpPipeLine extends NlpPipeLine {

  private[this] val props = new Properties()
  props.put("annotators", "tokenize, ssplit, pos, lemma")
  private[this] val pipeline = new StanfordCoreNLP(props)


  def Process(string: String): Array[String] = {

    val doc = new Annotation(string)

    pipeline.annotate(doc)

    val sentences = doc.get(classOf[SentencesAnnotation])

    val finalWordList = ListBuffer[String]()

    for (sentence <- sentences) {

      val tokens = sentence.get(classOf[TokensAnnotation])

      for (token <- tokens) {
        // this is the text of the token
        //val word = token.get(classOf[TextAnnotation])

        val pos = token.get(classOf[PartOfSpeechAnnotation])
        val lemma = token.get(classOf[LemmaAnnotation])

        if(Array("JJ", "JJR", "JJS",
          "MD", "NN", "NNS", "NNP", "NNPS",
          "RB", "RBR", "RBS", "RP", "UH",
          "VB", "VBZ", "VBP", "VBD", "VBN", "VBG") contains pos) {

          finalWordList.append(lemma)
        }
      }
    }

    StopWordRemover(finalWordList.toList).toArray//foldRight("")(_ + " "+ _)
  }
}