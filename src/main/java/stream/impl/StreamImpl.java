package stream.impl;

import java.util.HashSet;
import java.util.Set;

import stream.Stream;

public class StreamImpl implements Stream{

	private char[] source;
	private int count;
	private Set<Character> vowelList;

	public StreamImpl(String source){
		this.source = source.toCharArray();
		count = 0;
		vowelList = new HashSet<Character>();
	}

	@Override
	public char getNext() {

		char result = findNext();

		// caso não encontre a sequencia vogal consoante vogal será gerado uma exceção
		if(result == '!'){
			count = source.length;
			throw new RuntimeException("Não foi encontrado sequencia: vogal consoante vogal, onde a segunda vogal não tenha aparecido anteriormente");
		}

		return result;
	}

	@Override
	public boolean hasNext() {
		return count != source.length;
	}

	/**
	 * Encontra a próxima vogal que corresponda a sequencia vogal consoante vogal
	 * @return a segunda vogal que corresponde a sequencia vogal consoante vogal
	 */
	private char findNext(){
		// A exclamação indica fim do stream, caso retorne exclamação significa que o stream chegou ao fim
		char result = '!';
		
		for(int i = count; i < source.length; i++){
			
			// Verica se é uma vogal
			// e se o valor atual + 2 não estoura o tamanho do array
			if(isVowel(source[i]) && ((i+2) < source.length)){	
				
				// adiciona a vogal na lista vogal exibida
				vowelList.add(source[i]);
				
				// Verifica se os 2 próximos caracteres correspondem a consoante e vogal
				if(isConsonant(source[i + 1]) && isVowel(source[i + 2])){
					
					// Verifica se a vogal já apareceu antes
					if(vowelList.contains(source[i + 2])){
						// avança o contador
						count = i + 1;
						// inicia a procura pela sequencia vogal caractere vogal
						result = findNext();
					} else{
						// avança o contador para a segunda vogal da sequencia
						count = i + 2;
						// pega o valor da segunda vogal para ser retornado
						result = source[count];
					}
					
				}else{					
					// avança o contador
					count = i + 1;
					// inicia a procura pela sequencia vogal caractere vogal
					result = findNext();
				}
				
				// sai do loop for
				break;
			}
		}
		return result;
	}

	/**
	 * Verifica  se o caractere é uma consoante
	 * @param caractere o caractere a ser análizado
	 * @return true se for consoante false caso contrário
	 */
	private boolean isConsonant(char caractere) {

		int caractereToInt = caractere;

		caractereToInt = convertToLowerCase(caractereToInt);

		if((caractereToInt > 96 && caractereToInt < 123) &&
				caractereToInt != 97 &&
				caractereToInt != 101 &&
				caractereToInt != 105 &&
				caractereToInt != 111 &&
				caractereToInt != 117){
			return true;
		}

		return false;
	}

	/**
	 * Verifica se o caractere é uma vogal
	 * @param caractere o caractere a ser análizado
	 * @return true se for vogal false caso contrário
	 */
	private boolean isVowel(char caractere) {
		int caractereToInt = caractere;

		// converte para minusculo
		caractereToInt = convertToLowerCase(caractereToInt);

		if(caractereToInt == 97 ||
				caractereToInt == 101 ||
				caractereToInt == 105 ||
				caractereToInt == 111 ||
				caractereToInt == 117){
			return true;
		}

		return false;
	}

	/**
	 * Caso a letra esta em maiusculo converte para minusculo
	 * @param caractere o caractera que será análizado
	 * @return caracatere em minusculo
	 */
	private int convertToLowerCase(int caractere){
		if(caractere > 64 && caractere < 91){
			return caractere + 32;
		}else{
			return caractere;
		}
	}

}