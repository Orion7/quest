--liquibase formatted sql

--changeset rinat-s:add persons

CREATE TABLE persons (
  id        SERIAL,
  name      VARCHAR(255),
  alco_type VARCHAR(255),
  soft_type VARCHAR(255),
  drink_id  integer
)
