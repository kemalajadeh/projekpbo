-- MySQL dump 10.13  Distrib 8.4.3, for Win64 (x86_64)
--
-- Host: localhost    Database: db_kaskita
-- ------------------------------------------------------
-- Server version	8.4.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `db_kaskita`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `db_kaskita` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `db_kaskita`;

--
-- Table structure for table `tabel_kategori`
--

DROP TABLE IF EXISTS `tabel_kategori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tabel_kategori` (
  `id_kategori` int NOT NULL AUTO_INCREMENT,
  `nama_kategori` varchar(50) NOT NULL,
  `jenis` varchar(20) NOT NULL,
  PRIMARY KEY (`id_kategori`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabel_kategori`
--

LOCK TABLES `tabel_kategori` WRITE;
/*!40000 ALTER TABLE `tabel_kategori` DISABLE KEYS */;
INSERT INTO `tabel_kategori` VALUES (1,'bahan baku','Pemasukan'),(2,'bahan baku','Pengeluaran'),(3,'bahan','Pemasukan'),(4,'bahan','Pengeluaran');
/*!40000 ALTER TABLE `tabel_kategori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tabel_pengguna`
--

DROP TABLE IF EXISTS `tabel_pengguna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tabel_pengguna` (
  `id_pengguna` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`id_pengguna`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabel_pengguna`
--

LOCK TABLES `tabel_pengguna` WRITE;
/*!40000 ALTER TABLE `tabel_pengguna` DISABLE KEYS */;
INSERT INTO `tabel_pengguna` VALUES (1,'admin','admin123','Admin'),(2,'owner','owner123','Owner'),(3,'ahmad','ahmad123','User');
/*!40000 ALTER TABLE `tabel_pengguna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tabel_transaksi`
--

DROP TABLE IF EXISTS `tabel_transaksi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tabel_transaksi` (
  `id_transaksi` int NOT NULL AUTO_INCREMENT,
  `id_kategori` int NOT NULL,
  `id_pengguna` int NOT NULL,
  `tanggal` date NOT NULL,
  `nominal` double NOT NULL,
  `keterangan` varchar(255) NOT NULL,
  PRIMARY KEY (`id_transaksi`),
  KEY `id_kategori` (`id_kategori`),
  KEY `id_pengguna` (`id_pengguna`),
  CONSTRAINT `tabel_transaksi_ibfk_1` FOREIGN KEY (`id_kategori`) REFERENCES `tabel_kategori` (`id_kategori`),
  CONSTRAINT `tabel_transaksi_ibfk_2` FOREIGN KEY (`id_pengguna`) REFERENCES `tabel_pengguna` (`id_pengguna`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabel_transaksi`
--

LOCK TABLES `tabel_transaksi` WRITE;
/*!40000 ALTER TABLE `tabel_transaksi` DISABLE KEYS */;
INSERT INTO `tabel_transaksi` VALUES (8,3,1,'2026-11-25',150000,'bahan baku tempe'),(9,4,1,'2026-11-25',100000,'bahan baku tahu');
/*!40000 ALTER TABLE `tabel_transaksi` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-11-25 16:39:20
