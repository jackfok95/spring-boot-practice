
ALTER TABLE "user" DROP COLUMN last_login,
ADD COLUMN created_by text,
ADD COLUMN modified_by text;



ALTER TABLE category
DROP COLUMN description;

ALTER TABLE product
DROP COLUMN is_deleted,
ADD COLUMN created_by text,
ADD COLUMN modified_by text;
