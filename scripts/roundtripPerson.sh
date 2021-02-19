#!/bin/bash

curl -i -X POST -H "Content-Type:application/json" -d "{  \"firstName\" : \"Alan\",  \"lastName\" : \"Turing\" }" http://localhost:8001/custom-span
