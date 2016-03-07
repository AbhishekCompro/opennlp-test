package com.compro;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Arrays;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
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

public class BasicChunker {
    public static void main(String[] args) throws InvalidFormatException,
            IOException {POSModel model = new POSModelLoader()
			.load(new File("input/en-pos-maxent.bin"));
	PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
	POSTaggerME tagger = new POSTaggerME(model);
 
	String input = "Hi. How are you? This is Mike.";
	ObjectStream<String> lineStream = new PlainTextByLineStream(
			new StringReader(input));
 
	perfMon.start();
	String line;
	String whitespaceTokenizerLine[] = null;
 
	String[] tags = null;
	while ((line = lineStream.read()) != null) {
		whitespaceTokenizerLine = WhitespaceTokenizer.INSTANCE
				.tokenize(line);
		tags = tagger.tag(whitespaceTokenizerLine);
 
		POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
		System.out.println(sample.toString());
			perfMon.incrementCounter();
	}
	perfMon.stopAndPrintFinalResult();
 
	// chunker
	InputStream is = new FileInputStream("input/en-chunker.bin");
	ChunkerModel cModel = new ChunkerModel(is);
 
	ChunkerME chunkerME = new ChunkerME(cModel);
	String result[] = chunkerME.chunk(whitespaceTokenizerLine, tags);
 
	for (String s : result)
		System.out.println(s);
 
	Span[] span = chunkerME.chunkAsSpans(whitespaceTokenizerLine, tags);
	for (Span s : span)
		System.out.println(s.toString());}
}
