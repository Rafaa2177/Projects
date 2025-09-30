CREATE TABLE Aplicacao (
                           id        number(10) GENERATED AS IDENTITY,
                           aplicacao varchar2(255) NOT NULL,
                           PRIMARY KEY (id));
CREATE TABLE Ciclo (
                       id                    number(10) GENERATED AS IDENTITY,
                       Exploracaoid          number(10) NOT NULL,
                       data_inicial          date NOT NULL,
                       data_final            date,
                       qtt                   number(10) NOT NULL,
                       Unidadeid             number(10),
                       Plantaid              number(10),
                       plantaNaoIdentificada varchar2(255),
                       PRIMARY KEY (id));
CREATE TABLE Especie (
                         id      number(10) GENERATED AS IDENTITY,
                         especie varchar2(255) NOT NULL,
                         nome    varchar2(255) NOT NULL,
                         PRIMARY KEY (id));
CREATE TABLE Exploracao (
                            id                number(10) GENERATED AS IDENTITY,
                            idExploracao      number(10) NOT NULL,
                            Tipo_Exploracaoid number(10) NOT NULL,
                            designacao        varchar2(255) NOT NULL,
                            area              double precision,
                            Unidadeid         number(10),
                            PRIMARY KEY (id));
CREATE TABLE Fabricante (
                            id   number(10) GENERATED AS IDENTITY,
                            nome varchar2(255) NOT NULL,
                            PRIMARY KEY (id));
CREATE TABLE Fator_Producao (
                                Regaid    number(10) NOT NULL,
                                Produtoid number(10) NOT NULL,
                                PRIMARY KEY (Regaid,
                                             Produtoid));
CREATE TABLE Ficha_Tecnica (
                               id    number(10) GENERATED AS IDENTITY,
                               C1    varchar2(255) NOT NULL,
                               C2    varchar2(255),
                               C3    varchar2(255),
                               C4    varchar2(255),
                               Perc1 double precision NOT NULL,
                               Perc2 double precision,
                               Perc3 double precision,
                               Perc4 double precision,
                               PRIMARY KEY (id));
CREATE TABLE Formato (
                         id      number(10) GENERATED AS IDENTITY,
                         formato varchar2(255) NOT NULL,
                         PRIMARY KEY (id));
CREATE TABLE Modo (
                      id   number(10) GENERATED AS IDENTITY,
                      modo varchar2(255) NOT NULL,
                      PRIMARY KEY (id));
CREATE TABLE Operacoes (
                           id                    number(10) GENERATED AS IDENTITY,
                           Exploracaoid          number(10) NOT NULL,
                           Tipo_Operacaoid       number(10) NOT NULL,
                           Modoid                number(10),
                           data                  date NOT NULL,
                           quantidade            number(10),
                           Unidadeid             number(10),
                           Regaid                number(10),
                           Plantaid              number(10),
                           plantaNaoIdentificada varchar2(255),
                           PRIMARY KEY (id));
CREATE TABLE Planta (
                        id                 number(10) GENERATED AS IDENTITY,
                        variedade          varchar2(255) NOT NULL,
                        Especieid          number(10) NOT NULL,
                        Tipo_Plantacaoid   number(10) NOT NULL,
                        Planta_Intervaloid number(10) NOT NULL,
                        PRIMARY KEY (id));
CREATE TABLE Planta_Intervalo (
                                  id                   number(10) GENERATED AS IDENTITY,
                                  Sementeira_Plantacao varchar2(255),
                                  Poda                 varchar2(255),
                                  Floracao             varchar2(255),
                                  Colheita             varchar2(255),
                                  PRIMARY KEY (id));
CREATE TABLE Produto (
                         id              number(10) GENERATED AS IDENTITY,
                         designacao      varchar2(255) NOT NULL UNIQUE,
                         Ficha_Tecnicaid number(10) NOT NULL,
                         Fabricanteid    number(10),
                         Tipo_Produtoid  number(10) NOT NULL,
                         Aplicacaoid     number(10) NOT NULL,
                         Formatoid       number(10) NOT NULL,
                         PRIMARY KEY (id));
CREATE TABLE Rega (
                      id number(10) GENERATED AS IDENTITY,
                      PRIMARY KEY (id));
CREATE TABLE Tipo_Exploracao (
                                 id   number(10) GENERATED AS IDENTITY,
                                 tipo varchar2(255) NOT NULL,
                                 PRIMARY KEY (id));
CREATE TABLE Tipo_Operacao (
                               id   number(10) GENERATED AS IDENTITY,
                               tipo varchar2(255) NOT NULL,
                               PRIMARY KEY (id));
CREATE TABLE Tipo_Plantacao (
                                id   number(10) GENERATED AS IDENTITY,
                                tipo varchar2(255) NOT NULL,
                                PRIMARY KEY (id));
CREATE TABLE Tipo_Produto (
                              id   number(10) GENERATED AS IDENTITY,
                              tipo varchar2(255) NOT NULL,
                              PRIMARY KEY (id));
CREATE TABLE Unidade (
                         id   number(10) GENERATED AS IDENTITY,
                         nome varchar2(255) NOT NULL,
                         PRIMARY KEY (id));


ALTER TABLE Produto ADD CONSTRAINT FKProduto343882 FOREIGN KEY (Ficha_Tecnicaid) REFERENCES Ficha_Tecnica (id);
ALTER TABLE Produto ADD CONSTRAINT FKProduto744468 FOREIGN KEY (Fabricanteid) REFERENCES Fabricante (id);
ALTER TABLE Produto ADD CONSTRAINT FKProduto137662 FOREIGN KEY (Tipo_Produtoid) REFERENCES Tipo_Produto (id);
ALTER TABLE Produto ADD CONSTRAINT FKProduto132930 FOREIGN KEY (Aplicacaoid) REFERENCES Aplicacao (id);
ALTER TABLE Produto ADD CONSTRAINT FKProduto981829 FOREIGN KEY (Formatoid) REFERENCES Formato (id);
ALTER TABLE Operacoes ADD CONSTRAINT FKOperacoes497895 FOREIGN KEY (Tipo_Operacaoid) REFERENCES Tipo_Operacao (id);
ALTER TABLE Exploracao ADD CONSTRAINT FKExploracao388915 FOREIGN KEY (Tipo_Exploracaoid) REFERENCES Tipo_Exploracao (id);
ALTER TABLE Exploracao ADD CONSTRAINT FKExploracao425989 FOREIGN KEY (Unidadeid) REFERENCES Unidade (id);
ALTER TABLE Operacoes ADD CONSTRAINT FKOperacoes845182 FOREIGN KEY (Unidadeid) REFERENCES Unidade (id);
ALTER TABLE Planta ADD CONSTRAINT FKPlanta31516 FOREIGN KEY (Especieid) REFERENCES Especie (id);
ALTER TABLE Planta ADD CONSTRAINT FKPlanta940009 FOREIGN KEY (Tipo_Plantacaoid) REFERENCES Tipo_Plantacao (id);
ALTER TABLE Operacoes ADD CONSTRAINT FKOperacoes782228 FOREIGN KEY (Exploracaoid) REFERENCES Exploracao (id);
ALTER TABLE Operacoes ADD CONSTRAINT FKOperacoes500069 FOREIGN KEY (Plantaid) REFERENCES Planta (id);
ALTER TABLE Operacoes ADD CONSTRAINT FKOperacoes656048 FOREIGN KEY (Modoid) REFERENCES Modo (id);
ALTER TABLE Fator_Producao ADD CONSTRAINT FKFator_Prod423604 FOREIGN KEY (Produtoid) REFERENCES Produto (id);
ALTER TABLE Fator_Producao ADD CONSTRAINT FKFator_Prod178343 FOREIGN KEY (Regaid) REFERENCES Rega (id);
ALTER TABLE Operacoes ADD CONSTRAINT FKOperacoes782070 FOREIGN KEY (Regaid) REFERENCES Rega (id);
ALTER TABLE Ciclo ADD CONSTRAINT FKCiclo682845 FOREIGN KEY (Exploracaoid) REFERENCES Exploracao (id);
ALTER TABLE Ciclo ADD CONSTRAINT FKCiclo400547 FOREIGN KEY (Plantaid) REFERENCES Planta (id);
ALTER TABLE Ciclo ADD CONSTRAINT FKCiclo225791 FOREIGN KEY (Unidadeid) REFERENCES Unidade (id);
ALTER TABLE Planta ADD CONSTRAINT FKPlanta469914 FOREIGN KEY (Planta_Intervaloid) REFERENCES Planta_Intervalo (id);
