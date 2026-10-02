INSERT INTO T_TIPO_USUARIO VALUES (1, 'Solicitante');
INSERT INTO T_TIPO_USUARIO VALUES (2, 'Doador');
INSERT INTO T_TIPO_USUARIO VALUES (3, 'Administrador');
INSERT INTO T_TIPO_USUARIO VALUES (4, 'Voluntario');
INSERT INTO T_TIPO_USUARIO VALUES (5, 'Moderador');

INSERT INTO T_USUARIO VALUES ('ana@email.com', 'Ana Souza', '123456', 1);
INSERT INTO T_USUARIO VALUES ('bruno@email.com', 'Bruno Lima', '123456', 2);
INSERT INTO T_USUARIO VALUES ('carla@email.com', 'Carla Mendes', '123456', 2);
INSERT INTO T_USUARIO VALUES ('diego@email.com', 'Diego Alves', '123456', 4);
INSERT INTO T_USUARIO VALUES ('eva@email.com', 'Eva Rocha', '123456', 3);

INSERT INTO T_TIPO_NECESSIDADE VALUES (1, 'Alimentos');
INSERT INTO T_TIPO_NECESSIDADE VALUES (2, 'Roupas');
INSERT INTO T_TIPO_NECESSIDADE VALUES (3, 'Medicamentos');
INSERT INTO T_TIPO_NECESSIDADE VALUES (4, 'Transporte');
INSERT INTO T_TIPO_NECESSIDADE VALUES (5, 'Material Escolar');

INSERT INTO T_PEDIDO_AJUDA VALUES (1001, 'Necessito de cesta basica para 4 pessoas', 'Sao Paulo', 1, 'ana@email.com');
INSERT INTO T_PEDIDO_AJUDA VALUES (1002, 'Preciso de roupas infantis', 'Osasco', 2, 'ana@email.com');
INSERT INTO T_PEDIDO_AJUDA VALUES (1003, 'Preciso de remedios de uso continuo', 'Barueri', 3, 'ana@email.com');
INSERT INTO T_PEDIDO_AJUDA VALUES (1004, 'Ajuda com transporte para consulta', 'Santo Andre', 4, 'ana@email.com');
INSERT INTO T_PEDIDO_AJUDA VALUES (1005, 'Preciso de material escolar para duas criancas', 'Guarulhos', 5, 'ana@email.com');

INSERT INTO T_DOACAO VALUES ('bruno@email.com', 1001, 'Posso doar alimentos ainda hoje');
INSERT INTO T_DOACAO VALUES ('carla@email.com', 1002, 'Tenho roupas em bom estado');
INSERT INTO T_DOACAO VALUES ('diego@email.com', 1003, 'Posso ajudar com a compra dos remedios');
INSERT INTO T_DOACAO VALUES ('bruno@email.com', 1004, 'Posso oferecer transporte');
INSERT INTO T_DOACAO VALUES ('carla@email.com', 1005, 'Tenho cadernos e lapis para doar');

INSERT INTO T_ALERTAS VALUES (1, 'Novo pedido de ajuda cadastrado');
INSERT INTO T_ALERTAS VALUES (2, 'Pedido recebeu uma doacao');
INSERT INTO T_ALERTAS VALUES (3, 'Alta demanda para alimentos');
INSERT INTO T_ALERTAS VALUES (4, 'Alta demanda para medicamentos');
INSERT INTO T_ALERTAS VALUES (5, 'Novo alerta geral do sistema');

INSERT INTO T_EXIBE_ALERTA VALUES ('ana@email.com', 1);
INSERT INTO T_EXIBE_ALERTA VALUES ('bruno@email.com', 1);
INSERT INTO T_EXIBE_ALERTA VALUES ('carla@email.com', 2);
INSERT INTO T_EXIBE_ALERTA VALUES ('diego@email.com', 3);
INSERT INTO T_EXIBE_ALERTA VALUES ('eva@email.com', 5);
