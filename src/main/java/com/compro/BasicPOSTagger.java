package com.compro;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
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

public class BasicPOSTagger {
    public static void main(String[] args) throws InvalidFormatException,
            IOException {
	POSModel model = new POSModelLoader()	
		.load(new File("input/en-pos-maxent.bin"));
	PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
	POSTaggerME tagger = new POSTaggerME(model);
 
	String input = "Double-click This PC to open the Open dialog box. Alternatively, on the Open page, click Browse (alternatively, click This PC,";
	
//	String input = "click the Documents folder).";
	
//	String input = "In the Documents folder, click the a01h1DVDs file,";
	
//	String input = "and then click Open.";
	
//	String input = "Alternatively, double-click the a01h1DVDs file (or click the file, click the Open arrow,";
	
//	String input = "Press ENTER or TAB or RIGHT ARROW to select the Genre column.";
	
	ObjectStream<String> lineStream = new PlainTextByLineStream(
			new StringReader(input));
 
	perfMon.start();
	String line;
	while ((line = lineStream.read()) != null) {
 
		String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
				.tokenize(line);
		String[] tags = tagger.tag(whitespaceTokenizerLine);
 
		POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
		System.out.println(sample.toString());
 
		perfMon.incrementCounter();
	}
	perfMon.stopAndPrintFinalResult();
    }
}
