package help.lixin.starlink.core.template;

import help.lixin.starlink.core.dto.PageDTO;
import org.springframework.stereotype.Component;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import help.lixin.response.PageResponse;

@Component
public class QueryTemplate {

    public PageResponse execute(PageDTO pageDTO, IPageCallback callback) {
        try {
            Page page = PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
            if (null != callback) {
                callback.callback();
            }
            PageResponse res = PageResponse.newBuilder() //
                .pageCurrent(page.getPageNum()) //
                .pageSize(page.getPageSize()) //
                .total(page.getTotal()) //
                .records(page.getResult())//
                .build();
            return res;
        } finally {
            PageHelper.clearPage();
        }
    }
}
