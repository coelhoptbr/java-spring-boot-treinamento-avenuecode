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