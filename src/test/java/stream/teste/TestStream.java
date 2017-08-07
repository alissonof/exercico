package stream.teste;



import org.junit.Assert;
import org.junit.Test;

import stream.Main;
import stream.Stream;
import stream.impl.StreamImpl;

public class TestStream {

	@Test
	public void testa(){
		Stream stream = new StreamImpl("aAbBABacafe");
		
		char caractere = Main.firstChar(stream);
		Assert.assertEquals('e', caractere);
	}
	

	@Test
	public void testa2(){
		Stream stream = new StreamImpl("aAbBABacaffewiq");
		
		char caractere = Main.firstChar(stream);
		Assert.assertEquals('i', caractere);
	}
	
	@Test
	public void testa3(){
		Stream stream = new StreamImpl("abaaji");
		
		char caractere = Main.firstChar(stream);
		Assert.assertEquals('i', caractere);	
	}
	
	@Test
	public void testa4(){
		Stream stream = new StreamImpl("abaji");
		
		char caractere = Main.firstChar(stream);
		Assert.assertEquals('i', caractere);
	}
	
	@Test
	public void testa5(){
		Stream stream = new StreamImpl("abaabaaba");
		
		try{
			Main.firstChar(stream);
			Assert.assertFalse(true);
		}catch(Exception e){
			Assert.assertTrue(true);
		}
	}
	
}
