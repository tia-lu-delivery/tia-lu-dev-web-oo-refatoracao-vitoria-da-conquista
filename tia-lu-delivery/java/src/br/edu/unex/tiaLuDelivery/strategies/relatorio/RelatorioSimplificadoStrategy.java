package br.edu.unex.tiaLuDelivery.strategies.relatorio;

import br.edu.unex.tiaLuDelivery.model.Order;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Estratégia para geração de relatórios simplificados.
 * 
 * Esta classe implementa o padrão Strategy para gerar relatórios resumidos
 * contendo apenas as informações essenciais: total de pedidos, valor total
 * arrecadado e valor médio por pedido.
 * 
 */
public class RelatorioSimplificadoStrategy implements RelatorioStrategy {

    /**
     * Lista de pedidos que serão incluídos no relatório.
     */
    private final List<Order> pedidosHoje;

    /**
     * Construtor que inicializa a estratégia com a lista de pedidos.
     * 
     * @param pedidosHoje Lista de pedidos para incluir no relatório.
     *                    Não pode ser null.
     * @throws IllegalArgumentException se a lista de pedidos for null
     */
    public RelatorioSimplificadoStrategy(List<Order> pedidosHoje) {
        if (pedidosHoje == null) {
            throw new IllegalArgumentException("A lista de pedidos não pode ser null");
        }
        this.pedidosHoje = pedidosHoje;
    }

    /**
     * Gera um relatório simplificado com informações resumidas dos pedidos.
     * 
     * O relatório inclui:
     * 
     * - Total de pedidos
     * - Valor total arrecadado
     * - Valor médio por pedido
     * 
     * @return String contendo o relatório simplificado formatado
     */
    @Override
    public String gerarRelatorio() {
        int totalPedidos = pedidosHoje.size();
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (Order pedido : pedidosHoje) {
            valorTotal = valorTotal.add(pedido.total());
        }

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("\n=== RELATÓRIO SIMPLIFICADO - ")
                .append(java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .append(" ===\n\n");
        relatorio.append("Total de Pedidos: ").append(totalPedidos).append("\n");
        relatorio.append("Valor Total Arrecadado: R$ ").append(valorTotal).append("\n");
        relatorio.append("Valor Médio por Pedido: R$ ");

        if (totalPedidos > 0) {
            BigDecimal valorMedio = valorTotal.divide(BigDecimal.valueOf(totalPedidos), 2, RoundingMode.HALF_UP);
            relatorio.append(valorMedio);
        } else {
            relatorio.append("0.00");
        }

        return relatorio.toString();
    }
}