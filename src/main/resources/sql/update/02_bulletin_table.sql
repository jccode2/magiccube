CREATE TABLE IF NOT EXISTS `1000funs`.`bulletin` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `content` VARCHAR(45) NULL ,
  `sort` INT NULL DEFAULT 0 ,
  `enable` TINYINT(1) NULL DEFAULT true ,
  `deleted` TINYINT(1) NULL DEFAULT false ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;