package com.debasish.nlp.dataSources

import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.sql.functions._
import org.jsoup.Jsoup

/**
  * Created by Debasish Kaushik on 5/29/16.
  */

object DataSource {

  def apply(sc: SparkContext): DataFrame = {

    val sqlContext = new SQLContext(sc)

    val filePath = getClass.getResource("/labeledTrainData.tsv").toString

    val df = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("delimiter", "\t")
      .option("quote", "^")
      .option("header", "true")
      .option("inferSchema", "true")
      .load(filePath)

    val CleanReview = udf((cell: String) => {
      val jString = Jsoup.parse(cell).body.text.replace("\"", "").replace("\\", "")
      val regex = "[^a-zA-Z]"
      jString//.replaceAll(regex, " ")
    })

    val CleanID = udf((cell: String) => {
      cell.replace("\"", "")
    })

    df.withColumn("review", CleanReview(col("review"))).withColumn("id", CleanID(col("id")))
  }
}