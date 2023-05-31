-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-05-30 14:37:55.642

-- foreign keys
ALTER TABLE Inscripcion
    DROP CONSTRAINT Inscripcion_usuarios;

ALTER TABLE interesesUsuario
    DROP CONSTRAINT InteresesUsuario_usuarios;

ALTER TABLE comentario
    DROP CONSTRAINT comentario_solicitudes;

ALTER TABLE evento_publicacion
    DROP CONSTRAINT evento_publicacion_evento_publicacion_tipo;

ALTER TABLE interesesAudiencias
    DROP CONSTRAINT interesesAudiencias_Audiencias;

ALTER TABLE interesesAudiencias
    DROP CONSTRAINT interesesAudiencias_Intereses;

ALTER TABLE interesesCursos
    DROP CONSTRAINT interesesCursos_Cursos;

ALTER TABLE interesesCursos
    DROP CONSTRAINT interesesCursos_Intereses;

ALTER TABLE interesesEventos
    DROP CONSTRAINT interesesEventos_evento_publicacion;

ALTER TABLE interesesEventos
    DROP CONSTRAINT interesesEventos_sub_intereses;

ALTER TABLE interesesUsuario
    DROP CONSTRAINT interesesUsuario_sub_intereses;

ALTER TABLE modificaciones
    DROP CONSTRAINT modificaciones_evento_publicacion;

ALTER TABLE paralelo
    DROP CONSTRAINT paralelo_Cursos;

ALTER TABLE paralelo
    DROP CONSTRAINT paralelo_Inscripcion;

ALTER TABLE publico_destino_ep
    DROP CONSTRAINT publico_destino_ep_evento_publicacion;

ALTER TABLE publico_destino_ep
    DROP CONSTRAINT publico_destino_ep_publico_tipo;

ALTER TABLE publico_destino_ep
    DROP CONSTRAINT publico_destino_ep_rangos_edad;

ALTER TABLE solicitudes
    DROP CONSTRAINT solicitudes_evento_publicacion;

ALTER TABLE solicitudes
    DROP CONSTRAINT solicitudes_usuarios;

ALTER TABLE sub_intereses
    DROP CONSTRAINT sub_intereses_Intereses;

-- tables
DROP TABLE Inscripcion;

DROP TABLE Intereses;

DROP TABLE audiencias;

DROP TABLE carrera;

DROP TABLE comentario;

DROP TABLE cursos;

DROP TABLE evento_publicacion;

DROP TABLE evento_publicacion_tipo;

DROP TABLE interesesAudiencias;

DROP TABLE interesesCursos;

DROP TABLE interesesEventos;

DROP TABLE interesesUsuario;

DROP TABLE modificaciones;

DROP TABLE paralelo;

DROP TABLE publico_destino_ep;

DROP TABLE publico_tipo;

DROP TABLE rangos_edad;

DROP TABLE solicitudes;

DROP TABLE sub_intereses;

DROP TABLE usuarios;

-- End of file.

