 -- Eliminar la base de datos si existe
DROP DATABASE IF EXISTS gaia_analytics;

-- Crear la base de datos y usarla
CREATE DATABASE gaia_analytics;
USE gaia_analytics;

-- Crear tabla datos_suelo
CREATE TABLE datos_suelo (
    dato_id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    ubicacion VARCHAR(255),
    ph FLOAT,
    nutrientes VARCHAR(255)
);

-- Crear tabla analisis
CREATE TABLE analisis (
    analisis_id INT AUTO_INCREMENT PRIMARY KEY,
    dato_id INT,
    resultado TEXT,
    FOREIGN KEY (dato_id) REFERENCES datos_suelo(dato_id)
);

-- Crear tabla usuarios
CREATE TABLE usuarios (
    usuario_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(255),
    contrasena VARCHAR(255)
);

-- Crear tabla informes
CREATE TABLE informes (
    informe_id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    fecha DATE,
    contenido TEXT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(usuario_id)
);

-- Insertar datos en la tabla usuarios
INSERT INTO usuarios (nombre_usuario, contrasena) VALUES ('admin', 'password123');

-- Insertar datos en la tabla datos_suelo
INSERT INTO datos_suelo (fecha, ubicacion, ph, nutrientes) VALUES 
('2024-01-01', 'Campo 1', 6.5, 'N,P,K'),
('2024-01-02', 'Campo 2', 7.0, 'N,P');

-- Insertar datos en la tabla analisis
INSERT INTO analisis (dato_id, resultado) VALUES 
(1, 'Buen nivel de nutrientes'),
(2, 'Nivel de potasio bajo');

-- Insertar datos en la tabla informes
INSERT INTO informes (usuario_id, fecha, contenido) VALUES 
(1, '2024-01-01', 'Informe inicial de análisis de suelos');

-- Consultar datos de prueba
SELECT 
    ds.dato_id AS 'ID del Dato',
    ds.fecha AS 'Fecha',
    ds.ubicacion AS 'Ubicación',
    ds.ph AS 'pH',
    ds.nutrientes AS 'Nutrientes',
    a.resultado AS 'Resultado del Análisis'
FROM 
    datos_suelo ds
JOIN 
    analisis a ON ds.dato_id = a.dato_id;

-- Borrar datos de prueba con cláusula WHERE
DELETE FROM informes WHERE 1=1;
DELETE FROM analisis WHERE 1=1;
DELETE FROM datos_suelo WHERE 1=1;
DELETE FROM usuarios WHERE 1=1;

-- Verificar que las tablas están vacías
SELECT * FROM datos_suelo;
SELECT * FROM analisis;
SELECT * FROM usuarios;
SELECT * FROM informes;
