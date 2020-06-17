package cn.iocoder.springboot.lab15.springdatasolr.dataobject;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "new_core")
public class SolrProductDO {

    /**
     * ID 主键
     */
    @Id
    private Integer id;

    /**
     * SPU 名字
     */
    @Indexed(value = "name")
    private String name;
    /**
     * 描述
     */
    @Indexed(value = "description")
    private String description;
    /**
     * 分类编号
     */
    @Indexed(value = "cid")
    private Integer cid;
    /**
     * 分类名
     */
    @Indexed(value = "category_name")
    private String categoryName;

    public Integer getId() {
        return id;
    }

    public SolrProductDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SolrProductDO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SolrProductDO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCid() {
        return cid;
    }

    public SolrProductDO setCid(Integer cid) {
        this.cid = cid;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public SolrProductDO setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    @Override
    public String toString() {
        return "SolrProductDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cid=" + cid +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

}
