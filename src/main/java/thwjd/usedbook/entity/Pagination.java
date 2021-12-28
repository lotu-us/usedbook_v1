package thwjd.usedbook.entity;

import lombok.Data;

@Data
public class Pagination {
    //‘page=3&size=10&sort=id,DESC’
    private BookCategory category;
    private Integer page = 0;           //현재페이지
    private Integer perFirstRow = 0;    //한페이지의 첫번째 게시글
    private Integer perRows = 2;        //한페이지에 보여줄 게시글 수

    private Integer listStartNum;       //한페이지의 리스트 시작번호
    private Integer listEndNum;         //한페이지의 리스트 끝번호
    private Integer listLimit = 5;   //한페이지에 보여줄 리스트 수

    private Integer lastPageNum;        //마지막페이지번호
    private String orderString = "createtime DESC";


    public void setCategory(String categoryName) {
        BookCategory categoryUpper = BookCategory.valueOf(categoryName.toUpperCase());
        this.category = categoryUpper;
    }

    public void setLastPageNum(double totalPageCount) {
        this.lastPageNum = (int) Math.ceil(totalPageCount / this.perRows);
    }

    public void setPerFirstRow(Integer page) {
        this.perFirstRow = (page-1)*this.perRows;
    }

    public void setListStartNum(Integer currentPage) {
        //7페이지일때는 6~10페이지
        if(currentPage % this.listLimit != 0){
            this.listStartNum = (currentPage / this.listLimit) * this.listLimit +1;
        }else{
            this.listStartNum = currentPage - (this.listLimit - 1);
        }
    }

    public void setListEndNum() {
        this.listEndNum = this.listStartNum + this.listLimit -1;
    }


}
