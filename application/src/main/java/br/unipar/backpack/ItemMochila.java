package br.unipar.backpack;

public class ItemMochila {
	
    private int vaiNaMochila;
    private Item item;
    
	public int getVaiNaMochila() {
		return vaiNaMochila;
	}
	
	public void setVaiNaMochila(int vaiNaMochila) {
		this.vaiNaMochila = vaiNaMochila;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "                Item mochila [item = " + item.getDescricao() + ", peso = " + item.getPeso() + ", importância = " + item.getImportancia() + "]";
	}
}
