-- Usuario (Inserts)
INSERT INTO usuario (nome, senha, email, cep, cidade, uf, role)
VALUES ('Enzo Okuizumi', '123456', 'enzo@email.com', '01001-000', 'São Paulo', 'SP', 'USER');
INSERT INTO usuario (nome, senha, email, cep, cidade, uf, role)
VALUES ('Ana Souza', '123456', 'ana@email.com', '20040-000', 'Rio de Janeiro', 'RJ', 'USER');
INSERT INTO usuario (nome, senha, email, cep, cidade, uf, role)
VALUES ('Carlos Silva', '123456', 'carlos@email.com', '30110-010', 'Belo Horizonte', 'MG', 'USER');
INSERT INTO usuario (nome, senha, email, cep, cidade, uf, role)
VALUES ('Mariana Costa', '123456', 'mariana@email.com', '80010-000', 'Curitiba', 'PR', 'USER');
INSERT INTO usuario (nome, senha, email, cep, cidade, uf, role)
VALUES ('Joao Santos', '123456', 'joao@email.com', '70040-010', 'Brasília', 'DF', 'USER');
INSERT INTO usuario (nome, senha, email, cep, cidade, uf, role)
VALUES ('Fernanda Lima', '123456', 'fernanda@email.com', '40026-010', 'Salvador', 'BA', 'USER');
INSERT INTO usuario (nome, senha, email, cep, cidade, uf, role)
VALUES ('Administrador', 'admin123', 'admin@campusgigs.com', '01310-100', 'São Paulo', 'SP', 'ADMIN');

-- Servico (Inserts)
INSERT INTO servico (titulo, descricao, categoria, preco, usuario_id, situacao)
VALUES (
           'Desenvolvimento de site',
           'Criacao de sites utilizando HTML, CSS e JavaScript.',
           'Programacao',
           500.00,
           1,
           'ATIVO'
       );

INSERT INTO servico (titulo, descricao, categoria, preco, usuario_id, situacao)
VALUES (
           'Criacao de logo',
           'Criacao de logotipos personalizados.',
           'Design',
           150.00,
           2,
           'ATIVO'
       );

INSERT INTO servico (titulo, descricao, categoria, preco, usuario_id, situacao)
VALUES (
           'Edicao de video',
           'Edicao de videos para redes sociais.',
           'Audiovisual',
           200.00,
           3,
           'ATIVO'
       );

INSERT INTO servico (titulo, descricao, categoria, preco, usuario_id, situacao)
VALUES (
           'Aulas de ingles',
           'Aulas particulares de ingles.',
           'Educacao',
           80.00,
           4,
           'ATIVO'
       );

INSERT INTO servico (titulo, descricao, categoria, preco, usuario_id, situacao)
VALUES (
           'Manutencao de computador',
           'Formatacao e manutencao de computadores.',
           'Tecnologia',
           120.00,
           5,
           'ATIVO'
       );

INSERT INTO servico (titulo, descricao, categoria, preco, usuario_id, situacao)
VALUES (
           'Criacao de posts',
           'Criacao de artes para redes sociais.',
           'Design',
           100.00,
           2,
           'ATIVO'
       );

INSERT INTO servico (titulo, descricao, categoria, preco, usuario_id, situacao)
VALUES (
           'Desenvolvimento de API',
           'Desenvolvimento de APIs REST utilizando Java.',
           'Programacao',
           600.00,
           1,
           'ATIVO'
       );

INSERT INTO servico (titulo, descricao, categoria, preco, usuario_id, situacao)
VALUES (
           'Revisao de texto',
           'Revisao ortografica e gramatical de textos.',
           'Educacao',
           50.00,
           6,
           'ATIVO'
       );

-- Contratacao (Inserts)
INSERT INTO contratacao (servico_id, usuario_id, situacao)
VALUES (1, 3, 'CONCLUIDA');
INSERT INTO contratacao (servico_id, usuario_id, situacao)
VALUES (2, 1, 'SOLICITADA');
INSERT INTO contratacao (servico_id, usuario_id, situacao)
VALUES (3, 5, 'CONCLUIDA');
INSERT INTO contratacao (servico_id, usuario_id, situacao)
VALUES (4, 2, 'SOLICITADA');
INSERT INTO contratacao (servico_id, usuario_id, situacao)
VALUES (5, 4, 'CONCLUIDA');