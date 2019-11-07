package cn.iocoder.springboot.lab17.dynamicdatasource.constant;

/**
 * 数据库枚举类
 */
public class DBConstants {

    /**
     * 事务管理器 - 订单库
     */
    public static final String TX_MANAGER_ORDERS = "ordersTransactionManager";
    /**
     * 事务管理器 - 用户库
     */
    public static final String TX_MANAGER_USERS = "usersTransactionManager";

    /**
     * 实体管理器工厂 - 订单库
     */
    public static final String ENTITY_MANAGER_FACTORY_ORDERS = "ordersEntityManagerFactory";
    /**
     * 实体管理器工厂 - 用户库
     */
    public static final String ENTITY_MANAGER_FACTORY_USERS = "usersEntityManagerFactory";

}
