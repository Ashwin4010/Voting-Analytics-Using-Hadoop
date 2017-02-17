# Compute-Mean-Number-of-voters-Using-Hadoop
Using Hadoop compute the number of voters in each voting district in Russia and the mean number of voters in voting districts in Russia

## How to clone the repository
- git clone git@github.com:rajesh612/Compute-Mean-Using-Hadoop.git

## Tools and Softwares Required
#### Hadoop 2.7.1
#### JDK 1.8 
#### Eclipse IDE

## Steps to open Russiandata jar in IDE
#### Open IDE like Eclipse and import the project from russiandata.jar

## Steps to run Russiandata jar
#### 1. open cmd and type below commands
- >hdfs namenode -format   ####format namenode####
- >cd d:                   #### Hadoop Home directory ####
- >cd sbin
- >start-all.cmd
- >jps
- >cd ..
- >cd bin
#### 2.run the below commands in hadoop/bin path
- > hadoop fs -mkdir /input  ####Creating folders####
- >hadoop fs -mkdir /output

#### 3.Loading file on hadoop
- >hadoop fs -put G:\CSFall2016\BigData\ElectionData\Russia2011\Russia2011_1of2.csv /input
- >hadoop fs -put G:\CSFall2016\BigData\ElectionData\Russia2011\Russia2011_2of2.csv /input

#### 4.Run program
- >hadoop jar C:\Users\Rajesh\workspace\russiandata.jar ElectionDriver /input/Russia2011_1of2.csv /input/Russia2011_2of2.csv /output

#### 5.To stop background processes
- >cd ..
- >cd sbin
- >stop-all.cmd
