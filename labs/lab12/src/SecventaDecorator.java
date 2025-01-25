public abstract class SecventaDecorator implements ISecventa {
    SecventaCuvant secventa;

    public SecventaDecorator(SecventaCuvant secventa) {
        this.secventa = secventa;
    }

    public abstract String parcurge();
}

interface ISecventa {
    public String parcurge();
}

class SecventaCuvant implements ISecventa {
    private String[] cuvinte;
    private int index = -1;
    public SecventaCuvant(String secventa) {
        cuvinte = secventa.split(" ");
    }

    public String parcurge() {
        if(index >= -1 && index < cuvinte.length - 1) {
            index++;
            return cuvinte[index];
        }
        return null;
    }
}

class SecventaCaracter extends SecventaDecorator {
    int otherIndex = -1;
    public SecventaCaracter(SecventaCuvant secventa) {
        super(secventa);
    }

    public String parcurge() {
        return new String(String.valueOf(secventa.parcurge().charAt(0)));
    }
}

class SecventaMajuscule extends SecventaDecorator {
    public SecventaMajuscule(SecventaCuvant secventa) {
        super(secventa);
    }

    public String parcurge() {
        String temp = secventa.parcurge();
        return temp.toUpperCase();
    }
}