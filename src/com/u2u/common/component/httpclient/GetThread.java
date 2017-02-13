package com.u2u.common.component.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;


/**
 * @ClassName: GetThread <br>
 * @Description: 异步Get请求封装 <br>
 * @date 2015年4月14日 下午2:09:27 <br>
 * 
 * @author Freud
 */
public abstract class GetThread implements Runnable
{
    /** The httpClient. */
    private CloseableHttpClient httpClient;
    
    /** The httpClient. */
    private HttpGet httpGet;
    
    /**
     * <p>
     * Discription:[Get httpClient]
     * </p>
     * 
     * @return CloseableHttpClient httpClient.
     */
    public CloseableHttpClient getHttpClient()
    {
        return httpClient;
    }
    
    /**
     * <p>
     * Discription:[Set httpClient]
     * </p>
     * 
     * @param httpClient The httpClient to set.
     */
    public void setHttpClient(CloseableHttpClient httpClient)
    {
        this.httpClient = httpClient;
    }
    
    /**
     * <p>
     * Discription:[Get httpGet]
     * </p>
     * 
     * @return HttpGet httpGet.
     */
    public HttpGet getHttpGet()
    {
        return httpGet;
    }
    
    /**
     * <p>
     * Discription:[Set httpGet]
     * </p>
     * 
     * @param httpGet The httpGet to set.
     */
    public void setHttpGet(HttpGet httpGet)
    {
        this.httpGet = httpGet;
    }
    
    /**
     * 
     * <p>
     * Discription:[异步执行]
     * </p>
     * 
     * @author:[Freud]
     * @update:[2015-8-13] [更改人姓名][变更描述]
     */
    @Override
    public void run()
    {
        try
        {
            
            CloseableHttpResponse response = httpClient.execute(httpGet, new BasicHttpContext());
            HttpEntity entity = response.getEntity();
            if (entity != null)
            {
                this.callBack(new String(EntityUtils.toByteArray(entity), HttpClientUtil.ENCODING));
            }
            else
            {
                throw new RuntimeException("Response为空");
            }
        }
        catch (ClientProtocolException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 
     * <p>
     * Discription:[接口的回调函数]
     * </p>
     * 
     * @param responseContent
     * @author:[Freud]
     * @update:[2015-8-13] [更改人姓名][变更描述]
     */
    public abstract void callBack(String responseContent);
}