package org.chenwei.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.chenwei.Bean.BookInfoExcelModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;

@Slf4j
public class BookInfoExcelListener extends AnalysisEventListener<BookInfoExcelModel> {

    private BookInfoExcelModel bookInfoExcelModel;
    //BookInfoService 提供插入数据库的接口
    //private BookInfoService bookInfoService;
    //TODO 这个引入了线程池，当Excel数据量大时，可以快速处理。可以去掉
    //private ExecutorService executorService;


    /**
     * 自定义用于暂时存储data
     * 可以通过实例获取该值
     */
    private LinkedList<BookInfoExcelModel> datas = new LinkedList<BookInfoExcelModel>();

    //要使用线程池，可以放开这个注释
//    public BookInfoExcelListener(BookInfoExcelModel bookInfoExcelModel, BookInfoService bookInfoService,ExecutorService executorService) {
//        this.bookInfoExcelModel = bookInfoExcelModel;
//        this.bookInfoService = bookInfoService;
//        this.executorService = executorService;
//    }

    public BookInfoExcelListener(BookInfoExcelModel bookInfoExcelModel) {
        this.bookInfoExcelModel = bookInfoExcelModel;
    }

    public BookInfoExcelListener() {
    }

    @Override
    public void invoke(BookInfoExcelModel bookInfoExcelModel, AnalysisContext analysisContext) {
        //readerId由 a-z|A-Z|0-9|_ 组成的是正确数据  可以过滤数据
//        if(Pattern.matches("\\w*",blackListExcel.getReaderId())){
        datas.add(bookInfoExcelModel);
//        }
        //根据自己业务做处理
    }


    @SneakyThrows
    @Override
    @Transactional
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        datas.forEach(data -> {
            log.info("date:{}",new Date());
           //考虑到导入书籍数量可能上十万，此处使用线程池
           // executorService.execute(()->{
           //    bookInfoService.insertByExcel(data);
           // });
        });
    }

}