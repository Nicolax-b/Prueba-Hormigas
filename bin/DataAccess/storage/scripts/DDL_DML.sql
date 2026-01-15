-- © 2K26 ❱──💀──❰ pat_mic ? code is life : life is code
-- database: storage\Databases\antCiberDron.sqlite

DROP TABLE IF EXISTS HormigaSuperHabilidad;
DROP TABLE IF EXISTS HormigaAlimento;
DROP TABLE IF EXISTS SuperHabilidad;
DROP TABLE IF EXISTS Alimento;
DROP TABLE IF EXISTS Hormiga;
DROP TABLE IF EXISTS Genoma;
DROP TABLE IF EXISTS Estado;
DROP TABLE IF EXISTS HormigaTipo;
DROP TABLE IF EXISTS AlimentoTipo;


CREATE TABLE AlimentoTipo(
     IdAlimentoTipo INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre         VARCHAR(50)  NOT NULL UNIQUE
    ,Descripcion    VARCHAR(100) NULL
    ,Estado         VARCHAR(1)   NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);

CREATE TABLE Alimento(
     IdAlimento     INTEGER PRIMARY KEY AUTOINCREMENT
    ,IdAlimentoTipo INTEGER NOT NULL REFERENCES AlimentoTipo (IdAlimentoTipo)
    ,Nombre         VARCHAR(50)  NOT NULL UNIQUE
    ,Descripcion    VARCHAR(100) NULL
    ,Estado         VARCHAR(1)   NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);

CREATE TABLE HormigaTipo (
     IdHormigaTipo  INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre         VARCHAR(50)  NOT NULL UNIQUE
    ,Descripcion    VARCHAR(100) NULL
    ,Estado         VARCHAR(1)   NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);

CREATE TABLE Estado (
     IdEstado       INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre         VARCHAR(50)  NOT NULL UNIQUE
    ,Descripcion    VARCHAR(100) NULL
    ,Estado         VARCHAR(1)   NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);

CREATE TABLE Genoma (
     IdGenoma       INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre         VARCHAR(50)  NOT NULL UNIQUE
    ,Descripcion    VARCHAR(100) NULL
    ,Estado         VARCHAR(1)   NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);

CREATE TABLE SuperHabilidad (
     IdSuperHabilidad INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre           VARCHAR(50)  NOT NULL UNIQUE
    ,Descripcion      VARCHAR(100) NULL
    ,Estado           VARCHAR(1)   NOT NULL DEFAULT 'A'
    ,FechaCreacion    DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModifica    DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);

CREATE TABLE Hormiga (
     IdHormiga      INTEGER PRIMARY KEY AUTOINCREMENT
    ,IdHormigaTipo  INTEGER NOT NULL REFERENCES HormigaTipo (IdHormigaTipo)
    ,IdGenoma       INTEGER NOT NULL REFERENCES Genoma      (IdGenoma)
    ,IdEstado       INTEGER NOT NULL REFERENCES Estado      (IdEstado)
    ,Nombre         VARCHAR(50) NOT NULL UNIQUE
    ,Descripcion    VARCHAR(100) NULL
    ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);


CREATE TABLE HormigaAlimento (
     IdHormigaAlimento INTEGER PRIMARY KEY AUTOINCREMENT
    ,IdHormiga         INTEGER NOT NULL REFERENCES Hormiga (IdHormiga)
    ,IdAlimento        INTEGER NOT NULL REFERENCES Alimento (IdAlimento)
    ,Descripcion       VARCHAR(100) NULL
    ,Estado            VARCHAR(1)   NOT NULL DEFAULT 'A'
    ,FechaCreacion     DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModifica     DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);

CREATE TABLE HormigaSuperHabilidad (
     IdHormigaSuperHabilidad INTEGER PRIMARY KEY AUTOINCREMENT
    ,IdHormiga               INTEGER NOT NULL REFERENCES Hormiga (IdHormiga)
    ,IdGenoma                INTEGER NOT NULL REFERENCES Genoma (IdGenoma)
    ,IdSuperHabilidad        INTEGER NOT NULL REFERENCES SuperHabilidad (IdSuperHabilidad)
    ,Descripcion             VARCHAR(100) NULL
    ,Estado                  VARCHAR(1)   NOT NULL DEFAULT 'A'
    ,FechaCreacion           DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModifica           DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);


-- Genomas (X, XX, XY)
INSERT INTO Genoma (Nombre, Descripcion) VALUES 
 ('X',  'Asexual')
,('XX', 'Macho') 
,('XY', 'Hembra');

-- Tipos de Hormiga
INSERT INTO HormigaTipo (Nombre, Descripcion) VALUES 
 ('HLarva',       'Etapa inicial')
,('HSoldado',     'Defensa')
,('HZángano',     'Reproductivo Macho')
,('HRastreadora', 'Exploradora')
,('HReina',       'Madre de colonia')
,('HObrera',      'Trabajadora');

-- Tipos de Alimento
INSERT INTO AlimentoTipo (Nombre) VALUES
 ('Nectarívoros')
,('Carnívoro')
,('Omnívoro')
,('Insectívoro')
,('Herbívoro');

-- SuperHabilidades
INSERT INTO SuperHabilidad (Nombre) VALUES
 ('superSaltar')
,('superVolar')
,('superRastreo')
,('superReproductora')
,('superCortadora');

-- Estados
INSERT INTO Estado (Nombre) VALUES ('VIVA'), ('MUERTA');

DROP VIEW IF EXISTS vwHormiga;
CREATE VIEW vwHormiga AS
SELECT 
     H.IdHormiga
    ,H.Nombre AS NombreHormiga
    ,HT.Nombre AS Tipo
    ,G.Nombre  AS Genoma
    ,E.Nombre  AS EstadoActual
    -- Subconsulta para traer las habilidades concatenadas
    ,(SELECT GROUP_CONCAT(SH.Nombre, ', ') 
      FROM HormigaSuperHabilidad HSH 
      JOIN SuperHabilidad SH ON HSH.IdSuperHabilidad = SH.IdSuperHabilidad 
      WHERE HSH.IdHormiga = H.IdHormiga) AS Habilidades
FROM Hormiga H
JOIN HormigaTipo    HT ON H.IdHormigaTipo = HT.IdHormigaTipo
JOIN Genoma         G  ON H.IdGenoma      = G.IdGenoma
JOIN Estado         E  ON H.IdEstado      = E.IdEstado
WHERE H.Estado = 'A';

SELECT * FROM vwHormiga;