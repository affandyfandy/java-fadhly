public class Lab7_2<T> {
    private T content;

    public Lab7_2(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public void printContent() {
        System.out.println("Box content: " + content);
    }
}
