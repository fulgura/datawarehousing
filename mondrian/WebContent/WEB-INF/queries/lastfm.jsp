<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<jp:mondrianQuery id="query01" jdbcDriver="org.postgresql.Driver" jdbcUrl="jdbc:postgresql://localhost/lastfm" catalogUri="/WEB-INF/queries/Lastfm.xml"
   jdbcUser="dw" jdbcPassword="dw" connectionPooling="false">
SELECT
    {[Measures].[Tagging Count]} ON COLUMNS,
    {([Artist].[All Artists], [User].[All Users], [Tag].[All Tags])} ON ROWS
FROM Tagging
</jp:mondrianQuery>





<c:set var="title01" scope="session">Test Query issues uses Mondrian OLAP</c:set>