create database stock_prediction;

use stock_prediction;

# 历史行情表
CREATE TABLE history_market
(
    id                            int PRIMARY KEY AUTO_INCREMENT,
    code                          VARCHAR(10) ,
    time                          VARCHAR(20),
    pre_close                     DOUBLE,
    open                          DOUBLE,
    high                          DOUBLE,
    low                           DOUBLE,
    close                         DOUBLE,
    avg_price                     DOUBLE,
    changes                       DOUBLE,
    change_ratio                  DOUBLE,
    volume                        BIGINT,
    amount                        DOUBLE,
    turnover_ratio                DOUBLE,
    transaction_amount            BIGINT,
    total_shares                  BIGINT,
    total_capital                 DOUBLE,
    float_shares_of_a_shares      BIGINT,
    float_shares_of_b_shares      BIGINT,
    float_capital_of_a_shares     DOUBLE,
    float_capital_of_b_shares     DOUBLE,
    pe_ttm                        DOUBLE,
    pe                            DOUBLE,
    pb                            DOUBLE,
    ps                            DOUBLE,
    pcf                           DOUBLE,
    ths_trading_status_stock      VARCHAR(10),
    ths_up_and_down_status_stock  VARCHAR(10),
    ths_af_stock                  DOUBLE,
    ths_vol_after_trading_stock   BIGINT,
    ths_trans_num_after_trading_stock BIGINT,
    ths_amt_after_trading_stock   DOUBLE,
    ths_valid_turnover_stock      DOUBLE,
    UNIQUE KEY (time),
    INDEX idx_time(time)
);

drop table history_market;


