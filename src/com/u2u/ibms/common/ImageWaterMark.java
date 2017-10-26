package com.u2u.ibms.common;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import sun.misc.BASE64Encoder;

public class ImageWaterMark {

	public static void main(String[] args) {  

	    File srcImgFile = new File("D:/wartmarksrc.jpg");  
	    String logoText = "Passed!";  
	    String srcImageFile = "http://localhost:8089/U2U/styles/mobile/static/2017-04-21/17bdd575-0883-4718-891c-a3a7edc323cf.1492740740110.jpg";  
	    //File outputRotateImageFile = new File("D:/wartmarkresult2.jpg");  
	    //createWaterMarkByText(srcImgFile, logoText, outputImageFile);  
	    
//	    System.out.println("压缩图片开始...");  
	    File srcImgfile = getOnlineImg(srcImageFile);  
	    File distImgFile = new File("tmpDistImg.jpg"); 
//	    System.out.println("压缩前srcfile size:" + srcImgfile.length());  
//	    reduceImg(srcImgfile, diskImgFile, 1000, 1000, (float)0.8);  
//	    System.out.println("压缩后distfile size:" + diskImgFile.length()); 	    
	    
	   // System.out.println(distImgFile.getAbsolutePath());
	   // distImgFile.delete();
	    //System.out.println(addText(getOnlineImg(srcImageFile), logoText, 20));
	    
	    reduceImg(srcImgfile, distImgFile, 300, 200, null);
	    System.out.println(addText(distImgFile, logoText, 20));
	    
	    //System.out.println(imageToBase64(diskImgFile.getAbsolutePath()));
	} 	
	
	public static String addTxtMark(String srcImageFile,String markText,  
	        double degree){
		//String logoText = "身 份 证 验 证 通 过 ！";  
	    File srcImgfile = getOnlineImg(srcImageFile);  
	    File diskImgFile = new File("tmpDistImg.jpg"); 
	    diskImgFile.delete();
	    diskImgFile = new File("tmpDistImg.jpg"); 
	    //reduceImg(srcImgfile, diskImgFile, 1000, 1000, (float)0.1);
	    reduceImg(srcImgfile, diskImgFile, 300, 200, null);
	    return addText(diskImgFile, markText, degree);		
	}
	
	public static File getOnlineImg(String srcImgFile){
	    String tmpImgPath = "";
	    File tmpFile = new File("tmpSrcImg.jpg");
	    tmpFile.delete();
	    tmpFile = new File("tmpSrcImg.jpg");
		try{
	        tmpImgPath = tmpFile.getAbsolutePath();
	        //System.out.println("Temp Img for ID Verify: " + tmpImgPath);
	        URL url = new URL(srcImgFile);  
	        //打开链接  
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
	        //设置请求方式为"GET"  
	        conn.setRequestMethod("GET");  
	        //超时响应时间为5秒  
	        //conn.setConnectTimeout(5 * 1000);  
	        //通过输入流获取图片数据  
	        InputStream inStream = conn.getInputStream();  
	        //得到图片的二进制数据，以二进制封装得到数据，具有通用性  
	        byte[] data = readInputStream(inStream);  
	        //new一个文件对象用来保存图片，默认保存当前工程根目录  
	        //创建输出流  
	        FileOutputStream outStream = new FileOutputStream(tmpFile);  
	        //写入数据  
	        outStream.write(data);  
	        //关闭输出流  
	        outStream.close(); 
	        
		}catch (Exception e) {  
	        e.printStackTrace();  
	    }
		
		return tmpFile;
	}
	
	public static String addText(File srcImgFile, String logoText,  
	        double degree) {  
		//degree, 旋转角度
		//File srcImageFile = new File(srcImgFile);  
	    OutputStream os = null;  
	    String retImgStr = ""; 
	    String tmpImgPath = "";
	    try {  
	    	tmpImgPath = srcImgFile.getAbsolutePath();
	        Image srcImg = ImageIO.read(srcImgFile);  
	        BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),  
	                srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);  
	        Graphics2D g = buffImg.createGraphics();  
	        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,  
	                RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
	        g.drawImage(  
	                srcImg.getScaledInstance(srcImg.getWidth(null),  
	                        srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,  
	                null);  
	        if (degree>0) {  
	            g.rotate(Math.toRadians(degree),  
	                    (double) buffImg.getWidth() / 2,  
	                    (double) buffImg.getHeight() / 2);  
	        }  
	        g.setColor(Color.DARK_GRAY);  
	        g.setFont(new Font("宋体", Font.BOLD, 36));  
	        float alpha = 0.8f;  
	        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,  
	                alpha));  
	        g.drawString(logoText, buffImg.getWidth()/4, buffImg.getHeight()/2);  
	        g.dispose();  
	        

	        //System.out.println(directory.getCanonicalPath());//获取标准的路径 
	        //System.out.println(directory.getAbsolutePath());//获取绝对路径 

	        os = new FileOutputStream(tmpImgPath);  
	        // 生成图片  
	        ImageIO.write(buffImg, "JPG", os);  
	        retImgStr = imageToBase64(tmpImgPath);
	        //imageToBase64(imageToBase64(tmpImgPath));
	        srcImgFile.delete();
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }
	    
	    return retImgStr;
	}	
		
	
    // 将 file 转化为 Base64
    public static String imageToBase64(String path) {
        InputStream in = null;  
        byte[] data = null;  
        //读取图片字节数组  
        try   
        {  
            in = new FileInputStream(path);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        //对字节数组Base64编码  

        BASE64Encoder encoder = new BASE64Encoder();  
        //System.out.println(encoder.encode(data));        
        return encoder.encode(data);//返回Base64编码过的字节数组字符串 
    }	
    
    
    public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        while( (len=inStream.read(buffer)) != -1 ){  
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
        inStream.close();  
        //把outStream里的数据写入内存  
        return outStream.toByteArray();  
    }   
    
     
    /** 
     * 采用指定宽度、高度或压缩比例 的方式对图片进行压缩 
     * @param imgsrc 源图片地址 
     * @param imgdist 目标图片地址 
     * @param widthdist 压缩后图片宽度（当rate==null时，必传） 
     * @param heightdist 压缩后图片高度（当rate==null时，必传） 
     * @param rate 压缩比例  
     */  
    public static void reduceImg(File imgsrcFile, File imgDistFile, int widthdist,  
            int heightdist, Float rate) {  
        try {  
            // 检查文件是否存在  
            if (!imgsrcFile.exists()) {  
                return;  
            }  
            // 如果rate不为空说明是按比例压缩  
            if (rate != null && rate > 0) {  
                // 获取文件高度和宽度  
                int[] results = getImgWidth(imgsrcFile);  
                if (results == null || results[0] == 0 || results[1] == 0) {  
                    return;  
                } else {  
                    widthdist = (int) (results[0] * rate);  
                    heightdist = (int) (results[1] * rate);  
                }  
            }  
            // 开始读取文件并进行压缩  
            Image src = javax.imageio.ImageIO.read(imgsrcFile);  
            BufferedImage tag = new BufferedImage((int) widthdist,  
                    (int) heightdist, BufferedImage.TYPE_INT_RGB);  
  
            tag.getGraphics().drawImage(  
                    src.getScaledInstance(widthdist, heightdist,  
                            Image.SCALE_SMOOTH), 0, 0, null);  
  
            FileOutputStream out = new FileOutputStream(imgDistFile);  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
            encoder.encode(tag);  
            out.close();  
  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
    }  
    
    /** 
     * 获取图片宽度 
     * @param file 图片文件 
     * @return 宽度 
     */  
    public static int[] getImgWidth(File file) {  
        InputStream is = null;  
        BufferedImage src = null;  
        int result[] = { 0, 0 };  
        try {  
            is = new FileInputStream(file);  
            src = javax.imageio.ImageIO.read(is);  
            result[0] = src.getWidth(null); // 得到源图宽  
            result[1] = src.getHeight(null); // 得到源图高  
            is.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }    
}
