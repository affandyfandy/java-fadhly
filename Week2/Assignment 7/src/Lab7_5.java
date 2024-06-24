import java.util.List;

public class Lab7_5<T> {
    private List<T> data;
    private int pageSize;
    private int pageNumber;

    public Lab7_5(List<T> data, int pageSize, int pageNumber) {
        this.data = data;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public List<T> getPageData() {
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, data.size());
        return data.subList(startIndex, endIndex);
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
