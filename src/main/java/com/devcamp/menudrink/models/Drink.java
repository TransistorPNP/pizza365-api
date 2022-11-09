package com.devcamp.menudrink.models;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "drinks")
@Getter
@Setter
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ma_nuoc_uong", unique = true, nullable = false)
    @NotNull(message = "Thiếu mã nước uống")
    private String maNuocUong;

    @Column(name = "ten_nuoc_uong")
    @NotEmpty(message = "Thiếu tên nước uống")
    private String tenNuocUong;

    @Column(name = "don_gia")
    private long donGia;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ngay_tao")
    @CreatedDate
    private Date ngayTao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ngay_cap_nhat")
    @LastModifiedDate
    private Date ngayCapNhat;
}
