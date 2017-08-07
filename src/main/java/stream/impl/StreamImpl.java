package stream.impl;

import stream.Stream;

public class StreamImpl implements Stream{

	private char[] source;
	private int count;
	

	public StreamImpl(String source){
		
		if(source == null){
			throw new RuntimeException("Não é permitido valor nulo");
		}
		
		this.source = source.toCharArray();
		count = -1;
	}

	@Override
	public char getNext() {
		
		if(count == -1){
			throw new RuntimeException("método getNext() só pode ser chamado depois do método hasNext()");
		}
		
		return source[count];
	}

	@Override
	public boolean hasNext() {

		if(count < (source.length - 1)){
			count ++;
			return true;
		}
		
		return false;
	}

	
}
