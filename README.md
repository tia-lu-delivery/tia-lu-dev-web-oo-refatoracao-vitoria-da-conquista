# 🍔 Sistema de Pedidos - Refatoração com Padrões de Projeto

## 📌 Visão Geral
A criação de software é um processo contínuo. Inicialmente, o foco está em atender aos requisitos funcionais. No entanto, à medida que a aplicação cresce e a complexidade aumenta, a estrutura original do código pode se tornar um obstáculo para manutenção e evolução.  

Este projeto aplica **refatoração profunda** utilizando **padrões de projeto** para garantir que a aplicação de pedidos de comida possa evoluir de forma sustentável, modular e flexível.

---

## 🏗️ Padrões de Projeto Implementados

### 🔨 Builder (Construtor)
- Simplifica a criação de objetos `Pedido`.
- Garante que o pedido só é criado quando todas as informações necessárias estiverem presentes.
- Classe principal: `PedidoBuilder`.

---

### 🔄 State (Estado)
- Gerencia o ciclo de vida do pedido de forma clara e sem if/else aninhados.
- Cada estado do pedido é representado por uma classe separada que implementa `IPedidoEstado`.
- **Novos estados incluídos:**
  - `AguardandoAceiteEstado`
  - `RejeitadoEstado`
  - `CanceladoEstado`
  - `AguardandoEntregadorEstado`
  - `EmEntregaEstado`

**Exemplo de transições:**
- `AGUARDANDO_ACEITE → ACEITO | REJEITADO | CANCELADO`
- `ACEITO → PREPARANDO`
- `PREPARANDO → AGUARDANDO_ENTREGADOR`
- `AGUARDANDO_ENTREGADOR → EM_ENTREGA`

---

### 🧩 Composite (Composto)
- Permite tratar **produtos individuais** e **combos/kits** de forma uniforme.
- Estrutura de árvore:
  - **Folha (Leaf):** `Produto`
  - **Composite:** `Combo`, `KitProdutos`, ou até o próprio `Pedido`
- Exemplo: cálculo de preço total usando `ItemPedido.calcularPreco()`.

---

### 👀 Observer (Observador)
- Notifica automaticamente partes do sistema quando um pedido muda de estado.
- **Exemplos de observadores:**
  - `Cliente` → recebe notificações de atualização do pedido.
  - `FilaEntrega` → adiciona pedido à lista de entregas quando o status muda para `AGUARDANDO_ENTREGADOR`.

---

### 🎭 Strategy (Estratégia)
- Flexibiliza a geração de relatórios.
- Implementações:
  - `RelatorioSimplificadoStrategy`
  - `RelatorioDetalhadoStrategy`
- Facilita a adição de novos tipos de relatórios sem alterar a lógica central.

---

### 🧱 Facade (Fachada)
- Simplifica a interface para a CLI (linha de comando).
- Classe `SistemaPedidosFacade` fornece métodos unificados como:
  - `registrarNovoCliente()`
  - `criarPedido()`
- Oculta a complexidade interna do sistema.

---

### 🔒 Singleton (Instância Única)
- Garante uma única instância para gerenciar dados centralizados:
  - Clientes
  - Cardápio
  - Pedidos
- Evita inconsistências e duplicação de dados.

---

## 🚀 Fluxo de um Pedido
1. **Cliente cria pedido** → Estado inicial: `AGUARDANDO_ACEITE`.
2. **Estabelecimento aceita/rejeita**:
   - Aceito → `ACEITO → PREPARANDO → AGUARDANDO_ENTREGADOR → EM_ENTREGA → ENTREGUE`.
   - Rejeitado → `REJEITADO`.
   - Cancelado pelo cliente → `CANCELADO`.
3. **Observer** notifica cliente e serviços externos a cada mudança de estado.
4. **Composite** garante cálculo correto do valor do pedido, seja simples ou complexo.
5. **Strategy** define qual tipo de relatório será gerado.
6. **Facade** fornece interface única para uso via CLI.

---

## 👥 Equipe
Este projeto foi desenvolvido por uma equipe de **5 membros**:  
- Membro 1  
- Membro 2  
- Membro 3  
- Membro 4  
- Membro 5  

---

## 📌 Conclusão
A refatoração utilizando **Builder, State, Composite, Observer, Strategy, Facade e Singleton** torna o sistema de pedidos:
- Mais **modular**
- Mais **flexível**
- Mais **fácil de manter e evoluir**
- Preparado para **crescimento sustentável**
