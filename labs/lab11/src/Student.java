public class Student {
    private String name;
    private float media;

    public Student(String name, float media) {
        this.name = name;
        this.media = media;
    }

    public float getMedia() {
        return media;
    }

    public String toString() {
        return name + " " + media;
    }
}