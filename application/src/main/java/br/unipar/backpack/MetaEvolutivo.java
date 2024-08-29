package br.unipar.backpack;

import java.util.Arrays;
import java.util.Random;

public class MetaEvolutivo {

    private double[] vetor_variancia = new double[10];
    private Individuo[] populacao = new Individuo[10];
    private Individuo[] populacao2 = new Individuo[10];
    private Individuo[] populacaoNova = new Individuo[10];

    private int eras = 1000000;
    private int eraAtual;
    private double soma_aptidoes = 0;

    public MetaEvolutivo() {
        inicializarPopulacao();
        for (int i = 0; i < eras; i++) {
            eraAtual = i;
            executarEra();
        }
    }
    
    private void inicializarPopulacao() {
        for (int i = 0; i < populacao.length; i++) {
            Individuo individuo = new Individuo();
            individuo.popularIndividuo();
            populacao[i] = individuo;
        }
    }

    private void executarEra() {
        calcularDesvioPadrao();
        selecionarEPerturbarIndividuos();
        ordenarECombinarPopulacoes();
    }

    private void calcularDesvioPadrao() {
        for (int i = 0; i < vetor_variancia.length; i++) {
            double sum = 0;
            double sumOfSquares = 0;
            int n = populacao.length;

            for (int j = 0; j < n; j++) {
                int dp = populacao[j].getItemMochila()[i].getVaiNaMochila();
                sum += dp;
                sumOfSquares += dp * dp;
            }

            double mean = sum / n;
            double variance = (sumOfSquares / n) - (mean * mean);
            double stdDeviation = Math.sqrt(variance);

            vetor_variancia[i] = stdDeviation;
        }
    }

    private void selecionarEPerturbarIndividuos() {
        soma_aptidoes = 0;
        for (int i = 0; i < populacao.length; i++) {
            soma_aptidoes += populacao[i].getAptidao();
        }

        Random rand = new Random();
        for (int j = 0; j < populacao2.length; j++) {
            double selecao = rand.nextDouble() * soma_aptidoes;

            Individuo selecionado = new Individuo();
            double soma_parcial = 0;
            for (int i = 0; i < populacao.length; i++) {
                soma_parcial += populacao[i].getAptidao();
                if (soma_parcial >= selecao) {
                    selecionado = populacao[i];
                    break;
                }
            }

            populacao2[j] = perturbarIndividuo(selecionado, vetor_variancia);
        }
    }

    private void ordenarECombinarPopulacoes() {        
        Arrays.sort(populacao, (o1, o2) -> Double.compare(o2.getAptidao(), o1.getAptidao()));
        Arrays.sort(populacao2, (p1, p2) -> Double.compare(p2.getAptidao(), p1.getAptidao()));

        int numBest = Math.min(5, populacao.length);
        System.arraycopy(populacao, 0, populacaoNova, 0, numBest);
        System.arraycopy(populacao2, 0, populacaoNova, numBest, numBest);

        populacao = populacaoNova;
        
        if (eraAtual == 0 || eraAtual == (eras / 2) || (eraAtual + 1) == eras) {

            System.out.println("*************************** INÍCIO DA ERA " + eraAtual + " *********************************");
            for (int i = 0; i < populacao.length; i++) {
            	System.out.println((i + 1) + "º " + populacao[i].toString());
            }
            System.out.println("*************************** FIM DA ERA " + eraAtual + " *********************************");
        }
    }

    private Individuo perturbarIndividuo(Individuo selecionado, double[] vetor_variancia) {
        Random rand = new Random();
        for (int i = 0; i < vetor_variancia.length; i++) {
            double r = rand.nextDouble();

            if (r < vetor_variancia[i]) {
                int itemVaiNaMochila = selecionado.getItemMochila()[i].getVaiNaMochila();
                selecionado.getItemMochila()[i].setVaiNaMochila(itemVaiNaMochila == 1 ? 0 : 1);
            }
        }
        return selecionado;
    }
}