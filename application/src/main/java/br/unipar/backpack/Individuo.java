package br.unipar.backpack;

import java.util.Random;

public class Individuo {

	private ItemMochila[] itemMochila = new ItemMochila[10];
	private int C = 1;
	private double aptidao = 0;
	
	public ItemMochila[] getItemMochila() {
		return itemMochila;
	}
	
	public void setItemMochila(ItemMochila[] itemMochila) {
		this.itemMochila = itemMochila;
	}
	
	public int getC() {
		return C;
	}
	
	public void setC(int c) {
		C = c;
	}
	
	public double getAptidao() {
		return aptidao;
	}
	
	public void setAptidao(double aptidao) {
		this.aptidao = aptidao;
	}
	
    public void popularIndividuo() {
		Random random = new Random();
		int soma_peso = 0;
		int excesso_peso = 0;

		for (int i = 0; i < itemMochila.length; i++) {
			itemMochila[i] = new ItemMochila();
			itemMochila[i].setItem(Item.toEnum(i+1));

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
		return "Individuo [ aptidao=" + aptidao + " mochila = " + getItensMochila() + "]";
	}
	
	private String getItensMochila() {
		String itens = "";
		
		for (ItemMochila item : itemMochila) {
			if (item.getVaiNaMochila() == 1) {
				itens += item.toString() + ",";
			}
		}
		return itens;
	}
}
