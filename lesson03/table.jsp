	<%
	String rows = request.getParameter("rows");
	String cols = request.getParameter("cols");
	int rowsP = 0;
	int colsP = 0;
	int count = 0;
	StringBuilder generateT = new StringBuilder();
	
	if(rows==null && cols==null){
	}else if(rows.isEmpty() && cols.isEmpty()){
		out.write("<center><font color='red'>ERROR: NO DATA</font>");
	} else {
		 try{
	rowsP = Integer.parseInt(rows);
	colsP = Integer.parseInt(cols);
	generateT.append("<center><table border='3'>");
	for(int i=0;i<rowsP;i++){
		generateT.append("<tr>");
		for(int j=0;j<colsP;j++){
			
			if(i!=j){
				generateT.append("<td>"+ ++count +"</td>");
			} else {
				generateT.append("<td bgcolor='blue'>"+ ++count +"</td>");
			}
			if(j==colsP-1){
				generateT.append("</tr>");
			}
		}
	}
	generateT.append("</table>");
	out.write(generateT.toString());
	  }catch(NumberFormatException e){		
		out.write("<center><font color='red'>ERROR: INCORRECT DATA</font>");
	  }
	  
	}
	
	%>
	<center>
	<form action="">
		<table>
		<tr><td>Rows</td><td><input type="text" name="rows"></td></tr>
		<tr><td>Cols</td><td><input type="text" name="cols"><br></td></tr>
		<tr><td></td><td align="right"><input type="submit" value="Generate table"></td></tr>
	</form>
