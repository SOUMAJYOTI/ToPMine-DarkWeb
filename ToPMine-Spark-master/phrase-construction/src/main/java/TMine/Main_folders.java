package TMine;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import TMine.PhraseLDA;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.*;

/**
 * Created by Jin on 11/18/2015.
 */
public class Main_folders {
	public static final String MAIN_PATH = "F:/ToPMine-Spark-master/TopMine-Spark-master/phrase-construction/src/resources/";
    public static final String DATASET_NAME = "input";
    public static final String CORPUS_FILE_PATH = MAIN_PATH + String.format("%s.txt", DATASET_NAME);
    public static final String OUTPUT_FILE_PATH = MAIN_PATH + String.format("%s_output", DATASET_NAME);
    public static final String DICT_FILE_PATH = MAIN_PATH + String.format("%s_dict", DATASET_NAME);
    public static final String STOP_WORDS_FILE_PATH = MAIN_PATH + "stopwords.txt";


    public static void main(String[] args) throws IOException, PhraseConstructionException {
        System.out.println("Hello Spark");
        LocalDateTime startTime = LocalDateTime.now();

        // prepare conf
        SparkConf conf = new SparkConf().setAppName("Phrase Construction").setMaster("local");
        System.out.println(startTime);
        
        String user_doc_folder = "F:/ToPMine-Spark-master/TopMine-Spark-master/phrase-construction/src/resources/user_months";
        final File directories = new File(user_doc_folder);
        for(final File d: directories.listFiles()){
        	System.out.println(d.getName());
        	final File folder = new File(user_doc_folder +  "/" + d.getName());
            int cnt = 0;
            for (final File fileEntry : folder.listFiles()) {
//                if(cnt >= 1)
//                	break;
                List<String> uniquePosts = new ArrayList<String>();
                String fileName = fileEntry.getName();
                String userId = fileName.substring(0, fileName.length()-4);
            
                if(fileEntry.length() == 0){
                	continue;
                }
                cnt += 1;
                File OUTPUT_PATH = new File(MAIN_PATH + "user_months/results/" + d.getName() + "/" + userId);
            	
                System.out.println(OUTPUT_PATH);
    	        // if the directory does not exist, create it
    	        if (!OUTPUT_PATH.exists()) {
    	        	OUTPUT_PATH.mkdirs();
    	         }
    	        else{
    	        	continue;
    	        }
                String output_File = MAIN_PATH +  "user_months/" + d.getName() + "/" + fileName;
//                System.out.println(output_File);
    	        
    	        try(JavaSparkContext javaSparkContext = new JavaSparkContext(conf)) {
    	        	javaSparkContext.setLogLevel("OFF");
    	            SparkJob.runPhraseMining(javaSparkContext, output_File, OUTPUT_PATH + "/output_phrases.txt", OUTPUT_PATH + "/dict_phrases.txt", STOP_WORDS_FILE_PATH);
    	            SparkJob.runBagOfWordsLDA(javaSparkContext, output_File, OUTPUT_PATH + "/output_words.txt", OUTPUT_PATH + "/dict_words.txt", STOP_WORDS_FILE_PATH);
    	        }
    	                
    	
    	        // measure time
    	        long elapsedTimeInSeconds = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - startTime.toEpochSecond(ZoneOffset.UTC);
    	        System.out.println("Elpased Time: " + elapsedTimeInSeconds + " seconds");
            }
        }
    }
        
}
