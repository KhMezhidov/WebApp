#!/usr/bin/env bash
set -euo pipefail

CSV_FILE="/seed/co2_data.csv"

if [ ! -f "$CSV_FILE" ]; then
  echo "CSV file not found at $CSV_FILE"
  exit 1
fi

mysql --local-infile=1 -uroot -p"${MYSQL_ROOT_PASSWORD}" "${MYSQL_DATABASE}" <<'SQL'
CREATE TABLE IF NOT EXISTS emissions (
  id BIGINT NOT NULL AUTO_INCREMENT,
  country VARCHAR(255) NOT NULL,
  `year` INT NOT NULL,
  co2_value DOUBLE NULL,
  creator_id INT NULL,
  PRIMARY KEY (id)
);

LOAD DATA LOCAL INFILE '/seed/co2_data.csv'
INTO TABLE emissions
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ';'
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(country, @year, @co2)
SET
  `year` = CAST(NULLIF(TRIM(@year), '') AS UNSIGNED),
  co2_value = NULLIF(TRIM(REPLACE(@co2, '\r', '')), '');
SQL
