<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

<head>

  <meta charset="UTF-8">

  <title>Take Interview</title>

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"  type="text/css" />
<script src="<c:url value="/resources/js/jquery.js" />"></script>
 <script>
 $(document).ready(function() {
$('.submitt').click(function(){
//window.location.href = "file:///C:/Users/I061199/Downloads/interview/codepen_qwIgC/questions.html";
//alert("jquery worked");
});
});
</script>
</head>



<body>

<div id="login">
  <h1 style="font-size:150%;font-weight:500">Take Test</h1>
  <form:form action="questions" commandName="writtenTestCandidate">
    <form:input class="text" path="firstname" placeholder="First Name" />
    <form:input class="text" path="lastname" placeholder="Last Name" />
    <form:input class="text" path="consultancy" placeholder="Consultancy" />
    <form:select id="language" path="primarySkill" >
      <option value="">Select Language</option>
      <option value="Java">Java</option>
      <option value="C">C</option>
      <option value="C#">C#</option>
      <option value="ABAP">ABAP</option>
  </form:select>
    <input class="submitt" type="submit" value="Start"/>
  </form:form>
</div>



  

</body>

</html>