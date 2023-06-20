import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel

object Test {
  //屏蔽不必要的日志显示在终端上
  Logger.getLogger("org").setLevel(Level.ERROR)

  def main(args: Array[String]): Unit = {
    //给用户推荐
    val conf = new SparkConf().setMaster("local").setAppName("recommend_test")
    val sc = new SparkContext(conf)

    val myModelPath = "alsMoudle"
    val model = MatrixFactorizationModel.load(sc, myModelPath)

    val recommendProducts = model.recommendProducts(1, 10)
    for (r <- recommendProducts) {
      println("给用户id："+r.user+" 推荐新闻id为 "+r.product)
    }
    val userfeatures = model.userFeatures
    println("用户特征矩阵")
    println("user_id\\feature         f1              f2              f3              f4       ")
    for (uf <- userfeatures) {
      print("用户id：" + uf._1 + " : ")
      for (value <- uf._2) {
        print(value + " ")
      }
      println()
    }
    val itemfeatures = model.productFeatures
    println("新闻特征矩阵")
    println("new_id\\feature         f1              f2              f3              f4       ")
    for (uf <- itemfeatures) {
      print("新闻id：" + uf._1 + " : ")
      for (value <- uf._2) {
        print(value + " ")
      }
      println()
    }
  }
}
