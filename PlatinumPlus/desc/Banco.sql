CREATE TABLE Usuario (
    cpf VARCHAR(11) NOT NULL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(11) NOT NULL
);

CREATE TABLE Funcionario (
    idAdm Serial PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    FOREIGN KEY (cpf) REFERENCES Usuario(cpf)
);

CREATE TABLE Jogo (
    idJogo Serial PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Genero VARCHAR(100) NOT NULL,
    Descricao VARCHAR(255) NOT NULL,
    Valor FLOAT NOT NULL,
    Desenvolvedora VARCHAR(100) NOT NULL,
    QuantConquistas int NOT NULL,
    DescontoElegivel int NOT NULL
);

CREATE TABLE JogoUsuario (
    cpfUsuario VARCHAR(11) NOT NULL,
    idJogo int NOT NULL,
    QuantObtidaConquistas int NOT NULL,
    TemCupom int DEFAULT 1,
    FOREIGN KEY (idJogo) REFERENCES Jogo(idJogo),
    FOREIGN KEY (cpfUsuario) REFERENCES Usuario(cpf)
);

CREATE TABLE Cupom (
    idCupom Serial PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    Desconto int NOT NULL,
    FOREIGN KEY (cpf) REFERENCES Usuario(cpf)
);

insert into usuario(cpf, nome, email, senha, telefone) values ("admin", "admin", "admin", "admin", "admin");
insert into Funcionario(cpf) value ("admin");

-- Vou fazer aqui em baixo os Drops das Tabelas, pra quando a gente quiser limpar o banco

DROP TABLE Funcionario;
DROP TABLE JogoUsuario;
DROP TABLE Cupom;
DROP TABLE Jogo;
DROP TABLE Usuario;