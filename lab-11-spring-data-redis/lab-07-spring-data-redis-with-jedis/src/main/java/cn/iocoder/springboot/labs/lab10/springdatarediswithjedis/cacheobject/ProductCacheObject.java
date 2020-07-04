package cn.iocoder.springboot.labs.lab10.springdatarediswithjedis.cacheobject;

/**
 * 商品缓存对象
 */
public class ProductCacheObject {

    /**
     * 产品编号
     */
    private Integer id;
    /**
     * 产品名
     */
    private String name;
    /**
     * 产品分类编号
     */
    private Integer cid;

    public Integer getId() {
        return id;
    }

    public ProductCacheObject setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductCacheObject setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getCid() {
        return cid;
    }

    public ProductCacheObject setCid(Integer cid) {
        this.cid = cid;
        return this;
    }

    @Override
    public String toString() {
        return "ProductCacheObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cid=" + cid +
                '}';
    }

}
