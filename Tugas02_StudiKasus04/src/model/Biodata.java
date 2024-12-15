package model;

public class Biodata {
    private int id;
    private String nama;
    private String alamat;
    private String email;
    private String telepon;

    // Constructor
    public Biodata(int id, String nama, String alamat, String email, String telepon) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.telepon = telepon;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
}
