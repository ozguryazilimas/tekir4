-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: tekir
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.17.10.1

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
-- Table structure for table `DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` VALUES ('BASE-1','haky','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:52',1,'EXECUTED','7:8e28914cc13af6658c1cdec8782462c6','createTable tableName=GENERIC_SEQ; insert tableName=GENERIC_SEQ','',NULL,'3.5.3',NULL,NULL,'8123712603'),('BASE-2','haky','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:52',2,'EXECUTED','7:46499c3fa1af48a966f3c00a317421c0','createTable tableName=hibernate_sequence; insert tableName=hibernate_sequence','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1438162495087-2','haky (generated)','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:52',3,'EXECUTED','7:1dee2df0465b503d5ad3d9b7ce4e3b6f','createTable tableName=TLV_COMMANDS','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1438162495087-3','haky (generated)','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:52',4,'EXECUTED','7:2b56a63beebbf339077c79e7f4f5cfac','createTable tableName=TLV_EXEC_LOG','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1438162495087-4','haky (generated)','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:52',5,'EXECUTED','7:5969b758335c1e96f8f4fecb7f690560','createTable tableName=TLV_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1438162495087-5','haky (generated)','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:52',6,'EXECUTED','7:f661142f3c8ce573cf435546c2bcddc7','createTable tableName=TLV_SEQUENCE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1438162495087-6','haky (generated)','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:53',7,'EXECUTED','7:094b16c1f319c6db62b9a8fc2a34b268','createTable tableName=TLV_SUGGESTION_ITEM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1438162495087-14','haky (generated)','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:53',8,'EXECUTED','7:031e11cd9f2d4a06aaaff93e9159d549','createTable tableName=TLV_AUDIT_LOG','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1438162495087-16','haky (generated)','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:53',9,'EXECUTED','7:177385429889e1342d9e20234d239e15','createTable tableName=TLV_AUDIT_LOG_DET','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1438162495087-20','haky (generated)','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:53',10,'EXECUTED','7:eb4dbf836f0ae52d585a1ebccbdb51a7','addColumn tableName=TLV_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1438162495087-21','haky (generated)','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:53',11,'EXECUTED','7:b86ceac598fb08c35cbb7a96dc7f5e4a','addColumn tableName=TLV_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1438162495087-22','haky (generated)','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:53',12,'EXECUTED','7:56d14e01e90d597a1f508b49841b5485','addColumn tableName=TLV_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1438162495087-9','haky (generated)','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:53',13,'EXECUTED','7:f9450f94df649c365216c3a7ee1ee75c','addPrimaryKey constraintName=TLV_COMMANDSPK, tableName=TLV_COMMANDS','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1438162495087-10','haky (generated)','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:53',14,'EXECUTED','7:7b3ec35ccbe57cc8e86112b8b9f0ef7e','addPrimaryKey constraintName=TLV_EXEC_LOGPK, tableName=TLV_EXEC_LOG','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1438162495087-15','haky (generated)','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:53',15,'EXECUTED','7:c9e064a506cd0b70e054dff841b3377a','addPrimaryKey constraintName=TLV_AUDIT_LOGPK, tableName=TLV_AUDIT_LOG','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1438162495087-17','haky (generated)','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:53',16,'EXECUTED','7:68c572bad8870581102bfad7cff3650a','addPrimaryKey constraintName=TLV_AUDIT_DPK, tableName=TLV_AUDIT_LOG_DET','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1438162495087-11','haky (generated)','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:53',17,'EXECUTED','7:9413fbc8a08f742ac5eb4dccb8730f6d','addPrimaryKey constraintName=TLV_NOTEPK, tableName=TLV_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1438162495087-12','haky (generated)','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:53',18,'EXECUTED','7:ec2a0a9e4e75862d8d2cbb72507f3e69','addPrimaryKey constraintName=TLV_SEQUENCEPK, tableName=TLV_SEQUENCE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1438162495087-13','haky (generated)','liquibase/migration/telve-core-4.0.0.xml','2018-02-09 00:01:53',19,'EXECUTED','7:c17e17984de58c78ae9893034221c14e','addPrimaryKey constraintName=TLV_SUGGESTION_ITEMPK, tableName=TLV_SUGGESTION_ITEM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('kahve-1','huygun','liquibase/migration/kahve-4.0.0.xml','2018-02-09 00:01:53',20,'EXECUTED','7:a8ba35a904894082474c53f52bd49053','createTable tableName=KAHVE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-1','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:53',21,'EXECUTED','7:554dd316855b5e5597455e62bcb9f586','createTable tableName=TLI_GROUP','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-3','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:53',22,'EXECUTED','7:e8fefdac5970239d515e009403363b1b','createTable tableName=TLI_ROLE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-4','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:53',23,'EXECUTED','7:3f1209c6ebab9d8a07c57a73843a0777','createTable tableName=TLI_ROLE_PERM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-5','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:53',24,'EXECUTED','7:97314b5b45d643180b2a55923fc619da','createTable tableName=TLI_USER','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-6','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:53',25,'EXECUTED','7:972edaf3c2b7cfff8db562a1f55d1927','createTable tableName=TLI_USER_ATTR','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-7','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:54',26,'EXECUTED','7:13cebffea04540e93c666cf3744f1f21','createTable tableName=TLI_USER_GROUP','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-9','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:54',27,'EXECUTED','7:5cb76b466edac775a0ad068e0eee7ab8','createTable tableName=TLI_USER_ROLE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('default-user','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:54',28,'EXECUTED','7:65851766e9e109ef62ee09fa699e154a','insert tableName=TLI_USER','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-40','hakan.uygun','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:54',29,'EXECUTED','7:e28d2009af564481634b5bceabab8e49','addColumn tableName=TLI_USER','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-11','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:56',30,'EXECUTED','7:f805f2d8f310ebf7541ce4f03623417d','addForeignKeyConstraint baseTableName=TLI_USER_GROUP, constraintName=FK_cwoua04a5pyfbd082up2tfmx6, referencedTableName=TLI_USER','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-12','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:56',31,'EXECUTED','7:0d0bee2e68fa597bd136d70aa34fe0e8','addForeignKeyConstraint baseTableName=TLI_USER_ROLE, constraintName=FK_etsafcwp4b6ojei67xdspcf5f, referencedTableName=TLI_USER','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-13','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:56',32,'EXECUTED','7:6db9134d400e1e72a7628ee6daa683a5','addForeignKeyConstraint baseTableName=TLI_USER_GROUP, constraintName=FK_kjnhw3iwif4nnfmicyqqjcht0, referencedTableName=TLI_GROUP','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-25','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:56',33,'EXECUTED','7:8e115efe8fb6c0229198a9fdaa7d7f6b','addForeignKeyConstraint baseTableName=TLI_USER, constraintName=FK_kjnhw3iwif4nnfmicyqqjcht1, referencedTableName=TLI_GROUP','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-15','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:56',34,'EXECUTED','7:498ff3bf0160f0f638b8194c5e8969f7','addForeignKeyConstraint baseTableName=TLI_USER_ROLE, constraintName=FK_mfodp7tbtxxp6pj1rf14otrdk, referencedTableName=TLI_ROLE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-16','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:56',35,'EXECUTED','7:ed7c208b1180f561f356e5bcecfa2c02','addForeignKeyConstraint baseTableName=TLI_USER_ATTR, constraintName=FK_nfd7e34froibg422leqy1qmop, referencedTableName=TLI_USER','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-17','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:56',36,'EXECUTED','7:8c37e8f0e979a90be953fdefa7142b6a','addForeignKeyConstraint baseTableName=TLI_ROLE_PERM, constraintName=FK_p1jwmramrq4uks7ode7qo9slo, referencedTableName=TLI_ROLE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-19','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:56',37,'EXECUTED','7:61f0db848ec0a06d37aaca75bcb41274','addForeignKeyConstraint baseTableName=TLI_GROUP, constraintName=FK_sfuft7xjl327e7an6qtreau2k, referencedTableName=TLI_GROUP','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-21','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:56',38,'EXECUTED','7:d85e871b0a2be0dc6b1dd3c40888f40f','addUniqueConstraint constraintName=UC_TLI_GROUPCODE_COL, tableName=TLI_GROUP','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-22','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:56',39,'EXECUTED','7:46856e17f443ae06939375672d44015e','addUniqueConstraint constraintName=UC_TLI_ROLECODE_COL, tableName=TLI_ROLE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467025451677-30','oyas (generated)','liquibase/migration/telve-idm-4.0.1.xml','2018-02-09 00:01:56',40,'EXECUTED','7:e415fbadb5e8df64bd8f3da27c8569a4','addUniqueConstraint constraintName=TLI_USER_LOGIN_NAME, tableName=TLI_USER','',NULL,'3.5.3',NULL,NULL,'8123712603'),('20160714-1','hakan','liquibase/migration/tekir-feed-4.0.0.xml','2018-02-09 00:01:56',41,'EXECUTED','7:fa24c32e6474bb2c84b2b1e5beafc17c','createTable tableName=TFF_FEED','',NULL,'3.5.3',NULL,NULL,'8123712603'),('20160714-2','hakan','liquibase/migration/tekir-feed-4.0.0.xml','2018-02-09 00:01:56',42,'EXECUTED','7:4c00f3d4140691c7bbc15e22c7bd2887','createTable tableName=TFF_FEED_FOLLOW','',NULL,'3.5.3',NULL,NULL,'8123712603'),('20160714-3','hakan','liquibase/migration/tekir-feed-4.0.0.xml','2018-02-09 00:01:56',43,'EXECUTED','7:55452300e4002964b97e70fc43d85c1e','createTable tableName=TFF_FEED_MENTION','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467648713591-2','oyas (generated)','liquibase/migration/tekir-core-4.0.0.xml','2018-02-09 00:01:56',44,'EXECUTED','7:7fcd58a7664fa962df1a9159a1c0731a','createTable tableName=TCO_INDUSTRY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467648713591-3','oyas (generated)','liquibase/migration/tekir-core-4.0.0.xml','2018-02-09 00:01:56',45,'EXECUTED','7:128b901de9c18382a297cd8163aeb61f','createTable tableName=TCO_LOCATION','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467648713591-4','oyas (generated)','liquibase/migration/tekir-core-4.0.0.xml','2018-02-09 00:01:57',46,'EXECUTED','7:2d4b25a1edb41727ac81273b61977c68','createTable tableName=TCO_TERRITORY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467648713591-5','oyas (generated)','liquibase/migration/tekir-core-4.0.0.xml','2018-02-09 00:01:57',47,'EXECUTED','7:038297dce40fac226e6bfcf89f372a85','createTable tableName=TCO_TERRITORY_ITEM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467648713591-6','oyas (generated)','liquibase/migration/tekir-core-4.0.0.xml','2018-02-09 00:01:57',48,'EXECUTED','7:8b9b1fb7d911304f8422ac83510da68b','addForeignKeyConstraint baseTableName=TCO_TERRITORY_ITEM, constraintName=FK_TERIT_LOC, referencedTableName=TCO_LOCATION','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467648713591-7','oyas (generated)','liquibase/migration/tekir-core-4.0.0.xml','2018-02-09 00:01:57',49,'EXECUTED','7:46181c772d463cee9babf2a6b90e3f62','addForeignKeyConstraint baseTableName=TCO_TERRITORY_ITEM, constraintName=FK_TERIT_PARENT, referencedTableName=TCO_TERRITORY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467648713591-8','oyas (generated)','liquibase/migration/tekir-core-4.0.0.xml','2018-02-09 00:01:57',50,'EXECUTED','7:1cc379fbcd3e3ff7debcdbc7ce1829ad','addForeignKeyConstraint baseTableName=TCO_INDUSTRY, constraintName=FK_dvnd489u7yfnsbxn5uqna9hyj, referencedTableName=TCO_INDUSTRY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467648713591-9','oyas (generated)','liquibase/migration/tekir-core-4.0.0.xml','2018-02-09 00:01:57',51,'EXECUTED','7:5e1f47f2349a552048619c5ab184ada0','addForeignKeyConstraint baseTableName=TCO_LOCATION, constraintName=FK_lbo9phf3g4punwc3afdq1di4f, referencedTableName=TCO_LOCATION','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470428884610-1','oyas (generated)','liquibase/migration/tekir-core-ek1.xml','2018-02-09 00:01:57',52,'EXECUTED','7:c54cdd2268c2274f3510c506a85acda8','createTable tableName=TCO_UNIT_SET','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470428884610-2','oyas (generated)','liquibase/migration/tekir-core-ek1.xml','2018-02-09 00:01:57',53,'EXECUTED','7:a899f93136c067a396c3405cd0c45231','createTable tableName=TCO_UNIT_SET_ITEM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470428884610-3','oyas (generated)','liquibase/migration/tekir-core-ek1.xml','2018-02-09 00:01:57',54,'EXECUTED','7:ef5d44c0c9d25e7e6c048f4e64d03c64','addForeignKeyConstraint baseTableName=TCO_UNIT_SET_ITEM, constraintName=FK_UNITIT_US, referencedTableName=TCO_UNIT_SET','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470590129963-1','oyas (generated)','liquibase/migration/tekir-core-ek2.xml','2018-02-09 00:01:57',55,'EXECUTED','7:9b032421ea7a60e0875a054c089c61c1','createTable tableName=TCO_TAX','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479310886059-1','oyas (generated)','liquibase/migration/tekir-core-ek2.xml','2018-02-09 00:01:57',56,'EXECUTED','7:8c1f73c7acfe793fc38c64cdba3bb111','createTable tableName=TCO_PAYMENT_PLAN','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479743861726-1','oyas (generated)','liquibase/migration/tekir-core-ek2.xml','2018-02-09 00:01:57',57,'EXECUTED','7:4b1462c280b393ca34d796d33839bfc9','createTable tableName=TCO_BANK_CASH_ACC','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480285182307-1','oyas (generated)','liquibase/migration/tekir-core-ek2.xml','2018-02-09 00:01:57',58,'EXECUTED','7:cbf61a421798295b5a57b6cc667af996','createTable tableName=TCO_EXCHANGE_RATE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480327786898-1','oyas (generated)','liquibase/migration/tekir-core-ek2.xml','2018-02-09 00:01:57',59,'EXECUTED','7:e5c4ea499a76de349097e701dc3fbc57','addColumn tableName=TCO_EXCHANGE_RATE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480327786898-2','oyas (generated)','liquibase/migration/tekir-core-ek2.xml','2018-02-09 00:01:57',60,'EXECUTED','7:381992e61480ee9f0cdfb4ce599c0bbf','addColumn tableName=TCO_EXCHANGE_RATE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480327786898-18','oyas (generated)','liquibase/migration/tekir-core-ek2.xml','2018-02-09 00:01:57',61,'EXECUTED','7:eec4342ac454d5d9014570be01b6254a','dropColumn columnName=RATE, tableName=TCO_EXCHANGE_RATE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467648713591-1','oyas (generated)','liquibase/migration/tekir-commodity-4.0.0.xml','2018-02-09 00:01:57',62,'EXECUTED','7:99be7e369016484a92540d13e3e92b27','createTable tableName=TCO_COMMOTITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470598444235-1','oyas (generated)','liquibase/migration/tekir-commodity-ek1.xml','2018-02-09 00:01:57',63,'EXECUTED','7:1e56d21fb4a772fb91c91f7d3f42ffa4','createTable tableName=TCO_COMMOTITY_CAT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470598444235-2','oyas (generated)','liquibase/migration/tekir-commodity-ek1.xml','2018-02-09 00:01:57',64,'EXECUTED','7:6a9911389245b76107607df377b0a565','addColumn tableName=TCO_COMMOTITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470598444235-3','oyas (generated)','liquibase/migration/tekir-commodity-ek1.xml','2018-02-09 00:01:57',65,'EXECUTED','7:50ff6d60c93350e8f83e967fa9c445f1','addColumn tableName=TCO_COMMOTITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470598444235-4','oyas (generated)','liquibase/migration/tekir-commodity-ek1.xml','2018-02-09 00:01:57',66,'EXECUTED','7:482fa45f507a5610f3d4a348b2955b5b','addColumn tableName=TCO_COMMOTITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470598444235-5','oyas (generated)','liquibase/migration/tekir-commodity-ek1.xml','2018-02-09 00:01:58',67,'EXECUTED','7:af18085fb0b7d1419e7c7ad121e6723c','addColumn tableName=TCO_COMMOTITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470598444235-6','oyas (generated)','liquibase/migration/tekir-commodity-ek1.xml','2018-02-09 00:01:58',68,'EXECUTED','7:cb204f7a6cf6414c84f40e48b92a8d6c','addColumn tableName=TCO_COMMOTITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470598444235-7','oyas (generated)','liquibase/migration/tekir-commodity-ek1.xml','2018-02-09 00:01:58',69,'EXECUTED','7:d72e677da31ce2a01092968bde690c18','addColumn tableName=TCO_COMMOTITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470598444235-8','oyas (generated)','liquibase/migration/tekir-commodity-ek1.xml','2018-02-09 00:01:58',70,'EXECUTED','7:04695b81b9369ce6afa77222105eea87','addColumn tableName=TCO_COMMOTITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470598444235-9','oyas (generated)','liquibase/migration/tekir-commodity-ek1.xml','2018-02-09 00:01:58',71,'EXECUTED','7:9aa0acccd137ff895117bff537b24f89','addColumn tableName=TCO_COMMOTITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470598444235-15','oyas (generated)','liquibase/migration/tekir-commodity-ek1.xml','2018-02-09 00:01:58',72,'EXECUTED','7:a6feb5897dcf3f4f5472b2f6fe75673e','addColumn tableName=TCO_COMMOTITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470598444235-10','oyas (generated)','liquibase/migration/tekir-commodity-ek1.xml','2018-02-09 00:01:58',73,'EXECUTED','7:a858586d89c4b34443cb438c21106361','addForeignKeyConstraint baseTableName=TCO_COMMOTITY_CAT, constraintName=FK_6xdebpq4d9ldt8e4fwyhd0bls, referencedTableName=TCO_COMMOTITY_CAT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470598444235-11','oyas (generated)','liquibase/migration/tekir-commodity-ek1.xml','2018-02-09 00:01:58',74,'EXECUTED','7:6b80ac2ab1906f5bfffb9ff15b256f9f','addForeignKeyConstraint baseTableName=TCO_COMMOTITY, constraintName=FK_COMM_CAT, referencedTableName=TCO_COMMOTITY_CAT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470598444235-12','oyas (generated)','liquibase/migration/tekir-commodity-ek1.xml','2018-02-09 00:01:58',75,'EXECUTED','7:964152abcd76133c0b44baa3ce86bf93','addForeignKeyConstraint baseTableName=TCO_COMMOTITY, constraintName=FK_COMM_TAX1, referencedTableName=TCO_TAX','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470598444235-13','oyas (generated)','liquibase/migration/tekir-commodity-ek1.xml','2018-02-09 00:01:58',76,'EXECUTED','7:06838363e2916bad3c03d28d5be528bd','addForeignKeyConstraint baseTableName=TCO_COMMOTITY, constraintName=FK_COMM_TAX2, referencedTableName=TCO_TAX','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470598444235-14','oyas (generated)','liquibase/migration/tekir-commodity-ek1.xml','2018-02-09 00:01:58',77,'EXECUTED','7:486e79194b085d48e9edd7a4b68d56d3','addForeignKeyConstraint baseTableName=TCO_COMMOTITY, constraintName=FK_COMM_TAX3, referencedTableName=TCO_TAX','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470598444235-16','oyas (generated)','liquibase/migration/tekir-commodity-ek1.xml','2018-02-09 00:01:58',78,'EXECUTED','7:5f92ffb97e68eb2c372f9646bcf72665','addForeignKeyConstraint baseTableName=TCO_COMMOTITY, constraintName=FK_COMM_UNITSET, referencedTableName=TCO_UNIT_SET','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-2','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:58',79,'EXECUTED','7:91ace46d616bfec6073034c41ce42985','createTable tableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-3','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:58',80,'EXECUTED','7:85d52491a956995bb237e94883133449','createTable tableName=TCC_CONTACT_CAT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-5','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:58',81,'EXECUTED','7:a6789ddbc7480dd6cc26a52b4863cdc0','createTable tableName=TCC_CONTACT_INFO','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-6','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:58',82,'EXECUTED','7:b9570e35cff2db00e297fa0276b52b97','createTable tableName=TCC_CONTACT_REL','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-7','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:58',83,'EXECUTED','7:98ed6e1f598441abf2e00621cda0969c','createTable tableName=TCC_CORP_TYPE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467650592995-1','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:59',84,'EXECUTED','7:03995f53c1beb722ed864cb24e1afd26','createTable tableName=TCC_CONTACT_REL_DEF','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436684-1','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:59',85,'EXECUTED','7:b1e8edc04c136d0a30c6cb50f0bd64a8','createTable tableName=TCC_ACCOUNT_TXN','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479746567441-1','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:59',86,'EXECUTED','7:02dda08e17ffcda19330d6f2e576c322','addColumn tableName=TCC_CONTACT_INFO','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479746567441-2','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:59',87,'EXECUTED','7:429e7a77a043c97587614b47a7745808','addColumn tableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479746567441-3','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:59',88,'EXECUTED','7:6c03143d276a74923d107bc54af4e3d5','addColumn tableName=TCC_CONTACT_INFO','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479746567441-4','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:59',89,'EXECUTED','7:6e59d1ce5854ca3f85141c87c310f86d','addColumn tableName=TCC_CONTACT_INFO','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-9','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:59',90,'EXECUTED','7:8c1f22359fa2a578ad7739a9959efedf','addForeignKeyConstraint baseTableName=TCC_CONTACT_INFO, constraintName=FK_CONADDR_LOC, referencedTableName=TCO_LOCATION','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-12','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:59',91,'EXECUTED','7:8070f03911533d96869130f7042dc9fc','addForeignKeyConstraint baseTableName=TCC_CONTACT_INFO, constraintName=FK_CONINF_CONTACT, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-13','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:59',92,'EXECUTED','7:1853e9b5607b66e07448fdf264e1027d','addForeignKeyConstraint baseTableName=TCC_CONTACT_REL, constraintName=FK_CONREL_REL, referencedTableName=TCC_CONTACT_REL_DEF','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-14','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:59',93,'EXECUTED','7:9f7d35ea33dd382c3584b9f779808b46','addForeignKeyConstraint baseTableName=TCC_CONTACT_REL, constraintName=FK_CONREL_SOURCE, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-15','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:59',94,'EXECUTED','7:0d6d3f151e6e4d8479406ff8f952bfd1','addForeignKeyConstraint baseTableName=TCC_CONTACT_REL, constraintName=FK_CONREL_TARGET, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-16','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:59',95,'EXECUTED','7:d37f88a959f0ae3993fcc42d578e1111','addForeignKeyConstraint baseTableName=TCC_CONTACT, constraintName=FK_CON_ADDR, referencedTableName=TCC_CONTACT_INFO','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-17','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:59',96,'EXECUTED','7:b03338fd046abf9abe0fdaefa580fadd','addForeignKeyConstraint baseTableName=TCC_CONTACT, constraintName=FK_CON_CAT, referencedTableName=TCC_CONTACT_CAT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-18','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:01:59',97,'EXECUTED','7:7fc90b6078fc960263afc5fa6fe3eed1','addForeignKeyConstraint baseTableName=TCC_CONTACT, constraintName=FK_CON_EMAIL, referencedTableName=TCC_CONTACT_INFO','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-19','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:00',98,'EXECUTED','7:eeeb3c23faefef508e0ada124d653441','addForeignKeyConstraint baseTableName=TCC_CONTACT, constraintName=FK_CON_FAX, referencedTableName=TCC_CONTACT_INFO','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-20','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:00',99,'EXECUTED','7:574621357fdf99f1ce13e3e28fef030f','addForeignKeyConstraint baseTableName=TCC_CONTACT, constraintName=FK_CON_INDUSTRY, referencedTableName=TCO_INDUSTRY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-21','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:00',100,'EXECUTED','7:06409b2656ed22a77fb025a17a591fa2','addForeignKeyConstraint baseTableName=TCC_CONTACT, constraintName=FK_CON_MOBILE, referencedTableName=TCC_CONTACT_INFO','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-22','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:00',101,'EXECUTED','7:0a5dd5daeee58fdb8d47f75ae2a767b2','addForeignKeyConstraint baseTableName=TCC_CONTACT, constraintName=FK_CON_PHONE, referencedTableName=TCC_CONTACT_INFO','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-23','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:00',102,'EXECUTED','7:5aff7216ebf2bfa3b0a297bd969590ac','addForeignKeyConstraint baseTableName=TCC_CONTACT, constraintName=FK_CON_TER, referencedTableName=TCO_TERRITORY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-24','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:00',103,'EXECUTED','7:9c3b640de1aeed91d2419c45da00b694','addForeignKeyConstraint baseTableName=TCC_CONTACT, constraintName=FK_CORP_PARENT, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-25','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:00',104,'EXECUTED','7:85d28956bc0e6f860d9b1d5a2f321dd9','addForeignKeyConstraint baseTableName=TCC_CONTACT, constraintName=FK_CORP_PER, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-26','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:00',105,'EXECUTED','7:359c7723f8a8737693205e0aea6cf129','addForeignKeyConstraint baseTableName=TCC_CONTACT, constraintName=FK_CORP_TYPE, referencedTableName=TCC_CORP_TYPE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-27','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:00',106,'EXECUTED','7:85a057a1fc5ed5ed8d18f47c1d0889a8','addForeignKeyConstraint baseTableName=TCC_CONTACT, constraintName=FK_PER_CORP, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1467649436683-28','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:01',107,'EXECUTED','7:ed575c02a5c201d7816e212198dfdc13','addForeignKeyConstraint baseTableName=TCC_CONTACT_CAT, constraintName=FK_sjkf2ep6fcrq182sdjar52ttb, referencedTableName=TCC_CONTACT_CAT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480342069846-1','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:01',108,'EXECUTED','7:b7ae790038dfcfa9d3af237def0dbd69','addColumn tableName=TCC_ACCOUNT_TXN','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1492182980983-1','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:01',109,'EXECUTED','7:9c425de453e4ef36a12115d932cb1bc2','addColumn tableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1505062932003-1','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:01',110,'EXECUTED','7:0f28bde65b2c43e512a5a75ed2143e3f','addColumn tableName=TCC_CONTACT_REL_DEF','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1505062932003-2','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:01',111,'EXECUTED','7:972e2c5f8de6f31291e6c7cc162fec2f','addColumn tableName=TCC_CONTACT_REL_DEF','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1505062932003-3','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:01',112,'EXECUTED','7:214727695577003dab614590befdbbb1','addColumn tableName=TCC_CONTACT_REL_DEF','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1505062932003-4','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:01',113,'EXECUTED','7:85f811aca7716635bbc8ad909c8913f2','addColumn tableName=TCC_CONTACT_REL_DEF','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1505062932003-5','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:01',114,'EXECUTED','7:7d9f855b61f583df5a8a22df0fbce16d','addColumn tableName=TCC_CONTACT_REL_DEF','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1505062932003-11','oyas (generated)','liquibase/migration/tekir-contact-4.0.0.xml','2018-02-09 00:02:01',115,'EXECUTED','7:345d603ca6d56a64d901a442b47d51a7','dropColumn columnName=ROLES, tableName=TCC_CONTACT_REL_DEF','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469625097267-1','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:01',116,'EXECUTED','7:fa85111c19b97b4ee6c280ad991f3284','createTable tableName=TCA_ACTIVITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469625097267-2','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:01',117,'EXECUTED','7:0b7dd0a01fa7130a410fece1a441cedf','addForeignKeyConstraint baseTableName=TCA_ACTIVITY, constraintName=FK_ACC_ADDR, referencedTableName=TCC_CONTACT_INFO','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469625097267-3','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:01',118,'EXECUTED','7:9e83b995c7ff7d4741449f9d06cc04d4','addForeignKeyConstraint baseTableName=TCA_ACTIVITY, constraintName=FK_ACC_COR, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469625097267-4','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:01',119,'EXECUTED','7:10bf1c6a18ae96da633d695b2c9fcff1','addForeignKeyConstraint baseTableName=TCA_ACTIVITY, constraintName=FK_ACC_FOLL, referencedTableName=TCA_ACTIVITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469625097267-5','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:01',120,'EXECUTED','7:28efd670936d17147e5d288e892b90d5','addForeignKeyConstraint baseTableName=TCA_ACTIVITY, constraintName=FK_ACC_PER, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1503931845102-1','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:02',121,'EXECUTED','7:808604abc6fcf727100b80431abb872f','createTable tableName=TCA_ACT_MENTION','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1503931845102-2','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:02',122,'EXECUTED','7:4c857c49199c403f05fa1d03026303c2','addForeignKeyConstraint baseTableName=TCA_ACT_MENTION, constraintName=FK_ACTIVITY_FEED, referencedTableName=TCA_ACTIVITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1504351557656-1','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:02',123,'EXECUTED','7:78ab3cab8a5516969c3f370cf81bf52f','addColumn tableName=TCA_ACTIVITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1506975677459-1','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:02',124,'EXECUTED','7:fa18be9e29aebc9bd5b7808ba8800723','addColumn tableName=TCA_ACTIVITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1506975677459-2','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:02',125,'EXECUTED','7:05db79b9b948af8d3ab2f9741bb28be3','addColumn tableName=TCA_ACTIVITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1506975677459-3','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:02',126,'EXECUTED','7:f14d465c5bad82936a2f24d1eb1a0fdf','addColumn tableName=TCA_ACTIVITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1506975677459-4','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:02',127,'EXECUTED','7:2f74aa5c74135823b96d163dd236eb0f','addColumn tableName=TCA_ACTIVITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1506975677459-5','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:02',128,'EXECUTED','7:90c4adbae040bb58e9e8c5078005d609','addColumn tableName=TCA_ACTIVITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1506975677459-6','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:02',129,'EXECUTED','7:4289f088f0a1d1d3900b7def9070d4e0','addColumn tableName=TCA_ACTIVITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1506975677459-7','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:02',130,'EXECUTED','7:79b3b40f171dc320809d8a6e36035185','addColumn tableName=TCA_ACTIVITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1507112519400-3','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:02',131,'EXECUTED','7:4a7a29633e731d52ece8d8314c8fa5b9','modifyDataType columnName=BODY, tableName=TCA_ACTIVITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1507112519400-4','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:02',132,'EXECUTED','7:88c35eea9b84416242d100f254ce331a','modifyDataType columnName=EM_CC, tableName=TCA_ACTIVITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1507112519400-5','oyas (generated)','liquibase/migration/tekir-activity-4.0.0.xml','2018-02-09 00:02:02',133,'EXECUTED','7:bb0241dd847275b4c3151875670ec709','modifyDataType columnName=EM_TO, tableName=TCA_ACTIVITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478451375584-1','oyas (generated)','liquibase/migration/tekir-voucher-4.0.0.xml','2018-02-09 00:02:02',134,'EXECUTED','7:83040aa08906321fd1f1d44909e7c11b','createTable tableName=TVO_GROUP','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478451375584-2','oyas (generated)','liquibase/migration/tekir-voucher-4.0.0.xml','2018-02-09 00:02:03',135,'EXECUTED','7:c1370f198cac5871899b40f95b5a68a0','createTable tableName=TVO_PROCESS','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478451375584-3','oyas (generated)','liquibase/migration/tekir-voucher-4.0.0.xml','2018-02-09 00:02:03',136,'EXECUTED','7:2a078cd76539bdbd163614eb86d58beb','addForeignKeyConstraint baseTableName=TVO_PROCESS, constraintName=FK_PROCESS_ACC, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479824343704-1','oyas (generated)','liquibase/migration/tekir-voucher-4.0.0.xml','2018-02-09 00:02:03',137,'EXECUTED','7:55696c0eaaf57f885dc2a80cc0ceea0b','createTable tableName=TVO_MATCHABLE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479824343704-2','oyas (generated)','liquibase/migration/tekir-voucher-4.0.0.xml','2018-02-09 00:02:03',138,'EXECUTED','7:e596cb4577d925630192b20ef1f77841','createTable tableName=TVO_MATCHER','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479824343704-3','oyas (generated)','liquibase/migration/tekir-voucher-4.0.0.xml','2018-02-09 00:02:03',139,'EXECUTED','7:3347337e5050e51d8f097fe7e0520271','addForeignKeyConstraint baseTableName=TVO_MATCHER, constraintName=FK_MATCHER_MATCH, referencedTableName=TVO_MATCHABLE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479824343704-4','oyas (generated)','liquibase/migration/tekir-voucher-4.0.0.xml','2018-02-09 00:02:03',140,'EXECUTED','7:7eb6b10622df730556ebd885427b2dfb','addForeignKeyConstraint baseTableName=TVO_MATCHABLE, constraintName=FK_MATCH_ACC, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479895591607-1','oyas (generated)','liquibase/migration/tekir-voucher-4.0.0.xml','2018-02-09 00:02:03',141,'EXECUTED','7:efe54259f31ffe18187d67188c523a3f','addColumn tableName=TVO_PROCESS','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480345813271-1','oyas (generated)','liquibase/migration/tekir-voucher-4.0.0.xml','2018-02-09 00:02:03',142,'EXECUTED','7:7e93320523f8f884fed043ee201018f8','addColumn tableName=TVO_MATCHABLE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480345813271-4','oyas (generated)','liquibase/migration/tekir-voucher-4.0.0.xml','2018-02-09 00:02:03',143,'EXECUTED','7:e197219741db71792765bea24bd613d5','addColumn tableName=TVO_MATCHABLE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480345813271-2','oyas (generated)','liquibase/migration/tekir-voucher-4.0.0.xml','2018-02-09 00:02:03',144,'EXECUTED','7:7c16c37f9bb1bbde4dab1ff29d1f61ae','addColumn tableName=TVO_MATCHER','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1491230740840-1','oyas (generated)','liquibase/migration/tekir-voucher-4.0.0.xml','2018-02-09 00:02:03',145,'EXECUTED','7:5624aeca02f1f39f3869d6e9c896c037','addColumn tableName=TVO_GROUP','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1491235561935-1','oyas (generated)','liquibase/migration/tekir-voucher-4.0.0.xml','2018-02-09 00:02:03',146,'EXECUTED','7:a115d2f310281aaa81d48a5063e32ab0','addColumn tableName=TVO_GROUP','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1506243814559-1','oyas (generated)','liquibase/migration/tekir-voucher-4.0.0.xml','2018-02-09 00:02:03',147,'EXECUTED','7:293edea7be962abb70c58f64dc03a496','createTable tableName=TVO_PROCESS_STAKEHOLDER','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1506243814559-2','oyas (generated)','liquibase/migration/tekir-voucher-4.0.0.xml','2018-02-09 00:02:03',148,'EXECUTED','7:01fdeaf97da4d2d84ad9025b2c29eaec','addForeignKeyConstraint baseTableName=TVO_PROCESS_STAKEHOLDER, constraintName=FK_STAKE_CONTACT, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1506243814559-3','oyas (generated)','liquibase/migration/tekir-voucher-4.0.0.xml','2018-02-09 00:02:03',149,'EXECUTED','7:1cc40c66715c7e9e0f190ca655b6c9cc','addForeignKeyConstraint baseTableName=TVO_PROCESS_STAKEHOLDER, constraintName=FK_STAKE_PROCESS, referencedTableName=TVO_PROCESS','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480082261131-1','oyas (generated)','liquibase/migration/tekir-finance-4.0.0.xml','2018-02-09 00:02:03',150,'EXECUTED','7:e528c7c49397532c72a5549437adda2e','createTable tableName=TFN_FINANCE_ACC','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480082261131-2','oyas (generated)','liquibase/migration/tekir-finance-4.0.0.xml','2018-02-09 00:02:03',151,'EXECUTED','7:08f1d67a47cd22dc881b970cd06bbe02','createTable tableName=TFN_FINANCE_TXN','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1482317043182-1','oyas (generated)','liquibase/migration/tekir-finance-4.0.0.xml','2018-02-09 00:02:03',152,'EXECUTED','7:85b25393dd670f476cd4b0d1f540c6a0','createTable tableName=TFN_FINANCE_VIREMENT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480082261131-3','oyas (generated)','liquibase/migration/tekir-finance-4.0.0.xml','2018-02-09 00:02:04',153,'EXECUTED','7:29bd6d9f95ffeaedf06e07ab81652541','addForeignKeyConstraint baseTableName=TFN_FINANCE_TXN, constraintName=FK_FINACCTXN_ACC, referencedTableName=TFN_FINANCE_ACC','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480086140820-1','oyas (generated)','liquibase/migration/tekir-finance-4.0.0.xml','2018-02-09 00:02:04',154,'EXECUTED','7:0148fb010e340171bc33cf83aaf633c6','addColumn tableName=TFN_FINANCE_ACC','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480342120913-1','oyas (generated)','liquibase/migration/tekir-finance-4.0.0.xml','2018-02-09 00:02:04',155,'EXECUTED','7:8803f8b9937e623a289519741ae26cd8','addColumn tableName=TFN_FINANCE_TXN','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1482317043182-2','oyas (generated)','liquibase/migration/tekir-finance-4.0.0.xml','2018-02-09 00:02:04',156,'EXECUTED','7:a9f6893a0383c26aad0ea0fa6aa64459','addForeignKeyConstraint baseTableName=TFN_FINANCE_VIREMENT, constraintName=FK_FAV_FACC, referencedTableName=TFN_FINANCE_ACC','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1482317043182-3','oyas (generated)','liquibase/migration/tekir-finance-4.0.0.xml','2018-02-09 00:02:04',157,'EXECUTED','7:4c9e56f486ceb4d3b7ba1951ef664912','addForeignKeyConstraint baseTableName=TFN_FINANCE_VIREMENT, constraintName=FK_FAV_TACC, referencedTableName=TFN_FINANCE_ACC','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1482482197773-1','oyas (generated)','liquibase/migration/tekir-finance-4.0.0.xml','2018-02-09 00:02:04',158,'EXECUTED','7:2aee09855490dc8d9cd1e313ad4fbd9b','addColumn tableName=TFN_FINANCE_VIREMENT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1482482197773-2','oyas (generated)','liquibase/migration/tekir-finance-4.0.0.xml','2018-02-09 00:02:04',159,'EXECUTED','7:960220e4650e4f27c1ccd35d01e79c6e','addColumn tableName=TFN_FINANCE_VIREMENT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1482482197773-3','oyas (generated)','liquibase/migration/tekir-finance-4.0.0.xml','2018-02-09 00:02:04',160,'EXECUTED','7:441932fd02bacb43708e8a19612ada9b','addColumn tableName=TFN_FINANCE_VIREMENT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1482482197773-4','oyas (generated)','liquibase/migration/tekir-finance-4.0.0.xml','2018-02-09 00:02:04',161,'EXECUTED','7:4c81162e1ea3594e8432a10b8cd633ed','addColumn tableName=TFN_FINANCE_VIREMENT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1482482197773-5','oyas (generated)','liquibase/migration/tekir-finance-4.0.0.xml','2018-02-09 00:02:04',162,'EXECUTED','7:ea897a0c643990c4ea8fbf670ef60231','addColumn tableName=TFN_FINANCE_VIREMENT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1482482197773-6','oyas (generated)','liquibase/migration/tekir-finance-4.0.0.xml','2018-02-09 00:02:04',163,'EXECUTED','7:65b26de8a7a0150fd83f838ab9f02690','dropColumn columnName=AMOUNT, tableName=TFN_FINANCE_VIREMENT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1482482197773-7','oyas (generated)','liquibase/migration/tekir-finance-4.0.0.xml','2018-02-09 00:02:04',164,'EXECUTED','7:75951bee56303bcfc23a6e337cabe588','dropColumn columnName=CCY, tableName=TFN_FINANCE_VIREMENT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469889916962-1','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:04',165,'EXECUTED','7:d384e4d41cd6169661cfa49b1b06faf1','createTable tableName=TCO_OPPOTUNITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469889916962-4','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:04',166,'EXECUTED','7:4a79ecd213028445ee5fd04bb3a2bce8','addColumn tableName=TCO_OPPOTUNITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469889916962-10','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:04',167,'EXECUTED','7:6865f62971ab0283ec94a4f458abf8c1','addColumn tableName=TCO_OPPOTUNITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469889916962-11','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:04',168,'EXECUTED','7:b0805fc1cc4f0e00fa6e7f93561340df','addColumn tableName=TCO_OPPOTUNITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469889916962-12','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:04',169,'EXECUTED','7:d64557f1a9f5c61f132ddfb82201da5f','addColumn tableName=TCO_OPPOTUNITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478453126259-5','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:05',170,'EXECUTED','7:62c8f529ddbf92f01d5bca6677fa9e46','dropColumn tableName=TCO_OPPOTUNITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478453126259-6','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:05',171,'EXECUTED','7:3b48df53de57d889ece97fdde7b3a04d','addColumn tableName=TCO_OPPOTUNITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478453126259-1','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:05',172,'EXECUTED','7:b1eb3981886ca8f65efa17a438543481','addColumn tableName=TCO_OPPOTUNITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470507539036-1','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:05',173,'EXECUTED','7:c3a35bbf2c6481cd7661fd7b0703a11c','addColumn tableName=TCO_OPPOTUNITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1470507539036-2','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:05',174,'EXECUTED','7:9183c22e78fac5d40eed7c929e6585ab','addColumn tableName=TCO_OPPOTUNITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469889916962-2','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:05',175,'EXECUTED','7:3fda15b844c20f0c0f86fa1703726007','addForeignKeyConstraint baseTableName=TCO_OPPOTUNITY, constraintName=FK_OPP_ACC, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469889916962-3','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:05',176,'EXECUTED','7:4d29ec33377a74fe3db4fc4a6c8fe5a2','addForeignKeyConstraint baseTableName=TCO_OPPOTUNITY, constraintName=FK_OPP_PER, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469889916962-5','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:05',177,'EXECUTED','7:6a8010dfa2a919b05320b331fc6db2a2','addForeignKeyConstraint baseTableName=TCO_OPPOTUNITY, constraintName=FK_OPP_COMP, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478453126263-1','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:05',178,'EXECUTED','7:ac6139980c45c5feeea78334fde44c62','addColumn tableName=TCO_OPPOTUNITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1506279953350-1','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:05',179,'EXECUTED','7:4741e11579707fd56deba10c9e3db2ad','createTable tableName=TCO_OPPOTUNITY_ITEM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1506279953350-2','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:05',180,'EXECUTED','7:63ba622808ad584ee7a3858bec15b126','addForeignKeyConstraint baseTableName=TCO_OPPOTUNITY_ITEM, constraintName=FK_OPP_COMM, referencedTableName=TCO_COMMOTITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1506279953350-3','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:05',181,'EXECUTED','7:b6eeed0808732eb20cd9140f3586f3c4','addForeignKeyConstraint baseTableName=TCO_OPPOTUNITY_ITEM, constraintName=FK_OPP_OPP, referencedTableName=TCO_OPPOTUNITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1506279953350-4','oyas (generated)','liquibase/migration/tekir-opportunity-4.0.0.xml','2018-02-09 00:02:05',182,'EXECUTED','7:bf89da35eefe16bd1af89079e75335b9','addForeignKeyConstraint baseTableName=TCO_OPPOTUNITY, constraintName=FK_PROSS_VOG, referencedTableName=TVO_PROCESS','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469961995803-1','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:05',183,'EXECUTED','7:9beb0e0c9493e334f1eda59b81548c30','createTable tableName=TSQ_QUOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469961995803-2','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:05',184,'EXECUTED','7:6911985ada13616d1fb1f25a207ee7b5','createTable tableName=TSQ_QUOTE_ITEM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469961995803-5','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:05',185,'EXECUTED','7:8e2fae758f56bc35dc0f19525bab44b3','createTable tableName=TSQ_QUOTE_SUM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469889916962-4','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:06',186,'EXECUTED','7:f66132a080a572d76c05864e2a295f7f','addColumn tableName=TSQ_QUOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469889916962-10','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:06',187,'EXECUTED','7:af1f6c08e0b019fcc7c761a23a2c53d2','addColumn tableName=TSQ_QUOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469889916962-11','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:06',188,'EXECUTED','7:406fd0aab6f06b0005cf272596f00469','addColumn tableName=TSQ_QUOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469889916962-12','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:06',189,'EXECUTED','7:4c59f01ec41bbb8288bc5b130b47fe6a','addColumn tableName=TSQ_QUOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478453265724-5','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:06',190,'EXECUTED','7:7be45c0905b0c861e7a4305d5f95cfe9','dropColumn tableName=TSQ_QUOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478453265724-6','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:06',191,'EXECUTED','7:d910be72340480bf6f45e87843c8b593','addColumn tableName=TSQ_QUOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478453265724-1','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:06',192,'EXECUTED','7:32d4084cbdfaa3bdf3c624f0528d9ad1','addColumn tableName=TSQ_QUOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478459604822-1','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:06',193,'EXECUTED','7:d985430bcd7b6ce6fb3f3c37e4d42ffa','addColumn tableName=TSQ_QUOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479311498719-1','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:06',194,'EXECUTED','7:f3d6e9448b074436c8dbf9776e6a02a4','addColumn tableName=TSQ_QUOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479395667297-1','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:06',195,'EXECUTED','7:37061383ee4e0d32e11195f0fac3c9a1','addColumn tableName=TSQ_QUOTE_ITEM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479395667297-2','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:06',196,'EXECUTED','7:b00ad968b62e82c1c19b23751baf64b3','addColumn tableName=TSQ_QUOTE_ITEM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479395667297-3','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:06',197,'EXECUTED','7:5e0b3507defccab1d695c6621ea4d4ab','addColumn tableName=TSQ_QUOTE_ITEM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479311498719-2','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:06',198,'EXECUTED','7:1c0d7290d95b6805371d760b3baf8194','addForeignKeyConstraint baseTableName=TSQ_QUOTE, constraintName=FK_OPP_PP, referencedTableName=TCO_PAYMENT_PLAN','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469961995803-3','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:06',199,'EXECUTED','7:5caaa4ae69f4ca99a7eef0068edd3817','addForeignKeyConstraint baseTableName=TSQ_QUOTE_ITEM, constraintName=FK_QUOTEIT_COMMO, referencedTableName=TCO_COMMOTITY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469961995803-4','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:06',200,'EXECUTED','7:94d1bd775bc6f4a4fe6d21a3b5533236','addForeignKeyConstraint baseTableName=TSQ_QUOTE_ITEM, constraintName=FK_QUOTEIT_QUOTE, referencedTableName=TSQ_QUOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469961995803-6','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:06',201,'EXECUTED','7:480f1e3d0fabcb4a66fb0ad854a0ae97','addForeignKeyConstraint baseTableName=TSQ_QUOTE_SUM, constraintName=FK_QUOTESUM_QUOTE, referencedTableName=TSQ_QUOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1469889916967-10','oyas (generated)','liquibase/migration/tekir-quote-4.0.0.xml','2018-02-09 00:02:06',202,'EXECUTED','7:ef0386c3aca637126de203a8842cc312','addColumn tableName=TSQ_QUOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478422974382-1','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:06',203,'EXECUTED','7:33307a34d9656753f2a0d600006c261d','createTable tableName=TAN_CREDIT_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478422974382-2','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',204,'EXECUTED','7:5dcefec316b27298af6ac2d7b7d44789','createTable tableName=TAN_DEBIT_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478422974382-3','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',205,'EXECUTED','7:58af5e0ec3f25b9305f1699109628b5b','createTable tableName=TAN_VIREMENT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478453353504-7','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',206,'EXECUTED','7:294d5c0f51f01a480f575375e33249ba','dropColumn tableName=TAN_CREDIT_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478453353504-8','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',207,'EXECUTED','7:cc9caaff660e255fd00c6d332e94e8c6','dropColumn tableName=TAN_DEBIT_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478453353504-9','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',208,'EXECUTED','7:7bdf94a2581d77dc42c1f7277a1d2580','dropColumn tableName=TAN_VIREMENT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478453353504-4','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',209,'EXECUTED','7:c073658c99052412a513d2308703798e','addColumn tableName=TAN_CREDIT_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478453353504-5','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',210,'EXECUTED','7:8b1cedfcedf62456df345e764e36b4e6','addColumn tableName=TAN_DEBIT_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478453353504-6','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',211,'EXECUTED','7:83e86b2872b6ce7dad78681a44fcbaf6','addColumn tableName=TAN_VIREMENT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478453353504-1','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',212,'EXECUTED','7:00561df2c463b43351b94dcfbd7ea603','addColumn tableName=TAN_CREDIT_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478453353504-2','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',213,'EXECUTED','7:de57723960723c7f35882c7280dd3e8a','addColumn tableName=TAN_DEBIT_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478453353504-3','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',214,'EXECUTED','7:038a2f3bc724049ccd1aaebd082a16a7','addColumn tableName=TAN_VIREMENT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478459562366-1','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',215,'EXECUTED','7:12710508a788bc7d747bfc83cd4d8ff9','addColumn tableName=TAN_CREDIT_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478459562366-2','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',216,'EXECUTED','7:305a7fe880da0a5b2d30e6cbcc6c284f','addColumn tableName=TAN_DEBIT_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478459562366-3','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',217,'EXECUTED','7:2ccfbe3dff65f41f043894dc0d724379','addColumn tableName=TAN_VIREMENT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478422974382-4','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',218,'EXECUTED','7:45af246b4e23580abdc7dcedc433b512','addForeignKeyConstraint baseTableName=TAN_DEBIT_NOTE, constraintName=FK_AN_ACC, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478422974382-5','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',219,'EXECUTED','7:ad73298e2599dc8b1e64f92c2a039d01','addForeignKeyConstraint baseTableName=TAN_CREDIT_NOTE, constraintName=FK_ACN_ACC, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478422974382-6','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',220,'EXECUTED','7:e7b5a2588d41ed0c7b4a4058e3decf4f','addForeignKeyConstraint baseTableName=TAN_VIREMENT, constraintName=FK_AV_FACC, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1478422974382-7','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',221,'EXECUTED','7:94432c123b4cbe87c62ad2e8d8ec3a88','addForeignKeyConstraint baseTableName=TAN_VIREMENT, constraintName=FK_AV_TACC, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480342891079-1','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:07',222,'EXECUTED','7:2759719d93e416718561275137f5a288','addColumn tableName=TAN_CREDIT_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480342891079-2','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:08',223,'EXECUTED','7:a0fbc0a007870ff2e0dd4f5fb8832881','addColumn tableName=TAN_DEBIT_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480342891079-3','oyas (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:08',224,'EXECUTED','7:9ca09fdfc61176e920001dd19c744aa4','addColumn tableName=TAN_VIREMENT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1497628531346-1','oktay (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:08',225,'EXECUTED','7:cf1e1a43b7d0775cf797d0dfee6065af','dropForeignKeyConstraint baseTableName=TAN_CREDIT_NOTE, constraintName=FK_ACN_ACC','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1497628531346-2','oktay (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:08',226,'EXECUTED','7:f634c56f0f5145a603712e463c6a2234','dropColumn columnName=PROCESS_ID, tableName=TAN_CREDIT_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1497628531346-3','oktay (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:08',227,'EXECUTED','7:254677e17416af2ab9781efe0624426f','dropColumn columnName=PROCESS_ID, tableName=TAN_DEBIT_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1497628531346-4','oktay (generated)','liquibase/migration/tekir-account-notes-4.0.0.xml','2018-02-09 00:02:08',228,'EXECUTED','7:89fc428392d30fc346e96e1c9bc604dc','dropColumn columnName=PROCESS_ID, tableName=TAN_VIREMENT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479229387723-1','oyas (generated)','liquibase/migration/tekir-order-4.0.0.xml','2018-02-09 00:02:08',229,'EXECUTED','7:3993f13a773824f6305768843cd98400','createTable tableName=TOR_ORDER','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479229387723-2','oyas (generated)','liquibase/migration/tekir-order-4.0.0.xml','2018-02-09 00:02:08',230,'EXECUTED','7:ceab49f85f534af6eadd261458f3f134','createTable tableName=TOR_ORDER_ITEM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479229387723-3','oyas (generated)','liquibase/migration/tekir-order-4.0.0.xml','2018-02-09 00:02:08',231,'EXECUTED','7:52e6d92ef81f6b679e4fbe21e054aa1a','createTable tableName=TOR_ORDER_SUM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479229387723-4','oyas (generated)','liquibase/migration/tekir-order-4.0.0.xml','2018-02-09 00:02:08',232,'EXECUTED','7:ec166ae82b598aaa16dd5eefca9c2e82','addForeignKeyConstraint baseTableName=TOR_ORDER, constraintName=FK_ORDER_ACC, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479311713256-1','oyas (generated)','liquibase/migration/tekir-order-4.0.0.xml','2018-02-09 00:02:08',233,'EXECUTED','7:44319eda3dd1097920be22dec8ac6a56','addColumn tableName=TOR_ORDER','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479311713256-2','oyas (generated)','liquibase/migration/tekir-order-4.0.0.xml','2018-02-09 00:02:08',234,'EXECUTED','7:4209f4f43dfdd7e0d7bb92e50852c7fa','addColumn tableName=TOR_ORDER','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479395708232-1','oyas (generated)','liquibase/migration/tekir-order-4.0.0.xml','2018-02-09 00:02:08',235,'EXECUTED','7:c70c9ce36aea5379e2c656a2f6ab4893','addColumn tableName=TOR_ORDER_ITEM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479395708232-2','oyas (generated)','liquibase/migration/tekir-order-4.0.0.xml','2018-02-09 00:02:08',236,'EXECUTED','7:271c59d6cf71f6ef031b92cfeed18d9b','addColumn tableName=TOR_ORDER_ITEM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479395708232-3','oyas (generated)','liquibase/migration/tekir-order-4.0.0.xml','2018-02-09 00:02:08',237,'EXECUTED','7:f52aec34997a28d9aef067bdd4d07015','addColumn tableName=TOR_ORDER_ITEM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479311713256-3','oyas (generated)','liquibase/migration/tekir-order-4.0.0.xml','2018-02-09 00:02:08',238,'EXECUTED','7:7bea66d50d24b91fca65dea074baf601','addForeignKeyConstraint baseTableName=TOR_ORDER, constraintName=FK_ORD_PP, referencedTableName=TCO_PAYMENT_PLAN','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480343096214-1','oyas (generated)','liquibase/migration/tekir-order-4.0.0.xml','2018-02-09 00:02:08',239,'EXECUTED','7:2dc518fc8adf9e1ee593a411f3bea35b','addColumn tableName=TOR_ORDER','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479725030340-1','oyas (generated)','liquibase/migration/tekir-invoice-4.0.0.xml','2018-02-09 00:02:08',240,'EXECUTED','7:580c0666ec9b1f049072b21483ceeaaf','createTable tableName=TIV_INVOICE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479725030340-2','oyas (generated)','liquibase/migration/tekir-invoice-4.0.0.xml','2018-02-09 00:02:08',241,'EXECUTED','7:df4495f7fad0a347413d8444b8577ad1','createTable tableName=TIV_INVOICE_ITEM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479725030340-3','oyas (generated)','liquibase/migration/tekir-invoice-4.0.0.xml','2018-02-09 00:02:09',242,'EXECUTED','7:6cb35658d66b28961dd7e18a69556ca4','createTable tableName=TIV_INVOICE_SUM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479725030340-4','oyas (generated)','liquibase/migration/tekir-invoice-4.0.0.xml','2018-02-09 00:02:09',243,'EXECUTED','7:ca1f1e288a855f85a13e96de38439d78','addForeignKeyConstraint baseTableName=TIV_INVOICE, constraintName=FK_INV_PP, referencedTableName=TCO_PAYMENT_PLAN','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479725030340-5','oyas (generated)','liquibase/migration/tekir-invoice-4.0.0.xml','2018-02-09 00:02:09',244,'EXECUTED','7:fb50540f50c5539a75fd91857e7c728c','addForeignKeyConstraint baseTableName=TIV_INVOICE, constraintName=FK_PROSS_ACC, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480343283864-1','oyas (generated)','liquibase/migration/tekir-invoice-4.0.0.xml','2018-02-09 00:02:09',245,'EXECUTED','7:8fdb199237e317100d6241beabe16740','addColumn tableName=TIV_INVOICE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1479811992600-1','oyas (generated)','liquibase/migration/tekir-payment-4.0.0.xml','2018-02-09 00:02:09',246,'EXECUTED','7:bc69565f582690f6faa74d439cf60fc0','createTable tableName=TPC_PAYMENT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1480340414802-1','oyas (generated)','liquibase/migration/tekir-payment-4.0.0.xml','2018-02-09 00:02:09',247,'EXECUTED','7:476d7755fcbc6ec405a7bf7693f0a191','addColumn tableName=TPC_PAYMENT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1493825259447-1','muhammedf (generated)','liquibase/migration/tekir-lead-4.0.0.xml','2018-02-09 00:02:09',248,'EXECUTED','7:200af6ff502bcd1fdf148ba1fa2f795d','createTable tableName=TLE_LEAD','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1493825259447-2','muhammedf (generated)','liquibase/migration/tekir-lead-4.0.0.xml','2018-02-09 00:02:09',249,'EXECUTED','7:9238177d1ce9a6714652502727d3ca3a','createTable tableName=TLE_LEAD_CATEGORY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1493825259447-3','muhammedf (generated)','liquibase/migration/tekir-lead-4.0.0.xml','2018-02-09 00:02:09',250,'EXECUTED','7:2b809b9957bacd78ba48fd6ea51449d3','createTable tableName=TLE_LEAD_SOURCE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1493825259447-4','muhammedf (generated)','liquibase/migration/tekir-lead-4.0.0.xml','2018-02-09 00:02:09',251,'EXECUTED','7:73851e5bde1e73dc6d60bf173cb6070e','addForeignKeyConstraint baseTableName=TLE_LEAD_CATEGORY, constraintName=FK_3oyngyytnhnnhf7cs3lipnnju, referencedTableName=TLE_LEAD_CATEGORY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1493825259447-5','muhammedf (generated)','liquibase/migration/tekir-lead-4.0.0.xml','2018-02-09 00:02:09',252,'EXECUTED','7:a311044162499a99c11c9927fce239e0','addForeignKeyConstraint baseTableName=TLE_LEAD, constraintName=FK_LEAD_INDUSTRY, referencedTableName=TCO_INDUSTRY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1493825259447-6','muhammedf (generated)','liquibase/migration/tekir-lead-4.0.0.xml','2018-02-09 00:02:09',253,'EXECUTED','7:a1bfdac621f68da303cd69c14eb055ad','addForeignKeyConstraint baseTableName=TLE_LEAD, constraintName=FK_LEAD_LECATEGORY, referencedTableName=TLE_LEAD_CATEGORY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1493825259447-7','muhammedf (generated)','liquibase/migration/tekir-lead-4.0.0.xml','2018-02-09 00:02:09',254,'EXECUTED','7:0dcb3662470309fe8884b2299b1c7d89','addForeignKeyConstraint baseTableName=TLE_LEAD, constraintName=FK_LEAD_LESOURCE, referencedTableName=TLE_LEAD_SOURCE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1493825259447-8','muhammedf (generated)','liquibase/migration/tekir-lead-4.0.0.xml','2018-02-09 00:02:09',255,'EXECUTED','7:ebc4ef271d04c458ef9272c4f1c11664','addForeignKeyConstraint baseTableName=TLE_LEAD, constraintName=FK_LEAD_LOC, referencedTableName=TCO_LOCATION','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1493825259447-9','muhammedf (generated)','liquibase/migration/tekir-lead-4.0.0.xml','2018-02-09 00:02:09',256,'EXECUTED','7:41fa4cdde74e9cd94f0bf76bee40faee','addForeignKeyConstraint baseTableName=TLE_LEAD, constraintName=FK_LEAD_TER, referencedTableName=TCO_TERRITORY','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1493825259447-10','muhammedf (generated)','liquibase/migration/tekir-lead-4.0.0.xml','2018-02-09 00:02:10',257,'EXECUTED','7:716f4e1371e936c3d3478c35bd6274a2','addForeignKeyConstraint baseTableName=TLE_LEAD, constraintName=FK_VOG_VOG, referencedTableName=TVO_GROUP','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1493825259447-11','muhammedf (generated)','liquibase/migration/tekir-lead-4.0.0.xml','2018-02-09 00:02:10',258,'EXECUTED','7:23dede40e91d7e9a9725f9826524ba0b','addForeignKeyConstraint baseTableName=TLE_LEAD_SOURCE, constraintName=FK_rnmj1udkxb64cucjs9aknup6n, referencedTableName=TLE_LEAD_SOURCE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('201706161419-1','hakan.uygun','liquibase/migration/tekir-hr-4.0.0.xml','2018-02-09 00:02:10',259,'EXECUTED','7:e83fd27eefcf498649f37c065fc2c4f8','addColumn tableName=TCC_CONTACT; addColumn tableName=TCC_CONTACT; addColumn tableName=TCC_CONTACT; addColumn tableName=TCC_CONTACT; addColumn tableName=TCC_CONTACT; addColumn tableName=TCC_CONTACT; addColumn tableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1497632129981-1','oyas (generated)','liquibase/migration/tekir-hr-4.0.0.xml','2018-02-09 00:02:10',260,'EXECUTED','7:04be74bc532f1ed42a93696fb14f0881','createTable tableName=THR_LEAVE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1497632129981-2','oyas (generated)','liquibase/migration/tekir-hr-4.0.0.xml','2018-02-09 00:02:10',261,'EXECUTED','7:249359c1ff96e5d907f5e13e342e9a20','addForeignKeyConstraint baseTableName=THR_LEAVE, constraintName=FK_LEAVE_EMP, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1497633481705-1','oyas (generated)','liquibase/migration/tekir-hr-4.0.0.xml','2018-02-09 00:02:10',262,'EXECUTED','7:6a285c109a9c1b9e21c98debce835cb0','createTable tableName=THR_SALARY_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1497633481705-2','oyas (generated)','liquibase/migration/tekir-hr-4.0.0.xml','2018-02-09 00:02:10',263,'EXECUTED','7:e4228e29d0493346cf0f9a1aa50e83bf','createTable tableName=THR_SALARY_NOTE_ITEM','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1497633481705-3','oyas (generated)','liquibase/migration/tekir-hr-4.0.0.xml','2018-02-09 00:02:11',264,'EXECUTED','7:75c025385457357a39899b4dc3a831c5','addForeignKeyConstraint baseTableName=THR_SALARY_NOTE, constraintName=FK_SALARY_ACC, referencedTableName=TFN_FINANCE_ACC','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1497633481705-4','oyas (generated)','liquibase/migration/tekir-hr-4.0.0.xml','2018-02-09 00:02:11',265,'EXECUTED','7:9ee40df01cf16dbb8baa5284b666957f','addForeignKeyConstraint baseTableName=THR_SALARY_NOTE_ITEM, constraintName=FK_SALARY_EMP, referencedTableName=TCC_CONTACT','',NULL,'3.5.3',NULL,NULL,'8123712603'),('1497633481705-5','oyas (generated)','liquibase/migration/tekir-hr-4.0.0.xml','2018-02-09 00:02:11',266,'EXECUTED','7:b65796ff6677869e9fcc739015db936e','addForeignKeyConstraint baseTableName=THR_SALARY_NOTE_ITEM, constraintName=FK_SALIT_SAL, referencedTableName=THR_SALARY_NOTE','',NULL,'3.5.3',NULL,NULL,'8123712603');
/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOGLOCK`
--

LOCK TABLES `DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GENERIC_SEQ`
--

DROP TABLE IF EXISTS `GENERIC_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GENERIC_SEQ` (
  `next_val` bigint(20) NOT NULL,
  PRIMARY KEY (`next_val`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GENERIC_SEQ`
--

LOCK TABLES `GENERIC_SEQ` WRITE;
/*!40000 ALTER TABLE `GENERIC_SEQ` DISABLE KEYS */;
INSERT INTO `GENERIC_SEQ` VALUES (2551);
/*!40000 ALTER TABLE `GENERIC_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KAHVE`
--

DROP TABLE IF EXISTS `KAHVE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KAHVE` (
  `KV_KEY` varchar(255) NOT NULL,
  `KV_VAL` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`KV_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KAHVE`
--

LOCK TABLES `KAHVE` WRITE;
/*!40000 ALTER TABLE `KAHVE` DISABLE KEYS */;
INSERT INTO `KAHVE` VALUES ('corp.invAddr','Papatya Caddesi Yasemin Sokak  No:21'),('corp.invCountry','Turkiye'),('corp.invCounty','Besiktas'),('corp.invProvince','Istanbul'),('corp.invZipCode','34100'),('corp.name','AAA Anonim Sirketi'),('corp.taxNumber','6930329621'),('corp.taxOffice','Buyuk Mukellefler'),('notify.count.telve','0'),('SEQUENCE.COM','12'),('SEQUENCE.COR','1'),('SEQUENCE.PAY','5'),('SEQUENCE.PS','4'),('SEQUENCE.SAL','4'),('SEQUENCE.TAX','5'),('SEQUENCE.TER','1'),('SEQUENCE.UNI','2'),('tekir.currency.default','TRY'),('tekir.currency.exchangeMap','USD/TRY,EUR/TRY'),('tekir.currency.useable','TRY,USD,EUR'),('telve::dashboard.tab.0.col1','firstRunDashlet'),('telve::dashboard.tab.0.col2',''),('telve::dashboard.tab.0.col3',''),('telve::dashboard.tab.0.layout','0'),('telve::dashboard.tab.0.name','Ana Sayfa'),('telve::dashboard.tab.count','1'),('telve::favmenu.count','0'),('voucher.serial.SalesInvoiceFeature','SAL');
/*!40000 ALTER TABLE `KAHVE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TAN_CREDIT_NOTE`
--

DROP TABLE IF EXISTS `TAN_CREDIT_NOTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TAN_CREDIT_NOTE` (
  `ID` bigint(20) NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `CODE` varchar(30) DEFAULT NULL,
  `TXNDATE` datetime DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  `REFERENCE_NO` varchar(30) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `STATE_INFO` varchar(255) DEFAULT NULL,
  `STATE_REASON` varchar(255) DEFAULT NULL,
  `VOUCHER_NO` varchar(30) NOT NULL,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `CCY` varchar(255) DEFAULT NULL,
  `ACCOUNT_ID` bigint(20) DEFAULT NULL,
  `GROUP_ID` bigint(20) DEFAULT NULL,
  `TOPIC` varchar(255) DEFAULT NULL,
  `LOCAL_AMOUNT` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ACN_ACC` (`ACCOUNT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TAN_CREDIT_NOTE`
--

LOCK TABLES `TAN_CREDIT_NOTE` WRITE;
/*!40000 ALTER TABLE `TAN_CREDIT_NOTE` DISABLE KEYS */;
/*!40000 ALTER TABLE `TAN_CREDIT_NOTE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TAN_DEBIT_NOTE`
--

DROP TABLE IF EXISTS `TAN_DEBIT_NOTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TAN_DEBIT_NOTE` (
  `ID` bigint(20) NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `CODE` varchar(30) DEFAULT NULL,
  `TXNDATE` datetime DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  `REFERENCE_NO` varchar(30) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `STATE_INFO` varchar(255) DEFAULT NULL,
  `STATE_REASON` varchar(255) DEFAULT NULL,
  `VOUCHER_NO` varchar(30) NOT NULL,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `CCY` varchar(255) DEFAULT NULL,
  `ACCOUNT_ID` bigint(20) DEFAULT NULL,
  `GROUP_ID` bigint(20) DEFAULT NULL,
  `TOPIC` varchar(255) DEFAULT NULL,
  `LOCAL_AMOUNT` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_AN_ACC` (`ACCOUNT_ID`),
  CONSTRAINT `FK_AN_ACC` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `TCC_CONTACT` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TAN_DEBIT_NOTE`
--

LOCK TABLES `TAN_DEBIT_NOTE` WRITE;
/*!40000 ALTER TABLE `TAN_DEBIT_NOTE` DISABLE KEYS */;
/*!40000 ALTER TABLE `TAN_DEBIT_NOTE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TAN_VIREMENT`
--

DROP TABLE IF EXISTS `TAN_VIREMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TAN_VIREMENT` (
  `ID` bigint(20) NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `CODE` varchar(30) DEFAULT NULL,
  `TXNDATE` datetime DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  `REFERENCE_NO` varchar(30) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `STATE_INFO` varchar(255) DEFAULT NULL,
  `STATE_REASON` varchar(255) DEFAULT NULL,
  `VOUCHER_NO` varchar(30) NOT NULL,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `CCY` varchar(255) DEFAULT NULL,
  `FROM_ACCOUNT_ID` bigint(20) DEFAULT NULL,
  `TO_ACCOUNT_ID` bigint(20) DEFAULT NULL,
  `GROUP_ID` bigint(20) DEFAULT NULL,
  `TOPIC` varchar(255) DEFAULT NULL,
  `LOCAL_AMOUNT` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_AV_FACC` (`FROM_ACCOUNT_ID`),
  KEY `FK_AV_TACC` (`TO_ACCOUNT_ID`),
  CONSTRAINT `FK_AV_FACC` FOREIGN KEY (`FROM_ACCOUNT_ID`) REFERENCES `TCC_CONTACT` (`ID`),
  CONSTRAINT `FK_AV_TACC` FOREIGN KEY (`TO_ACCOUNT_ID`) REFERENCES `TCC_CONTACT` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TAN_VIREMENT`
--

LOCK TABLES `TAN_VIREMENT` WRITE;
/*!40000 ALTER TABLE `TAN_VIREMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `TAN_VIREMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCA_ACTIVITY`
--

DROP TABLE IF EXISTS `TCA_ACTIVITY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCA_ACTIVITY` (
  `ACTIVITY_TYPE` varchar(31) NOT NULL,
  `ID` bigint(20) NOT NULL,
  `ASSIGNEE` varchar(255) DEFAULT NULL,
  `BODY` text,
  `DUE_DATE` datetime DEFAULT NULL,
  `ACT_DATE` datetime DEFAULT NULL,
  `DIRECTION` varchar(255) DEFAULT NULL,
  `DURATION` bigint(20) DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `RESULT_NOTE` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `STATUS_REASON` varchar(255) DEFAULT NULL,
  `SUBJECT` varchar(255) DEFAULT NULL,
  `NUMBER` varchar(255) DEFAULT NULL,
  `ATTENDEES` varchar(255) DEFAULT NULL,
  `LOCATION` varchar(255) DEFAULT NULL,
  `MEETING_MINUTES` varchar(255) DEFAULT NULL,
  `CORPORATION_ID` bigint(20) DEFAULT NULL,
  `FOLLOWUP_ID` bigint(20) DEFAULT NULL,
  `PERSON_ID` bigint(20) DEFAULT NULL,
  `ADDR_ID` bigint(20) DEFAULT NULL,
  `ACT_NO` varchar(20) DEFAULT NULL,
  `EM_BCC` varchar(255) DEFAULT NULL,
  `EM_CC` varchar(2000) DEFAULT NULL,
  `EM_FID` varchar(255) DEFAULT NULL,
  `EM_FROM` varchar(255) DEFAULT NULL,
  `EM_MID` varchar(255) DEFAULT NULL,
  `EM_RID` varchar(255) DEFAULT NULL,
  `EM_TO` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ACC_ADDR` (`ADDR_ID`),
  KEY `FK_ACC_COR` (`CORPORATION_ID`),
  KEY `FK_ACC_FOLL` (`FOLLOWUP_ID`),
  KEY `FK_ACC_PER` (`PERSON_ID`),
  CONSTRAINT `FK_ACC_ADDR` FOREIGN KEY (`ADDR_ID`) REFERENCES `TCC_CONTACT_INFO` (`ID`),
  CONSTRAINT `FK_ACC_COR` FOREIGN KEY (`CORPORATION_ID`) REFERENCES `TCC_CONTACT` (`ID`),
  CONSTRAINT `FK_ACC_FOLL` FOREIGN KEY (`FOLLOWUP_ID`) REFERENCES `TCA_ACTIVITY` (`ID`),
  CONSTRAINT `FK_ACC_PER` FOREIGN KEY (`PERSON_ID`) REFERENCES `TCC_CONTACT` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCA_ACTIVITY`
--

LOCK TABLES `TCA_ACTIVITY` WRITE;
/*!40000 ALTER TABLE `TCA_ACTIVITY` DISABLE KEYS */;
/*!40000 ALTER TABLE `TCA_ACTIVITY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCA_ACT_MENTION`
--

DROP TABLE IF EXISTS `TCA_ACT_MENTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCA_ACT_MENTION` (
  `ID` bigint(20) NOT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `ACTIVITY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ACTIVITY_FEED` (`ACTIVITY_ID`),
  CONSTRAINT `FK_ACTIVITY_FEED` FOREIGN KEY (`ACTIVITY_ID`) REFERENCES `TCA_ACTIVITY` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCA_ACT_MENTION`
--

LOCK TABLES `TCA_ACT_MENTION` WRITE;
/*!40000 ALTER TABLE `TCA_ACT_MENTION` DISABLE KEYS */;
/*!40000 ALTER TABLE `TCA_ACT_MENTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCC_ACCOUNT_TXN`
--

DROP TABLE IF EXISTS `TCC_ACCOUNT_TXN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCC_ACCOUNT_TXN` (
  `ID` bigint(20) NOT NULL,
  `CODE` varchar(30) DEFAULT NULL,
  `TXNDATE` datetime DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  `PROCESS_ID` varchar(255) DEFAULT NULL,
  `REFERENCE_NO` varchar(30) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `STATUS_REASON` varchar(255) DEFAULT NULL,
  `ACCOUNT_ID` bigint(20) DEFAULT NULL,
  `ACCOUNTABLE` bit(1) DEFAULT NULL,
  `DEBIT` bit(1) DEFAULT NULL,
  `CCY` varchar(3) DEFAULT NULL,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `LOCAL_AMOUNT` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCC_ACCOUNT_TXN`
--

LOCK TABLES `TCC_ACCOUNT_TXN` WRITE;
/*!40000 ALTER TABLE `TCC_ACCOUNT_TXN` DISABLE KEYS */;
INSERT INTO `TCC_ACCOUNT_TXN` VALUES (802,'','2018-02-09 00:00:00','','telve','PS-000001',NULL,'SAL-000001','SalesInvoiceFeature',652,'DRAFT-NEUTRAL-DRAFT',NULL,452,'\0','\0','TRY',90.00,90.00),(803,'','2018-02-09 00:00:00','','telve','PS-000002',NULL,'SAL-000002','SalesInvoiceFeature',653,'DRAFT-NEUTRAL-DRAFT',NULL,452,'\0','\0','TRY',118.80,118.80),(1352,'','2018-02-13 00:00:00','','telve','PS-000003',NULL,'SAL-000003','SalesInvoiceFeature',1202,'DRAFT-NEUTRAL-DRAFT',NULL,452,'\0','\0','TRY',118.80,118.80),(2052,'','2018-02-13 00:00:00','','telve','PS-000004',NULL,'SAL-000004','SalesInvoiceFeature',1902,'DRAFT-NEUTRAL-DRAFT',NULL,452,'\0','\0','TRY',29755.47,29755.47);
/*!40000 ALTER TABLE `TCC_ACCOUNT_TXN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCC_CONTACT`
--

DROP TABLE IF EXISTS `TCC_CONTACT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCC_CONTACT` (
  `CONTACT_TYPE` varchar(31) NOT NULL,
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `CHANNELS` varchar(255) DEFAULT NULL,
  `ROLES` varchar(255) DEFAULT NULL,
  `CODE` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `OWNER_ORG` varchar(255) DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  `SOURCE` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `STATUS_REASON` varchar(255) DEFAULT NULL,
  `OWNER_TEAM` varchar(255) DEFAULT NULL,
  `ORG_NAME` varchar(255) DEFAULT NULL,
  `COMPANY_TITLE` varchar(255) DEFAULT NULL,
  `FIRST_NAME` varchar(255) DEFAULT NULL,
  `GENDER` char(1) DEFAULT NULL,
  `JOB_TITLE` varchar(255) DEFAULT NULL,
  `LAST_NAME` varchar(255) DEFAULT NULL,
  `SECOND_NAME` varchar(255) DEFAULT NULL,
  `SSN` varchar(255) DEFAULT NULL,
  `USE_NAME` varchar(255) DEFAULT NULL,
  `CAT_ID` bigint(20) DEFAULT NULL,
  `INDUSTRY_ID` bigint(20) DEFAULT NULL,
  `ADDR_ID` bigint(20) DEFAULT NULL,
  `EMAIL_ID` bigint(20) DEFAULT NULL,
  `FAX_ID` bigint(20) DEFAULT NULL,
  `MOBILE_ID` bigint(20) DEFAULT NULL,
  `PHONE_ID` bigint(20) DEFAULT NULL,
  `TERRITORY_ID` bigint(20) DEFAULT NULL,
  `TYPE_ID` bigint(20) DEFAULT NULL,
  `PARENT_ID` bigint(20) DEFAULT NULL,
  `CONTACT_ID` bigint(20) DEFAULT NULL,
  `CORP_ID` bigint(20) DEFAULT NULL,
  `BANK_ACCOUNT` varchar(255) DEFAULT NULL,
  `CCY` varchar(255) DEFAULT NULL,
  `TAX_NUMBER` varchar(255) DEFAULT NULL,
  `TAX_OFFICE` varchar(255) DEFAULT NULL,
  `SOURCE_FP` varchar(255) DEFAULT NULL,
  `SOURCE_BK` varchar(255) DEFAULT NULL,
  `SOURCE_PK` bigint(20) DEFAULT NULL,
  `BANK_ID` bigint(20) DEFAULT NULL,
  `VAT_WITHHOLDING` bit(1) DEFAULT NULL,
  `EMPLOYEE_NO` varchar(255) DEFAULT NULL,
  `SGK_NO` varchar(255) DEFAULT NULL,
  `RECRUITMENT_DT` date DEFAULT NULL,
  `TERMINATION_DT` date DEFAULT NULL,
  `LEAVE_DAY` int(11) DEFAULT NULL,
  `SALARY_AMOUNT` decimal(19,2) DEFAULT NULL,
  `SALARY_CCY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CON_ADDR` (`ADDR_ID`),
  KEY `FK_CON_CAT` (`CAT_ID`),
  KEY `FK_CON_EMAIL` (`EMAIL_ID`),
  KEY `FK_CON_FAX` (`FAX_ID`),
  KEY `FK_CON_INDUSTRY` (`INDUSTRY_ID`),
  KEY `FK_CON_MOBILE` (`MOBILE_ID`),
  KEY `FK_CON_PHONE` (`PHONE_ID`),
  KEY `FK_CON_TER` (`TERRITORY_ID`),
  KEY `FK_CORP_PARENT` (`PARENT_ID`),
  KEY `FK_CORP_PER` (`CONTACT_ID`),
  KEY `FK_CORP_TYPE` (`TYPE_ID`),
  KEY `FK_PER_CORP` (`CORP_ID`),
  CONSTRAINT `FK_CON_ADDR` FOREIGN KEY (`ADDR_ID`) REFERENCES `TCC_CONTACT_INFO` (`ID`),
  CONSTRAINT `FK_CON_CAT` FOREIGN KEY (`CAT_ID`) REFERENCES `TCC_CONTACT_CAT` (`ID`),
  CONSTRAINT `FK_CON_EMAIL` FOREIGN KEY (`EMAIL_ID`) REFERENCES `TCC_CONTACT_INFO` (`ID`),
  CONSTRAINT `FK_CON_FAX` FOREIGN KEY (`FAX_ID`) REFERENCES `TCC_CONTACT_INFO` (`ID`),
  CONSTRAINT `FK_CON_INDUSTRY` FOREIGN KEY (`INDUSTRY_ID`) REFERENCES `TCO_INDUSTRY` (`ID`),
  CONSTRAINT `FK_CON_MOBILE` FOREIGN KEY (`MOBILE_ID`) REFERENCES `TCC_CONTACT_INFO` (`ID`),
  CONSTRAINT `FK_CON_PHONE` FOREIGN KEY (`PHONE_ID`) REFERENCES `TCC_CONTACT_INFO` (`ID`),
  CONSTRAINT `FK_CON_TER` FOREIGN KEY (`TERRITORY_ID`) REFERENCES `TCO_TERRITORY` (`ID`),
  CONSTRAINT `FK_CORP_PARENT` FOREIGN KEY (`PARENT_ID`) REFERENCES `TCC_CONTACT` (`ID`),
  CONSTRAINT `FK_CORP_PER` FOREIGN KEY (`CONTACT_ID`) REFERENCES `TCC_CONTACT` (`ID`),
  CONSTRAINT `FK_CORP_TYPE` FOREIGN KEY (`TYPE_ID`) REFERENCES `TCC_CORP_TYPE` (`ID`),
  CONSTRAINT `FK_PER_CORP` FOREIGN KEY (`CORP_ID`) REFERENCES `TCC_CONTACT` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=453 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCC_CONTACT`
--

LOCK TABLES `TCC_CONTACT` WRITE;
/*!40000 ALTER TABLE `TCC_CONTACT` DISABLE KEYS */;
INSERT INTO `TCC_CONTACT` VALUES ('CORPORATION',452,'2018-02-09 01:21:56','2018-02-13 02:18:43','telve','','','CONTACT,CORPORATION,ACCOUNT,CUSTOMER','COR-000001','BBB Limited Sirketi','',NULL,'telve',NULL,NULL,NULL,NULL,'BBB Limited Sirketi',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,502,NULL,NULL,NULL,1552,NULL,NULL,NULL,NULL,NULL,'','TRY','6930329622','Cankaya',NULL,NULL,NULL,NULL,'\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `TCC_CONTACT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCC_CONTACT_CAT`
--

DROP TABLE IF EXISTS `TCC_CONTACT_CAT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCC_CONTACT_CAT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `CODE` varchar(255) NOT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `PATH` varchar(255) DEFAULT NULL,
  `PID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_sjkf2ep6fcrq182sdjar52ttb` (`PID`),
  CONSTRAINT `FK_sjkf2ep6fcrq182sdjar52ttb` FOREIGN KEY (`PID`) REFERENCES `TCC_CONTACT_CAT` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCC_CONTACT_CAT`
--

LOCK TABLES `TCC_CONTACT_CAT` WRITE;
/*!40000 ALTER TABLE `TCC_CONTACT_CAT` DISABLE KEYS */;
/*!40000 ALTER TABLE `TCC_CONTACT_CAT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCC_CONTACT_INFO`
--

DROP TABLE IF EXISTS `TCC_CONTACT_INFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCC_CONTACT_INFO` (
  `INFO_TYPE` varchar(31) NOT NULL,
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `ROLES` varchar(255) DEFAULT NULL,
  `SUBTYPES` varchar(255) DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NETWORK` varchar(255) DEFAULT NULL,
  `LAT` double DEFAULT NULL,
  `LON` double DEFAULT NULL,
  `ZIP` varchar(255) DEFAULT NULL,
  `COUNTY` varchar(255) DEFAULT NULL,
  `PROVINCE` varchar(255) DEFAULT NULL,
  `COUNTRY` varchar(255) DEFAULT NULL,
  `CONTACT_ID` bigint(20) DEFAULT NULL,
  `LOCATION_ID` bigint(20) DEFAULT NULL,
  `BANK` varchar(255) DEFAULT NULL,
  `IBAN` varchar(255) DEFAULT NULL,
  `CCY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CONADDR_LOC` (`LOCATION_ID`),
  KEY `FK_CONINF_CONTACT` (`CONTACT_ID`),
  CONSTRAINT `FK_CONADDR_LOC` FOREIGN KEY (`LOCATION_ID`) REFERENCES `TCO_LOCATION` (`ID`),
  CONSTRAINT `FK_CONINF_CONTACT` FOREIGN KEY (`CONTACT_ID`) REFERENCES `TCC_CONTACT` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1553 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCC_CONTACT_INFO`
--

LOCK TABLES `TCC_CONTACT_INFO` WRITE;
/*!40000 ALTER TABLE `TCC_CONTACT_INFO` DISABLE KEYS */;
INSERT INTO `TCC_CONTACT_INFO` VALUES ('ADR',502,'2018-02-09 01:21:56','2018-02-13 02:10:40','telve','Ihlamur Mahallesi Selvi Caddesi Sedir Sokak 75/A','PRIMARY','','',NULL,NULL,NULL,'06100','Kizilay','Ankara','Turkiye',452,NULL,NULL,NULL,NULL),('PHONE',1552,'2018-02-13 02:17:55','2018-02-13 02:18:29','telve','','PRIMARY','LAND','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,452,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `TCC_CONTACT_INFO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCC_CONTACT_REL`
--

DROP TABLE IF EXISTS `TCC_CONTACT_REL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCC_CONTACT_REL` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `INFO` varchar(255) DEFAULT NULL,
  `RELEATION_ID` bigint(20) DEFAULT NULL,
  `SOURCE_ID` bigint(20) DEFAULT NULL,
  `TARGET_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CONREL_REL` (`RELEATION_ID`),
  KEY `FK_CONREL_SOURCE` (`SOURCE_ID`),
  KEY `FK_CONREL_TARGET` (`TARGET_ID`),
  CONSTRAINT `FK_CONREL_REL` FOREIGN KEY (`RELEATION_ID`) REFERENCES `TCC_CONTACT_REL_DEF` (`ID`),
  CONSTRAINT `FK_CONREL_SOURCE` FOREIGN KEY (`SOURCE_ID`) REFERENCES `TCC_CONTACT` (`ID`),
  CONSTRAINT `FK_CONREL_TARGET` FOREIGN KEY (`TARGET_ID`) REFERENCES `TCC_CONTACT` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCC_CONTACT_REL`
--

LOCK TABLES `TCC_CONTACT_REL` WRITE;
/*!40000 ALTER TABLE `TCC_CONTACT_REL` DISABLE KEYS */;
/*!40000 ALTER TABLE `TCC_CONTACT_REL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCC_CONTACT_REL_DEF`
--

DROP TABLE IF EXISTS `TCC_CONTACT_REL_DEF`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCC_CONTACT_REL_DEF` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `CODE` varchar(30) NOT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `SOURCE_ROLES` varchar(255) DEFAULT NULL,
  `TARGET_ROLES` varchar(255) DEFAULT NULL,
  `WEIGHT` int(11) DEFAULT NULL,
  `reversName` varchar(255) DEFAULT NULL,
  `vectorName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCC_CONTACT_REL_DEF`
--

LOCK TABLES `TCC_CONTACT_REL_DEF` WRITE;
/*!40000 ALTER TABLE `TCC_CONTACT_REL_DEF` DISABLE KEYS */;
/*!40000 ALTER TABLE `TCC_CONTACT_REL_DEF` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCC_CORP_TYPE`
--

DROP TABLE IF EXISTS `TCC_CORP_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCC_CORP_TYPE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `CODE` varchar(30) NOT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCC_CORP_TYPE`
--

LOCK TABLES `TCC_CORP_TYPE` WRITE;
/*!40000 ALTER TABLE `TCC_CORP_TYPE` DISABLE KEYS */;
/*!40000 ALTER TABLE `TCC_CORP_TYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCO_BANK_CASH_ACC`
--

DROP TABLE IF EXISTS `TCO_BANK_CASH_ACC`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCO_BANK_CASH_ACC` (
  `ID` bigint(20) NOT NULL,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `CODE` varchar(30) NOT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `ACCOUNT_NO` varchar(255) DEFAULT NULL,
  `BANK` varchar(255) DEFAULT NULL,
  `BRANCH` varchar(255) DEFAULT NULL,
  `CCY` varchar(255) DEFAULT NULL,
  `IBAN` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCO_BANK_CASH_ACC`
--

LOCK TABLES `TCO_BANK_CASH_ACC` WRITE;
/*!40000 ALTER TABLE `TCO_BANK_CASH_ACC` DISABLE KEYS */;
/*!40000 ALTER TABLE `TCO_BANK_CASH_ACC` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCO_COMMOTITY`
--

DROP TABLE IF EXISTS `TCO_COMMOTITY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCO_COMMOTITY` (
  `ID` bigint(20) NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `CODE` varchar(255) DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `CATEGORY_ID` bigint(20) DEFAULT NULL,
  `CURRENCY` varchar(255) DEFAULT NULL,
  `PRICE` decimal(19,2) DEFAULT NULL,
  `TAX1_ID` bigint(20) DEFAULT NULL,
  `TAX2_ID` bigint(20) DEFAULT NULL,
  `TAX3_ID` bigint(20) DEFAULT NULL,
  `UNIT_DEFAULT` varchar(255) DEFAULT NULL,
  `UNIT_SET` varchar(255) DEFAULT NULL,
  `UNITSET_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_COMM_CAT` (`CATEGORY_ID`),
  KEY `FK_COMM_TAX1` (`TAX1_ID`),
  KEY `FK_COMM_TAX2` (`TAX2_ID`),
  KEY `FK_COMM_TAX3` (`TAX3_ID`),
  KEY `FK_COMM_UNITSET` (`UNITSET_ID`),
  CONSTRAINT `FK_COMM_CAT` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `TCO_COMMOTITY_CAT` (`ID`),
  CONSTRAINT `FK_COMM_TAX1` FOREIGN KEY (`TAX1_ID`) REFERENCES `TCO_TAX` (`ID`),
  CONSTRAINT `FK_COMM_TAX2` FOREIGN KEY (`TAX2_ID`) REFERENCES `TCO_TAX` (`ID`),
  CONSTRAINT `FK_COMM_TAX3` FOREIGN KEY (`TAX3_ID`) REFERENCES `TCO_TAX` (`ID`),
  CONSTRAINT `FK_COMM_UNITSET` FOREIGN KEY (`UNITSET_ID`) REFERENCES `TCO_UNIT_SET` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCO_COMMOTITY`
--

LOCK TABLES `TCO_COMMOTITY` WRITE;
/*!40000 ALTER TABLE `TCO_COMMOTITY` DISABLE KEYS */;
INSERT INTO `TCO_COMMOTITY` VALUES (402,'2018-02-09 01:21:11','2018-02-09 01:24:24','telve','','COM-000001','','urun1',NULL,'TRY',3232.00,252,253,NULL,'UNI-01:adet2',NULL,302),(1702,'2018-02-13 02:47:05','2018-02-13 02:47:05','telve','','COM-000002','','Masa Üstü Bilgisayar',NULL,'TRY',825.50,252,NULL,NULL,'UNI-01:adet2',NULL,302),(1703,'2018-02-13 02:47:37','2018-02-13 02:47:37','telve','','COM-000003','','Notebook Bilgisayar',NULL,'TRY',1215.00,252,NULL,NULL,'UNI-01:adet2',NULL,302),(1812,'2018-02-13 02:56:30','2018-02-13 02:56:30','telve','','COM-000007','','Notebook Çantasi',NULL,'TRY',38.25,252,NULL,NULL,'UNI-01:adet2',NULL,302),(1813,'2018-02-13 02:57:08','2018-02-13 02:57:08','telve','','COM-000008','','Yazici',NULL,'TRY',121.30,252,NULL,NULL,'UNI-01:adet2',NULL,302),(1814,'2018-02-13 02:57:44','2018-02-13 02:57:44','telve','','COM-000009','','CD-R 2*56',NULL,'TRY',7.00,252,NULL,NULL,'UNI-02:kutu',NULL,1602),(1815,'2018-02-13 02:58:01','2018-02-13 02:58:01','telve','','COM-000010','','DVD-R',NULL,'TRY',11.00,252,NULL,NULL,'UNI-02:kutu',NULL,1602),(1816,'2018-02-13 02:58:22','2018-02-13 02:58:22','telve','','COM-000011','','Dolma Kalem',NULL,'TRY',4.25,252,NULL,NULL,'UNI-01:adet2',NULL,302),(1817,'2018-02-13 02:58:51','2018-02-13 03:12:02','telve','','COM-000012','','Tükenmez Kalem',NULL,'TRY',2.15,252,NULL,NULL,'UNI-01:adet2',NULL,302);
/*!40000 ALTER TABLE `TCO_COMMOTITY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCO_COMMOTITY_CAT`
--

DROP TABLE IF EXISTS `TCO_COMMOTITY_CAT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCO_COMMOTITY_CAT` (
  `ID` bigint(20) NOT NULL,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `CODE` varchar(255) NOT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `PATH` varchar(255) DEFAULT NULL,
  `PID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_6xdebpq4d9ldt8e4fwyhd0bls` (`PID`),
  CONSTRAINT `FK_6xdebpq4d9ldt8e4fwyhd0bls` FOREIGN KEY (`PID`) REFERENCES `TCO_COMMOTITY_CAT` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCO_COMMOTITY_CAT`
--

LOCK TABLES `TCO_COMMOTITY_CAT` WRITE;
/*!40000 ALTER TABLE `TCO_COMMOTITY_CAT` DISABLE KEYS */;
/*!40000 ALTER TABLE `TCO_COMMOTITY_CAT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCO_EXCHANGE_RATE`
--

DROP TABLE IF EXISTS `TCO_EXCHANGE_RATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCO_EXCHANGE_RATE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE` date DEFAULT NULL,
  `BASE_CCY` varchar(255) DEFAULT NULL,
  `TERM_CCY` varchar(255) DEFAULT NULL,
  `BUY_RATE` decimal(12,5) DEFAULT NULL,
  `SELL_RATE` decimal(12,5) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCO_EXCHANGE_RATE`
--

LOCK TABLES `TCO_EXCHANGE_RATE` WRITE;
/*!40000 ALTER TABLE `TCO_EXCHANGE_RATE` DISABLE KEYS */;
/*!40000 ALTER TABLE `TCO_EXCHANGE_RATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCO_INDUSTRY`
--

DROP TABLE IF EXISTS `TCO_INDUSTRY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCO_INDUSTRY` (
  `ID` bigint(20) NOT NULL,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `CODE` varchar(255) NOT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `PATH` varchar(255) DEFAULT NULL,
  `PID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_dvnd489u7yfnsbxn5uqna9hyj` (`PID`),
  CONSTRAINT `FK_dvnd489u7yfnsbxn5uqna9hyj` FOREIGN KEY (`PID`) REFERENCES `TCO_INDUSTRY` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCO_INDUSTRY`
--

LOCK TABLES `TCO_INDUSTRY` WRITE;
/*!40000 ALTER TABLE `TCO_INDUSTRY` DISABLE KEYS */;
/*!40000 ALTER TABLE `TCO_INDUSTRY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCO_LOCATION`
--

DROP TABLE IF EXISTS `TCO_LOCATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCO_LOCATION` (
  `ID` bigint(20) NOT NULL,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `CODE` varchar(255) NOT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `PATH` varchar(255) DEFAULT NULL,
  `LAT` double DEFAULT NULL,
  `LON` double DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `PID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_lbo9phf3g4punwc3afdq1di4f` (`PID`),
  CONSTRAINT `FK_lbo9phf3g4punwc3afdq1di4f` FOREIGN KEY (`PID`) REFERENCES `TCO_LOCATION` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCO_LOCATION`
--

LOCK TABLES `TCO_LOCATION` WRITE;
/*!40000 ALTER TABLE `TCO_LOCATION` DISABLE KEYS */;
/*!40000 ALTER TABLE `TCO_LOCATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCO_OPPOTUNITY`
--

DROP TABLE IF EXISTS `TCO_OPPOTUNITY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCO_OPPOTUNITY` (
  `ID` bigint(20) NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `CODE` varchar(30) DEFAULT NULL,
  `TXNDATE` datetime DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  `REFERENCE_NO` varchar(30) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `STATUS_REASON` varchar(255) DEFAULT NULL,
  `VOUCHER_NO` varchar(30) NOT NULL,
  `CUSTOMER_NEED` varchar(255) DEFAULT NULL,
  `EST_CLOSE_DATE` date DEFAULT NULL,
  `PROBABILITY` int(11) DEFAULT NULL,
  `SITUATIION` varchar(255) DEFAULT NULL,
  `SOLUTION` varchar(255) DEFAULT NULL,
  `TOPIC` varchar(255) DEFAULT NULL,
  `ACCOUNT_ID` bigint(20) DEFAULT NULL,
  `CONTACT_ID` bigint(20) DEFAULT NULL,
  `COMPETITOR_ID` bigint(20) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `STATE_REASON` varchar(255) DEFAULT NULL,
  `STATE_INFO` varchar(255) DEFAULT NULL,
  `PROCESS_ID` bigint(20) DEFAULT NULL,
  `GROUP_ID` bigint(20) DEFAULT NULL,
  `BUDGET` decimal(19,2) DEFAULT NULL,
  `CCY` varchar(255) DEFAULT NULL,
  `LOCAL_BUDGET` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_OPP_ACC` (`ACCOUNT_ID`),
  KEY `FK_OPP_PER` (`CONTACT_ID`),
  KEY `FK_OPP_COMP` (`COMPETITOR_ID`),
  KEY `FK_PROSS_VOG` (`PROCESS_ID`),
  CONSTRAINT `FK_OPP_ACC` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `TCC_CONTACT` (`ID`),
  CONSTRAINT `FK_OPP_COMP` FOREIGN KEY (`COMPETITOR_ID`) REFERENCES `TCC_CONTACT` (`ID`),
  CONSTRAINT `FK_OPP_PER` FOREIGN KEY (`CONTACT_ID`) REFERENCES `TCC_CONTACT` (`ID`),
  CONSTRAINT `FK_PROSS_VOG` FOREIGN KEY (`PROCESS_ID`) REFERENCES `TVO_PROCESS` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCO_OPPOTUNITY`
--

LOCK TABLES `TCO_OPPOTUNITY` WRITE;
/*!40000 ALTER TABLE `TCO_OPPOTUNITY` DISABLE KEYS */;
/*!40000 ALTER TABLE `TCO_OPPOTUNITY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCO_OPPOTUNITY_ITEM`
--

DROP TABLE IF EXISTS `TCO_OPPOTUNITY_ITEM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCO_OPPOTUNITY_ITEM` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `INFO` varchar(255) DEFAULT NULL,
  `COMMODITY_ID` bigint(20) DEFAULT NULL,
  `OPPORTUNITY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_OPP_COMM` (`COMMODITY_ID`),
  KEY `FK_OPP_OPP` (`OPPORTUNITY_ID`),
  CONSTRAINT `FK_OPP_COMM` FOREIGN KEY (`COMMODITY_ID`) REFERENCES `TCO_COMMOTITY` (`ID`),
  CONSTRAINT `FK_OPP_OPP` FOREIGN KEY (`OPPORTUNITY_ID`) REFERENCES `TCO_OPPOTUNITY` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCO_OPPOTUNITY_ITEM`
--

LOCK TABLES `TCO_OPPOTUNITY_ITEM` WRITE;
/*!40000 ALTER TABLE `TCO_OPPOTUNITY_ITEM` DISABLE KEYS */;
/*!40000 ALTER TABLE `TCO_OPPOTUNITY_ITEM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCO_PAYMENT_PLAN`
--

DROP TABLE IF EXISTS `TCO_PAYMENT_PLAN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCO_PAYMENT_PLAN` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `CODE` varchar(30) NOT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `TERM` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=553 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCO_PAYMENT_PLAN`
--

LOCK TABLES `TCO_PAYMENT_PLAN` WRITE;
/*!40000 ALTER TABLE `TCO_PAYMENT_PLAN` DISABLE KEYS */;
INSERT INTO `TCO_PAYMENT_PLAN` VALUES (552,'','PAY-005','','op',12);
/*!40000 ALTER TABLE `TCO_PAYMENT_PLAN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCO_TAX`
--

DROP TABLE IF EXISTS `TCO_TAX`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCO_TAX` (
  `ID` bigint(20) NOT NULL,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `CODE` varchar(30) NOT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `FROM_DATE` date DEFAULT NULL,
  `RATE` decimal(19,2) DEFAULT NULL,
  `TO_DATE` date DEFAULT NULL,
  `EINVOICE_CODE` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCO_TAX`
--

LOCK TABLES `TCO_TAX` WRITE;
/*!40000 ALTER TABLE `TCO_TAX` DISABLE KEYS */;
INSERT INTO `TCO_TAX` VALUES (252,'','TAX-001','','kdv',NULL,18.00,NULL,'0015','KDV'),(253,'','TAX-002','','oiv',NULL,20.00,NULL,'4080','OIV');
/*!40000 ALTER TABLE `TCO_TAX` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCO_TERRITORY`
--

DROP TABLE IF EXISTS `TCO_TERRITORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCO_TERRITORY` (
  `ID` bigint(20) NOT NULL,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `CODE` varchar(30) NOT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCO_TERRITORY`
--

LOCK TABLES `TCO_TERRITORY` WRITE;
/*!40000 ALTER TABLE `TCO_TERRITORY` DISABLE KEYS */;
/*!40000 ALTER TABLE `TCO_TERRITORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCO_TERRITORY_ITEM`
--

DROP TABLE IF EXISTS `TCO_TERRITORY_ITEM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCO_TERRITORY_ITEM` (
  `ID` bigint(20) NOT NULL,
  `LOCATION_ID` bigint(20) DEFAULT NULL,
  `PARENT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_TERIT_LOC` (`LOCATION_ID`),
  KEY `FK_TERIT_PARENT` (`PARENT_ID`),
  CONSTRAINT `FK_TERIT_LOC` FOREIGN KEY (`LOCATION_ID`) REFERENCES `TCO_LOCATION` (`ID`),
  CONSTRAINT `FK_TERIT_PARENT` FOREIGN KEY (`PARENT_ID`) REFERENCES `TCO_TERRITORY` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCO_TERRITORY_ITEM`
--

LOCK TABLES `TCO_TERRITORY_ITEM` WRITE;
/*!40000 ALTER TABLE `TCO_TERRITORY_ITEM` DISABLE KEYS */;
/*!40000 ALTER TABLE `TCO_TERRITORY_ITEM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCO_UNIT_SET`
--

DROP TABLE IF EXISTS `TCO_UNIT_SET`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCO_UNIT_SET` (
  `ID` bigint(20) NOT NULL,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `CODE` varchar(30) NOT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `BASE_UNIT` varchar(255) DEFAULT NULL,
  `EINVOICE_CODE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCO_UNIT_SET`
--

LOCK TABLES `TCO_UNIT_SET` WRITE;
/*!40000 ALTER TABLE `TCO_UNIT_SET` DISABLE KEYS */;
INSERT INTO `TCO_UNIT_SET` VALUES (302,'','UNI-01','','adet','adet2','NIU'),(1602,'','UNI-02','','kkutu','kutu','BX');
/*!40000 ALTER TABLE `TCO_UNIT_SET` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TCO_UNIT_SET_ITEM`
--

DROP TABLE IF EXISTS `TCO_UNIT_SET_ITEM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TCO_UNIT_SET_ITEM` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `UNIT` varchar(255) DEFAULT NULL,
  `UNIT_SET_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_UNITIT_US` (`UNIT_SET_ID`),
  CONSTRAINT `FK_UNITIT_US` FOREIGN KEY (`UNIT_SET_ID`) REFERENCES `TCO_UNIT_SET` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TCO_UNIT_SET_ITEM`
--

LOCK TABLES `TCO_UNIT_SET_ITEM` WRITE;
/*!40000 ALTER TABLE `TCO_UNIT_SET_ITEM` DISABLE KEYS */;
INSERT INTO `TCO_UNIT_SET_ITEM` VALUES (352,NULL,0.00,'adet',302),(353,NULL,0.00,'adet2',302),(1652,NULL,0.00,'kutu',1602);
/*!40000 ALTER TABLE `TCO_UNIT_SET_ITEM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TFF_FEED`
--

DROP TABLE IF EXISTS `TFF_FEED`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TFF_FEED` (
  `ID` bigint(20) NOT NULL,
  `FEED_TIME` datetime DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `FEEDER` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `FEED_SUBJECT` varchar(255) DEFAULT NULL,
  `FEED_BODY` varchar(255) DEFAULT NULL,
  `BASE_FP` varchar(255) DEFAULT NULL,
  `BASE_BK` varchar(255) DEFAULT NULL,
  `BASE_PK` bigint(20) DEFAULT NULL,
  `REL_FP` varchar(255) DEFAULT NULL,
  `REL_BK` varchar(255) DEFAULT NULL,
  `REL_PK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TFF_FEED`
--

LOCK TABLES `TFF_FEED` WRITE;
/*!40000 ALTER TABLE `TFF_FEED` DISABLE KEYS */;
INSERT INTO `TFF_FEED` VALUES (852,'2018-02-09 01:23:59','DRAFT','SalesInvoiceFeeder','telve','SAL-000001','feeder.messages.InvoiceFeeder.CREATE$%&Telve TELVE$%&SAL-000001',NULL,NULL,NULL,NULL,NULL,NULL),(853,'2018-02-09 01:26:54','DRAFT','SalesInvoiceFeeder','telve','SAL-000002','feeder.messages.InvoiceFeeder.CREATE$%&Telve TELVE$%&SAL-000002',NULL,NULL,NULL,NULL,NULL,NULL),(1402,'2018-02-13 01:23:45','DRAFT','SalesInvoiceFeeder','telve','SAL-000003','feeder.messages.InvoiceFeeder.CREATE$%&Telve TELVE$%&SAL-000003',NULL,NULL,NULL,NULL,NULL,NULL),(2102,'2018-02-13 03:12:21','DRAFT','SalesInvoiceFeeder','telve','SAL-000004','feeder.messages.InvoiceFeeder.CREATE$%&Telve TELVE$%&SAL-000004',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `TFF_FEED` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TFF_FEED_FOLLOW`
--

DROP TABLE IF EXISTS `TFF_FEED_FOLLOW`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TFF_FEED_FOLLOW` (
  `ID` bigint(20) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `FOLLOW_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TFF_FEED_FOLLOW`
--

LOCK TABLES `TFF_FEED_FOLLOW` WRITE;
/*!40000 ALTER TABLE `TFF_FEED_FOLLOW` DISABLE KEYS */;
/*!40000 ALTER TABLE `TFF_FEED_FOLLOW` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TFF_FEED_MENTION`
--

DROP TABLE IF EXISTS `TFF_FEED_MENTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TFF_FEED_MENTION` (
  `ID` bigint(20) NOT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `FEED_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TFF_FEED_MENTION`
--

LOCK TABLES `TFF_FEED_MENTION` WRITE;
/*!40000 ALTER TABLE `TFF_FEED_MENTION` DISABLE KEYS */;
INSERT INTO `TFF_FEED_MENTION` VALUES (902,'ProcessFeature','PS-000001',602,852),(903,'CorporationFeature','kurumAdi',452,852),(904,'SalesInvoiceFeature','SAL-000001',652,852),(905,'ProcessFeature','PS-000002',603,853),(906,'CorporationFeature','kurumAdi',452,853),(907,'SalesInvoiceFeature','SAL-000002',653,853),(1452,'ProcessFeature','PS-000003',1152,1402),(1453,'CorporationFeature','kurumAdi',452,1402),(1454,'SalesInvoiceFeature','SAL-000003',1202,1402),(2152,'ProcessFeature','PS-000004',1852,2102),(2153,'CorporationFeature','BBB Limited Sirketi',452,2102),(2154,'SalesInvoiceFeature','SAL-000004',1902,2102);
/*!40000 ALTER TABLE `TFF_FEED_MENTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TFN_FINANCE_ACC`
--

DROP TABLE IF EXISTS `TFN_FINANCE_ACC`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TFN_FINANCE_ACC` (
  `ID` bigint(20) NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `ACCOUNT_NO` varchar(255) DEFAULT NULL,
  `ROLES` varchar(255) DEFAULT NULL,
  `BANK` varchar(255) DEFAULT NULL,
  `BRANCH` varchar(255) DEFAULT NULL,
  `CLOSE_DATE` date DEFAULT NULL,
  `CODE` varchar(255) DEFAULT NULL,
  `CCY` varchar(255) DEFAULT NULL,
  `IBAN` varchar(255) DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `OPEN_DATE` date DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `STATUS_REASON` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TFN_FINANCE_ACC`
--

LOCK TABLES `TFN_FINANCE_ACC` WRITE;
/*!40000 ALTER TABLE `TFN_FINANCE_ACC` DISABLE KEYS */;
/*!40000 ALTER TABLE `TFN_FINANCE_ACC` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TFN_FINANCE_TXN`
--

DROP TABLE IF EXISTS `TFN_FINANCE_TXN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TFN_FINANCE_TXN` (
  `ID` bigint(20) NOT NULL,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `CODE` varchar(30) DEFAULT NULL,
  `CCY` varchar(255) DEFAULT NULL,
  `TXNDATE` datetime DEFAULT NULL,
  `DEBIT` bit(1) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  `PROCESS_ID` varchar(255) DEFAULT NULL,
  `REFERENCE_NO` varchar(30) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `STATUS_REASON` varchar(255) DEFAULT NULL,
  `ACCOUNT_ID` bigint(20) DEFAULT NULL,
  `LOCAL_AMOUNT` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_FINACCTXN_ACC` (`ACCOUNT_ID`),
  CONSTRAINT `FK_FINACCTXN_ACC` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `TFN_FINANCE_ACC` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TFN_FINANCE_TXN`
--

LOCK TABLES `TFN_FINANCE_TXN` WRITE;
/*!40000 ALTER TABLE `TFN_FINANCE_TXN` DISABLE KEYS */;
/*!40000 ALTER TABLE `TFN_FINANCE_TXN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TFN_FINANCE_VIREMENT`
--

DROP TABLE IF EXISTS `TFN_FINANCE_VIREMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TFN_FINANCE_VIREMENT` (
  `ID` bigint(20) NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `CODE` varchar(30) DEFAULT NULL,
  `TXNDATE` datetime DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  `REFERENCE_NO` varchar(30) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `STATE_INFO` varchar(255) DEFAULT NULL,
  `STATE_REASON` varchar(255) DEFAULT NULL,
  `TOPIC` varchar(255) DEFAULT NULL,
  `VOUCHER_NO` varchar(30) NOT NULL,
  `LOCAL_AMOUNT` decimal(19,2) DEFAULT NULL,
  `GROUP_ID` bigint(20) DEFAULT NULL,
  `FROM_ACCOUNT_ID` bigint(20) DEFAULT NULL,
  `TO_ACCOUNT_ID` bigint(20) DEFAULT NULL,
  `FROM_AMOUNT` decimal(19,2) DEFAULT NULL,
  `FROM_CCY` varchar(255) DEFAULT NULL,
  `TO_AMOUNT` decimal(19,2) DEFAULT NULL,
  `TO_CCY` varchar(255) DEFAULT NULL,
  `VIREMENT_TYPE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_FAV_FACC` (`FROM_ACCOUNT_ID`),
  KEY `FK_FAV_TACC` (`TO_ACCOUNT_ID`),
  CONSTRAINT `FK_FAV_FACC` FOREIGN KEY (`FROM_ACCOUNT_ID`) REFERENCES `TFN_FINANCE_ACC` (`ID`),
  CONSTRAINT `FK_FAV_TACC` FOREIGN KEY (`TO_ACCOUNT_ID`) REFERENCES `TFN_FINANCE_ACC` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TFN_FINANCE_VIREMENT`
--

LOCK TABLES `TFN_FINANCE_VIREMENT` WRITE;
/*!40000 ALTER TABLE `TFN_FINANCE_VIREMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `TFN_FINANCE_VIREMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `THR_LEAVE`
--

DROP TABLE IF EXISTS `THR_LEAVE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `THR_LEAVE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `CODE` varchar(30) DEFAULT NULL,
  `TXNDATE` datetime DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  `REFERENCE_NO` varchar(30) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `STATE_INFO` varchar(255) DEFAULT NULL,
  `STATE_REASON` varchar(255) DEFAULT NULL,
  `TOPIC` varchar(255) DEFAULT NULL,
  `VOUCHER_NO` varchar(30) NOT NULL,
  `ANNUAL` bit(1) DEFAULT NULL,
  `END_DATE` datetime DEFAULT NULL,
  `LEAVE_DAY` int(11) DEFAULT NULL,
  `PAID` bit(1) DEFAULT NULL,
  `START_DATE` datetime DEFAULT NULL,
  `GROUP_ID` bigint(20) DEFAULT NULL,
  `EMPLOYEE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_LEAVE_EMP` (`EMPLOYEE_ID`),
  CONSTRAINT `FK_LEAVE_EMP` FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `TCC_CONTACT` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `THR_LEAVE`
--

LOCK TABLES `THR_LEAVE` WRITE;
/*!40000 ALTER TABLE `THR_LEAVE` DISABLE KEYS */;
/*!40000 ALTER TABLE `THR_LEAVE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `THR_SALARY_NOTE`
--

DROP TABLE IF EXISTS `THR_SALARY_NOTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `THR_SALARY_NOTE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `CODE` varchar(30) DEFAULT NULL,
  `TXNDATE` datetime DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  `REFERENCE_NO` varchar(30) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `STATE_INFO` varchar(255) DEFAULT NULL,
  `STATE_REASON` varchar(255) DEFAULT NULL,
  `TOPIC` varchar(255) DEFAULT NULL,
  `VOUCHER_NO` varchar(30) NOT NULL,
  `CCY` varchar(255) DEFAULT NULL,
  `PAYMENT_DATE` date DEFAULT NULL,
  `GROUP_ID` bigint(20) DEFAULT NULL,
  `ACCOUNT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SALARY_ACC` (`ACCOUNT_ID`),
  CONSTRAINT `FK_SALARY_ACC` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `TFN_FINANCE_ACC` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `THR_SALARY_NOTE`
--

LOCK TABLES `THR_SALARY_NOTE` WRITE;
/*!40000 ALTER TABLE `THR_SALARY_NOTE` DISABLE KEYS */;
/*!40000 ALTER TABLE `THR_SALARY_NOTE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `THR_SALARY_NOTE_ITEM`
--

DROP TABLE IF EXISTS `THR_SALARY_NOTE_ITEM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `THR_SALARY_NOTE_ITEM` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `EMPLOYEE_ID` bigint(20) DEFAULT NULL,
  `MASTER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SALARY_EMP` (`EMPLOYEE_ID`),
  KEY `FK_SALIT_SAL` (`MASTER_ID`),
  CONSTRAINT `FK_SALARY_EMP` FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `TCC_CONTACT` (`ID`),
  CONSTRAINT `FK_SALIT_SAL` FOREIGN KEY (`MASTER_ID`) REFERENCES `THR_SALARY_NOTE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `THR_SALARY_NOTE_ITEM`
--

LOCK TABLES `THR_SALARY_NOTE_ITEM` WRITE;
/*!40000 ALTER TABLE `THR_SALARY_NOTE_ITEM` DISABLE KEYS */;
/*!40000 ALTER TABLE `THR_SALARY_NOTE_ITEM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TIV_INVOICE`
--

DROP TABLE IF EXISTS `TIV_INVOICE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TIV_INVOICE` (
  `DIRECTION` varchar(31) NOT NULL,
  `ID` bigint(20) NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `CODE` varchar(30) DEFAULT NULL,
  `TXNDATE` datetime DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  `REFERENCE_NO` varchar(30) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `STATE_INFO` varchar(255) DEFAULT NULL,
  `STATE_REASON` varchar(255) DEFAULT NULL,
  `TOPIC` varchar(255) DEFAULT NULL,
  `VOUCHER_NO` varchar(30) NOT NULL,
  `TOT_CCY` varchar(255) DEFAULT NULL,
  `SHIP_DATE` date DEFAULT NULL,
  `SHIP_NOTE` varchar(255) DEFAULT NULL,
  `TOT_AMT` decimal(19,2) DEFAULT NULL,
  `GROUP_ID` bigint(20) DEFAULT NULL,
  `ACCOUNT_ID` bigint(20) DEFAULT NULL,
  `PROCESS_ID` bigint(20) DEFAULT NULL,
  `PAYMENTPLAN_ID` bigint(20) DEFAULT NULL,
  `LOCAL_AMOUNT` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_INV_PP` (`PAYMENTPLAN_ID`),
  KEY `FK_PROSS_ACC` (`ACCOUNT_ID`),
  CONSTRAINT `FK_INV_PP` FOREIGN KEY (`PAYMENTPLAN_ID`) REFERENCES `TCO_PAYMENT_PLAN` (`ID`),
  CONSTRAINT `FK_PROSS_ACC` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `TCC_CONTACT` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TIV_INVOICE`
--

LOCK TABLES `TIV_INVOICE` WRITE;
/*!40000 ALTER TABLE `TIV_INVOICE` DISABLE KEYS */;
INSERT INTO `TIV_INVOICE` VALUES ('SALES',652,'2018-02-09 01:23:59','2018-02-09 01:23:59','telve','','2018-02-09 00:00:00','','telve','',NULL,NULL,NULL,'DRAFT-NEUTRAL-DRAFT',NULL,NULL,'fat1','SAL-000001','TRY',NULL,'',90.00,NULL,452,602,552,90.00),('SALES',653,'2018-02-09 01:26:54','2018-02-09 01:26:54','telve','','2018-02-09 00:00:00','','telve','',NULL,NULL,NULL,'DRAFT-NEUTRAL-DRAFT',NULL,NULL,'fat2','SAL-000002','TRY',NULL,'',118.80,NULL,452,603,552,118.80),('SALES',1202,'2018-02-13 01:23:44','2018-02-13 01:23:44','telve','','2018-02-13 00:00:00','','telve','',NULL,NULL,NULL,'DRAFT-NEUTRAL-DRAFT',NULL,NULL,'fat3','SAL-000003','TRY',NULL,'',118.80,NULL,452,1152,552,118.80),('SALES',1902,'2018-02-13 03:12:21','2018-02-13 03:12:21','telve','','2018-02-13 00:00:00','','telve','',NULL,NULL,NULL,'DRAFT-NEUTRAL-DRAFT',NULL,NULL,'denFat','SAL-000004','TRY',NULL,'',29755.47,NULL,452,1852,552,29755.47);
/*!40000 ALTER TABLE `TIV_INVOICE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TIV_INVOICE_ITEM`
--

DROP TABLE IF EXISTS `TIV_INVOICE_ITEM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TIV_INVOICE_ITEM` (
  `ID` bigint(20) NOT NULL,
  `DISC_AMT` decimal(19,2) DEFAULT NULL,
  `DISC_RATE` int(11) DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `LİNE_TOT_AMT` decimal(19,2) DEFAULT NULL,
  `PRICE_AMT` decimal(19,2) DEFAULT NULL,
  `QTY_AMT` decimal(19,2) DEFAULT NULL,
  `QTY_UNIT` varchar(255) DEFAULT NULL,
  `TOT_AMT` decimal(19,2) DEFAULT NULL,
  `COMMODITY_ID` bigint(20) DEFAULT NULL,
  `MASTER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TIV_INVOICE_ITEM`
--

LOCK TABLES `TIV_INVOICE_ITEM` WRITE;
/*!40000 ALTER TABLE `TIV_INVOICE_ITEM` DISABLE KEYS */;
INSERT INTO `TIV_INVOICE_ITEM` VALUES (702,10.00,10,'',90.00,10.00,10.00,'UNI-01:adet2',100.00,402,652),(703,10.00,10,'',90.00,10.00,10.00,'UNI-01:adet2',100.00,402,653),(1252,10.00,10,'',90.00,10.00,10.00,'UNI-01:adet2',100.00,402,1202),(1952,495.30,5,'',9410.70,825.50,12.00,'UNI-01:adet2',9906.00,1702,1902),(1953,291.60,3,'',9428.40,1215.00,8.00,'UNI-01:adet2',9720.00,1703,1902),(1954,0.00,0,'',688.50,38.25,18.00,'UNI-01:adet2',688.50,1812,1902),(1955,0.00,0,'',1576.90,121.30,13.00,'UNI-01:adet2',1576.90,1813,1902),(1956,0.00,0,'',1400.00,7.00,200.00,'UNI-02:kutu',1400.00,1814,1902),(1957,0.00,0,'',2200.00,11.00,200.00,'UNI-02:kutu',2200.00,1815,1902),(1958,0.00,0,'',340.00,4.25,80.00,'UNI-01:adet2',340.00,1816,1902),(1959,0.00,0,'',172.00,2.15,80.00,'UNI-01:adet2',172.00,1817,1902);
/*!40000 ALTER TABLE `TIV_INVOICE_ITEM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TIV_INVOICE_SUM`
--

DROP TABLE IF EXISTS `TIV_INVOICE_SUM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TIV_INVOICE_SUM` (
  `ID` bigint(20) NOT NULL,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `ROW_KEY` varchar(255) DEFAULT NULL,
  `MASTER_ID` bigint(20) DEFAULT NULL,
  `BASE_NAME` varchar(255) DEFAULT NULL,
  `CODE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TIV_INVOICE_SUM`
--

LOCK TABLES `TIV_INVOICE_SUM` WRITE;
/*!40000 ALTER TABLE `TIV_INVOICE_SUM` DISABLE KEYS */;
INSERT INTO `TIV_INVOICE_SUM` VALUES (752,0.00,NULL,'TaxTotal',652,NULL,NULL),(753,100.00,NULL,'BeforeDiscountTotal',652,NULL,NULL),(754,10.00,NULL,'Discount',652,NULL,NULL),(755,90.00,NULL,'BeforeTaxTotal',652,NULL,NULL),(756,90.00,NULL,'GrandTotal',652,NULL,NULL),(757,10.00,NULL,'DiscountRate',652,NULL,NULL),(758,10.80,'kdv','Tax.TAX-001',653,'KDV','0015'),(759,18.00,'oiv','Tax.TAX-002',653,'OIV','0016'),(760,28.80,NULL,'TaxTotal',653,NULL,NULL),(761,100.00,NULL,'BeforeDiscountTotal',653,NULL,NULL),(762,10.00,NULL,'Discount',653,NULL,NULL),(763,90.00,NULL,'BeforeTaxTotal',653,NULL,NULL),(764,118.80,NULL,'GrandTotal',653,NULL,NULL),(765,10.00,NULL,'DiscountRate',653,NULL,NULL),(1302,10.80,'kdv','Tax.TAX-001',1202,'KDV','0015'),(1303,18.00,'oiv','Tax.TAX-002',1202,'OIV','4080'),(1304,28.80,NULL,'TaxTotal',1202,NULL,NULL),(1305,100.00,NULL,'BeforeDiscountTotal',1202,NULL,NULL),(1306,10.00,NULL,'Discount',1202,NULL,NULL),(1307,90.00,NULL,'BeforeTaxTotal',1202,NULL,NULL),(1308,118.80,NULL,'GrandTotal',1202,NULL,NULL),(1309,10.00,NULL,'DiscountRate',1202,NULL,NULL),(2002,4538.97,'kdv','Tax.TAX-001',1902,'KDV','0015'),(2003,4538.97,NULL,'TaxTotal',1902,NULL,NULL),(2004,26003.40,NULL,'BeforeDiscountTotal',1902,NULL,NULL),(2005,786.90,NULL,'Discount',1902,NULL,NULL),(2006,25216.50,NULL,'BeforeTaxTotal',1902,NULL,NULL),(2007,29755.47,NULL,'GrandTotal',1902,NULL,NULL),(2008,3.03,NULL,'DiscountRate',1902,NULL,NULL);
/*!40000 ALTER TABLE `TIV_INVOICE_SUM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TLE_LEAD`
--

DROP TABLE IF EXISTS `TLE_LEAD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TLE_LEAD` (
  `ID` bigint(20) NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `CODE` varchar(30) DEFAULT NULL,
  `TXNDATE` datetime DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  `REFERENCE_NO` varchar(30) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `STATE_INFO` varchar(255) DEFAULT NULL,
  `STATE_REASON` varchar(255) DEFAULT NULL,
  `TOPIC` varchar(255) DEFAULT NULL,
  `VOUCHER_NO` varchar(30) NOT NULL,
  `RELATED_ADDRESS` varchar(255) DEFAULT NULL,
  `RELATED_COMPANY_NAME` varchar(255) DEFAULT NULL,
  `RELATED_EMAIL` varchar(255) DEFAULT NULL,
  `RELATED_PERSON_NAME` varchar(255) DEFAULT NULL,
  `RELATED_PERSON_SURNAME` varchar(255) DEFAULT NULL,
  `RELATED_PHONE` varchar(255) DEFAULT NULL,
  `GROUP_ID` bigint(20) DEFAULT NULL,
  `INDUSTRY_ID` bigint(20) DEFAULT NULL,
  `LEAD_CATEGORY_ID` bigint(20) DEFAULT NULL,
  `LEAD_SOURCE_ID` bigint(20) DEFAULT NULL,
  `LOCATION_ID` bigint(20) DEFAULT NULL,
  `TERRITORY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_LEAD_INDUSTRY` (`INDUSTRY_ID`),
  KEY `FK_LEAD_LECATEGORY` (`LEAD_CATEGORY_ID`),
  KEY `FK_LEAD_LESOURCE` (`LEAD_SOURCE_ID`),
  KEY `FK_LEAD_LOC` (`LOCATION_ID`),
  KEY `FK_LEAD_TER` (`TERRITORY_ID`),
  KEY `FK_VOG_VOG` (`GROUP_ID`),
  CONSTRAINT `FK_LEAD_INDUSTRY` FOREIGN KEY (`INDUSTRY_ID`) REFERENCES `TCO_INDUSTRY` (`ID`),
  CONSTRAINT `FK_LEAD_LECATEGORY` FOREIGN KEY (`LEAD_CATEGORY_ID`) REFERENCES `TLE_LEAD_CATEGORY` (`ID`),
  CONSTRAINT `FK_LEAD_LESOURCE` FOREIGN KEY (`LEAD_SOURCE_ID`) REFERENCES `TLE_LEAD_SOURCE` (`ID`),
  CONSTRAINT `FK_LEAD_LOC` FOREIGN KEY (`LOCATION_ID`) REFERENCES `TCO_LOCATION` (`ID`),
  CONSTRAINT `FK_LEAD_TER` FOREIGN KEY (`TERRITORY_ID`) REFERENCES `TCO_TERRITORY` (`ID`),
  CONSTRAINT `FK_VOG_VOG` FOREIGN KEY (`GROUP_ID`) REFERENCES `TVO_GROUP` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TLE_LEAD`
--

LOCK TABLES `TLE_LEAD` WRITE;
/*!40000 ALTER TABLE `TLE_LEAD` DISABLE KEYS */;
/*!40000 ALTER TABLE `TLE_LEAD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TLE_LEAD_CATEGORY`
--

DROP TABLE IF EXISTS `TLE_LEAD_CATEGORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TLE_LEAD_CATEGORY` (
  `ID` bigint(20) NOT NULL,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `CODE` varchar(255) NOT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `PATH` varchar(255) DEFAULT NULL,
  `PID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_3oyngyytnhnnhf7cs3lipnnju` (`PID`),
  CONSTRAINT `FK_3oyngyytnhnnhf7cs3lipnnju` FOREIGN KEY (`PID`) REFERENCES `TLE_LEAD_CATEGORY` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TLE_LEAD_CATEGORY`
--

LOCK TABLES `TLE_LEAD_CATEGORY` WRITE;
/*!40000 ALTER TABLE `TLE_LEAD_CATEGORY` DISABLE KEYS */;
/*!40000 ALTER TABLE `TLE_LEAD_CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TLE_LEAD_SOURCE`
--

DROP TABLE IF EXISTS `TLE_LEAD_SOURCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TLE_LEAD_SOURCE` (
  `ID` bigint(20) NOT NULL,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `CODE` varchar(255) NOT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `PATH` varchar(255) DEFAULT NULL,
  `PID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_rnmj1udkxb64cucjs9aknup6n` (`PID`),
  CONSTRAINT `FK_rnmj1udkxb64cucjs9aknup6n` FOREIGN KEY (`PID`) REFERENCES `TLE_LEAD_SOURCE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TLE_LEAD_SOURCE`
--

LOCK TABLES `TLE_LEAD_SOURCE` WRITE;
/*!40000 ALTER TABLE `TLE_LEAD_SOURCE` DISABLE KEYS */;
/*!40000 ALTER TABLE `TLE_LEAD_SOURCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TLI_GROUP`
--

DROP TABLE IF EXISTS `TLI_GROUP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TLI_GROUP` (
  `ID` bigint(20) NOT NULL,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `CODE` varchar(30) NOT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(30) NOT NULL,
  `PATH` varchar(255) DEFAULT NULL,
  `PID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UC_TLI_GROUPCODE_COL` (`CODE`),
  KEY `FK_sfuft7xjl327e7an6qtreau2k` (`PID`),
  CONSTRAINT `FK_sfuft7xjl327e7an6qtreau2k` FOREIGN KEY (`PID`) REFERENCES `TLI_GROUP` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TLI_GROUP`
--

LOCK TABLES `TLI_GROUP` WRITE;
/*!40000 ALTER TABLE `TLI_GROUP` DISABLE KEYS */;
/*!40000 ALTER TABLE `TLI_GROUP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TLI_ROLE`
--

DROP TABLE IF EXISTS `TLI_ROLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TLI_ROLE` (
  `ID` bigint(20) NOT NULL,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `CODE` varchar(30) NOT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UC_TLI_ROLECODE_COL` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TLI_ROLE`
--

LOCK TABLES `TLI_ROLE` WRITE;
/*!40000 ALTER TABLE `TLI_ROLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `TLI_ROLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TLI_ROLE_PERM`
--

DROP TABLE IF EXISTS `TLI_ROLE_PERM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TLI_ROLE_PERM` (
  `ID` bigint(20) NOT NULL,
  `PERMISSION` varchar(255) DEFAULT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_p1jwmramrq4uks7ode7qo9slo` (`ROLE_ID`),
  CONSTRAINT `FK_p1jwmramrq4uks7ode7qo9slo` FOREIGN KEY (`ROLE_ID`) REFERENCES `TLI_ROLE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TLI_ROLE_PERM`
--

LOCK TABLES `TLI_ROLE_PERM` WRITE;
/*!40000 ALTER TABLE `TLI_ROLE_PERM` DISABLE KEYS */;
/*!40000 ALTER TABLE `TLI_ROLE_PERM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TLI_USER`
--

DROP TABLE IF EXISTS `TLI_USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TLI_USER` (
  `ID` bigint(20) NOT NULL,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `ISAUTOCREATED` bit(1) DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `LOGIN_NAME` varchar(100) NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `FIRST_NAME` varchar(255) DEFAULT NULL,
  `LAST_NAME` varchar(255) DEFAULT NULL,
  `PW_HASH` varchar(255) DEFAULT NULL,
  `USER_TYPE` varchar(255) DEFAULT NULL,
  `GROUP_ID` bigint(20) DEFAULT NULL,
  `MOBILE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `TLI_USER_LOGIN_NAME` (`LOGIN_NAME`),
  KEY `FK_kjnhw3iwif4nnfmicyqqjcht1` (`GROUP_ID`),
  CONSTRAINT `FK_kjnhw3iwif4nnfmicyqqjcht1` FOREIGN KEY (`GROUP_ID`) REFERENCES `TLI_GROUP` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TLI_USER`
--

LOCK TABLES `TLI_USER` WRITE;
/*!40000 ALTER TABLE `TLI_USER` DISABLE KEYS */;
INSERT INTO `TLI_USER` VALUES (1,'','\0',NULL,'telve',NULL,'Telve','TELVE','$shiro1$SHA-256$500000$fyHhEC/9htwbh4Ob0kYfYA==$pAcy831UgjJkgyi06CoPVHmhIcrYSxo0kz4PwlOUX6E=','SUPERADMIN',NULL,NULL);
/*!40000 ALTER TABLE `TLI_USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TLI_USER_ATTR`
--

DROP TABLE IF EXISTS `TLI_USER_ATTR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TLI_USER_ATTR` (
  `ID` bigint(20) NOT NULL,
  `ATTR_KEY` varchar(255) DEFAULT NULL,
  `ATTR_VALUE` varchar(255) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_nfd7e34froibg422leqy1qmop` (`USER_ID`),
  CONSTRAINT `FK_nfd7e34froibg422leqy1qmop` FOREIGN KEY (`USER_ID`) REFERENCES `TLI_USER` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TLI_USER_ATTR`
--

LOCK TABLES `TLI_USER_ATTR` WRITE;
/*!40000 ALTER TABLE `TLI_USER_ATTR` DISABLE KEYS */;
/*!40000 ALTER TABLE `TLI_USER_ATTR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TLI_USER_GROUP`
--

DROP TABLE IF EXISTS `TLI_USER_GROUP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TLI_USER_GROUP` (
  `ID` bigint(20) NOT NULL,
  `GROUP_ID` bigint(20) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_cwoua04a5pyfbd082up2tfmx6` (`USER_ID`),
  KEY `FK_kjnhw3iwif4nnfmicyqqjcht0` (`GROUP_ID`),
  CONSTRAINT `FK_cwoua04a5pyfbd082up2tfmx6` FOREIGN KEY (`USER_ID`) REFERENCES `TLI_USER` (`ID`),
  CONSTRAINT `FK_kjnhw3iwif4nnfmicyqqjcht0` FOREIGN KEY (`GROUP_ID`) REFERENCES `TLI_GROUP` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TLI_USER_GROUP`
--

LOCK TABLES `TLI_USER_GROUP` WRITE;
/*!40000 ALTER TABLE `TLI_USER_GROUP` DISABLE KEYS */;
/*!40000 ALTER TABLE `TLI_USER_GROUP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TLI_USER_ROLE`
--

DROP TABLE IF EXISTS `TLI_USER_ROLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TLI_USER_ROLE` (
  `ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_etsafcwp4b6ojei67xdspcf5f` (`USER_ID`),
  KEY `FK_mfodp7tbtxxp6pj1rf14otrdk` (`ROLE_ID`),
  CONSTRAINT `FK_etsafcwp4b6ojei67xdspcf5f` FOREIGN KEY (`USER_ID`) REFERENCES `TLI_USER` (`ID`),
  CONSTRAINT `FK_mfodp7tbtxxp6pj1rf14otrdk` FOREIGN KEY (`ROLE_ID`) REFERENCES `TLI_ROLE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TLI_USER_ROLE`
--

LOCK TABLES `TLI_USER_ROLE` WRITE;
/*!40000 ALTER TABLE `TLI_USER_ROLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `TLI_USER_ROLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TLV_AUDIT_LOG`
--

DROP TABLE IF EXISTS `TLV_AUDIT_LOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TLV_AUDIT_LOG` (
  `ID` bigint(20) NOT NULL,
  `TS` datetime DEFAULT NULL,
  `DNAME` varchar(255) DEFAULT NULL,
  `DPK` bigint(20) DEFAULT NULL,
  `DBK` varchar(255) DEFAULT NULL,
  `ACT` varchar(255) DEFAULT NULL,
  `MSG` varchar(255) DEFAULT NULL,
  `CAT` varchar(255) DEFAULT NULL,
  `UID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TLV_AUDIT_LOG`
--

LOCK TABLES `TLV_AUDIT_LOG` WRITE;
/*!40000 ALTER TABLE `TLV_AUDIT_LOG` DISABLE KEYS */;
INSERT INTO `TLV_AUDIT_LOG` VALUES (1,'2018-02-09 00:07:26','Login',0,'','AUTH','','AUTH','telve'),(52,'2018-02-09 00:13:44','Login',0,'','AUTH','','AUTH','telve'),(102,'2018-02-09 00:14:58','Login',0,'','AUTH','','AUTH','telve'),(152,'2018-02-09 00:22:13','Login',0,'','AUTH','','AUTH','telve'),(202,'2018-02-09 01:18:39','Login',0,'','AUTH','','AUTH','telve'),(203,'2018-02-09 01:19:07','TaxDefinition',252,'kdv','INSERT','','PARAM','telve'),(204,'2018-02-09 01:19:21','TaxDefinition',253,'oiv','INSERT','','PARAM','telve'),(205,'2018-02-09 01:20:45','UnitSetDefinition',302,'adet','INSERT','','PARAM','telve'),(206,'2018-02-09 01:21:11','Commodity',402,'urun1','INSERT','','ENTITY','telve'),(207,'2018-02-09 01:21:56','Corporation',452,'kurumAdi','INSERT','','ENTITY','telve'),(208,'2018-02-09 01:22:15','Corporation',452,'kurumAdi','UPDATE','','ENTITY','telve'),(209,'2018-02-09 01:23:37','PaymentPlan',552,'op','INSERT','','PARAM','telve'),(210,'2018-02-09 01:23:59','SalesInvoice',652,'SAL-000001','INSERT','','ENTITY','telve'),(211,'2018-02-09 01:23:59','SalesInvoice',652,'SAL-000001','CREATE','CREATE -> DRAFT','STATE_CHANGE','telve'),(212,'2018-02-09 01:24:24','Commodity',402,'urun1','UPDATE','','ENTITY','telve'),(213,'2018-02-09 01:24:52','SalesInvoice',652,'SAL-000001','UPDATE','','ENTITY','telve'),(214,'2018-02-09 01:26:54','SalesInvoice',653,'SAL-000002','INSERT','','ENTITY','telve'),(215,'2018-02-09 01:26:54','SalesInvoice',653,'SAL-000002','CREATE','CREATE -> DRAFT','STATE_CHANGE','telve'),(952,'2018-02-12 23:23:50','Login',0,'','AUTH','','AUTH','telve'),(1002,'2018-02-12 23:51:03','Login',0,'','AUTH','','AUTH','telve'),(1052,'2018-02-13 01:08:37','Login',0,'','AUTH','','AUTH','telve'),(1053,'2018-02-13 01:15:18','Corporation',452,'kurumAdi','UPDATE','','ENTITY','telve'),(1054,'2018-02-13 01:19:46','TaxDefinition',253,'oiv','UPDATE','','PARAM','telve'),(1102,'2018-02-13 01:22:13','Login',0,'','AUTH','','AUTH','telve'),(1103,'2018-02-13 01:23:44','SalesInvoice',1202,'SAL-000003','INSERT','','ENTITY','telve'),(1104,'2018-02-13 01:23:45','SalesInvoice',1202,'SAL-000003','CREATE','CREATE -> DRAFT','STATE_CHANGE','telve'),(1502,'2018-02-13 01:36:55','Login',0,'','AUTH','','AUTH','telve'),(1503,'2018-02-13 02:08:25','Corporation',452,'kurumAdi','UPDATE','','ENTITY','telve'),(1504,'2018-02-13 02:08:51','Corporation',452,'kurumAdi','UPDATE','','ENTITY','telve'),(1505,'2018-02-13 02:10:43','Corporation',452,'kurumAdi','UPDATE','','ENTITY','telve'),(1506,'2018-02-13 02:17:03','Corporation',452,'BBB Limited Sirketi','UPDATE','','ENTITY','telve'),(1507,'2018-02-13 02:17:57','Corporation',452,'BBB Limited Sirketi','UPDATE','','ENTITY','telve'),(1508,'2018-02-13 02:18:43','Corporation',452,'BBB Limited Sirketi','UPDATE','','ENTITY','telve'),(1509,'2018-02-13 02:20:07','Corporation',452,'BBB Limited Sirketi','UPDATE','','ENTITY','telve'),(1510,'2018-02-13 02:45:06','UnitSetDefinition',1602,'kkutu','INSERT','','PARAM','telve'),(1511,'2018-02-13 02:45:11','UnitSetDefinition',1602,'kkutu','UPDATE','','PARAM','telve'),(1512,'2018-02-13 02:46:51','TaxDefinition',252,'kdv','UPDATE','','PARAM','telve'),(1513,'2018-02-13 02:47:05','Commodity',1702,'Masa Üstü Bilgisayar','INSERT','','ENTITY','telve'),(1514,'2018-02-13 02:47:37','Commodity',1703,'Notebook Bilgisayar','INSERT','','ENTITY','telve'),(1752,'2018-02-13 02:50:22','Login',0,'','AUTH','','AUTH','telve'),(1753,'2018-02-13 02:54:59','Commodity',1802,'Notebook Cantasi','DELETE','','ENTITY','telve'),(1754,'2018-02-13 02:56:12','Commodity',1810,'Notebook Cantasi','DELETE','','ENTITY','telve'),(1755,'2018-02-13 02:56:30','Commodity',1812,'Notebook Çantasi','INSERT','','ENTITY','telve'),(1756,'2018-02-13 02:57:08','Commodity',1813,'Yazici','INSERT','','ENTITY','telve'),(1757,'2018-02-13 02:57:44','Commodity',1814,'CD-R 2*56','INSERT','','ENTITY','telve'),(1758,'2018-02-13 02:58:01','Commodity',1815,'DVD-R','INSERT','','ENTITY','telve'),(1759,'2018-02-13 02:58:22','Commodity',1816,'Dolma Kalem','INSERT','','ENTITY','telve'),(1760,'2018-02-13 02:58:51','Commodity',1817,'Tükenmez Kalem','INSERT','','ENTITY','telve'),(1761,'2018-02-13 03:12:02','Commodity',1817,'Tükenmez Kalem','UPDATE','','ENTITY','telve'),(1762,'2018-02-13 03:12:21','SalesInvoice',1902,'SAL-000004','INSERT','','ENTITY','telve'),(1763,'2018-02-13 03:12:21','SalesInvoice',1902,'SAL-000004','CREATE','CREATE -> DRAFT','STATE_CHANGE','telve'),(2202,'2018-02-13 03:15:09','Login',0,'','AUTH','','AUTH','telve'),(2252,'2018-02-13 03:31:23','Login',0,'','AUTH','','AUTH','telve'),(2302,'2018-02-13 03:39:02','Login',0,'','AUTH','','AUTH','telve'),(2352,'2018-02-13 04:07:36','Login',0,'','AUTH','','AUTH','telve'),(2402,'2018-02-13 04:19:03','Login',0,'','AUTH','','AUTH','telve'),(2403,'2018-02-13 04:35:29','SalesInvoice',1902,'SAL-000004','UPDATE','','ENTITY','telve'),(2452,'2018-02-13 04:39:15','Login',0,'','AUTH','','AUTH','telve');
/*!40000 ALTER TABLE `TLV_AUDIT_LOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TLV_AUDIT_LOG_DET`
--

DROP TABLE IF EXISTS `TLV_AUDIT_LOG_DET`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TLV_AUDIT_LOG_DET` (
  `ID` bigint(20) NOT NULL,
  `LID` bigint(20) DEFAULT NULL,
  `ATTR` varchar(255) DEFAULT NULL,
  `OVAL` varchar(255) DEFAULT NULL,
  `NVAL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TLV_AUDIT_LOG_DET`
--

LOCK TABLES `TLV_AUDIT_LOG_DET` WRITE;
/*!40000 ALTER TABLE `TLV_AUDIT_LOG_DET` DISABLE KEYS */;
/*!40000 ALTER TABLE `TLV_AUDIT_LOG_DET` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TLV_COMMANDS`
--

DROP TABLE IF EXISTS `TLV_COMMANDS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TLV_COMMANDS` (
  `ID` bigint(20) NOT NULL,
  `COMMAND` longtext,
  `CREATE_BY` varchar(255) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `SCHEDULE` bit(1) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TLV_COMMANDS`
--

LOCK TABLES `TLV_COMMANDS` WRITE;
/*!40000 ALTER TABLE `TLV_COMMANDS` DISABLE KEYS */;
/*!40000 ALTER TABLE `TLV_COMMANDS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TLV_EXEC_LOG`
--

DROP TABLE IF EXISTS `TLV_EXEC_LOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TLV_EXEC_LOG` (
  `ID` bigint(20) NOT NULL,
  `LOG_DATE` datetime DEFAULT NULL,
  `EXEC_BY` varchar(255) DEFAULT NULL,
  `LOGGER` varchar(255) DEFAULT NULL,
  `MSG` varchar(255) DEFAULT NULL,
  `SEVERITY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TLV_EXEC_LOG`
--

LOCK TABLES `TLV_EXEC_LOG` WRITE;
/*!40000 ALTER TABLE `TLV_EXEC_LOG` DISABLE KEYS */;
/*!40000 ALTER TABLE `TLV_EXEC_LOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TLV_NOTE`
--

DROP TABLE IF EXISTS `TLV_NOTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TLV_NOTE` (
  `ID` bigint(20) NOT NULL,
  `ATTACHMENT` varchar(255) DEFAULT NULL,
  `BODY` varchar(2000) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  `PERMISSION` varchar(255) DEFAULT NULL,
  `PRIORITY` varchar(255) DEFAULT NULL,
  `SUBJECT` varchar(255) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TLV_NOTE`
--

LOCK TABLES `TLV_NOTE` WRITE;
/*!40000 ALTER TABLE `TLV_NOTE` DISABLE KEYS */;
/*!40000 ALTER TABLE `TLV_NOTE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TLV_SEQUENCE`
--

DROP TABLE IF EXISTS `TLV_SEQUENCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TLV_SEQUENCE` (
  `ID` bigint(20) NOT NULL,
  `SQ_KEY` varchar(255) DEFAULT NULL,
  `SQ_VALUE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TLV_SEQUENCE`
--

LOCK TABLES `TLV_SEQUENCE` WRITE;
/*!40000 ALTER TABLE `TLV_SEQUENCE` DISABLE KEYS */;
/*!40000 ALTER TABLE `TLV_SEQUENCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TLV_SUGGESTION_ITEM`
--

DROP TABLE IF EXISTS `TLV_SUGGESTION_ITEM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TLV_SUGGESTION_ITEM` (
  `ID` bigint(20) NOT NULL,
  `ISACTIVE` bit(1) DEFAULT NULL,
  `DATA` varchar(255) NOT NULL,
  `CODE_GROUP` varchar(255) DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `CODE_KEY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TLV_SUGGESTION_ITEM`
--

LOCK TABLES `TLV_SUGGESTION_ITEM` WRITE;
/*!40000 ALTER TABLE `TLV_SUGGESTION_ITEM` DISABLE KEYS */;
/*!40000 ALTER TABLE `TLV_SUGGESTION_ITEM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TOR_ORDER`
--

DROP TABLE IF EXISTS `TOR_ORDER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TOR_ORDER` (
  `DIRECTION` varchar(31) NOT NULL,
  `ID` bigint(20) NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `CODE` varchar(30) DEFAULT NULL,
  `TXNDATE` datetime DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  `REFERENCE_NO` varchar(30) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `STATE_INFO` varchar(255) DEFAULT NULL,
  `STATE_REASON` varchar(255) DEFAULT NULL,
  `TOPIC` varchar(255) DEFAULT NULL,
  `VOUCHER_NO` varchar(30) NOT NULL,
  `TOT_CCY` varchar(255) DEFAULT NULL,
  `SHIP_DATE` date DEFAULT NULL,
  `TOT_AMT` decimal(19,2) DEFAULT NULL,
  `GROUP_ID` bigint(20) DEFAULT NULL,
  `ACCOUNT_ID` bigint(20) DEFAULT NULL,
  `PROCESS_ID` bigint(20) DEFAULT NULL,
  `PAYMENTPLAN_ID` bigint(20) DEFAULT NULL,
  `SHIP_NOTE` varchar(255) DEFAULT NULL,
  `LOCAL_AMOUNT` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ORDER_ACC` (`ACCOUNT_ID`),
  KEY `FK_ORD_PP` (`PAYMENTPLAN_ID`),
  CONSTRAINT `FK_ORDER_ACC` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `TCC_CONTACT` (`ID`),
  CONSTRAINT `FK_ORD_PP` FOREIGN KEY (`PAYMENTPLAN_ID`) REFERENCES `TCO_PAYMENT_PLAN` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TOR_ORDER`
--

LOCK TABLES `TOR_ORDER` WRITE;
/*!40000 ALTER TABLE `TOR_ORDER` DISABLE KEYS */;
/*!40000 ALTER TABLE `TOR_ORDER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TOR_ORDER_ITEM`
--

DROP TABLE IF EXISTS `TOR_ORDER_ITEM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TOR_ORDER_ITEM` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `INFO` varchar(255) DEFAULT NULL,
  `PRICE_AMT` decimal(19,2) DEFAULT NULL,
  `QTY_AMT` decimal(19,2) DEFAULT NULL,
  `QTY_UNIT` varchar(255) DEFAULT NULL,
  `TOT_AMT` decimal(19,2) DEFAULT NULL,
  `COMMODITY_ID` bigint(20) DEFAULT NULL,
  `MASTER_ID` bigint(20) DEFAULT NULL,
  `DISC_AMT` decimal(19,2) DEFAULT NULL,
  `DISC_RATE` int(11) DEFAULT NULL,
  `LINE_TOT_AMT` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TOR_ORDER_ITEM`
--

LOCK TABLES `TOR_ORDER_ITEM` WRITE;
/*!40000 ALTER TABLE `TOR_ORDER_ITEM` DISABLE KEYS */;
/*!40000 ALTER TABLE `TOR_ORDER_ITEM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TOR_ORDER_SUM`
--

DROP TABLE IF EXISTS `TOR_ORDER_SUM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TOR_ORDER_SUM` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `ROW_KEY` varchar(255) DEFAULT NULL,
  `MASTER_ID` bigint(20) DEFAULT NULL,
  `BASE_NAME` varchar(255) DEFAULT NULL,
  `CODE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TOR_ORDER_SUM`
--

LOCK TABLES `TOR_ORDER_SUM` WRITE;
/*!40000 ALTER TABLE `TOR_ORDER_SUM` DISABLE KEYS */;
/*!40000 ALTER TABLE `TOR_ORDER_SUM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TPC_PAYMENT`
--

DROP TABLE IF EXISTS `TPC_PAYMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TPC_PAYMENT` (
  `DIRECTION` varchar(31) NOT NULL,
  `ID` bigint(20) NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `CODE` varchar(30) DEFAULT NULL,
  `TXNDATE` datetime DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  `REFERENCE_NO` varchar(30) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `STATE_INFO` varchar(255) DEFAULT NULL,
  `STATE_REASON` varchar(255) DEFAULT NULL,
  `TOPIC` varchar(255) DEFAULT NULL,
  `VOUCHER_NO` varchar(30) NOT NULL,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `CCY` varchar(255) DEFAULT NULL,
  `GROUP_ID` bigint(20) DEFAULT NULL,
  `ACCOUNT_ID` bigint(20) DEFAULT NULL,
  `PROCESS_ID` bigint(20) DEFAULT NULL,
  `BCA_ID` bigint(20) DEFAULT NULL,
  `LOCAL_AMOUNT` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TPC_PAYMENT`
--

LOCK TABLES `TPC_PAYMENT` WRITE;
/*!40000 ALTER TABLE `TPC_PAYMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `TPC_PAYMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TSQ_QUOTE`
--

DROP TABLE IF EXISTS `TSQ_QUOTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TSQ_QUOTE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `CODE` varchar(30) DEFAULT NULL,
  `TXNDATE` datetime DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `OWNER` varchar(255) DEFAULT NULL,
  `REFERENCE_NO` varchar(30) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `STATUS_REASON` varchar(255) DEFAULT NULL,
  `VOUCHER_NO` varchar(30) NOT NULL,
  `EXP_DATE` date DEFAULT NULL,
  `REVISION` int(11) DEFAULT NULL,
  `TOT_AMT` decimal(19,2) DEFAULT NULL,
  `TOT_CCY` varchar(255) DEFAULT NULL,
  `ACCOUNT_ID` bigint(20) DEFAULT NULL,
  `COMPETITOR_ID` bigint(20) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `STATE_REASON` varchar(255) DEFAULT NULL,
  `STATE_INFO` varchar(255) DEFAULT NULL,
  `PROCESS_ID` bigint(20) DEFAULT NULL,
  `GROUP_ID` bigint(20) DEFAULT NULL,
  `TOPIC` varchar(255) DEFAULT NULL,
  `PAYMENTPLAN_ID` bigint(20) DEFAULT NULL,
  `LOCAL_AMOUNT` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_OPP_PP` (`PAYMENTPLAN_ID`),
  CONSTRAINT `FK_OPP_PP` FOREIGN KEY (`PAYMENTPLAN_ID`) REFERENCES `TCO_PAYMENT_PLAN` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TSQ_QUOTE`
--

LOCK TABLES `TSQ_QUOTE` WRITE;
/*!40000 ALTER TABLE `TSQ_QUOTE` DISABLE KEYS */;
/*!40000 ALTER TABLE `TSQ_QUOTE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TSQ_QUOTE_ITEM`
--

DROP TABLE IF EXISTS `TSQ_QUOTE_ITEM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TSQ_QUOTE_ITEM` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `INFO` varchar(255) DEFAULT NULL,
  `QTY_AMT` decimal(19,2) DEFAULT NULL,
  `QTY_UNIT` varchar(255) DEFAULT NULL,
  `TOT_AMT` decimal(19,2) DEFAULT NULL,
  `TOT_CCY` varchar(255) DEFAULT NULL,
  `PRICE_AMT` decimal(19,2) DEFAULT NULL,
  `PROICE_CCY` varchar(255) DEFAULT NULL,
  `COMMODITY_ID` bigint(20) DEFAULT NULL,
  `MASTER_ID` bigint(20) DEFAULT NULL,
  `DISC_AMT` decimal(19,2) DEFAULT NULL,
  `DISC_RATE` int(11) DEFAULT NULL,
  `LINE_TOT_AMT` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_QUOTEIT_COMMO` (`COMMODITY_ID`),
  KEY `FK_QUOTEIT_QUOTE` (`MASTER_ID`),
  CONSTRAINT `FK_QUOTEIT_COMMO` FOREIGN KEY (`COMMODITY_ID`) REFERENCES `TCO_COMMOTITY` (`ID`),
  CONSTRAINT `FK_QUOTEIT_QUOTE` FOREIGN KEY (`MASTER_ID`) REFERENCES `TSQ_QUOTE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TSQ_QUOTE_ITEM`
--

LOCK TABLES `TSQ_QUOTE_ITEM` WRITE;
/*!40000 ALTER TABLE `TSQ_QUOTE_ITEM` DISABLE KEYS */;
/*!40000 ALTER TABLE `TSQ_QUOTE_ITEM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TSQ_QUOTE_SUM`
--

DROP TABLE IF EXISTS `TSQ_QUOTE_SUM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TSQ_QUOTE_SUM` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `INFO` varchar(255) DEFAULT NULL,
  `ROW_KEY` varchar(255) DEFAULT NULL,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `MASTER_ID` bigint(20) DEFAULT NULL,
  `BASE_NAME` varchar(255) DEFAULT NULL,
  `CODE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_QUOTESUM_QUOTE` (`MASTER_ID`),
  CONSTRAINT `FK_QUOTESUM_QUOTE` FOREIGN KEY (`MASTER_ID`) REFERENCES `TSQ_QUOTE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TSQ_QUOTE_SUM`
--

LOCK TABLES `TSQ_QUOTE_SUM` WRITE;
/*!40000 ALTER TABLE `TSQ_QUOTE_SUM` DISABLE KEYS */;
/*!40000 ALTER TABLE `TSQ_QUOTE_SUM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TVO_GROUP`
--

DROP TABLE IF EXISTS `TVO_GROUP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TVO_GROUP` (
  `ID` bigint(20) NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `CODE` varchar(30) DEFAULT NULL,
  `GROUP_NO` varchar(30) NOT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `TOPIC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TVO_GROUP`
--

LOCK TABLES `TVO_GROUP` WRITE;
/*!40000 ALTER TABLE `TVO_GROUP` DISABLE KEYS */;
/*!40000 ALTER TABLE `TVO_GROUP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TVO_MATCHABLE`
--

DROP TABLE IF EXISTS `TVO_MATCHABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TVO_MATCHABLE` (
  `ID` bigint(20) NOT NULL,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `CCY` varchar(255) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `PROCESS_NO` varchar(30) DEFAULT NULL,
  `REMAINDER` decimal(19,2) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `TOPIC` varchar(255) DEFAULT NULL,
  `TXNDATE` date DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `ACCOUNT_ID` bigint(20) DEFAULT NULL,
  `LOCAL_AMOUNT` decimal(19,2) DEFAULT NULL,
  `LOCAL_REMAINDER` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_MATCH_ACC` (`ACCOUNT_ID`),
  CONSTRAINT `FK_MATCH_ACC` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `TCC_CONTACT` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TVO_MATCHABLE`
--

LOCK TABLES `TVO_MATCHABLE` WRITE;
/*!40000 ALTER TABLE `TVO_MATCHABLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `TVO_MATCHABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TVO_MATCHER`
--

DROP TABLE IF EXISTS `TVO_MATCHER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TVO_MATCHER` (
  `ID` bigint(20) NOT NULL,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `CCY` varchar(255) DEFAULT NULL,
  `FEATURE_BK` varchar(255) DEFAULT NULL,
  `FEATURE` varchar(255) DEFAULT NULL,
  `FEATURE_PK` bigint(20) DEFAULT NULL,
  `MASTER_ID` bigint(20) DEFAULT NULL,
  `LOCAL_AMOUNT` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_MATCHER_MATCH` (`MASTER_ID`),
  CONSTRAINT `FK_MATCHER_MATCH` FOREIGN KEY (`MASTER_ID`) REFERENCES `TVO_MATCHABLE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TVO_MATCHER`
--

LOCK TABLES `TVO_MATCHER` WRITE;
/*!40000 ALTER TABLE `TVO_MATCHER` DISABLE KEYS */;
/*!40000 ALTER TABLE `TVO_MATCHER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TVO_PROCESS`
--

DROP TABLE IF EXISTS `TVO_PROCESS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TVO_PROCESS` (
  `ID` bigint(20) NOT NULL,
  `PROCESS_NO` varchar(30) NOT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `TOPIC` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `ACCOUNT_ID` bigint(20) DEFAULT NULL,
  `COUNTER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PROCESS_ACC` (`ACCOUNT_ID`),
  CONSTRAINT `FK_PROCESS_ACC` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `TCC_CONTACT` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TVO_PROCESS`
--

LOCK TABLES `TVO_PROCESS` WRITE;
/*!40000 ALTER TABLE `TVO_PROCESS` DISABLE KEYS */;
INSERT INTO `TVO_PROCESS` VALUES (602,'PS-000001','OPEN','fat1','SALES',452,0),(603,'PS-000002','OPEN','fat2','SALES',452,0),(1152,'PS-000003','OPEN','fat3','SALES',452,0),(1852,'PS-000004','OPEN','denFat','SALES',452,0);
/*!40000 ALTER TABLE `TVO_PROCESS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TVO_PROCESS_STAKEHOLDER`
--

DROP TABLE IF EXISTS `TVO_PROCESS_STAKEHOLDER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TVO_PROCESS_STAKEHOLDER` (
  `ID` bigint(20) NOT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `CONTACT_ID` bigint(20) DEFAULT NULL,
  `PROCESS_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_STAKE_CONTACT` (`CONTACT_ID`),
  KEY `FK_STAKE_PROCESS` (`PROCESS_ID`),
  CONSTRAINT `FK_STAKE_CONTACT` FOREIGN KEY (`CONTACT_ID`) REFERENCES `TCC_CONTACT` (`ID`),
  CONSTRAINT `FK_STAKE_PROCESS` FOREIGN KEY (`PROCESS_ID`) REFERENCES `TVO_PROCESS` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TVO_PROCESS_STAKEHOLDER`
--

LOCK TABLES `TVO_PROCESS_STAKEHOLDER` WRITE;
/*!40000 ALTER TABLE `TVO_PROCESS_STAKEHOLDER` DISABLE KEYS */;
/*!40000 ALTER TABLE `TVO_PROCESS_STAKEHOLDER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) NOT NULL,
  PRIMARY KEY (`next_val`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-13  8:54:32
