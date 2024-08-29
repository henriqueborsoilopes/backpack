package br.unipar.backpack;

public enum Item {

	ITEM8(8, "Faca",         1, 10),
	ITEM2(2, "Fósforo",      1, 10),
	ITEM1(1, "Barraca",      3, 10),
	ITEM4(4, "Comida",       3, 10),
	ITEM7(7, "Lampião",      1, 3),
	ITEM3(3, "Garrafas",     1, 5),
	ITEM5(5, "Colchonete",   2, 6),
	ITEM10(10, "Roupas",     2, 6),
	ITEM6(6, "Fogareiro",    2, 8),
	ITEM9(9, "Tralha pesca", 3, 2);

	private int posicao;
    private String descricao;
    private int peso;
    private int importancia;

    private Item(int posicao, String descricao, int peso, int importancia) {
        this.descricao = descricao;
        this.peso = peso;
        this.importancia = importancia;
        this.posicao = posicao;
    }

	public int getPosicao() {
		return posicao;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getPeso() {
		return peso;
	}

	public int getImportancia() {
		return importancia;
	}
	
	public static Item toEnum(int posicao) {
		for(Item item : Item.values()) {
			if (item.getPosicao() == posicao) {
				return item;
			}
		}
		return null;
	}
}
