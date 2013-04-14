SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `1000funs` ;
CREATE SCHEMA IF NOT EXISTS `1000funs` DEFAULT CHARACTER SET utf8 ;
USE `1000funs` ;

-- -----------------------------------------------------
-- Table `1000funs`.`shop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `1000funs`.`shop` ;

CREATE  TABLE IF NOT EXISTS `1000funs`.`shop` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `code` VARCHAR(45) NULL ,
  `shop_name` VARCHAR(45) NULL ,
  `region_id` INT NULL ,
  `manager_id` VARCHAR(45) NULL ,
  `detail_address` VARCHAR(45) NULL ,
  `deleted` TINYINT(1) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `1000funs`.`food`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `1000funs`.`food` ;

CREATE  TABLE IF NOT EXISTS `1000funs`.`food` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `code` VARCHAR(45) NULL ,
  `food_name` VARCHAR(45) NULL ,
  `detail` VARCHAR(400) NULL ,
  `image` VARCHAR(400) NULL ,
  `type` INT NULL COMMENT '1 food , 2 package' ,
  `deleted` TINYINT(1) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `1000funs`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `1000funs`.`users` ;

CREATE  TABLE IF NOT EXISTS `1000funs`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `code` VARCHAR(45) NULL ,
  `user_name` VARCHAR(45) NULL ,
  `phone` VARCHAR(45) NULL ,
  `email` VARCHAR(45) NULL ,
  `password` VARCHAR(200) NULL ,
  `score` INT NULL ,
  `quota` DOUBLE NULL ,
  `default_region_id` INT NULL ,
  `default_address` VARCHAR(200) NULL ,
  `register_time` VARCHAR(45) NULL ,
  `user_type` INT NULL COMMENT '0:consumer\\n1:employee\\n2:manager\\n3:admin' ,
  `deleted` TINYINT(1) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `1000funs`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `1000funs`.`orders` ;

CREATE  TABLE IF NOT EXISTS `1000funs`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `code` VARCHAR(45) NULL ,
  `shop_id` INT NULL ,
  `create_time` VARCHAR(45) NULL ,
  `except_time` VARCHAR(45) NULL ,
  `user_id` INT NULL ,
  `user_remark` VARCHAR(45) NULL COMMENT 'user remark' ,
  `address` VARCHAR(200) NULL ,
  `contact` VARCHAR(45) NULL COMMENT 'who will accept the food.' ,
  `phone` VARCHAR(45) NULL ,
  `order_status` INT NULL COMMENT '0:new\\n1:dealed\\n2:exception\\n3:evaluated\\n' ,
  `manager_id` INT NULL ,
  `manager_remark` VARCHAR(45) NULL ,
  `sender_id` INT NULL ,
  `payment_type` INT NULL COMMENT '0: cash\\n1: online' ,
  `total_price` DOUBLE NULL COMMENT 'current total price' ,
  `deleted` TINYINT(1) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `1000funs`.`evaluate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `1000funs`.`evaluate` ;

CREATE  TABLE IF NOT EXISTS `1000funs`.`evaluate` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `order_id` INT NULL ,
  `speed` INT NULL ,
  `taste` INT NULL ,
  `service` INT NULL ,
  `detail` VARCHAR(200) NULL ,
  `evaluate_time` VARCHAR(45) NULL ,
  `deleted` TINYINT(1) NULL DEFAULT false ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `1000funs`.`food_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `1000funs`.`food_group` ;

CREATE  TABLE IF NOT EXISTS `1000funs`.`food_group` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `group_name` VARCHAR(45) NULL ,
  `image` VARCHAR(200) NULL ,
  `detail` VARCHAR(200) NULL ,
  `type` INT NULL COMMENT '1 food , 2 package' ,
  `deleted` TINYINT(1) NULL DEFAULT false ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `1000funs`.`package_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `1000funs`.`package_item` ;

CREATE  TABLE IF NOT EXISTS `1000funs`.`package_item` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `package_id` INT NULL ,
  `food_id` INT NULL ,
  `deleted` TINYINT(1) NULL DEFAULT false ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `1000funs`.`order_plan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `1000funs`.`order_plan` ;

CREATE  TABLE IF NOT EXISTS `1000funs`.`order_plan` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `code` VARCHAR(45) NULL ,
  `shop_id` INT NULL ,
  `user_id` INT NULL ,
  `except_time` VARCHAR(45) NULL ,
  `user_remark` VARCHAR(45) NULL ,
  `rule_id` INT NULL COMMENT 'everyday , the matched rules will generate order' ,
  `create_time` VARCHAR(45) NULL ,
  `end_time` VARCHAR(45) NULL ,
  `deleted` TINYINT(1) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `1000funs`.`order_plan_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `1000funs`.`order_plan_item` ;

CREATE  TABLE IF NOT EXISTS `1000funs`.`order_plan_item` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `plan_id` INT NULL ,
  `item_type` INT NULL COMMENT '0 food , 1 package' ,
  `item_id` INT NULL COMMENT 'food or package\\\'s id' ,
  `amount` INT NULL ,
  `deleted` TINYINT(1) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `1000funs`.`region`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `1000funs`.`region` ;

CREATE  TABLE IF NOT EXISTS `1000funs`.`region` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `code` VARCHAR(45) NULL ,
  `region_name` VARCHAR(45) NULL ,
  `parent_id` INT NULL ,
  `image` VARCHAR(400) NULL ,
  `has_children` TINYINT(1) NULL ,
  `full_path` VARCHAR(200) NULL ,
  `deleted` TINYINT(1) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `1000funs`.`food_re_shop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `1000funs`.`food_re_shop` ;

CREATE  TABLE IF NOT EXISTS `1000funs`.`food_re_shop` (
  `shop_id` INT NOT NULL ,
  `food_id` INT NOT NULL ,
  `group_id` INT NULL ,
  `origin_price` DOUBLE NULL ,
  `current_price` DOUBLE NULL ,
  `stock` INT NULL ,
  `droped` TINYINT(1) NULL ,
  `deleted` TINYINT(1) NULL ,
  PRIMARY KEY (`shop_id`, `food_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `1000funs`.`plan_rule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `1000funs`.`plan_rule` ;

CREATE  TABLE IF NOT EXISTS `1000funs`.`plan_rule` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `rule_name` VARCHAR(45) NULL ,
  `detail` VARCHAR(45) NULL ,
  `deleted` TINYINT(1) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `1000funs`.`order_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `1000funs`.`order_item` ;

CREATE  TABLE IF NOT EXISTS `1000funs`.`order_item` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `order_id` INT NULL ,
  `item_id` INT NULL COMMENT 'food or package\\\'s id' ,
  `amount` INT NULL ,
  `plate` INT NULL DEFAULT 1 ,
  `deleted` TINYINT(1) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `1000funs`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `1000funs`.`address` ;

CREATE  TABLE IF NOT EXISTS `1000funs`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `short_name` VARCHAR(45) NULL ,
  `full_name` VARCHAR(45) NULL ,
  `image` VARCHAR(400) NULL ,
  `user_ip` VARCHAR(45) NULL ,
  `user_id` INT NULL ,
  `input_time` VARCHAR(45) NULL ,
  `deleted` TINYINT(1) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `1000funs`.`temp_address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `1000funs`.`temp_address` ;

CREATE  TABLE IF NOT EXISTS `1000funs`.`temp_address` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `short_name` VARCHAR(45) NULL ,
  `full_name` VARCHAR(45) NULL ,
  `image` VARCHAR(400) NULL ,
  `user_ip` VARCHAR(45) NULL ,
  `user_id` INT NULL ,
  `input_time` VARCHAR(45) NULL ,
  `deleted` TINYINT(1) NULL ,
  `temp_addresscol` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `1000funs`.`config`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `1000funs`.`config` ;

CREATE  TABLE IF NOT EXISTS `1000funs`.`config` (
  `name` VARCHAR(100) NOT NULL ,
  `value` VARCHAR(1000) NULL ,
  `class_name` VARCHAR(100) NULL ,
  PRIMARY KEY (`name`) )
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
