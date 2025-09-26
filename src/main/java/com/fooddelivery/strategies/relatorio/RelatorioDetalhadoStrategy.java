package com.fooddelivery.strategies.relatorio;

import com.fooddelivery.model.Pedido;
import com.fooddelivery.model.ItemPedido;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioDetalhadoStrategy implements RelatorioStrategy {
    
    private final List<Pedido> pedidosHoje;

    public RelatorioDetalhadoStrategy(List<Pedido> pedidosHoje) {
        this.pedidosHoje = pedidosHoje;
    }

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

            for (Pedido pedido : pedidosHoje) {
                relatorio.append("──────────────────────────────────────────────────\n");
                relatorio.append("Pedido #").append(pedido.getId()).append(" - ").append(pedido.getDataHora())
                        .append("\n");
                relatorio.append("Cliente: ").append(pedido.getCliente().getNome()).append(" (ID: ")
                        .append(pedido.getCliente().getId()).append(")\n");
                relatorio.append("Telefone: ").append(pedido.getCliente().getTelefone()).append("\n");
                relatorio.append("Status: ").append(pedido.getStatus()).append("\n\n");

                relatorio.append("Itens:\n");
                for (ItemPedido item : pedido.getItens()) {
                    relatorio.append(" | ").append(item.getItem().getNome());
                    relatorio.append(" | Qtd: ").append(item.getQuantidade());
                    relatorio.append(" | Preço Unit: R$ ").append(item.getPrecoUnitario());
                    relatorio.append(" | Subtotal: R$ ").append(item.calcularSubtotal()).append("\n");
                }

                BigDecimal totalPedido = pedido.calcularTotal();
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