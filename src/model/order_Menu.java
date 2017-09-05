package model;

import java.io.Serializable;

/**
 * Created by chewzhijie on 25/3/16.
 */
public class order_Menu implements Serializable
{
    Integer menu_ID;
    Integer quantity;

    public order_Menu(Integer menu_ID,Integer quantity)
    {
        this.menu_ID = menu_ID;
        this.quantity = quantity;
    }

    public order_Menu()
    {

    }

    //Getter
    public Integer getMenu_ID()
    {
        return this.menu_ID;
    }

    public  Integer getQuantity()
    {
        return  this.quantity;
    }

    //Setter
    public void setMenu_ID(Integer menu_ID)
    {
        this.menu_ID = menu_ID;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

}
