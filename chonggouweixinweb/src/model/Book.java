package model;

/**
 * 书籍类的model
 */
public class Book {
    private int bookID;
    private String bookName;
    private String author;
    private int price;
    private String introduce;
    private String type;

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrice() {
        return price;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getType() {
        return type;
    }
}
