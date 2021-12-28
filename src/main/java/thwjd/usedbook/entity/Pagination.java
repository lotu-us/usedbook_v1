package thwjd.usedbook.entity;

import lombok.Data;
import thwjd.usedbook.repository.BookPostRepositoryMapper;

@Data
public class Pagination {
    //‘page=3&size=10&sort=id,DESC’
    private BookCategory category;
    private Integer page = 0;
    private Integer offset = 0;
    private Integer limit = 10;
    private String orderString = "createtime DESC";
    private Integer maxPageNum;

    public Pagination(){

    }

    public Pagination(String categoryName, double allPageCount, Integer page){
        setCategory(categoryName);
        setMaxPageNum(allPageCount);
        setOffset(page);
    }

    public void setCategory(String categoryName) {
        BookCategory categoryUpper = BookCategory.valueOf(categoryName.toUpperCase());
        this.category = categoryUpper;
    }

    public void setMaxPageNum(double allPageCount) {
        this.maxPageNum = (int) Math.ceil(allPageCount / this.limit);
    }

    public void setOffset(Integer page) {
        this.offset = (page-1)*10;
    }
}
