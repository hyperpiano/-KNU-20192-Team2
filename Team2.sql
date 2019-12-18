/*원래 존재하던 company 데이터베이스 삭제*/
drop database company;

/*새로운 company 데이터베이스 생성*/
create database company;

/*company database 사용*/
use company;

/*employee table 생성 : utf-8을 기본으로 함*/
create table employee(
name varchar(5) /*이름은 최대 4글자*/,
employeeid varchar(9) /*8자의 id*/, 
birthdate date,
department varchar(11) /*최대 10자*/,
phonenum varchar(12) /* 010-XXXX-XXXX이므로 11자*/,
address varchar(101) /*최대 100자*/,
mail varchar(51) /*최대 50자*/,
salary double,
position varchar(11) /*최대 10자*/,
score double) default character set utf8 collate utf8_general_ci;

/*employee 내부구조 확인*/
desc employee;