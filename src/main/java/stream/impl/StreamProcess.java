package stream.impl;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import stream.Stream;

public class StreamProcess {

	private Map<Character, Long> map = new HashMap<Character, Long>();
	private Set<ConsonantVowel> list = new  LinkedHashSet<ConsonantVowel>();

	public char process(Stream input){

		ConsonantVowel consonantVowel = null;
		boolean flagConsonant = false;
		char result;

		// Separa o conjunto consoante vogal que são precedidos por uma vogal
		while(input.hasNext()){
			char caractere = input.getNext();

			// Se não for uma letra nem vale nem continua a lógica
			if(isLetter(caractere)){
				countChar(caractere);
				if(isVowel(caractere) && consonantVowel == null){
					consonantVowel = new ConsonantVowel();
				}else if(isConsonant(caractere) && consonantVowel != null && !flagConsonant){
					consonantVowel.setConsonant(caractere);
					flagConsonant = true;
				}else if(isVowel(caractere) && consonantVowel != null && flagConsonant){
					consonantVowel.setVowel(caractere);
					list.add(consonantVowel);
					consonantVowel = new ConsonantVowel();
					flagConsonant = false;
				// Caso chegue e aqui e seja consoante significa que é uma 
				// consoante seguida da outra por isso se inicia tudo	
				}else if(isConsonant(caractere)){
					consonantVowel = null;
					flagConsonant = false;
				}
			}else{
				consonantVowel = null;
				flagConsonant = false;
			}
		}
		
		// verifica se o conjunto consoante vogal aparece apenas uma vez na stream
		for(ConsonantVowel cV : list){

			if(map.get(cV.getConsonant()) == 1 &&
					map.get(cV.getVowel()) == 1){
				result = cV.getVowel();
				return result;
			}
		}


		throw new RuntimeException("Não foi encontrado nenhuma ocorrência.");
	}

	/**
	 * Conta a quantidade de vezes que cada caractere aparace no stream
	 * @param caractere
	 */
	private void countChar(char caractere){
		Long count = map.get(caractere);
		if(count == null){
			map.put(caractere, 1l);
		}else{
			count++;
			map.put(caractere, count);
		}
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
	/**
	 * Verifica se o caractere é uma letra
	 * @param caractere
	 * @return true se for uma letra false caso contrário
	 */
	private boolean isLetter(char caractere){
		int caractereToInt = caractere;
	
		if((caractereToInt > 64 && caractereToInt < 91) ||
			(caractereToInt > 96 && caractereToInt < 123)){
			return true;
		}
		
		return false;
	}

	private class ConsonantVowel{
		private char vowel;
		private char consonant;

		/**
		 * @return the vowel
		 */
		public char getVowel() {
			return vowel;
		}

		/**
		 * @param vowel the vowel to set
		 */
		public void setVowel(char vowel) {
			this.vowel = vowel;
		}

		/**
		 * @return the consonant
		 */
		public char getConsonant() {
			return consonant;
		}

		/**
		 * @param consonant the consonant to set
		 */
		public void setConsonant(char consonant) {
			this.consonant = consonant;
		}

		@Override
		public int hashCode() {
			return consonant * vowel;
		}

		@Override
		public boolean equals(Object obj) {
			
			if(obj != null && obj instanceof ConsonantVowel){
				ConsonantVowel other = (ConsonantVowel) obj;
				if(consonant == other.getConsonant() &&
						vowel == other.getVowel()){
					return true;
				}
			}
			
			return false;
		}		

	}
}
