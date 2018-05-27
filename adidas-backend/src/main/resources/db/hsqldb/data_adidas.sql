INSERT INTO book_store VALUES ('1', '新华书店', '湖北省武汉市洪山区文秀街131号');

INSERT INTO book VALUES ('1', '1', '社会研究方法教程', '袁方', '68.00', '社会学', '2015-03-01');
INSERT INTO book VALUES ('2', '1', '算法', '高德纳', '108.00', '数据结构', '2014-02-13');
INSERT INTO book VALUES ('3', '1', 'Java核心技术Ⅰ', 'Cay', '93.00', '编程语言', '2011-06-14');
INSERT INTO book VALUES ('4', '1', '现代操作系统', 'William', '56.50', '操作系统', '2016-08-23');
INSERT INTO book VALUES ('5', '1', 'Head First设计模式', 'Freeman', '32.00', '设计模式', '2013-10-15');
INSERT INTO book VALUES ('6', '1', '学习OpenCV', 'Bradski', '46.00', '技术', '2014-02-13');
INSERT INTO book VALUES ('7', '1', '小王子', '周克希', '15.00', '文学', '2008-07-13');
INSERT INTO book VALUES ('8', '1', 'Effective Java', 'Bloch', '38.00', '编程语言', '2014-12-03');
INSERT INTO book VALUES ('9', '1', '编程珠玑', 'Jon', '36.00', '数据结构', '2013-12-03');
INSERT INTO book VALUES ('10', '1', 'SQL必知必会', 'Ben', '13.00', '数据库', '2015-08-26');
INSERT INTO book VALUES ('11', '1', '编译器设计', 'Kelth', '59.00', '编译器', '2014-08-13');

insert into activity (ID_ACTIVITY,ACTIVITY) values(1,'Tennis');
insert into activity (ID_ACTIVITY,ACTIVITY) values(2,'Running');
insert into activity (ID_ACTIVITY,ACTIVITY) values(3,'Football');
insert into activity (ID_ACTIVITY,ACTIVITY) values(4,'Cycling');
insert into activity (ID_ACTIVITY,ACTIVITY) values(5,'Basketball');

insert into sensor_type (ID_SENSOR_TYPE, SENSOR_TYPE) values (1, 'Left foot');
insert into sensor_type (ID_SENSOR_TYPE, SENSOR_TYPE) values (2, 'Right foot');

insert into user (ID_USER, USER_NAME, PASSWORD) values (1, 'Error404', 'Error404');

insert into product (id_product,product,weburl,imgurl,prize,id_activity) values (1,'ZAPATILLA CITY CUP','https://www.adidas.es/zapatilla-city-cup/CQ1081.html','
https://www.adidas.es/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dwcc8a748e/zoom/CQ1081_01_standard.jpg', 89.95, 1);

insert into product (id_product,product,weburl,imgurl,prize,id_activity) values (2,'ZAPATILLA BUSENITZ VULC RX','https://www.adidas.es/zapatilla-busenitz-vulc-rx/CQ1172.html','
https://www.adidas.es/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dwaa3887aa/zoom/CQ1172_01_standard.jpg', 89.95, 2);

insert into product (id_product,product,weburl,imgurl,prize,id_activity) values (3,'ZAPATILLA BUSENITZ PRO','https://www.adidas.es/zapatilla-busenitz-pro/G48060.html','
https://www.adidas.es/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw68c131fb/zoom/G48060_01_standard.jpg', 89.95, 3);

insert into product (id_product,product,weburl,imgurl,prize,id_activity) values (4,'ZAPATILLA LUCAS PREMIERE','https://www.adidas.es/zapatilla-lucas-premiere/CQ1104.html','
https://www.adidas.es/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dw547e8d42/zoom/CQ1104_01_standard.jpg', 99.95, 4);

insert into product (id_product,product,weburl,imgurl,prize,id_activity) values (5,'ZAPATILLA POWER PERFECT 3','https://www.adidas.es/zapatilla-power-perfect-3/DA9882.html','
https://www.adidas.es/dis/dw/image/v2/aagl_prd/on/demandware.static/-/Sites-adidas-products/default/dwe5e7617e/zoom/DA9882_01_standard.jpg', 119.95, 5);
commit;