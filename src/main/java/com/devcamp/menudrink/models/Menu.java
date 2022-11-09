package com.devcamp.menudrink.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "menu")
@Getter
@Setter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "duong_kinh")
    private int duongKinh;

    @Column(name = "kich_thuoc")
    private String kichThuoc;

    @Column(name = "salad_gram")
    private int salad;

    @Column(name = "so_luong_nuoc_ngot")
    private int soLuongNuoc;

    @Column(name = "so_luong_suon")
    private int suon;

    @Column(name = "thanh_tien")
    private int thanhTien;
}
