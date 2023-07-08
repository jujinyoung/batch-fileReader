package file.springBatch.batch.chunk.writer;

import file.springBatch.batch.domain.ApiRequestVO;
import file.springBatch.batch.domain.ApiResponseVO;
import file.springBatch.service.AbstractApiService;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class ApiItemWriter3 implements ItemWriter<ApiRequestVO> {
    private final AbstractApiService apiService;

    public ApiItemWriter3(AbstractApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void write(List<? extends ApiRequestVO> items) throws Exception {
        ApiResponseVO responseVO = apiService.service(items);
        System.out.println("responseVO = " + responseVO);
    }
}
