<%@ page contentType="text/html" pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<html>
<head><title>www.mldnjava.cn��MLDN�߶�Java��ѵ</title></head>
<body>

<%
    request.setCharacterEncoding("GBK") ;
%>
<%
    List<String> info = (List<String>) request.getAttribute("info") ;
    if(info != null){   // ����Ϣ����
        Iterator<String> iter = info.iterator() ;
        while(iter.hasNext()){
%>
            <h4><%=iter.next()%></h4>
<%
        }
    }
%>
<form action="/dachuang/ReleaseSaleServlet" method="post">
     ���⣺<input type="text" name="goodname"><br>
     ������<input type="text" name="description"><br>
     ͼƬ��<input type="text" name="picture"><br>
     �¾ɳ̶ȣ�<input type="text" name="status"><br>
     ԭ�ۣ�<input type="text" name="sprice"><br>
     �۸�<input type="text" name="price"><br>
     �û�ID��<input type="text" name="userid"><br>
     �����<input type="text" name="bigsort"><br>
     ���׵ص㣺<input type="text" name="place"><br>
    <input type="submit" value="�ύ">
    <input type="reset" value="����">
</form>
</body>
</html>
