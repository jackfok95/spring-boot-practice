
CREATE TABLE "user"(
                         id                SERIAL ,
                         username          text,
                         password          text,
                         name              text,
                         email             text,
                         date_of_birth     date,
                         address           text,
                         nation            text,
                         last_login        TIMESTAMP without time zone DEFAULT now(),
                         enabled           BOOLEAN DEFAULT TRUE,
                         created_at        TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
                         updated_at        TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
                         PRIMARY KEY (id)
);

CREATE TABLE category (
                                      id                      SERIAL          ,
                                      name                    text            ,
                                      description             text            ,
                                      PRIMARY KEY (id)
);

-- set product's user_id to null and keep it, if user is deleted
CREATE TABLE product(
                            id                      SERIAL                  ,
                            user_id                 BIGINT                  ,
                            category_id             BIGINT NOT NULL         ,
                            name                    text                    ,
                            description             text                    ,
                            qty                     BIGINT                  ,
                            price                   numeric(8,2)            ,
                            is_sold_out             BOOLEAN DEFAULT FALSE   ,
                            is_deleted              BOOLEAN DEFAULT FALSE   ,
                            created_at              TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
                            updated_at              TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
                            PRIMARY KEY (id),
                            FOREIGN KEY (user_id)     REFERENCES "user" (id) ON DELETE SET NULL ,
                            FOREIGN KEY (category_id) REFERENCES category (id)
);