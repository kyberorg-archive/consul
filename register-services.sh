#!/bin/bash

curl -s -XPUT -d"{
  \"Name\": \"postgres\",
  \"ID\": \"postgres\",
  \"Tags\": [ \"postgres\" ],
  \"Address\": \"localhost\",
  \"Port\": 5432,
  \"Check\": {
    \"Name\": \"PostgreSQL TCP on port 5432\",
    \"ID\": \"postgres\",
    \"Interval\": \"10s\",
    \"TCP\": \"postgres:5432\",
    \"Timeout\": \"1s\",
    \"Status\": \"passing\"
  }
}" localhost:8500/v1/agent/service/register

curl -s -XPUT -d"{
  \"Name\": \"browserless\",
  \"ID\": \"browserless\",
  \"Tags\": [ \"browserless\" ],
  \"Address\": \"localhost\",
  \"Port\": 3000,
  \"Check\": {
    \"Name\": \"Browserless TCP on port 3000\",
    \"ID\": \"browserless\",
    \"Interval\": \"10s\",
    \"TCP\": \"browserless:3000\",
    \"Timeout\": \"1s\",
    \"Status\": \"passing\"
  }
}" localhost:8500/v1/agent/service/register

curl -s -XPUT -d"{
  \"Name\": \"example.app\",
  \"ID\": \"example.app\",
  \"Tags\": [ \"example.app\" ],
  \"Address\": \"localhost\",
  \"Port\": 8080,
  \"Check\": {
    \"Name\": \"example.app HTTP on port 8080\",
    \"ID\": \"example.app\",
    \"Interval\": \"10s\",
    \"HTTP\": \"example.app:8080/actuator/health\",
    \"Timeout\": \"1s\",
    \"Status\": \"passing\"
  }
}" localhost:8500/v1/agent/service/register
