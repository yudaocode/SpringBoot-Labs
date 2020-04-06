package cn.iocoder.springcloud.labx17.orderservice.feign.dto;

/**
 * 商品减少库存 DTO
 */
public class ProductReduceStockDTO {

    /**
     * 商品编号
     */
    private Long productId;
    /**
     * 数量
     */
    private Integer amount;

    public Long getProductId() {
        return productId;
    }

    public ProductReduceStockDTO setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public ProductReduceStockDTO setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

}
