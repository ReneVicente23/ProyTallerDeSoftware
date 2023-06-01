-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-05-30 14:37:55.642

-- tables
-- Table: Inscripcion
CREATE TABLE Inscripcion (
    inscripcionId serial  NOT NULL,
    usuarios_userId int  NOT NULL,
    estate int  NOT NULL,
    CONSTRAINT Inscripcion_pk PRIMARY KEY (inscripcionId)
);

-- Table: Intereses
CREATE TABLE Intereses (
    interesId serial  NOT NULL,
    nombre_interes varchar(50)  NOT NULL,
    imagen varchar(255)  NOT NULL,
    CONSTRAINT Intereses_pk PRIMARY KEY (interesId)
);

-- Table: audiencias
CREATE TABLE audiencias (
    audienciasId int  NOT NULL,
    CONSTRAINT audiencias_pk PRIMARY KEY (audienciasId)
);

-- Table: carrera
CREATE TABLE carrera (
    id_carrera serial  NOT NULL,
    carrera varchar(50)  NOT NULL,
    CONSTRAINT carrera_pk PRIMARY KEY (id_carrera)
);

-- Table: comentario
CREATE TABLE comentario (
    id_comentario serial  NOT NULL,
    comentario varchar(500)  NOT NULL,
    solicitudes_solicitudId int  NOT NULL,
    CONSTRAINT comentario_pk PRIMARY KEY (id_comentario)
);

-- Table: cursos
CREATE TABLE cursos (
    cursoId serial  NOT NULL,
    nombreCurso varchar(50)  NOT NULL,
    descripcion varchar(500)  NOT NULL,
    duracion varchar(50)  NOT NULL,
    fechaInicio date  NOT NULL,
    fechaFin date  NOT NULL,
    cupo int  NOT NULL,
    CONSTRAINT cursos_pk PRIMARY KEY (cursoId)
);

-- Table: evento_publicacion
CREATE TABLE evento_publicacion (
    ep_id serial  NOT NULL,
    titulo varchar(50)  NOT NULL,
    descripcion varchar(200)  NOT NULL,
    id_imagen varchar(200)  NOT NULL,
    lugar varchar(50)  NOT NULL,
    link varchar(50)  NOT NULL,
    evento_publicacion_tipo_id_eptipo int  NOT NULL,
    CONSTRAINT evento_publicacion_pk PRIMARY KEY (ep_id)
);

-- Table: evento_publicacion_tipo
CREATE TABLE evento_publicacion_tipo (
    id_eptipo serial  NOT NULL,
    tipo varchar(50)  NOT NULL,
    CONSTRAINT evento_publicacion_tipo_pk PRIMARY KEY (id_eptipo)
);

-- Table: interesesAudiencias
CREATE TABLE interesesAudiencias (
    idinaudi serial  NOT NULL,
    Intereses_interesId int  NOT NULL,
    Audiencias_audienciasId int  NOT NULL,
    CONSTRAINT interesesAudiencias_pk PRIMARY KEY (idinaudi)
);

-- Table: interesesCursos
CREATE TABLE interesesCursos (
    idincur serial  NOT NULL,
    Cursos_cursoId int  NOT NULL,
    Intereses_interesId int  NOT NULL,
    CONSTRAINT interesesCursos_pk PRIMARY KEY (idincur)
);

-- Table: interesesEventos
CREATE TABLE interesesEventos (
    idineven serial  NOT NULL,
    evento_publicacion_ep_id int  NOT NULL,
    sub_intereses_id_subinteres int  NOT NULL,
    CONSTRAINT interesesEventos_pk PRIMARY KEY (idineven)
);

-- Table: interesesUsuario
CREATE TABLE interesesUsuario (
    idinterUs serial  NOT NULL,
    usuarios_userId int  NOT NULL,
    sub_intereses_id_subinteres int  NOT NULL,
    CONSTRAINT interesesUsuario_pk PRIMARY KEY (idinterUs)
);

-- Table: modificaciones
CREATE TABLE modificaciones (
    id_modificacion serial  NOT NULL,
    modificacion varchar(1000)  NOT NULL,
    fechamod timestamp  NOT NULL,
    evento_publicacion_ep_id int  NOT NULL,
    CONSTRAINT modificaciones_pk PRIMARY KEY (id_modificacion)
);

-- Table: paralelo
CREATE TABLE paralelo (
    paraleloId serial  NOT NULL,
    Cursos_cursoId int  NOT NULL,
    Inscripcion_inscripcionId int  NOT NULL,
    estate int  NOT NULL,
    CONSTRAINT paralelo_pk PRIMARY KEY (paraleloId)
);

-- Table: publico_destino_ep
CREATE TABLE publico_destino_ep (
    id_ped serial  NOT NULL,
    evento_publicacion_ep_id int  NOT NULL,
    tipopublico int  NOT NULL,
    rangos_edad_id_rangos_edad int  NOT NULL,
    publico_tipo_id_publico int  NOT NULL,
    CONSTRAINT publico_destino_ep_pk PRIMARY KEY (id_ped)
);

-- Table: publico_tipo
CREATE TABLE publico_tipo (
    id_publico serial  NOT NULL,
    tipo varchar(50)  NOT NULL,
    CONSTRAINT publico_tipo_pk PRIMARY KEY (id_publico)
);

-- Table: rangos_edad
CREATE TABLE rangos_edad (
    id_rangos_edad serial  NOT NULL,
    edad_inicio int  NOT NULL,
    edad_fin int  NOT NULL,
    CONSTRAINT rangos_edad_pk PRIMARY KEY (id_rangos_edad)
);

-- Table: solicitudes
CREATE TABLE solicitudes (
    solicitudId serial  NOT NULL,
    usuarios_userId int  NOT NULL,
    descripcion varchar(500)  NOT NULL,
    estado int  NOT NULL,
    evento_publicacion_ep_id int  NOT NULL,
    fecha_solicitud timestamp  NOT NULL,
    fecha_revisado timestamp  NULL,
    CONSTRAINT solicitudes_pk PRIMARY KEY (solicitudId)
);

-- Table: sub_intereses
CREATE TABLE sub_intereses (
    id_subinteres serial  NOT NULL,
    nombre varchar(100)  NOT NULL,
    Intereses_interesId int  NOT NULL,
    CONSTRAINT sub_intereses_pk PRIMARY KEY (id_subinteres)
);

-- Table: usuarios
CREATE TABLE usuarios (
    userId serial  NOT NULL,
    googleId varchar(100)  NOT NULL,
    usertype int  NULL,
    family_name varchar(100)  NOT NULL,
    given_name varchar(100)  NOT NULL,
    hd varchar(50)  NOT NULL,
    email varchar(100)  NOT NULL,
    picture varchar(250)  NOT NULL,
    nickname varchar(200)  NULL,
    birthday timestamp  NULL,
    career varchar(100)  NULL,
    CONSTRAINT usuarios_pk PRIMARY KEY (userId)
);

-- foreign keys
-- Reference: Inscripcion_usuarios (table: Inscripcion)
ALTER TABLE Inscripcion ADD CONSTRAINT Inscripcion_usuarios
    FOREIGN KEY (usuarios_userId)
    REFERENCES usuarios (userId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: InteresesUsuario_usuarios (table: interesesUsuario)
ALTER TABLE interesesUsuario ADD CONSTRAINT InteresesUsuario_usuarios
    FOREIGN KEY (usuarios_userId)
    REFERENCES usuarios (userId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: comentario_solicitudes (table: comentario)
ALTER TABLE comentario ADD CONSTRAINT comentario_solicitudes
    FOREIGN KEY (solicitudes_solicitudId)
    REFERENCES solicitudes (solicitudId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: evento_publicacion_evento_publicacion_tipo (table: evento_publicacion)
ALTER TABLE evento_publicacion ADD CONSTRAINT evento_publicacion_evento_publicacion_tipo
    FOREIGN KEY (evento_publicacion_tipo_id_eptipo)
    REFERENCES evento_publicacion_tipo (id_eptipo)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: interesesAudiencias_Audiencias (table: interesesAudiencias)
ALTER TABLE interesesAudiencias ADD CONSTRAINT interesesAudiencias_Audiencias
    FOREIGN KEY (Audiencias_audienciasId)
    REFERENCES audiencias (audienciasId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: interesesAudiencias_Intereses (table: interesesAudiencias)
ALTER TABLE interesesAudiencias ADD CONSTRAINT interesesAudiencias_Intereses
    FOREIGN KEY (Intereses_interesId)
    REFERENCES Intereses (interesId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: interesesCursos_Cursos (table: interesesCursos)
ALTER TABLE interesesCursos ADD CONSTRAINT interesesCursos_Cursos
    FOREIGN KEY (Cursos_cursoId)
    REFERENCES cursos (cursoId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: interesesCursos_Intereses (table: interesesCursos)
ALTER TABLE interesesCursos ADD CONSTRAINT interesesCursos_Intereses
    FOREIGN KEY (Intereses_interesId)
    REFERENCES Intereses (interesId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: interesesEventos_evento_publicacion (table: interesesEventos)
ALTER TABLE interesesEventos ADD CONSTRAINT interesesEventos_evento_publicacion
    FOREIGN KEY (evento_publicacion_ep_id)
    REFERENCES evento_publicacion (ep_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: interesesEventos_sub_intereses (table: interesesEventos)
ALTER TABLE interesesEventos ADD CONSTRAINT interesesEventos_sub_intereses
    FOREIGN KEY (sub_intereses_id_subinteres)
    REFERENCES sub_intereses (id_subinteres)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: interesesUsuario_sub_intereses (table: interesesUsuario)
ALTER TABLE interesesUsuario ADD CONSTRAINT interesesUsuario_sub_intereses
    FOREIGN KEY (sub_intereses_id_subinteres)
    REFERENCES sub_intereses (id_subinteres)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: modificaciones_evento_publicacion (table: modificaciones)
ALTER TABLE modificaciones ADD CONSTRAINT modificaciones_evento_publicacion
    FOREIGN KEY (evento_publicacion_ep_id)
    REFERENCES evento_publicacion (ep_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: paralelo_Cursos (table: paralelo)
ALTER TABLE paralelo ADD CONSTRAINT paralelo_Cursos
    FOREIGN KEY (Cursos_cursoId)
    REFERENCES cursos (cursoId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: paralelo_Inscripcion (table: paralelo)
ALTER TABLE paralelo ADD CONSTRAINT paralelo_Inscripcion
    FOREIGN KEY (Inscripcion_inscripcionId)
    REFERENCES Inscripcion (inscripcionId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: publico_destino_ep_evento_publicacion (table: publico_destino_ep)
ALTER TABLE publico_destino_ep ADD CONSTRAINT publico_destino_ep_evento_publicacion
    FOREIGN KEY (evento_publicacion_ep_id)
    REFERENCES evento_publicacion (ep_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: publico_destino_ep_publico_tipo (table: publico_destino_ep)
ALTER TABLE publico_destino_ep ADD CONSTRAINT publico_destino_ep_publico_tipo
    FOREIGN KEY (publico_tipo_id_publico)
    REFERENCES publico_tipo (id_publico)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: publico_destino_ep_rangos_edad (table: publico_destino_ep)
ALTER TABLE publico_destino_ep ADD CONSTRAINT publico_destino_ep_rangos_edad
    FOREIGN KEY (rangos_edad_id_rangos_edad)
    REFERENCES rangos_edad (id_rangos_edad)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: solicitudes_evento_publicacion (table: solicitudes)
ALTER TABLE solicitudes ADD CONSTRAINT solicitudes_evento_publicacion
    FOREIGN KEY (evento_publicacion_ep_id)
    REFERENCES evento_publicacion (ep_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: solicitudes_usuarios (table: solicitudes)
ALTER TABLE solicitudes ADD CONSTRAINT solicitudes_usuarios
    FOREIGN KEY (usuarios_userId)
    REFERENCES usuarios (userId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: sub_intereses_Intereses (table: sub_intereses)
ALTER TABLE sub_intereses ADD CONSTRAINT sub_intereses_Intereses
    FOREIGN KEY (Intereses_interesId)
    REFERENCES Intereses (interesId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

