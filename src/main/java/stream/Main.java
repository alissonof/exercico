package stream;

import stream.impl.StreamImpl;
import stream.impl.StreamProcess;

public class Main {
	public static void main(String[] args) {
		Stream stream = new StreamImpl("aAbBABacafe");
		System.out.println(firstChar(stream));
	}
	
	public static char firstChar(Stream input){
		StreamProcess streamProcess = new StreamProcess();
		return streamProcess.process(input);
	}
}
