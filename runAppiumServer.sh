#!/usr/bin/env bash
kill -9 $(lsof -t -i:4723)
appium --address 127.0.0.1 --port 4723 --chromedriver-executable $(pwd)/src/main/resources/drivers/chromedriver-mac-83
