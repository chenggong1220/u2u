package com.u2u.framework.license;

public class licenseVerifyTest {
	public static void main(String[] args){
		VerifyLicense vLicense = new VerifyLicense();
		try{
			
			vLicense.setParam("verifyparam.properties");
			
			vLicense.verify();
		}
		catch(Exception er){
		    System.out.println(er.getLocalizedMessage());
			er.printStackTrace();
		}

	}
	
	public void test(){
	       VerifyLicense vLicense = new VerifyLicense();
	        try{
	            
	            vLicense.setParam("verifyparam.properties");
	            
	            vLicense.verify();
	        }
	        catch(Exception er){
	            System.out.println(er.getLocalizedMessage());
	            er.printStackTrace();
	        }
	    
	}
}
