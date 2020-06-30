package cn.iocoder.springboot.lab15.springdataelasticsearch.constant;

/**
 * ES 字段分析器的枚举类
 *
 * 关于 IK 分词，文章 https://blog.csdn.net/xsdxs/article/details/72853288 不错。
 * 目前项目使用的 ES 版本是 6.7.1 ，可以在 https://www.elastic.co/cn/downloads/past-releases/elasticsearch-6-7-1 下载。
 * 如果不知道怎么安装 ES ，可以看 https://blog.csdn.net/chengyuqiang/article/details/78837712 简单。
 */
public class FieldAnalyzer {

    /**
     * IK 最大化分词
     *
     * 会将文本做最细粒度的拆分
     */
    public static final String IK_MAX_WORD = "ik_max_word";

    /**
     * IK 智能分词
     *
     * 会做最粗粒度的拆分
     */
    public static final String IK_SMART = "ik_smart";

}
