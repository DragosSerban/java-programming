public class Book implements Buyer, Cloneable {
    private String title;
    private String[] authors;
    private int year;
    private String publisher;
    private int recommendedAge;
    private float price;
    private int crt;

    public Book() {
        this.authors = new String[3];
    }

    public Book(String title, int year, String publisher, int recommendedAge, float price) {
        this.title = title;
        this.authors = new String[3];
        this.year = year;
        this.publisher = publisher;
        this.recommendedAge = recommendedAge;
        this.price = price;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getRecommendedAge() {
        return this.recommendedAge;
    }

    public void setRecommendedAge(int recommendedAge) {
        this.recommendedAge = recommendedAge;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String[] getAuthors() {
        return this.authors;
    }

    public void addAuthor(String author) {
        if(crt + 1 == this.authors.length) {
            String[] tempArray = new String[2 * this.authors.length];
            for(int i = 0; i < crt; i++)
                tempArray[i] = this.authors[i];
            this.authors = tempArray;
        }
        this.authors[crt++] = author;
    }

    public String toString() {
        String authorsString = new String();
        for(int i = 0; i < this.crt - 1; i++)
            authorsString += this.authors[i] + ", ";
        authorsString += this.authors[crt-1];
        return "Cartea " + this.title + " a fost scrisa de " + authorsString + " in " + this.year + ", publicata la "
                + this.publisher + ", costa " + this.price + " si este recomandata persoanelor cu varsta de "
                + this.recommendedAge + " ani";
    }

    public Object clone() {
        Book b = new Book();
        b.setRecommendedAge(this.getRecommendedAge());
        b.setYear(this.getYear());
        b.setTitle(this.getTitle());
        b.setPrice(this.getPrice());
        b.setPublisher(this.getPublisher());
        int crt1 = 0;
        while(true) {
            if(this.getAuthors()[crt1] != null)
                b.addAuthor(this.getAuthors()[crt1++]);
            else
                break;
        }
        return (Object)b;
    }

    @Override
    public float buyBook(User user) {
        if (user instanceof Student)
            this.price -= 0.05f * this.price;
        else if (user instanceof Teacher)
            this.price -= 0.1f * this.price;
        else if (user instanceof Librarian)
            this.price -= 0.2f * this.price;
        return this.price;
    }
}
