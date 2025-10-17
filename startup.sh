#!/bin/bash
#rm -rf allure-results/*
mvn clean test -DsuiteXmlFile=test-suites/testng.xml
allure generate allure-results --clean -o allure-report
