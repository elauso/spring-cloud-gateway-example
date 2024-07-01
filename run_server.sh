#!/bin/bash
docker build -t hello-world -f Dockerfile_server .
docker run -p 8081:8081 hello-world
