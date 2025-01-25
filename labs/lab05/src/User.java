public abstract class User {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Numele este " + name + " si varsta este " + age;
    }
}

class Student extends User {
    public Student(String name, int age) {
        super(name, age);
    }
}

class Teacher extends User {
    public Teacher(String name, int age) {
        super(name, age);
    }
}

class Librarian extends User {
    public Librarian(String name, int age) {
        super(name, age);
    }
}
