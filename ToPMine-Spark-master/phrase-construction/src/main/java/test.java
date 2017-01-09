import org.apache.commons.lang.mutable.MutableLong;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.feature.IDFModel;
import org.apache.spark.mllib.clustering.DistributedLDAModel;
import org.apache.spark.mllib.clustering.LDA;
import org.apache.spark.mllib.feature.IDF;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;

import java.io.*;
import java.util.*;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line = "jjj. jjjj";
		List<String> temp = Arrays.asList(line.split("\\."));
		Iterable<String> iterable = temp;
		System.out.println(temp);
	}

}
