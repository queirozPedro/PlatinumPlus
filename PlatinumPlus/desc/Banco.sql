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

-- Para popular o banco

INSERT INTO Jogo (Nome, Genero, Descricao, Valor, Desenvolvedora, QuantConquistas, DescontoElegivel) VALUES 
('Call of Duty: Modern Warfare', 'FPS', 'Um dos melhores jogos de tiro em primeira pessoa', 59.99, 'Infinity Ward', 70, 1),
('Battlefield 5', 'FPS', 'Uma guerra épica com batalhas intensas em grande escala', 29.99, 'DICE', 60, 5),
('Counter-Strike: Global Offensive', 'FPS', 'Um clássico dos jogos de tiro competitivos', 14.99, 'Valve', 80, 10),
('Rainbow Six Siege', 'FPS', 'Um jogo tático de tiro em equipe com operações especiais', 39.99, 'Ubisoft', 65, 15),
('Overwatch', 'FPS', 'Um jogo de tiro em equipe com personagens únicos e habilidades especiais', 19.99, 'Blizzard Entertainment', 75, 20),
('DOOM Eternal', 'FPS', 'Um combate frenético contra demônios em um inferno futurista', 49.99, 'id Software', 55, 25),
('Apex Legends', 'FPS', 'Um battle royale com personagens únicos e mecânicas de equipe', 0.00, 'Respawn Entertainment', 90, 26),
('Titanfall 2', 'FPS', 'Um jogo de tiro com robôs gigantes e ação acelerada', 19.99, 'Respawn Entertainment', 70, 27),
('Half-Life: Alyx', 'FPS', 'Uma experiência de realidade virtual imersiva na saga Half-Life', 59.99, 'Valve', 40, 28),
('Metro Exodus', 'FPS', 'Uma jornada através de um mundo pós-apocalíptico russo', 29.99, '4A Games', 60, 30);
