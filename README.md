#Some(NLP)

This project for me to learn some NLP concepts and also can I integrate it with Spark.
There are two types of Pipelines here in the project, first is the NLP pipeline and
the second is ML pipeline.

But in this project, I actually wanted to work on the more on the NLP pipeline, and how to customize it.
As I was new to both Stanford NLP and Open NLP, most of time was consumed in understanding these libraries.
I have built two pipelines, one is where we can call these libraries interchangeably, and
the second one is a CoreStanfordNLP standard pipeline. The advantage of using one library is I needn't convert it into
primitive data types after every step.

##Things to note:

I am only using previously trained models, the data used is a Kaggle IMDB dataset. It's size is 34 MB. Feeding this data set to a random forest 
of size 10, I am getting accuracy 74 percent.


Features implemented |  StanFordNLP | OpenNLP | Comments |
--------------------:|-------------:|--------:|---------:|
Tokenizers           |                ✓                  |             ✓             |
StopWords Removers   |                ✗                  |             ✗             |  Using a custom build List 
Stemmers             |                ✓                  |             ✓             |  Snowball from OpenNLP and Porter from Stanford                                                                         
SentenceDetection           |                ✓                  |             ✓             |
Parts-of-speech tagging     |                ✓                  |             ✓             |
Parsers                     |                ✓                  |             ✓             |
Lemmatizers                 |                ✓                  |             ✓             |  OpenNLP don't have a dictionary. So, using elastic search dict
Named Entity Recognizer     |                ✓                  |             ✓             |  For OpenNLP, only location identifier is implemented
