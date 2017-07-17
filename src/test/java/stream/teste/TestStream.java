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
	public void testaChamaGetNextSemChamarHasNext(){
		Stream stream = new StreamImpl("aAbBABacafewA");

		try{
			stream.getNext();
			Assert.assertTrue(false);
		}catch(RuntimeException ex){
			Assert.assertEquals("Não pode invocar o método getNext sem antes chamar o método hasNext", ex.getMessage());
		}
			
	}

	@Test
	public void testaRetornaFalseHasNext(){
		Stream stream = new StreamImpl("aAbBABacafewAqAq");
		
		int count = 0;
		while(stream.hasNext()){
			
			// É esperado que entre somente uma vez
			Assert.assertTrue(count < 1);
			Assert.assertEquals('e', stream.getNext());
			count++; 
		}
		

		try{
			stream.getNext();
			Assert.assertTrue(false);
		}catch(RuntimeException ex){
			Assert.assertEquals("Já Chegou no fim do Stream", ex.getMessage());
		}
		
		Assert.assertEquals(false, stream.hasNext());
		
	}
	

	@Test
	public void testa2SequenciaSeguidas(){
		Stream stream = new StreamImpl("aAbBABacafewiq");
		
		int count = 0;
		while(stream.hasNext()){
			
			if(count == 0){
				Assert.assertEquals('e', stream.getNext());
			}else if(count == 1){
				Assert.assertEquals('i', stream.getNext());
			}else{
				// não pode cair no else pois significa ele entrou mais de duas vezes dentro do while
				// o que não é esperado
				Assert.assertTrue(false);
			}
			
			count++;
			
		}
		
	}
}
