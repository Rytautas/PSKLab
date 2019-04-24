CREATE TABLE IF NOT EXISTS SHOP
(
  ID INTEGER NOT NULL IDENTITY,
  NAME VARCHAR(30),
  OPT_LOCK_VERSION INTEGER,
  PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS ITEM
(
  ID INTEGER NOT NULL IDENTITY,
  NAME VARCHAR(30),
  PRICE FLOAT,
  OPT_LOCK_VERSION INTEGER,
  PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS COURIER
(
  ID INTEGER NOT NULL IDENTITY,
  NAME VARCHAR(30),
  SHOP_ID INTEGER,
  SPEED INTEGER,
  OPT_LOCK_VERSION INTEGER,
  PRIMARY KEY (ID),
  FOREIGN KEY (SHOP_ID) REFERENCES SHOP
);

CREATE TABLE IF NOT EXISTS SHOPITEM
(
  SHOP_ID INTEGER,
  ITEM_ID INTEGER,
  PRIMARY KEY (SHOP_ID, ITEM_ID),
  FOREIGN KEY (SHOP_ID) REFERENCES SHOP,
  FOREIGN KEY (ITEM_ID) REFERENCES ITEM
)