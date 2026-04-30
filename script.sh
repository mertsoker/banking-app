#!/bin/bash
cd /home/ubuntu
docker pull mertsoker/banking-app:latest
docker-compose down
docker-compose -f docker-compose.yml up -d