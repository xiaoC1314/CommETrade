/**
 * Open a connection to the specified URL, which is
 * intended to provide an XML message.  The specified data
 * is sent to the server as parameters.  This is the same as
 * calling xmlOpen("POST", url, toSend, responseHandler).
 *
 * @param string url    The URL to connect to.
 * @param string toSend The data to send to the server; must be URL encoded.
 * @param function responseHandler The Javascript function handling server response.
 */
function xmlPost(url, toSend)
{
    xmlOpen("POST", url, toSend, tableResponseHandler);
}

/**
 * Open a connection to the specified URL, which is
 * intended to provide an XML message.  No other data is
 * sent to the server.  This is the same as calling
 * xmlOpen("GET", url, null, responseHandler).
 *
 * @param string url    The URL to connect to.
 * @param function responseHandler The Javascript function handling server response.
 */
function xmlGet(url)
{
    xmlOpen("GET", url, null, tableResponseHandler);
}

/**
 * Open a connection to the specified URL, which is
 * intended to respond with an XML message.
 * 
 * @param string method The connection method; either "GET" or "POST".
 * @param string url    The URL to connect to.
 * @param string toSend The data to send to the server; must be URL encoded.
 * @param function responseHandler The Javascript function handling server response.
 */
function xmlOpen(method, url, toSend, responseHandler)
{
    if (window.XMLHttpRequest)
    {
        // browser has native support for XMLHttpRequest object
        req = new XMLHttpRequest();
    }
    else if (window.ActiveXObject)
    {
        // try XMLHTTP ActiveX (Internet Explorer) version
        req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    if(req)
    {
        req.onreadystatechange = responseHandler;
        req.open(method, url, true);
        req.setRequestHeader("content-type","application/x-www-form-urlencoded");
        req.send(toSend);
    }
    else
    {
        alert('Your browser does not seem to support XMLHttpRequest.');
    }
}


function tableResponseHandler()
{
    // Make sure the request is loaded (readyState = 4)
    if (req.readyState == 4)
    {
        // Make sure the status is "OK"
        if (req.status == 200)
        {
         	  var result = req.responseText;
         	  if(result=="") return;
         	  
        	  var temp_array = result.split("\r\n");
        	
            var callback_funname = temp_array[0];
            var control_name = temp_array[1];
            var arrayLength = temp_array[2];    
            if(arrayLength=="") 
            	arrayLength = 2;
        		else 
        			arrayLength = parseInt(arrayLength);       
        			
            for(index=3;index<temp_array.length-1;index=index+arrayLength)
            {
            	if(arrayLength == 2)
            	{
            		eval(callback_funname+"('"+control_name+"','"+temp_array[index]+"','"+temp_array[index+1]+"')");
            	}
            	else
            	{           		
            		var send_array = new Array(arrayLength); 
            		for(j=0; j < arrayLength; j++) send_array[j] = temp_array[index+j];
            		
            		eval(callback_funname+"('"+control_name+"',eval('send_array'))");	
            	}			
            }
        }
        else
        {
            alert(req.statusText);
        }
    }
}