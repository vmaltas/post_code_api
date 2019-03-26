CREATE DATABASE `ukpostcode` DEFAULT CHARSET utf8;

GRANT ALL ON ukpostcode.* TO 'ukpostcodeuser'@'localhost' IDENTIFIED BY 'ABC123!.';

USE ukpostcode;

CREATE TABLE `post_code` (
  `id` int(11) NOT NULL,
  `postcode` varchar(45) NOT NULL,
  `latitude` decimal(10,8) NOT NULL,
  `longitude` decimal(11,8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `postcode_UNIQUE` (`postcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


