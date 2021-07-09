CREATE TABLE `tbl_product` (
	`product_id` int NOT NULL AUTO_INCREMENT,
	`product_name` varchar(255) NOT NULL,
	`product_size` int NOT NULL DEFAULT 'null',
	`product_color` int NOT NULL DEFAULT 'null',
	PRIMARY KEY (`product_id`)
);

CREATE TABLE `tbl_options` (
	`option_id` int NOT NULL AUTO_INCREMENT,
	`variant_id` int NOT NULL,
	`option_value` varchar(255) NOT NULL,
	PRIMARY KEY (`option_id`)
);

CREATE TABLE `tbl_variant` (
	`variant_id` int NOT NULL AUTO_INCREMENT,
	`variant_name` varchar(255) NOT NULL,
	PRIMARY KEY (`variant_id`)
);

ALTER TABLE `tbl_product` ADD CONSTRAINT `tbl_product_fk0` FOREIGN KEY (`product_size`) REFERENCES `tbl_options`(`option_id`);

ALTER TABLE `tbl_product` ADD CONSTRAINT `tbl_product_fk1` FOREIGN KEY (`product_color`) REFERENCES `tbl_options`(`option_id`);

ALTER TABLE `tbl_options` ADD CONSTRAINT `tbl_options_fk0` FOREIGN KEY (`variant_id`) REFERENCES `tbl_variant`(`variant_id`);

