#!/usr/bin/env bash
openssl req -x509 -newkey rsa:4096 -keyout key-with-password.pem -out cert.pem -days 365
openssl rsa -in key-with-password.pem -out key-without-password.pem