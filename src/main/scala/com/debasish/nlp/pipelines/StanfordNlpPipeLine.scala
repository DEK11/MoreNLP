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

class StanfordNlpPipeLine extends NlpPipeLine {

  val props = new Properties()
  props.put("annotators", "tokenize, ssplit, pos, lemma")
  val pipeline = new StanfordCoreNLP(props)


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

        if((Array("NN") contains pos) || true) {

          finalWordList.append(lemma)
        }
      }
    }

    StopWordRemover(finalWordList.toList).toArray//foldRight("")(_ + " "+ _)
  }
}