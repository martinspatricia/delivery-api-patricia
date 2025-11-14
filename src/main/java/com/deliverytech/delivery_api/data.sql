-- Inserir clientes de teste
INSERT INTO clientes (nome, email, telefone, endereco, ativo, data_cadastro) VALUES
('João Silva', 'joao@email.com', '11999999999', 'Rua A, 123', true, CURRENT_TIMESTAMP),
('Maria Santos', 'maria@email.com', '11888888888', 'Rua B, 456', true, CURRENT_TIMESTAMP),
('Pedro Oliveira', 'pedro@email.com', '11777777777', 'Rua C, 789', true, CURRENT_TIMESTAMP);

-- Inserir restaurantes de teste
INSERT INTO restaurantes (nome, categoria, endereco, telefone, taxa_entrega, ativo) VALUES
('Pizzaria do Zé', 'Pizza', 'Rua Central, 100', '1133333333', 5.00, true),
('Hamburgueria Top', 'Lanches', 'Av. Principal, 200', '1144444444', 7.50, true),
('Sushi Master', 'Japonesa', 'Rua Oriental, 300', '1155555555', 10.00, true);