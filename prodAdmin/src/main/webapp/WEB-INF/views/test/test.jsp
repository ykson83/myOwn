<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <script src="https://unpkg.com/vue"></script> -->
<script src="https://unpkg.com/vue/dist/vue.min.js"></script>
<title>Insert title here</title>
</head>
<body>
 <div id="simple">
 <h2>{{message}}</h2>
 </div>
</body>
<script>
   /*  var app = new Vue({
    el: '#app',
    data: {
        message: '안녕하세요 Vue!'
    }
    }); */
    
    var model = {
    		message: "first vue app"
    }
    
    var simple = new Vue ({
    	el : "#simple",
    	data : model
    })
</script>
</html>