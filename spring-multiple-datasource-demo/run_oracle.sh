#!/bin/bash


docker run -itd --name myoracle -p 1521:1521 -e ORACLE_PWD=oracle  container-registry.oracle.com/database/free:latest

