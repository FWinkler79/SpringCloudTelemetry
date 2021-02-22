#!/bin/bash
SCRIPT_DIR=$(dirname "$0")
docker-compose -f "$SCRIPT_DIR/prometheus-docker-compose.yml" up -d