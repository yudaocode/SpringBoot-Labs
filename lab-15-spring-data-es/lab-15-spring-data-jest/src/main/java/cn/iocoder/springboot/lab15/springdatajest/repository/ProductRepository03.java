package cn.iocoder.springboot.lab15.springdatajest.repository;

import cn.iocoder.springboot.lab15.springdatajest.dataobject.ESProductDO;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.util.StringUtils;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

public interface ProductRepository03 extends ElasticsearchRepository<ESProductDO, Integer> {

    default Page<ESProductDO> search(Integer cid, String keyword, Pageable pageable) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        // 筛选条件 cid
        if (cid != null) {
            nativeSearchQueryBuilder.withFilter(QueryBuilders.termQuery("cid", cid));
        }
        // 筛选
        if (StringUtils.hasText(keyword)) {
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] functions = { // TODO 芋艿，分值随便打的
                    new FunctionScoreQueryBuilder.FilterFunctionBuilder(matchQuery("name", keyword),
                            ScoreFunctionBuilders.weightFactorFunction(10)),
                    new FunctionScoreQueryBuilder.FilterFunctionBuilder(matchQuery("sellPoint", keyword),
                            ScoreFunctionBuilders.weightFactorFunction(2)),
                    new FunctionScoreQueryBuilder.FilterFunctionBuilder(matchQuery("categoryName", keyword),
                            ScoreFunctionBuilders.weightFactorFunction(3)),
//                    new FunctionScoreQueryBuilder.FilterFunctionBuilder(matchQuery("description", keyword),
//                            ScoreFunctionBuilders.weightFactorFunction(2)), // TODO 芋艿，目前这么做，如果商品描述很长，在按照价格降序，会命中超级多的关键字。
            };
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(functions)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2F); // TODO 芋艿，需要考虑下 score
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }
        // 排序
        if (StringUtils.hasText(keyword)) { // 关键字，使用打分
            nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        } else if (pageable.getSort().isSorted()) { // 有排序，则进行拼接
            pageable.getSort().get().forEach(sortField -> nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(sortField.getProperty())
                    .order(sortField.getDirection().isAscending() ? SortOrder.ASC : SortOrder.DESC)));
        } else { // 无排序，则按照 ID 倒序
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
        }
        // 分页
        nativeSearchQueryBuilder.withPageable(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize())); // 避免
        // 执行查询
        return search(nativeSearchQueryBuilder.build());
    }

}
