-- MariaDB dump 10.19  Distrib 10.6.7-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: taco_bell
-- ------------------------------------------------------
-- Server version	10.6.7-MariaDB-3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auxiliar`
--

DROP TABLE IF EXISTS `auxiliar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auxiliar` (
  `codUsr` varchar(7) NOT NULL,
  `puesto` varchar(20) NOT NULL,
  PRIMARY KEY (`codUsr`),
  CONSTRAINT `auxiliar_ibfk_1` FOREIGN KEY (`codUsr`) REFERENCES `trabajador` (`codUsr`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auxiliar`
--

LOCK TABLES `auxiliar` WRITE;
/*!40000 ALTER TABLE `auxiliar` DISABLE KEYS */;
INSERT INTO `auxiliar` VALUES ('AU00002','Cocina'),('AU00005','Recepcion'),('AU00006','Limpieza'),('AU00009','Limpieza');
/*!40000 ALTER TABLE `auxiliar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `codUsr` varchar(7) NOT NULL,
  `correoLogin` varchar(320) NOT NULL,
  PRIMARY KEY (`codUsr`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`codUsr`) REFERENCES `usuarie` (`codUsr`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('CL00004','joana.rente6@tuta.io'),('CL00011','unaizuluu@gmail.com'),('CL00012','julenbara6@gmail.com');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `descuento`
--

DROP TABLE IF EXISTS `descuento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `descuento` (
  `codDsc` varchar(10) NOT NULL,
  `usos` float DEFAULT NULL,
  `cantidadDesc` float NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date DEFAULT NULL,
  PRIMARY KEY (`codDsc`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `descuento`
--

LOCK TABLES `descuento` WRITE;
/*!40000 ALTER TABLE `descuento` DISABLE KEYS */;
INSERT INTO `descuento` VALUES ('DE00000001',3,2,'2022-04-08','2022-05-27'),('DE00000002',1,2.99,'2022-04-08','2022-05-25');
/*!40000 ALTER TABLE `descuento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `establecimiento`
--

DROP TABLE IF EXISTS `establecimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `establecimiento` (
  `codEst` varchar(7) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `loc` varchar(20) NOT NULL,
  PRIMARY KEY (`codEst`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `establecimiento`
--

LOCK TABLES `establecimiento` WRITE;
/*!40000 ALTER TABLE `establecimiento` DISABLE KEYS */;
INSERT INTO `establecimiento` VALUES ('ES00001','Avda. Urquijo','Bilbao'),('ES00002','Gran Vía','MADRID'),('ES00003','Abando','Bilbao');
/*!40000 ALTER TABLE `establecimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `codMnu` varchar(10) NOT NULL DEFAULT '',
  `codDsc` varchar(10) DEFAULT NULL,
  `precio` float NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`codMnu`),
  KEY `codDsc` (`codDsc`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`codDsc`) REFERENCES `descuento` (`codDsc`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES ('ME00000001','DE00000001',8.99,'Menú Quesarito y Nachos'),('ME00000002',NULL,8.99,'Menú Qesarito Veggie'),('ME00000003',NULL,8.99,'Menú Quesarito y Patatas Mex'),('ME00000004',NULL,8.99,'Menú Santa Mónica'),('ME00000005',NULL,8.99,'Menú Santa Mónica Veggie'),('ME00000006',NULL,8.99,'Menú Quesadilla'),('ME00000007',NULL,8.99,'Menú Quesadilla Veggie'),('ME00000008',NULL,11.99,'Menú Crunchywrap Supreme'),('ME00000009',NULL,11.99,'Menú Crunchywrap Supreme V'),('ME00000010',NULL,7.99,'Menú Tacos Supreme'),('ME00000011',NULL,8.99,'Menú Gran Burrito'),('ME00000012',NULL,8.99,'Menú Gran Burrito Veggie'),('ME00000013',NULL,5.99,'Menú Kids');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_producto`
--

DROP TABLE IF EXISTS `menu_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_producto` (
  `codMnu` varchar(10) NOT NULL,
  `codPrd` varchar(10) NOT NULL,
  PRIMARY KEY (`codMnu`,`codPrd`),
  KEY `codPrd` (`codPrd`),
  CONSTRAINT `menu_producto_ibfk_1` FOREIGN KEY (`codMnu`) REFERENCES `menu` (`codMnu`) ON DELETE CASCADE,
  CONSTRAINT `menu_producto_ibfk_2` FOREIGN KEY (`codPrd`) REFERENCES `producto` (`codPrd`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_producto`
--

LOCK TABLES `menu_producto` WRITE;
/*!40000 ALTER TABLE `menu_producto` DISABLE KEYS */;
INSERT INTO `menu_producto` VALUES ('ME00000001','PR00000001'),('ME00000001','PR00000003'),('ME00000001','PR00000007'),('ME00000002','PR00000002'),('ME00000002','PR00000004'),('ME00000002','PR00000007');
/*!40000 ALTER TABLE `menu_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `codPed` varchar(10) NOT NULL,
  `codCle` varchar(7) NOT NULL,
  `codRep` varchar(7) NOT NULL,
  `codMnu` varchar(10) NOT NULL,
  `codEst` varchar(7) NOT NULL,
  `fechaPed` date NOT NULL,
  PRIMARY KEY (`codPed`),
  KEY `codMnu` (`codMnu`),
  KEY `codRep` (`codRep`),
  KEY `codCle` (`codCle`),
  KEY `pedido_ibfk_4` (`codEst`),
  CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`codMnu`) REFERENCES `menu` (`codMnu`) ON DELETE CASCADE,
  CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`codRep`) REFERENCES `repartidor` (`codUsr`) ON DELETE CASCADE,
  CONSTRAINT `pedido_ibfk_3` FOREIGN KEY (`codCle`) REFERENCES `cliente` (`codUsr`) ON DELETE CASCADE,
  CONSTRAINT `pedido_ibfk_4` FOREIGN KEY (`codEst`) REFERENCES `establecimiento` (`codEst`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES ('PE00000001','CL00004','RE00003','ME00000001','ES00001','2022-04-09');
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `codPrd` varchar(10) NOT NULL,
  `precio` float NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `ingrediente1` varchar(20) DEFAULT NULL,
  `ingrediente2` varchar(20) DEFAULT NULL,
  `ingrediente3` varchar(20) DEFAULT NULL,
  `ingrediente4` varchar(20) DEFAULT NULL,
  `ingrediente5` varchar(20) DEFAULT NULL,
  `tipo` varchar(20) NOT NULL,
  PRIMARY KEY (`codPrd`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES ('PR00000001',4.99,'Quesarito','Ternera','Arroz','Queso','Nata agria',NULL,'Comida'),('PR00000002',4.99,'Quesarito Veggie','Veggie','Arroz','Queso','Nata agria',NULL,'Comida'),('PR00000003',1.99,'Nachos','Nachos','Salsa de queso','Salsa picante',NULL,NULL,'Aperitivo'),('PR00000004',3.99,'Chicken Nachos','Nachos','Salsa de queso','Salsa picante','Nuggets',NULL,'Aperitivo'),('PR00000005',2.99,'Patatas Mexican','Patatas fritas','Paprika',NULL,NULL,NULL,'Aperitivo'),('PR00000006',4.99,'Santa Monica','Ternera','Frijoles','Guacamole','Arroz','Pico de gallo','Comida'),('PR00000007',2.99,'Coca-Cola',NULL,NULL,NULL,NULL,NULL,'Bebida'),('PR00000008',3.99,'Nachos Deluxe','Nachos','Salsa de queso','Guacamole','Salsa picante','Nata agria','Aperitivo');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repartidor`
--

DROP TABLE IF EXISTS `repartidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repartidor` (
  `codUsr` varchar(7) NOT NULL DEFAULT '',
  `codVehiculo` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`codUsr`),
  CONSTRAINT `repartidor_ibfk_1` FOREIGN KEY (`codUsr`) REFERENCES `trabajador` (`codUsr`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repartidor`
--

LOCK TABLES `repartidor` WRITE;
/*!40000 ALTER TABLE `repartidor` DISABLE KEYS */;
INSERT INTO `repartidor` VALUES ('RE00003','2003MAR'),('RE00007','2534CNJ'),('RE00008','3563LBJ'),('RE00010','9734BBB');
/*!40000 ALTER TABLE `repartidor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabajador`
--

DROP TABLE IF EXISTS `trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trabajador` (
  `codUsr` varchar(7) NOT NULL DEFAULT '',
  `codEst` varchar(7) NOT NULL,
  `horario` varchar(20) DEFAULT NULL,
  `sueldo` float DEFAULT NULL,
  `tipo` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`codUsr`),
  KEY `codEst` (`codEst`),
  CONSTRAINT `trabajador_ibfk_1` FOREIGN KEY (`codUsr`) REFERENCES `usuarie` (`codUsr`) ON DELETE CASCADE,
  CONSTRAINT `trabajador_ibfk_2` FOREIGN KEY (`codEst`) REFERENCES `establecimiento` (`codEst`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajador`
--

LOCK TABLES `trabajador` WRITE;
/*!40000 ALTER TABLE `trabajador` DISABLE KEYS */;
INSERT INTO `trabajador` VALUES ('AU00002','ES00001','Tarde',1000,'Auxiliar'),('AU00005','ES00001','Mañana',1000,'Auxiliar'),('AU00006','ES00001','Mañana',1000,'Auxiliar'),('AU00009','ES00003','Mañana',1000,'Auxiliar'),('RE00003','ES00001','Tarde',1200,'Repartidor'),('RE00007','ES00001','Tarde',1000,'Repartidor'),('RE00008','ES00001','Mañana',1000,'Repartidor'),('RE00010','ES00003','Tarde',1000,'Repartidor');
/*!40000 ALTER TABLE `trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarie`
--

DROP TABLE IF EXISTS `usuarie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarie` (
  `codUsr` varchar(7) NOT NULL,
  `passwd` varchar(20) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellido` varchar(20) NOT NULL,
  `tipo` varchar(14) NOT NULL,
  PRIMARY KEY (`codUsr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarie`
--

LOCK TABLES `usuarie` WRITE;
/*!40000 ALTER TABLE `usuarie` DISABLE KEYS */;
INSERT INTO `usuarie` VALUES ('AD00001','abcd*1234','Nicolás','Rodríguez','Administrador'),('AU00002','abcd*1234','Daniel','Barrios','Trabajador'),('AU00005','abcd*1234','Nerea','Oceja','Trabajador'),('AU00006','abcd*1234','Sendoa','Badiola','Trabajador'),('AU00009','abcd*1234','Unai','Bonilla','Trabajador'),('CL00004','abcd*1234','Joana','Renteria','Cliente'),('CL00011','abcd*1234','Unai','Zulueta','Cliente'),('CL00012','abcd*1234','Julen','Barainca','Cliente'),('RE00003','abcd*1234','Markelito','Fernández','Trabajador'),('RE00007','abcd*1234','Fran','Ysasi-Fernandez','Trabajador'),('RE00008','abcd*1234','Alex','Irusta','Trabajador'),('RE00010','abcd*1234','Mikel','Bermejo','Trabajador');
/*!40000 ALTER TABLE `usuarie` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-16 14:12:59
