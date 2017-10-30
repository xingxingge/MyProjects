package com.topsec.tsm.bigdata.spark.ml;
import java.util.HashMap;

import scala.Tuple2;

import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.DecisionTree;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.SparkConf;

/**
 * Classification and regression using decision trees.
 */
public final class JavaDecisionTree {

  public static void main(String[] args) {
    String datapath = "sample_libsvm_data.txt";
    if (args.length == 1) {
      datapath = args[0];
    } else if (args.length > 1) {
      System.err.println("Usage: JavaDecisionTree <libsvm format data file>");
      System.exit(1);
    }
    SparkConf sparkConf = new SparkConf().setAppName("JavaDecisionTree");
    sparkConf.setMaster("local");
    JavaSparkContext sc = new JavaSparkContext(sparkConf);

    JavaRDD<LabeledPoint> data = MLUtils.loadLibSVMFile(sc.sc(), datapath).toJavaRDD().cache();

    // Compute the number of classes from the data.
    Integer numClasses = data.map(new Function<LabeledPoint, Double>() {
      @Override public Double call(LabeledPoint p) {
        return p.label();
      }
    }).countByValue().size();

    // Set parameters.
    //  Empty categoricalFeaturesInfo indicates all features are continuous.
    HashMap<Integer, Integer> categoricalFeaturesInfo = new HashMap<Integer, Integer>();
    String impurity = "gini";
    Integer maxDepth = 5;
    Integer maxBins = 32;

    // Train a DecisionTree model for classification.
    final DecisionTreeModel model = DecisionTree.trainClassifier(data, numClasses,
            categoricalFeaturesInfo, impurity, maxDepth, maxBins);

    // Evaluate model on training instances and compute training error
    JavaPairRDD<Double, Double> predictionAndLabel =
            data.mapToPair(new PairFunction<LabeledPoint, Double, Double>() {
              @Override public Tuple2<Double, Double> call(LabeledPoint p) {
                return new Tuple2<Double, Double>(model.predict(p.features()), p.label());
              }
            });
    Double trainErr =
            1.0 * predictionAndLabel.filter(new Function<Tuple2<Double, Double>, Boolean>() {
              @Override public Boolean call(Tuple2<Double, Double> pl) {
                return !pl._1().equals(pl._2());
              }
            }).count() / data.count();
    System.out.println("Training error: " + trainErr);
    System.out.println("Learned classification tree model:\n" + model);

    // Train a DecisionTree model for regression.
    impurity = "variance";
    final DecisionTreeModel regressionModel = DecisionTree.trainRegressor(data,
            categoricalFeaturesInfo, impurity, maxDepth, maxBins);

    // Evaluate model on training instances and compute training error
    JavaPairRDD<Double, Double> regressorPredictionAndLabel =
            data.mapToPair(new PairFunction<LabeledPoint, Double, Double>() {
              @Override public Tuple2<Double, Double> call(LabeledPoint p) {
                return new Tuple2<Double, Double>(regressionModel.predict(p.features()), p.label());
              }
            });
    Double trainMSE =
            regressorPredictionAndLabel.map(new Function<Tuple2<Double, Double>, Double>() {
              @Override public Double call(Tuple2<Double, Double> pl) {
                Double diff = pl._1() - pl._2();
                return diff * diff;
              }
            }).reduce(new Function2<Double, Double, Double>() {
              @Override public Double call(Double a, Double b) {
                return a + b;
              }
            }) / data.count();
    System.out.println("Training Mean Squared Error: " + trainMSE);
    System.out.println("Learned regression tree model:\n" + regressionModel);

    sc.stop();
  }
}
