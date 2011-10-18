<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<jp:mondrianQuery id="query01" jdbcDriver="org.postgresql.Driver" jdbcUrl="jdbc:postgresql://127.0.01/mondrian" catalogUri="/WEB-INF/queries/IssueSchema.xml"
   jdbcUser="postgres" jdbcPassword="postgres" connectionPooling="false">
SELECT
    Type.Children ON COLUMNS,
    Assignee.Children ON ROWS
FROM Issue
</jp:mondrianQuery>





<c:set var="title01" scope="session">Test Query issues uses Mondrian OLAP</c:set>
