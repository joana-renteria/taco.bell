-- MySQL dump 10.13  Distrib 5.7.29, for Win64 (x86_64)
--
-- Host: localhost    Database: taco_bell
-- ------------------------------------------------------
-- Server version	5.7.29-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
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
/*!40000 ALTER TABLE `establecimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `codMnu` varchar(10) NOT NULL,
  `codDsc` varchar(10) DEFAULT NULL,
  `precio` float NOT NULL,
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
  `fechaPed` date NOT NULL,
  PRIMARY KEY (`codPed`),
  KEY `codMnu` (`codMnu`),
  KEY `codRep` (`codRep`),
  KEY `codCle` (`codCle`),
  CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`codMnu`) REFERENCES `menu` (`codMnu`) ON DELETE CASCADE,
  CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`codRep`) REFERENCES `repartidor` (`codUsr`) ON DELETE CASCADE,
  CONSTRAINT `pedido_ibfk_3` FOREIGN KEY (`codCle`) REFERENCES `cliente` (`codUsr`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
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

-- Dump completed on 2022-04-07 13:20:36
