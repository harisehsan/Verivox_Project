#! /usr/bin/bash
mvn clean test -Dcucumber.options="--tags @address_check" allure:serve