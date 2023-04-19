CREATE DATABASE CurrencyExchangeDB;

CREATE TABLE currencies
(
    id       SERIAL PRIMARY KEY, -- Айди валюты, автоинкремент, первичный ключ
    code     VARCHAR(128) UNIQUE NOT NULL, --Код валюты
    fullName VARCHAR(128)        NOT NULL, -- Полное имя валюты
    Sign     VARCHAR(128)        NOT NULL  --  Символ валюты
);

DROP TABLE  currencies;


-- D	int	Айди валюты, автоинкремент, первичный ключ
-- Code	Varchar	Код валюты
-- FullName	Varchar	Полное имя валюты
-- Sign	Varchar	Символ валюты


-- ExchangeRates
-- ID	int	Айди курса обмена, автоинкремент, первичный ключ
-- BaseCurrencyId	int	ID базовой валюты, внешний ключ на Currencies.ID
-- TargetCurrencyId	int	ID целевой валюты, внешний ключ на Currencies.ID
-- Rate	Decimal(6)	Курс обмена единицы базовой валюты к единице целевой валюты

