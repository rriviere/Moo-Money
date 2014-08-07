drop table user_roles;
drop table users;
drop table files;

CREATE TABLE users (
  username VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  firstname VARCHAR(45) NOT NULL,
  lastname VARCHAR(45) NOT NULL,
  project VARCHAR(45) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  CONSTRAINT pk_users PRIMARY KEY (username,project));
  
CREATE TABLE user_roles (
  	user_role_id INT(11) NOT NULL AUTO_INCREMENT,
  	username VARCHAR(45) NOT NULL,
  	project VARCHAR(45) NOT NULL,
  	role VARCHAR(45) NOT NULL,
  CONSTRAINT pk_user_roles PRIMARY KEY (user_role_id),
  CONSTRAINT uk_role_username_proj UNIQUE (role,username,project),
  CONSTRAINT fk_user_role_user FOREIGN KEY (username,project) REFERENCES users (username,project));
  
CREATE TABLE file (
  	file_id mediumint(10) unsigned NOT NULL AUTO_INCREMENT,
  	filename varchar(100) NOT NULL,
  	file_notes varchar(100) DEFAULT NULL,
  	file_size mediumint(10) DEFAULT NULL,
  	file_type varchar(40) DEFAULT NULL,
  	file longblob DEFAULT NULL,
  CONSTRAINT pk_file PRIMARY KEY (file_id)
);

CREATE TABLE IF NOT EXISTS `etags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_code` varchar(100) NOT NULL,
  `item_description` varchar(500) NOT NULL,
  `btn_type` enum('primary','important','success','default','warning') NOT NULL DEFAULT 'default',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;





  
INSERT INTO users(username,password,firstname,lastname,project,enabled) VALUES ('richard','d8d7b0944cf2b88336c9afe487329939','richard','riviere','all',TRUE);
INSERT INTO users(username,password,firstname,lastname,project,enabled) VALUES ('ariane','6ae199a93c381bf6d5de27491139d3f9','ariane','riviere','all',TRUE); 
INSERT INTO user_roles (username,project,role) VALUES ('richard','all','ROLE_LEVEL1');
INSERT INTO user_roles (username,project,role) VALUES ('ariane','all','ROLE_LEVEL1');
INSERT INTO `etags` (`id`, `item_code`, `item_description`, `btn_type`) VALUES
(1, 'Mel', 'Melbourne', 'default'),
(2, 'Syd', 'Sydney', 'default');


commit;
