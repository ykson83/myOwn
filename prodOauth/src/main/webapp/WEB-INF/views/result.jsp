<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div>
<textarea rows="10" cols="95">${result}</textarea><br>
<button onclick="location.href='oauth/token'">access token 받기</button>
<button onclick="location.href='https://www.googleapis.com/calendar/v3/users/me/calendarList?access_token=${access_token}'">userInfo 받기</button>
<button onclick="location.href='oauth/refreshToken'">refresh token보내기</button>
</div> 
</body>
</html>