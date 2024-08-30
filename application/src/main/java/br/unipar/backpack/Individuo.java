package br.unipar.backpack;

import java.util.Random;

public class Individuo {

	private ItemMochila[] itemMochila = new ItemMochila[10];
	private int C = 1;
	private double aptidao = 0;
	private double peso = 0.0;

	public ItemMochila[] getItemMochila() {
		return itemMochila;
	}
	
	public double getAptidao() {
		return aptidao;
	}
	
    public void popularIndividuo() {
		Random random = new Random();
		int soma_peso = 0;
		int excesso_peso = 0;

		for (int i = 0; i < itemMochila.length; i++) {
			itemMochila[i] = new ItemMochila();
			itemMochila[i].setItem(Item.toEnum((i + 1)));

			itemMochila[i].setVaiNaMochila(random.nextInt(2));
			soma_peso += itemMochila[i].getItem().getPeso();

			if (soma_peso > 12) {
				excesso_peso = soma_peso - 12;
			}

			if (itemMochila[i].getVaiNaMochila() == 1) {
				aptidao += (itemMochila[i].getItem().getImportancia() / itemMochila[i].getItem().getPeso()) - (C * excesso_peso);
			}
		}

		if (aptidao < 0.0) {
			aptidao = 0.1;
		}
	}
    
	@Override
	public String toString() {
		String itens = getItensMochila();
		return "Indivíduo [ aptidao = " + Math.round(aptidao) + ", peso da mochila = " + peso + ",\n        mochila = [" + itens + "]]\n";
	}
	
	private String getItensMochila() {
		String itens = "";
		peso = 0.0;
			
		for (ItemMochila item : itemMochila) {
			if (item.getVaiNaMochila() == 1) {
				peso += item.getItem().getPeso();
				itens += "\n" + item.toString();
			}
		}
		return itens;
	}
}
