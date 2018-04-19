该项目请和standardMR，ReductionTest配合使用
这两个项目负责打mr的jar包，里面的hdfs路径和包名等都要和该项目一致，打包放在WEB-INF/classes/jars中

项目数据放在resource中，归约前为test1.log和test2.log

使用hadoop要配合修改core-site.xml,hdfs-site.xml,mapred-site.xml,yarn-site.xml,使用mysql要配合修改db.properties，这些文件同在resource中