package com.u2u.framework.util.image;

/**
 * 
 * @ClassName: ImagePropertyVO <br>
 * @Description: 图像基类 <br>
 * @date 2015-3-2 下午2:30:08 <br>
 * 
 * @author Dean
 */
public class ImagePropertyVO
{
    private int width;
    
    private int height;
    
    /**
     * @return Returns the width.
     */
    public int getWidth()
    {
        return width;
    }
    
    /**
     * @param width - The width to set.
     */
    public void setWidth(int width)
    {
        this.width = width;
    }
    
    /**
     * @return Returns the height.
     */
    public int getHeight()
    {
        return height;
    }
    
    /**
     * @param height - The height to set.
     */
    public void setHeight(int height)
    {
        this.height = height;
    }
    
}
