<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<jp:mondrianQuery id="query01" jdbcDriver="org.postgresql.Driver" jdbcUrl="jdbc:postgresql://localhost/lastfm" catalogUri="/WEB-INF/queries/Lastfm.xml"
   jdbcUser="dw" jdbcPassword="dw" connectionPooling="false">

WITH MEMBER [Measures].[Efficiency] AS
   Cast(([Measures].[Success Sum] * 1000 / [Measures].[Tagging Count]) as Numeric)
SELECT 
{[Measures].[Success Sum], 
[Measures].[Tagging Count],
[Measures].[Efficiency]}
     ON COLUMNS,
    NON EMPTY{([Time],
    [Artist], 
    [Tag])} ON ROWS
FROM Recommendation 
</jp:mondrianQuery>





<c:set var="title01" scope="session">Test Query issues uses Mondrian OLAP</c:set>
