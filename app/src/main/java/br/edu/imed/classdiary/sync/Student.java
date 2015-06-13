package br.edu.imed.classdiary.sync;

import android.content.ContentValues;

public class Student {

    private Long id;
    private String name;
    private String ddd;
    private String phone;
    private String address;
    private String email;
    private String password;


    public Student(Long studentId) {
        this.id = studentId;
    }

    public Student(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put("codigo", id);
        values.put("nome", name);
        values.put("ddd", ddd);
        values.put("telefone", phone);
        values.put("endereco", address);
        values.put("email", email);
        values.put("tipo", "2");
        return values;
    }
}

