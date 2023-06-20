import java.io.File
import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.recommendation.{ALS, Rating}
import org.apache.spark.rdd.{JdbcRDD, RDD}

import java.sql.{Connection, DriverManager}

object Train {
  //屏蔽不必要的日志显示在终端上
  Logger.getLogger("org").setLevel(Level.WARN)

  def main(args: Array[String]): Unit = {
    // 配置Spark
    val conf = new SparkConf().setMaster("local").setAppName("alsMoudleTrain")
    val sc = new SparkContext(conf)
    // 定义模型
    val myModelPath = "alsMoudle"
    // 获取数据
    def getCon(): Connection = {
      DriverManager.getConnection("jdbc:mysql://localhost:3306/News?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC", "root", "010619HYy")
    }
    //定义SQL 用来读取数据 user_id,new_id,value
    var sql = "SELECT user_id,new_id,SUM( CASE operation_type " +
      "WHEN 0 THEN 1 WHEN 1 THEN ? WHEN 2 THEN ? ELSE 0 END ) " +
      "AS value FROM useroperationnew GROUP BY user_id,new_id;"
    // 实例 JdbcRDD 进行读取Mysql数据
    val data = new JdbcRDD(sc, getCon, sql, 3, 5, 3, re => {
      Rating(re.getInt(1).toInt, re.getInt(2).toInt, re.getInt(3).toDouble)
    }).collect()
    var ratings = sc.parallelize(data)
    ratings.filter(x => x.user == 101).foreach(println)
    // 训练模型
    val rank = 4
    val numIterations = 10
    val model = ALS.train(ratings, rank, numIterations, 0.01)
    val recommendProducts = model.recommendProducts(1, 10)
    val userfeatures =model.userFeatures
    println("打印用户特征矩阵")
    for (uf <- userfeatures) {
      print("用户id："+uf._1 + " : ")
      for (value <- uf._2) {
        print(value + " ")
      }
      println()
    }
    val itemfeatures =model.productFeatures
    println("打印新闻特征矩阵")
    for (uf <- itemfeatures) {
      print("新闻id："+uf._1 + " : ")
      for (value <- uf._2) {
        print(value + " ")
      }
      println()
    }
    val path: File = new File(myModelPath)
    dirDel(path)  //删除原模型保存的文件，不删除新模型保存会报错
    //保存模型
    model.save(sc, myModelPath)
    sc.stop()
  }

  //删除模型目录和文件
  def dirDel(path: File) {
    if (!path.exists())
      return
    else if (path.isFile) {
      path.delete()
      return
    }
    val file: Array[File] = path.listFiles()
    for (d <- file) {
      dirDel(d)
    }
    path.delete()
  }
}