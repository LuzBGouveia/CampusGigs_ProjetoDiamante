-- usuario
INSERT INTO usuario (nome, senha, email, role)
VALUES ('Lucas Barros', '123456', 'lucas@email.com', 'USER');
INSERT INTO usuario (nome, senha, email, role)
VALUES ('Ana Souza', '123456', 'ana@email.com', 'USER');
INSERT INTO usuario (nome, senha, email, role)
VALUES ('Carlos Silva', '123456', 'carlos@email.com', 'USER');
INSERT INTO usuario (nome, senha, email, role)
VALUES ('Mariana Costa', '123456', 'mariana@email.com', 'USER');
INSERT INTO usuario (nome, senha, email, role)
VALUES ('Joao Santos', '123456', 'joao@email.com', 'USER');
INSERT INTO usuario (nome, senha, email, role)
VALUES ('Fernanda Lima', '123456', 'fernanda@email.com', 'USER');
INSERT INTO usuario (nome, senha, email, role)
VALUES ('Administrador', 'admin123', 'admin@campusgigs.com', 'ADMIN');

-- Servico
INSERT INTO servico (titulo, descricao, categoria, preco, usuario_id, situacao)
VALUES (
    'Desenvolvimento de site',
    'Criacao de sites utilizando HTML, CSS e JavaScript.',
    'Programacao',
    500.00,
    1,
    'DISPONIVEL'
);
INSERT INTO servico (titulo, descricao, categoria, preco, usuario_id, situacao)
VALUES (
    'Criacao de logo',
    'Criacao de logotipos personalizados.',
    'Design',
    150.00,
    2,
    'DISPONIVEL'
);
INSERT INTO servico (titulo, descricao, categoria, preco, usuario_id, situacao)
VALUES (
    'Edicao de video',
    'Edicao de videos para redes sociais.',
    'Audiovisual',
    200.00,
    3,
    'DISPONIVEL'
);
INSERT INTO servico (titulo, descricao, categoria, preco, usuario_id, situacao)
VALUES (
    'Aulas de ingles',
    'Aulas particulares de ingles.',
    'Educacao',
    80.00,
    4,
    'DISPONIVEL'
);
INSERT INTO servico (titulo, descricao, categoria, preco, usuario_id, situacao)
VALUES (
    'Manutencao de computador',
    'Formatacao e manutencao de computadores.',
    'Tecnologia',
    120.00,
    5,
    'DISPONIVEL'
);
INSERT INTO servico (titulo, descricao, categoria, preco, usuario_id, situacao)
VALUES (
    'Criacao de posts',
    'Criacao de artes para redes sociais.',
    'Design',
    100.00,
    2,
    'DISPONIVEL'
);
INSERT INTO servico (titulo, descricao, categoria, preco, usuario_id, situacao)
VALUES (
    'Desenvolvimento de API',
    'Desenvolvimento de APIs REST utilizando Java.',
    'Programacao',
    600.00,
    1,
    'DISPONIVEL'
);
INSERT INTO servico (titulo, descricao, categoria, preco, usuario_id, situacao)
VALUES (
    'Revisao de texto',
    'Revisao ortografica e gramatical de textos.',
    'Educacao',
    50.00,
    6,
    'DISPONIVEL'
);

-- Contratacao
INSERT INTO contratacao (servico_id, usuario_id, situacao)
VALUES (1,3,'CONCLUIDA');
INSERT INTO contratacao (servico_id, usuario_id, situacao)
VALUES (2,1,'PENDENTE');
INSERT INTO contratacao (servico_id, usuario_id, situacao)
VALUES (3,5,'CONCLUIDA');
INSERT INTO contratacao (servico_id, usuario_id, situacao)
VALUES (4,2,'PENDENTE');
INSERT INTO contratacao (servico_id, usuario_id, situacao)
VALUES (5,4,'CONCLUIDA');

COMMIT;
