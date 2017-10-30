package com.topsec.tsm.bigdata.spark.ml;

import com.topsec.tsm.bigdata.spark.SqlContextTest;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.mllib.linalg.*;
import org.apache.spark.mllib.linalg.distributed.*;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.util.MLUtils;
import org.junit.Test;
import scala.Tuple2;

import java.io.Serializable;

/**
 * Created by hx on 16-8-4.
 */
public class MlibDataTypeTest  implements Serializable {
  public static JavaSparkContext javaSparkContext;

  @Test
  public  void vectorTest () {
//    SQLContext sqlContext= SqlContextTest.sqlContext;
    Vector vd= Vectors.dense(2,0,6);
    System.out.println(vd.apply(2));
    Vector vs=Vectors.sparse(4,new int[]{0,2,1,3},new double[]{9,5,2,7});
    System.out.println(vs.apply(2));
  }

  @Test
  public void labelPointTest(){
    javaSparkContext= SqlContextTest.javaSparkContext;
// Create a labeled point with a positive label and a dense feature vector.
    LabeledPoint pos = new LabeledPoint(1.0, Vectors.dense(1.0, 0.0, 3.0));
    double label=pos.label();
    Vector vector = pos.features();
    System.out.println(label);
    System.out.println(vector.apply(2));

// Create a labeled point with a negative label and a sparse feature vector.
    LabeledPoint neg = new LabeledPoint(0.0, Vectors.sparse(3, new int[] {0, 2}, new double[] {1.0, 3.0}));
    double l=neg.label();
    System.out.println(l);
    Vector v = neg.features();
    System.out.println(v.apply(2));
    JavaRDD<LabeledPoint> javaRdd=MLUtils.loadLibSVMFile(javaSparkContext.sc(),"/f/JavaHome/code/MyProjects/sample_libsvm_data.txt").toJavaRDD();
    javaRdd.foreach(new VoidFunction<LabeledPoint>() {
      @Override
      public void call(LabeledPoint labeledPoint) throws Exception {
        System.out.println(labeledPoint);
      }
    });

  }
  @Test
  public void localMatrixTest(){
    // Create a dense matrix ((1.0, 2.0), (3.0, 4.0), (5.0, 6.0))//密集矩阵
    Matrix dm = Matrices.dense(3, 2, new double[] {1.0, 3.0, 5.0, 2.0, 4.0, 6.0});
//    System.out.println(dm.apply(1,1));
    System.out.println(dm);

// Create a sparse matrix ((9.0, 0.0), (0.0, 8.0), (0.0, 6.0))//稀疏矩阵
    Matrix sm = Matrices.sparse(3, 2, new int[] {0, 1, 3}, new int[] {0, 2, 1}, new double[] {9, 6, 8});
//    System.out.println(sm.apply(2,1));
    System.out.println(sm);


    System.out.println(sm.apply(0,0));
    System.out.println(sm.apply(2,1));
    System.out.println(sm.apply(1,1));
    /*
    * 1.0  2.0
      3.0  4.0
      5.0  6.0
      3 x 2 CSCMatrix
      (0,0) 9.0
      (2,1) 6.0
      (1,1) 8.0
    * */
  }
  @Test
  public void distributeMatrixTest(){
    javaSparkContext= SqlContextTest.javaSparkContext;
    JavaRDD<String> javaRdd=javaSparkContext.textFile("/f/JavaHome/code/MyProjects/distributeMatrix.txt");
    JavaRDD<String[]> javarddstrings=javaRdd.map(new Function<String, String[]>() {
      @Override
      public String[] call(String s) throws Exception {
        return s.split(" ");
      }
    });

   JavaRDD<Double[]> doubleJavaRDD= javarddstrings.map(new Function<String[], Double[]>() {
      @Override
      public Double[] call(String[] strings) throws Exception {
        Double[] doubles=new Double[strings.length];
        for (int i = 0; i < strings.length; i++) {
          doubles[i]=Double.valueOf(strings[i]);

        }
        return doubles;
      }
    });

    JavaRDD<Vector> rows =doubleJavaRDD.map(new Function<Double[], Vector>() {
      @Override
      public Vector call(Double[] doubles) throws Exception {
        double[] ds=new double[doubles.length];
        for (int i = 0; i < doubles.length ; i++) {
          ds[i]=doubles[i].doubleValue();
        }
//        System.out.println(Vectors.dense(ds));
        return Vectors.dense(ds);
      }
    });
    RowMatrix mat = new RowMatrix(rows.rdd());
    // Get its size.
    long m = mat.numRows();
    System.out.println(m);
    long n = mat.numCols();
    System.out.println(n);
    JavaRDD<Vector> vectors=mat.rows().toJavaRDD();//转换成Vector
    vectors.foreach(new VoidFunction<Vector>() {
      @Override
      public void call(Vector vector) throws Exception {
        System.out.println(vector);

      }
    });

    System.out.println("索引矩阵测试===");
    JavaRDD<IndexedRow> indexedRowMatrixJavaRDD=vectors.map(new Function<Vector, IndexedRow>() {
      @Override
      public IndexedRow call(Vector vector) throws Exception {
        return new IndexedRow(vector.size(),vector);
      }
    });

    IndexedRowMatrix indexedRowMatrix = new IndexedRowMatrix(indexedRowMatrixJavaRDD.rdd());
    System.out.println(mat.numRows());
    System.out.println(mat.numCols());

// Drop its row indices.
    RowMatrix rowMat = indexedRowMatrix.toRowMatrix();//转成单纯的行矩阵
    CoordinateMatrix coordinateMatrix = indexedRowMatrix.toCoordinateMatrix();//转成坐标矩阵
    BlockMatrix blockMatrix = indexedRowMatrix.toBlockMatrix();//转成块矩阵
//打印内容
    indexedRowMatrix.rows().toJavaRDD().foreach(new VoidFunction<IndexedRow>() {
      @Override
      public void call(IndexedRow indexedRow) throws Exception {
        System.out.println(indexedRow);
      }
    });

// QR decomposition
//    QRDecomposition<RowMatrix, Matrix> result = mat.tallSkinnyQR(true);

    //坐标矩阵
    System.out.println("坐标矩阵测试===");
    coordinateMatrix.entries().toJavaRDD().foreach(new VoidFunction<MatrixEntry>() {
      @Override
      public void call(MatrixEntry matrixEntry) throws Exception {
        System.out.println(matrixEntry);
      }
    });
    //块矩阵
    System.out.println("块矩阵测试===");
    blockMatrix.validate();
    BlockMatrix ata = blockMatrix.transpose().multiply(blockMatrix);
    ata.blocks().toJavaRDD().foreach(new VoidFunction<Tuple2<Tuple2<Object, Object>, Matrix>>() {
      @Override
      public void call(Tuple2<Tuple2<Object, Object>, Matrix> tuple2MatrixTuple2) throws Exception {
        System.out.println(tuple2MatrixTuple2._1());
        System.out.println(tuple2MatrixTuple2._2());
      }
    });

  }


}
