CREATE TABLE marca (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE modelo (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    marca_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (marca_id) references marca(id)
);

CREATE TABLE carro (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    placa VARCHAR(255) NOT NULL,
    ano INTEGER  NOT NULL,
    valor DECIMAL(20, 2),
    modelo_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (modelo_id) references modelo(id)
);

create table cliente (
    id INTEGER      NOT NULL AUTO_INCREMENT,
    documento varchar(20) not null,
    email varchar(50),
    endereco varchar(200),
    nome varchar(100) not null,
    renda_familiar DECIMAL(20, 2),
    telefone varchar(20),
    primary key (id));