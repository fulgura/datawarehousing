 java -cp "/lib/mondrian.jar; /lib/log4j.jar; /lib/commons-logging.jar; /lib/eigenbase-xom.jar; /lib/eigenbase-resgen.jar; /lib/eigenbase-properties.jar; /lib/postgres-jdbc.jar"
     mondrian.test.loader.MondrianFoodMartLoader
     -verbose -tables -data -indexes
     -jdbcDrivers="org.postgresql.Driver,sun.jdbc.odbc.JdbcOdbcDriver"
     -inputJdbcURL="jdbc:odbc:MondrianFoodMart"
     -outputJdbcURL="jdbc:postgresql://localhost/foodmart"
     -outputJdbcUser=postgres
     -outputJdbcPassword=postgres