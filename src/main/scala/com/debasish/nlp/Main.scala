package com.debasish.nlp

import com.debasish.nlp.dataSources.DataSource
import com.debasish.nlp.pipelines.NlpPipeLine
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions.udf
import org.apache.spark.ml.classification.RandomForestClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{CountVectorizer, CountVectorizerModel, IndexToString, StringIndexer}
import org.apache.spark.ml.Pipeline

/**
  * Created by Debasish Kaushik on 5/13/16.
  */

object Main {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("MoreNLP")
    val sc = new SparkContext(conf)

    var df = DataSource(sc)

    val func = udf((str:String) => NlpPipeLine(str))
    df = df.withColumn("review", func(df("review")))


    val cvModel: CountVectorizerModel = new CountVectorizer()
      .setInputCol("review")
      .setOutputCol("features")
      .setVocabSize(300)
      .setMinDF(2)
      .fit(df)

    val labelIndexer = new StringIndexer()
      .setInputCol("sentiment")
      .setOutputCol("indexedLabel")
      .fit(df)

    val Array(train, test) = df.randomSplit(Array(0.8, 0.2), 12345)

    val rf = new RandomForestClassifier()
      .setLabelCol("indexedLabel")
      .setFeaturesCol("features")
      .setNumTrees(10)

    val labelConverter = new IndexToString()
      .setInputCol("prediction")
      .setOutputCol("predictedLabel")
      .setLabels(labelIndexer.labels)

    val pipeline = new Pipeline()
      .setStages(Array(cvModel, labelIndexer, rf, labelConverter))

    val model = pipeline.fit(train)

    val predictions = model.transform(test)

    val evaluator = new MulticlassClassificationEvaluator()
      .setLabelCol("indexedLabel")
      .setPredictionCol("prediction")
      .setMetricName("precision")

    val accuracy = evaluator.evaluate(predictions)
    println("Test Error = " + (1.0 - accuracy))

  }
}