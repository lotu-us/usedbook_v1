package thwjd.usedbook.entity;

import lombok.Data;

@Data
public class Pagination {
    //‘page=3&size=10&sort=id,DESC’
    private BookCategory category;
    private Integer page = 0;           //현재페이지
    private Integer perFirstRow = 0;    //한페이지의 첫번째 게시글
    private Integer perRows = 5;        //한페이지에 보여줄 게시글 수

    private Integer listStartNum;       //한페이지의 리스트 시작번호
    private Integer listEndNum;         //한페이지의 리스트 끝번호
    private Integer listLimit = 5;   //한페이지에 보여줄 리스트 수

    private Integer listLastNum;        //마지막페이지번호
    private String orderString = "createtime desc";
    private Integer preview;
    private Integer next;


    public void setterProcess(){
        setPerFirstRow();
        setListStartNum();
        setListEndNum();
        setPreview();
        setNext();
    }


    public void setCategory(String categoryName) {
        BookCategory categoryUpper = BookCategory.valueOf(categoryName.toUpperCase());
        this.category = categoryUpper;
    }

    public void setListLastNum(double totalPageCount) {
        this.listLastNum = (int) Math.ceil(totalPageCount / this.perRows);
    }

    private void setPerFirstRow() {
        this.perFirstRow = (this.page-1)*this.perRows;
    }

    private void setListStartNum() {
        //7페이지일때는 6~10페이지
        if(this.page % this.listLimit != 0){
            this.listStartNum = (this.page / this.listLimit) * this.listLimit +1;
        }else{
            this.listStartNum = this.page - (this.listLimit - 1);
        }
    }

    private void setListEndNum() {
        //3페이지가 끝인데 5페이지까지 보이면 안됨
        this.listEndNum = this.listStartNum + this.listLimit -1;
        if(this.listLastNum < this.listEndNum){
            this.listEndNum = this.listLastNum;
        }

    }

    private void setPreview() {
        this.preview = this.page -1;
    }

    private void setNext() {
        this.next = this.page +1;
    }
}
