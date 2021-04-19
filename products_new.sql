BEGIN;
DROP TABLE IF EXISTS products_new CASCADE;
CREATE TABLE products_new (id bigserial PRIMARY KEY, title VARCHAR(255), cost int);
COMMIT;