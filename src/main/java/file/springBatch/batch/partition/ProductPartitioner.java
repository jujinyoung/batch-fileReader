package file.springBatch.batch.partition;

import file.springBatch.batch.domain.ProductVO;
import file.springBatch.batch.job.api.QueryGenerator;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductPartitioner implements Partitioner {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {

        ProductVO[] productList = QueryGenerator.getProductList(dataSource);
        Map<String, ExecutionContext> result = new HashMap<>();

        AtomicInteger index = new AtomicInteger();
        Arrays.asList(productList).stream().forEach(productVO -> {
            ExecutionContext value = new ExecutionContext();

            result.put("partition" + index.getAndIncrement(), value);
            value.put("product", productVO);

        });

        return result;
    }
}
