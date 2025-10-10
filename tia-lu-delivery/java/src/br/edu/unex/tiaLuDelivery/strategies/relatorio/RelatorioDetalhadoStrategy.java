package br.edu.unex.tiaLuDelivery.strategies.relatorio;

import br.edu.unex.tiaLuDelivery.model.Order;
import br.edu.unex.tiaLuDelivery.model.OrderItem;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Estratégia para geração de relatórios detalhados.
 * 
 * Esta classe implementa o padrão Strategy para gerar relatórios completos
 * contendo informações detalhadas sobre cada pedido, incluindo dados do cliente,
 * itens pedidos, preços e totais.
 * 
 */
public class RelatorioDetalhadoStrategy implements RelatorioStrategy {

    /**
     * Lista de pedidos que serão incluídos no relatório detalhado.
     */
    private final List<Order> pedidosHoje;

    /**
     * Construtor que inicializa a estratégia com a lista de pedidos.
     * 
     * @param pedidosHoje Lista de pedidos para incluir no relatório detalhado.
     *                    Não pode ser null.
     * @throws IllegalArgumentException se a lista de pedidos for null
     */
    public RelatorioDetalhadoStrategy(List<Order> pedidosHoje) {
        if (pedidosHoje == null) {
            throw new IllegalArgumentException("A lista de pedidos não pode ser null");
        }
        this.pedidosHoje = pedidosHoje;
    }

    /**
     * Gera um relatório detalhado com informações completas dos pedidos.
     * 
     * O relatório inclui para cada pedido:
     * 
     * - ID do pedido
     * - Informações do cliente (nome, ID, telefone)
     * - Status do pedido
     * - Lista detalhada de itens com quantidades e preços
     * - Total do pedido
     * 
     * Apresenta um resumo geral com:
     * 
     * - Total de pedidos
     * - Valor total arrecadado
     * 
     * @return String contendo o relatório detalhado formatado
     */
    @Override
    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("\n=== RELATÓRIO DETALHADO - ")
                .append(java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .append(" ===\n\n");

        if (pedidosHoje.isEmpty()) {
            relatorio.append("Nenhum pedido registrado hoje.\n");
        } else {
            BigDecimal valorTotalGeral = BigDecimal.ZERO;

            for (Order pedido : pedidosHoje) {
                relatorio.append("──────────────────────────────────────────────────\n");
                relatorio.append("Pedido #").append(pedido.getId())
                        .append("\n");
                relatorio.append("Cliente: ").append(pedido.getCustomer().getName()).append(" (ID: ")
                        .append(pedido.getCustomer().getId()).append(")\n");
                relatorio.append("Telefone: ").append(pedido.getCustomer().getPhoneNumber()).append("\n");
                relatorio.append("Status: ").append(pedido.getStatus()).append("\n\n");

                relatorio.append("Itens:\n");
                for (OrderItem item : pedido.getItems()) {
                    relatorio.append(" | ").append(item.getMenuItem().getName());
                    relatorio.append(" | Qtd: ").append(item.getAmount());
                    relatorio.append(" | Preço Unit: R$ ").append(item.getMenuItem().getUnitPrice());
                    relatorio.append(" | Subtotal: R$ ").append(item.subtotal()).append("\n");
                }

                BigDecimal totalPedido = pedido.total();
                relatorio.append("\nTOTAL DO PEDIDO: R$ ").append(totalPedido).append("\n\n");
                valorTotalGeral = valorTotalGeral.add(totalPedido);
            }

            relatorio.append("──────────────────────────────────────────────────\n\n");
            relatorio.append("RESUMO GERAL:\n");
            relatorio.append("Total de Pedidos: ").append(pedidosHoje.size()).append("\n");
            relatorio.append("Valor Total Arrecadado: R$ ").append(valorTotalGeral).append("\n");
        }

        return relatorio.toString();
    }
}