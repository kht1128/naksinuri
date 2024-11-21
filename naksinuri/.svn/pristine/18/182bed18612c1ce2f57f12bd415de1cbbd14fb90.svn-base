function file_download( fPath, fName ){
        
    if( $( "#down_form" ).length > 0 ){
        
        $("#fPath").val( fPath );
        $("#fName").val( fName );
        
    }else{
    
        var html = "<form action='/download.do' name='down_form' id='down_form' method='post'>";
        html += "<input type='hidden' id='fPath' name='fPath' value='" + fPath + "' />";
        html += "<input type='hidden' id='fName' name='fName' value='" + fName + "' />";
        html += "</form>";
        $( document.body ).append( html );
        
    }
    
    $( "#down_form" ).get(0).submit();
    
}
