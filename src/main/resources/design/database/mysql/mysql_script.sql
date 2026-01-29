-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema indi3
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema indi3
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `indi3` DEFAULT CHARACTER SET utf8mb3 ;
USE `indi3` ;

-- -----------------------------------------------------
-- Table `indi3`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indi3`.`customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  `phone_number` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`customer_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `indi3`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indi3`.`category` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `indi3`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indi3`.`product` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `rating` DECIMAL(4,2) NULL DEFAULT NULL,
  `stock` INT NOT NULL,
  `brand` VARCHAR(50) NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`product_id`),
  INDEX `fk_Product_Category_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_Product_Category`
    FOREIGN KEY (`category_id`)
    REFERENCES `indi3`.`category` (`category_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `indi3`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indi3`.`cart` (
  `cart_id` INT NOT NULL,
  `quantity` INT NULL DEFAULT NULL,
  `customer_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`cart_id`, `customer_id`),
  INDEX `fk_Cart_Customer1_idx` (`customer_id` ASC) VISIBLE,
  INDEX `fk_Cart_Product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_Cart_Customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `indi3`.`customer` (`customer_id`),
  CONSTRAINT `fk_Cart_Product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `indi3`.`product` (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `indi3`.`images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indi3`.`images` (
  `image_id` INT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(200) NOT NULL,
  `product_id` INT NOT NULL,
  `thumbnail` TINYINT(1) NOT NULL,
  PRIMARY KEY (`image_id`),
  INDEX `fk_Images_Product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_Images_Product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `indi3`.`product` (`product_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `indi3`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indi3`.`payment` (
  `payment_id` INT NOT NULL AUTO_INCREMENT,
  `payment_date` DATETIME NOT NULL,
  `payment_method` VARCHAR(100) NOT NULL,
  `amount` DECIMAL(10,2) NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`payment_id`),
  INDEX `fk_Payment_Customer1_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_Payment_Customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `indi3`.`customer` (`customer_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `indi3`.`shipment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indi3`.`shipment` (
  `shipment_id` INT NOT NULL AUTO_INCREMENT,
  `shipment_date` DATETIME NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `city` VARCHAR(100) NOT NULL,
  `state` VARCHAR(20) NULL DEFAULT NULL,
  `country` VARCHAR(50) NOT NULL,
  `zip_code` VARCHAR(10) NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`shipment_id`),
  INDEX `fk_Shipment_Customer1_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_Shipment_Customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `indi3`.`customer` (`customer_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `indi3`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indi3`.`order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `order_date` DATETIME NOT NULL,
  `total_price` DECIMAL(10,2) NOT NULL,
  `customer_id` INT NOT NULL,
  `payment_id` INT NOT NULL,
  `shipment_id` INT NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_Order_Customer1_idx` (`customer_id` ASC) VISIBLE,
  INDEX `fk_Order_Payment1_idx` (`payment_id` ASC) VISIBLE,
  INDEX `fk_Order_Shipment1_idx` (`shipment_id` ASC) VISIBLE,
  CONSTRAINT `fk_Order_Customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `indi3`.`customer` (`customer_id`),
  CONSTRAINT `fk_Order_Payment1`
    FOREIGN KEY (`payment_id`)
    REFERENCES `indi3`.`payment` (`payment_id`),
  CONSTRAINT `fk_Order_Shipment1`
    FOREIGN KEY (`shipment_id`)
    REFERENCES `indi3`.`shipment` (`shipment_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `indi3`.`order_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indi3`.`order_item` (
  `order_item_id` INT NOT NULL AUTO_INCREMENT,
  `quantity` INT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `product_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  PRIMARY KEY (`order_item_id`, `order_id`),
  INDEX `fk_Order_Item_Product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_Order_Item_Order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_Order_Item_Order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `indi3`.`order` (`order_id`),
  CONSTRAINT `fk_Order_Item_Product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `indi3`.`product` (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `indi3`.`whislist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indi3`.`whislist` (
  `whislist_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`whislist_id`, `customer_id`),
  INDEX `fk_Whislist_Product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_Whislist_Product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `indi3`.`product` (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
