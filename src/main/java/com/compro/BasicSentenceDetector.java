package com.compro;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Arrays;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world OpenNLP!
 * 
 */

public class BasicSentenceDetector {
    public static void main(String[] args) throws InvalidFormatException,
            IOException {	
    	
    	String input = "Double-click This PC to open the Open dialog box. Alternatively, on the Open page, click Browse (alternatively, click This PC,";
    	
    	/*	String input = "and then click the Documents folder).";
    		
    		String input = "In the Documents folder, click the a01h1DVDs file,";
    		
    		String input = "and then click Open.";
    		
    		String input = "Alternatively, double-click the a01h1DVDs file (or click the file, click the Open arrow,";*/
            
        	// always start with a model, a model is learned from training data
        	InputStream is = new FileInputStream("input/en-sent.bin");
        	SentenceModel model = new SentenceModel(is);
        	SentenceDetectorME sdetector = new SentenceDetectorME(model);
         
        	String sentences[] = sdetector.sentDetect(input);
         
        	System.out.println(sentences[0]);
        	System.out.println(sentences[1]);
        	is.close();}
}
