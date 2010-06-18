ALTER TABLE `broker` CHANGE itemCount itemCount bigint(20) NOT NULL;
ALTER TABLE `broker` CHANGE price price bigint(20) NOT NULL DEFAULT '0';
ALTER TABLE `inventory` CHANGE itemCount itemCount bigint(20) NOT NULL default '0';
ALTER TABLE `mail` CHANGE attachedKinahCount attachedKinahCount bigint(20) NOT NULL;