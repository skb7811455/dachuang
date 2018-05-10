<%@ page contentType="text/html" pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<html>
<head><title>www.mldnjava.cn，MLDN高端Java培训</title></head>
<body>

<%
    request.setCharacterEncoding("GBK") ;
%>
<%
    List<String> info = (List<String>) request.getAttribute("info") ;
    if(info != null){   // 有信息返回
        Iterator<String> iter = info.iterator() ;
        while(iter.hasNext()){
%>
            <h4><%=iter.next()%></h4>
<%
        }
    }
%>
<form action="/dachuang/ReleaseSaleServlet" method="post">
     标题：<input type="text" name="goodname"><br>
     描述：<input type="text" name="description"><br>
     图片：<input type="text" name="picture"><br>
     新旧程度：<input type="text" name="status"><br>
     原价：<input type="text" name="sprice"><br>
     价格：<input type="text" name="price"><br>
     用户ID：<input type="text" name="userid"><br>
     大类别：<input type="text" name="bigsort"><br>
     交易地点：<input type="text" name="place"><br>
    <input type="submit" value="提交">
    <input type="reset" value="重置">
</form>
</body>
</html>
