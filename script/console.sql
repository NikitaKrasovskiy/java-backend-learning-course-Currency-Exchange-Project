CREATE DATABASE CurrencyExchangeDB;

CREATE TABLE currencies
(
    id        SERIAL PRIMARY KEY,           -- Айди валюты, автоинкремент, первичный ключ
    code      VARCHAR(128) UNIQUE NOT NULL, --Код валюты
    full_name VARCHAR(128)        NOT NULL, -- Полное имя валюты
    sign      VARCHAR(128)        NOT NULL  --  Символ валюты
);

DROP TABLE currencies;

CREATE TABLE exchange_rates
(
    id                 SERIAL PRIMARY KEY,                         -- Айди валюты, автоинкремент, первичный ключ
    base_currency_id   INT REFERENCES currencies (id) NOT NULL, --Код валюты
    target_currency_id INT REFERENCES currencies (id) NOT NULL, -- Полное имя валюты
    rate               DECIMAL                        NOT NULL, --  Символ валюты
    UNIQUE (base_currency_id, target_currency_id)
);

DROP TABLE exchange_rates;

INSERT INTO currencies (code, full_name, sign)
VALUES ('AUD', 'Australian dollar', 'A$'),
       ('USD', 'United States dollar', '$'),
       ('EUR', 'Euro', '€');

INSERT INTO exchange_rates (base_currency_id, target_currency_id, rate)
VALUES (1, 2,  0.99);
-- TODO сделал вторую таблицу, исправил наимование таблиц


-- D	int	Айди валюты, автоинкремент, первичный ключ
-- Code	Varchar	Код валюты
-- FullName	Varchar	Полное имя валюты
-- Sign	Varchar	Символ валюты


-- ExchangeRates
-- ID	int	Айди курса обмена, автоинкремент, первичный ключ
-- BaseCurrencyId	int	ID базовой валюты, внешний ключ на Currencies.ID
-- TargetCurrencyId	int	ID целевой валюты, внешний ключ на Currencies.ID
-- Rate	Decimal(6)	Курс обмена единицы базовой валюты к единице целевой валюты

