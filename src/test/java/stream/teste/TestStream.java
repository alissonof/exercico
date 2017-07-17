package stream.teste;



import org.junit.Assert;
import org.junit.Test;

import stream.Stream;
import stream.impl.StreamImpl;

public class TestStream {

	@Test
	public void testaSequenciaSimples(){
		Stream stream = new StreamImpl("aAbBABacafe");

		Assert.assertEquals(true, stream.hasNext());
		Assert.assertEquals('e', stream.getNext());
		
	}
	
	
	@Test
	public void testaSequenciaTerminaVogal(){
		Stream stream = new StreamImpl("aAbBABacafewA");

		Assert.assertEquals(true, stream.hasNext());
		Assert.assertEquals('e', stream.getNext());
		
		Assert.assertEquals(true, stream.hasNext());
		
		try{
			stream.getNext();
			Assert.assertTrue(false);
		}catch(RuntimeException ex){
			Assert.assertEquals("Não foi encontrado sequencia: vogal consoante vogal, onde a segunda vogal não tenha aparecido anteriormente", ex.getMessage());
		}
		
		Assert.assertEquals(false, stream.hasNext());
		
	}

	@Test
	public void testaRetornaFalseHasNext(){
		Stream stream = new StreamImpl("aAbBABacafewAqAq");

		Assert.assertEquals(true, stream.hasNext());
		Assert.assertEquals('e', stream.getNext());
		
		Assert.assertEquals(true, stream.hasNext());
		
		try{
			stream.getNext();
			Assert.assertTrue(false);
		}catch(RuntimeException ex){
			Assert.assertEquals("Não foi encontrado sequencia: vogal consoante vogal, onde a segunda vogal não tenha aparecido anteriormente", ex.getMessage());
		}
		
		Assert.assertEquals(false, stream.hasNext());
		
	}
	

	@Test
	public void testa2SequenciaSeguidas(){
		Stream stream = new StreamImpl("aAbBABacafewiq");

		Assert.assertEquals(true, stream.hasNext());
		Assert.assertEquals('e', stream.getNext());
		
		Assert.assertEquals(true, stream.hasNext());
		Assert.assertEquals('i', stream.getNext());
		
		Assert.assertEquals(true, stream.hasNext());
		try{
			stream.getNext();
			Assert.assertTrue(false);
		}catch(RuntimeException ex){
			Assert.assertEquals("Não foi encontrado sequencia: vogal consoante vogal, onde a segunda vogal não tenha aparecido anteriormente", ex.getMessage());
		}
		
		Assert.assertEquals(false, stream.hasNext());
		
	}
}
