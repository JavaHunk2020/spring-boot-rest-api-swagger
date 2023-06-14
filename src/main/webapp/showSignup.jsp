<!DOCTYPE html>

<%@page import="com.it.dto.SignupDTO"%>
<%@page import="java.util.List"%>
<html>
<head>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
   <header style="background-color: yellow;height: 30px;">
   </header>

 <div  class="container">
 	  <h2>Signup Page</h2>
 	  
 	  <img  src="students_schoool_footer2.png"  style="height: 180px;"/>
        <img  src="students_schoool_footer2.png"  style="height: 180px;"/>
          <img  src="students_schoool_footer2.png"  style="height: 180px;"/>
         <hr/>
        
        <h1>PROFILE DATA!</h1>
        <span style="color:red;font-size: 16px;font-weight: bold;">${message}</span>
        
          <form action="seachRecords">
          	<input type="text"  value="${param.searchText}"  name="searchText" class="form-control" style="width: 30%;display: inline;">
            <button type="submit" class="btn btn-primary" style="margin-top: -5px;">GO!!!</button>
            
             <a href="signups">
             <button type="button" class="btn btn-primary" style="margin-top: -5px;">Clear</button>
             </a>
            </form>
            <hr/>
            
              <%
		 List<SignupDTO> dtos=(List<SignupDTO>)request.getAttribute("signups");
              
              %>
            
         <b>Total Record(s) : <span style="color:blue;"><%=dtos.size()%></span></b>
         <hr/>
         <table class="table table-bordered">
    <thead>
      <tr>
      <th>Pid</th>
        <th>
         <a href="sortData?sort=username&orderBy=asc"> 
           <img alt="" src="ad.png">
        </a>
        Username</th>
        <th>Password</th>
        <th>
        <a href="sortData?sort=email&orderBy=desc"> 
        <img alt="" src="ad.png">
        </a>
          Email
        </th>
         <th>Mobile</th>
         <th>Address</th>
         <th>Created Date</th>
         <th>Action</th>
            <th>Service</th>
      </tr>
    </thead>
    <tbody>
    
      <%
        for(SignupDTO signupDTO : dtos){        
      %>
      <tr>
         <td><%=signupDTO.getPid()%></td>
        <td><%=signupDTO.getUsername()%></td>
       <td><%=signupDTO.getPassword()%></td>
        <td>
        <%=signupDTO.getEmail()%></td>
         <td><%=signupDTO.getMobile()%></td>
          <td><%=signupDTO.getAddress()%></td>
             <td><%=signupDTO.getDoe()%></td>
             <td>
              <a href="dsignup?pid=<%=signupDTO.getPid()%>">
              <button type="button" class="btn btn-danger">DELETE</button>
              </a>
              
              <a href="esignup?pid=<%=signupDTO.getPid()%>&serviceName=<%=signupDTO.getServiceName()%>">
              <button type="button" class="btn btn-primary">EDIT</button>
              </a>
             </td>
             <td>
             <b>
             <%=signupDTO.getServiceName()%></b>
               <img src="data:image/png;base64,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" style="height: 60px;">
             </td>
      </tr>
      
      <% } %>
      
    </tbody>
  </table>
        
 </div>

</body>
</html>