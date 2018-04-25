package com.boot.stickershop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_files")
@Setter
@Getter
public class ProductFile {
    public ProductFile(){ regtime = LocalDateTime.now(); }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "save_file_name")
    private String saveFileName;

    private String contentType;
    private Long length;
    private LocalDateTime regtime;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public void addProduct(Product product){
        this.product = product;
        if(!product.getProductFiles().contains(this)){
            product.getProductFiles().add(this);
        }
    }
}
