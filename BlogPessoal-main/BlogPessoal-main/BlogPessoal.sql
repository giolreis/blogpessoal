SELECT * FROM db_blogpessoal.tb_postagens;
USE db_blogpessoal;

-- Limpar a tabela
-- TRUNCATE TABLE tb_postagens;

-- Inserindo os valores a nossa tabela de postagens
INSERT INTO tb_postagens(data, texto, titulo)
VALUES(current_timestamp(), "Realizei um curso de quatro dias com foco em aprendizado Python básico", "Diploma Básico Python"),
(current_timestamp(), "Completei uma graduação técnica de um ano e meio em desenvolvimento de sistemas", "Graduação Técnica DS"),
(current_timestamp(), "Realizei o teste de grau de fluência de inglês do Michigan University", "MET Test - B2"),
(current_timestamp(), "Trabalhei durante 4 meses como monitora infantil de inglês", "FISK Monitoria");
