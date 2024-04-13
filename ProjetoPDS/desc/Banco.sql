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
    DataLancamento DATE NOT NULL,
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
    cpf VARCHAR(11) NOT NULL,
    Desconto int NOT NULL,
    FOREIGN KEY (cpf) REFERENCES Usuario(cpf)
);