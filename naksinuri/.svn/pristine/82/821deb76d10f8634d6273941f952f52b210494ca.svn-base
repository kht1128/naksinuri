package egovframework.naksinuri_original.let.naksinuri.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerifyRecaptcha {

	private static final Logger LOGGER = LoggerFactory.getLogger(VerifyRecaptcha.class);
	
	 public static final String url = "https://www.google.com/recaptcha/api/siteverify";
	    private final static String USER_AGENT = "Mozilla/5.0";
	    private static String secret = ""; //local
	 
	    public static void setSecretKey(String key){
	    	secret = key;
	    }
	    
	    public static boolean verify(String gRecaptchaResponse) throws IOException {
	        if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
	            return false;
	        }
	        
	        HttpsURLConnection con = null;
	        OutputStream outputstream = null;
	        DataOutputStream wr = null;	    
	        InputStream inputstream = null;
	        InputStreamReader inputstreamreader = null;
	        BufferedReader in = null;
	        try{
		        URL obj = new URL(url);
		        con = (HttpsURLConnection) obj.openConnection();
		 
		        // add reuqest header
		        con.setRequestMethod("POST");
		        con.setRequestProperty("User-Agent", USER_AGENT);
		        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		 
		        String postParams = "secret=" + secret + "&response="
		                + gRecaptchaResponse;
		 
		        // Send post request
		        con.setDoOutput(true);
		        outputstream = con.getOutputStream();
		        wr = new DataOutputStream(outputstream);
		        wr.writeBytes(postParams);
		        wr.flush();
        		wr.close();
        		outputstream.close();
	            
		        int responseCode = con.getResponseCode();
		        LOGGER.debug("responseCode : "+responseCode);
		        inputstream = con.getInputStream();
		        inputstreamreader = new InputStreamReader(inputstream);
		        in = new BufferedReader(inputstreamreader);
		        String inputLine;
		        StringBuffer response = new StringBuffer();
		 
		        while ((inputLine = in.readLine()) != null) {
		            response.append(inputLine);
		        }
		        
		        in.close();
		        inputstreamreader.close();
		        inputstream.close();
		        con.disconnect();

		        // print result
		        LOGGER.debug(response.toString());
		         
		        //parse JSON response and return 'success' value
		        JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
		        JsonObject jsonObject = jsonReader.readObject();
		        jsonReader.close();
		         
	        	return jsonObject.getBoolean("success");
	        } catch(ProtocolException e2) {
	        	LOGGER.debug("[fail ProtocolException] "+e2.toString());
	        	return false;
	        } catch(SocketTimeoutException e3) {
	        	LOGGER.debug("[fail SocketTimeoutException] "+e3.toString());
	        	return false;
	        } catch(IOException e1){
	        	LOGGER.debug("[fail IOException] "+e1.toString());
	        	return false;
	        } catch(Exception e){
	        	LOGGER.debug("[fail process] "+e.toString());
	            return false;
	        } finally {
	        	try {
	        		wr.close();
	            } catch(IOException e1){
	            	LOGGER.debug("fail wr IOException");
	            } catch(Exception e) {
	            	LOGGER.debug("fail wr Exception "+e.toString());
	        	}
	        	try {
	        		outputstream.close();
	            } catch(IOException e1){
	            	LOGGER.debug("fail outputstream IOException");
	            } catch(Exception e) {
	            	LOGGER.debug("fail outputstream Exception "+e.toString());
	        	}
	        	try {
	        		in.close();
	            } catch(IOException e1){
	            	LOGGER.debug("fail in IOException");
	            } catch(Exception e) {
	            	LOGGER.debug("fail in Exception "+e.toString());
	        	}
	        	try {
	        		inputstreamreader.close();
	            } catch(IOException e1){
	            	LOGGER.debug("fail inputstreamreader IOException");
	            } catch(Exception e) {
	            	LOGGER.debug("fail inputstreamreader Exception "+e.toString());
	        	}
	        	try {
	        		inputstream.close();
	            } catch(IOException e1){
	            	LOGGER.debug("fail inputstream IOException");
	            } catch(Exception e) {
	            	LOGGER.debug("fail inputstream Exception "+e.toString());
	        	}
	        	try {
	        		con.disconnect();
	            } catch(Exception e) {
	            	LOGGER.debug("fail disconnect Exception "+e.toString());
	        	}
			}
	}
}

