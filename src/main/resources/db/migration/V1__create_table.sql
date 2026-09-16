-- usuario
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome   VARCHAR(100) NOT NULL,
    senha  VARCHAR(255) NOT NULL,
    email  VARCHAR(150) NOT NULL,
    cep    VARCHAR(10)  NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    uf     VARCHAR(2)   NOT NULL,
    role   VARCHAR(10)  NOT NULL,

    CONSTRAINT uk_usuario_email UNIQUE (email),
    CONSTRAINT ck_usuario_role CHECK (role IN ('USER', 'ADMIN'))
);

-- Servico
CREATE TABLE servico (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    descricao VARCHAR(500),
    categoria VARCHAR(100),
    preco NUMERIC(10,2),
    usuario_id INTEGER NOT NULL,
    situacao VARCHAR(30),

    CONSTRAINT fk_servico_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- Contratacao
CREATE TABLE contratacao (
    id SERIAL PRIMARY KEY,
    servico_id INTEGER NOT NULL,
    usuario_id INTEGER NOT NULL,
    situacao VARCHAR(30),

    CONSTRAINT fk_contratacao_servico FOREIGN KEY (servico_id) REFERENCES servico(id),
    CONSTRAINT fk_contratacao_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT uk_contratacao_servico UNIQUE (servico_id)
);

-- Índices para Foreign Keys
CREATE INDEX idx_servico_usuario
    ON servico(usuario_id);

CREATE INDEX idx_contratacao_servico
    ON contratacao(servico_id);

CREATE INDEX idx_contratacao_usuario
    ON contratacao(usuario_id);