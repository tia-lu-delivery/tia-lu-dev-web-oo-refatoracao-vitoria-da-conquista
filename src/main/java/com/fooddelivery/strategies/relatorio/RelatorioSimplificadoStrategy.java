package com.fooddelivery.strategies.relatorio;

import com.fooddelivery.model.Pedido;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioSimplificadoStrategy implements RelatorioStrategy {
    
    private final List<Pedido> pedidosHoje;

    public RelatorioSimplificadoStrategy(List<Pedido> pedidosHoje) {
        this.pedidosHoje = pedidosHoje;
    }

    @Override
    public String gerarRelatorio() {
        int totalPedidos = pedidosHoje.size();
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (Pedido pedido : pedidosHoje) {
            valorTotal = valorTotal.add(pedido.calcularTotal());
        }

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("\n=== RELATÓRIO SIMPLIFICADO - ")
                .append(java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .append(" ===\n\n");
        relatorio.append("Total de Pedidos: ").append(totalPedidos).append("\n");
        relatorio.append("Valor Total Arrecadado: R$ ").append(valorTotal).append("\n");
        relatorio.append("Valor Médio por Pedido: R$ ");

        if (totalPedidos > 0) {
            BigDecimal valorMedio = valorTotal.divide(BigDecimal.valueOf(totalPedidos), 2, BigDecimal.ROUND_HALF_UP);
            relatorio.append(valorMedio);
        } else {
            relatorio.append("0.00");
        }

        return relatorio.toString();
    }
}