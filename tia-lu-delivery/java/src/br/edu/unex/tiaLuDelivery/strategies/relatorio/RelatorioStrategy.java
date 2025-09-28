package br.edu.unex.tiaLuDelivery.strategies.relatorio;

/**
 * Interface que define o contrato para estratégias de geração de relatórios.
 * 
 * Esta interface é parte da implementação do padrão Strategy, permitindo que diferentes
 * algoritmos de geração de relatórios sejam utilizados.
 * 
 */
public interface RelatorioStrategy {

    /**
     * Gera um relatório baseado na estratégia implementada.
     * 
     * Este método deve ser implementado por cada estratégia,
     * definindo como os dados serão formatados e apresentados no relatório.
     * 
     * @return String contendo o relatório formatado
     */
    String gerarRelatorio();
}