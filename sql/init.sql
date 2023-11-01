CREATE TABLE IF NOT EXISTS user (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL,
                                    address VARCHAR(255),
                                    email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS product (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
                                       price INT NOT NULL,
                                       category VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `order` (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       user_id BIGINT NOT NULL,
                                       product_id BIGINT NOT NULL,
                                       FOREIGN KEY (user_id) REFERENCES user(id),
                                       FOREIGN KEY (product_id) REFERENCES product(id)
);

INSERT INTO user (name, address, email) VALUES ('침팬지', '욱욱악악', 'email1@example.com');
INSERT INTO user (name, address, email) VALUES ('고릴라', '끾끾', 'go@example.com');

-- 한식
INSERT INTO product (name, price, category) VALUES ('불고기', 15000, '한식');
INSERT INTO product (name, price, category) VALUES ('김치찌개', 8000, '한식');
INSERT INTO product (name, price, category) VALUES ('비빔밥', 9000, '한식');
INSERT INTO product (name, price, category) VALUES ('갈비탕', 16000, '한식');
INSERT INTO product (name, price, category) VALUES ('떡볶이', 7000, '한식');
INSERT INTO product (name, price, category) VALUES ('냉면', 8500, '한식');
INSERT INTO product (name, price, category) VALUES ('삼계탕', 17000, '한식');
INSERT INTO product (name, price, category) VALUES ('부대찌개', 8500, '한식');
INSERT INTO product (name, price, category) VALUES ('된장찌개', 7500, '한식');
INSERT INTO product (name, price, category) VALUES ('순대국', 7000, '한식');
INSERT INTO product (name, price, category) VALUES ('곰탕', 18000, '한식');
INSERT INTO product (name, price, category) VALUES ('뼈해장국', 9000, '한식');
INSERT INTO product (name, price, category) VALUES ('콩나물국밥', 8500, '한식');
INSERT INTO product (name, price, category) VALUES ('돌솥비빔밥', 12000, '한식');
INSERT INTO product (name, price, category) VALUES ('해물파전', 10000, '한식');
INSERT INTO product (name, price, category) VALUES ('무생채', 6500, '한식');
INSERT INTO product (name, price, category) VALUES ('야채전', 7500, '한식');
INSERT INTO product (name, price, category) VALUES ('계란찜', 6000, '한식');
INSERT INTO product (name, price, category) VALUES ('오징어볶음', 11000, '한식');
INSERT INTO product (name, price, category) VALUES ('된장찌개', 7500, '한식');
INSERT INTO product (name, price, category) VALUES ('순대국', 7000, '한식');
INSERT INTO product (name, price, category) VALUES ('곰탕', 18000, '한식');
INSERT INTO product (name, price, category) VALUES ('뼈해장국', 9000, '한식');
INSERT INTO product (name, price, category) VALUES ('콩나물국밥', 8500, '한식');
INSERT INTO product (name, price, category) VALUES ('돌솥비빔밥', 12000, '한식');
INSERT INTO product (name, price, category) VALUES ('해물파전', 10000, '한식');
INSERT INTO product (name, price, category) VALUES ('무생채', 6500, '한식');
INSERT INTO product (name, price, category) VALUES ('야채전', 7500, '한식');
INSERT INTO product (name, price, category) VALUES ('계란찜', 6000, '한식');
INSERT INTO product (name, price, category) VALUES ('오징어볶음', 11000, '한식');


-- 중식
INSERT INTO product (name, price, category) VALUES ('짜장면', 5000, '중식');
INSERT INTO product (name, price, category) VALUES ('짬뽕', 6000, '중식');
INSERT INTO product (name, price, category) VALUES ('탕수육', 12000, '중식');
INSERT INTO product (name, price, category) VALUES ('양장피', 7000, '중식');
INSERT INTO product (name, price, category) VALUES ('마파두부', 8000, '중식');
INSERT INTO product (name, price, category) VALUES ('군만두', 5500, '중식');
INSERT INTO product (name, price, category) VALUES ('사천짜장', 7500, '중식');
INSERT INTO product (name, price, category) VALUES ('고추잡채', 8500, '중식');
INSERT INTO product (name, price, category) VALUES ('갈비탕', 15000, '중식');
INSERT INTO product (name, price, category) VALUES ('유린기', 11000, '중식');
INSERT INTO product (name, price, category) VALUES ('해물짜장', 12000, '중식');
INSERT INTO product (name, price, category) VALUES ('깐풍기', 10000, '중식');
INSERT INTO product (name, price, category) VALUES ('마라탕', 13000, '중식');
INSERT INTO product (name, price, category) VALUES ('짜장밥', 9000, '중식');
INSERT INTO product (name, price, category) VALUES ('짬뽕밥', 10000, '중식');
INSERT INTO product (name, price, category) VALUES ('꿔바로우', 12000, '중식');
INSERT INTO product (name, price, category) VALUES ('찹쌀탕수육', 14000, '중식');
INSERT INTO product (name, price, category) VALUES ('마라샹궈', 11000, '중식');
INSERT INTO product (name, price, category) VALUES ('팔보채', 9000, '중식');
INSERT INTO product (name, price, category) VALUES ('잡채밥', 9500, '중식');

-- 일식
INSERT INTO product (name, price, category) VALUES ('초밥', 20000, '일식');
INSERT INTO product (name, price, category) VALUES ('라멘', 8000, '일식');
INSERT INTO product (name, price, category) VALUES ('돈까스', 10000, '일식');
INSERT INTO product (name, price, category) VALUES ('우동', 7500, '일식');
INSERT INTO product (name, price, category) VALUES ('사시미', 18000, '일식');
INSERT INTO product (name, price, category) VALUES ('텐동', 8500, '일식');
INSERT INTO product (name, price, category) VALUES ('야끼소바', 9000, '일식');
INSERT INTO product (name, price, category) VALUES ('스시세트', 22000, '일식');
INSERT INTO product (name, price, category) VALUES ('규동', 11000, '일식');
INSERT INTO product (name, price, category) VALUES ('데리야끼', 12000, '일식');
INSERT INTO product (name, price, category) VALUES ('오야끼', 7500, '일식');
INSERT INTO product (name, price, category) VALUES ('사케동', 13000, '일식');
INSERT INTO product (name, price, category) VALUES ('테리야키', 9000, '일식');
INSERT INTO product (name, price, category) VALUES ('참치덮밥', 9500, '일식');
INSERT INTO product (name, price, category) VALUES ('연어덮밥', 10000, '일식');
INSERT INTO product (name, price, category) VALUES ('스시롤', 12000, '일식');
INSERT INTO product (name, price, category) VALUES ('새우튀김', 8500, '일식');
INSERT INTO product (name, price, category) VALUES ('카레라이스', 11000, '일식');
INSERT INTO product (name, price, category) VALUES ('와카메샐러드', 7000, '일식');
INSERT INTO product (name, price, category) VALUES ('마끼', 13000, '일식');
INSERT INTO product (name, price, category) VALUES ('유동', 9000, '일식');


INSERT INTO `tb_order` (user_id, product_id) VALUES (1, 1);
INSERT INTO `tb_order` (user_id, product_id) VALUES (1, 2);
INSERT INTO `tb_order` (user_id, product_id) VALUES (1, 3);
INSERT INTO `tb_order` (user_id, product_id) VALUES (1, 20);
INSERT INTO `tb_order` (user_id, product_id) VALUES (1, 50);
INSERT INTO `tb_order` (user_id, product_id) VALUES (1, 12);
INSERT INTO `tb_order` (user_id, product_id) VALUES (1, 22);
INSERT INTO `tb_order` (user_id, product_id) VALUES (1, 32);
INSERT INTO `tb_order` (user_id, product_id) VALUES (1, 11);
INSERT INTO `tb_order` (user_id, product_id) VALUES (1, 6);

