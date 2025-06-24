INSERT INTO processo (id, numero_processo, comarca, vara, status, nome_reclamante, nome_reclamado) VALUES
(1, '0000001-01.2025.1.01.0001', 'Comarca Central', '1ª Vara Cível', 'ATIVO', 'João da Silva', 'Empresa XYZ Ltda'),
(2, '0000002-02.2025.1.01.0002', 'Comarca Central', '2ª Vara Cível', 'ATIVO', 'Maria Oliveira', 'Empresa ABC S.A.'),
(3, '0000003-03.2025.1.02.0001', 'Comarca Leste', 'Vara da Família', 'SUSPENSO', 'Pedro Santos', 'Joana Santos'),
(4, '0000004-04.2024.1.01.0001', 'Comarca Central', '1ª Vara Cível', 'ARQUIVADO', 'Ana Costa', 'Seguradora Segura S.A.'),
(5, '0000005-05.2025.1.03.0001', 'Comarca Oeste', 'Vara do Trabalho', 'ATIVO', 'Carlos Souza', 'Construtora Grande Obra');

INSERT INTO audiencia (id, processo_id, tipo, data_hora, local) VALUES
(1, 1, 'INSTRUCAO', '2025-06-25 10:00:00', 'Sala 101'),
(2, 2, 'INSTRUCAO', '2025-06-26 14:30:00', 'Sala 202'),
(3, 2, 'JULGAMENTO', '2025-08-15 09:00:00', 'Sala 202'),
(4, 3, 'CONCILIACAO', '2025-07-01 11:00:00', 'Sala 301'),
(5, 5, 'INSTRUCAO', '2025-07-10 15:00:00', 'Sala 401');
