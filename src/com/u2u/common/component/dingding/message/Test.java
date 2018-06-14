package com.u2u.common.component.dingding.message;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
 
import org.apache.commons.io.IOUtils;
 
public class Test {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String URL = "https://oapi.dingtalk.com/message/send?access_token=5fcfe93b0d2c386dbbe15543a685c384";
        String Json = findXmlInfo();
     
        Json = "{'touser':'deandongming','agentid':'12131159','msgtype':'text','text':{'content':'Value1'}}";
        String postResult =  doHttpPost(Json,URL); 
        System.out.println("postResult:"+postResult);       
         
    }
     
     
    private static String findXmlInfo() {
        // TODO Auto-generated method stub
        return null;
    }
 
 
    @SuppressWarnings("null")
	public static String doHttpPost(String xmlInfo,String URL){         
         System.out.println("发起的数据:"+xmlInfo);       
        byte[] xmlData = xmlInfo.getBytes();            
         InputStream instr  = null; 
         java.io.ByteArrayOutputStream out = null;              
          try{                          
                 URL url = new URL(URL);                
                 URLConnection urlCon = url.openConnection();               
                 urlCon.setDoOutput(true);              
                 urlCon.setDoInput(true);               
                 urlCon.setUseCaches(false);                            
                 urlCon.setRequestProperty("Content-Type", "application/json");       
                 urlCon.setRequestProperty("Content-length",String.valueOf(xmlData.length)); 
                 System.out.println(String.valueOf(xmlData.length));
                 DataOutputStream printout = new DataOutputStream(urlCon.getOutputStream());            
                 printout.write(xmlData);               
                 printout.flush();              
                 printout.close();              
                 instr = urlCon.getInputStream();   
                 byte[] bis = IOUtils.toByteArray(instr);
                 String ResponseString = new String(bis, "UTF-8");  
                if ((ResponseString == null) || ("".equals(ResponseString.trim()))) {
                     System.out.println("返回空");
                    }
               System.out.println("返回数据为:" + ResponseString);
              return ResponseString;    
             
          }catch(Exception e){              
                 e.printStackTrace();
                return "0";
          }             
          finally {             
                 try {          
                        out.close();   
                        instr.close();  
                         
                 }catch (Exception ex) {      
                        return "0";
                     }                  
                 }                  
          }                     
     
 
}
