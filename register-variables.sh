#!/bin/bash

curl --request PUT localhost:8500/v1/kv/example.app/

curl --request PUT --data WARN localhost:8500/v1/kv/example.app/logging.level.ROOT