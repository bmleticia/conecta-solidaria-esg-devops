CREATE TABLE T_TIPO_USUARIO (
    id_tipo_usuario NUMBER(2) PRIMARY KEY,
    nm_tipo_usuario VARCHAR2(100) NOT NULL
);

CREATE TABLE T_USUARIO (
    ds_email        VARCHAR2(100) PRIMARY KEY,
    nm_usuario      VARCHAR2(100) NOT NULL,
    ds_senha        VARCHAR2(50) NOT NULL,
    id_tipo_usuario NUMBER(2) NOT NULL,
    CONSTRAINT T_TIPO_USUARIO_FK
        FOREIGN KEY (id_tipo_usuario)
        REFERENCES T_TIPO_USUARIO(id_tipo_usuario)
);

CREATE TABLE T_TIPO_NECESSIDADE (
    id_tipo_necessidade NUMBER(2) PRIMARY KEY,
    ds_tipo_necessidade VARCHAR2(50) NOT NULL
);

CREATE SEQUENCE SEQ_PEDIDO_AJUDA START WITH 1006 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE SEQ_ALERTA START WITH 6 INCREMENT BY 1 NOCACHE;

CREATE TABLE T_PEDIDO_AJUDA (
    id_pedido           NUMBER(16) PRIMARY KEY,
    ds_pedido           VARCHAR2(500) NOT NULL,
    ds_localizacao      VARCHAR2(100) NOT NULL,
    id_tipo_necessidade NUMBER(2) NOT NULL,
    ds_email            VARCHAR2(100) NOT NULL,
    CONSTRAINT T_TIPO_NECESSIDADE_FK
        FOREIGN KEY (id_tipo_necessidade)
        REFERENCES T_TIPO_NECESSIDADE(id_tipo_necessidade),
    CONSTRAINT T_USUARIO_PEDIDO_FK
        FOREIGN KEY (ds_email)
        REFERENCES T_USUARIO(ds_email)
);

CREATE TABLE T_DOACAO (
    ds_email    VARCHAR2(100) NOT NULL,
    id_pedido   NUMBER(16) NOT NULL,
    ds_mensagem VARCHAR2(500),
    CONSTRAINT T_DOACAO_PK PRIMARY KEY (ds_email, id_pedido),
    CONSTRAINT T_USUARIO_DOACAO_FK
        FOREIGN KEY (ds_email)
        REFERENCES T_USUARIO(ds_email),
    CONSTRAINT T_PEDIDO_AJUDA_FK
        FOREIGN KEY (id_pedido)
        REFERENCES T_PEDIDO_AJUDA(id_pedido)
);

CREATE TABLE T_ALERTAS (
    id_alerta NUMBER(20) PRIMARY KEY,
    ds_alerta VARCHAR2(200) NOT NULL
);

CREATE TABLE T_EXIBE_ALERTA (
    ds_email VARCHAR2(100) NOT NULL,
    id_alerta NUMBER(20) NOT NULL,
    CONSTRAINT T_EXIBE_ALERTA_PK PRIMARY KEY (ds_email, id_alerta),
    CONSTRAINT T_USUARIO_ALERTA_FK
        FOREIGN KEY (ds_email)
        REFERENCES T_USUARIO(ds_email),
    CONSTRAINT T_ALERTAS_FK
        FOREIGN KEY (id_alerta)
        REFERENCES T_ALERTAS(id_alerta)
);
