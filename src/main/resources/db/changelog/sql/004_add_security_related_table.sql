CREATE TABLE authority (
                        name                    text            ,
                        PRIMARY KEY (name)
);

CREATE TABLE user_authority(
                      id                      SERIAL                  ,
                      user_id                 BIGINT                  ,
                      authority               text NOT NULL         ,
                      PRIMARY KEY (id),
                      FOREIGN KEY (user_id)     REFERENCES "user" (id) ON DELETE SET NULL ,
                      FOREIGN KEY (authority)   REFERENCES authority (name)
);
