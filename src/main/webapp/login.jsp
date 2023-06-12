<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
  <script type="text/javascript">
  
     let contextPath="${pagecontext.request.contextpath}";
  
     function  deleteMessage() {
	        document.getElementById("emessage").innerHTML="";
     }
     
     
     
     function  checkUser() {
    	 
	       let usernamev=document.getElementById("username").value;
	       let passwordv=document.getElementById("password").value;
	       
	       //I HAVE TO WRITE FOR AJAX
	       const requestOptions = {
    				method: 'POST',
  			  headers: { 
       					  'Content-Type': 'application/json',
       					  'Accept': 'application/json'
			    },
               body: JSON.stringify({ username:usernamev,password:passwordv})
         };
	       
	       //AJAX CALL
		fetch(contextPath+'/auth', requestOptions)
		    .then(response => response.json())
		    .then(data => {
		    	 console.log(data);
		    	 if(data.code==='200') {
		    		 document.getElementById("cs").innerHTML="Aahah username and password matched";
		    	 }else{
		    		 document.getElementById("cs").innerHTML="Sorry this is not correct, try once again!";
		    	 }
		    });
 } 
  
       function  validate() {
    	       let usernamev=document.getElementById("username").value;
    	       if(usernamev.length==0){
    	    	    document.getElementById("emessage").innerHTML="Username cannot be empty";
    	    	    document.getElementById("username").focus();
    	    	   return;
    	       }
    	       
    	       let passwordv=document.getElementById("password").value;
    	       
    	       if(passwordv.length==0){
   	    	    document.getElementById("emessage").innerHTML="Password cannot be empty";
   	    	    document.getElementById("password").focus();
   	    	   return;
   	       }
    	   
    	    //Now I am submitting form through JavaScript code   
    	   document.forms[0].submit();
    	  
       } 
          
     
  </script>
  
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
         
         <span style="color:red;font-weight: bold;font-size: 20px;"  id="emessage">
         ${message}</span>
        
         <form action="login" method="post">
         
          <div class="form-group" style="width: 60%">
            <label>Username</label>
            <input onkeypress="deleteMessage();" type="text"  id="username"  name="username" class="form-control">
           
         </div>
         
         <div class="form-group" style="width: 60%">
            <label>Password</label>
            <input onkeypress="deleteMessage();" id="password" type="password" name="password" class="form-control">
         </div>
         
           <div class="form-group" style="width: 60%">
			 	 <button type="button" class="btn btn-primary" onclick="validate();">Login</button>
              <button type="reset" class="btn btn-danger">Clear</button>
                     <a href="signups">
                          <button type="button" class="btn btn-warning">All Data!</button>
                     </a>
                     
                   <button type="button" class="btn btn-primary" onclick="checkUser();">Check!!!!</button>
         </div>
         </form>
         <span id="cs" style="color:red;font-size: 22px;font-weight: bold;">Coming Soon</span>
 	  
 </div>

</body>
</html>