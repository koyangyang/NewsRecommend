import org.apache.spark.mllib.recommendation.Rating

import java.sql.{Connection, DriverManager}
import org.apache.spark.rdd.{JdbcRDD, RDD}
import org.apache.spark.{SparkConf, SparkContext}
object Main {
  def main(args: Array[String]): Unit = {
    // 定义配置文件
    val conf = new SparkConf().setAppName("mysql").setMaster("local")
    // 通过配置文件实例spark上下文对象
    val sc = new SparkContext(conf)
    // 定义连接器
    def getCon(): Connection = {
      DriverManager.getConnection("jdbc:mysql://localhost:3306/News?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC", "root", "010619HYy")
    }
    //定义SQL 用来读取数据 username, birthday, sex, address
    var sql = "SELECT user_id,new_id,SUM( CASE operation_type WHEN 0 THEN 1 WHEN 1 THEN ? WHEN 2 THEN ? ELSE 0 END ) AS value FROM useroperationnew GROUP BY user_id,new_id;"
    // 实例 JdbcRDD 进行读取数据
    val ratings = new JdbcRDD(sc, getCon, sql, 3,5,3,re => {
        Rating(re.getInt(1).toInt,re.getInt(2).toInt,re.getInt(3).toDouble)
      }).collect()
    // 结果收集
    var data = sc.parallelize(ratings)
    data.foreach(println)
  }
}