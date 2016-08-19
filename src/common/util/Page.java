package common.util;

import java.io.*;


public class Page implements Serializable {

    public static final int DEFAULT_PAGE_SIZE = 13;

    private int pageIndex;//��ǰҳ
    private int pageSize;//һҳ���ٸ�
    private int totalCount;//һ�����ٸ�
    private int pageCount;//һ������ҳ

    public Page(int pageIndex, int pageSize) {    
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageSize < 1) {
            pageSize = 1;
        }
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public Page(int pageIndex) {
        this(pageIndex, DEFAULT_PAGE_SIZE);
    }

    public void nextpage() {
        if (pageIndex < this.pageCount) {
            this.pageIndex++;
        }
    }

    public void prepage() {
        if (pageIndex > 1) {
            this.pageIndex--;
        }
    }

    public void firstpage() {
        this.pageIndex = 1;

    }

    public void lastpage() {
        this.pageIndex = pageCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getFirstResult() {
        return (pageIndex - 1) * pageSize;
    }

    public boolean getHasPrevious() {
        return pageIndex > 1;
    }

    public boolean getHasNext() {
        return pageIndex < pageCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
        // adjust pageIndex:
        if (totalCount == 0) {
            if (pageIndex != 1) {
                throw new IndexOutOfBoundsException("Page index out of range.");
            }
        } else {
            if (pageIndex > pageCount) {
                throw new IndexOutOfBoundsException("Page index out of range.");
            }
        }
    }

    public boolean isEmpty() {
        return totalCount == 0;
    }

    private void readObject(ObjectInputStream ois) throws IOException,
            ClassNotFoundException {
        ois.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

}
