<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>SEND AUTHORIZATION CODE</h2>
	<form method="post" action="/email/send.do">
		SENDER NAME: <input type="text" name="senderName" class="form-control" /><br />
		SENDER EMAIL: <input type="text" name="senderEmail" class="form-control" /><br />
		RECIPIENT EMAIL: <input type="text" name="recieverEmail" class="form-control" /><br />
		TITLE: <input type="text" name="subject" class="form-control" /><br />
		CONTENT: <textarea name="message" rows="5" cols="80"></textarea><br /><br />
		<input type="submit" value="SEND" class="btn btn-primary" />
	</form>
	<span style="color:grey;"> ${msg} </span>
</body>
</html>