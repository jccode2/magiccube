-- V_SHOP_FOOD : A shop's available food
CREATE OR REPLACE VIEW V_SHOP_FOOD AS
SELECT fs.shop_id AS shop_id,
       fs.food_id AS food_id,
       fs.group_id AS group_id,
       f.code AS food_code,
       f.food_name AS food_name,
       f.detail AS food_detail,
       f.image AS food_image,
       f.type AS food_type,
       g.group_name as group_name, 
       g.image as group_image, 
       g.detail as group_detail, 
       g.type as group_type, 
       g.sort as group_sort, 
       fs.origin_price AS origin_price,
       fs.current_price AS current_price,
       fs.stock AS stock,
       fs.droped AS droped
FROM food f
      JOIN food_re_shop fs on f.id = fs.food_id
      JOIN food_group g on g.id = fs.group_id 
WHERE f.deleted = 0
       AND fs.deleted = 0
       AND g.deleted = 0;
       